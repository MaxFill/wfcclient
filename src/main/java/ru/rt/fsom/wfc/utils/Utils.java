package ru.rt.fsom.wfc.utils;

import java.math.BigInteger;
import ru.rt.fsom.wfc.data.dict.SysParams;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.context.FacesContext;

public final class Utils {
    private static final Logger LOGGER = Logger.getLogger(SysParams.LOGGER_NAME);              
	
    public static String generateUID(){
	return UUID.randomUUID().toString();
    }    	    
            
    @SuppressWarnings("unchecked")
    public static <T> T findBean(String beanName, FacesContext context) {
        return (T) context.getApplication().evaluateExpressionGet(context, "#{" + beanName + "}", Object.class);
    }

    public static BigInteger strToBigInteger(String value) {
        BigInteger result = null;
        try {
            result = new BigInteger(value);
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
        return result;
    }
    
}