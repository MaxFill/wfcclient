package ru.rt.fsom.wfc.ejb;

import com.google.gson.reflect.TypeToken;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Optional;
import java.util.logging.Level;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import javax.ejb.Stateless;
import javax.xml.datatype.XMLGregorianCalendar;
import org.apache.commons.lang3.StringUtils;
import ru.rt.fsom.wfc.data.dict.Params;
import ru.rt.fsom.wfc.data.dict.SysParams;
import ru.rt.fsom.wfc.data.oms.Attachment;
import ru.rt.fsom.wfc.data.oms.Attribute;
import ru.rt.fsom.wfc.data.oms.AttributeRestriction;
import ru.rt.fsom.wfc.data.oms.Attributes;
import ru.rt.fsom.wfc.data.oms.Comment;
import ru.rt.fsom.wfc.data.oms.Comments;
import ru.rt.fsom.wfc.data.oms.InheritableAttribute;
import ru.rt.fsom.wfc.data.oms.InheritableAttributes;
import ru.rt.fsom.wfc.data.oms.Location;
import ru.rt.fsom.wfc.data.oms.LocationCategory;
import ru.rt.fsom.wfc.data.oms.OIReference;
import ru.rt.fsom.wfc.data.oms.OIReferences;
import ru.rt.fsom.wfc.data.oms.OrderItemBillingInfo;
import ru.rt.fsom.wfc.data.oms.OrderItemSpecification;
import ru.rt.fsom.wfc.data.oms.OrderParties;
import ru.rt.fsom.wfc.data.oms.Party;
import ru.rt.fsom.wfc.data.oms.RequestOrderItem;
import ru.rt.fsom.wfc.data.tickets.TicketData;
import ru.rt.fsom.wfc.data.tickets.TicketForm;
import ru.rt.fsom.wfc.data.tickets.TicketFilter;
import ru.rt.fsom.wfc.data.tickets.TicketParam;
import ru.rt.fsom.wfc.data.tickets.TicketStatus;
import ru.rt.fsom.wfc.data.users.User;
import ru.rt.fsom.wfc.restAPI.TicketRestClient;
import ru.rt.fsom.wfc.utils.DateUtils;
import ru.rt.fsom.wfc.utils.MsgUtils;
import ru.rt.fsom.wfc.utils.Utils;

@Stateless
public class TicketFacade extends BaseFacade{     
    
    public String getFormName(TicketData ticket, String token){ 
	TicketRestClient client = new TicketRestClient(conf.gerRestApiURL());
	final String gsonResult = client.getTicketParamByName(ticket.getTicketId(), SysParams.FORM_NAME, token);
	client.close();
	//LOGGER.log(Level.INFO, "getFormName gson = {0}", gsonResult);
	if (gsonResult == null) return null;
	TicketParam ticketParam = getGson().fromJson(gsonResult, TicketParam.class);
	String formName = ticketParam.getValue();
	if (formName == null || !TicketForm.FORMS.contains(formName)){
	    return null;
	}
	return formName;
    }	
 
    public void saveTicket(TicketData ticket){
	//ToDo!
    }       
    
    public TicketData ticketFindById(String ticketId, String token, User user){
	Date dateStart = new Date();
	LOGGER.log(Level.INFO, "{1} start request get ticket by id={0}", new Object[]{ticketId, user.getName()});	
	TicketRestClient client = new TicketRestClient(conf.gerRestApiURL());
	String gsonResult = client.getTicketById(ticketId, token);
	client.close();
	Date dateEnd = new Date();
	long diff = dateEnd.getTime() - dateStart.getTime();
	LOGGER.log(Level.INFO, "{1} finish request get ticket by id={0}, duration={2}ms", new Object[]{ticketId, user.getName(), diff});
	if (gsonResult == null) return null;
	TicketData ticket = getGson().fromJson(gsonResult, TicketData.class);
	findTicketStatusById(ticket.getStatus()).ifPresent(s->ticket.setTicketStatus(s));	
	return ticket;
    }
    
