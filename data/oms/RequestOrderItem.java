package ru.rt.fsom.wfc.data.oms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * <p>Java class for RequestOrderItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="RequestOrderItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="orderItemId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderItemInstanceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderItemAction" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderItemActionReason" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderItemActionModifier" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderItemCategory" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderItemPriority" type="{http://www.w3.org/2001/XMLSchema}long" minOccurs="0"/>
 *         &lt;element name="orderItemRequiredDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="orderItemSpecification" type="{http://oms.rt.ru/}OrderItemSpecification" minOccurs="0"/>
 *         &lt;element name="orderItemLocation" type="{http://oms.rt.ru/}Location" minOccurs="0"/>
 *         &lt;element name="orderItemComments" type="{http://oms.rt.ru/}Comments" minOccurs="0"/>
 *         &lt;element name="orderItemAttributes" type="{http://oms.rt.ru/}InheritableAttributes" minOccurs="0"/>
 *         &lt;element name="orderItemParties" type="{http://oms.rt.ru/}OrderParties" minOccurs="0"/>
 *         &lt;element name="orderItemBillingInfo" type="{http://oms.rt.ru/}OrderItemBillingInfo" minOccurs="0"/>
 *         &lt;element name="orderItemAppointmentRequired" type="{http://www.w3.org/2001/XMLSchema}boolean" minOccurs="0"/>
 *         &lt;element name="orderItemAppointmentId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderItemReferences" type="{http://oms.rt.ru/}OIReferences" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RequestOrderItem", propOrder = {

})
public class RequestOrderItem {

    @XmlElement(required = true)
    protected String orderItemId;
    protected String orderItemInstanceId;
    @XmlElement(required = true)
    protected String orderItemAction;
    protected String orderItemActionReason;
    protected String orderItemActionModifier;
    @XmlElement(required = true)
    protected String orderItemCategory;
    protected Long orderItemPriority;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar orderItemRequiredDate;
    protected OrderItemSpecification orderItemSpecification;
    protected Location orderItemLocation;
    protected Comments orderItemComments;
    protected InheritableAttributes orderItemAttributes;
    protected OrderParties orderItemParties;
    protected OrderItemBillingInfo orderItemBillingInfo;
    protected Boolean orderItemAppointmentRequired;
    protected String orderItemAppointmentId;
    protected OIReferences orderItemReferences;

    /**
     * Gets the value of the orderItemId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderItemId() {
        return orderItemId;
    }

    /**
     * Sets the value of the orderItemId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderItemId(String value) {
        this.orderItemId = value;
    }

    /**
     * Gets the value of the orderItemInstanceId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderItemInstanceId() {
        return orderItemInstanceId;
    }

    /**
     * Sets the value of the orderItemInstanceId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderItemInstanceId(String value) {
        this.orderItemInstanceId = value;
    }

    /**
     * Gets the value of the orderItemAction property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderItemAction() {
        return orderItemAction;
    }

    /**
     * Sets the value of the orderItemAction property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderItemAction(String value) {
        this.orderItemAction = value;
    }

    /**
     * Gets the value of the orderItemActionReason property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderItemActionReason() {
        return orderItemActionReason;
    }

    /**
     * Sets the value of the orderItemActionReason property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderItemActionReason(String value) {
        this.orderItemActionReason = value;
    }

    /**
     * Gets the value of the orderItemActionModifier property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderItemActionModifier() {
        return orderItemActionModifier;
    }

    /**
     * Sets the value of the orderItemActionModifier property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderItemActionModifier(String value) {
        this.orderItemActionModifier = value;
    }

    /**
     * Gets the value of the orderItemCategory property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderItemCategory() {
        return orderItemCategory;
    }

    /**
     * Sets the value of the orderItemCategory property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderItemCategory(String value) {
        this.orderItemCategory = value;
    }

    /**
     * Gets the value of the orderItemPriority property.
     * 
     * @return
     *     possible object is
     *     {@link Long }
     *     
     */
    public Long getOrderItemPriority() {
        return orderItemPriority;
    }

    /**
     * Sets the value of the orderItemPriority property.
     * 
     * @param value
     *     allowed object is
     *     {@link Long }
     *     
     */
    public void setOrderItemPriority(Long value) {
        this.orderItemPriority = value;
    }

