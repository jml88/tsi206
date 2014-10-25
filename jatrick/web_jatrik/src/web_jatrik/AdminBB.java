package web_jatrik;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.naming.NamingException;

import comunicacion.Comunicacion;
import datatypes.DatosConfiguracionGral;

@Named("adminBB")
@ViewScoped
public class AdminBB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DatosConfiguracionGral configGeneral;
	
	@PostConstruct
	public void init(){
		try {
			if(Comunicacion.getInstance().getConfiguracionControlador().getDatosConfiguracionGral() != null){
				setConfigGeneral(Comunicacion.getInstance().getConfiguracionControlador().getDatosConfiguracionGral());
			}else{
				configGeneral = new DatosConfiguracionGral();
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public DatosConfiguracionGral getConfigGeneral() {
		return configGeneral;
	}

	public void setConfigGeneral(DatosConfiguracionGral configGeneral) {
		this.configGeneral = configGeneral;
	}
	

	public String createOrModifiedConfigGralBasica(){
		String ret ="";
		try {
			Comunicacion.getInstance().getConfiguracionControlador().crearConfiguracionGral(configGeneral);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}
	
	
}
