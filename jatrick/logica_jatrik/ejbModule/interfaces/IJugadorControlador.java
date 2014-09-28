package interfaces;

import javax.ejb.Remote;

import jugadores.Jugador;

@Remote
public interface IJugadorControlador {
	
	public int crearJugador();
	
	public Jugador findJugador(int codJugador);

}
