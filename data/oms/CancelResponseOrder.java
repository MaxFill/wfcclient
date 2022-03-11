
package ru.rt.fsom.wfc.data.oms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for CancelResponseOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="CancelResponseOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="orderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderOMSId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderState" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="orderCompletionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "CancelResponseOrder", propOrder = {

})
public class CancelResponseOrder {

    protected String orderId;
    @XmlElement(required = true)
    protected String orderOMSId;
    @XmlElement(required = true)
    protected String orderState;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar orderStartDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar orderCompletionDate;

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

}
