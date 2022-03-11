package ru.rt.fsom.wfc.data.dict;

import java.util.ArrayList;
import java.util.List;

public class Theme {
    private final int id;
    private final String displayName;
    private final String name;

    public static final Theme THEME_OMEGA = new Theme(0, "Omega", "omega");
    public static final Theme THEME_ARISTO = new Theme(1, "Aristo", "aristo"); 
    public static final Theme THEME_LUNA_BLUE = new Theme(2, "Luna", "luna-blue");  
    public static final Theme THEME_ROSTELECOM_LIGHT = new Theme(3, "Rostelecom-light", "rostelecom-light");
    public static final Theme THEME_ROSTELECOM_DARK = new Theme(4, "Rostelecom-dark", "rostelecom-dark");
    
    public static final List<Theme> THEMES = new ArrayList<Theme>() {{		
	add(THEME_OMEGA);
	add(THEME_ARISTO);
	add(THEME_LUNA_BLUE);
	add(THEME_ROSTELECOM_LIGHT);
	add(THEME_ROSTELECOM_DARK);	
    }};     

    public Theme(int id, String displayName, String name) {
	this.id = id;
	this.displayName = displayName;
	this.name = name;
    }
    
    public int getId() {
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
	int hash = 3;
	hash = 47 * hash + this.id;
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
	final Theme other = (Theme) obj;
	if (this.id != other.id) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return name;
    }    
    
}
