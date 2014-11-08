package interfaces;

import javax.ejb.Local;

import equipos.Estadio;
import excepciones.CapitalNegativo;
import excepciones.NoExisteEquipoExcepcion;
import excepciones.NoSePuedeAgrandarEstadio;

@Local
public interface IEstadioControlador {
	
	public Estadio estadioDeEquipo(int codigoEquipo) throws NoExisteEquipoExcepcion;
	
	public void mejorarEstadioEquipo(int codigoEquipo) throws NoExisteEquipoExcepcion, CapitalNegativo, NoSePuedeAgrandarEstadio;

}
