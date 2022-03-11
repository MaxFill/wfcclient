
package ru.rt.fsom.wfc.data.oms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for SyncSubmitResponseOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SyncSubmitResponseOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="orderId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderOMSId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderOMSurl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderAttributes" type="{http://oms.rt.ru/}Attributes" minOccurs="0"/>
 *         &lt;element name="orderState" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="orderCompletionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/>
 *         &lt;element name="orderParties" type="{http://oms.rt.ru/}OrderParties" minOccurs="0"/>
 *         &lt;element name="orderItems" type="{http://oms.rt.ru/}ResponseOrderItems" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SyncSubmitResponseOrder", propOrder = {

})
public class SyncSubmitResponseOrder {

    @XmlElement(required = true)
    protected String orderId;
    @XmlElement(required = true)
    protected String orderOMSId;
    protected String orderOMSurl;
    protected Attributes orderAttributes;
    @XmlElement(required = true)
    protected String orderState;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar orderStartDate;
    @XmlElement(required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar orderCompletionDate;
    protected OrderParties orderParties;
    protected ResponseOrderItems orderItems;

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
     * Gets the value of the orderOMSurl property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderOMSurl() {
        return orderOMSurl;
    }

    /**
     * Sets the value of the orderOMSurl property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderOMSurl(String value) {
        this.orderOMSurl = value;
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
     * Gets the value of the orderState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderState() {
        return orderState;
    }

    /**
     * Sets the value of the orderState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderState(String value) {
        this.orderState = value;
    }

    /**
     * Gets the value of the orderStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrderStartDate() {
        return orderStartDate;
    }

    /**
     * Sets the value of the orderStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrderStartDate(XMLGregorianCalendar value) {
        this.orderStartDate = value;
    }

    /**
     * Gets the value of the orderCompletionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrderCompletionDate() {
        return orderCompletionDate;
    }

    /**
     * Sets the value of the orderCompletionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrderCompletionDate(XMLGregorianCalendar value) {
        this.orderCompletionDate = value;
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
     *     {@link ResponseOrderItems }
     *     
     */
    public ResponseOrderItems getOrderItems() {
        return orderItems;
    }

    /**
     * Sets the value of the orderItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link ResponseOrderItems }
     *     
     */
    public void setOrderItems(ResponseOrderItems value) {
        this.orderItems = value;
    }

}
