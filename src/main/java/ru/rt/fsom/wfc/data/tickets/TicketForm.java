package ru.rt.fsom.wfc.data.tickets;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class TicketForm {
    private static final String CROSS_PATCHING_FORM = "cross_patching";    
    
    public static final List<String> FORMS = new ArrayList<String>() {{	
	add(CROSS_PATCHING_FORM);
    }}; 
    
    private final String title;
	
    TicketForm(String name){
	this.title = name;
    }

    public String getTitle() {
	return title;
    }

    @Override
    public int hashCode() {
	int hash = 5;
	hash = 97 * hash + Objects.hashCode(this.title);
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
	final TicketForm other = (TicketForm) obj;
	if (!Objects.equals(this.title, other.title)) {
	    return false;
	}
	return true;
    }
    
    
}
