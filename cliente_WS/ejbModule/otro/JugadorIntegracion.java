
package otro;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para jugadorIntegracion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="jugadorIntegracion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="apellido1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="apellido2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="ataque" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="defensa" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="edad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="equipo" type="{http://api/}equipoIntegracion" minOccurs="0"/>
 *         &lt;element name="lesion" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="nombre" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *         &lt;element name="porteria" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="tarjetasPartido" type="{http://www.w3.org/2001/XMLSchema}int" minOccurs="0"/>
 *         &lt;element name="tecnica" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="velocidad" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "jugadorIntegracion", propOrder = {
    "apellido1",
    "apellido2",
    "ataque",
    "defensa",
    "edad",
    "equipo",
    "lesion",
    "nombre",
    "porteria",
    "tarjetasPartido",
    "tecnica",
    "velocidad"
})
public class JugadorIntegracion {

    protected String apellido1;
    protected String apellido2;
    protected int ataque;
    protected int defensa;
    protected int edad;
    protected EquipoIntegracion equipo;
    protected Integer lesion;
    protected String nombre;
    protected int porteria;
    protected Integer tarjetasPartido;
    protected int tecnica;
    protected int velocidad;

    /**
     * Obtiene el valor de la propiedad apellido1.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido1() {
        return apellido1;
    }

    /**
     * Define el valor de la propiedad apellido1.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido1(String value) {
        this.apellido1 = value;
    }

    /**
     * Obtiene el valor de la propiedad apellido2.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getApellido2() {
        return apellido2;
    }

    /**
     * Define el valor de la propiedad apellido2.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setApellido2(String value) {
        this.apellido2 = value;
    }

    /**
     * Obtiene el valor de la propiedad ataque.
     * 
     */
    public int getAtaque() {
        return ataque;
    }

    /**
     * Define el valor de la propiedad ataque.
     * 
     */
    public void setAtaque(int value) {
        this.ataque = value;
    }

    /**
     * Obtiene el valor de la propiedad defensa.
     * 
     */
    public int getDefensa() {
        return defensa;
    }

    /**
     * Define el valor de la propiedad defensa.
     * 
     */
    public void setDefensa(int value) {
        this.defensa = value;
    }

    /**
     * Obtiene el valor de la propiedad edad.
     * 
     */
    public int getEdad() {
        return edad;
    }

    /**
     * Define el valor de la propiedad edad.
     * 
     */
    public void setEdad(int value) {
        this.edad = value;
    }

    /**
     * Obtiene el valor de la propiedad equipo.
     * 
     * @return
     *     possible object is
     *     {@link EquipoIntegracion }
     *     
     */
    public EquipoIntegracion getEquipo() {
        return equipo;
    }

    /**
     * Define el valor de la propiedad equipo.
     * 
     * @param value
     *     allowed object is
     *     {@link EquipoIntegracion }
     *     
     */
    public void setEquipo(EquipoIntegracion value) {
        this.equipo = value;
    }

    /**
     * Obtiene el valor de la propiedad lesion.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getLesion() {
        return lesion;
    }

    /**
     * Define el valor de la propiedad lesion.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setLesion(Integer value) {
        this.lesion = value;
    }

    /**
     * Obtiene el valor de la propiedad nombre.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Define el valor de la propiedad nombre.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombre(String value) {
        this.nombre = value;
    }

    /**
     * Obtiene el valor de la propiedad porteria.
     * 
     */
    public int getPorteria() {
        return porteria;
    }

    /**
     * Define el valor de la propiedad porteria.
     * 
     */
    public void setPorteria(int value) {
        this.porteria = value;
    }

    /**
     * Obtiene el valor de la propiedad tarjetasPartido.
     * 
     * @return
     *     possible object is
     *     {@link Integer }
     *     
     */
    public Integer getTarjetasPartido() {
        return tarjetasPartido;
    }

    /**
     * Define el valor de la propiedad tarjetasPartido.
     * 
     * @param value
     *     allowed object is
     *     {@link Integer }
     *     
     */
    public void setTarjetasPartido(Integer value) {
        this.tarjetasPartido = value;
    }

    /**
     * Obtiene el valor de la propiedad tecnica.
     * 
     */
    public int getTecnica() {
        return tecnica;
    }

    /**
     * Define el valor de la propiedad tecnica.
     * 
     */
    public void setTecnica(int value) {
        this.tecnica = value;
    }

    /**
     * Obtiene el valor de la propiedad velocidad.
     * 
     */
    public int getVelocidad() {
        return velocidad;
    }

    /**
     * Define el valor de la propiedad velocidad.
     * 
     */
    public void setVelocidad(int value) {
        this.velocidad = value;
    }

}
