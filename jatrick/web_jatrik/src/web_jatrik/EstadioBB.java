package web_jatrik;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import equipos.Estadio;
import excepciones.CapitalNegativo;
import excepciones.NoExisteEquipoExcepcion;
import excepciones.NoSePuedeAgrandarEstadio;

@Named("estadioBB")
@ViewScoped
public class EstadioBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Estadio estadio;
	
	private int codigoEquipo;
	
	private int valorAgrandar;
	
	@Inject
	private SessionBB session;
	
	@PostConstruct
	public void init(){
		codigoEquipo = session.getDatosManager().getCodEquipo();
		
		try {
			estadio = comunicacion.Comunicacion.getInstance().getIEstadioControlador().estadioDeEquipo(codigoEquipo);
			valorAgrandar = comunicacion.Comunicacion.getInstance().getConfiguracionControlador().getConfiguracion().getCuestaAgrandarEstadio();
		} catch (NoExisteEquipoExcepcion | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}
	}
	
	public String agrandarEstadio(){
		try {
			comunicacion.Comunicacion.getInstance().getIEstadioControlador().mejorarEstadioEquipo(codigoEquipo);
			estadio = comunicacion.Comunicacion.getInstance().getIEstadioControlador().estadioDeEquipo(codigoEquipo);
		} catch (NoExisteEquipoExcepcion | CapitalNegativo
				| NoSePuedeAgrandarEstadio | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}
		
		return "";
	}

	public Estadio getEstadio() {
		return estadio;
	}

	public void setEstadio(Estadio estadio) {
		this.estadio = estadio;
	}

	public int getValorAgrandar() {
		return valorAgrandar;
	}

	public void setValorAgrandar(int valorAgrandar) {
		this.valorAgrandar = valorAgrandar;
	}
}
