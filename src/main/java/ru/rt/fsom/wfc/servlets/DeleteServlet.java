package ru.rt.fsom.wfc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import ru.rt.fsom.wfc.data.tickets.TicketAttaches;

@WebServlet("/delete")
public class DeleteServlet extends BaseServlet{
    private static final long serialVersionUID = 1224672188334273288L;
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
	String result = attacheFacade.deleteAttacheFromTicket(attache, tocken);
	if (StringUtils.isBlank(result)){
	    response.setStatus(HttpServletResponse.SC_OK);
	} else {
	    errors.add(result);
	    setStatusResponse(response, errors, HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
	}
    }
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
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
            out.println("<h1>FileUploadServlet use method POST at " + request.getContextPath() + "/delete</h1>");
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