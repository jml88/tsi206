
package com.tsi201;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Clase Java para jRequestMatchDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="jRequestMatchDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="awayTeamDTO" type="{http://tsi201.com/}teamDTO" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="localTeamDTO" type="{http://tsi201.com/}teamDTO" minOccurs="0"/>
 *         &lt;element name="matchDTO" type="{http://tsi201.com/}matchDTO" minOccurs="0"/>
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
@XmlType(name = "jRequestMatchDTO", propOrder = {
    "awayTeamDTO",
    "id",
    "localTeamDTO",
    "matchDTO",
    "startDate"
})
public class JRequestMatchDTO {

    protected TeamDTO awayTeamDTO;
    protected Integer id;
    protected TeamDTO localTeamDTO;
    protected MatchDTO matchDTO;
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startDate;

    /**
     * Obtiene el valor de la propiedad awayTeamDTO.
     * 
     * @return
     *     possible object is
     *     {@link TeamDTO }
     *     
     */
    public TeamDTO getAwayTeamDTO() {
        return awayTeamDTO;
    }

    /**
     * Define el valor de la propiedad awayTeamDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link TeamDTO }
     *     
     */
    public void setAwayTeamDTO(TeamDTO value) {
        this.awayTeamDTO = value;
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
     * Obtiene el valor de la propiedad localTeamDTO.
     * 
     * @return
     *     possible object is
     *     {@link TeamDTO }
     *     
     */
    public TeamDTO getLocalTeamDTO() {
        return localTeamDTO;
    }

    /**
     * Define el valor de la propiedad localTeamDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link TeamDTO }
     *     
     */
    public void setLocalTeamDTO(TeamDTO value) {
        this.localTeamDTO = value;
    }

    /**
     * Obtiene el valor de la propiedad matchDTO.
     * 
     * @return
     *     possible object is
     *     {@link MatchDTO }
     *     
     */
    public MatchDTO getMatchDTO() {
        return matchDTO;
    }

    /**
     * Define el valor de la propiedad matchDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link MatchDTO }
     *     
     */
    public void setMatchDTO(MatchDTO value) {
        this.matchDTO = value;
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
