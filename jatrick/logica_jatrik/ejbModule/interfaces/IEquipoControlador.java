package interfaces;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import partidos.Partido;
import users.Manager;
import jugadores.Jugador;
import datatypes.DatosEquipo;
import datatypes.DatosJugador;
import datatypes.EnumEntrenamiento;
import equipos.Alineacion;
import equipos.Equipo;
import excepciones.NoExisteEquipoExcepcion;

@Local
public interface IEquipoControlador {
	
	public int crearEquipo(String nombreEquipo, boolean bot, int cantidad);
	
	public Equipo findEquipo(int codigoEquipo);
	
	public DatosEquipo obtenerEquipo(int codEquipo);
	
	public void modificarEquipo(DatosEquipo equipo);
	
	public Set<Equipo> listarEquiposSistema();
	
	public Set<Equipo> listarEquiposPais(int codigoPais);
	
	public Set<Equipo> listarEquiposTorneo(int codTorneo);
	
	public Set<DatosEquipo> obtenerEquiposSistema();
	
	public Set<DatosEquipo> obtenerEquiposPais(int codigoPais);
	
	public Set<DatosEquipo> obtenerEquiposTorneo(int codTorneo);
	
	public Set<DatosJugador> obtenerJugadoresEquipo(int codEquipo);

	public int crearAlineacion(List<Jugador> delanteros, List<Jugador> mediocampistas,
			List<Jugador> defensas, Jugador golero, Jugador lesionDelantero,
			Jugador lesionMediocampistas, Jugador lesionDefensas,
			Jugador lesionGolero, List<Jugador> suplentes, boolean defecto);

	public Alineacion findAlineacion(int codigoAlineacion);

	public Equipo asignarTorneo(Manager manager, DatosEquipo e);
	
	public void modificarTipoEntrenamientoEquipo(int codigoEquipo,EnumEntrenamiento tipoEntrenamiento) throws NoExisteEquipoExcepcion;

	public List<Partido> obtenerProximosPartidos(int codEquipo, int cantidad);
	
	public void elegirEntrenamiento(int idCodigoEquipo, EnumEntrenamiento enumEntrenamiento);
	
	public EnumEntrenamiento entrenamientoEquipo(int idCodigoEquipo);
	
}
