package web_jatrik;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import comunicacion.Comunicacion;

import datatypes.DatosEquipo;
import datatypes.DatosJugador;

@Named("homeBB")
@ViewScoped
public class HomeBB implements Serializable {
	
	@Inject
	SessionBB sesion;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int codEquipo;
	private DatosEquipo equipo;
	private Set<DatosJugador> jugadores;
	private Set<DatosEquipo> otrosEquipos;
	
	public HomeBB() {
		super();
    }
	
	@PostConstruct
	public void init() {
		try {
			this.codEquipo = this.sesion.getDatosManager().getCodEquipo();
			this.jugadores = Comunicacion.getInstance().getIEquipoControlador().obtenerJugadoresEquipo(this.codEquipo);
			this.equipo = Comunicacion.getInstance().getIEquipoControlador().obtenerEquipo(this.codEquipo);
			this.otrosEquipos = new HashSet<DatosEquipo>();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
	
	//NAVEGACIONES
	public String jugarAmistoso() {
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

	public int getCodEquipo() {
		return codEquipo;
	}

	public void setCodEquipo(int codEquipo) {
		this.codEquipo = codEquipo;
	}
}
