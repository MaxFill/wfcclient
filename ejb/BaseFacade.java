package ru.rt.fsom.wfc.ejb;

import ru.rt.fsom.wfc.conf.Conf;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.logging.Logger;
import javax.ejb.EJB;
import ru.rt.fsom.wfc.data.dict.SysParams;

public abstract class BaseFacade {
    protected static final Logger LOGGER = Logger.getLogger(SysParams.LOGGER_NAME);
    
    @EJB protected Conf conf; 
    
    protected Gson getGson(){
	return new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() { 
	    @Override
	    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
		  return new Date(jsonElement.getAsJsonPrimitive().getAsLong()); 
	    } 
	}).create();
    }  
}