package equipos;

import interfaces.IEquipoControlador;

import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jugadores.JugadorControlador;
import datatypes.DatosEquipo;

@Stateless
@LocalBean
public class EquipoControlador implements IEquipoControlador{
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@EJB
	JugadorControlador jugadorC;
	
	@Override
	public int crearEquipo(DatosEquipo de) {
		
		Alineacion alineacionDefecto = new Alineacion();
		
		Equipo e = new Equipo(de, alineacionDefecto);
		
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
		if (equipo == null) {
			
		}
		return equipo.getDatos();
	}

	@Override
	public Set<Equipo> listarEquiposSistema() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Equipo> listarEquiposPais(int codigoPais) {
		jugadorC.findJugador(codigoPais);
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Equipo> listarEquiposTorneo(int codTorneo) {
		// TODO Auto-generated method stub
		return null;
	}

}
