package equipos;

import fabricas.HomeFactory;
import interfaces.IEquipoControlador;
import interfaces.IJugadorControlador;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jugadores.Jugador;
import datatypes.DatosEquipo;
import datatypes.DatosJugador;

@Stateless
@Named
public class EquipoControlador implements IEquipoControlador{
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@Inject
	private HomeFactory hf;
	
	@Override
	public int crearEquipo(String nombreEquipo) {
		Alineacion alineacionDefecto = new Alineacion();
		
		em.persist(alineacionDefecto);
		
		Equipo e = new Equipo(nombreEquipo, null, alineacionDefecto);
		//em.persist(e);
		
		IJugadorControlador ijc = hf.getJugadorControlador();
		Set<Jugador> plantel = ijc.generarJugadores(20, e);
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

}
