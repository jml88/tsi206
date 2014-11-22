
package otro;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para alineacionIntegracion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="alineacionIntegracion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="defensas" type="{http://api/}jugadorIntegracion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="delanteros" type="{http://api/}jugadorIntegracion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="lesionDefensas" type="{http://api/}jugadorIntegracion" minOccurs="0"/>
 *         &lt;element name="lesionDelantero" type="{http://api/}jugadorIntegracion" minOccurs="0"/>
 *         &lt;element name="lesionGolero" type="{http://api/}jugadorIntegracion" minOccurs="0"/>
 *         &lt;element name="lesionMediocampistas" type="{http://api/}jugadorIntegracion" minOccurs="0"/>
 *         &lt;element name="mediocampistas" type="{http://api/}jugadorIntegracion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="suplentes" type="{http://api/}jugadorIntegracion" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "alineacionIntegracion", propOrder = {
    "defensas",
    "delanteros",
    "lesionDefensas",
    "lesionDelantero",
    "lesionGolero",
    "lesionMediocampistas",
    "mediocampistas",
    "suplentes"
})
public class AlineacionIntegracion {

    @XmlElement(nillable = true)
    protected List<JugadorIntegracion> defensas;
    @XmlElement(nillable = true)
    protected List<JugadorIntegracion> delanteros;
    protected JugadorIntegracion lesionDefensas;
    protected JugadorIntegracion lesionDelantero;
    protected JugadorIntegracion lesionGolero;
    protected JugadorIntegracion lesionMediocampistas;
    @XmlElement(nillable = true)
    protected List<JugadorIntegracion> mediocampistas;
    @XmlElement(nillable = true)
    protected List<JugadorIntegracion> suplentes;

    /**
     * Gets the value of the defensas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the defensas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDefensas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JugadorIntegracion }
     * 
     * 
     */
    public List<JugadorIntegracion> getDefensas() {
        if (defensas == null) {
            defensas = new ArrayList<JugadorIntegracion>();
        }
        return this.defensas;
    }

    /**
     * Gets the value of the delanteros property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the delanteros property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDelanteros().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JugadorIntegracion }
     * 
     * 
     */
    public List<JugadorIntegracion> getDelanteros() {
        if (delanteros == null) {
            delanteros = new ArrayList<JugadorIntegracion>();
        }
        return this.delanteros;
    }

    /**
     * Obtiene el valor de la propiedad lesionDefensas.
     * 
     * @return
     *     possible object is
     *     {@link JugadorIntegracion }
     *     
     */
    public JugadorIntegracion getLesionDefensas() {
        return lesionDefensas;
    }

    /**
     * Define el valor de la propiedad lesionDefensas.
     * 
     * @param value
     *     allowed object is
     *     {@link JugadorIntegracion }
     *     
     */
    public void setLesionDefensas(JugadorIntegracion value) {
        this.lesionDefensas = value;
    }

    /**
     * Obtiene el valor de la propiedad lesionDelantero.
     * 
     * @return
     *     possible object is
     *     {@link JugadorIntegracion }
     *     
     */
    public JugadorIntegracion getLesionDelantero() {
        return lesionDelantero;
    }

    /**
     * Define el valor de la propiedad lesionDelantero.
     * 
     * @param value
     *     allowed object is
     *     {@link JugadorIntegracion }
     *     
     */
    public void setLesionDelantero(JugadorIntegracion value) {
        this.lesionDelantero = value;
    }

    /**
     * Obtiene el valor de la propiedad lesionGolero.
     * 
     * @return
     *     possible object is
     *     {@link JugadorIntegracion }
     *     
     */
    public JugadorIntegracion getLesionGolero() {
        return lesionGolero;
    }

    /**
     * Define el valor de la propiedad lesionGolero.
     * 
     * @param value
     *     allowed object is
     *     {@link JugadorIntegracion }
     *     
     */
    public void setLesionGolero(JugadorIntegracion value) {
        this.lesionGolero = value;
    }

    /**
     * Obtiene el valor de la propiedad lesionMediocampistas.
     * 
     * @return
     *     possible object is
     *     {@link JugadorIntegracion }
     *     
     */
    public JugadorIntegracion getLesionMediocampistas() {
        return lesionMediocampistas;
    }

    /**
     * Define el valor de la propiedad lesionMediocampistas.
     * 
     * @param value
     *     allowed object is
     *     {@link JugadorIntegracion }
     *     
     */
    public void setLesionMediocampistas(JugadorIntegracion value) {
        this.lesionMediocampistas = value;
    }

    /**
     * Gets the value of the mediocampistas property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the mediocampistas property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getMediocampistas().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JugadorIntegracion }
     * 
     * 
     */
    public List<JugadorIntegracion> getMediocampistas() {
        if (mediocampistas == null) {
            mediocampistas = new ArrayList<JugadorIntegracion>();
        }
        return this.mediocampistas;
    }

    /**
     * Gets the value of the suplentes property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the suplentes property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getSuplentes().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JugadorIntegracion }
     * 
     * 
     */
    public List<JugadorIntegracion> getSuplentes() {
        if (suplentes == null) {
            suplentes = new ArrayList<JugadorIntegracion>();
        }
        return this.suplentes;
    }

}
