package ru.rt.fsom.wfc;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

public class SessionListener implements HttpSessionListener {
    protected static final Logger LOGGER = Logger.getLogger(SessionListener.class.getName());    
     
    @Override
    public void sessionCreated(HttpSessionEvent event) {
        //LOGGER.log(Level.INFO, "HttpSessionListener: Session is created!");
    }

    @Override
    public void sessionDestroyed(HttpSessionEvent se){
	//LOGGER.log(Level.INFO, "HttpSessionListener: Session closed!");
    }   

}