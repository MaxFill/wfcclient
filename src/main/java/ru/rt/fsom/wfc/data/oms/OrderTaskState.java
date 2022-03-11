
package ru.rt.fsom.wfc.data.oms;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderTaskState.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OrderTaskState">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PLANNED"/>
 *     &lt;enumeration value="INPROGRESS"/>
 *     &lt;enumeration value="PENDING"/>
 *     &lt;enumeration value="COMPLETED"/>
 *     &lt;enumeration value="CANCELLED"/>
 *     &lt;enumeration value="ONHOLD"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OrderTaskState")
@XmlEnum
public enum OrderTaskState {

    PLANNED,
    INPROGRESS,
    PENDING,
    COMPLETED,
    CANCELLED,
    ONHOLD;

    public String value() {
        return name();
    }

    public static OrderTaskState fromValue(String v) {
        return valueOf(v);
    }

}
