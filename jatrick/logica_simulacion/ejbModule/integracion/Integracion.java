package integracion;

import integracion_logica.AlineacionIntegracion;
import integracion_logica.EquipoIntegracion;
import integracion_logica.ResultadoIntegracionDto;
import interfazIntegracion.InterfazIntegracionSimulacion;

import javax.ejb.Stateless;
import javax.inject.Named;

@Stateless
@Named("integracion")
public class Integracion implements InterfazIntegracionSimulacion {
	

	public ResultadoIntegracionDto jugarPartido(int idEquipo, EquipoIntegracion equipo,  AlineacionIntegracion alineacion){
		return null;
	}
}
