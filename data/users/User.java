package ru.rt.fsom.wfc.data.users;

import java.util.Objects;

public class User {
    private Integer id;
    private String name;
    private final String tocken;
    
    public User(Integer id, String name, String tocken) {
	this.id = id;
	this.name = name;
        this.tocken = tocken;
    }

    public Integer getId() {
	return id;
    }
    public void setId(Integer id) {
	this.id = id;
    }
    
    public String getName() {
	return name;
    }
    public void setName(String name) {
	this.name = name;
    }

    public String getTocken() {
        return tocken;
    }

    @Override
    public int hashCode() {
	int hash = 5;
	hash = 29 * hash + Objects.hashCode(this.name);
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
	final User other = (User) obj;
	if (!Objects.equals(this.name, other.name)) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "FSOMUsers{" + "name=" + name + '}';
    }
        
}
