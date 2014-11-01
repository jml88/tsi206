package configuracionGral;

import interfaces.IConfiguracionControlador;

import java.util.Calendar;
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
			Calendar c = Calendar.getInstance();
			PeriodicoPartido pp = new PeriodicoPartido(0, 0, 0,EnumPeriodicoPartido.MINUTO);
			ConfiguracionGral conf = new ConfiguracionGral(0, 0, 0, 0, 0, 0, 0, 0, 0, c, pp);
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
			return null;
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
		cg.setCantEquipoTorneo(dcg.getCantEquipoTorneo());
		cg.setCantJugadoresArranque(dcg.getCantJugadoresArranque());
		cg.setDineroInicial(dcg.getDineroInicial());
		cg.setNumeroFecha(dcg.getNumeroFecha());
		cg.setNumeroTorneo(dcg.getNumeroTorneo());
		cg.setCantidadDescensos(dcg.getCantidadDescensos());
		cg.setPremio(dcg.getPremio());
		cg.setCantidadTorneos(dcg.getCantidadTorneos());
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
