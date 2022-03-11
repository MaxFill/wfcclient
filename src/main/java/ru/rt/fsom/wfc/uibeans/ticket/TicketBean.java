package ru.rt.fsom.wfc.uibeans.ticket;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.omnifaces.cdi.ViewScoped;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.file.UploadedFile;
import ru.rt.fsom.wfc.data.dict.Forms;
import ru.rt.fsom.wfc.data.dict.OpenMode;
import ru.rt.fsom.wfc.data.tickets.TicketGroup;
import ru.rt.fsom.wfc.ejb.AttacheFacade;
import ru.rt.fsom.wfc.data.tickets.TicketAttaches;
import ru.rt.fsom.wfc.data.tickets.TicketData;
import ru.rt.fsom.wfc.data.tickets.TicketStatus;
import ru.rt.fsom.wfc.ejb.GroupFacade;
import ru.rt.fsom.wfc.uibeans.BaseCardBean;
import ru.rt.fsom.wfc.ejb.TicketFacade;
import static ru.rt.fsom.wfc.uibeans.BaseFormBean.LOGGER;
import ru.rt.fsom.wfc.utils.MsgUtils;

@Named
@ViewScoped
public class TicketBean extends BaseCardBean{
    private static final long serialVersionUID = 1L;    

    @EJB private TicketFacade facade;
    @EJB private GroupFacade groupFacade;
    @EJB private AttacheFacade attacheFacade;
    
    private TicketData selectedTicket;
    private UploadedFile file;    
    private final Integer countDataColumn = 2;
      
    @Override
    protected void doInit() {
    }    
    
    @Override
    protected void doPrepareOpen(String ticketId) {
	selectedTicket = facade.ticketFindById(ticketId, getTocken(), getCurrentUser());
	if (selectedTicket == null){
	    MsgUtils.errorFormatMsg("ObjectWithIDNotFound", new Object[]{"Ticket", ticketId});
	    return;
	}
	facade.loadTicketParams(selectedTicket, getTocken());
    }

    public void onTicketReserved(){	
	if (!validate()) return;
	changeTicketStatus(TicketStatus.STATUS_RESERVED);
    }
    
    public void onTicketSentOk(){
	if (!validate()) return;
	changeTicketStatus(TicketStatus.STATUS_SENT_OK);
    }
    
    public void onTicketSentExp(){
	if (!validate()) return;
	changeTicketStatus(TicketStatus.STATUS_SENT_EXP);
    }    
            
    public void onTicketSentFail(){
	if (!validate()) return;
	changeTicketStatus(TicketStatus.STATUS_SENT_FAIL);
    }  
	
    public void onTicketWaiting(){
	if (!validate()) return;
	changeTicketStatus(TicketStatus.STATUS_WAITING);
    }
           
    public void onTicketResend(){
	if (!validate()) return;
	changeTicketStatus(TicketStatus.STATUS_RESEND);
    }
	
    public void onTicketCancelled(){
	if (!validate()) return;
	changeTicketStatus(TicketStatus.STATUS_CANCELLED);
    }        
    
    @Override
    public void onSaveItemAndClose() { 
        if (OpenMode.INSERT_MODE.equals(openMode)){
            onItemChange();
        }
	if (isItemChanged){
	    facade.saveTicket(selectedTicket);
	}
	closeForm(selectedTicket); 
    }
    
    public void onUploadAtache(FileUploadEvent event){
	LOGGER.log(Level.INFO, "ticketBean onUploadAtache start!");
	UploadedFile uploadFile = event.getFile();
	if (uploadFile == null) return;	
	Map<String, Object> params = new HashMap<>();        
	params.put("contentType", uploadFile.getContentType());
        params.put("fileName", uploadFile.getFileName());
        params.put("size", uploadFile.getSize());
        params.put("authorId", getCurrentUser().getId());
	params.put("ticketId", selectedTicket.getTicketId());
	Set<String> errors = new HashSet<>();
	TicketAttaches newAttach = null;
	try {
	    newAttach = attacheFacade.uploadAtache(params, uploadFile.getInputStream(), getTocken(), errors);
	} catch (IOException ex) {
	    errors.add(ex.getMessage());
	    LOGGER.log(Level.SEVERE, null, ex.getMessage());
	}
	if (!errors.isEmpty()){
	    MsgUtils.showErrors(errors);
	} else {
	    selectedTicket.getAttacheses().add(newAttach);
	    MsgUtils.succesMsg("Successfully");
	}
    } 
    
