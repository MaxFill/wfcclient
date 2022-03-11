package ru.rt.fsom.wfc.uibeans;

import java.io.Serializable;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;
import ru.rt.fsom.wfc.data.users.User;
import ru.rt.fsom.wfc.data.users.UserSettings;

@Named
@SessionScoped
public class SessionBean implements Serializable{
    private User currentUser;
    private UserSettings settings;
    
    public User getCurrentUser() {
        return currentUser;
    }

    public void setCurrentUser(User user) {
        this.currentUser = user;
        settings = new UserSettings(user.getId());
    }

    public UserSettings getSettings() {
        return settings;
    }
    
}
