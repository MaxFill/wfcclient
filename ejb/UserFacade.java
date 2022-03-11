package ru.rt.fsom.wfc.ejb;

import com.google.gson.reflect.TypeToken;
import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import ru.rt.fsom.wfc.data.CarmTocken;
import ru.rt.fsom.wfc.data.tickets.TicketFilter;
import ru.rt.fsom.wfc.data.users.User;
import ru.rt.fsom.wfc.restAPI.UserRestClient;

@Stateless
public class UserFacade extends BaseFacade{
    
    public TicketFilter saveFilter(TicketFilter filter, String token){
	UserRestClient client = new UserRestClient(conf.gerRestApiURL());
	String gsonResult = client.saveFilter(filter, token);
	client.close();	
	//LOGGER.log(Level.INFO, "Client start load filter from json {0}", gsonResult);
	if (gsonResult == null) return null;
	return getGson().fromJson(gsonResult, TicketFilter.class);
    }
    
    public List<TicketFilter> loadFilters(int userId, String token){
	UserRestClient client = new UserRestClient(conf.gerRestApiURL());
	String gsonResult = client.loadFilters(userId, token);
	client.close();	
	//LOGGER.log(Level.INFO, "loadFilters gson = {0}", gsonResult);	
	if (gsonResult == null) return new ArrayList<>();
	List<TicketFilter> result = getGson().fromJson(gsonResult, new TypeToken<ArrayList<TicketFilter>>(){}.getType());
	return result;
    }
    
    public User checkUserByLogin(String userName, char[] password){
	UserRestClient client = new UserRestClient(conf.gerRestApiURL());
	String gsonResult = client.remoteLogin(userName, password);
	CarmTocken carmTocken = getGson().fromJson(gsonResult, CarmTocken.class);
        //Map<Integer, String> result = getGson().fromJson(gsonResult, new TypeToken<HashMap<Integer, String>>(){}.getType());
        if (carmTocken == null) return null;
	Integer userId = carmTocken.getUserId();
        String token = carmTocken.getTocken();
        /*
	for (Entry<Integer, String> entry : result.entrySet()){
            userId = entry.getKey();
            token = entry.getValue();
        }
	*/
	return new User(userId, userName, token);
    }
    
    public TicketFilter cloneFilter(TicketFilter source, String name){
	TicketFilter target = new TicketFilter(source.getUserId(), name);
	target.setDateIssueFrom(source.getDateIssueFrom());
	target.setDateIssueTo(source.getDateIssueTo());
	target.setDateJeopardyFrom(source.getDateJeopardyFrom());
	target.setDateJeopardyTo(source.getDateJeopardyTo());
	target.setGroup(source.getGroup());
	target.setJeopardyStatus(source.getJeopardyStatus());
	target.setStatuses(source.getStatuses());
	target.setTicketId(source.getTicketId());
	target.setTicketInfo(source.getTicketInfo());
	target.setRequestId(source.getRequestId());
	target.setUserName(source.getUserName());
	return target;
    }
    
    public void deleteFilter(TicketFilter filter, String token){
	UserRestClient client = new UserRestClient(conf.gerRestApiURL());
	client.deleteFilter(filter, token);
	client.close();	
    }
}