    public void onDownloadAttache(TicketAttaches attache){
	try {
	    Map<String, Object> params = new HashMap<>();
	    params.put("openType", "attachment");
	    FacesContext facesContext = FacesContext.getCurrentInstance();
	    HttpServletResponse response = (HttpServletResponse) facesContext.getExternalContext().getResponse(); 
	    attacheFacade.attacheDownLoad(response, attache, params);
	    facesContext.responseComplete();
	} catch (UnsupportedEncodingException | FileNotFoundException | NumberFormatException ex) {
	    MsgUtils.errorMessage(ex.getMessage());
	    LOGGER.log(Level.SEVERE, "Exception: {0}", ex.getMessage());
	}
    }
    
    public void onDeleteAttache(TicketAttaches attache){
	String errMsg = attacheFacade.deleteAttacheFromTicket( attache, getTocken());
	if (StringUtils.isNoneBlank(errMsg)){
	    MsgUtils.errorMessage(errMsg);
	} else {
	    selectedTicket.getAttacheses().remove(attache);
	    MsgUtils.succesMsg("Successfully");
	}
    }
    
    public String getAttachePath(TicketAttaches attache){
	final String guid = attache.getGuid();
	StringBuilder sb = new StringBuilder(conf.getUploadPath());
            sb.append(guid.substring(0, 2))
                .append(File.separator)
                .append(guid.substring(2, 4)).append(File.separator); 
	return sb.toString();
    }
    
    public boolean isInWork(){
	return TicketStatus.STATUS_RESERVED == selectedTicket.getTicketStatus();
    }   

    @Override
    public boolean isReadOnly() {
	return OpenMode.VIEW_MODE.equals(openMode) || isTicketArhived();
    }
    
    /* *** privates *** */ 
    
    private boolean isTicketArhived(){
	return TicketStatus.STATUS_SENT_EXP.equals(selectedTicket.getTicketStatus()) ||
		TicketStatus.STATUS_SENT_FAIL.equals(selectedTicket.getTicketStatus()) ||
		TicketStatus.STATUS_SENT_OK.equals(selectedTicket.getTicketStatus()) ||
		TicketStatus.STATUS_CANCELLED.equals(selectedTicket.getTicketStatus());
    }
    
    private boolean validate(){
	//ToDo
	return true;
    }
    
    private void changeTicketStatus(TicketStatus status){
	String errMsg =	facade.changeTicketStatus(selectedTicket, status, getCurrentUser().getName(), getTocken());
	if (errMsg != null){
	    MsgUtils.errorMessage(errMsg);
	    return;
	}
	MsgUtils.succesMessage("CommandExecutedSuccessfully");
	closeForm(selectedTicket);	
    }
    
    /* gets & sets */

    public List<TicketAttaches> getAttaches(){
	if (selectedTicket.getAttacheses() == null){
	    selectedTicket.setAttacheses(attacheFacade.findAttaches(selectedTicket, getTocken()));
	}
	return selectedTicket.getAttacheses();
    }
    
    public List<TicketGroup> getTicketGroup(){
	if (selectedTicket.getTicketGroup() == null){
	    selectedTicket.setTicketGroup(groupFacade.findTicketGroups(selectedTicket, getTocken()));
	}
	return selectedTicket.getTicketGroup();
    }
    
    public String getFileSize(TicketAttaches attache){
	return FileUtils.byteCountToDisplaySize(attache.getFileSize());
    }
    
    public void setFile(UploadedFile file) {
	this.file = file;
    }
    public UploadedFile getFile() {
	return file;
    }

    public TicketData getSelectedTicket() {
	return selectedTicket;
    }
    public void setSelectedTicket(TicketData selectedTicket) {
	this.selectedTicket = selectedTicket;
    }   

    public Integer getCountDataColumn() {
	return countDataColumn;
    }
    
    public Integer getMaxSizeFile(){
	return conf.getMaxUploadFileSize();
    }
    @Override
    public String getFormHeader() {
	return getFromBundle(Forms.CROSS_PATCHING_TASK_FORM);
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