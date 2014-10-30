package configuracion;

import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import configuracionGral.ConfiguracionGral;

@Stateless
@Named
public class ConfiguracionControladorLocal {
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	public ConfiguracionGral getConfiguracion(){
		@SuppressWarnings("unchecked")
		List<ConfiguracionGral> cg = (List<ConfiguracionGral>)em.createQuery("Select cg from ConfiguracionGral cg").getResultList();
		if(cg.isEmpty()){
			
			return null;
		}
		else{
			return cg.get(0);
		}
		
	}

}
