package ru.rt.fsom.wfc.restAPI;

import com.google.gson.reflect.TypeToken;
import java.util.logging.Level;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ru.rt.fsom.wfc.data.tickets.TicketAttaches;

public class AttachedRestClient extends BaseRestClient{
    private static final String BASE_PATH = "attached";

    public AttachedRestClient(String apiUrl) {
	super(apiUrl, BASE_PATH);
    }
    
    public String getAttachedByTicketId(int ticketId, String token) throws ClientErrorException {
	Response response = webTarget.path("ticket")
	    .queryParam("ticketId", ticketId)
	    .queryParam("token", token)
	    .request(MediaType.APPLICATION_JSON)
	    .get();
	if (Response.Status.OK != response.getStatusInfo()) return null;
	return response.readEntity(String.class);
    }
    
    public String getAttachedById(int id, String token) throws ClientErrorException {
	Response response = webTarget.path("id")
	    .queryParam("id", id).queryParam("token", token)
	    .request(MediaType.APPLICATION_JSON)
	    .get();
	if (Response.Status.OK != response.getStatusInfo()) return null;
	return response.readEntity(String.class);
    }
	
    public Object createAttached(TicketAttaches attache, String token) throws ClientErrorException{	
	Response response = webTarget.path("add")
	    .queryParam("token", token)
	    .request(MediaType.APPLICATION_JSON)
	    .put(Entity.json(attache));	
	if (Response.Status.OK != response.getStatusInfo()){
	    return response.readEntity(String.class);
	}
	String gsonResult = response.readEntity(String.class);	
	//LOGGER.log(Level.INFO, "REST Client json ={0}", gsonResult);
	TicketAttaches attached = getGson().fromJson(gsonResult, new TypeToken<TicketAttaches>(){}.getType());
	return attached;
    }
      
    public String deleteAttached(int id, String token) throws ClientErrorException{	
	Response response = webTarget.path("delete")
	    .queryParam("id", id).queryParam("token", token)
	    .request(MediaType.APPLICATION_JSON)
	    .delete();
	LOGGER.log(Level.INFO, "REST Client deleteAttached Response.Status = {0}", response.getStatusInfo().getReasonPhrase());
	if (Response.Status.OK == response.getStatusInfo()){
	    return null;
	}
	return response.readEntity(String.class); //return error msg
    }
}