package partidos;


import equipos.Alineacion;
import equipos.Equipo;
import excepciones.NoExistePartidoExepcion;
import fabricas.HomeFactory;
import interfaces.IPartidoControlador;

import java.util.Calendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Stateless
@LocalBean
public class PartidoControlador implements IPartidoControlador {
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@Inject
	private HomeFactory hf;
	
	@Override
	public int crearPartidoAmistoso(int codEquipoLocal, int  codEquipoVisitante, Calendar fecha) {
		Equipo el = hf.getEquipoControlador().findEquipo(codEquipoLocal);
		Equipo ev = hf.getEquipoControlador().findEquipo(codEquipoVisitante);
		Partido p = new Amistoso(el,ev,fecha);
		ResultadoPartido rp = new ResultadoPartido();
		em.persist(rp);
		p.setResultado(rp);
		p.setTipoPartido("Amistoso");
		em.persist(p);
		return 0;
	}
	
	@Override
	public int crearPartidoTorneo(int codEquipoLocal, int  codEquipoVisitante, Calendar fecha) {
		Equipo el = hf.getEquipoControlador().findEquipo(codEquipoLocal);
		Equipo ev = hf.getEquipoControlador().findEquipo(codEquipoVisitante);
		PartidoTorneo p = new PartidoTorneo(el,ev,fecha);
		em.persist(p);
		return 0;
	}

	@Override
	public Partido findPartido(int codPartido) {
		return em.find(Partido.class, codPartido);
	}
	
	public List<Comentario> obtenerComentariosPartido(int codPartido, int nroComentario) throws NoExistePartidoExepcion{
		
		Partido p = em.find(Partido.class,codPartido );
		if(p == null){
			throw new NoExistePartidoExepcion("No existe partido de id " + codPartido);
		}
		@SuppressWarnings("unchecked")
		List<Comentario> comentarios = (List<Comentario>)em.createQuery("SELECT c FROM Comentario c Where c.Id > :nroComentario"
				+ " and c.partido.codigo = :Partido order by c.minuto asc")
				.setParameter("nroComentario", nroComentario)
				.setParameter("Partido", p.getCodigo())
				.getResultList();
		
		return comentarios;
		
	}
	
//	@Override
//	public void setAlineacioPartido(DatosAlineacion datosAlineacion, int idPartido, int idEquipo){
//		IJugadorControlador ijc = hf.getJugadorControlador();
//		IEquipoControlador iec = hf.getEquipoControlador();
//		
//		Partido partido = this.findPartido(idPartido);
//		
//		List<Jugador> delanteros = new ArrayList<Jugador>();
//		List<Jugador> defensas = new ArrayList<Jugador>();
//		List<Jugador> mediocampistas = new ArrayList<Jugador>();
//		List<Jugador> suplentes = new ArrayList<Jugador>();
//		
//		Jugador golero = null;
//		Jugador lesionDelantero = null;
//		Jugador lesionMediocampistas = null;
//		Jugador lesionDefensas = null;
//		Jugador lesionGolero = null;
//		
//		if(!datosAlineacion.getGoleros().isEmpty())
//			golero = ijc.findJugador(datosAlineacion.getGoleros().get(0).getCodigo());
//		
//		if(datosAlineacion.getLesionDelantero() != null)
//			lesionDelantero = ijc.findJugador(datosAlineacion.getLesionDelantero().getCodigo());
//		
//		if(datosAlineacion.getLesionMediocampistas() != null)
//			lesionMediocampistas = ijc.findJugador(datosAlineacion.getLesionMediocampistas().getCodigo());
//		
//		if(datosAlineacion.getLesionDefensas() != null)
//			lesionDefensas = ijc.findJugador(datosAlineacion.getLesionDefensas().getCodigo());
//		
//		if(datosAlineacion.getLesionGolero() != null)
//			lesionGolero = ijc.findJugador(datosAlineacion.getLesionGolero().getCodigo());
//		
//		boolean defecto = datosAlineacion.isDefecto();
//		
//		for (DatosJugador datosJugador : datosAlineacion.getDefensas()) {
//			defensas.add(ijc.findJugador(datosJugador.getCodigo()));
//		}
//		for (DatosJugador datosJugador : datosAlineacion.getMediocampistas()) {
//			mediocampistas.add(ijc.findJugador(datosJugador.getCodigo()));
//		}
//		for (DatosJugador datosJugador : datosAlineacion.getDelanteros()) {
//			delanteros.add(ijc.findJugador(datosJugador.getCodigo()));
//		}
//		for (DatosJugador datosJugador : datosAlineacion.getSuplentes()) {
//			suplentes.add(ijc.findJugador(datosJugador.getCodigo()));
//		}
//		
//		int codAlineacion = iec.crearAlineacion(delanteros, mediocampistas, defensas, golero, lesionDelantero, lesionMediocampistas, lesionDefensas, lesionGolero, suplentes, defecto);
//		Alineacion alineacion = iec.findAlineacion(codAlineacion);
//		
//		if(defecto){
//			Equipo e = iec.findEquipo(idEquipo);
//			e.setAlineacionDefecto(alineacion);
//			em.merge(e);
//		}
//		
//		if (partido.getLocal().getCodigo() == idEquipo)
//			partido.setAlineacionLocal(alineacion);
//		else if(partido.getVisitante().getCodigo() == idEquipo)
//			partido.setAlineacionVisitante(alineacion);
//		
//		em.merge(partido);
//	}
	
	@Override
	public void setAlineacioPartido(Alineacion alineacion, int idPartido, int idEquipo){
		
		Partido partido = this.findPartido(idPartido);
		
		if (partido.getLocal().getCodigo() == idEquipo)
			partido.setAlineacionLocal(alineacion);
		else if(partido.getVisitante().getCodigo() == idEquipo)
			partido.setAlineacionVisitante(alineacion);
		
		em.merge(partido);
	}
	
	@Override
	public Set<Partido> obtenerPartidosUsuario(int codEquipo) {
		Set<Partido> result = new HashSet<Partido>();
		String consulta = "SELECT p FROM Partido p WHERE p.local.codigo = :codEquipo OR p.visitante.codigo = :codEquipo";
		Query query = em.createQuery(consulta);
		query.setParameter("codEquipo", codEquipo);
		for (Object o : query.getResultList()) {
			Partido p = (Partido)o;
			result.add(p);
		}
		return result;
	}
	
	@Override
	public Set<Partido> obtenerPartidosAmistososUsuario(int codEquipo) {
		Set<Partido> result = new HashSet<Partido>();
		String consulta = "SELECT p FROM Amistoso p WHERE p.local.codigo = :codEquipo OR p.visitante.codigo = :codEquipo";
		Query query = em.createQuery(consulta);
		query.setParameter("codEquipo", codEquipo);
		for (Object o : query.getResultList()) {
			Partido p = (Partido)o;
			result.add(p);
		}
		return result;
	}
	
	@Override
	public ResultadoPartido obtenerResultadoPartido(int idPartido){
		Partido p = em.find(Partido.class, idPartido);
		return p.getResultado();
	}
}
