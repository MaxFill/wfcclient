package ru.rt.fsom.wfc.restAPI;

import java.util.logging.Level;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import ru.rt.fsom.wfc.data.tickets.TicketFilter;

public class TicketRestClient extends BaseRestClient{
    private static final String BASE_PATH = "ticket";

    public TicketRestClient(String apiUrl) {
	super(apiUrl, BASE_PATH);
    }

    public String getTicketById(String id, String token) throws ClientErrorException {
	Response response = webTarget.queryParam("id", id)
	    .queryParam("token", token)
	    .request(MediaType.APPLICATION_JSON)
	    .get();
	if (Response.Status.OK != response.getStatusInfo()) return null;
	return response.readEntity(String.class);
    }

    public String getTicketParamByName(int ticketId, String paramName, String token) throws ClientErrorException {	
	Response response = webTarget.path("param")
	    .queryParam("id", ticketId)
	    .queryParam("name", paramName)
	    .queryParam("token", token)
	    .request(MediaType.APPLICATION_JSON)
	    .get();
	if (Response.Status.OK != response.getStatusInfo()) return null;
	return response.readEntity(String.class);
    }

    public String getTicketParam(int ticketId, String token) throws ClientErrorException {	
	Response response = webTarget.path("params")
	    .queryParam("id", ticketId)
	    .queryParam("token", token)
	    .request(MediaType.APPLICATION_JSON)
	    .get();
	if (Response.Status.OK != response.getStatusInfo()) return null;
	return response.readEntity(String.class);
    }

    /**
     * Изменение статуса тикета с отправкой в IL данных для изменени статуса task 
     * @param ticketId - идентификатор 
     * @param userName - имя пользователя
     * @param status - согласно документации NOKIA WFClient
     * @param token
     * @return - Текст ошибки если возникла ошибка или null если выполнено успешно
     * @throws ClientErrorException 
     */
    public String changeTicketStatus(int ticketId, int status, String userName, String token) throws ClientErrorException {	
	Response response = webTarget.path("status").path("change")
	    .queryParam("id", ticketId)
	    .queryParam("status", status)
	    .queryParam("token", token)
	    .request(MediaType.APPLICATION_JSON).get();
	if (Response.Status.OK != response.getStatusInfo()) return null;
	return response.readEntity(String.class);
    }
    
    public String getTickets(TicketFilter filter, String token) throws ClientErrorException{
	Response response = webTarget.path("find")
	    .queryParam("token", token)
	    .request(MediaType.APPLICATION_JSON)
	    .put(Entity.json(filter));
	//LOGGER.log(Level.INFO, "Client Response.Status = {0}", response.getStatusInfo().getReasonPhrase());
	if (Response.Status.OK != response.getStatusInfo()) return null;
	return response.readEntity(String.class);
    }
    
}