package interfaces;

import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import jugadores.Jugador;
import datatypes.DatosEquipo;
import datatypes.DatosJugador;
import equipos.Alineacion;
import equipos.Equipo;

@Local
public interface IEquipoControlador {
	
	public int crearEquipo(String nombreEquipo);
	
	public Equipo findEquipo(int codigoEquipo);
	
	public DatosEquipo obtenerEquipo(int codEquipo);
	
	public Set<Equipo> listarEquiposSistema();
	
	public Set<Equipo> listarEquiposPais(int codigoPais);
	
	public Set<Equipo> listarEquiposTorneo(int codTorneo);
	
	public Set<DatosEquipo> obtenerEquiposSistema();
	
	public Set<DatosEquipo> obtenerEquiposPais(int codigoPais);
	
	public Set<DatosEquipo> obtenerEquiposTorneo(int codTorneo);
	
	public Set<DatosJugador> obtenerJugadoresEquipo(int codEquipo);

	int crearAlineacion(List<Jugador> delanteros, List<Jugador> mediocampistas,
			List<Jugador> defensas, Jugador golero, Jugador lesionDelantero,
			Jugador lesionMediocampistas, Jugador lesionDefensas,
			Jugador lesionGolero, List<Jugador> suplentes, boolean defecto);

	Alineacion findAlineacion(int codigoAlineacion);
	
}
