package interfaces;

import java.util.Set;

import javax.ejb.Remote;

import datatypes.DatosJugador;
import jugadores.Jugador;

@Remote
public interface IJugadorControlador {
	
	public int crearJugador();
	
	public Jugador findJugador(int codJugador);
	
	public Set<DatosJugador> obtenerJugadoresEquipo(int codEquipo);

}
