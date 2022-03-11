package ru.rt.fsom.wfc.info;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.rt.fsom.wfc.data.dict.SysParams;

/**
 *
 * @author Maksim.Filatov
 */
@WebServlet("/info")
public class InfoServlet extends HttpServlet {
    protected static final Logger LOGGER = Logger.getLogger(SysParams.LOGGER_NAME);
    
    @EJB private InfoFacade facade;
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //LOGGER.log(Level.INFO, "DoGet!");	
	response.setStatus(facade.getDbStatus());
	response.setContentType("text/html;charset=UTF-8");
	try (PrintWriter out = response.getWriter()) {
	    InetAddress inetAddress = InetAddress.getLocalHost();	    
	    String ipv4 = inetAddress.getHostAddress();
	    out.println(ipv4);
	}
    }
}
