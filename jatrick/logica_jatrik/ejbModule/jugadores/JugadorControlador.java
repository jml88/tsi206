package jugadores;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import interfaces.IJugadorControlador;

@Stateless
@LocalBean
public class JugadorControlador implements IJugadorControlador {
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;

	@Override
	public int crearJugador() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Jugador findJugador(int codJugador) {
		return em.find(Jugador.class, codJugador);
	}
	

}
