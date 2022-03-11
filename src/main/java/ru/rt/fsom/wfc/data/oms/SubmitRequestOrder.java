package ru.rt.fsom.wfc.data.oms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for SubmitRequestOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubmitRequestOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="orderId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderOMSId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderType" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderParentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderPriority" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="orderChannel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="branch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="affiliate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderAttributes" type="{http://oms.rt.ru/}Attributes" minOccurs="0"/>
 *         &lt;element name="orderDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="orderRequestedStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="orderRequiredDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="orderComments" type="{http://oms.rt.ru/}Comments" minOccurs="0"/>
 *         &lt;element name="orderLocation" type="{http://oms.rt.ru/}Location" minOccurs="0"/>
 *         &lt;element name="orderParties" type="{http://oms.rt.ru/}OrderParties" minOccurs="0"/>
 *         &lt;element name="orderItems" type="{http://oms.rt.ru/}RequestOrderItems"/>
 *         &lt;element name="orderAppointmentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubmitRequestOrder", propOrder = {

})
public class SubmitRequestOrder {

    @XmlElement(required = true)
    protected String orderId;
    protected String orderOMSId;
    @XmlElement(required = true)
    protected String orderType;
    protected String orderParentId;
    protected Long orderPriority;
    protected String orderChannel;
    protected String branch;
    protected String affiliate;
    protected Attributes orderAttributes;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar orderDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar orderRequestedStartDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar orderRequiredDate;
    protected Comments orderComments;
    protected Location orderLocation;
    protected OrderParties orderParties;
    @XmlElement(required = true)
    protected RequestOrderItems orderItems;
    protected String orderAppointmentId;

    /**
     * Gets the value of the orderId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderId() {
        return orderId;
    }

    /**
     * Sets the value of the orderId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderId(String value) {
        this.orderId = value;
    }

    /**
     * Gets the value of the orderOMSId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderOMSId() {
        return orderOMSId;
    }

    /**
     * Sets the value of the orderOMSId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderOMSId(String value) {
        this.orderOMSId = value;
    }

    /**
     * Gets the value of the orderType property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderType() {
        return orderType;
    }

    /**
     * Sets the value of the orderType property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderType(String value) {
        this.orderType = value;
    }

    /**
     * Gets the value of the orderParentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderParentId() {
        return orderParentId;
    }

    /**
     * Sets the value of the orderParentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderParentId(String value) {
        this.orderParentId = value;
    }

    /**
     * Gets the value of the orderPriority property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOrderPriority() {
        return orderPriority;
    }

    /**
     * Sets the value of the orderPriority property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOrderPriority(Long value) {
        this.orderPriority = value;
    }

    /**
     * Gets the value of the orderChannel property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderChannel() {
        return orderChannel;
    }

    /**
     * Sets the value of the orderChannel property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderChannel(String value) {
        this.orderChannel = value;
    }

    /**
     * Gets the value of the branch property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBranch() {
        return branch;
    }

    /**
     * Sets the value of the branch property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBranch(String value) {
        this.branch = value;
    }

    /**
     * Gets the value of the affiliate property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAffiliate() {
        return affiliate;
    }

    /**
     * Sets the value of the affiliate property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAffiliate(String value) {
        this.affiliate = value;
    }

    /**
     * Gets the value of the orderAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link Attributes }
     *     
     */
    public Attributes getOrderAttributes() {
        return orderAttributes;
    }

    /**
     * Sets the value of the orderAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Attributes }
     *     
     */
    public void setOrderAttributes(Attributes value) {
        this.orderAttributes = value;
    }

    /**
     * Gets the value of the orderDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrderDate() {
        return orderDate;
    }

    /**
     * Sets the value of the orderDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrderDate(XMLGregorianCalendar value) {
        this.orderDate = value;
    }

    /**
     * Gets the value of the orderRequestedStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrderRequestedStartDate() {
        return orderRequestedStartDate;
    }

    /**
     * Sets the value of the orderRequestedStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrderRequestedStartDate(XMLGregorianCalendar value) {
        this.orderRequestedStartDate = value;
    }

    /**
     * Gets the value of the orderRequiredDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrderRequiredDate() {
        return orderRequiredDate;
    }

    /**
     * Sets the value of the orderRequiredDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrderRequiredDate(XMLGregorianCalendar value) {
        this.orderRequiredDate = value;
    }

    /**
     * Gets the value of the orderComments property.
     * 
     * @return
     *     possible object is
     *     {@link Comments }
     *     
     */
    public Comments getOrderComments() {
        return orderComments;
    }

    /**
     * Sets the value of the orderComments property.
     * 
     * @param value
     *     allowed object is
     *     {@link Comments }
     *     
     */
    public void setOrderComments(Comments value) {
        this.orderComments = value;
    }

    /**
     * Gets the value of the orderLocation property.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public Location getOrderLocation() {
        return orderLocation;
    }

    /**
     * Sets the value of the orderLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public void setOrderLocation(Location value) {
        this.orderLocation = value;
    }

    /**
     * Gets the value of the orderParties property.
     * 
     * @return
     *     possible object is
     *     {@link OrderParties }
     *     
     */
    public OrderParties getOrderParties() {
        return orderParties;
    }

    /**
     * Sets the value of the orderParties property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderParties }
     *     
     */
    public void setOrderParties(OrderParties value) {
        this.orderParties = value;
    }

    /**
     * Gets the value of the orderItems property.
     * 
     * @return
     *     possible object is
     *     {@link RequestOrderItems }
     *     
     */
    public RequestOrderItems getOrderItems() {
        return orderItems;
    }

    /**
     * Sets the value of the orderItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link RequestOrderItems }
     *     
     */
    public void setOrderItems(RequestOrderItems value) {
        this.orderItems = value;
    }

    /**
     * Gets the value of the orderAppointmentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderAppointmentId() {
        return orderAppointmentId;
    }

    /**
     * Sets the value of the orderAppointmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderAppointmentId(String value) {
        this.orderAppointmentId = value;
    }

}
