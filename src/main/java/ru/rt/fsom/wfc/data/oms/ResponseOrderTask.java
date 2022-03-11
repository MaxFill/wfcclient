
package ru.rt.fsom.wfc.data.oms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Java class for ResponseOrderTask complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseOrderTask">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="orderTaskId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderTaskName" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderTaskGroupName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderTaskStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="orderTaskCompletionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="orderTaskExpectedStartDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="orderTaskExpectedCompletionDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *         &lt;element name="orderTaskState" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderTaskComments" type="{http://oms.rt.ru/}Comments" minOccurs="0"/>
 *         &lt;element name="orderTaskAttributes" type="{http://oms.rt.ru/}Attributes" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseOrderTask", propOrder = {

})
public class ResponseOrderTask {

    @XmlElement(required = true)
    protected String orderTaskId;
    @XmlElement(required = true)
    protected String orderTaskName;
    protected String orderTaskGroupName;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar orderTaskStartDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar orderTaskCompletionDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar orderTaskExpectedStartDate;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar orderTaskExpectedCompletionDate;
    @XmlElement(required = true)
    protected String orderTaskState;
    protected Comments orderTaskComments;
    protected Attributes orderTaskAttributes;

    /**
     * Gets the value of the orderTaskId property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderTaskId() {
        return orderTaskId;
    }

    /**
     * Sets the value of the orderTaskId property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderTaskId(String value) {
        this.orderTaskId = value;
    }

    /**
     * Gets the value of the orderTaskName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderTaskName() {
        return orderTaskName;
    }

    /**
     * Sets the value of the orderTaskName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderTaskName(String value) {
        this.orderTaskName = value;
    }

    /**
     * Gets the value of the orderTaskGroupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderTaskGroupName() {
        return orderTaskGroupName;
    }

    /**
     * Sets the value of the orderTaskGroupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderTaskGroupName(String value) {
        this.orderTaskGroupName = value;
    }

    /**
     * Gets the value of the orderTaskStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrderTaskStartDate() {
        return orderTaskStartDate;
    }

    /**
     * Sets the value of the orderTaskStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrderTaskStartDate(XMLGregorianCalendar value) {
        this.orderTaskStartDate = value;
    }

    /**
     * Gets the value of the orderTaskCompletionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrderTaskCompletionDate() {
        return orderTaskCompletionDate;
    }

    /**
     * Sets the value of the orderTaskCompletionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrderTaskCompletionDate(XMLGregorianCalendar value) {
        this.orderTaskCompletionDate = value;
    }

    /**
     * Gets the value of the orderTaskExpectedStartDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrderTaskExpectedStartDate() {
        return orderTaskExpectedStartDate;
    }

    /**
     * Sets the value of the orderTaskExpectedStartDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrderTaskExpectedStartDate(XMLGregorianCalendar value) {
        this.orderTaskExpectedStartDate = value;
    }

    /**
     * Gets the value of the orderTaskExpectedCompletionDate property.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getOrderTaskExpectedCompletionDate() {
        return orderTaskExpectedCompletionDate;
    }

    /**
     * Sets the value of the orderTaskExpectedCompletionDate property.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setOrderTaskExpectedCompletionDate(XMLGregorianCalendar value) {
        this.orderTaskExpectedCompletionDate = value;
    }

    /**
     * Gets the value of the orderTaskState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderTaskState() {
        return orderTaskState;
    }

    /**
     * Sets the value of the orderTaskState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderTaskState(String value) {
        this.orderTaskState = value;
    }

    /**
     * Gets the value of the orderTaskComments property.
     * 
     * @return
     *     possible object is
     *     {@link Comments }
     *     
     */
    public Comments getOrderTaskComments() {
        return orderTaskComments;
    }

    /**
     * Sets the value of the orderTaskComments property.
     * 
     * @param value
     *     allowed object is
     *     {@link Comments }
     *     
     */
    public void setOrderTaskComments(Comments value) {
        this.orderTaskComments = value;
    }

    /**
     * Gets the value of the orderTaskAttributes property.
     * 
     * @return
     *     possible object is
     *     {@link Attributes }
     *     
     */
    public Attributes getOrderTaskAttributes() {
        return orderTaskAttributes;
    }

    /**
     * Sets the value of the orderTaskAttributes property.
     * 
     * @param value
     *     allowed object is
     *     {@link Attributes }
     *     
     */
    public void setOrderTaskAttributes(Attributes value) {
        this.orderTaskAttributes = value;
    }

}
