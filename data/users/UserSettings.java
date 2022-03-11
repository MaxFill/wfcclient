package ru.rt.fsom.wfc.data.users;

import java.util.Objects;

public class UserSettings {
    private final Integer userId;
    private String filters;

    public UserSettings(Integer userId) {
	this.userId = userId;
    }

    public Integer getUserId() {
	return userId;
    }    

    public String getFilters() {
	return filters;
    }
    public void setFilters(String filters) {
	this.filters = filters;
    }

    @Override
    public int hashCode() {
	int hash = 5;
	hash = 67 * hash + Objects.hashCode(this.userId);
	return hash;
    }

    @Override
    public boolean equals(Object obj) {
	if (this == obj) {
	    return true;
	}
	if (obj == null) {
	    return false;
	}
	if (getClass() != obj.getClass()) {
	    return false;
	}
	final UserSettings other = (UserSettings) obj;
	if (!Objects.equals(this.userId, other.userId)) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "UserSettings{" + "userId=" + userId + '}';
    }
        
}
