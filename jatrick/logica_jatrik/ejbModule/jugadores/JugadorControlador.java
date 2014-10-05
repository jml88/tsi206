package jugadores;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import datatypes.DatosJugador;
import equipos.Equipo;
import equipos.EquipoControlador;
import interfaces.IJugadorControlador;

@Stateless
@LocalBean
public class JugadorControlador implements IJugadorControlador {
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@EJB
	EquipoControlador ec;

	@Override
	public int crearJugador() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Jugador findJugador(int codJugador) {
		return em.find(Jugador.class, codJugador);
	}

	@Override
	public Set<DatosJugador> obtenerJugadoresEquipo(int codEquipo) {
		Set<DatosJugador> result = new HashSet<DatosJugador>();
		Equipo equipo = ec.findEquipo(codEquipo);
		if (equipo == null) {
			
		}
		for (Jugador j : equipo.getPlantel()) {
			result.add(j.getDatos());
		}
		return result;
	}
	

}
