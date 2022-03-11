package ru.rt.fsom.wfc.data.tickets;

import java.io.Serializable;

public class TicketParam implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int id;
    private int ticketId;
    private String name;
    private String value;

    public TicketParam() {
    }

    public int getId() {
	return id;
    }
    public void setId(int id) {
	this.id = id;
    }

    public int getTicketId() {
	return ticketId;
    }
    public void setTicketId(int ticketId) {
	this.ticketId = ticketId;
    }

    public String getName() {
	return name;
    }
    public void setName(String name) {
	this.name = name;
    }

    public String getValue() {
	return value;
    }
    public void setValue(String value) {
	this.value = value;
    }

    @Override
    public int hashCode() {
	int hash = 7;
	hash = 37 * hash + this.id;
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
	final TicketParam other = (TicketParam) obj;
	if (this.id != other.id) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "TicketParam{" + "id=" + id + '}';
    }        
    
}
