package partidos;


import datatypes.EnumPartido;
import equipos.Alineacion;
import equipos.Equipo;
import excepciones.NoExistePartidoExepcion;
import fabricas.HomeFactory;
import interfaces.IPartidoControlador;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import org.hibernate.validator.constraintvalidation.HibernateConstraintValidatorContext;


@Stateless
@LocalBean
public class PartidoControlador implements IPartidoControlador {
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@Inject
	private HomeFactory hf;
	
	@Override
	public int crearPartidoAmistoso(int codEquipoLocal, int  codEquipoVisitante, Calendar fecha, int cantidadRetp) {
		Equipo el = hf.getEquipoControlador().findEquipo(codEquipoLocal);
		Equipo ev = hf.getEquipoControlador().findEquipo(codEquipoVisitante);
		Partido p = new Amistoso(el,ev,fecha,cantidadRetp);
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
	
	@Override
	public Partido obtenerPartido(int codPartido) {
		Partido p = findEager(em, Partido.class, codPartido);
		return p;
	}
	
	@Override
	public Alineacion findAlineacionLocal(int codPartido) {
		int codAlineacion = -1;
		Partido p = em.find(Partido.class, codPartido);
		if (p.getAlineacionLocal() != null){
			codAlineacion = p.getAlineacionLocal().getCodigo();
			Alineacion a = findEager(em, Alineacion.class, codAlineacion);
			return a;
		}else
			return null;

	}
	
	@Override
	public Alineacion findAlineacionVisitante(int codPartido) {
		int codAlineacion = -1;
		Partido p = em.find(Partido.class, codPartido);
		if (p.getAlineacionVisitante() != null){
			codAlineacion = p.getAlineacionVisitante().getCodigo();
			Alineacion a = findEager(em, Alineacion.class, codAlineacion);
			return a;
		}else
			return null;
	}
	
	public static <T> T findEager(EntityManager em, Class<T> type, Object id) {
	    T entity = em.find(type, id);
	    for (Field field: type.getDeclaredFields()) {
	        OneToMany annotation = field.getAnnotation(OneToMany.class);
	        if (annotation != null) {	            
                try {
                    new PropertyDescriptor(field.getName(), type).getReadMethod().invoke(entity);
                } catch (Exception e) {
                    e.printStackTrace();
                }
	        }
	    }
	    return entity;
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
		
		if (partido.getLocal().getCodigo() == idEquipo){
			if (partido.getAlineacionLocal() == null){
				partido.setAlineacionLocal(alineacion);
			}
			else{
				Alineacion a = partido.getAlineacionLocal();
				a.setAlineacionDefecto(alineacion.isAlineacionDefecto());
				a.setGolero(alineacion.getGolero());
				a.setDefensas(alineacion.getDefensas());
				a.setDelanteros(alineacion.getDelanteros());
				a.setMediocampistas(alineacion.getMediocampistas());
				a.setLesionGolero(alineacion.getLesionGolero());
				a.setLesionDefensas(alineacion.getLesionDefensas());
				a.setLesionMediocampistas(alineacion.getLesionMediocampistas());
				a.setLesionDelantero(alineacion.getLesionDelantero());
			}
		}
		else if(partido.getVisitante().getCodigo() == idEquipo){
			if (partido.getAlineacionVisitante() == null){
				partido.setAlineacionVisitante(alineacion);
			}
			else{
				Alineacion a = partido.getAlineacionVisitante();
				a.setAlineacionDefecto(alineacion.isAlineacionDefecto());
				a.setGolero(alineacion.getGolero());
				a.setDefensas(alineacion.getDefensas());
				a.setDelanteros(alineacion.getDelanteros());
				a.setMediocampistas(alineacion.getMediocampistas());
				a.setLesionGolero(alineacion.getLesionGolero());
				a.setLesionDefensas(alineacion.getLesionDefensas());
				a.setLesionMediocampistas(alineacion.getLesionMediocampistas());
				a.setLesionDelantero(alineacion.getLesionDelantero());
			}
		}
		
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

	@Override
	public void retarAmistoso(int codEquipo, Integer codEquipoRetado,
			Date fechaAmistoso, int cantidadAmisto) {
		Calendar c = Calendar.getInstance();
		c.setTime(fechaAmistoso);
		this.crearPartidoAmistoso(codEquipoRetado, codEquipo, c, cantidadAmisto);
		
	}
	
	@Override
	public List<Amistoso> obtenerAmistososAConcretar(int codEquipo){
		return em.createQuery("select a from Amistoso a where a.local.codigo = :codEquipo and a.estado = 'POR_CONCRETAR'").setParameter("codEquipo", codEquipo).getResultList();
	}
	
	@Override
	public void cancelarAmistoso(int codAmistoso){
		Amistoso a = em.find(Amistoso.class, codAmistoso);
		a.setAlineacionLocal(null);
		a.setAlineacionVisitante(null);
		a.setLocal(null);
		a.setVisitante(null);
		em.remove(a);
	}
	
	@Override
	public void aceptarAmistoso(int codAmistoso){
		Amistoso a = em.find(Amistoso.class, codAmistoso);
		a.setEstado(EnumPartido.POR_JUGAR);
	}
}
