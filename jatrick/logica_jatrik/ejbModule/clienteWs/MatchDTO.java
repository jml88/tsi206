
package clienteWs;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para matchDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="matchDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="awayGoals" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="awayTeam" type="{http://tsi201.com/}teamDTO" minOccurs="0"/>
 *         &lt;element name="currentMinute" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="events" type="{http://tsi201.com/}matchEventDTO" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="localGoals" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="localTeam" type="{http://tsi201.com/}teamDTO" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://www.w3.org/2001/XMLSchema}dateTime" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "matchDTO", propOrder = {
    "awayGoals",
    "awayTeam",
    "currentMinute",
    "events",
    "id",
    "localGoals",
    "localTeam",
    "startDate"
})
public class MatchDTO {

    protected Integer awayGoals;
    protected TeamDTO awayTeam;
    protected Integer currentMinute;
    @XmlElement(nillable = true)
    protected List<MatchEventDTO> events;
    protected Integer id;
    protected Integer localGoals;
    protected TeamDTO localTeam;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;

    /**
     * Obtiene el valor de la propiedad awayGoals.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getAwayGoals() {
        return awayGoals;
    }

    /**
     * Define el valor de la propiedad awayGoals.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setAwayGoals(Integer value) {
        this.awayGoals = value;
    }

    /**
     * Obtiene el valor de la propiedad awayTeam.
     * 
     * @return
     *     possible object is
     *     {@link TeamDTO }
     *     
     */
    public TeamDTO getAwayTeam() {
        return awayTeam;
    }

    /**
     * Define el valor de la propiedad awayTeam.
     * 
     * @param value
     *     allowed object is
     *     {@link TeamDTO }
     *     
     */
    public void setAwayTeam(TeamDTO value) {
        this.awayTeam = value;
    }

    /**
     * Obtiene el valor de la propiedad currentMinute.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getCurrentMinute() {
        return currentMinute;
    }

    /**
     * Define el valor de la propiedad currentMinute.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setCurrentMinute(Integer value) {
        this.currentMinute = value;
    }

    /**
     * Gets the value of the events property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the events property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEvents().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link MatchEventDTO }
     * 
     * 
     */
    public List<MatchEventDTO> getEvents() {
        if (events == null) {
            events = new ArrayList<MatchEventDTO>();
        }
        return this.events;
    }

    /**
     * Obtiene el valor de la propiedad id.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getId() {
        return id;
    }

    /**
     * Define el valor de la propiedad id.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setId(Integer value) {
        this.id = value;
    }

    /**
     * Obtiene el valor de la propiedad localGoals.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLocalGoals() {
        return localGoals;
    }

    /**
     * Define el valor de la propiedad localGoals.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLocalGoals(Integer value) {
        this.localGoals = value;
    }

    /**
     * Obtiene el valor de la propiedad localTeam.
     * 
     * @return
     *     possible object is
     *     {@link TeamDTO }
     *     
     */
    public TeamDTO getLocalTeam() {
        return localTeam;
    }

    /**
     * Define el valor de la propiedad localTeam.
     * 
     * @param value
     *     allowed object is
     *     {@link TeamDTO }
     *     
     */
    public void setLocalTeam(TeamDTO value) {
        this.localTeam = value;
    }

    /**
     * Obtiene el valor de la propiedad startDate.
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartDate() {
        return startDate;
    }

    /**
     * Define el valor de la propiedad startDate.
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartDate(XMLGregorianCalendar value) {
        this.startDate = value;
    }

}
