
package ru.rt.fsom.wfc.data.oms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for AsyncSubmitResponseOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AsyncSubmitResponseOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="orderId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderOMSId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderOMSurl" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="orderExpectedCompletionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="orderState" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AsyncSubmitResponseOrder", propOrder = {

})
public class AsyncSubmitResponseOrder {

    @XmlElement(required = true)
    protected String orderId;
    @XmlElement(required = true)
    protected String orderOMSId;
    protected String orderOMSurl;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar orderStartDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar orderExpectedCompletionDate;
    @XmlElement(required = true)
    protected String orderState;

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
     * Gets the value of the orderExpectedCompletionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrderExpectedCompletionDate() {
        return orderExpectedCompletionDate;
    }

    /**
     * Sets the value of the orderExpectedCompletionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrderExpectedCompletionDate(XMLGregorianCalendar value) {
        this.orderExpectedCompletionDate = value;
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

}
