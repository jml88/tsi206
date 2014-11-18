package partido;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jugadores.Jugador;

import org.jboss.ejb3.annotation.TransactionTimeout;

import partidos.Comentario;
import partidos.ConfiguracionPartido;
import partidos.Partido;
import partidos.PartidoTorneo;
import partidos.ResultadoPartido;
import campeonato.Posicion;
import datatypes.DatosAlineacion;
import datatypes.DatosJugador;
import datatypes.EnumPartido;
import equipos.Alineacion;
import equipos.Equipo;
import finanzas.FinanzasControladorSimulacion;

@Stateless
@LocalBean
public class PartidoControlador {

	@PersistenceContext(unitName = "jatrik")
	private EntityManager em;
	
	@Inject
	private FinanzasControladorSimulacion fc;
	
	public PartidoControlador() {

	}



	// Debe devolver todos los partidos que se juegan el mismo dia de c


	public void partidoEnJuego(Partido p) {
		p.setEstado(EnumPartido.JUGANDO);
		em.merge(p);
	}



	// public void crearPartido(){
	// Partido p = new Partido();
	// em.persist(p);
	// }

	public void crearComentario(String mensaje, Partido partido, int minuto) {

		Query q = em.createQuery("select max(c.Id) from Comentario c");
		Comentario c = new Comentario();
		c.setMensaje(mensaje);
		c.setPartido(partido);
		c.setMinuto(minuto);
		em.persist(c);
	}

	public ConfiguracionPartido findConfiguracionPartido() {
		return (ConfiguracionPartido) em.createQuery(
				"select c from ConfiguracionPartido c").getSingleResult();
	}

	public boolean tieneConfiguracionPartido() {
		return em.createQuery("select c from ConfiguracionPartido c")
				.getResultList().size() != 0;
	}

	public void crearConfiguracionPartido(int cantidadJugadas, int duracion) {
		ConfiguracionPartido cp = new ConfiguracionPartido();
		cp.setCantidadJugadas(cantidadJugadas);
		cp.setDuracion(duracion);
		em.persist(cp);
	}

	public void partidoPorSimular(Partido p) {
		p.setEstado(EnumPartido.POR_SIMULAR);
		em.merge(p);

	}


	// private void equipoAsciende(Equipo e, Torneo t) {
	// t.getAsciende().getEquipos().add(e);
	//
	// }



	



//	public PartidoTorneo findPartidoTorneo(int codigo) {
//		return em.find(PartidoTorneo.class, codigo);
//
//	}



	public void sumarGolLocal(ResultadoPartido rp, Jugador jL) {
		rp.agregarGolLocal();
		jL.setGolesCarrera(jL.getGolesCarrera() + 1);
		jL.setGolesLiga(jL.getGolesLiga() + 1);
		if (!rp.getGoleadoresLocal().contains(jL)) {
			rp.getGoleadoresLocal().add(jL);
		}
		em.merge(rp);
	}

	public void sumarGolVisitante(ResultadoPartido rp, Jugador jV) {
		rp.agregarGolVisitante();
		jV.setGolesCarrera(jV.getGolesCarrera() + 1);
		jV.setGolesLiga(jV.getGolesLiga() + 1);
		if (!rp.getGoleadoresVisitante().contains(jV)) {
			rp.getGoleadoresVisitante().add(jV);
		}
		em.merge(rp);
	}


	public void lesionJugador(Jugador j){
		//TODO hacerlo bien
		j.setLesion(2);
	}
	
	public void tarjetaJugador(Jugador j){
		
	}


	// public Partido crearPartidoPrueba(){
	// Partido p = new Partido();
	// em.persist(p);
	// return p;
	// }

}
