package ru.rt.fsom.wfc.uibeans.ticket;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import ru.rt.fsom.wfc.data.tickets.TicketStatus;

@FacesConverter("ticketStatusConverter")
public class TicketStatusConverter implements Converter{
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
        if(value != null && value.trim().length() > 0) {
            try {
                Object searcheObj = TicketStatus.STATUSES.get(Integer.parseInt(value));
		return searcheObj;
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
            return String.valueOf(((TicketStatus) object).getStatusId());
        }
        else {
            return "";
        }
    }


}
