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
import datatypes.DatosMinutoPartido;
import datatypes.EnumPartido;
import equipos.Alineacion;
import equipos.Equipo;
import excepciones.NoExisteEquipoExcepcion;
import finanzas.FinanzasControladorSimulacion;

@Stateless
@LocalBean
public class PartidoControlador {

	@PersistenceContext(unitName = "jatrik")
	private EntityManager em;

//	@Inject
//	private FinanzasControladorSimulacion fc;
	
	@Inject
	private SimulacionControlador sc;

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

		Comentario c = new Comentario();
		c.setMensaje(mensaje);
		c.setPartido(partido);
		c.setMinuto(minuto);
		em.persist(c);
	}

	public void crearComentarioAmarilla(Jugador jFalta, Jugador jFauleado,
			Partido partido, int minuto) {

		Comentario c = new Comentario();
		c.setMensaje("Entrada a destiempo de "
				+ jFalta.getNombre()
				+ " "
				+ jFalta.getApellido1()
				+ " a "
				+ jFauleado.getNombre()
				+ " "
				+ jFauleado.getApellido1()
				+ " y... ¡Falta!\nEl árbitro consulta con su auxiliar y... Tarjeta amarilla para "
				+ jFalta.getNombre() + " " + jFalta.getNombre());
		c.setPartido(partido);
		c.setMinuto(minuto);
		em.persist(c);
	}

	public void crearComentarioRoja(Jugador jFalta, Jugador jFauleado,
			Partido partido, int minuto) {

		Comentario c = new Comentario();
		c.setMensaje("Entrada a destiempo de "
				+ jFalta.getNombre()
				+ " "
				+ jFalta.getApellido1()
				+ " a "
				+ jFauleado.getNombre()
				+ " "
				+ jFauleado.getApellido1()
				+ " y... ¡Falta!\n El árbitro consulta con su auxiliar y... Tarjeta roja para "
				+ jFalta.getNombre() + " " + jFalta.getApellido1());
		c.setPartido(partido);
		c.setMinuto(minuto);
		em.persist(c);
	}

	public void crearComentarioFalta(Jugador jFalta, Jugador jFauleado,
			Partido partido, int minuto) {

		Comentario c = new Comentario();
		c.setMensaje("Entrada a destiempo de "
				+ jFalta.getNombre()
				+ " "
				+ jFalta.getApellido1()
				+ " a "
				+ jFauleado.getNombre()
				+ " "
				+ jFauleado.getApellido1()
				+ " y... ¡Falta!\n El árbitro consulta con su auxiliar y... no hay tarjeta");
		c.setPartido(partido);
		c.setMinuto(minuto);
		em.persist(c);
	}

	public void crearComentarioGol(int tipoGol, Jugador jGol, Jugador jgolero,
			Equipo egol, Partido partido, int minuto) {

		Comentario c = new Comentario();
		String mensaje = "";
		if (tipoGol == 1) {
			mensaje = "Tiro Libre " + egol.getNombre()
					+ "  Chuta y... GOOOOOLLLLL!!";
		} else if (tipoGol == 2) {
			mensaje = "El juez pita para patear el penal " + egol.getNombre()
					+ "  Chuta y... GOOOOOLLLLL!!	";
		} else {
			mensaje = "Pared al borde del área para " + egol.getNombre()
					+ "  Chuta y... GOOOOOLLLLL!!	";
		}

		c.setMensaje(mensaje);
		c.setPartido(partido);
		c.setMinuto(minuto);
		em.persist(c);
	}

	public void crearComentarioJugadaGol(int tipoJGol, Jugador jGol,
			Jugador jgolero, Equipo egol, Partido partido, int minuto) {

		Comentario c = new Comentario();
		String mensaje = "";
		if (tipoJGol == 1) {
			mensaje = "Tiro Libre " + egol.getNombre()
					+ "  Chuta y... rebota en la barrera!!";
		} else if (tipoJGol == 2) {
			mensaje = "El juez pita para patear el penal " + egol.getNombre()
					+ "  Chuta y... paradon del golero!!	";
		} else {
			double jugada = Math.random();
			if (jugada > 0.5) {
				mensaje = "¡¡¡ "
						+ egol.getNombre()
						+ " recupera el balón al borde del área rival creando peligro !!!\n¡"
						+ jgolero.getNombre()
						+ " "
						+ jgolero.getApellido1()
						+ " bloquea el balón en dos tiempos con algun problema !";
			} else {
				mensaje = jGol.getNombre()
						+ " "
						+ jGol.getApellido1()
						+ " se escapa por la punta y levanta el centro\n directo a las gradas, se ve que es de las escuela del futbol uruguayo ";
			}

		}

		c.setMensaje(mensaje);
		c.setPartido(partido);
		c.setMinuto(minuto);
		em.persist(c);
	}

