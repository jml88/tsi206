package interfaces;

import java.util.Date;
import java.util.List;

import datatypes.EnumTipoTransaccion;
import excepciones.NoExisteEquipoExcepcion;
import finanzas.Finanzas;

public interface IFinanzasControlador {
	
	public List<Finanzas> obtenerTransaccionesDesde(int codigoEquipo,Date date) throws NoExisteEquipoExcepcion;
	
	public int obtenerCapitalEquipo(int codigoEquipo,Date fechaMinima) throws NoExisteEquipoExcepcion;
	

	public int obtenerGastos(int codigoEquipo, Date date,
			EnumTipoTransaccion tipoTransaccion) throws NoExisteEquipoExcepcion;
	

}
