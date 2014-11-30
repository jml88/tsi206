package interfazIntegracion;

import javax.ejb.Local;
import javax.ejb.Remote;

import excepciones.NoExisteEquipoExcepcion;
import integracion_logica.AlineacionIntegracion;
import integracion_logica.EquipoIntegracion;
import integracion_logica.ResultadoIntegracionDto;

@Local
public interface InterfazIntegracionSimulacion {
	
	public ResultadoIntegracionDto jugarPartido(int idEquipo, EquipoIntegracion equipo,  AlineacionIntegracion alineacion) throws NoExisteEquipoExcepcion;

}
