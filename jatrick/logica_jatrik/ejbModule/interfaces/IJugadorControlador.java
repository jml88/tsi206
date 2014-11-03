package interfaces;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import jugadores.Jugador;
import equipos.Equipo;
import excepciones.NoExisteEquipoExcepcion;

@Local
public interface IJugadorControlador {
	
	public int crearJugador();
	
	public Jugador findJugador(int codJugador);

	Set<Jugador> generarJugadores(int cant, Equipo equipo);
	
	public List<Jugador> listarJugador(int idEquipo) throws NoExisteEquipoExcepcion;

}
