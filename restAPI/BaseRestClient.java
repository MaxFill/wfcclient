package ru.rt.fsom.wfc.restAPI;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.logging.Logger;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.WebTarget;
import ru.rt.fsom.wfc.data.dict.SysParams;
import javax.ws.rs.client.ClientBuilder;

public abstract class BaseRestClient {
    protected static final Logger LOGGER = Logger.getLogger(SysParams.LOGGER_NAME);

    protected final WebTarget webTarget;
    protected final Client client; 
    
    public BaseRestClient(String apiUrl, String path) {
	this.client = ClientBuilder.newClient();
	this.webTarget = client.target(apiUrl).path(path);
    }
       
    public void close() {
	client.close();
    }
    
    protected Gson getGson(){
	return new GsonBuilder().registerTypeAdapter(Date.class, new JsonDeserializer<Date>() { 
	    @Override
	    public Date deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context) throws JsonParseException {
		  return new Date(jsonElement.getAsJsonPrimitive().getAsLong()); 
	    } 
	}).create();
    } 
}