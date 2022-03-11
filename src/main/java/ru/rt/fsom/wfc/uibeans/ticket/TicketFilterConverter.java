package ru.rt.fsom.wfc.uibeans.ticket;

import java.util.Objects;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import ru.rt.fsom.wfc.data.tickets.TicketFilter;
import ru.rt.fsom.wfc.data.tickets.TicketStatus;
import ru.rt.fsom.wfc.utils.Utils;

@FacesConverter("ticketFilterConverter")
public class TicketFilterConverter implements Converter{
     
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
		TicketsMonitor bean = Utils.findBean("ticketsMonitor", fc);
                return bean.getFilters().stream()
		    .filter(g->Objects.equals(g.getName(), value))
		    .findFirst().orElse(null);
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
            return ((TicketFilter) object).getName();
        }
        else {
            return "";
        }
    }


}
