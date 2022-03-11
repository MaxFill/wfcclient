
package ru.rt.fsom.wfc.data.oms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for QueryRequestOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="QueryRequestOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;all>
 *         &lt;element name="orderId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderOMSId" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *         &lt;element name="branch" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="affiliate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="orderParties" type="{http://oms.rt.ru/}OrderParties" minOccurs="0"/>
 *         &lt;element name="orderItems" type="{http://oms.rt.ru/}QueryOrderItems" minOccurs="0"/>
 *       &lt;/all>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "QueryRequestOrder", propOrder = {

})
public class QueryRequestOrder {

    protected String orderId;
    @XmlElement(required = true)
    protected String orderOMSId;
    protected String branch;
    protected String affiliate;
    protected OrderParties orderParties;
    protected QueryOrderItems orderItems;

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
     *     {@link QueryOrderItems }
     *     
     */
    public QueryOrderItems getOrderItems() {
        return orderItems;
    }

    /**
     * Sets the value of the orderItems property.
     * 
     * @param value
     *     allowed object is
     *     {@link QueryOrderItems }
     *     
     */
    public void setOrderItems(QueryOrderItems value) {
        this.orderItems = value;
    }

}
