package entrenamiento;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jugadores.Jugador;
import partidos.Partido;
import configuracion.ConfiguracionControladorLocal;
import datatypes.EnumEntrenamiento;
import equipos.Equipo;

@Stateless
@LocalBean
public class ControladorEntrenamiento {
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@Inject
	private ConfiguracionControladorLocal controladorConf;
	
	
	private void entrenarEquipo(Equipo e){
		for (Jugador j : e.getPlantel()) {
			entrenarJugador(j, e.getTipoEntrenamiento());
		}	
	}
	
	private void entrenarJugador(Jugador j,EnumEntrenamiento tipoEntrenamiento){
		
		int valorEntrenamiento = controladorConf.getConfiguracion().valorEntrenamiento(j.getEdad());
		int valorNoEntrenar = controladorConf.getConfiguracion().valorNoEntrenamiento(j.getEdad());
		j.entrenar(tipoEntrenamiento, valorEntrenamiento,valorNoEntrenar);
		em.persist(em);
	}
	
	public void entrenarDespuesDePartido(Partido p){
		entrenarEquipo(p.getLocal());
		entrenarEquipo(p.getVisitante());
	}

}
