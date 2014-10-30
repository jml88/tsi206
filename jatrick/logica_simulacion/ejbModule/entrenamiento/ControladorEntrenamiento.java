package entrenamiento;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import partido.PartidoControlador;
import partidos.Partido;
import configuracion.ConfiguracionControladorLocal;
import configuracionGral.ConfiguracionGral;
import datatypes.EnumEntrenamiento;
import equipos.Equipo;
import jugadores.Jugador;

public class ControladorEntrenamiento {
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@Inject
	private ConfiguracionControladorLocal controladorConf;
	
	@Inject
	private PartidoControlador pc;
	
	private void entrenarEquipo(Equipo e){
		for (Jugador j : e.getPlantel()) {
			entrenarJugador(j, e.getTipoEntrenamiento());
		}	
	}
	
	private void entrenarJugador(Jugador j,EnumEntrenamiento tipoEntrenamiento){
		
		int valorEntrenamiento = controladorConf.getConfiguracion().valorEntrenamiento(j.getEdad());
		j.entrenar(tipoEntrenamiento, valorEntrenamiento);
		em.persist(em);
	}
	
	public void entrenarDespuesDePartido(Partido p){
		entrenarEquipo(p.getLocal());
		entrenarEquipo(p.getVisitante());
	}

}