    public List<TicketData> ticketsFind(TicketFilter filter, String token, User user){	
	//LOGGER.log(Level.INFO, "{0} start request find tickets", user.getName());
	TicketRestClient client = new TicketRestClient(conf.gerRestApiURL());
	String gsonResult = client.getTickets(filter, token);
	//LOGGER.log(Level.INFO, "gsonResult={0}", gsonResult);
	client.close();
	//Date dateStart = new Date();
	//Date dateEnd = new Date();
	//long diff = dateEnd.getTime() - dateStart.getTime();
	//LOGGER.log(Level.INFO, "{0} finish request find tickets, duration={1}ms", new Object[]{user.getName(), diff});	
	if (gsonResult == null) return new ArrayList<>();
	List<TicketData> tikets = getGson().fromJson(gsonResult, new TypeToken<ArrayList<TicketData>>(){}.getType());
	tikets.forEach(td->findTicketStatusById(td.getStatus()).ifPresent(v->td.setTicketStatus(v)));
	return tikets;
    }
     
    public void loadTicketParams(TicketData ticket, String token){		
	TicketRestClient client = new TicketRestClient(conf.gerRestApiURL());
	String gsonResult = client.getTicketParam(ticket.getTicketId(), token);
	client.close();
	//LOGGER.log(Level.INFO, "loadTicketParams gson = {0}", gsonResult);	
	if (gsonResult == null) return;
	List<TicketParam> result = getGson().fromJson(gsonResult, new TypeToken<ArrayList<TicketParam>>(){}.getType());
	Map<String, String> params = result.stream().collect(Collectors.toMap(TicketParam::getName, TicketParam::getValue));	
	ticket.setParams(params);
	ticket.getTaskParams().addAll(
	    result.stream().filter(p->p.getName().startsWith(Params.TASK))
		.map(tp->{
		    String param = StringUtils.replace(tp.getName(), Params.TASK + "_", "");
		    String caption = MsgUtils.getBandleLabel(param);
		    if (caption != null){
			tp.setName(caption);
		    } else {
			tp.setName(param);
		    }
		    return tp;
		})
		.collect(Collectors.toList())
	);
	loadOrderItems(ticket, params);
	loadOrderParty(ticket, params);	
    }    
    
    public String changeTicketStatus(TicketData ticket, TicketStatus status, String userName, String token){
	TicketRestClient client = new TicketRestClient(conf.gerRestApiURL());
	final String gsonResult = client.changeTicketStatus(ticket.getTicketId(), status.getStatusId(), userName, token);
	client.close();
	//LOGGER.log(Level.INFO, "changeTicketStatus gson = {0}", gsonResult);
	if (gsonResult == null) return null;	
	return getGson().fromJson(gsonResult, String.class);
    }    
	
    /* *** privates *** */
    
    private Optional<TicketStatus> findTicketStatusById(Integer statusId){
	return TicketStatus.STATUSES.stream().filter(s->Objects.equals(statusId, s.getStatusId())).findFirst();
    }                      
    
    /* *** oms data converter *** */
    
    private void loadOrderItems(TicketData ticket, Map<String, String> params){
        final String prefix = Params.OI;
	ticket.getOrderItems().addAll(
	    IntStream.rangeClosed(1, getListSize(params, prefix))
		.mapToObj(index->{
		    final String itemKey = prefix + "_" + index;
		    RequestOrderItem orderItem = new RequestOrderItem();
		    getOptString(params, itemKey + "_" + Params.ID).ifPresent(orderItem::setOrderItemId);
		    getOptString(params, itemKey + "_" + Params.INSTANCEID).ifPresent(orderItem::setOrderItemInstanceId);		    
		    getOptString(params, itemKey + "_" + Params.APPOINTMENT_ID).ifPresent(orderItem::setOrderItemAppointmentId);		    
		    getOptString(params, itemKey + "_" + Params.ACTION).ifPresent(orderItem::setOrderItemAction); 
		    getOptString(params, itemKey + "_" + Params.CATEGORY).ifPresent(orderItem::setOrderItemCategory); 
		    getOptionalLong(params, itemKey + "_" + Params.PRIORITY).ifPresent(orderItem::setOrderItemPriority);
		    getOptCalendar(params, itemKey + "_" + Params.REQUIREDDATE).ifPresent(orderItem::setOrderItemRequiredDate);		    
		    getLocation(params, itemKey).ifPresent(orderItem::setOrderItemLocation);
		    getOrderItemSpecification(params, itemKey).ifPresent(orderItem::setOrderItemSpecification);
		    getOrderParties(params, itemKey).ifPresent(orderItem::setOrderItemParties);
		    getComments(params, itemKey).ifPresent(orderItem::setOrderItemComments);
		    orderItem.setOrderItemAttributes(getItemAttributes(params, itemKey));
		    orderItem.setOrderItemBillingInfo(getOrderItemBillingInfo(params, itemKey));
		    orderItem.setOrderItemReferences(getOIReferences(params, itemKey));
		    return orderItem;
		})
		.collect(Collectors.toList())
	);
    }
    
