package web_jatrik;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import jugadores.Jugador;
import comunicacion.Comunicacion;
import equipos.Equipo;
import excepciones.NoExisteEquipoExcepcion;

@Named("jugadoresBB")
@ViewScoped
public class JugadoresBB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Jugador> jugadoresLista;
	
	private Jugador jugador;
	
	private String mensaje;
	
	private int cantidadAmisto;
	
	private Date fechaAmistoso;
	
	private Integer codEquipoRetado;
	
	private String asunto;
	
	private List<Integer[]> ligasGanadas;
	
	private Integer copasGanadas;
	
	private Integer codigoEquipo;
	
	private String nombreEquipo;

	@Inject
	private SessionBB session;
	
	
	public void parametros(){
		codigoEquipo = codEquipoRetado;
		if (codEquipoRetado == null){
			codigoEquipo = session.getDatosManager().getCodEquipo();
			
		}
		
		
		
		try {
			nombreEquipo = Comunicacion.getInstance().getIEquipoControlador().findEquipo(codigoEquipo).getNombre();
			jugadoresLista = Comunicacion.getInstance().getIJugadorControlador().listarJugador(codigoEquipo);
			Equipo equipo = Comunicacion.getInstance().getIEquipoControlador().findEquipo(codigoEquipo);
			ligasGanadas = new LinkedList<Integer[]>();
			copasGanadas = equipo.getCopasGanadas();

			for (Map.Entry<Integer, Integer> entry : equipo.getLigasGanadas().entrySet())
			{
				Integer[] i =new Integer[2];
				i[0] = entry.getKey();
				i[1] = entry.getValue();
				ligasGanadas.add(i);
			}


			
		} catch (NoExisteEquipoExcepcion | NamingException e) {
			// TODO Auto-generated catch block
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}
	}
	
		
	@PostConstruct
	public void init(){
		if (codEquipoRetado == null){
			codigoEquipo = session.getDatosManager().getCodEquipo();
		}
		
		
		try {
			jugadoresLista = Comunicacion.getInstance().getIJugadorControlador().listarJugador(codigoEquipo);
			Equipo equipo = Comunicacion.getInstance().getIEquipoControlador().findEquipo(codigoEquipo);
			ligasGanadas = new LinkedList<Integer[]>();
			copasGanadas = equipo.getCopasGanadas();

			for (Map.Entry<Integer, Integer> entry : equipo.getLigasGanadas().entrySet())
			{
				Integer[] i =new Integer[2];
				i[0] = entry.getKey();
				i[1] = entry.getValue();
				ligasGanadas.add(i);
			}


			
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
	
	public String retarAmisto(){
		try {
			Comunicacion.getInstance().getIPartidoControlador().retarAmistoso(session.getDatosManager().getCodEquipo(),codEquipoRetado,fechaAmistoso,cantidadAmisto);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public String enviarMensaje(){
		try {
			Comunicacion.getInstance().getIEquipoControlador().crearMensaje(asunto, mensaje, Calendar.getInstance().getTime(), session.getDatosManager().getCodEquipo(), codEquipoRetado);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
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

	public int getCantidadAmisto() {
		return cantidadAmisto;
	}

	public void setCantidadAmisto(int cantidadAmisto) {
		this.cantidadAmisto = cantidadAmisto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Date getFechaAmistoso() {
		return fechaAmistoso;
	}

	public void setFechaAmistoso(Date fechaAmistoso) {
		this.fechaAmistoso = fechaAmistoso;
	}

	public String getAsunto() {
		return asunto;
	}

	public void setAsunto(String asunto) {
		this.asunto = asunto;
	}

	public List<Integer[]> getLigasGanadas() {
		return ligasGanadas;
	}

	public void setLigasGanadas(List<Integer[]> ligasGanadas) {
		this.ligasGanadas = ligasGanadas;
	}

	public Integer getCopasGanadas() {
		return copasGanadas;
	}

	public void setCopasGanadas(Integer copasGanadas) {
		this.copasGanadas = copasGanadas;
	}

	public Integer getCodEquipoRetado() {
		return codEquipoRetado;
	}

	public void setCodEquipoRetado(Integer codEquipoRetado) {
		this.codEquipoRetado = codEquipoRetado;
	}


	public String getNombreEquipo() {
		return nombreEquipo;
	}


	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}
	

}
