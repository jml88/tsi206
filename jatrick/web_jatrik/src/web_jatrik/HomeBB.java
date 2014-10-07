package web_jatrik;

import interfaces.IEquipoControlador;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import datatypes.DatosEquipo;
import datatypes.DatosJugador;

@Named("homeBB")
@RequestScoped
public class HomeBB implements Serializable {
	
	@Inject
	private IEquipoControlador iec;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DatosEquipo equipo;
	private Set<DatosJugador> jugadores;
	private Set<DatosEquipo> otrosEquipos;
	
	//CONSTRUCTOR
	public HomeBB() {
		try {
			this.jugadores = iec.obtenerJugadoresEquipo(Comunicacion.getInstance().getSesion().getDatosManager().getCodEquipo());
			this.equipo = iec.obtenerEquipo(Comunicacion.getInstance().getSesion().getDatosManager().getCodEquipo());
			this.otrosEquipos = new HashSet<DatosEquipo>();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
    }
	
	//NAVEGACIONES
	public String jugarAmistoso() {
		this.otrosEquipos = iec.obtenerEquiposSistema();
		this.otrosEquipos.remove(equipo);
		return "jugarAmistoso";
	}
	
	public String verAlineacion() {
		return "verAlineacion";
	}

	//GETS Y SETS
	public DatosEquipo getEquipo() {
		return equipo;
	}

	public void setEquipo(DatosEquipo equipo) {
		this.equipo = equipo;
	}

	public Set<DatosJugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(Set<DatosJugador> jugadores) {
		this.jugadores = jugadores;
	}
	
	public Set<DatosEquipo> getOtrosEquipos() {
		return otrosEquipos;
	}

	public void setOtrosEquipos(Set<DatosEquipo> otrosEquipos) {
		this.otrosEquipos = otrosEquipos;
	}
}
