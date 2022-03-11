
package ru.rt.fsom.wfc.data.oms;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for OrderType.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="OrderType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="PRECHECK"/>
 *     &lt;enumeration value="CHECK"/>
 *     &lt;enumeration value="PREBUILD"/>
 *     &lt;enumeration value="ESTIMATE"/>
 *     &lt;enumeration value="QUALIFY"/>
 *     &lt;enumeration value="INVEST"/>
 *     &lt;enumeration value="BUILD"/>
 *     &lt;enumeration value="PREPROVIDE"/>
 *     &lt;enumeration value="PROVIDE"/>
 *     &lt;enumeration value="ACTIVATE"/>
 *     &lt;enumeration value="COMMIT"/>
 *     &lt;enumeration value="CANCEL"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "OrderType")
@XmlEnum
public enum OrderType {

    PRECHECK,
    CHECK,
    PREBUILD,
    ESTIMATE,
    QUALIFY,
    INVEST,
    BUILD,
    PREPROVIDE,
    PROVIDE,
    ACTIVATE,
    COMMIT,
    CANCEL;

    public String value() {
        return name();
    }

    public static OrderType fromValue(String v) {
        return valueOf(v);
    }

}