    private void loadOrderParty(TicketData ticket, Map<String, String> params){
	final String key =  Params.ORDER + "_" + Params.PARTY;        
	int size = getListSize(params, key);
	if (size == 0) return;
	ticket.getOrderParties().addAll(
	    IntStream.rangeClosed(1, size)
		.mapToObj(index->getParty(params, key + "_" + index))
		.collect(Collectors.toList())
        );
    }

    private Optional<OrderParties> getOrderParties(Map<String, String> params, String prefix) {
        Optional<OrderParties> optOrderParties = getParties(params, prefix);
	if (optOrderParties.isPresent()){
	    getAttachment(params, prefix, optOrderParties.get());	    
	} else {
	    optOrderParties = getAttachment(params, prefix, null);
	}
        return optOrderParties;
    }
	
    private Optional<OrderParties> getParties(Map<String, String> params, final String prefix) {
        final String key = prefix + "_" + Params.PARTY;        
	int size = getListSize(params, key);
	if (size == 0) return Optional.empty();
	OrderParties orderParties = new OrderParties();
	orderParties.getOrderPartyOrOrderAttachment().addAll(
	    IntStream.rangeClosed(1, size)
		.mapToObj(index->getParty(params, key + "_" + index))
		.collect(Collectors.toList())
        );
	return Optional.of(orderParties);
    }
	
    private Party getParty(Map<String, String> params, final String itemKey){
	Party party = new Party();
	getOptString(params, itemKey + "_" + Params.ROLE).ifPresent(party::setPartyRole); //RT.FFAPI_v19
	getOptString(params, itemKey + "_" + Params.ID).ifPresent(party::setPartyId);
	getOptString(params, itemKey + "_" + Params.NAME).ifPresent(party::setPartyName);
	party.setPartyAttributes(getAttributes(params, itemKey));
	return party;
    }
    
    private Attributes getAttributes(Map<String, String> params, final String prefix){
        final String key = prefix + "_" + Params.ATTR;        
        Attributes orderAttributes = new Attributes();
        orderAttributes.getAttribute().addAll(
	    IntStream.rangeClosed(1, getListSize(params, key))
		.mapToObj(index -> {
		    final String itemKey = key + "_" + index;
		    Attribute attribute = new Attribute();
		    getOptString(params, itemKey + "_" + Params.NAME).ifPresent(attribute::setName);		
		    getOptString(params, itemKey + "_" + Params.VALUE).ifPresent(attribute.getContent()::add);
		    return attribute;
		})
		.collect(Collectors.toList())
	);
        return orderAttributes;
    }
    
    private InheritableAttributes getItemAttributes(Map<String, String> params, final String prefix) {
        InheritableAttributes orderItemAttributes = new InheritableAttributes();        
        final String key = prefix + "_" + Params.ATTR;
	orderItemAttributes.getAttribute().addAll(
	    IntStream.rangeClosed(1, getListSize(params, key))
		.mapToObj(index->{		
		    final String itemKey = key + "_" + index;
		    InheritableAttribute attribute = new InheritableAttribute();
		    getOptString(params, itemKey + "_" + Params.NAME).ifPresent(attribute::setName);
		    getOptionalBoolean(params, itemKey + "_" + Params.ISUPDATE).ifPresent(attribute::setIsUpdated);
		    getOptionalBoolean(params, itemKey + "_" + Params.ISCHANGED).ifPresent(attribute::setIsChanged);
		    getOptString(params, itemKey + "_" + Params.VALUE).ifPresent(attribute.getContent()::add);
		    getAttributeRestriction(params, itemKey).ifPresent(attribute::setRestriction); //RT.FFAPI_v19 !
		    return attribute;
		})
		.collect(Collectors.toList())
	);
        return orderItemAttributes;
    }
    
