package web_jatrik;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import org.primefaces.context.RequestContext;

import jugadores.Jugador;
import comunicacion.Comunicacion;
import excepciones.CapitalNegativo;
import excepciones.NoExisteEquipoExcepcion;
import excepciones.NoExisteJugador;
import excepciones.NoExisteJugadorALaVenta;
import excepciones.NoExisteJugadorExcepcion;
import excepciones.NoSePuedeComprarException;
import excepciones.YaExisteJugadorALaVenta;

@Named("jugadorBB")
@ViewScoped
public class JugadorBB implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Jugador jugador;
	
	private int precio;
	
	int codigoEquipo;
	
	private boolean ponerEnVenta;
	
	private boolean puedeComprar;
	
	@Inject
	private SessionBB session;

	
	@PostConstruct
	public void init(){
		FacesContext faces = FacesContext.getCurrentInstance();
		jugador = (Jugador)faces.getExternalContext().getApplicationMap().get("jugador");
		int idEquipoJugador = jugador.getEquipo().getCodigo();
		codigoEquipo = session.getDatosManager().getCodEquipo();
		try {
			ponerEnVenta = Comunicacion.getInstance().getIMercadoDePasesControlador().puedePonerEnVentaJugador(idEquipoJugador, jugador.getCodigo());
			puedeComprar = Comunicacion.getInstance().getIMercadoDePasesControlador().puedeComprarJugador(idEquipoJugador, jugador.getCodigo());
		} catch (NoExisteJugador | NoExisteEquipoExcepcion
				| NoExisteJugadorALaVenta | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
	
	public String comprarJugador(){
		try {
			
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
	
	
	public String confirmarVenta(){
		int codigoEquipo = session.getDatosManager().getCodEquipo();
		try {
			Comunicacion.getInstance().getIMercadoDePasesControlador().ponerJugadorEnVenta(codigoEquipo, jugador.getCodigo(), precio);
			//RequestContext.getCurrentInstance().addCallbackParam("doneChangeParam", true);
			FacesContext context = FacesContext.getCurrentInstance();
	        context.addMessage(null, new FacesMessage("Agregado!!!") );
	        //RequestContext.getCurrentInstance().closeDialog("confirmarVenta");
		} catch (NoExisteEquipoExcepcion | NoExisteJugadorExcepcion
				| YaExisteJugadorALaVenta | NamingException e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}
		
		return "";
	}
	
	public void ponerEnVentaJugador(){
		Map<String, Object> options = new HashMap<String, Object>();
		options.put("modal", true);
		options.put("draggable", false);
		options.put("resizable", false);
		options.put("contentHeight", 100);
		options.put("closeOnEscape", true);
		
		RequestContext.getCurrentInstance().openDialog("confirmarVenta",options,null);
		
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

	public boolean isPonerEnVenta() {
		return ponerEnVenta;
	}

	public void setPonerEnVenta(boolean ponerEnVenta) {
		this.ponerEnVenta = ponerEnVenta;
	}

	public boolean isPuedeComprar() {
		return puedeComprar;
	}

	public void setPuedeComprar(boolean puedeComprar) {
		this.puedeComprar = puedeComprar;
	}

	
	
	
	

}
