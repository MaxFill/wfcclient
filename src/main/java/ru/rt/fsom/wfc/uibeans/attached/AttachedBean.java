package ru.rt.fsom.wfc.uibeans.attached;

import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import org.omnifaces.cdi.ViewScoped;
import ru.rt.fsom.wfc.data.dict.OpenMode;
import ru.rt.fsom.wfc.ejb.AttacheFacade;
import ru.rt.fsom.wfc.data.tickets.TicketAttaches;
import ru.rt.fsom.wfc.uibeans.BaseCardBean;
import ru.rt.fsom.wfc.utils.MsgUtils;

@Named
@ViewScoped
public class AttachedBean extends BaseCardBean{
    private static final long serialVersionUID = 1L;    
    
    @EJB private AttacheFacade facade;
    
    private TicketAttaches attached;   
    
    @Override
    protected void doInit() {
    }    
    
    @Override
    protected void doPrepareOpen(String attachedId) {
	Integer id;
	try {
	    id = Integer.valueOf(attachedId);
	    attached = facade.findAttacheById(id, getTocken());
	    if (attached == null){
		MsgUtils.errorFormatMsg("ObjectWithIDNotFound", new Object[]{"TicketAttaches", attachedId});
	    }
	    downloadAttache(attached);
	} catch (UnsupportedEncodingException | FileNotFoundException | NumberFormatException ex) {
	    LOGGER.log(Level.SEVERE, "Exception: {0}", ex.getMessage());
	}	
    }

    private void downloadAttache(TicketAttaches attache) throws FileNotFoundException, UnsupportedEncodingException{     
        FacesContext facesContext = FacesContext.getCurrentInstance();
	HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse(); 
	Map<String, Object> params = new HashMap<>();
	params.put("openType", "inline");
	params.put("authorId", getCurrentUser().getId());
	params.put("authorName", getCurrentUser().getName());
	facade.attacheDownLoad(response, attache, params);
	facesContext.responseComplete();
    }    

    @Override
    public boolean isReadOnly() {
	return OpenMode.VIEW_MODE.equals(openMode);
    }    
    
    /* gets & sets */

    @Override
    public String getFormHeader() {
	return "Cross patching";
    }

    @Override
    public String getCenterHeader() {
	return "";
    }

    @Override
    public String getLeftHeader() {
	return "";
    }    
}