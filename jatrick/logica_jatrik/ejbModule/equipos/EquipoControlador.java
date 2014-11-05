package equipos;

import excepciones.NoExisteEquipoExcepcion;
import fabricas.HomeFactory;
import interfaces.IEquipoControlador;
import interfaces.IJugadorControlador;

import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partidos.Partido;
import campeonato.Torneo;
import users.Manager;
import jugadores.Jugador;
import datatypes.DatosEquipo;
import datatypes.DatosJugador;
import datatypes.EnumEntrenamiento;

@Stateless
@Named
public class EquipoControlador implements IEquipoControlador{
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@Inject
	private HomeFactory hf;
	
	@Override
	public int crearEquipo(String nombreEquipo, boolean bot, int cantidad) {
//		Alineacion alineacionDefecto = new Alineacion();
//		
//		em.persist(alineacionDefecto);
		
		Equipo e = new Equipo(nombreEquipo, null, null);
		e.setBot(bot);
		//em.persist(e);
		
		IJugadorControlador ijc = hf.getJugadorControlador();
		Set<Jugador> plantel = ijc.generarJugadores(cantidad, e);
		e.setPlantel(plantel);
		em.persist(e);
		
		return e.getCodigo();
	}

	@Override
	public Equipo findEquipo(int codigoEquipo) {
		return em.find(Equipo.class, codigoEquipo);
	}

	@Override
	public DatosEquipo obtenerEquipo(int codEquipo) {
		Equipo equipo = this.findEquipo(codEquipo);
		return equipo.getDatos();
	}

	@Override
	public Set<Equipo> listarEquiposSistema() {
		Set<Equipo> result = new HashSet<Equipo>();
		String consulta = "SELECT e FROM Equipo e";
		Query query = em.createQuery(consulta);
		for (Object o : query.getResultList()) {
			result.add((Equipo)o);
		}
		return result;
	}

	@Override
	public Set<Equipo> listarEquiposPais(int codigoPais) {
		Set<Equipo> result = new HashSet<Equipo>();
		for (Equipo e : this.listarEquiposSistema()) {
			if (e.getCodPais() == codigoPais) {
				result.add(e);
			}
		}
		return result;
	}

	@Override
	public Set<Equipo> listarEquiposTorneo(int codTorneo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<DatosEquipo> obtenerEquiposSistema() {
		Set<DatosEquipo> result = new HashSet<DatosEquipo>(); 
		for (Equipo e : this.listarEquiposSistema()) {
			result.add(e.getDatos());
		}
		return result;
	}

	@Override
	public Set<DatosEquipo> obtenerEquiposPais(int codigoPais) {
		Set<DatosEquipo> result = new HashSet<DatosEquipo>(); 
		for (Equipo e : this.listarEquiposPais(codigoPais)) {
			result.add(e.getDatos());
		}
		return result;
	}

	@Override
	public Set<DatosEquipo> obtenerEquiposTorneo(int codTorneo) {
		Set<DatosEquipo> result = new HashSet<DatosEquipo>(); 
		for (Equipo e : this.listarEquiposTorneo(codTorneo)) {
			result.add(e.getDatos());
		}
		return result;
	}
	
	@Override
	public Set<DatosJugador> obtenerJugadoresEquipo(int codEquipo) {
		Set<DatosJugador> result = new HashSet<DatosJugador>();
		Equipo equipo = this.findEquipo(codEquipo);
		for (Jugador j : equipo.getPlantel()) {
			result.add(j.getDatos());
		}
		return result;
	}
	
	@Override
	public int crearAlineacion(List<Jugador> delanteros, List<Jugador> mediocampistas,
			List<Jugador> defensas, Jugador golero, Jugador lesionDelantero,
			Jugador lesionMediocampistas, Jugador lesionDefensas,
			Jugador lesionGolero, List<Jugador> suplentes, boolean defecto){
		
		Alineacion alineacion = new Alineacion(delanteros, mediocampistas, defensas, golero, lesionDelantero, lesionMediocampistas, lesionDefensas, lesionGolero, suplentes, defecto);
		em.persist(alineacion);
		return alineacion.getCodigo();
		
	}
	
	@Override
	public Alineacion findAlineacion(int codigoAlineacion) {
		return em.find(Alineacion.class, codigoAlineacion);
	}

	@Override
	public void modificarTipoEntrenamientoEquipo(int codigoEquipo,EnumEntrenamiento tipoEntrenamiento) throws NoExisteEquipoExcepcion {
		Equipo e = findEquipo(codigoEquipo);
		if(e==null){
			throw new NoExisteEquipoExcepcion("No existe el equipo de id: " + codigoEquipo);
		}
		
		e.setTipoEntrenamiento(tipoEntrenamiento);
		em.merge(e);
	}
	
	public Equipo asignarTorneo(Manager manager, DatosEquipo eq) {
		//TODO asignar el torneo correctamente, o sea, el torneo de mas abajo
		List<Torneo> torneos = hf.getCampeontaoControlador().obtenerTorneos();
		for(Torneo t : torneos){
			for(Equipo e : t.getEquipos()){
				if(e.isBot()){
					manager.setEquipo(e);
					manager.setTorneo(t);
					e.setBot(false);
					e.setNombre(eq.getNombre());
					em.merge(e);
					em.merge(manager);
					return e;
				}
			}
		}
		return null;
	}

	@Override
	public void modificarEquipo(DatosEquipo equipo) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public List<Partido> obtenerProximosPartidos(int codEquipo, int cantidad){
		Query q = em.createQuery("select p from Partido p where (p.local.codigo = :codEquipo or p.visitante.codigo = :codEquipo) and (p.estado = 'POR_JUGAR' or p.estado = 'POR_SIMULAR') order by p.fechaHora");
		q.setParameter("codEquipo", codEquipo);
//		q.setParameter("fechaActual", (new GregorianCalendar()).getTime());
//		q.setParameter("cantidad", cantidad);
		return q.setMaxResults(cantidad).getResultList();
	}

	@Override
	public void elegirEntrenamiento(int idCodigoEquipo,
			EnumEntrenamiento enumEntrenamiento) {
		// TODO Auto-generated method stub
		Equipo e = findEquipo(idCodigoEquipo);
		e.setTipoEntrenamiento(enumEntrenamiento);
		em.persist(e);
		
		
	}

	@Override
	public EnumEntrenamiento entrenamientoEquipo(int idCodigoEquipo) {
		// TODO Auto-generated method stub
		Equipo e = findEquipo(idCodigoEquipo);
		return e.getTipoEntrenamiento();
	}
	

}
