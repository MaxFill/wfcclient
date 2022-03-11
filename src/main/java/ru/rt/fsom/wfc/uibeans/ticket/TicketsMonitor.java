package ru.rt.fsom.wfc.uibeans.ticket;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.stream.Collectors;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.model.SelectItem;
import javax.inject.Inject;
import org.omnifaces.cdi.ViewScoped;
import javax.inject.Named;
import javax.servlet.http.HttpServletRequest;
import org.apache.commons.lang3.StringUtils;
import org.primefaces.PrimeFaces;
import org.primefaces.component.themeswitcher.ThemeSwitcher;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.data.PageEvent;
import org.primefaces.extensions.component.layout.LayoutPane;
import ru.rt.fsom.wfc.data.dict.CountryFlags;
import ru.rt.fsom.wfc.data.tickets.TicketData;
import ru.rt.fsom.wfc.data.tickets.TicketGroup;
import ru.rt.fsom.wfc.ejb.TicketFacade;
import ru.rt.fsom.wfc.data.tickets.TicketStatus;
import ru.rt.fsom.wfc.data.dict.OpenMode;
import ru.rt.fsom.wfc.data.dict.SysParams;
import ru.rt.fsom.wfc.data.dict.Theme;
import ru.rt.fsom.wfc.data.tickets.TicketFilter;
import ru.rt.fsom.wfc.ejb.GroupFacade;
import ru.rt.fsom.wfc.ejb.UserFacade;
import ru.rt.fsom.wfc.uibeans.AppBean;
import ru.rt.fsom.wfc.uibeans.BaseFormBean;
import static ru.rt.fsom.wfc.uibeans.BaseFormBean.LOGGER;
import ru.rt.fsom.wfc.utils.DateUtils;
import ru.rt.fsom.wfc.utils.MsgUtils;

@Named
@ViewScoped
public class TicketsMonitor extends BaseFormBean{
    private static final long serialVersionUID = 1L;
    private static final int GUI_SESSION_EXPIRE_IDLE = 540000;	    //9 минут
    private static final String PANEL_NORTH = "north";
    private static final String PANEL_EAST = "east";
    private static final String PANEL_WEST = "west";
    private static final String URL_HELP = "https://confluence.rt.ru/pages/viewpage.action?pageId=113186423&preview=/113186423/160208992/%D0%9F%D0%90%D0%A1%D0%9F%D0%9E%D0%A0%D0%A2%20%D0%BC%D0%BE%D0%B4%D1%83%D0%BB%D1%8F%20%D1%83%D0%BF%D1%80%D0%B0%D0%B2%D0%BB%D0%B5%D0%BD%D0%B8%D1%8F%20%D0%BE%D1%87%D0%B5%D1%80%D0%B5%D0%B4%D1%8F%D0%BC%D0%B8%20%D0%BE%D1%82%D0%BF%D1%80%D0%B0%D0%B2%D0%BA%D0%B8%20%D0%BD%D0%BE%D1%82%D0%B8%D1%84%D0%B8%D0%BA%D0%B0%D1%86%D0%B8%D0%B9.docx";
    
    @Inject private AppBean appBean;
    
    @EJB private TicketFacade facade;
    @EJB private GroupFacade groupFacade;
    @EJB private UserFacade userFacade;
    
    private TicketData selectedTicket = null;
    private TicketFilter filter = null; 
    private List<TicketData> tickets = null;
    
    private final List<TicketFilter> filters = new ArrayList<>();
    private final List<TicketStatus> filterStatusItems = new ArrayList<>();    
    private final List<SelectItem> filterTicketStatuses = new ArrayList<>();
    private final List<TicketGroup> filterTicketGroups = new ArrayList<>();     
    private final List<Theme> themes = new ArrayList<>();
    private final Integer countDataColumn = 2; //кол-во столбцов на панели просмотра тикета
    
    private boolean autoClosePanel = false;
    private boolean showViewPanel = true;    
    private String currentFilterName;
    private Theme currentTheme;
    private CountryFlags selectedLang;
    
    @Override
    protected void doInit() {
	locale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	themes.addAll(Theme.THEMES);
	currentTheme = Theme.THEME_ROSTELECOM_LIGHT;
	selectedLang = CountryFlags.FLAGS.stream()
	    .filter(l->Objects.equals(l.getName(), locale.getLanguage()))
	    .findFirst()
	    .orElse(CountryFlags.ENG_FLAG);
	FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
	filterTicketGroups.addAll(groupFacade.findAllGroups(getTocken()));
	filterStatusItems.addAll(TicketStatus.STATUSES);
    }
    
    @Override
    protected void doBeforeOpenForm(Map<String, String> params) {
	filters.addAll(userFacade.loadFilters(getCurUserId(), getTocken()));
	initCurrentFilter();	
    }
    
