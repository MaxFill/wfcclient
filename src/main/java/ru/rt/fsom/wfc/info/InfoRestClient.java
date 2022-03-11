package ru.rt.fsom.wfc.info;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ru.rt.fsom.wfc.restAPI.BaseRestClient;

/**
 *
 * @author Maksim.Filatov
 */
public class InfoRestClient extends BaseRestClient{
    private static final String BASE_PATH = "info";

    public InfoRestClient(String apiUrl) {
	super(apiUrl, BASE_PATH);
    }
    
    public int getDbStatus() throws ClientErrorException {
	Response response = webTarget.path("dbstatus").request(MediaType.APPLICATION_JSON).get();	
	return response.getStatus();	
    }
}
