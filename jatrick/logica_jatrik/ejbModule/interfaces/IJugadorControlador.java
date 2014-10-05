package interfaces;

import javax.ejb.Local;

import jugadores.Jugador;

@Local
public interface IJugadorControlador {
	
	public int crearJugador();
	
	public Jugador findJugador(int codJugador);

}
