package configuracionGral;

import java.util.List;

import interfaces.IConfiguracionControlador;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import datatypes.DatosConfiguracionGral;
import excepciones.NoExisteConfiguracionException;

@Stateless
@Named
public class ConfiguracionControlador implements IConfiguracionControlador{
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@Override
	public ConfiguracionGral getConfiguracion(){
		@SuppressWarnings("unchecked")
		List<ConfiguracionGral> cg = (List<ConfiguracionGral>)em.createQuery("Select cg from ConfiguracionGral cg").getResultList();
		if(cg.isEmpty()){
			return new ConfiguracionGral();
		}
		else{
			return cg.get(0);
		}
		
	}
	
	@Override
	public void crearConfiguracionGral(DatosConfiguracionGral dcg){
		if(getConfiguracion()!= null){
			ConfiguracionGral cg = new ConfiguracionGral();
			cg.setCantEquipoTorneo(dcg.getCantEquipoTorneo());
			cg.setCantJugadoresArranque(dcg.getCantJugadoresArranque());
			cg.setDineroInicial(dcg.getDineroInicial());
			cg.setNumeroFecha(dcg.getNumeroFecha());
			cg.setNumeroTorneo(dcg.getNumeroTorneo());
			cg.setPremio(dcg.getPremio());
		}
	}
	
	@Override
	public void modificarConfiguracion(DatosConfiguracionGral dcg) throws NoExisteConfiguracionException{
		ConfiguracionGral cg = this.getConfiguracion();
		if(cg == null){
			throw new NoExisteConfiguracionException("No existe configuracion general");
		}
		cg.setCantEquipoTorneo(dcg.getCantEquipoTorneo());
		cg.setCantJugadoresArranque(dcg.getCantJugadoresArranque());
		cg.setDineroInicial(dcg.getDineroInicial());
		cg.setNumeroFecha(dcg.getNumeroFecha());
		cg.setNumeroTorneo(dcg.getNumeroTorneo());
		cg.setPremio(dcg.getPremio());
	}

	@Override
	public DatosConfiguracionGral getDatosConfiguracionGral() {
		return getConfiguracion().getDatos();
	}

	@Override
	public void crearOModificarConfiguracion(DatosConfiguracionGral dcg) throws NoExisteConfiguracionException {
		ConfiguracionGral cg = getConfiguracion();
		if(cg!= null){
			modificarConfiguracion(dcg);
		}
		else{
			crearConfiguracionGral(dcg);
		}
		
	}

	
	
	
	

}
