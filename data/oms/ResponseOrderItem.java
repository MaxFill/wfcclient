
package ru.rt.fsom.wfc.data.oms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseOrderItem complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseOrderItem">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="orderItemId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="orderItemInstanceId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderItemState" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderItemSpecification" type="{http://oms.rt.ru/}OrderItemSpecification" minOccurs="0"/>
 *         &lt;element name="orderItemAttributes" type="{http://oms.rt.ru/}InheritableAttributes" minOccurs="0"/>
 *         &lt;element name="orderItemParties" type="{http://oms.rt.ru/}OrderParties" minOccurs="0"/>
 *         &lt;element name="orderItemAction" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderItemResult" type="{http://oms.rt.ru/}OrderItemResult" minOccurs="0"/>
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
@XmlType(name = "ResponseOrderItem", propOrder = {

})
public class ResponseOrderItem {

    @XmlElement(required = true)
    protected String orderItemId;
    protected String orderItemInstanceId;
    protected String orderItemState;
    protected OrderItemSpecification orderItemSpecification;
    protected InheritableAttributes orderItemAttributes;
    protected OrderParties orderItemParties;
    protected String orderItemAction;
    protected OrderItemResult orderItemResult;
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
     * Gets the value of the orderItemState property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOrderItemState() {
        return orderItemState;
    }

    /**
     * Sets the value of the orderItemState property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOrderItemState(String value) {
        this.orderItemState = value;
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
     * Gets the value of the orderItemResult property.
     * 
     * @return
     *     possible object is
     *     {@link OrderItemResult }
     *     
     */
    public OrderItemResult getOrderItemResult() {
        return orderItemResult;
    }

    /**
     * Sets the value of the orderItemResult property.
     * 
     * @param value
     *     allowed object is
     *     {@link OrderItemResult }
     *     
     */
    public void setOrderItemResult(OrderItemResult value) {
        this.orderItemResult = value;
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
