package ru.rt.fsom.wfc.data.tickets;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@JsonInclude(JsonInclude.Include.NON_NULL)
public class TicketStatus implements Serializable{
    private static final long serialVersionUID = 1L;
    
    public static final int WAITING_CODE = 0;
    public static final int RESERVED_CODE = 1;
    public static final int SENT_CODE_OK = 2;
    public static final int SENT_CODE_EXPIRED = 3;
    public static final int SENT_CODE_FAIL = 4;
    public static final int RESEND_CODE = 5; 
    public static final int CANCELLED_CODE = 6;
    public static final int TRIGGERABLE_CODE = 7;
    
    private int statusId;
    private String name;
    private String icon;
    
    public TicketStatus(int statusId, String name, String icon) {
	this.statusId = statusId;
	this.name = name;
	this.icon = icon;
    }
	
    @JsonIgnore
    public static final TicketStatus STATUS_WAITING = new TicketStatus(TicketStatus.WAITING_CODE, "Waiting", "clock");
    @JsonIgnore
    public static final TicketStatus STATUS_RESERVED = new TicketStatus(TicketStatus.RESERVED_CODE, "Reserved", "service");
    @JsonIgnore
    public static final TicketStatus STATUS_SENT_EXP = new TicketStatus(TicketStatus.SENT_CODE_EXPIRED, "Sent [EXPIRED]", "done");
    @JsonIgnore
    public static final TicketStatus STATUS_SENT_OK = new TicketStatus(TicketStatus.SENT_CODE_OK, "Sent [OK]", "done");
    @JsonIgnore
    public static final TicketStatus STATUS_SENT_FAIL = new TicketStatus(TicketStatus.SENT_CODE_FAIL, "Sent [FAIL]", "done");
    @JsonIgnore
    public static final TicketStatus STATUS_CANCELLED = new TicketStatus(TicketStatus.CANCELLED_CODE, "Cancelled", "stop");
    @JsonIgnore
    public static final TicketStatus STATUS_RESEND = new TicketStatus(TicketStatus.RESEND_CODE, "Resend", "stop");
    @JsonIgnore
    public static final TicketStatus STATUS_TRIGGERABLE = new TicketStatus(TicketStatus.TRIGGERABLE_CODE, "Triggerable", "importance");

    public TicketStatus() {
    }
     
    @JsonIgnore
    public static final List<TicketStatus> STATUSES = new ArrayList<TicketStatus>() {{	
	add(STATUS_WAITING);
	add(STATUS_RESERVED);
	add(STATUS_SENT_EXP);
	add(STATUS_SENT_OK);
	add(STATUS_SENT_FAIL);	
	add(STATUS_RESEND);
	add(STATUS_CANCELLED);
	add(STATUS_TRIGGERABLE);
    }};    
    
    public int getStatusId() {
	return statusId;
    }
    public void setStatusId(int statusId) {
	this.statusId = statusId;
    }

    public String getName() {
	return name;
    }
    public void setName(String name) {
	this.name = name;
    }

    @JsonIgnore
    public String getIcon16() {
	return icon + "-16";
    }
    
    @JsonIgnore
    public String getStyle(){
	if (statusId == 0 || statusId == 1 || statusId == 5){
	    return "statusWarn";
	}
	if (statusId == 2){
	    return "statusCompleted";
	}
	if (statusId == 7){
	    return "statusInfo";
	}
	if (statusId == 3 ||  statusId == 4){
	    return "statusCritical";
	}
	if (statusId == 6 ){
	    return "statusFail";
	}
	return "statusWarn";
    }
    
    @Override
    public int hashCode() {
	int hash = 7;
	hash = 97 * hash + this.statusId;
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
	final TicketStatus other = (TicketStatus) obj;
	if (this.statusId != other.statusId) {
	    return false;
	}
	return true;
    }

    @Override
    public String toString() {
	return "TicketStatus{" + "statusId=" + statusId + ", name=" + name + '}';
    }
        
}
