package ru.rt.fsom.wfc.data.tickets;

import java.io.Serializable;
import java.util.Objects;

public class TicketGroup implements Serializable{
    private static final long serialVersionUID = 0L;

    private Integer id;
    private String name;

    public TicketGroup() {
    }

    public TicketGroup(Integer id, String name) {
	this.id = id;
	this.name = name;
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
    
    @Override
    public int hashCode() {
	int hash = 3;
	hash = 37 * hash + Objects.hashCode(this.id);
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
	final TicketGroup other = (TicketGroup) obj;
	if (!Objects.equals(this.id, other.id)) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "WorkGroup{" + "id=" + id + ", name=" + name + '}';
    }
        
}
