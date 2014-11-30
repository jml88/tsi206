package integracion;

import integracion_logica.JugadorIntegracion;
import integracion_logica.ResultadoIntegracionDto;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import datatypes.EnumPartido;
import jugadores.Jugador;
import partidos.Comentario;
import partidos.ConfiguracionPartido;
import partidos.Partido;
import equipos.Equipo;

@Stateless
@LocalBean
public class PartidoControladorIntegracion {
	
	
	@PersistenceContext(unitName = "jatrik")
	private EntityManager em;


	// public void crearPartido(){
	// Partido p = new Partido();
	// em.persist(p);
	// }

	public Comentario crearComentario(String mensaje, Partido partido, int minuto) {

		Comentario c = new Comentario();
		c.setMensaje(mensaje);
		c.setPartido(partido);
		c.setMinuto(minuto);
		return c;
	}

	public Comentario crearComentarioAmarilla(Jugador jFalta, Jugador jFauleado,
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
		return c;
	}

	public Comentario crearComentarioRoja(Jugador jFalta, Jugador jFauleado,
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
		return c;
	}

	public Comentario crearComentarioFalta(Jugador jFalta, Jugador jFauleado,
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
		return c;
	}

	public Comentario crearComentarioGol(int tipoGol, Jugador jGol, Jugador jgolero,
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
		return c;
	}

	public Comentario crearComentarioJugadaGol(int tipoJGol, Jugador jGol,
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
		return c;
	}

	public Comentario crearComentarioLesion(Jugador j, Partido partido, int minuto) {

		//Query q = em.createQuery("select max(c.Id) from Comentario c");
		Comentario c = new Comentario();
		c.setMensaje(j.getNombre()
				+ " "
				+ j.getApellido1()
				+ " se duele y sale cojeando del terreno de juego en una jugada");
		c.setPartido(partido);
		c.setMinuto(minuto);
		return c;
	}

	



	// private void equipoAsciende(Equipo e, Torneo t) {
	// t.getAsciende().getEquipos().add(e);
	//
	// }

	// public PartidoTorneo findPartidoTorneo(int codigo) {
	// return em.find(PartidoTorneo.class, codigo);
	//
	// }

	public void sumarGolLocal(ResultadoIntegracionDto rp, JugadorIntegracion jL) {
		rp.agregarGolLocal();
		if (!rp.getGoleadoresLocal().contains(jL)) {
			rp.getGoleadoresLocal().add(jL);
		}
	}

	public void sumarGolVisitante(ResultadoIntegracionDto rp, JugadorIntegracion jV) {
		rp.agregarGolVisitante();
		if (!rp.getGoleadoresVisitante().contains(jV)) {
			rp.getGoleadoresVisitante().add(jV);
		}
	}

	public void lesionJugador(JugadorIntegracion j) {
		// TODO hacerlo bien
		j.setLesion(2);
	}

	public void tarjetaJugador(Jugador j) {

	}

	// public Partido crearPartidoPrueba(){
	// Partido p = new Partido();
	// em.persist(p);
	// return p;
	// }
	
	public ConfiguracionPartido findConfiguracionPartido() {
		return (ConfiguracionPartido) em.createQuery(
				"select c from ConfiguracionPartido c").getSingleResult();
	}
	
	public Partido partidoEnJuego(Partido p) {
		p.setEstado(EnumPartido.JUGANDO);
		return p;
	}
	

}


