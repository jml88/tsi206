package api;

import integracion_logica.AlineacionIntegracion;
import integracion_logica.EquipoIntegracion;
import integracion_logica.ResultadoIntegracionDto;

import java.util.LinkedList;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.naming.NamingException;

import comunicacion.Comunicacion;

import excepciones.NoExisteEquipoExcepcion;

@WebService
public class IntegracionServicios {
	
	@WebMethod
	public LinkedList<EquipoIntegracion> listarEquipos(){
		try {
			
			return new LinkedList<EquipoIntegracion>(Comunicacion.getInstance().getIntegracionLogica().listarEquipos());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	@WebMethod
	public ResultadoIntegracionDto jugarPartido(int idEquipo, EquipoIntegracion equipo, AlineacionIntegracion alineacion) throws NoExisteEquipoExcepcion{
		try {
			ResultadoIntegracionDto res = Comunicacion.getInstance().getIntegracionSimulacion().jugarPartido(idEquipo, equipo, alineacion);
			return res;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}

}
