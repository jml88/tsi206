package web_jatrik;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import org.primefaces.event.SelectEvent;

import comunicacion.Comunicacion;
import excepciones.NoExisteEquipoExcepcion;
import jugadores.Jugador;

@Named("jugadoresBB")
@ViewScoped
public class JugadoresBB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Jugador> jugadoresLista;
	
	private Jugador jugador;

	@Inject
	private SessionBB session;
	
		
	@PostConstruct
	public void init(){
		int codigoEquipo = session.getDatosManager().getCodEquipo();
		try {
			jugadoresLista = (List<Jugador>)Comunicacion.getInstance().getIJugadorControlador().listarJugador(codigoEquipo);
		} catch (NoExisteEquipoExcepcion | NamingException e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}
		
	}
	
	public void setSelected(List<Jugador> jugadores) {
        this.jugadoresLista = jugadores;
    }
	
	public String verDetallesJugador() {
		FacesContext faces = FacesContext.getCurrentInstance();
		faces.getExternalContext().getApplicationMap().put("jugador", jugador);
		ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		configurableNavigationHandler.performNavigation("/webPages/jugadores/detallesJugador.xhtml?faces-redirect=true");
		return "/webPages/jugadores/detallesJugador.xhtml?faces-redirect=true";
    }
	
	public List<Jugador> getJugadoresLista() {
		return jugadoresLista;
	}

	public void setJugadoresLista(List<Jugador> jugadoresLista) {
		this.jugadoresLista = jugadoresLista;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}

	
	
	
	

}
