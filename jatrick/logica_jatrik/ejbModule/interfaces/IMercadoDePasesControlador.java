package interfaces;

import java.util.List;

import javax.ejb.Local;

import mercadoDePases.CompraVentaJugadores;
import mercadoDePases.Oferta;
import excepciones.CapitalNegativo;
import excepciones.NoExisteEquipoExcepcion;
import excepciones.NoExisteJugador;
import excepciones.NoExisteJugadorALaVenta;
import excepciones.NoExisteJugadorExcepcion;
import excepciones.NoSePuedeComprarException;
import excepciones.YaExisteJugadorALaVenta;

@Local
public interface IMercadoDePasesControlador {

	public List<CompraVentaJugadores> listarJugadoresEnVenta();
	
	public void ponerJugadorEnVenta(int codigoEquipo, int codigoJugador, int precio) throws NoExisteEquipoExcepcion, NoExisteJugadorExcepcion, YaExisteJugadorALaVenta;
	
	public void cancelarJugadorPuestoEnVenta(int codigoEquipo,int codigoJugador) throws NoExisteEquipoExcepcion, NoExisteJugadorExcepcion, NoExisteJugadorALaVenta;
	
	public void comprarJugador(int codigoEquipoCompra, int codigoJugador) throws NoExisteEquipoExcepcion, NoExisteJugadorExcepcion, NoExisteJugadorALaVenta, CapitalNegativo, NoSePuedeComprarException;
	
	public boolean puedePonerEnVentaJugador(int codigoEquipo, int codigoJugador) throws NoExisteJugador, NoExisteEquipoExcepcion, NoExisteJugadorALaVenta;
	
	public boolean puedeComprarJugador(int codigoEquipoCompra, int codigoJugador) throws NoExisteEquipoExcepcion, NoExisteJugador;

	public void ofertarJugador(int codigoEquipo, int codigoJugador, int cantidad);

	public List<Oferta> obtenerOfertasRealizadas(int codigoEquipo);

	public void cancelarOfertasRealizadas(List<Oferta> ofertasRealizadasCancelar);

	public List<Oferta> obtenerOfertasRecibidas(int codigoEquipo);

	public void cancelarOfertasRecibidas(List<Oferta> ofertasRecibidasCancelar);
}
