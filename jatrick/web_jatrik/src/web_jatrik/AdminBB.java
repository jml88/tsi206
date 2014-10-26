package web_jatrik;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.naming.NamingException;

import comunicacion.Comunicacion;
import datatypes.DatosConfiguracionGral;
import excepciones.NoExisteConfiguracionException;

@Named("adminBB")
@ViewScoped
public class AdminBB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DatosConfiguracionGral configGeneral;
	
	private String selection;
	
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
			Comunicacion.getInstance().getConfiguracionControlador().crearOModificarConfiguracion(configGeneral);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NoExisteConfiguracionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}



	public String getSelection() {
		return selection;
	}



	public void setSelection(String selection) {
		this.selection = selection;
	}
	
	
}
