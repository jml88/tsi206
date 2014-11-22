
package otro;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para resultadoIntegracionDto complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="resultadoIntegracionDto">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="comentarios" type="{http://api/}comentarioIntegracion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="goleadoresLocal" type="{http://api/}jugadorIntegracion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="goleadoresVisitante" type="{http://api/}jugadorIntegracion" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="golesLocal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="golesVisitante" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="penalesLocal" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="penalesVisitante" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "resultadoIntegracionDto", propOrder = {
    "comentarios",
    "goleadoresLocal",
    "goleadoresVisitante",
    "golesLocal",
    "golesVisitante",
    "penalesLocal",
    "penalesVisitante"
})
public class ResultadoIntegracionDto {

    @XmlElement(nillable = true)
    protected List<ComentarioIntegracion> comentarios;
    @XmlElement(nillable = true)
    protected List<JugadorIntegracion> goleadoresLocal;
    @XmlElement(nillable = true)
    protected List<JugadorIntegracion> goleadoresVisitante;
    protected int golesLocal;
    protected int golesVisitante;
    protected int penalesLocal;
    protected int penalesVisitante;

    /**
     * Gets the value of the comentarios property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the comentarios property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getComentarios().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link ComentarioIntegracion }
     * 
     * 
     */
    public List<ComentarioIntegracion> getComentarios() {
        if (comentarios == null) {
            comentarios = new ArrayList<ComentarioIntegracion>();
        }
        return this.comentarios;
    }

    /**
     * Gets the value of the goleadoresLocal property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the goleadoresLocal property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGoleadoresLocal().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JugadorIntegracion }
     * 
     * 
     */
    public List<JugadorIntegracion> getGoleadoresLocal() {
        if (goleadoresLocal == null) {
            goleadoresLocal = new ArrayList<JugadorIntegracion>();
        }
        return this.goleadoresLocal;
    }

    /**
     * Gets the value of the goleadoresVisitante property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the goleadoresVisitante property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getGoleadoresVisitante().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link JugadorIntegracion }
     * 
     * 
     */
    public List<JugadorIntegracion> getGoleadoresVisitante() {
        if (goleadoresVisitante == null) {
            goleadoresVisitante = new ArrayList<JugadorIntegracion>();
        }
        return this.goleadoresVisitante;
    }

    /**
     * Obtiene el valor de la propiedad golesLocal.
     * 
     */
    public int getGolesLocal() {
        return golesLocal;
    }

    /**
     * Define el valor de la propiedad golesLocal.
     * 
     */
    public void setGolesLocal(int value) {
        this.golesLocal = value;
    }

    /**
     * Obtiene el valor de la propiedad golesVisitante.
     * 
     */
    public int getGolesVisitante() {
        return golesVisitante;
    }

    /**
     * Define el valor de la propiedad golesVisitante.
     * 
     */
    public void setGolesVisitante(int value) {
        this.golesVisitante = value;
    }

    /**
     * Obtiene el valor de la propiedad penalesLocal.
     * 
     */
    public int getPenalesLocal() {
        return penalesLocal;
    }

    /**
     * Define el valor de la propiedad penalesLocal.
     * 
     */
    public void setPenalesLocal(int value) {
        this.penalesLocal = value;
    }

    /**
     * Obtiene el valor de la propiedad penalesVisitante.
     * 
     */
    public int getPenalesVisitante() {
        return penalesVisitante;
    }

    /**
     * Define el valor de la propiedad penalesVisitante.
     * 
     */
    public void setPenalesVisitante(int value) {
        this.penalesVisitante = value;
    }

}
