package web_jatrik;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import org.primefaces.context.RequestContext;

import jugadores.Jugador;
import comunicacion.Comunicacion;
import equipos.Equipo;
import excepciones.CapitalNegativo;
import excepciones.NoExisteEquipoExcepcion;
import excepciones.NoExisteJugador;
import excepciones.NoExisteJugadorALaVenta;
import excepciones.NoExisteJugadorExcepcion;
import excepciones.NoSePuedeComprarException;
import excepciones.YaExisteJugadorALaVenta;

@Named("juvenilBB")
@ViewScoped
public class JuvenilBB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Jugador jugador;
	
	private Equipo equipo;
	
	@Inject
	private SessionBB session;

	
	@PostConstruct
	public void init(){
		try {
			equipo = Comunicacion.getInstance().getIEquipoControlador().findEquipo(session.getDatosManager().getCodEquipo());
		} catch ( NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
    public String obtenerJuvenil(){
    	
    	try {
			jugador = Comunicacion.getInstance().getIEquipoControlador().obtenerJuvenil(session.getDatosManager().getCodEquipo());
			FacesContext faces = FacesContext.getCurrentInstance();
			faces.getExternalContext().getApplicationMap().put("jugador", jugador);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
    	return "/webPages/jugadores/detallesJugador.xhtml?faces-redirect=true";
    }

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
}
