package ru.rt.fsom.wfc.data.dict;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class CountryFlags implements Serializable{    
    private static final long serialVersionUID = -5537548573980796679L;
  
    private final String displayName; 
    private final String name;
    private final Integer id;
    
    public static final CountryFlags ENG_FLAG = new CountryFlags(0, "en", "English");
    public static final CountryFlags RUS_FLAG = new CountryFlags(1, "ru", "Русский");
    
    public static final List<CountryFlags> FLAGS = new ArrayList<CountryFlags>() {{	
	add(ENG_FLAG);
	add(RUS_FLAG);
    }}; 
 
    public CountryFlags(Integer id, String name, String displayName) {
        this.id = id;
        this.displayName = displayName;
        this.name = name;
    }

    public Integer getId() {
        return id;
    }
 
    public String getDisplayName() {
        return displayName;
    }
 
    public String getName() {
        return name;
    }

    @Override
    public int hashCode() {
	int hash = 5;
	hash = 71 * hash + Objects.hashCode(this.id);
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
	final CountryFlags other = (CountryFlags) obj;
	if (!Objects.equals(this.id, other.id)) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
        return name;
    }
 
}