    /* Смена локали c формы*/
    public void onChangeLocale(){
        locale = new Locale(selectedLang.getName());
        FacesContext.getCurrentInstance().getViewRoot().setLocale(locale);
    }  
    
    public void onSessionExpReset(){
    }    
    
    public void onSearche(){
	Date dateStart = new Date();
	LOGGER.log(Level.INFO, "{0} Start searche tickets", getCurrentUser().getName());
	Set<String> checkMsg = checkFilter();
	if (!checkMsg.isEmpty()){	    
	    MsgUtils.warnMsg("NO_SEARCHE_PARAMS");
	    return;
	}
	tickets = facade.ticketsFind(filter, getTocken(), getCurrentUser());
	if (tickets.size() > 0 ){
	    if (isAutoClosePanel()){
		PrimeFaces.current().executeScript("PF('mainLayout').toggle('center_north');");
	    }
	} else {
	    MsgUtils.warnMsg("NO_SEARCHE_FIND");	    
	}
	Date dateEnd = new Date();
	long diff = dateEnd.getTime() - dateStart.getTime();
	LOGGER.log(Level.INFO, "{0} Finish searche tickets duration={1}ms", new Object[]{getCurrentUser().getName(), diff});
    }
    
    public void onOpenTicketForm(TicketData ticket){	
	LOGGER.log(Level.INFO, "{0} Ticket form is start open", getCurrentUser().getName());
	selectedTicket = ticket;
	FacesContext context = FacesContext.getCurrentInstance();	
	if (ticket == null){
	    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN,  "Error", "Not selected ticket!"));
	    return;
	}
		
	String formName = facade.getFormName(ticket, getTocken());	
	if (formName == null) {
	    context.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_WARN, "Ticket form ["+ formName + "] load error!", "Form not found!"));
	    return;
	}          
        
	Map<String, List<String>> paramsMap = new HashMap<>();
        paramsMap.put(SysParams.ITEM_ID, Collections.singletonList(String.valueOf(ticket.getTicketId())));
        paramsMap.put(SysParams.OPEN_MODE, Collections.singletonList(OpenMode.EDIT_MODE));
	paramsMap.put(SysParams.USER_LOCALE, Collections.singletonList(selectedLang.getName()));
	
        Map<String, Object> options = new HashMap<>();
        options.put("resizable", true);
        options.put("modal", true);
        options.put("minWidth", 450);
        options.put("minHeight", 300);
        options.put("width", 800);
        options.put("height", 550);
        options.put("maximizable", false);
        options.put("minimizable", false);
        options.put("closable", true);
        options.put("closeOnEscape", true);
        options.put("contentWidth", "100%");
        options.put("contentHeight", "100%");	
        PrimeFaces.current().dialog().openDynamic(formName, options, paramsMap);
	//Date dateStart = new Date();	
	//Date dateEnd = new Date();
	//long diff = dateEnd.getTime() - dateStart.getTime();
	//LOGGER.log(Level.INFO, "{0} Ticket form is open duration={1}ms", new Object[]{getCurrentUser().getName(), diff});	
    }    
    
    public void onClearFilter(){
	filter.setDateIssueFrom(null);
	filter.setDateIssueTo(null);
	filter.setDateJeopardyFrom(null);
	filter.setDateJeopardyTo(null);
	filter.setGroup(null);
	filter.setJeopardyStatus(null);
	filter.setStatuses(null);
	filter.setRequestId(null);
	filter.setTicketId(null);
	filter.setUserName(null);
	filter.setTicketInfo(null);		
	tickets = new ArrayList<>();
	selectedTicket = null;
    }
    
    public void onPageChange(PageEvent event){
	selectedTicket = null;		
    }	
    
    /**
     * Обработка события после закрытия карточки тикета
     * @param event 
     */
    public void onUpdateAfterCloseForm(SelectEvent event){
	//LOGGER.log(Level.INFO, "return from view bean!");
	if (event.getObject() == null) return;
	TicketData ticket = (TicketData)event.getObject();
	tickets.remove(ticket);
	tickets.add(ticket);
    }
    
    public void onSetAllFilterStatus(){
	filter.getStatuses().clear();
	filter.getStatuses().addAll(TicketStatus.STATUSES);
    }
    
    public void onClearFilterStatus(){
	filter.getStatuses().clear();
    }    
    
    public void onSaveFilter(){
	TicketFilter savesFilter;
	if (currentFilterName.equalsIgnoreCase(filter.getName())){
	    savesFilter = filter;
	} else {
	    savesFilter = userFacade.cloneFilter(filter, currentFilterName);
	}
	TicketFilter result = userFacade.saveFilter(savesFilter, getTocken());
	if (result == null){
	    MsgUtils.errorMsg("InternalServerError");
	    return;
	}
	filters.clear();
	filters.addAll(userFacade.loadFilters(getCurUserId(), getTocken()));
	filter = result;	
	PrimeFaces.current().executeScript("PF('saveFilterDlg').hide()");
	PrimeFaces.current().ajax().update("searcheFRM:filters");
    }
      
    public void onShowDlgSaveFilter(){
	currentFilterName = filter.getName();
	MsgUtils.warnMsg("FilterAlreadyExists", "FilterWillBeOverwritten");
	PrimeFaces.current().executeScript("PF('saveFilterDlg').show();");
    }    
        
    public void onDeleteFilter(){
	userFacade.deleteFilter(filter, getTocken());
	filters.remove(filter);
	initCurrentFilter();
    }
    
    public void onChangeAutoClosePanel(){
	autoClosePanel = !autoClosePanel;
    }
    
    public void onViewPanelClose(){
	showViewPanel = false;
	PrimeFaces.current().executeScript("PF('mainLayout').toggle('center_center_east');");
    }
    
    public void onInfoPanelClose(){
	PrimeFaces.current().executeScript("PF('mainLayout').toggle('west');");
    }
    
    public void onChangeViewPanel(){
	if (showViewPanel){
	    showViewPanel = false;
	    PrimeFaces.current().executeScript("PF('viewPanel').close();");
	} else {
	    showViewPanel = true;
	    PrimeFaces.current().executeScript("PF('viewPanel').show();");
	}
	PrimeFaces.current().executeScript("PF('mainLayout').toggle('center_center_east');");
    }
    
    public void handleClose(org.primefaces.extensions.event.CloseEvent event) {	
	String name = ((LayoutPane) event.getComponent()).getPosition();
	//LOGGER.log(Level.INFO, "close panel = {0}", name);
	if (PANEL_EAST.equals(name)){
	    showViewPanel = false;
	}
    }
    
    public void handleOpen(org.primefaces.extensions.event.OpenEvent event) {
	String name = ((LayoutPane) event.getComponent()).getPosition();
	//LOGGER.log(Level.INFO, "open panel = {0}", name);
	if (null != name) switch (name) {
	    case PANEL_NORTH:
		PrimeFaces.current().executeScript("PF('searchePanel').show();");
		break;
	    case PANEL_EAST:
		PrimeFaces.current().executeScript("PF('viewPanel').show();");
		showViewPanel = true;
		break;
	    case PANEL_WEST:
		PrimeFaces.current().executeScript("PF('infoPanel').show();");
		break;
	    default:
		break;
	}
    }
    
    public void onTicketSelect(){
	if (showViewPanel){
	    PrimeFaces.current().ajax().update("dataFRM");
	}
    }
    
    public String convertDate(Date date){
	if (date == null) return "";
	DateFormat formatter = new SimpleDateFormat("dd.MM.yyyy HH:mm:ss");
	formatter.setTimeZone(getTimeZone());
	return formatter.format(date);
    }
    
    public void onChangeTheme(AjaxBehaviorEvent event) throws UnsupportedEncodingException{
	if ((ThemeSwitcher)event.getSource() == null) return;
	currentTheme = (Theme) ((ThemeSwitcher)event.getSource()).getValue();		
    }
	
    public List<CountryFlags> getLanguages() {
        return CountryFlags.FLAGS;
    }
    
    /*
    Close user session & close window
    */
    public void onSessionExit(){	
	doSessionExit(SysParams.LOGOUT_PAGE); 
    }
    
    public void onSessionExpire(){	
	doSessionExit(SysParams.EXPIRE_PAGE);
    }
	
    private void doSessionExit(String page){	
	ExternalContext ectx = FacesContext.getCurrentInstance().getExternalContext(); 
        StringBuilder sb = new StringBuilder();
        HttpServletRequest request = (HttpServletRequest) ectx.getRequest();
        try {
            URL reconstructedURL = new URL(request.getScheme(),
                    request.getServerName(),
                    request.getServerPort(),
                    "");
	    appBean.delActiveUser(request.getSession().getId(), getCurrentUser());
	    String serverURL = reconstructedURL.toString();            
            sb.append(serverURL).append(ectx.getRequestContextPath()).append(page);
            ectx.redirect(sb.toString()); 
	    ectx.invalidateSession();
	    LOGGER.log(Level.INFO, "Session close for user={0}, total session={1}", new Object[]{getCurrentUser().getName(), appBean.countActiveUser()});
        } catch (MalformedURLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        } 
    } 

    /* *** privates *** */    
    
    private void initCurrentFilter(){
	if (filters.isEmpty()){
	    filter = new TicketFilter(getCurUserId(), MsgUtils.getBandleLabel("Noname"));
	    filter.setUserName(getCurrentUser().getName());
	    filter.setDateIssueTo(DateUtils.endDateToday());
	    filters.add(filter);
	} else {
	    filter = filters.get(0);	    
	}	
    }
	
    private Set<String> checkFilter(){
	Set<String> errors = new HashSet<>();
	if (filter == null){
	    errors.add("Filter data empty!");
	    return errors;
	}
	boolean isEmptyFilter = true;
	if (filter.getRequestId() != null){
	    //LOGGER.log(Level.INFO, "filter getRequestId not null!");
	    isEmptyFilter = false;
	} else
	    if (filter.getTicketId() != null){
		//LOGGER.log(Level.INFO, "filter getTicketId not null!");
		isEmptyFilter = false;
	    } else
		if (StringUtils.isNoneBlank(filter.getUserName())){
		    isEmptyFilter = false;
		} else	
		    if (filter.getDateIssueFrom() != null || filter.getDateIssueTo() != null || filter.getDateJeopardyFrom() != null || filter.getDateJeopardyTo() != null){
			isEmptyFilter = false;
		    } else	
			if (!filter.getStatuses().isEmpty()){
			    isEmptyFilter = false;
			} else	
			    if (filter.getGroup() != null){
				isEmptyFilter = false;
			    } 
	if (isEmptyFilter){
	    errors.add("Filter data empty!");
	}
	return errors;
    }     

    @Override
    public String getCenterHeader() {
	return MsgUtils.getBandleLabel("WorkQueuesAndTikets");
    }

    @Override
    public String getLeftHeader() {
	return "FSOM Workflow client";
    }
 
    @Override
    public String getFormHeader(){
	return "FSOM Workflow client";
    }
    
    /* gets & sets */    
    
    public String getVersionNumber(){
	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	return ec.getInitParameter("VERSION_NUMBER");
    }
    
    public String getVersionDate(){
	ExternalContext ec = FacesContext.getCurrentInstance().getExternalContext();
	return ec.getInitParameter("VERSION_DATE");
    }
    
    public TimeZone getTimeZone(){
	return TimeZone.getTimeZone("Europe/Moscow");
    }

    public Integer getMaxSizeFile(){	
	return conf.getMaxUploadFileSize();
    }
    
    public CountryFlags getSelectedLang() {
        return selectedLang;
    }
    public void setSelectedLang(CountryFlags selectedLang) {
        this.selectedLang = selectedLang;
    } 

    public Theme getCurrentTheme() {
	return currentTheme;
    }
    public void setCurrentTheme(Theme currentTheme) {
	this.currentTheme = currentTheme;
    }
    
    public String getAutoClosePanelIcon(){
	if (autoClosePanel){
	    return "pi pi-check";
	} else {
	    return "pi pi-times";
	}
    }
    
    public String getShowViewPanelIcon(){
	if (showViewPanel){
	    return "pi pi-check";
	} else {
	    return "pi pi-times";
	}
    }
	
    public List<TicketFilter> getFilters() {
	return filters;
    }
    
    public List<Theme> getThemes() {
	return themes;
    }    
    
    public String getUrlHelp() {
	return URL_HELP;
    }    
    
    public int getSessionExpireIdle(){
	return GUI_SESSION_EXPIRE_IDLE;
    }

    public String getCurrentFilterName() {
	return currentFilterName;
    }
    public void setCurrentFilterName(String currentFilterName) {
	this.currentFilterName = currentFilterName;
    }
        
    public List<TicketData> getTickets() {
	if (tickets == null){	    
	    tickets = new ArrayList<>();
	}
	return tickets;
    }
    public void setTickets(List<TicketData> tickets) {
	this.tickets = tickets;
    }

    public TicketData getSelectedTicket() {
	return selectedTicket;
    }
    public void setSelectedTicket(TicketData selectedTicket) {
	this.selectedTicket = selectedTicket;
    }

    public TicketFilter getFilter() {
	return filter;
    }
    public void setFilter(TicketFilter filter) {
	this.filter = filter;
    }   
    
    public List<TicketStatus> getFilterStatusItems() {
	return filterStatusItems;
    }
        
    public List<SelectItem> getFilterTicketStatuses() {
	return filterTicketStatuses;
    }

    public List<TicketGroup> getFilterTicketGroups() {
	return filterTicketGroups;
    }

    public Integer getCountDataColumn() {
	return countDataColumn;
    }
	
    
    public boolean isShowViewPanel() {
	return showViewPanel;
    }
    public void setShowViewPanel(boolean showViewPanel) {
	this.showViewPanel = showViewPanel;
    }
         
    public boolean isAutoClosePanel() {
	return autoClosePanel;
    }
    public void setAutoClosePanel(boolean autoClosePanel) {
	this.autoClosePanel = autoClosePanel;
    }
    
}