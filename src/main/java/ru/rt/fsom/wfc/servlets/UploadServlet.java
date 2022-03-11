package ru.rt.fsom.wfc.servlets;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.apache.commons.lang3.StringUtils;
import ru.rt.fsom.wfc.conf.Conf;

@WebServlet("/upload")
public class UploadServlet extends BaseServlet {
    private static final long serialVersionUID = 1224672188334273288L;
    
    @EJB private Conf conf;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String tocken = checkJWTAuth(request, response);
	if (tocken == null) return;
	Integer ticketId = null;
	Integer userId = null;
	try {
	    ticketId = Integer.valueOf(request.getParameter("ticketId"));
	    userId = Integer.valueOf(request.getParameter("userId"));
	} catch (NumberFormatException ex){
	}
	Set<String> result = new HashSet<>();
	if (ticketId == null || userId == null){
	    result.add("The required parameter [ticketId] or [userId] is missing!");
	    setStatusResponse(response, result, HttpServletResponse.SC_BAD_REQUEST);
	    return;
	}
	boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
	    String errMsg = "Request must be multipart!";
	    result.add(errMsg);
	    setStatusResponse(response, result, HttpServletResponse.SC_BAD_REQUEST);
	    LOGGER.log(Level.INFO, errMsg);
            return;
        }
        DiskFileItemFactory factory = new DiskFileItemFactory();
        factory.setSizeThreshold(conf.getMaxUploadFileSize()/10);
        File tempDir = (File)getServletContext().getAttribute("javax.servlet.context.tempdir");
        factory.setRepository(tempDir);
        ServletFileUpload upload = new ServletFileUpload(factory);
        upload.setSizeMax(conf.getMaxUploadFileSize()); // 1024 * 1024 * 10
        upload.setHeaderEncoding("UTF-8"); 
	try {
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();
            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();
		//LOGGER.log(Level.INFO, "upoloadFile name={0}", new Object[]{item.getName()});
		result = doUpload(item, tocken, ticketId, userId);
		if (result.isEmpty()){
		    result.add("The file has been successfully uploaded to the minio storage!");
		    setStatusResponse(response, result, HttpServletResponse.SC_OK);
		} else {
		    setStatusResponse(response, result, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
	    }
        } catch (FileUploadException | IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
	    Set<String> errors = new HashSet<>();
	    errors.add(ex.getMessage());
	    setStatusResponse(response, errors, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
        } 
    }
    
    private Set<String> doUpload(FileItem uploadFile, String tocken, Integer ticketId, Integer userId){
	Set<String> errors = new HashSet<>();
	Map<String, Object> params = new HashMap<>();        
	params.put("contentType", uploadFile.getContentType());
        params.put("fileName", uploadFile.getName());
        params.put("size", uploadFile.getSize());
        params.put("authorId", userId);
	params.put("ticketId", ticketId);
	try {
	    attacheFacade.uploadAtache(params, uploadFile.getInputStream(), tocken, errors);
	} catch (IOException ex) {
	    errors.add(ex.getMessage());
	    LOGGER.log(Level.SEVERE, null, ex.getMessage());
	}
	return errors;
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>FSOM FileMinioServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>FileUploadServlet use method POST at " + request.getContextPath() + "</h1>");
	    out.println("<h2>Use multy part form data request!</h2>");
	    out.println("<h3>Specify the your auth token in the query parameter [JWT] or in the request header</h3>");
            out.println("<h3>Upload your file in the parameter [uploadfile]</h3>");
	    out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "ru.rt.fsom.s3attachservice servlet";
    }

}
