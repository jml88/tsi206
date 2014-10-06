package interfaces;

import java.util.Set;

import javax.ejb.Local;

import jugadores.Jugador;

@Local
public interface IJugadorControlador {
	
	public int crearJugador();
	
	public Jugador findJugador(int codJugador);

	Set<Jugador> generarJugadores(int cant);

}
