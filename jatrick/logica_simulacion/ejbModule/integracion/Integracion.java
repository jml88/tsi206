package integracion;

import java.util.Calendar;

import integracion_logica.AlineacionIntegracion;
import integracion_logica.EquipoIntegracion;
import integracion_logica.ResultadoIntegracionDto;
import interfazIntegracion.InterfazIntegracionSimulacion;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jugadores.Jugador;
import datatypes.DatosAlineacion;
import partidos.Partido;
import partidos.PartidoIntegracion;
import equipos.Alineacion;
import equipos.Equipo;
import excepciones.NoExisteEquipoExcepcion;

@Stateless
@Named("integracion")
public class Integracion implements InterfazIntegracionSimulacion {
	

	@Inject
	IntegracionConvert convert;
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@Inject
	LogicaSimulacionIntegracion simulacion;
	
	public Alineacion crearAlineacion(Equipo equipo) {
		Alineacion alineacion = new Alineacion();
		alineacion.setAlineacionDefecto(true);
		int num = 0;
		for (Jugador jugador : equipo.getPlantel()) {
			if (num == 0) {
				alineacion.setGolero(jugador);;
			} else if (num == 1 || num == 2 || num == 3 || num == 4) {
				alineacion.getDefensas().add(jugador);
			} else if (num == 5 || num == 6 || num == 7 || num == 8) {
				alineacion.getMediocampistas().add(jugador);
			} else if (num == 9 || num == 10) {
				alineacion.getDelanteros().add(jugador);
			}
			num++;
		}
		return alineacion;
	}
	
	public ResultadoIntegracionDto jugarPartido(int idEquipo, EquipoIntegracion equipo,  AlineacionIntegracion alineacion) throws NoExisteEquipoExcepcion{
		Equipo e = convert.equipoIntegracionToEquipo(equipo);
		Alineacion a = convert.alineacionIntegracionToAlineacion(alineacion);
		Equipo local = (Equipo)em.find(Equipo.class, idEquipo);
		if(local == null)
		{
			throw new NoExisteEquipoExcepcion("El equipo local no existe en la base de id: " + idEquipo);
		}
		
		Partido partido = new PartidoIntegracion(local, e, Calendar.getInstance());
		partido.setAlineacionVisitante(a);
		Alineacion alineacionLocal = crearAlineacion(local);
		if(e.getAlineacionDefecto()==null)
		{
			e.setAlineacionDefecto(alineacionLocal);
		}
		partido.setAlineacionLocal(e.getAlineacionDefecto());
		
		return simulacion.simularPartidoCompleto(partido);
		
	}
}
