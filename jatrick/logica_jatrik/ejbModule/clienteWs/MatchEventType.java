
package clienteWs;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para matchEventType.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * <p>
 * <pre>
 * &lt;simpleType name="matchEventType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="GOAL"/>
 *     &lt;enumeration value="CARD_RED"/>
 *     &lt;enumeration value="CARD_YELLOW"/>
 *     &lt;enumeration value="INJURED"/>
 *     &lt;enumeration value="FOULT"/>
 *     &lt;enumeration value="EMPTY_1"/>
 *     &lt;enumeration value="EMPTY_2"/>
 *     &lt;enumeration value="EMPTY_3"/>
 *     &lt;enumeration value="EMPTY_4"/>
 *     &lt;enumeration value="EMPTY_5"/>
 *     &lt;enumeration value="EMPTY_6"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 * 
 */
@XmlType(name = "matchEventType")
@XmlEnum
public enum MatchEventType {

    GOAL,
    CARD_RED,
    CARD_YELLOW,
    INJURED,
    FOULT,
    EMPTY_1,
    EMPTY_2,
    EMPTY_3,
    EMPTY_4,
    EMPTY_5,
    EMPTY_6;

    public String value() {
        return name();
    }

    public static MatchEventType fromValue(String v) {
        return valueOf(v);
    }

}
