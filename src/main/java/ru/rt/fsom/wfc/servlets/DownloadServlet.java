package ru.rt.fsom.wfc.servlets;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.rt.fsom.wfc.data.tickets.TicketAttaches;
import static ru.rt.fsom.wfc.uibeans.BaseFormBean.LOGGER;
import ru.rt.fsom.wfc.utils.MsgUtils;

@WebServlet("/download")
public class DownloadServlet extends BaseServlet{
    private static final long serialVersionUID = 1224672188334273288L;
       
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	String tocken = checkJWTAuth(request, response);
	if (tocken == null) return;
	Integer attacheId = null;
	try {
	    attacheId = Integer.valueOf(request.getParameter("attacheId"));
	} catch (NumberFormatException ex){
	}
	Set<String> errors = new HashSet<>();
	if (attacheId == null){
	    errors.add("The required parameter [attacheId] is missing!");
	    setStatusResponse(response, errors, HttpServletResponse.SC_BAD_REQUEST);
	    return;
	}
	TicketAttaches attache = attacheFacade.findAttacheById(attacheId, tocken);
	if (attache == null){
	    errors.add("Attachment with Id=" + attacheId + " not found!");
	    setStatusResponse(response, errors, HttpServletResponse.SC_BAD_REQUEST);
	    return;
	}
	try {
	    Map<String, Object> params = new HashMap<>();
	    params.put("openType", "attachment");
	    attacheFacade.attacheDownLoad(response, attache, params);
	} catch (UnsupportedEncodingException | FileNotFoundException | NumberFormatException ex) {
	    response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	    errors.add(ex.getMessage());
	    LOGGER.log(Level.SEVERE, "Exception: {0}", ex.getMessage());
	}

    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	processRequest(request, response);
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>FSOM FileUploadServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>FileUploadServlet use method GET at " + request.getContextPath() + "/download</h1>");
	    out.println("<h3>Specify the file name in the parameter [fileName]</h3>");
	    out.println("<h3>Specify the your auth token in the parameter [JWT] or in the request header</h3>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    public String getServletInfo() {
        return "ru.rt.fsom.s3attachservice servlet";
    }
}
