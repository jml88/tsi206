package clienteWs;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.7.11
 * 2014-11-30T20:52:01.918-02:00
 * Generated source version: 2.7.11
 * 
 */
@WebService(targetNamespace = "http://tsi201.com/", name = "integracion")
@XmlSeeAlso({ObjectFactory.class})
public interface Integracion {

    @WebMethod
    @RequestWrapper(localName = "play", targetNamespace = "http://tsi201.com/", className = "clienteWs.Play")
    @ResponseWrapper(localName = "playResponse", targetNamespace = "http://tsi201.com/", className = "clienteWs.PlayResponse")
    @WebResult(name = "return", targetNamespace = "")
    public clienteWs.JRequestMatchDTO play(
        @WebParam(name = "arg0", targetNamespace = "")
        clienteWs.TeamDTO arg0,
        @WebParam(name = "arg1", targetNamespace = "")
        java.lang.Integer arg1
    );

    @WebMethod
    @RequestWrapper(localName = "ping", targetNamespace = "http://tsi201.com/", className = "clienteWs.Ping")
    @ResponseWrapper(localName = "pingResponse", targetNamespace = "http://tsi201.com/", className = "clienteWs.PingResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.lang.String ping();

    @WebMethod
    @RequestWrapper(localName = "getTeams", targetNamespace = "http://tsi201.com/", className = "clienteWs.GetTeams")
    @ResponseWrapper(localName = "getTeamsResponse", targetNamespace = "http://tsi201.com/", className = "clienteWs.GetTeamsResponse")
    @WebResult(name = "return", targetNamespace = "")
    public java.util.List<clienteWs.TeamDTO> getTeams();
}
