
package otro;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the otro package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _JugarPartidoResponse_QNAME = new QName("http://api/", "jugarPartidoResponse");
    private final static QName _ListarEquiposResponse_QNAME = new QName("http://api/", "listarEquiposResponse");
    private final static QName _ListarEquipos_QNAME = new QName("http://api/", "listarEquipos");
    private final static QName _JugarPartido_QNAME = new QName("http://api/", "jugarPartido");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: otro
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link JugarPartidoResponse }
     * 
     */
    public JugarPartidoResponse createJugarPartidoResponse() {
        return new JugarPartidoResponse();
    }

    /**
     * Create an instance of {@link ListarEquiposResponse }
     * 
     */
    public ListarEquiposResponse createListarEquiposResponse() {
        return new ListarEquiposResponse();
    }

    /**
     * Create an instance of {@link ListarEquipos }
     * 
     */
    public ListarEquipos createListarEquipos() {
        return new ListarEquipos();
    }

    /**
     * Create an instance of {@link JugarPartido }
     * 
     */
    public JugarPartido createJugarPartido() {
        return new JugarPartido();
    }

    /**
     * Create an instance of {@link ResultadoIntegracionDto }
     * 
     */
    public ResultadoIntegracionDto createResultadoIntegracionDto() {
        return new ResultadoIntegracionDto();
    }

    /**
     * Create an instance of {@link EquipoIntegracion }
     * 
     */
    public EquipoIntegracion createEquipoIntegracion() {
        return new EquipoIntegracion();
    }

    /**
     * Create an instance of {@link ComentarioIntegracion }
     * 
     */
    public ComentarioIntegracion createComentarioIntegracion() {
        return new ComentarioIntegracion();
    }

    /**
     * Create an instance of {@link JugadorIntegracion }
     * 
     */
    public JugadorIntegracion createJugadorIntegracion() {
        return new JugadorIntegracion();
    }

    /**
     * Create an instance of {@link AlineacionIntegracion }
     * 
     */
    public AlineacionIntegracion createAlineacionIntegracion() {
        return new AlineacionIntegracion();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JugarPartidoResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api/", name = "jugarPartidoResponse")
    public JAXBElement<JugarPartidoResponse> createJugarPartidoResponse(JugarPartidoResponse value) {
        return new JAXBElement<JugarPartidoResponse>(_JugarPartidoResponse_QNAME, JugarPartidoResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEquiposResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api/", name = "listarEquiposResponse")
    public JAXBElement<ListarEquiposResponse> createListarEquiposResponse(ListarEquiposResponse value) {
        return new JAXBElement<ListarEquiposResponse>(_ListarEquiposResponse_QNAME, ListarEquiposResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ListarEquipos }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api/", name = "listarEquipos")
    public JAXBElement<ListarEquipos> createListarEquipos(ListarEquipos value) {
        return new JAXBElement<ListarEquipos>(_ListarEquipos_QNAME, ListarEquipos.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link JugarPartido }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://api/", name = "jugarPartido")
    public JAXBElement<JugarPartido> createJugarPartido(JugarPartido value) {
        return new JAXBElement<JugarPartido>(_JugarPartido_QNAME, JugarPartido.class, null, value);
    }

}
