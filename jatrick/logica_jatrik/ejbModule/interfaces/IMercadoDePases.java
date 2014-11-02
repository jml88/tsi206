package interfaces;

import java.util.List;

import javax.ejb.Local;

import excepciones.NoExisteEquipoExcepcion;
import excepciones.NoExisteJugadorExcepcion;
import excepciones.YaExisteJugadorALaVenta;
import mercadoDePases.CompraVentaJugadores;

@Local
public interface IMercadoDePases {

	public List<CompraVentaJugadores> listarJugadoresEnVenta();
	
	public void ponerJugadorEnVenta(int codigoEquipo, int codigoJugador, int precio) throws NoExisteEquipoExcepcion, NoExisteJugadorExcepcion, YaExisteJugadorALaVenta;
	
	public void comprarJugador(int codigoEquipoCompra, int codigoJugador);
}
