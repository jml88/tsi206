package mercadoDePases;

import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import jugadores.Jugador;
import equipos.Equipo;
import excepciones.NoExisteEquipoExcepcion;
import excepciones.NoExisteJugadorExcepcion;
import excepciones.YaExisteJugadorALaVenta;
import fabricas.HomeFactory;
import interfaces.IMercadoDePases;

@Stateless
@Named
public class MercadoDePasesControlador implements IMercadoDePases {

	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@Inject
	private HomeFactory hf;
	
	@Override
	public List<CompraVentaJugadores> listarJugadoresEnVenta() {
		// TODO Auto-generated method stub
		return em.createQuery("Select cv from CompraVentaJugadores cv").getResultList();
		
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
		
		if(em.createQuery("SELECT cv FROM CompraVentaJugadores cv WHERE "
				+ "cv.codigo = :jugador AND cv.activo = :booleano")
				.setParameter("jugador",j.getCodigo())
				.setParameter("booleano", true)
				.setMaxResults(1).getResultList()!=null){
			
			throw new YaExisteJugadorALaVenta("El jugador de id "+ j.getCodigo() +" está a la venta");
		}
		
		CompraVentaJugadores cv = new CompraVentaJugadores(precio, j, Calendar.getInstance().getTime(), e,null,true);
		em.persist(cv);
			
	}

	@Override
	public void comprarJugador(int codigoEquipoCompra, int codigoJugador) {
		// TODO Auto-generated method stub
		
		
	}

}