    private OrderItemBillingInfo getOrderItemBillingInfo(Map<String, String> params, String prefix) {
        OrderItemBillingInfo orderItemBillingInfo = new OrderItemBillingInfo();
        return orderItemBillingInfo;
    }

    private OIReferences getOIReferences(Map<String, String> params, final String prefix) {
        OIReferences references = new OIReferences();
        final String key = prefix + "_" + Params.REFERENCES;
        references.getReference().addAll(
	    IntStream.rangeClosed(1, getListSize(params, key))
		.mapToObj(index->{
		    final String itemKey = key + "_" + index;
		    OIReference reference = new OIReference();
		    getOptString(params, itemKey + "_" + Params.NAME).ifPresent(reference::setName);
		    getOptString(params, itemKey + "_" + Params.SPECIFICATION_ID).ifPresent(reference::setSpecId);
		    getOptString(params, itemKey + "_" + Params.VALUE).ifPresent(reference::setValue);
		    getOptionalBoolean(params, itemKey + "_" + Params.ISCHANGED).ifPresent(reference::setIsChanged);
		    getOptionalBoolean(params, itemKey + "_" + Params.ISUPDATE).ifPresent(reference::setIsUpdated);
		    return reference;})
		.collect(Collectors.toList())
	);
        return references;
    }
    
    private Optional<Location> getLocation(Map<String, String> params, final String prefix)  {
        Location location = null;
        final String itemKey = prefix + "_" + Params.LOCATION;
        if (params.containsKey(itemKey + "_" + Params.ID)) {
            location = new Location();
	    getOptString(params, itemKey + "_" + Params.ID).ifPresent(location::setLocationId);
	    getOptString(params, itemKey + "_" + Params.REGISTER).ifPresent(location::setLocationRegister); //RT.FFAPI_v19
	    getLocationCategory(params, itemKey).ifPresent(location::setLocationCategory);
	    location.setLocationAttributes(getAttributes(params, itemKey));
	}
        return Optional.ofNullable(location);
    }
    
