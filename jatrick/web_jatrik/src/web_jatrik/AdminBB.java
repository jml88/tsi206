package web_jatrik;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.naming.NamingException;

import comunicacion.Comunicacion;

import configuracionGral.ConfiguracionGral;
import configuracionGral.PeriodicoPartido;
import datatypes.EnumPeriodicoPartido;
import excepciones.NoExisteConfiguracionException;

@Named("adminBB")
@ViewScoped
public class AdminBB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private ConfiguracionGral configGeneral;
	
	private int selection;
	
	private int dia;
	
	private int hora;
	
	private int minuto;
	
	@PostConstruct
	public void init(){
		try {
			if(Comunicacion.getInstance().getConfiguracionControlador().getDatosConfiguracionGral() != null){
				setConfigGeneral(Comunicacion.getInstance().getConfiguracionControlador().getDatosConfiguracionGral());
				
				EnumPeriodicoPartido p = this.configGeneral.getPeriodicoPartido().getPeriodico();
				switch(p){
					
				case MINUTO:
					selection = 0;
					break;
				case HORA:
					selection = 1;
					break;
				case DIA:
					selection = 2;
					break;
				default:
					selection = 0;
					break;
				}
				
				dia = this.configGeneral.getPeriodicoPartido().getDia();
				hora = this.configGeneral.getPeriodicoPartido().getHora();
				minuto = this.configGeneral.getPeriodicoPartido().getMinuto();
				
			}else{
				
				selection = 9;
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	

	public ConfiguracionGral getConfigGeneral() {
		return configGeneral;
	}

	public void setConfigGeneral(ConfiguracionGral configGeneral) {
		this.configGeneral = configGeneral;
	}
	

	public String createOrModifiedConfigGralBasica(){
		String ret ="";
		try {
			EnumPeriodicoPartido p;
			switch(selection){
				
			case 0:
				p = EnumPeriodicoPartido.MINUTO;
				break;
			case 1:
				p = EnumPeriodicoPartido.HORA;
				break;
			case 2:
				p = EnumPeriodicoPartido.DIA;
				break;
			default:
				p = EnumPeriodicoPartido.MINUTO;
				break;
			}
			PeriodicoPartido periodico = new PeriodicoPartido(dia, hora, minuto,p);
			configGeneral.setPeriodicoPartido(periodico);
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


	
	public String crearPartidosYTorneos(){
		String ret ="";
		try {
			Comunicacion.getInstance().getCampeonatoControlador().crearCampeonato();
		} catch (NoExisteConfiguracionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ret;
	}

	public int getSelection() {
		return selection;
	}



	public void setSelection(int selection) {
		this.selection = selection;
	}



	public int getDia() {
		return dia;
	}



	public void setDia(int dia) {
		this.dia = dia;
	}



	public int getHora() {
		return hora;
	}



	public void setHora(int hora) {
		this.hora = hora;
	}



	public int getMinuto() {
		return minuto;
	}



	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}
	
	
}
