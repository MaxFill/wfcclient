package ru.rt.fsom.wfc.restAPI;

import java.util.logging.Level;
import javax.ws.rs.ClientErrorException;
import ru.rt.fsom.wfc.data.tickets.TicketFilter;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ru.rt.fsom.wfc.restAPI.BaseRestClient;

public class UserRestClient extends BaseRestClient{
    private static final String BASE_PATH = "user";

    public UserRestClient(String apiUrl) {
	super(apiUrl, BASE_PATH);
    }
    
    public String remoteLogin(String userName, char[] password){
	Response response = webTarget.path("login")
	    .queryParam("name", userName)
	    .queryParam("pwd", String.valueOf(password))
	    .request(MediaType.APPLICATION_JSON)
            .get();
	if (Response.Status.OK != response.getStatusInfo()) return null;
	return response.readEntity(String.class);
    }
    
    public String saveFilter(TicketFilter filter, String token) throws ClientErrorException{	
	Response response = webTarget.path("filter").path("save")
                .queryParam("token", token)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(filter));	
	//LOGGER.log(Level.INFO, "REST saveFilter Response.Status = {0}", response.getStatusInfo().getReasonPhrase());
	if (Response.Status.OK != response.getStatusInfo()){
	    return null;
	}
	return response.readEntity(String.class);
    }
    
    public String loadFilters(int userId, String token) throws ClientErrorException{	
	Response response = webTarget.path("filters")
                .queryParam("userId", userId).queryParam("token", token)
                .request(MediaType.APPLICATION_JSON)
                .get();
	if (Response.Status.OK != response.getStatusInfo()) return null;
	return response.readEntity(String.class);
    }
    
    public String deleteFilter(TicketFilter filter, String token) throws ClientErrorException{
	Response response = webTarget.path("filter").path("delete")
                .queryParam("token", token)
                .request(MediaType.APPLICATION_JSON)
                .put(Entity.json(filter));
	LOGGER.log(Level.INFO, "REST deleteFilter Response.Status = {0}", response.getStatusInfo().getReasonPhrase());
	if (Response.Status.OK != response.getStatusInfo()) return null;
	return response.readEntity(String.class);
    }
}
