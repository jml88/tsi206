package interfaces;

import java.util.Set;

import javax.ejb.Local;

import datatypes.DatosEquipo;
import datatypes.DatosJugador;
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
	
}
