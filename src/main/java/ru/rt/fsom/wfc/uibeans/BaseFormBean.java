package ru.rt.fsom.wfc.uibeans;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import org.apache.commons.lang3.StringUtils;
import ru.rt.fsom.wfc.data.dict.SysParams;
import ru.rt.fsom.wfc.data.users.User;
import ru.rt.fsom.wfc.conf.Conf;
import ru.rt.fsom.wfc.utils.MsgUtils;

public abstract class BaseFormBean implements Serializable{
    public static final Logger LOGGER = Logger.getLogger(SysParams.LOGGER_NAME);
    private static final String ALLOW_FILE_TYPES = "/(\\.|\\/)(pdf|docx|xlsx|xls|doc|rtf|txt|odt|zip|rar|png|tiff|gif|jpg|jpe?g)$/";
    
    protected Locale locale;
    
    @EJB protected Conf conf;
    @Inject protected SessionBean sessionBean;
    
    @PostConstruct
    private void init(){
	//LOGGER.log(Level.INFO, "Create view bean {0}", this.getClass().getSimpleName());
	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	Map<String, String> requestParams = ec.getRequestParameterMap();
	if (requestParams.containsKey(SysParams.USER_LOCALE)){ 
	    locale = new Locale(requestParams.get(SysParams.USER_LOCALE));
	} else {                
	    locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	}
	doInit();
    }
    
    @PreDestroy
    private void destroy(){
	//LOGGER.log(Level.INFO, "Destroy view bean {0}", this.getClass().getSimpleName());
    }
    
    protected abstract void doInit();
    
    public void onBeforeOpenForm(){	
	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	Map<String, String> requestParams = ec.getRequestParameterMap();
	Map<String, Object> sesionParams = ec.getSessionMap();
	Map<String, String> params = new HashMap<>(requestParams);
	if (sesionParams.containsKey(SysParams.USER_LOGIN)){
	    //LOGGER.log(Level.INFO, "Param USER_LOGIN ={0}", sesionParams.get(SysParams.USER_LOGIN));
	    params.put(SysParams.USER_LOGIN, (String)sesionParams.get(SysParams.USER_LOGIN));
	} 
	doBeforeOpenForm(params);
    }        
    
    protected abstract void doBeforeOpenForm(Map<String, String> requestParams);
    
    public User getCurrentUser(){
	return sessionBean.getCurrentUser();
    }
    
    public String getTocken(){
        return getCurrentUser().getTocken();
    }
    
    public Integer getCurUserId(){
       return getCurrentUser().getId(); 
    }
    
    public void refresh(){	
    }
    
    public void clear(){	
    }
    
    public void viewHelp(){	
    }
    
    public abstract String getFormHeader();	
    
    public abstract String getCenterHeader();
    
    public abstract String getLeftHeader();
    
    public String getAllowFileTypes(){
	return ALLOW_FILE_TYPES;
    }
	
    public void onSaveSettings(){
    }
    
    public String getFromBundle(String key){	
	String result = MsgUtils.getBandleLabel(StringUtils.upperCase(key));
	return result == null ? key : result;
    }  

    public Locale getLocale() {
	return locale;
    }
    public void setLocale(Locale locale) {
	this.locale = locale;
    }
   
}