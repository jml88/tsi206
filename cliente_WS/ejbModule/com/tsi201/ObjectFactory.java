
package com.tsi201;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the com.tsi201 package. 
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

    private final static QName _PingResponse_QNAME = new QName("http://tsi201.com/", "pingResponse");
    private final static QName _GetTeams_QNAME = new QName("http://tsi201.com/", "getTeams");
    private final static QName _GetTeamsResponse_QNAME = new QName("http://tsi201.com/", "getTeamsResponse");
    private final static QName _Play_QNAME = new QName("http://tsi201.com/", "play");
    private final static QName _PlayResponse_QNAME = new QName("http://tsi201.com/", "playResponse");
    private final static QName _Ping_QNAME = new QName("http://tsi201.com/", "ping");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.tsi201
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetTeamsResponse }
     * 
     */
    public GetTeamsResponse createGetTeamsResponse() {
        return new GetTeamsResponse();
    }

    /**
     * Create an instance of {@link Play }
     * 
     */
    public Play createPlay() {
        return new Play();
    }

    /**
     * Create an instance of {@link PlayResponse }
     * 
     */
    public PlayResponse createPlayResponse() {
        return new PlayResponse();
    }

    /**
     * Create an instance of {@link Ping }
     * 
     */
    public Ping createPing() {
        return new Ping();
    }

    /**
     * Create an instance of {@link PingResponse }
     * 
     */
    public PingResponse createPingResponse() {
        return new PingResponse();
    }

    /**
     * Create an instance of {@link GetTeams }
     * 
     */
    public GetTeams createGetTeams() {
        return new GetTeams();
    }

    /**
     * Create an instance of {@link JRequestMatchDTO }
     * 
     */
    public JRequestMatchDTO createJRequestMatchDTO() {
        return new JRequestMatchDTO();
    }

    /**
     * Create an instance of {@link PlayerDTO }
     * 
     */
    public PlayerDTO createPlayerDTO() {
        return new PlayerDTO();
    }

    /**
     * Create an instance of {@link MatchEventDTO }
     * 
     */
    public MatchEventDTO createMatchEventDTO() {
        return new MatchEventDTO();
    }

    /**
     * Create an instance of {@link TeamDTO }
     * 
     */
    public TeamDTO createTeamDTO() {
        return new TeamDTO();
    }

    /**
     * Create an instance of {@link MatchDTO }
     * 
     */
    public MatchDTO createMatchDTO() {
        return new MatchDTO();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PingResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tsi201.com/", name = "pingResponse")
    public JAXBElement<PingResponse> createPingResponse(PingResponse value) {
        return new JAXBElement<PingResponse>(_PingResponse_QNAME, PingResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTeams }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tsi201.com/", name = "getTeams")
    public JAXBElement<GetTeams> createGetTeams(GetTeams value) {
        return new JAXBElement<GetTeams>(_GetTeams_QNAME, GetTeams.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetTeamsResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tsi201.com/", name = "getTeamsResponse")
    public JAXBElement<GetTeamsResponse> createGetTeamsResponse(GetTeamsResponse value) {
        return new JAXBElement<GetTeamsResponse>(_GetTeamsResponse_QNAME, GetTeamsResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Play }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tsi201.com/", name = "play")
    public JAXBElement<Play> createPlay(Play value) {
        return new JAXBElement<Play>(_Play_QNAME, Play.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link PlayResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tsi201.com/", name = "playResponse")
    public JAXBElement<PlayResponse> createPlayResponse(PlayResponse value) {
        return new JAXBElement<PlayResponse>(_PlayResponse_QNAME, PlayResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Ping }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://tsi201.com/", name = "ping")
    public JAXBElement<Ping> createPing(Ping value) {
        return new JAXBElement<Ping>(_Ping_QNAME, Ping.class, null, value);
    }

}
