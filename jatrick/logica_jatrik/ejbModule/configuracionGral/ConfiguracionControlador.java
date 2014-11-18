package configuracionGral;

import interfaces.IConfiguracionControlador;

import java.util.Date;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import datatypes.EnumPeriodicoPartido;
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
			PeriodicoPartido periodico = new PeriodicoPartido(1, 1, 3, EnumPeriodicoPartido.MINUTO);
			ConfiguracionGral conf = new ConfiguracionGral(10000, 1000, 20, 8, 0, 0, 2,1, 10, new Date(), 21,30,33,3,1,0,1,0,-1,false,150,170,200, periodico,90000,5000, 5000,100000,150,500);
			em.persist(conf);
			return conf;
		}
		else{
			return cg.get(0);
		}
		
	}
	
	public ConfiguracionGral tieneConfiguracion(){
		@SuppressWarnings("unchecked")
		List<ConfiguracionGral> cg = (List<ConfiguracionGral>)em.createQuery("Select cg from ConfiguracionGral cg").getResultList();
		if(cg.isEmpty()){
			PeriodicoPartido periodico = new PeriodicoPartido(1, 1, 3, EnumPeriodicoPartido.MINUTO);
			ConfiguracionGral conf = new ConfiguracionGral(10000, 1000, 20, 8, 0, 0, 2,1, 10, new Date(), 21,30,33,3,1,0,1,0,-1,false,150,170,200, periodico,90000,5000, 5000,100000,150,500);
			em.persist(conf);
			return conf;

		}
		else{
			return cg.get(0);
		}
		
	}
	
	@Override
	public void crearConfiguracionGral(ConfiguracionGral dcg){
		if(getConfiguracion()== null){
			em.persist(dcg);
		}
		
	}
	
	@Override
	public void modificarConfiguracion(ConfiguracionGral dcg) throws NoExisteConfiguracionException{
		ConfiguracionGral cg = this.getConfiguracion();
		if(cg == null){
			throw new NoExisteConfiguracionException("No existe configuracion general");
		}
		cg.mergeConfiguracionGral(dcg);
		
		if(dcg.getFechaArranqueCampeonato()!=null){
			Date c = dcg.getFechaArranqueCampeonato();
			cg.setFechaArranqueCampeonato(c);
		}
		else{
			cg.setFechaArranqueCampeonato(null);
		}
		
		PeriodicoPartido p = cg.getPeriodicoPartido();
		if(p==null){
			p = new PeriodicoPartido();
		}
		p.setDia(dcg.getPeriodicoPartido().getDia());
		p.setHora(dcg.getPeriodicoPartido().getHora());
		p.setMinuto(dcg.getPeriodicoPartido().getMinuto());
		p.setPeriodico(dcg.getPeriodicoPartido().getPeriodico());
		cg.setPeriodicoPartido(p);
		
		
		
		
		em.persist(cg);
	}

	@Override
	public ConfiguracionGral getDatosConfiguracionGral() {
		return tieneConfiguracion();
	}

	@Override
	public void crearOModificarConfiguracion(ConfiguracionGral dcg) throws NoExisteConfiguracionException {
		ConfiguracionGral cg = getConfiguracion();
		if(cg!= null){
			modificarConfiguracion(dcg);
		}
		else{
			crearConfiguracionGral(dcg);
		}
		
	}

	
	
	
	

}
