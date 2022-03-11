
package ru.rt.fsom.wfc.data.oms;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for SubmitResponseOrder complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="SubmitResponseOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;choice>
 *         &lt;element name="response" type="{http://oms.rt.ru/}SyncSubmitResponseOrder"/>
 *         &lt;element name="acknowledgement" type="{http://oms.rt.ru/}AsyncSubmitResponseOrder"/>
 *       &lt;/choice>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "SubmitResponseOrder", propOrder = {
    "response",
    "acknowledgement"
})
public class SubmitResponseOrder {

    protected SyncSubmitResponseOrder response;
    protected AsyncSubmitResponseOrder acknowledgement;

    /**
     * Gets the value of the response property.
     * 
     * @return
     *     possible object is
     *     {@link SyncSubmitResponseOrder }
     *     
     */
    public SyncSubmitResponseOrder getResponse() {
        return response;
    }

    /**
     * Sets the value of the response property.
     * 
     * @param value
     *     allowed object is
     *     {@link SyncSubmitResponseOrder }
     *     
     */
    public void setResponse(SyncSubmitResponseOrder value) {
        this.response = value;
    }

    /**
     * Gets the value of the acknowledgement property.
     * 
     * @return
     *     possible object is
     *     {@link AsyncSubmitResponseOrder }
     *     
     */
    public AsyncSubmitResponseOrder getAcknowledgement() {
        return acknowledgement;
    }

    /**
     * Sets the value of the acknowledgement property.
     * 
     * @param value
     *     allowed object is
     *     {@link AsyncSubmitResponseOrder }
     *     
     */
    public void setAcknowledgement(AsyncSubmitResponseOrder value) {
        this.acknowledgement = value;
    }

}
