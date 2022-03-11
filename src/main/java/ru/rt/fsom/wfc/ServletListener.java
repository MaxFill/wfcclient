package ru.rt.fsom.wfc;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class ServletListener implements ServletContextListener {
    
    @Override
    public void contextInitialized(ServletContextEvent sce) {	
	//Params.LOGGER.log(Level.INFO, "ServletContextEvent contextInitialized!");
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
    }
}