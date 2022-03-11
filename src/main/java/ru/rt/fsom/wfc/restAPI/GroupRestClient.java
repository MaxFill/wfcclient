package ru.rt.fsom.wfc.restAPI;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class GroupRestClient extends BaseRestClient{    
    private static final String BASE_PATH = "groups";
       
    public GroupRestClient(String apiUrl) {
	super(apiUrl, BASE_PATH);
    }

    public String getGroupById(String id, String token) throws ClientErrorException {
	Response response = webTarget.path("id")
	    .queryParam("id", id).queryParam("token", token)
	    .request(MediaType.APPLICATION_JSON)
	    .get();
	if (Response.Status.OK != response.getStatusInfo()) return null;
	return response.readEntity(String.class);
    }

    public String getGroupByName(String groupName, String token) throws ClientErrorException {	
	Response response = webTarget.path("name")
	    .queryParam("name", groupName).queryParam("token", token)
	    .request(MediaType.APPLICATION_JSON)
	    .get();
	if (Response.Status.OK != response.getStatusInfo()) return null;
	return response.readEntity(String.class);
    }

    public String getAllGroups(String token) throws ClientErrorException {	
	Response response = webTarget
	    .queryParam("token", token)
	    .request(MediaType.APPLICATION_JSON)
	    .get();
	//LOGGER.log(Level.INFO, "REST getAllGroups response={0}", response.getStatusInfo().getReasonPhrase());
	if (Response.Status.OK != response.getStatusInfo()) return null;
	return response.readEntity(String.class);
    }	
    	
    public String getTicketGroups(Integer ticketId, String token) throws ClientErrorException {	
	Response response = webTarget.path("ticket")
	    .queryParam("ticketId", ticketId)
	    .queryParam("token", token)
	    .request(MediaType.APPLICATION_JSON)
	    .get();
	//LOGGER.log(Level.INFO, "REST getTicketGroups response={0}", response.getStatusInfo().getReasonPhrase());
	if (Response.Status.OK != response.getStatusInfo()) return null;
	return response.readEntity(String.class);
    }
}