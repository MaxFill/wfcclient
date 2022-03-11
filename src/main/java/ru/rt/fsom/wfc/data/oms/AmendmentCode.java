
package ru.rt.fsom.wfc.data.oms;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AmendmentCode.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;simpleType name="AmendmentCode">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="ORDERITEMS"/>
 *     &lt;enumeration value="ORDERATTRIBUTES"/>
 *     &lt;enumeration value="GATEPASSED"/>
 *     &lt;enumeration value="ORDERPARTY"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "AmendmentCode")
@XmlEnum
public enum AmendmentCode {


    /**
     * Изменения в составе и параметрах компонентов заказа
     * 
     */
    ORDERITEMS,

    /**
     * Изменения в параметрах заказа
     * 
     */
    ORDERATTRIBUTES,

    /**
     * Внешний процесс прошел точку синхронизации
     * 
     */
    GATEPASSED,

    /**
     * Изменения в объекте заказа orderParty
     * 
     */
    ORDERPARTY;

    public String value() {
        return name();
    }

    public static AmendmentCode fromValue(String v) {
        return valueOf(v);
    }

}
