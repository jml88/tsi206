package web_jatrik;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import comunicacion.Comunicacion;
import excepciones.CapitalNegativo;
import excepciones.NoExisteEquipoExcepcion;
import excepciones.NoExisteJugadorALaVenta;
import excepciones.NoExisteJugadorExcepcion;
import excepciones.NoSePuedeComprarException;
import excepciones.YaExisteJugadorALaVenta;
import jugadores.Jugador;

@Named("jugadorBB")
@ViewScoped
public class JugadorBB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Jugador jugador;
	
	private int precio;
	
	@Inject
	private SessionBB session;

	
	@PostConstruct
	public void init(){
		FacesContext faces = FacesContext.getCurrentInstance();
		jugador = (Jugador)faces.getExternalContext().getApplicationMap().get("jugador");
		
		
	}
	
	public String comprarJugador(){
		try {
			int codigoEquipo = session.getDatosManager().getCodEquipo();
			Comunicacion.getInstance().getIMercadoDePasesControlador().comprarJugador(codigoEquipo, jugador.getCodigo());
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Excelente compra!!!") );
	        
		} catch (NoExisteEquipoExcepcion e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		} catch (NoExisteJugadorExcepcion e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		} catch (NoExisteJugadorALaVenta e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		} catch (CapitalNegativo e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		} catch (NoSePuedeComprarException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		} catch (NamingException e) {
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}
		return "";
	}
	
	
	public String ponerEnVentaJugador(){
		int codigoEquipo = session.getDatosManager().getCodEquipo();
		try {
			Comunicacion.getInstance().getIMercadoDePasesControlador().ponerJugadorEnVenta(codigoEquipo, jugador.getCodigo(), precio);
			
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Agregado!!!") );
	        
		} catch (NoExisteEquipoExcepcion | NoExisteJugadorExcepcion
				| YaExisteJugadorALaVenta | NamingException e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}
		return "";
	}
	
	public String cancelarVentaJugador(){
		int codigoEquipo = session.getDatosManager().getCodEquipo();
		try {
			Comunicacion.getInstance().getIMercadoDePasesControlador().cancelarJugadorPuestoEnVenta(codigoEquipo, jugador.getCodigo());
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("El jugador no esta a la venta!!!") );
	        
		} catch (NoExisteEquipoExcepcion | NoExisteJugadorExcepcion
				| NoExisteJugadorALaVenta | NamingException e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}
		return "";
	}
	
	public Jugador getJugador() {
		return jugador;
	}

	public void setJugador(Jugador jugador) {
		this.jugador = jugador;
	}
	
	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
		this.precio = precio;
	}

	
	
	
	

}
