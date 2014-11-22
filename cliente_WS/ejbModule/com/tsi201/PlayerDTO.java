
package com.tsi201;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para playerDTO complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="playerDTO">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="attack" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="defense" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="goalkeeping" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="name" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="positionDTO" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="sinceDTO" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="speed" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *         &lt;element name="technique" type="{http://www.w3.org/2001/XMLSchema}double" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "playerDTO", propOrder = {
    "attack",
    "defense",
    "goalkeeping",
    "id",
    "name",
    "positionDTO",
    "sinceDTO",
    "speed",
    "technique"
})
public class PlayerDTO {

    protected Double attack;
    protected Double defense;
    protected Double goalkeeping;
    protected Integer id;
    protected String name;
    protected Integer positionDTO;
    protected Integer sinceDTO;
    protected Double speed;
    protected Double technique;

    /**
     * Obtiene el valor de la propiedad attack.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getAttack() {
        return attack;
    }

    /**
     * Define el valor de la propiedad attack.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setAttack(Double value) {
        this.attack = value;
    }

    /**
     * Obtiene el valor de la propiedad defense.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getDefense() {
        return defense;
    }

    /**
     * Define el valor de la propiedad defense.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setDefense(Double value) {
        this.defense = value;
    }

    /**
     * Obtiene el valor de la propiedad goalkeeping.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getGoalkeeping() {
        return goalkeeping;
    }

    /**
     * Define el valor de la propiedad goalkeeping.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setGoalkeeping(Double value) {
        this.goalkeeping = value;
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
     * Obtiene el valor de la propiedad name.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Define el valor de la propiedad name.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Obtiene el valor de la propiedad positionDTO.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getPositionDTO() {
        return positionDTO;
    }

    /**
     * Define el valor de la propiedad positionDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setPositionDTO(Integer value) {
        this.positionDTO = value;
    }

    /**
     * Obtiene el valor de la propiedad sinceDTO.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getSinceDTO() {
        return sinceDTO;
    }

    /**
     * Define el valor de la propiedad sinceDTO.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setSinceDTO(Integer value) {
        this.sinceDTO = value;
    }

    /**
     * Obtiene el valor de la propiedad speed.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getSpeed() {
        return speed;
    }

    /**
     * Define el valor de la propiedad speed.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setSpeed(Double value) {
        this.speed = value;
    }

    /**
     * Obtiene el valor de la propiedad technique.
     * 
     * @return
     *     possible object is
     *     {@link Double }
     *     
     */
    public Double getTechnique() {
        return technique;
    }

    /**
     * Define el valor de la propiedad technique.
     * 
     * @param value
     *     allowed object is
     *     {@link Double }
     *     
     */
    public void setTechnique(Double value) {
        this.technique = value;
    }

}
