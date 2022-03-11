package ru.rt.fsom.wfc.ejb;

import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ru.rt.fsom.wfc.data.tickets.TicketGroup;
import ru.rt.fsom.wfc.data.tickets.TicketData;
import ru.rt.fsom.wfc.restAPI.GroupRestClient;

@Stateless
public class GroupFacade extends BaseFacade{        
    
    public TicketGroup findGroupByName(String name, String token){	
	GroupRestClient client = new GroupRestClient(conf.gerRestApiURL());
	String gsonResult = client.getGroupByName(name, token);
	client.close();
	//LOGGER.log(Level.INFO, "findGroupByName gson = {0}", gsonResult);	
	if (gsonResult == null) return null;
	return getGson().fromJson(gsonResult, TicketGroup.class);
    }
    
    public TicketGroup findGroupById(String ticketId, String token){	
	GroupRestClient client = new GroupRestClient(conf.gerRestApiURL());
	String gsonResult = client.getGroupById(ticketId, token);
	client.close();
	//LOGGER.log(Level.INFO, "findGroupById gson = {0}", gsonResult);	
	if (gsonResult == null) return null;
	return getGson().fromJson(gsonResult, TicketGroup.class);
    }
    
    public List<TicketGroup> findAllGroups(String token){
	GroupRestClient client = new GroupRestClient(conf.gerRestApiURL());
	String gsonResult = client.getAllGroups(token);
	client.close();
	//LOGGER.log(Level.INFO, "findAllGroups gson = {0}", gsonResult);	
	if (gsonResult == null) return new ArrayList<>();
	List<TicketGroup> result = getGson().fromJson(gsonResult, new TypeToken<ArrayList<TicketGroup>>(){}.getType());
	//LOGGER.log(Level.INFO, "findAllGroups result size = {0}", result.size());	
	return result;
    }
    
    public List<TicketGroup> findTicketGroups(TicketData ticket, String token){
	GroupRestClient client = new GroupRestClient(conf.gerRestApiURL());
	String gsonResult = client.getTicketGroups(ticket.getTicketId(), token);
	client.close();
	//LOGGER.log(Level.INFO, "findTicketGroups gson = {0}", gsonResult);	
	if (gsonResult == null) return new ArrayList<>();
	List<TicketGroup> result = getGson().fromJson(gsonResult, new TypeToken<ArrayList<TicketGroup>>(){}.getType());
	return result;
    }
       
}