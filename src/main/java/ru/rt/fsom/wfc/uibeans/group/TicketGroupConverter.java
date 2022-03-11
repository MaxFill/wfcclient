package ru.rt.fsom.wfc.uibeans.group;

import java.util.Objects;
import java.util.logging.Logger;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;
import javax.faces.convert.FacesConverter;
import org.apache.commons.lang3.StringUtils;
import ru.rt.fsom.wfc.data.dict.SysParams;
import ru.rt.fsom.wfc.data.tickets.TicketGroup;
import ru.rt.fsom.wfc.uibeans.ticket.TicketsMonitor;
import ru.rt.fsom.wfc.utils.Utils;

@FacesConverter("ticketGroupConverter")
public class TicketGroupConverter implements Converter{
    protected static final Logger LOGGER = Logger.getLogger(SysParams.LOGGER_NAME);
    
    @Override
    public Object getAsObject(FacesContext fc, UIComponent uic, String value) {
	if(StringUtils.isNoneBlank(value)) {
            try {
                TicketsMonitor bean = Utils.findBean("ticketsMonitor", fc);
                Integer id = Integer.valueOf(value);
		//LOGGER.log(Level.INFO, "GroupConverter getById={0}", id);		
		return bean.getFilterTicketGroups().stream()
		    .filter(g->Objects.equals(g.getId(), id))
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
            return String.valueOf(((TicketGroup) object).getId());
        }
        else {
            return "";
        }
    }


}
