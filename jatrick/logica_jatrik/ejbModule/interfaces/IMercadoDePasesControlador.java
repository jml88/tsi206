package interfaces;

import java.util.List;

import javax.ejb.Local;

import excepciones.CapitalNegativo;
import excepciones.NoExisteEquipoExcepcion;
import excepciones.NoExisteJugadorALaVenta;
import excepciones.NoExisteJugadorExcepcion;
import excepciones.NoSePuedeComprarException;
import excepciones.YaExisteJugadorALaVenta;
import mercadoDePases.CompraVentaJugadores;

@Local
public interface IMercadoDePasesControlador {

	public List<CompraVentaJugadores> listarJugadoresEnVenta();
	
	public void ponerJugadorEnVenta(int codigoEquipo, int codigoJugador, int precio) throws NoExisteEquipoExcepcion, NoExisteJugadorExcepcion, YaExisteJugadorALaVenta;
	
	public void cancelarJugadorPuestoEnVenta(int codigoEquipo,int codigoJugador) throws NoExisteEquipoExcepcion, NoExisteJugadorExcepcion, NoExisteJugadorALaVenta;
	
	public void comprarJugador(int codigoEquipoCompra, int codigoJugador) throws NoExisteEquipoExcepcion, NoExisteJugadorExcepcion, NoExisteJugadorALaVenta, CapitalNegativo, NoSePuedeComprarException;
}