    private Optional<LocationCategory> getLocationCategory(Map<String, String> params, String prefix) {        
        String itemKey = prefix + "_" + Params.CATEGORY;
        try {
            Optional<String> value = getOptString(params, itemKey);
	    if (value.isPresent()){
		return Optional.of(LocationCategory.valueOf(value.get().toUpperCase()));
	    }
        } catch (IllegalArgumentException ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());            
        }
        return Optional.empty();
    }

    private Optional<OrderItemSpecification> getOrderItemSpecification(Map<String, String> params, final String prefix) {
        final String itemKey = prefix + "_" + Params.SPECIFICATION;
        if (params.containsKey(itemKey + "_" + Params.ID)) {
	    OrderItemSpecification specification = new OrderItemSpecification();
            getOptString(params, itemKey + "_" + Params.CATALOG_ID).ifPresent(specification::setCatalogId);
	    getOptString(params, itemKey + "_" + Params.ID).ifPresent(specification::setSpecId);
            getOptString(params, itemKey + "_" + Params.INTERNALSPECID).ifPresent(specification::setInternalSpecId);
            getOptString(params, itemKey + "_" + Params.EXTERNALSPECID).ifPresent(specification::setExternalSpecId);
            getOptString(params, itemKey + "_" + Params.VERSION).ifPresent(specification::setSpecVersion);
            getOptString(params, itemKey + "_" + Params.NAME).ifPresent(specification::setSpecName);
	    return Optional.of(specification);
        }
        return Optional.empty();
    }
    
    private Optional<Comments> getComments(Map<String, String> params, final String prefix) {
        final String itemKey = prefix + "_" + Params.COMMENT;
	int size = getListSize(params, itemKey);
	if (size == 0) return Optional.empty();
	Comments comments = new Comments(); 
	comments.getAny().addAll(
	    IntStream.rangeClosed(1, size)
		.mapToObj(index -> getComment(params, itemKey + "_" + index))
		.collect(Collectors.toList())
	);
        return Optional.of(comments);
    }
    
    private Comment getComment(Map<String, String> params, final String itemKey){
	//Params.LOGGER.log(Level.INFO, "load comment itemKey={0}", new Object[]{itemKey});
	Comment comment = new Comment();
	getOptString(params, itemKey + "_" + Params.COMMENTER).ifPresent(comment::setCommenter);
	getOptString(params, itemKey + "_" + Params.TEXT).ifPresent(comment::setText);
	getOptString(params, itemKey + "_" + Params.TYPE).ifPresent(comment::setType);
	getOptCalendar(params, itemKey + "_" + Params.DATE).ifPresent(comment::setDate);
	return comment;
    }
	
    private Optional<OrderParties> getAttachment(Map<String, String> params, final String prefix, OrderParties orderParties) {
        final String key = prefix + "_" + Params.ATTACHMENT;
	int size = getListSize(params, key);
	if (size == 0) return Optional.empty();
	if (orderParties == null){
	    orderParties = new OrderParties();
	}
	orderParties.getOrderPartyOrOrderAttachment().addAll(
	    IntStream.rangeClosed(1, size)
		.mapToObj(index-> {
		    final String itemKey = key + "_" + index + "_";
		    Attachment attachment = new Attachment();
		    getOptString(params, itemKey + Params.TYPE).ifPresent(v -> {
			BigInteger attachmentType = Utils.strToBigInteger(v);
			if (attachmentType != null) {
			    attachment.setAttachmentType(attachmentType);
			}
		    });
		    getOptCalendar(params, itemKey + Params.CREATIONDATE).ifPresent(attachment::setCreationDate);
		    getOptString(params, itemKey + Params.AUTHOR).ifPresent(attachment::setAuthor);
		    getOptString(params, itemKey + Params.URL).ifPresent(attachment::setURL);
		    getOptString(params, itemKey + Params.HEADER).ifPresent(attachment::setHeader);
		    getOptString(params, itemKey + Params.FILENAME).ifPresent(attachment::setFileName);
		    getOptString(params, itemKey + Params.FILEEXTENSION).ifPresent(attachment::setFileExtension);
		    return attachment;
		})
		.collect(Collectors.toList())
	);
	return Optional.of(orderParties);
    }
    
    private Optional<AttributeRestriction> getAttributeRestriction(Map<String, String> params, String itemKey){	
	try {
	    Optional<String> value = Optional.ofNullable(params.get(itemKey + "_" + Params.RESTRICTION));
	    return value.map(v -> AttributeRestriction.fromValue(v));
	} catch (Exception ex) {
	    LOGGER.log(Level.SEVERE, ex.getMessage());
	}
	return Optional.empty();
    }
    
    private Optional<String> getOptString(Map<String, String> params, final String param) {
	//String value = params.getArg(param);
	//logger.printDebug("Get optional parameter: [" + param + "]= " + value);
	return Optional.ofNullable(params.get(param));
    }
    
    private Optional<Long> getOptionalLong(Map<String, String> params, String param){
	String value = params.get(param);
	//logger.printDebug("Get optional Long parameter: [" + param + "] = " + value);
	if (value != null && !"null".equalsIgnoreCase(value)) {
	    try {
		return Optional.of(Long.valueOf(value));
	    } catch (NumberFormatException ex) {
		LOGGER.log(Level.SEVERE, ex.getMessage());
	    }
	}
        return Optional.empty();
    }
	
    private Optional<XMLGregorianCalendar> getOptCalendar(Map<String, String> params, String param) {
        XMLGregorianCalendar result = null;
	Optional<String> value = getOptString(params, param);
        try {
            if (value.isPresent()){
		result = DateUtils.toXMLGregorianCalendar(value.get());
	    }
        } catch (Exception ex) {
            LOGGER.log(Level.SEVERE, ex.getMessage());
        }
        return Optional.ofNullable(result);
    }
	
    private Optional<Boolean> getOptionalBoolean(Map<String, String> params, String param){
	return getOptString(params, param).map(v -> Boolean.valueOf(v));
    }
    
    private int getListSize(Map<String, String> params, String itemKey) {
        String listSizeParam = itemKey + "_" + Params.LISTSIZE;        
        if (params.containsKey(listSizeParam)) {
            return Integer.valueOf(params.get(listSizeParam));
        }
        return 0;
    }
}