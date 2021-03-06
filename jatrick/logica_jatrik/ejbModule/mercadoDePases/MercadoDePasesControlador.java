package mercadoDePases;

import datatypes.EnumTipoTransaccion;
import equipos.Equipo;
import excepciones.CapitalNegativo;
import excepciones.NoExisteEquipoExcepcion;
import excepciones.NoExisteJugador;
import excepciones.NoExisteJugadorALaVenta;
import excepciones.NoExisteJugadorExcepcion;
import excepciones.NoPuedeVenderJugador;
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
		
//		List<CompraVentaJugadores> res = em.createQuery("SELECT cv FROM CompraVentaJugadores cv WHERE "
//				+ "cv.jugadorVenta.codigo = :jugador AND cv.activo = :booleano")
//				.setParameter("jugador",j.getCodigo())
//				.setParameter("booleano", true)
//				.setMaxResults(1).getResultList();
		
		if(j.isEnVenta()){
			
			throw new YaExisteJugadorALaVenta("El jugador de id "+ j.getCodigo() +" está a la venta");
		}
		
//		Oferta oferta = new Oferta(Calendar.getInstance(),precio,e);
		CompraVentaJugadores cv = new CompraVentaJugadores(precio, j, Calendar.getInstance().getTime(),null, e,null,true);
		em.persist(cv);
		j.setEnVenta(true);
			
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
		
		Mensaje m = new Mensaje("Compra y venta", "El jugador " + j.getFullName() + ""+" se ha vendido", null, e, Calendar.getInstance().getTime(),false);
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

	@Override
	public boolean puedePonerEnVentaJugador(int codigoEquipo, int codigoJugador) throws NoExisteJugador, NoExisteEquipoExcepcion, NoExisteJugadorALaVenta {
		Jugador j = hf.getJugadorControlador().findJugador(codigoJugador);
		if(j==null){
			throw new NoExisteJugador("No existe jugador de id: " + codigoJugador);
		}
		Equipo e = hf.getEquipoControlador().findEquipo(codigoEquipo);
		if(e==null){
			throw new NoExisteEquipoExcepcion("No existe equipo de id " + codigoEquipo);
		}
		
		if(j.getEquipo().getCodigo() != codigoEquipo){
			return false;
		}
		@SuppressWarnings("unchecked")
		List<CompraVentaJugadores> l = em.createQuery("SELECT cv FROM CompraVentaJugadores cv WHERE "
				+ "cv.jugadorVenta.codigo = :jugador AND cv.activo = :booleano")
				.setParameter("jugador",j.getCodigo())
				.setParameter("booleano", true)
				.getResultList();
		if(l == null || l.size() == 0 || l.get(0) == null){
			return true;
		}
		
		
		
		return false;
	}

	@Override
	public boolean puedeComprarJugador(int codigoEquipoCompra, int codigoJugador) throws NoExisteEquipoExcepcion, NoExisteJugador {
		Jugador j = hf.getJugadorControlador().findJugador(codigoJugador);
		if(j==null){
			throw new NoExisteJugador("No existe jugador de id: " + codigoJugador);
		}
		Equipo e = hf.getEquipoControlador().findEquipo(codigoEquipoCompra);
		if(e==null){
			throw new NoExisteEquipoExcepcion("No existe equipo de id " + codigoEquipoCompra);
		}
		
		if(j.getEquipo().getCodigo() == codigoEquipoCompra){
			return false;
		}
		@SuppressWarnings("unchecked")
		List<CompraVentaJugadores> l = em.createQuery("SELECT cv FROM CompraVentaJugadores cv WHERE "
				+ "cv.jugadorVenta.codigo = :jugador AND cv.activo = :booleano")
				.setParameter("jugador",j.getCodigo())
				.setParameter("booleano", true)
				.getResultList();
		if(l == null || l.size() == 0 || l.get(0) == null){
			return false;
		}
		return true;
	}

	@Override
	public void ofertarJugador(int codigoEquipo, int codigoJugador, int cantidad) {
		CompraVentaJugadores cvj = (CompraVentaJugadores) em.createQuery("SELECT cv FROM CompraVentaJugadores cv WHERE "
				+ "cv.jugadorVenta.codigo = :jugador AND cv.activo = true").setParameter("jugador", codigoJugador).getSingleResult();
		Equipo e = em.find(Equipo.class, codigoEquipo);
		Oferta of = new Oferta(Calendar.getInstance(), cantidad, e);
		of.setCompraVenta(cvj);
		em.persist(of);
		cvj.getOfertas().add(of);
		
	}

	@Override
	public List<Oferta> obtenerOfertasRealizadas(int codigoEquipo) {
		return em.createQuery("select o from Oferta o where o.equipoOferta.codigo = :codEquipo and o.compraVenta.activo = true").setParameter("codEquipo", codigoEquipo).getResultList();
		
	}

	@Override
	public void cancelarOfertasRealizadas(List<Oferta> ofertasRealizadasCancelar) {
		
		for (Oferta oferta : ofertasRealizadasCancelar) {
			Oferta of =em.find(Oferta.class, oferta.getCodigo());
			em.remove(of);
		}
		
	}

	@Override
	public List<Oferta> obtenerOfertasRecibidas(int codigoEquipo) {
		return em.createQuery("select o from Oferta o where o.compraVenta.equipoVenta.codigo = :codEquipo and o.compraVenta.activo = true").setParameter("codEquipo", codigoEquipo).getResultList();
	}

	@Override
	public void cancelarOfertasRecibidas(List<Oferta> ofertasRecibidasCancelar) {
		for (Oferta oferta : ofertasRecibidasCancelar) {
			Oferta of = em.find(Oferta.class, oferta.getCodigo());
			em.remove(of);
		}
		
	}

}
