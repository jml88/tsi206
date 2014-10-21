package web_jatrik;

import java.io.Serializable;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import comunicacion.Comunicacion;
import datatypes.DatosEquipo;

@Named("jugarAmistosoBB")
@ViewScoped
public class JugarAmistosoBB implements Serializable {
	
	@Inject
	SessionBB sesion;
	
	private Set<DatosEquipo> equipos;
	private DatosEquipo equipoSeleccionado;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JugarAmistosoBB() {
		super();
		this.equipos = new HashSet<DatosEquipo>();
	}
	
	@PostConstruct
	public void init() {
		try {
			this.equipos = Comunicacion.getInstance().getIEquipoControlador().obtenerEquiposSistema();
			DatosEquipo miEquipo = null;
			for (DatosEquipo de : this.equipos) {
				if (de.getCodigo() == sesion.getDatosManager().getCodEquipo()) {
					miEquipo = de;
					break;
				}
			}	
			this.equipos.remove(miEquipo);
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public String volver() {
		return "volver";
	}
	
	public String seleccionarEquipo() {
		String result = "/webPages/partidos/jugarAmistoso.xhtml";
		try {
			//GregorianCalendar(year, month, dayOfMonth, hourOfDay, minute)
			GregorianCalendar fecha = new GregorianCalendar(2014, Calendar.OCTOBER, 20, 22, 58);
			Comunicacion.getInstance().getIPartidoControlador().crearPartidoAmistoso(sesion.getDatosManager().getCodEquipo(),
					this.equipoSeleccionado.getCodigo(), fecha);
			result = "/webPages/home/home.xhtml?faces-redirect=true";
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return result;
	}

	public Set<DatosEquipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(Set<DatosEquipo> equipos) {
		this.equipos = equipos;
	}

	public DatosEquipo getEquipoSeleccionado() {
		return equipoSeleccionado;
	}

	public void setEquipoSeleccionado(DatosEquipo equipoSeleccionado) {
		this.equipoSeleccionado = equipoSeleccionado;
	}
	
	
}
