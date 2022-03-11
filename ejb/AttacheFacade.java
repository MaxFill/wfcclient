package ru.rt.fsom.wfc.ejb;

import com.amazonaws.AmazonClientException;
import com.amazonaws.AmazonServiceException;
import com.amazonaws.ClientConfiguration;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.client.builder.AwsClientBuilder;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.model.DeleteObjectRequest;
import com.amazonaws.services.s3.model.GetObjectRequest;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.S3Object;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import javax.ejb.Stateless;
import javax.faces.context.FacesContext;
import javax.mail.internet.MimeUtility;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.ClientErrorException;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import ru.rt.fsom.wfc.data.tickets.TicketAttaches;
import ru.rt.fsom.wfc.data.tickets.TicketData;
import ru.rt.fsom.wfc.data.users.User;
import ru.rt.fsom.wfc.restAPI.AttachedRestClient;

@Stateless
public class AttacheFacade extends BaseFacade{
    
    public List<TicketAttaches> findAttaches(TicketData ticket, String token){
	AttachedRestClient restClient = new AttachedRestClient(conf.gerRestApiURL());
	String gsonResult = restClient.getAttachedByTicketId(ticket.getTicketId(), token);	    
	//LOGGER.log(Level.INFO, "attache gson = {0}", gsonResult);
	if (gsonResult == null) return new ArrayList<>();
	return getGson().fromJson(gsonResult, new TypeToken<ArrayList<TicketAttaches>>(){}.getType());
    }    
        
    public TicketAttaches findAttacheById(int attacheId, String token){
	//LOGGER.log(Level.INFO, "facade findAttacheById ...");
	AttachedRestClient restClient = new AttachedRestClient(conf.gerRestApiURL());
	String gsonResult = restClient.getAttachedById(attacheId, token);	    
	//LOGGER.log(Level.INFO, "attache gson = {0}", gsonResult);
	if (gsonResult == null) return null;
	return getGson().fromJson(gsonResult, TicketAttaches.class);
    }
    
    public void attacheDownLoad(HttpServletResponse response, TicketAttaches attache, Map<String, Object> params) throws FileNotFoundException, UnsupportedEncodingException {       
        InputStream inputStream;
	if (conf.isUseMinio()){
	    String bucketName = makeBucketName(attache.getGuid());
	    inputStream = minioDownload(attache.getName(), bucketName);
	} else {
	    final String filePathName = conf.getUploadPath() + attache.getFullName();
	    inputStream = new FileInputStream(filePathName);	    
	}
	doDownload(response, inputStream, attache, (String) params.get("openType"));
    }
    
