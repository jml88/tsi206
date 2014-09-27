package jugadores;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;

import interfaces.IJugadorControlador;

@Stateless
@LocalBean
public class JugadorControlador implements IJugadorControlador {

	@Override
	public int crearJugador() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Jugador findJugador(int codJugador) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
