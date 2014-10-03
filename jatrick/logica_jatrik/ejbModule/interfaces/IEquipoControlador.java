package interfaces;

import java.util.Set;

import javax.ejb.Remote;

//import datatypes.DatosEquipo;
import equipos.Equipo;

@Remote
public interface IEquipoControlador {
	
	
//	public int crearEquipo(DatosEquipo de);
	
	public Equipo findEquipo(int codigoEquipo);
	
	public Set<Equipo> listarEquiposSistema();
	
	public Set<Equipo> listarEquiposPais(int codigoPais);
	
	public Set<Equipo> listarEquiposTorneo(int codTorneo);

}
