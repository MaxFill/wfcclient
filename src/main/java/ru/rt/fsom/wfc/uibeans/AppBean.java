package ru.rt.fsom.wfc.uibeans;

import java.io.Serializable;
import java.util.concurrent.ConcurrentHashMap;
import javax.enterprise.context.ApplicationScoped;
import javax.inject.Named;
import ru.rt.fsom.wfc.data.users.User;

@Named
@ApplicationScoped
public class AppBean implements Serializable{
    private final ConcurrentHashMap<String, User> activeUsers = new ConcurrentHashMap<>();
    
    public void addActiveUser(String sessionId, User user){
	activeUsers.put(sessionId, user);
    }
    
    public void delActiveUser(String sessionId, User user){
	activeUsers.remove(sessionId);
    }
    
    public int countActiveUser(){
	return activeUsers.size();
    }
}
