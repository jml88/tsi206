package finanzas;

import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import datatypes.EnumTipoTransaccion;
import equipos.Equipo;
import equipos.EquipoControlador;
import excepciones.NoExisteEquipoExcepcion;
import interfaces.IFinanzasControlador;

public class FinanzasControlador implements IFinanzasControlador{

	@Inject
	private EquipoControlador ec;
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@SuppressWarnings("unchecked")
	@Override
	public List<Finanzas> obtenerTransaccionesDesde(int codigoEquipo, Date date) throws NoExisteEquipoExcepcion {
		
		Equipo e = ec.findEquipo(codigoEquipo);
		if(e==null){
			throw new NoExisteEquipoExcepcion("No existe el equipo de id: " + codigoEquipo);
		}
		
		return em.createQuery("SELECT f FROM Finanzas f  WHERE f.equipo.codigo = :equipo "
				+ "AND (DATE)f.fechaTransaccion >= :fecha")
				.setParameter("equipo", e.getCodigo())
				.setParameter("fecha", date)
				.getResultList();
	}

	@Override
	public int obtenerCapitalEquipo(int codigoEquipo,Date fechaMinima) throws NoExisteEquipoExcepcion {
		Equipo e = ec.findEquipo(codigoEquipo);
		if(e==null){
			throw new NoExisteEquipoExcepcion("No existe el equipo de id: " + codigoEquipo);
		}
		int capital = e.getCapital();
		Object o = em.createQuery("SELECT f FROM Finanzas sum(f.monto)  WHERE f.equipo.codigo = :equipo "
				+ "AND (DATE)f.fechaTransaccion >= :fecha")
				.setParameter("equipo", e.getCodigo())
				.setParameter("fecha", fechaMinima)
				.getSingleResult();
		
		capital =+ ((Number) o).intValue();
		
		
		return capital;
	}

	@Override
	public int obtenerGastos(int codigoEquipo, Date date,EnumTipoTransaccion tipoTransaccion) throws NoExisteEquipoExcepcion {
		Equipo e = ec.findEquipo(codigoEquipo);
		if(e==null){
			throw new NoExisteEquipoExcepcion("No existe el equipo de id: " + codigoEquipo);
		}
		Object o = em.createQuery("SELECT sum(f.monto) FROM Finanzas f  WHERE f.equipo.codigo = :equipo "
				+ "AND (DATE)f.fechaTransaccion >= :fecha AND f.tipoTransaccion = :transaccion")
				.setParameter("equipo", e.getCodigo())
				.setParameter("fecha", date)
				.setParameter("transaccion", tipoTransaccion)
				.getSingleResult();
		
		return ((Number) o).intValue() ;
	}


}
