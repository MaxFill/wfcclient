package ru.rt.fsom.wfc.data.tickets;

import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import ru.rt.fsom.wfc.data.oms.Party;
import ru.rt.fsom.wfc.data.oms.RequestOrderItem;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketData implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Integer ticketId;
    private String taskId;
    private Integer requestId;    
    private Integer rowVersion;
    private String modifyingUser;
    private String userName;
    private String ticketInfo;
    private String triggerValue;
    private Integer ticketType;
    private Integer status;
    private Date dateIssue;
    private Date dateExpiration;
    private Date dateProcessed;
    private Date dateJeopardy;    
    private TicketStatus ticketStatus;
    private String jeopardyStatus;
    private Date dateIssueFrom;
    private Date dateIssueTo;
    private Date dateJeopardyFrom;
    private Date dateJeopardyTo;
        
    private Map<String, String> params = new HashMap<>();       
    private List<TicketGroup> ticketGroups = null;
    private List<Party> orderParties = null;
    private List<RequestOrderItem> orderItems = null;
    private List<TicketParam> taskParams = null;    //параметры загруженные из TASK_*
    private List<TicketAttaches> attacheses = null;
    
    public TicketData() {
    }

    public Integer getTicketId() {
	return ticketId;
    }
    public void setTicketId(Integer ticketId) {
	this.ticketId = ticketId;
    }

    public String getTaskId() {
	return taskId;
    }
    public void setTaskId(String taskId) {
	this.taskId = taskId;
    }

    public Integer getRequestId() {
	return requestId;
    }
    public void setRequestId(Integer requestId) {
	this.requestId = requestId;
    }        

    public String getModifyingUser() {
	return modifyingUser;
    }
    public void setModifyingUser(String modifyingUser) {
	this.modifyingUser = modifyingUser;
    }

    public String getUserName() {
	return userName;
    }
    public void setUserName(String userName) {
	this.userName = userName;
    }

    public String getTicketInfo() {
	return ticketInfo;
    }
    public void setTicketInfo(String ticketInfo) {
	this.ticketInfo = ticketInfo;
    }

    public Integer getRowVersion() {
	return rowVersion;
    }
    public void setRowVersion(Integer rowVersion) {
	this.rowVersion = rowVersion;
    }
    
    public String getTriggerValue() {
	return triggerValue;
    }
    public void setTriggerValue(String triggerValue) {
	this.triggerValue = triggerValue;
    }

    public Date getDateIssue() {
	return dateIssue;
    }
    public void setDateIssue(Date dateIssue) {
	this.dateIssue = dateIssue;
    }

    public Date getDateExpiration() {
	return dateExpiration;
    }
    public void setDateExpiration(Date dateExpiration) {
	this.dateExpiration = dateExpiration;
    }

    public Date getDateProcessed() {
	return dateProcessed;
    }
    public void setDateProcessed(Date dateProcessed) {
	this.dateProcessed = dateProcessed;
    }

    public Date getDateJeopardy() {
	return dateJeopardy;
    }
    public void setDateJeopardy(Date dateJeopardy) {
	this.dateJeopardy = dateJeopardy;
    }

    public TicketStatus getTicketStatus() {
	return ticketStatus;
    }
    public void setTicketStatus(TicketStatus ticketStatus) {
	this.ticketStatus = ticketStatus;
    }

    public Integer getTicketType() {
	return ticketType;
    }
    public void setTicketType(Integer ticketType) {
	this.ticketType = ticketType;
    }

    public Date getDateIssueFrom() {
	return dateIssueFrom;
    }
    public void setDateIssueFrom(Date dateIssueFrom) {
	this.dateIssueFrom = dateIssueFrom;
    }

    public Date getDateIssueTo() {
	return dateIssueTo;
    }
    public void setDateIssueTo(Date dateIssueTo) {
	this.dateIssueTo = dateIssueTo;
    }

    public Date getDateJeopardyFrom() {
	return dateJeopardyFrom;
    }
    public void setDateJeopardyFrom(Date dateJeopardyFrom) {
	this.dateJeopardyFrom = dateJeopardyFrom;
    }

    public Date getDateJeopardyTo() {
	return dateJeopardyTo;
    }
    public void setDateJeopardyTo(Date dateJeopardyTo) {
	this.dateJeopardyTo = dateJeopardyTo;
    }       

    public Map<String, String> getParams() {
	return params;
    }
    public void setParams(Map<String, String> params) {
	this.params = params;
    }

    public List<Party> getOrderParties() {
	if (orderParties == null){
	    orderParties = new ArrayList<>();
	}
	return orderParties;
    }
    public void setOrderParties(List<Party> orderParties) {
	this.orderParties = orderParties;
    }

    public List<RequestOrderItem> getOrderItems() {
	if (orderItems == null){
	    orderItems = new ArrayList<>();
	}
	return orderItems;
    }
    public void setOrderItems(List<RequestOrderItem> orderItems) {
	this.orderItems = orderItems;
    }

    public List<TicketParam> getTaskParams() {
	if (taskParams == null){
	    taskParams = new ArrayList<>();
	}
	return taskParams;
    }
    public void setTaskParams(List<TicketParam> taskParams) {
	this.taskParams = taskParams;
    }

    public Integer getStatus() {
	return status;
    }
    public void setStatus(Integer status) {
	this.status = status;
    }

    public List<TicketGroup> getTicketGroup() {
	return ticketGroups;
    }
    public void setTicketGroup(List<TicketGroup> ticketGroup) {
	this.ticketGroups = ticketGroup;
    }              

    public String getJeopardyStatus() {
	return jeopardyStatus;
    }
    public void setJeopardyStatus(String jeopardyStatus) {
	this.jeopardyStatus = jeopardyStatus;
    }

    public List<TicketAttaches> getAttacheses() {
	return attacheses;
    }
    public void setAttacheses(List<TicketAttaches> attacheses) {
	this.attacheses = attacheses;
    }
    
    /* *** *** */
    
    @Override
    public int hashCode() {
	int hash = 3;
	hash = 23 * hash + Objects.hashCode(this.ticketId);
	hash = 23 * hash + Objects.hashCode(this.requestId);
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
	final TicketData other = (TicketData) obj;
	if (!Objects.equals(this.ticketId, other.ticketId)) {
	    return false;
	}
	if (!Objects.equals(this.requestId, other.requestId)) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "Ticket{" + "d=" + ticketId + ", Request=" + requestId + ", Type=" + ticketType + '}';
    }   
        
}
