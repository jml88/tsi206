package integracion_logica;

import javax.ejb.Stateless;
import javax.inject.Named;

import jugadores.Jugador;
import partidos.Comentario;
import equipos.Alineacion;
import equipos.Equipo;

@Stateless
@Named
public class IntegracionConvert {
	
	public Equipo equipoIntegracionToEquipo(EquipoIntegracion ei){
		Equipo e = new Equipo();
		e.setBot(false);
		e.setCodigo(ei.getCodigoEquipo());
		e.setCodigoIntegracion(ei.getCodigoEquipoIntegracion());
		e.setNombre(ei.getNombreEquipo());
		return e;
	}

	public EquipoIntegracion equipoToEquipoIntegracion(Equipo e){
		EquipoIntegracion ei = new EquipoIntegracion();
		ei.setCodigoEquipoIntegracion(e.getCodigoIntegracion());
		ei.setNombreEquipo(e.getNombre());
		ei.setCodigoEquipo(e.getCodigo());
		return ei;
	}
	
	public Comentario comentarioIntegracionToComentario(ComentarioIntegracion ci){
		Comentario c = new Comentario();
		c.setMensaje(ci.getMensaje());
		c.setMinuto(ci.getMinuto());
		return c;
	}
	
	public ComentarioIntegracion comentarioTocomentarioIntegracion(Comentario c){
		ComentarioIntegracion ci = new ComentarioIntegracion();
		ci.setMensaje(c.getMensaje());
		ci.setMinuto(c.getMinuto());
		return ci;
	}
	
	public Jugador jugadorIntegracionToJugador(JugadorIntegracion ji){
		Jugador j = new Jugador();
		j.setApellido1(ji.getApellido1());
		j.setApellido2(ji.getApellido2());
		j.setAtaque(ji.getAtaque());
		j.setDefensa(ji.getDefensa());
		j.setEdad(ji.getEdad());
		j.setEquipo(equipoIntegracionToEquipo(ji.getEquipo()));
		j.setLesion(ji.getLesion());
		j.setNombre(ji.getNombre());
		j.setPorteria(ji.getPorteria());
		j.setTecnica(ji.getTecnica());
		j.setVelocidad(ji.getVelocidad());
		return j;
	}
	
	public JugadorIntegracion jugadorToJugadorIntegracion(Jugador j){
		JugadorIntegracion ji = new JugadorIntegracion();
		ji.setApellido1(j.getApellido1());
		ji.setApellido2(j.getApellido2());
		ji.setAtaque(j.getAtaque());
		ji.setDefensa(j.getDefensa());
		ji.setEdad(j.getEdad());
		ji.setEquipo(equipoToEquipoIntegracion(j.getEquipo()));
		ji.setLesion(j.getLesion());
		ji.setNombre(j.getNombre());
		ji.setPorteria(j.getPorteria());
		ji.setTecnica(j.getTecnica());
		ji.setVelocidad(j.getVelocidad());
		return ji;
	}
	
	public AlineacionIntegracion alineacionToAlineacionIntegracion(Alineacion a){
		AlineacionIntegracion ai = new AlineacionIntegracion();
		for (Jugador j : a.getDefensas()) {
			ai.getDefensas().add(jugadorToJugadorIntegracion(j));
		}
		
		for (Jugador j : a.getDelanteros()) {
			ai.getDelanteros().add(jugadorToJugadorIntegracion(j));
		}
		
		for (Jugador j : a.getMediocampistas()) {
			ai.getMediocampistas().add(jugadorToJugadorIntegracion(j));
		}
		
		for (Jugador j : a.getSuplentes()) {
			ai.getSuplentes().add(jugadorToJugadorIntegracion(j));
		}
		ai.setLesionDefensas(jugadorToJugadorIntegracion(a.getLesionDefensas()));
		ai.setLesionDelantero(jugadorToJugadorIntegracion(a.getLesionDelantero()));
		ai.setLesionGolero(jugadorToJugadorIntegracion(a.getLesionGolero()));
		ai.setLesionMediocampistas(jugadorToJugadorIntegracion(a.getLesionMediocampistas()));
		return ai;
	}
	
	public Alineacion alineacionIntegracionToAlineacion(AlineacionIntegracion ai){
		Alineacion a = new Alineacion();
		return a;
	}
}
