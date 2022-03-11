package ru.rt.fsom.wfc;

import java.io.IOException;
import java.net.URL;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.application.ViewExpiredException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import ru.rt.fsom.wfc.data.dict.SysParams;

public class RedirectFilter implements Filter {    
    private static final Logger LOGGER = Logger.getLogger(RedirectFilter.class.getName());
    private static final String PRIME_URL     = "javax.faces.resource";
    private static final String RESOURCE_URL  = "/faces/resources/"; 
    
    public RedirectFilter() {
    }    
    
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {        
    }
    
    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;        
        HttpSession session = request.getSession(false);
        
	final String userId = (session != null) ? (String) session.getAttribute(SysParams.USER_LOGIN) : null;
        final String ctxPath = request.getContextPath();        
        final String serverURL = new URL(request.getScheme(), request.getServerName(), request.getServerPort(), "").toString();
        final String reqURL = request.getRequestURI();

        if (reqURL.contains(PRIME_URL) || reqURL.contains(RESOURCE_URL)){
            chain.doFilter(request, response);
            return;
        }      
        
        if (reqURL.contains(SysParams.LOGIN_PAGE)){ 
            chain.doFilter(request, response);
            return;
        }
        
	if (reqURL.contains(SysParams.LOGOUT_PAGE)){ 
            chain.doFilter(request, response);
            return;
        }
		
	if (reqURL.contains(SysParams.EXPIRE_PAGE)){ 
            chain.doFilter(request, response);
            return;
        }
		
        if (reqURL.equals(ctxPath + "/")){          
            StringBuilder sb = new StringBuilder();
            sb.append(serverURL).append(ctxPath).append("/faces").append(SysParams.MAIN_PAGE);
            response.sendRedirect(sb.toString());
            //LOGGER.log(Level.INFO, "redirect={0}",sb.toString());
	    return;   
        }
               
        if (userId == null){
            if ("partial/ajax".equals(request.getHeader("Faces-Request"))){  
                response.setContentType("text/xml");
                response.setCharacterEncoding("UTF-8");
                //LOGGER.log(Level.INFO, "redirect partial/ajax");
		chain.doFilter(request, response);
                return;
            } else {
                String targetUrl = reqURL.replaceAll(ctxPath, "").replaceAll("/faces", "").replaceAll("/", "%2F");
                StringBuilder loginURL = new StringBuilder();
                loginURL.append(serverURL).append(ctxPath).append("/faces");
                loginURL.append(SysParams.LOGIN_PAGE).append("?from=").append(targetUrl).append(makeParams(request.getParameterMap()));
                Map<String,String[]> params = request.getParameterMap();
                if (params.containsKey("docId")){
                    String[] param = params.get("docId");
                    loginURL.append("?docId=").append(param[0]);
                }                
                response.sendRedirect(loginURL.toString());                
                return;
            }
        }
        
        try {
            chain.doFilter(request, response);
        } 
        catch (ServletException e) {
            if (e.getRootCause() instanceof ViewExpiredException) {
                String errorURL = serverURL + ctxPath + "/faces" + SysParams.EXPIRE_PAGE;
                response.sendRedirect(errorURL);
            } else {
                throw e;
            }
        }
    }
    
    private String makeParams(Map<String, String[]> paramMap){
        StringBuilder result = new StringBuilder();
        if (paramMap.containsKey("itemId")){
            String[] values = paramMap.get("itemId");
            result.append("?itemId=").append(values[0]);
        }
	if (paramMap.containsKey(SysParams.USER_LOGIN)){
            String[] values = paramMap.get(SysParams.USER_LOGIN);
            result.append("?").append(SysParams.USER_LOGIN).append("=").append(values[0]);
        }
        return result.toString();
    }
        
    @Override
    public void destroy() {        
    }  
    
    @Override
    public String toString() {
        return "RedirectFilter";
    }
}