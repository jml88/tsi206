package partido;

import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jugadores.Jugador;
import campeonato.Posicion;
import partidos.Partido;
import partidos.PartidoCopa;
import partidos.PartidoTorneo;
import partidos.ResultadoPartido;
import datatypes.DatosAlineacion;
import datatypes.DatosJugador;
import datatypes.EnumPartido;
import equipos.Alineacion;
import equipos.Equipo;

@Stateless
@LocalBean
public class SimulacionControlador {
	
	
	@PersistenceContext(unitName = "jatrik")
	private EntityManager em;
	
	public Partido find(int id) {
		return em.find(Partido.class, id);
	}
	
	public DatosAlineacion crearAlineacion(Equipo equipo) {
		DatosAlineacion da = new DatosAlineacion();
		da.setDefecto(true);
		int num = 0;
		for (Jugador jugador : equipo.getPlantel()) {
			if (num == 0) {
				da.getGoleros().add(jugador.getDatos());
			} else if (num == 1 || num == 2 || num == 3 || num == 4) {
				da.getDefensas().add(jugador.getDatos());
			} else if (num == 5 || num == 6 || num == 7 || num == 8) {
				da.getMediocampistas().add(jugador.getDatos());
			} else if (num == 9 || num == 10) {
				da.getDelanteros().add(jugador.getDatos());
			}
			num++;
		}
		return da;
	}
	
	public PartidoTorneo findPartidoTorneo(int codigo) {
		return em.find(PartidoTorneo.class, codigo);

	}
	
	public PartidoCopa findPartidoCopa(int codigo) {
		return em.find(PartidoCopa.class, codigo);

	}
	
	public void partidoFinalizado(Partido p) {
		p.setEstado(EnumPartido.FINALIZADO);
		if (p.getResultado().getGolesLocal() == -1){
			p.getResultado().setGolesLocal(0);
		}
		if (p.getResultado().getGolesVisitante() == -1){
			p.getResultado().setGolesVisitante(0);
		}
		em.merge(p);
		em.flush();
	}
	
	public void actualizarPosicionFechaTorneo(Posicion posLocal,
			Posicion posVisitante, PartidoTorneo pt) {
		posLocal.actualizarFecha(pt.getResultado().getGolesLocal(), pt
				.getResultado().getGolesVisitante());
		em.merge(posLocal);
		posVisitante.actualizarFecha(pt.getResultado().getGolesVisitante(), pt
				.getResultado().getGolesLocal());
		for (Jugador jL : pt.getLocal().getPlantel()) {
			if (jL.getLesion() != null){
				jL.setLesion((jL.getLesion()-1)== 0? null : jL.getLesion()-1);
			}
			if (jL.getSancionLiga() != null){
				jL.setSancionLiga((jL.getSancionLiga()-1)== 0? null : jL.getSancionLiga()-1);
			}
		}
		for (Jugador jV : pt.getVisitante().getPlantel()) {
			if (jV.getLesion() != null){
				jV.setLesion((jV.getLesion()-1)== 0? null : jV.getLesion()-1);
			}
			if (jV.getSancionLiga() != null){
				jV.setSancionLiga((jV.getSancionLiga()-1)== 0? null : jV.getSancionLiga()-1);
			}
			jV.setTarjetasPartido(null);
		}
		pt.getLocal().setJuvenilFecha(true);
		pt.getVisitante().setJuvenilFecha(true);
		em.merge(posVisitante);

	}
	
	public void setAlineacioPartido(DatosAlineacion datosAlineacion,
			int idPartido, int idEquipo) {

		Partido partido = em.find(Partido.class, idPartido);

		List<Jugador> delanteros = new ArrayList<Jugador>();
		List<Jugador> defensas = new ArrayList<Jugador>();
		List<Jugador> mediocampistas = new ArrayList<Jugador>();
		List<Jugador> suplentes = new ArrayList<Jugador>();

		Jugador golero = null;
		Jugador lesionDelantero = null;
		Jugador lesionMediocampistas = null;
		Jugador lesionDefensas = null;
		Jugador lesionGolero = null;

		if (!datosAlineacion.getGoleros().isEmpty())
			golero = em.find(Jugador.class, datosAlineacion.getGoleros().get(0)
					.getCodigo());

		if (datosAlineacion.getLesionDelantero() != null)
			lesionDelantero = em.find(Jugador.class, datosAlineacion
					.getLesionDelantero().getCodigo());

		if (datosAlineacion.getLesionMediocampistas() != null)
			lesionMediocampistas = em.find(Jugador.class, datosAlineacion
					.getLesionMediocampistas().getCodigo());

		if (datosAlineacion.getLesionDefensas() != null)
			lesionDefensas = em.find(Jugador.class, datosAlineacion
					.getLesionDefensas().getCodigo());

		if (datosAlineacion.getLesionGolero() != null)
			lesionGolero = em.find(Jugador.class, datosAlineacion
					.getLesionGolero().getCodigo());

		boolean defecto = datosAlineacion.isDefecto();

		for (DatosJugador datosJugador : datosAlineacion.getDefensas()) {
			defensas.add(em.find(Jugador.class, datosJugador.getCodigo()));
		}
		for (DatosJugador datosJugador : datosAlineacion.getMediocampistas()) {
			mediocampistas
					.add(em.find(Jugador.class, datosJugador.getCodigo()));
		}
		for (DatosJugador datosJugador : datosAlineacion.getDelanteros()) {
			delanteros.add(em.find(Jugador.class, datosJugador.getCodigo()));
		}
		for (DatosJugador datosJugador : datosAlineacion.getSuplentes()) {
			suplentes.add(em.find(Jugador.class, datosJugador.getCodigo()));
		}

		Alineacion alineacion = new Alineacion(delanteros, mediocampistas,
				defensas, golero, lesionDelantero, lesionMediocampistas,
				lesionDefensas, lesionGolero, suplentes, defecto);
		em.persist(alineacion);
		int codAlineacion = alineacion.getCodigo();
		alineacion = em.find(Alineacion.class, codAlineacion);

		if (defecto) {
			Equipo e = em.find(Equipo.class, idEquipo);
			e.setAlineacionDefecto(alineacion);
			em.merge(e);
		}

		if (partido.getLocal().getCodigo() == idEquipo)
			partido.setAlineacionLocal(alineacion);
		else if (partido.getVisitante().getCodigo() == idEquipo)
			partido.setAlineacionVisitante(alineacion);

		em.merge(partido);
	}

	public void actualizarCopa(PartidoCopa pc, Equipo ganador) {
		if (pc.getSiguienteFase()!= null){
			if (pc.getSiguienteFase().getLocal()!= null){
				pc.getSiguienteFase().setLocal(ganador);
			}
			else{
				pc.getSiguienteFase().setVisitante(ganador);
			}
		}
		else{
			ganador.setCopasGanadas(ganador.getCopasGanadas());
			ganador.setCapital(ganador.getCapital()+(pc.getCopa().getIngreso()*pc.getCopa().getCantidadEquipos()));
		}
		
	}

	public void setPenalesPartido(ResultadoPartido resultado, int penalesL,
			int penalesV) {
		resultado.setPenalesLocal(penalesL);
		resultado.setPenalesVisitante(penalesV);
		em.merge(resultado);
		
	}

	public Alineacion copiarAlineacion(Alineacion alineacionDefecto) {
		Alineacion a = new Alineacion();
		a = alineacionDefecto;
		em.persist(a);
		return a;
	}

}