    synchronized public TicketAttaches uploadAtache(Map<String, Object> params, InputStream inputStream, String token, Set<String> errors) {
	String result;
	AttachedRestClient restClient = null;
        LOGGER.log(Level.INFO, "facade uploadAtache ...");
	final String fileName = (String) params.get("fileName");
        final String fileExt = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();	   	
	TicketAttaches attache = null;
	try {
	    attache = new TicketAttaches();
	    attache.setName(fileName);
	    attache.setTicketId((Integer) params.get("ticketId"));
	    attache.setExtension(fileExt);	
	    attache.setType((String) params.get("contentType"));
	    attache.setFileSize((Long) params.get("size"));
	    attache.setAuthor((Integer) params.get("authorId"));
	    attache.setDateCreate(new Date());	
	    restClient = new AttachedRestClient(conf.gerRestApiURL());
	    Object restResult = restClient.createAttached(attache, token);
	    if (restResult instanceof String){	//возникла ошибка записи метаданных в базу
		LOGGER.log(Level.INFO, "facade attache error = {0}", restResult);
		result = getGson().fromJson((String)restResult, String.class);
		errors.add(result);
		return null;
	    }
	    TicketAttaches newAttached = (TicketAttaches) restResult;
	    if (conf.isUseMinio()){ //upload to minio storage
		String bucketName = makeBucketName(newAttached.getGuid());
		result = minioUpload(inputStream, bucketName, fileName);
		if (StringUtils.isNoneBlank(result)){ //if minio error ...
		    String delResult = restClient.deleteAttached(attache.getId(), token); //delete record
		    LOGGER.log(Level.INFO, "error upload! delete attache result ={0}", delResult); 
		    errors.add(result);
		    return null;
		}
	    } else { //upload to local files storage
		final String guid = attache.getGuid();
		StringBuilder sb = new StringBuilder(conf.getUploadPath());
		sb.append(guid.substring(0, 2)).append(File.separator).append(guid.substring(2, 4));                               
		Files.createDirectories(Paths.get(sb.toString()));       
		sb.append(File.separator).append(guid).append(".").append(fileExt);	
		Files.copy(inputStream, Paths.get(sb.toString()));
	    }	    
	    LOGGER.log(Level.INFO, "facade successfully add attached id= {0}", newAttached.getId()); 
        } catch (Exception ex) {
	    errors.add(ex.getMessage());
            LOGGER.log(Level.SEVERE, null, ex);
	    if (restClient != null && attache != null){
		restClient.deleteAttached(attache.getId(), token); //delete record
	    }
	} finally {
	    if (restClient != null){
		restClient.close();
	    }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException ex) {
                    LOGGER.log(Level.SEVERE, null, ex);
                }
            }
        }
	return attache;
    }
    
    public void deleteAttaches(TicketData ticket, List<TicketAttaches> attaches, User user, String token){
        if (attaches != null){
            attaches.forEach(attache -> {
		deleteAttacheFromTicket(attache, token);
		ticket.getAttacheses().remove(attache);
	    });
        }
    }
	
    synchronized public String deleteAttacheFromTicket(TicketAttaches attache, String token){
        //LOGGER.log(Level.INFO, "facade deleteAttacheFromTicket ...");
	String result = null;
	try{   
            if (conf.isUseMinio()){ //if file was upload to minio storage
		String bucketName = makeBucketName(attache.getGuid());
		result = minioRemove(attache.getName(), bucketName);
		if (StringUtils.isNoneBlank(result)){
		    return result; //error occurred in minio
		}
	    } else {
		final String fileName = conf.getUploadPath() + attache.getFullName();
		File file = new File(fileName);
		file.delete();
	    }
	    AttachedRestClient restClient = new AttachedRestClient(conf.gerRestApiURL());
	    String gsonResult = restClient.deleteAttached(attache.getId(), token);	    
	    if (gsonResult != null) return gsonResult;
        } catch (ClientErrorException ex){
            LOGGER.log(Level.SEVERE, null, ex);
	    result = ex.getMessage();
        }
	return result;
    }
 
    /* privates */    
    
    private void doDownload(HttpServletResponse response, InputStream inputStream, TicketAttaches attache, String openType) throws UnsupportedEncodingException{	
	if (inputStream == null) throw new IllegalArgumentException("нет данных для файла!");
	String contentType =  attache.getType();
	String fileName = attache.getName();
	long contentLenght = attache.getFileSize();
	//LOGGER.log(Level.INFO, "facade doDownload ... contentType={0} fileName={1} contentLenght={2}", new Object[]{contentType, fileName, contentLenght});
	fileName = MimeUtility.encodeText(fileName, "UTF-8", null);		    
	response.reset();
	response.setHeader("Content-Length", String.valueOf(contentLenght));
	response.setCharacterEncoding("UTF-8");
	response.setHeader("Content-Disposition", openType + "; filename=\"" + fileName + "\"");
	response.setHeader("Access-Control-Allow-Credentials", "true");
	response.setHeader("Access-Control-Allow-Headers", "accept, authorization, content-type, x-requested-with");
	response.setHeader("Access-Control-Allow-Methods", "GET, POST, OPTIONS, PUT, DELETE");
	response.setHeader("Access-Control-Max-Age", "1");
	if (StringUtils.isNoneBlank(contentType)){
	    response.setHeader("Content-Type", contentType + "; charset=UTF-8");
	} else {
	    response.setHeader("Content-Type", "application/octet-stream; charset=UTF-8");
	}
	try (OutputStream outputStream = response.getOutputStream()) {		
	    byte[] bytesBuffer = new byte[2048];
	    int bytesRead;
	    while ((bytesRead = inputStream.read(bytesBuffer)) > 0) {
		outputStream.write(bytesBuffer, 0, bytesRead);
	    }
	    outputStream.flush();
	    outputStream.close();
	    response.setStatus(HttpServletResponse.SC_OK);	    
	} catch (IOException ex) {
	    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    LOGGER.log(Level.SEVERE, ex.getMessage());
	} 
    }

    private AmazonS3 getS3Client() {	
        AWSCredentials credentials = new BasicAWSCredentials(conf.getMinioAccesKey(), conf.getMinioSecretKey());
        //LOGGER.log(Level.INFO, "getS3Client = {0}, {1}", new Object[]{conf.getMinioAccesKey(), conf.getMinioSecretKey()}); 
	    
	ClientConfiguration clientConfiguration = new ClientConfiguration();
        clientConfiguration.setSignerOverride("AWSS3V4SignerType");

        AmazonS3 s3Client = AmazonS3ClientBuilder
                .standard()
                .withEndpointConfiguration(new AwsClientBuilder.EndpointConfiguration(conf.getMinioURL(), Regions.US_EAST_1.name()))
                .withPathStyleAccessEnabled(true)
                .withClientConfiguration(clientConfiguration)
                .withCredentials(new AWSStaticCredentialsProvider(credentials))
                .build();
	return s3Client;
    }
    
    private String minioUpload(InputStream inputStream, String bucketName, String fileName) throws IOException{
	LOGGER.log(Level.INFO, "minioUpload started for bucketName {0}", bucketName); 
	String result = "";
	File tempFile = File.createTempFile("minio_", ".tmp");
        try {
	    FileUtils.copyInputStreamToFile(inputStream, tempFile); 
	    if (!getS3Client().doesBucketExist(bucketName)){
		LOGGER.log(Level.INFO, "minioUpload createBucket = {0}", bucketName);
		getS3Client().createBucket(bucketName);
	    }
	    LOGGER.log(Level.INFO, "minioUpload putObject! {0} {1}", new Object[]{fileName, tempFile});
            getS3Client().putObject(new PutObjectRequest(bucketName, fileName, tempFile));
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " + "means your request made it "
                    + "to Amazon S3, but was rejected with an error response" + " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
	    result = "Internal error! AmazonServiceException: " + ase.getErrorCode();
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " + "means the client encountered " + "an internal error while trying to "
                    + "communicate with S3, " + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
	    result = "Internal error! AmazonClientException: " + ace.getMessage();
        } finally {
	    if(tempFile.exists()) {
		tempFile.delete();
	    }
	}
	return result;
    }
    
    //для каждого тикета создаётся свой bucket, пользователи получают доступ к файлам тикета через доступ к карточке тикета
    private String makeBucketName(String guid) throws ClassCastException{
	return ("ticket-" + guid).toLowerCase();
    }
    
    private String minioRemove(String fileName, String bucketName){
	LOGGER.log(Level.INFO, "minioRemove start: bucketName={0}, fileName={1}", new Object[]{bucketName, fileName}); 
	String result = null;
	try {
	    getS3Client().deleteObject(new DeleteObjectRequest(bucketName, fileName));
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " + "means your request made it "
                    + "to Amazon S3, but was rejected with an error response" + " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
	    result = "Internal error! AmazonServiceException: " + ase.getErrorCode();
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " + "means the client encountered " + "an internal error while trying to "
                    + "communicate with S3, " + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
	    result = "Internal error! AmazonClientException: " + ace.getMessage();
        }
	return result;
    }
    
    private InputStream minioDownload(String fileName, String bucketName){
	LOGGER.log(Level.INFO, "minioDownload start: bucketName={0}, fileName={1}", new Object[]{bucketName, fileName}); 
        try {
            GetObjectRequest rangeObjectRequest = new GetObjectRequest(bucketName, fileName);
            S3Object objectPortion = getS3Client().getObject(rangeObjectRequest);
            return objectPortion.getObjectContent();
        } catch (AmazonServiceException ase) {
            System.out.println("Caught an AmazonServiceException, which " + "means your request made it "
                    + "to Amazon S3, but was rejected with an error response" + " for some reason.");
            System.out.println("Error Message:    " + ase.getMessage());
            System.out.println("HTTP Status Code: " + ase.getStatusCode());
            System.out.println("AWS Error Code:   " + ase.getErrorCode());
            System.out.println("Error Type:       " + ase.getErrorType());
            System.out.println("Request ID:       " + ase.getRequestId());
        } catch (AmazonClientException ace) {
            System.out.println("Caught an AmazonClientException, which " + "means the client encountered " + "an internal error while trying to "
                    + "communicate with S3, " + "such as not being able to access the network.");
            System.out.println("Error Message: " + ace.getMessage());
        }
	return null;
    }
}