package finanzas;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import datatypes.EnumTipoTransaccion;
import equipos.Equipo;
import equipos.EquipoControlador;
import excepciones.NoExisteEquipoExcepcion;
import fabricas.HomeFactory;
import interfaces.IFinanzasControlador;

@Stateless
@Named
public class FinanzasControlador implements IFinanzasControlador{

	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@Inject
	private HomeFactory hf;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Finanzas> obtenerTransaccionesDesde(int codigoEquipo, Date date) throws NoExisteEquipoExcepcion {
		
		Equipo e = hf.getEquipoControlador().findEquipo(codigoEquipo);
		if(e==null){
			throw new NoExisteEquipoExcepcion("No existe el equipo de id: " + codigoEquipo);
		}
		
		return em.createQuery("SELECT f FROM Finanzas f  WHERE f.equipo.codigo = :equipo "
				+ "AND f.fechaTransaccion >= :fecha")
				.setParameter("equipo", e.getCodigo())
				.setParameter("fecha", date)
				.getResultList();
	}

	@Override
	public int obtenerCapitalEquipo(int codigoEquipo,Date fechaMinima) throws NoExisteEquipoExcepcion {
		Equipo e = hf.getEquipoControlador().findEquipo(codigoEquipo);
		if(e==null){
			throw new NoExisteEquipoExcepcion("No existe el equipo de id: " + codigoEquipo);
		}
		int capital = e.getCapital();
		Object o = em.createQuery("SELECT sum(monto) AS montoTotal  FROM Finanzas f   WHERE equipo.codigo = :equipo "
				+ "AND fechaTransaccion >= :fecha")
				.setParameter("equipo", e.getCodigo())
				.setParameter("fecha", fechaMinima)
				.getSingleResult();
		
		if(o!=null){
			capital -= ((Number) o).intValue();
		}
		
		
		return capital;
	}

	@Override
	public int obtenerGastos(int codigoEquipo, Date date,EnumTipoTransaccion tipoTransaccion) throws NoExisteEquipoExcepcion {
		Equipo e = hf.getEquipoControlador().findEquipo(codigoEquipo);
		if(e==null){
			throw new NoExisteEquipoExcepcion("No existe el equipo de id: " + codigoEquipo);
		}
		List<Finanzas> finanzasLista = em.createQuery("SELECT f  FROM Finanzas f  WHERE f.equipo.codigo = :equipo "
				+ "AND f.fechaTransaccion >= :fecha AND f.tipoTransaccion = :transaccion")
				.setParameter("equipo", e.getCodigo())
				.setParameter("fecha", date)
				.setParameter("transaccion", tipoTransaccion)
				.getResultList();
		
		if(finanzasLista==null){
			return 0;
		}
		int ret = 0;
		for (Finanzas finanzas : finanzasLista) {
			ret += finanzas.getMonto();
		}
		
			
		
		return ret;
	}


}
