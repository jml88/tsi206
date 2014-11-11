package equipos;

import datatypes.EnumTipoTransaccion;
import excepciones.CapitalNegativo;
import excepciones.NoExisteEquipoExcepcion;
import excepciones.NoSePuedeAgrandarEstadio;
import fabricas.HomeFactory;
import finanzas.Finanzas;
import interfaces.IEstadioControlador;

import java.util.Calendar;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import configuracionGral.ConfiguracionGral;

public class EstadioControlador implements IEstadioControlador {
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;

	@Inject
	private HomeFactory hf;
	
	private ConfiguracionGral config = hf.getConfiguracionControlador().getConfiguracion();

	@Override
	public Estadio estadioDeEquipo(int codigoEquipo) throws NoExisteEquipoExcepcion {
		Equipo e = hf.getEquipoControlador().findEquipo(codigoEquipo);
		if(e==null){
			throw new NoExisteEquipoExcepcion("No existe equipo de id: "+ codigoEquipo );
		}
		return e.getEstadio();
	}

	@Override
	public void mejorarEstadioEquipo(int codigoEquipo) throws NoExisteEquipoExcepcion, CapitalNegativo, NoSePuedeAgrandarEstadio {
		Equipo e = hf.getEquipoControlador().findEquipo(codigoEquipo);
		if(e==null){
			throw new NoExisteEquipoExcepcion("No existe equipo de id: "+ codigoEquipo );
		}
		
		if (e.getCapital() < config.getCuestaAgrandarEstadio()){
			throw new CapitalNegativo("No tienes suficiente capital");
		}
		
		Estadio estadio = e.getEstadio();
		
		if(estadio.getCapacidad() >= config.getCapacidadMaximaEstadio()){
			throw new NoSePuedeAgrandarEstadio("Ya se ha llegado a la capacidad máxima del estadio");
		}
		
		e.setCapital(e.getCapital() - config.getCuestaAgrandarEstadio());
		Finanzas credito = new Finanzas(-config.getCuestaAgrandarEstadio(), Calendar.getInstance().getTime(), e, EnumTipoTransaccion.INVERSION_ESTADIO);
		int capacidad = estadio.getCapacidad() + config.getCapacidadAgrandarEstadio();
		if (capacidad > config.getCapacidadAgrandarEstadio()){
			capacidad = config.getCapacidadAgrandarEstadio();
		}
		estadio.setCapacidad(capacidad);
		
		em.merge(e);
		em.persist(credito);
		em.merge(estadio);
		
		
	}

}