//	public void crearComentarioLesion(Jugador j, Partido partido, int minuto) {
//
//
//		Comentario c = new Comentario();
//		c.setMensaje(j.getNombre()
//				+ " "
//				+ j.getApellido1()
//				+ " se duele y sale cojeando del terreno de juego en una jugada");
//		c.setPartido(partido);
//		c.setMinuto(minuto);
//		em.persist(c);
//	}

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

	// public PartidoTorneo findPartidoTorneo(int codigo) {
	// return em.find(PartidoTorneo.class, codigo);
	//
	// }

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

//	public void lesionJugador(Jugador j) {
//		// TODO hacerlo bien
//		j.setLesion(2);
//	}

	public void tarjetaJugador(Jugador j) {

	}

	public Jugador cambioPorLesion(int linea, Jugador ju, Alineacion a) {
		Jugador retorno = null;
		if (linea == 3){
			if (a.getLesionDelantero() != null){
				for (Jugador j : a.getDelanteros()) {
					if (j.getCodigo() == ju.getCodigo()){
						a.getDelanteros().remove(ju);
						a.getDelanteros().add(a.getLesionDelantero());
						retorno = a.getLesionDelantero();
						a.setLesionDelantero(null);
						break;
					}
				}
			}
			
		}
		else if (linea == 2){
			if (a.getLesionMediocampistas() != null){
				for (Jugador j : a.getMediocampistas()) {
					if (j.getCodigo() == ju.getCodigo()){
						a.getMediocampistas().remove(ju);
						a.getMediocampistas().add(a.getLesionMediocampistas());
						retorno = a.getLesionMediocampistas();
						a.setLesionMediocampistas(null);
						break;
					}
				}
			}
		}
		else{
			if (a.getLesionDefensas() != null){
				for (Jugador j : a.getDefensas()) {
					if (j.getCodigo() == ju.getCodigo()){
						a.getDefensas().remove(ju);
						a.getDefensas().add(a.getLesionDefensas());
						retorno = a.getLesionDefensas();
						a.setLesionDefensas(null);
						break;
					}
				}
			}
		}
		return retorno;
	}

	public void crearComentarioCambioLesion(Jugador juLesionado, Jugador jEntra, Partido p, int minuto) {
		Comentario c = new Comentario();
		if (jEntra == null){
		c.setMensaje(juLesionado.getNombre()
				+ " "
				+ juLesionado.getApellido1()
				+ " se duele y sale cojeando del terreno de juego en una jugada");
		}
		else{
			c.setMensaje(juLesionado.getNombre()
					+ " "
					+ juLesionado.getApellido1()
					+ " se duele y sale cojeando del terreno de juego en una jugada\n El tecnico envia a "+jEntra.getNombre()+" "+jEntra.getApellido1()+" en su lugar");
		}
		c.setPartido(p);
		c.setMinuto(minuto);
		em.persist(c);
		
	}

	// public Partido crearPartidoPrueba(){
	// Partido p = new Partido();
	// em.persist(p);
	// return p;
	// }
	
	

}
