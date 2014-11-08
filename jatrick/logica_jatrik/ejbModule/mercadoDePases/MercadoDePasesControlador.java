package mercadoDePases;

import datatypes.EnumTipoTransaccion;
import equipos.Equipo;
import excepciones.CapitalNegativo;
import excepciones.NoExisteEquipoExcepcion;
import excepciones.NoExisteJugadorALaVenta;
import excepciones.NoExisteJugadorExcepcion;
import excepciones.NoSePuedeComprarException;
import excepciones.YaExisteJugadorALaVenta;
import fabricas.HomeFactory;
import finanzas.Finanzas;
import interfaces.IMercadoDePasesControlador;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jugadores.Jugador;
import mensajes.Mensaje;

@Stateless
@Named
public class MercadoDePasesControlador implements IMercadoDePasesControlador {

	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@Inject
	private HomeFactory hf;
	
	@Override
	public List<CompraVentaJugadores> listarJugadoresEnVenta() {
		// TODO Auto-generated method stub
		List<CompraVentaJugadores> compraVentaLista = em.createQuery("Select cv from CompraVentaJugadores cv Where "
				+ "cv.activo = :booleano")
				.setParameter("booleano", true)
				.getResultList();
		List<Jugador> jugadores = new LinkedList<Jugador>();
		for (CompraVentaJugadores cvj : compraVentaLista) {
			jugadores.add(cvj.getJugadorVenta());
		}
		return compraVentaLista;
		
	}

	@Override
	public void ponerJugadorEnVenta(int codigoEquipo, int codigoJugador,
			int precio) throws NoExisteEquipoExcepcion, NoExisteJugadorExcepcion, YaExisteJugadorALaVenta {
		// TODO Auto-generated method stub
		Equipo e = hf.getEquipoControlador().findEquipo(codigoEquipo);
		Jugador j = hf.getJugadorControlador().findJugador(codigoJugador);
		
		if(e==null){
			throw new NoExisteEquipoExcepcion("No existe equipo de id: " + codigoEquipo);
		}
		if(j==null){
			throw new NoExisteJugadorExcepcion("No existe Juagador de id: " + codigoJugador);
		}
		
		List<CompraVentaJugadores> res = em.createQuery("SELECT cv FROM CompraVentaJugadores cv WHERE "
				+ "cv.jugadorVenta.codigo = :jugador AND cv.activo = :booleano")
				.setParameter("jugador",j.getCodigo())
				.setParameter("booleano", true)
				.setMaxResults(1).getResultList();
		
		if(!res.isEmpty()){
			
			throw new YaExisteJugadorALaVenta("El jugador de id "+ j.getCodigo() +" está a la venta");
		}
		
		CompraVentaJugadores cv = new CompraVentaJugadores(precio, j, Calendar.getInstance().getTime(),null, e,null,true);
		em.persist(cv);
			
	}

	@Override
	public void comprarJugador(int codigoEquipoCompra, int codigoJugador) throws NoExisteEquipoExcepcion, NoExisteJugadorExcepcion, NoExisteJugadorALaVenta, CapitalNegativo, NoSePuedeComprarException {
		// TODO Auto-generated method stub
		Equipo e = hf.getEquipoControlador().findEquipo(codigoEquipoCompra);
		Jugador j = hf.getJugadorControlador().findJugador(codigoJugador);
		
		if(e==null){
			throw new NoExisteEquipoExcepcion("No existe equipo de id: " + codigoEquipoCompra);
		}
		if(j==null){
			throw new NoExisteJugadorExcepcion("No existe Juagador de id: " + codigoJugador);
		}
	
		@SuppressWarnings("unchecked")
		List<CompraVentaJugadores> l = em.createQuery("SELECT cv FROM CompraVentaJugadores cv WHERE "
				+ "cv.jugadorVenta.codigo = :jugador AND cv.activo = :booleano")
				.setParameter("jugador",j.getCodigo())
				.setParameter("booleano", true)
				.getResultList();
		if(l == null || l.size() == 0 || l.get(0) == null){
			throw new NoExisteJugadorALaVenta("El jugador a la venta de id: " +codigoJugador + " no esta a la venta");
		}
		
		CompraVentaJugadores cv = l.get(0);
		if(e.getCapital() - cv.getPrecio() <= 0){
			throw new CapitalNegativo("No tienes suficiente capital");
		}
		
		if(cv.getEquipoCompra().getCodigo() == e.getCodigo()){
			throw new NoSePuedeComprarException("No puedes comprar tu propio jugador");
		}
		
		Equipo equipoVende = cv.getEquipoVenta();
		
		cv.setActivo(false);
		cv.setEquipoCompra(e);
		cv.setFechaDeTransaccion(Calendar.getInstance().getTime());
		em.merge(cv);
		
		equipoVende.setCapital(equipoVende.getCapital()+cv.getPrecio());
		em.merge(equipoVende);
		
		e.setCapital(e.getCapital()+ cv.getPrecio());
		em.merge(e);
		
		j.setEquipo(e);
		em.merge(e);
		
		Finanzas debito = new Finanzas(cv.getPrecio(), Calendar.getInstance().getTime(), equipoVende, EnumTipoTransaccion.VENTA_JUGADORES);
		em.persist(debito);
		
		Finanzas credito = new Finanzas(-cv.getPrecio(), Calendar.getInstance().getTime(), e, EnumTipoTransaccion.COMPRA_JUGADORES);
		em.persist(credito);
		
		Mensaje m = new Mensaje("Compra y venta", "El jugador " + j.getFullName() + " se ha vendido", null, e.getUsuario(), Calendar.getInstance().getTime(),false);
		em.persist(m);
		
		
	}

	@Override
	public void cancelarJugadorPuestoEnVenta(int codigoEquipo, int codigoJugador) throws NoExisteEquipoExcepcion, NoExisteJugadorExcepcion, NoExisteJugadorALaVenta {
		// TODO Auto-generated method stub
		Equipo e = hf.getEquipoControlador().findEquipo(codigoEquipo);
		Jugador j = hf.getJugadorControlador().findJugador(codigoJugador);
		if(e==null){
			throw new NoExisteEquipoExcepcion("No existe equipo de id: " + codigoEquipo);
		}
		if(j==null){
			throw new NoExisteJugadorExcepcion("No existe Juagador de id: " + codigoJugador);
		}
	
		@SuppressWarnings("unchecked")
		List<CompraVentaJugadores> l = em.createQuery("SELECT cv FROM CompraVentaJugadores cv WHERE "
				+ "cv.jugadorVenta.codigo = :jugador AND cv.activo = :booleano")
				.setParameter("jugador",j.getCodigo())
				.setParameter("booleano", true)
				.setMaxResults(1).getResultList();
		if(l.size() == 0 || l.get(0) == null){
			throw new NoExisteJugadorALaVenta("El jugador a la venta de id: " +codigoJugador + " no esta a la venta");
		}
		
		CompraVentaJugadores cv = l.get(0);
		em.remove(cv);
	}

}