    /**
     * Gets the value of the orderItemRequiredDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrderItemRequiredDate() {
        return orderItemRequiredDate;
    }

    /**
     * Sets the value of the orderItemRequiredDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrderItemRequiredDate(XMLGregorianCalendar value) {
        this.orderItemRequiredDate = value;
    }

    /**
     * Gets the value of the orderItemSpecification property.
     * 
     * @return
     *     possible object is
     *     {@link OrderItemSpecification }
     *     
     */
    public OrderItemSpecification getOrderItemSpecification() {
        return orderItemSpecification;
    }

    /**
     * Sets the value of the orderItemSpecification property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderItemSpecification }
     *     
     */
    public void setOrderItemSpecification(OrderItemSpecification value) {
        this.orderItemSpecification = value;
    }

    /**
     * Gets the value of the orderItemLocation property.
     * 
     * @return
     *     possible object is
     *     {@link Location }
     *     
     */
    public Location getOrderItemLocation() {
        return orderItemLocation;
    }

    /**
     * Sets the value of the orderItemLocation property.
     * 
     * @param value
     *     allowed object is
     *     {@link Location }
     *     
     */
    public void setOrderItemLocation(Location value) {
        this.orderItemLocation = value;
    }

    /**
     * Gets the value of the orderItemComments property.
     * 
     * @return
     *     possible object is
     *     {@link Comments }
     *     
     */
    public Comments getOrderItemComments() {
        return orderItemComments;
    }

    /**
     * Sets the value of the orderItemComments property.
     * 
     * @param value
     *     allowed object is
     *     {@link Comments }
     *     
     */
    public void setOrderItemComments(Comments value) {
        this.orderItemComments = value;
    }

    /**
     * Gets the value of the orderItemAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link InheritableAttributes }
     *     
     */
    public InheritableAttributes getOrderItemAttributes() {
        return orderItemAttributes;
    }

    /**
     * Sets the value of the orderItemAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link InheritableAttributes }
     *     
     */
    public void setOrderItemAttributes(InheritableAttributes value) {
        this.orderItemAttributes = value;
    }

    /**
     * Gets the value of the orderItemParties property.
     * 
     * @return
     *     possible object is
     *     {@link OrderParties }
     *     
     */
    public OrderParties getOrderItemParties() {
        return orderItemParties;
    }

    /**
     * Sets the value of the orderItemParties property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderParties }
     *     
     */
    public void setOrderItemParties(OrderParties value) {
        this.orderItemParties = value;
    }

    /**
     * Gets the value of the orderItemBillingInfo property.
     * 
     * @return
     *     possible object is
     *     {@link OrderItemBillingInfo }
     *     
     */
    public OrderItemBillingInfo getOrderItemBillingInfo() {
        return orderItemBillingInfo;
    }

    /**
     * Sets the value of the orderItemBillingInfo property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderItemBillingInfo }
     *     
     */
    public void setOrderItemBillingInfo(OrderItemBillingInfo value) {
        this.orderItemBillingInfo = value;
    }

    /**
     * Gets the value of the orderItemAppointmentRequired property.
     * 
     * @return
     *     possible object is
     *     {@link Boolean }
     *     
     */
    public Boolean isOrderItemAppointmentRequired() {
        return orderItemAppointmentRequired;
    }

    /**
     * Sets the value of the orderItemAppointmentRequired property.
     * 
     * @param value
     *     allowed object is
     *     {@link Boolean }
     *     
     */
    public void setOrderItemAppointmentRequired(Boolean value) {
        this.orderItemAppointmentRequired = value;
    }

    /**
     * Gets the value of the orderItemAppointmentId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderItemAppointmentId() {
        return orderItemAppointmentId;
    }

    /**
     * Sets the value of the orderItemAppointmentId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderItemAppointmentId(String value) {
        this.orderItemAppointmentId = value;
    }

    /**
     * Gets the value of the orderItemReferences property.
     * 
     * @return
     *     possible object is
     *     {@link OIReferences }
     *     
     */
    public OIReferences getOrderItemReferences() {
        return orderItemReferences;
    }

    /**
     * Sets the value of the orderItemReferences property.
     * 
     * @param value
     *     allowed object is
     *     {@link OIReferences }
     *     
     */
    public void setOrderItemReferences(OIReferences value) {
        this.orderItemReferences = value;
    }

}
