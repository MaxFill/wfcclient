package ru.rt.fsom.wfc.data.oms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubmitOrderRequest", propOrder = {
    "originator",
    "receiver",
    "callbackEndpoint",
    "mode",
    "requestId",
    "order"
})
public class SubmitOrderRequest {

    @XmlElement(required = true)
    protected String originator;
    @XmlElement(required = true)
    protected String receiver;
    protected String callbackEndpoint;
    protected OrderMode mode;
    @XmlElement(required = true)
    protected String requestId;
    @XmlElement(required = true)
    protected SubmitRequestOrder order;

    /**
     * Gets the value of the originator property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOriginator() {
        return originator;
    }

    /**
     * Sets the value of the originator property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOriginator(String value) {
        this.originator = value;
    }

    /**
     * Gets the value of the receiver property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReceiver() {
        return receiver;
    }

    /**
     * Sets the value of the receiver property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReceiver(String value) {
        this.receiver = value;
    }

    /**
     * Gets the value of the callbackEndpoint property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCallbackEndpoint() {
        return callbackEndpoint;
    }

    /**
     * Sets the value of the callbackEndpoint property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCallbackEndpoint(String value) {
        this.callbackEndpoint = value;
    }

    /**
     * Gets the value of the mode property.
     * 
     * @return
     *     possible object is
     *     {@link OrderMode }
     *     
     */
    public OrderMode getMode() {
        return mode;
    }

    /**
     * Sets the value of the mode property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderMode }
     *     
     */
    public void setMode(OrderMode value) {
        this.mode = value;
    }

    /**
     * Gets the value of the requestId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRequestId() {
        return requestId;
    }

    /**
     * Sets the value of the requestId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRequestId(String value) {
        this.requestId = value;
    }

    /**
     * Gets the value of the order property.
     * 
     * @return
     *     possible object is
     *     {@link SubmitRequestOrder }
     *     
     */
    public SubmitRequestOrder getOrder() {
        return order;
    }

    /**
     * Sets the value of the order property.
     * 
     * @param value
     *     allowed object is
     *     {@link SubmitRequestOrder }
     *     
     */
    public void setOrder(SubmitRequestOrder value) {
        this.order = value;
    }

}
