package ru.rt.fsom.wfc.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.lang3.StringUtils;
import ru.rt.fsom.wfc.ejb.AttacheFacade;

public class BaseServlet extends HttpServlet{
    protected static final String JWTOKEN = "token";
    protected static final Logger LOGGER = Logger.getLogger(BaseServlet.class.getName());
    
    @EJB protected AttacheFacade attacheFacade;
    
    protected String checkJWTAuth(HttpServletRequest request, HttpServletResponse response) throws IOException{
	String tocken = request.getParameter(JWTOKEN);
	if (StringUtils.isBlank(tocken)){
	    tocken = request.getHeader(JWTOKEN);
	}
	Set<String> errMsg = new HashSet<>();
	if (StringUtils.isBlank(tocken)){
	    errMsg.add("Parameter [token] not found!");
	    setStatusResponse(response, errMsg, HttpServletResponse.SC_UNAUTHORIZED);
	    return null;
	}	
	if (tocken == null) {
	    setStatusResponse(response, errMsg, HttpServletResponse.SC_UNAUTHORIZED);
	    return null;
	}
	return tocken;
    }
    
    protected void setStatusResponse(HttpServletResponse response, Set<String> errMsg, int httpStatus) throws IOException{
	response.setStatus(httpStatus);
	response.setContentType("text/html;charset=UTF-8");
	try (PrintWriter out = response.getWriter()) {
	    out.println(String.join(",", errMsg));
	}
    }
}
