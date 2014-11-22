
package otro;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Clase Java para equipoIntegracion complex type.
 * 
 * <p>El siguiente fragmento de esquema especifica el contenido que se espera que haya en esta clase.
 * 
 * <pre>
 * &lt;complexType name="equipoIntegracion">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="codigoEquipo" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="codigoEquipoIntegracion" type="{http://www.w3.org/2001/XMLSchema}int"/>
 *         &lt;element name="nombreEquipo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "equipoIntegracion", propOrder = {
    "codigoEquipo",
    "codigoEquipoIntegracion",
    "nombreEquipo"
})
public class EquipoIntegracion {

    protected int codigoEquipo;
    protected int codigoEquipoIntegracion;
    protected String nombreEquipo;

    /**
     * Obtiene el valor de la propiedad codigoEquipo.
     * 
     */
    public int getCodigoEquipo() {
        return codigoEquipo;
    }

    /**
     * Define el valor de la propiedad codigoEquipo.
     * 
     */
    public void setCodigoEquipo(int value) {
        this.codigoEquipo = value;
    }

    /**
     * Obtiene el valor de la propiedad codigoEquipoIntegracion.
     * 
     */
    public int getCodigoEquipoIntegracion() {
        return codigoEquipoIntegracion;
    }

    /**
     * Define el valor de la propiedad codigoEquipoIntegracion.
     * 
     */
    public void setCodigoEquipoIntegracion(int value) {
        this.codigoEquipoIntegracion = value;
    }

    /**
     * Obtiene el valor de la propiedad nombreEquipo.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNombreEquipo() {
        return nombreEquipo;
    }

    /**
     * Define el valor de la propiedad nombreEquipo.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNombreEquipo(String value) {
        this.nombreEquipo = value;
    }

}
