package configuracionGral;

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
	
	
	public ConfiguracionGral getConfiguracion(){
		ConfiguracionGral cg = (ConfiguracionGral)em.createQuery("Select cg from ConfiguracionGral cg").getSingleResult();
		return cg;
	}
	
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
	
	

}
