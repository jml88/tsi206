package jugadores;

import facbircas.HomeFactory;
import interfaces.IJugadorControlador;

import java.util.HashSet;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@Named
public class JugadorControlador implements IJugadorControlador {
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@Inject
	private HomeFactory hf;
	
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
	public Set<Jugador> generarJugadores(int cant){
		Set<Jugador> jugadores = new HashSet<Jugador>();
		
		Jugador j = new Jugador();
		
		//TODO: Hacer bien despues!!
		j.setNombre("fulano");
		j.setApellido1("");
		
		return jugadores;
		
	}

}
