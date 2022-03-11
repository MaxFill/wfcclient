package ru.rt.fsom.wfc.uibeans.theme;

import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import ru.rt.fsom.wfc.data.dict.Theme;

@FacesConverter("themeConverter")
public class ThemeConverter implements Converter{
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
		return Theme.THEMES.stream().filter(t->Objects.equals(t.getName(),value)).findFirst().orElse(Theme.THEME_OMEGA);
            } catch(NumberFormatException e) {
                throw new ConverterException(new FacesMessage(FacesMessage.SEVERITY_ERROR, "Conversion Error", "Некорректное значение."));
            }
        } else {
            return null;
        }
    }

    @Override
    public String getAsString(FacesContext fc, UIComponent uic, Object object) {
        if(object != null) {
            return String.valueOf(((Theme) object).getName());
        }
        else {
            return "";
        }
    }
}