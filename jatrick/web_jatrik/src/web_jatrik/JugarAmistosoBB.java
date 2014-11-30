package web_jatrik;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import partidos.Amistoso;
import comunicacion.Comunicacion;
import datatypes.DatosEquipo;

@Named("jugarAmistosoBB")
@ViewScoped
public class JugarAmistosoBB implements Serializable {
	
	@Inject
	SessionBB sesion;
	
	private List<Amistoso> amistosos;

	private Amistoso amistosoSeleccionado;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JugarAmistosoBB() {
		super();
		this.amistosos = new LinkedList<Amistoso>();
	}
	
	@PostConstruct
	public void init() {
		try {
			this.amistosos = Comunicacion.getInstance().getIPartidoControlador().obtenerAmistososAConcretar(sesion.getDatosManager().getCodEquipo());
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public String volver() {
		return "volver";
	}
	
	public String cancelar(){
		String result = "/webPages/partidos/jugarAmistoso.xhtml";
		try {
			Comunicacion.getInstance().getIPartidoControlador().cancelarAmistoso(amistosoSeleccionado.getCodigo());
			//GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute)
//			GregorianCalendar fecha = new GregorianCalendar(2014, Calendar.OCTOBER, 20, 22, 58);
//			Comunicacion.getInstance().getIPartidoControlador().crearPartidoAmistoso(sesion.getDatosManager().getCodEquipo(),
//					this.equipoSeleccionado.getCodigo(), fecha, 10);
//			result = "/webPages/home/home.xhtml?faces-redirect=true";
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return result;
	}
	
	public String aceptar() {
		String result = "/webPages/partidos/jugarAmistoso.xhtml";
		try {
			Comunicacion.getInstance().getIPartidoControlador().aceptarAmistoso(amistosoSeleccionado.getCodigo());
			//GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute)
//			GregorianCalendar fecha = new GregorianCalendar(2014, Calendar.OCTOBER, 20, 22, 58);
//			Comunicacion.getInstance().getIPartidoControlador().crearPartidoAmistoso(sesion.getDatosManager().getCodEquipo(),
//					this.equipoSeleccionado.getCodigo(), fecha, 10);
//			result = "/webPages/home/home.xhtml?faces-redirect=true";
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public List<Amistoso> getAmistosos() {
		return amistosos;
	}

	public void setAmistosos(List<Amistoso> amistosos) {
		this.amistosos = amistosos;
	}

	public Amistoso getAmistosoSeleccionado() {
		return amistosoSeleccionado;
	}

	public void setAmistosoSeleccionado(Amistoso amistosoSeleccionado) {
		this.amistosoSeleccionado = amistosoSeleccionado;
	}
	
}
