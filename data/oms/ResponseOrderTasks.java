
package ru.rt.fsom.wfc.data.oms;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ResponseOrderTasks complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ResponseOrderTasks">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="orderTask" type="{http://oms.rt.ru/}ResponseOrderTask" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ResponseOrderTasks", propOrder = {
    "orderTask"
})
public class ResponseOrderTasks {

    protected List<ResponseOrderTask> orderTask;

    /**
     * Gets the value of the orderTask property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the orderTask property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getOrderTask().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ResponseOrderTask }
     * 
     * 
     */
    public List<ResponseOrderTask> getOrderTask() {
        if (orderTask == null) {
            orderTask = new ArrayList<ResponseOrderTask>();
        }
        return this.orderTask;
    }

}
