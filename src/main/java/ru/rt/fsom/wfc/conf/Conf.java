package ru.rt.fsom.wfc.conf;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.Enumeration;
import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.jar.Attributes;
import java.util.jar.JarFile;
import java.util.jar.Manifest;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import org.apache.commons.lang3.StringUtils;
import ru.rt.fsom.wfc.data.dict.SysParams;

@Singleton
@Startup
public class Conf {    
    protected static final Logger LOGGER = Logger.getLogger(SysParams.LOGGER_NAME);
    
    private static final int DEFAULT_UPLOAD_MAX_SIZE = 1000000;
    private static final int ERR_MIN_LENGHT = 1024;
    private static final String DEFAULT_UPLOAD_URL = "/opt/wildfly/standalone/data/attachments/";    
    private static final String REST_API_URI = "http://fsoma.ks.rt.ru:8080/WFCServices/rest";
    private static final String MINIO_URI = "http://localhost:9000";
    
    private ResourceBundle properties;    
    private String versionInfo;
    private String minioURL;
    private String minioAccessKey;
    private String minioSecretKey;
    
    @PostConstruct
    private void init(){
	initConfFile();
	loadVersionInfo();
    }

    public String gerRestApiURL(){
	return getStrPropertyByKey("fsomwfc_api_url", REST_API_URI);
    }
    
    public String getVersionInfo() {
	return versionInfo;
    }
    
    public String getMinioURL(){
	if (minioURL == null){
	    minioURL = getStrPropertyByKey("minio_url", MINIO_URI);
	}
	return minioURL;
    }
    
    public String getMinioSecretKey(){
	if (minioSecretKey == null){
	    minioSecretKey = getStrPropertyByKey("minio_secret_key", "");
	}
	return minioSecretKey;
    }
    
    public String getMinioAccesKey(){
	if (minioAccessKey == null){
	    minioAccessKey = getStrPropertyByKey("minio_access_key", "");
	}
	return minioAccessKey;
    }
    
    public boolean isUseMinio(){
	return StringUtils.isNoneBlank(getMinioURL());
    }
    
    private int getErrMinLenght(){
	return ERR_MIN_LENGHT;
    }
    
    public String getShortMsg(String longMsg){
	String shortMsg = "";
	if (longMsg != null){
	    shortMsg = longMsg.substring(0, Math.min(getErrMinLenght(), longMsg.length()));
	}
	return shortMsg;
    }

    public String getUploadPath(){	
	return getStrPropertyByKey("upload_url", DEFAULT_UPLOAD_URL);
    }
    
    public int getMaxUploadFileSize(){
	return getIntPropertyByKey("upload_max_size", DEFAULT_UPLOAD_MAX_SIZE);
    }
    
    /* *** privates *** */
    
    private void loadVersionInfo() {	
	Enumeration resEnum;
	try {
	    resEnum = Thread.currentThread().getContextClassLoader().getResources(JarFile.MANIFEST_NAME);
	    while (resEnum.hasMoreElements()) {
		try {
		    URL url = (URL)resEnum.nextElement();
		    InputStream is = url.openStream();
		    if (is != null) {
			Manifest manifest = new Manifest(is);
			Attributes attrs = manifest.getMainAttributes();
			if (attrs.getValue("build_name") != null){
			    StringBuilder sb = new StringBuilder();
			    sb.
				append("{").
				append("name:'").append(attrs.getValue("build_name")).append("', ").
				append("version:'").append(attrs.getValue("build_version")).append("', ").
				append("date:'").append(attrs.getValue("build_date")).append("', ").
				append("specification:'").append(attrs.getValue("build_specif")).append("'").
				append("}");
			    LOGGER.log(Level.INFO, "Version info loaded: {0}", sb.toString());
			    versionInfo = sb.toString();
			}
		    }		    
		}
		catch (IOException ex) {
		    LOGGER.log(Level.SEVERE, null, ex);
		}
	    }
	} catch (IOException ex){
	    LOGGER.log(Level.SEVERE, null, ex);
	}
    }
    
    private void initConfFile(){
        try {
            File props_path = new File(System.getProperty("jboss.server.config.dir"));
            URL[] urls = {props_path.toURI().toURL()};
            ClassLoader loader = new URLClassLoader(urls);
            properties = ResourceBundle.getBundle("FSOM", Locale.ROOT, loader);
        } catch (MalformedURLException ex) {
            LOGGER.log(Level.SEVERE, "error loading config file!", ex);
        }
    }
    
    private String getStrPropertyByKey(final String key, final String defaultValue){       
        String result = defaultValue;
        if (null != properties){
            try { 
               result = properties.getString(key);
            }
            catch (MissingResourceException | ClassCastException ignoreIt){}//ignore this and return default value
        }
        return result;
    }  
    
    private Integer getIntPropertyByKey(final String key, final int defaultValue){       
        Integer result = defaultValue;
        if (null != properties){
            try { 
               result = Integer.valueOf(properties.getString(key));
            }
            catch (MissingResourceException | ClassCastException ignoreIt){}//ignore this and return default value
        }
        return result;
    } 
}