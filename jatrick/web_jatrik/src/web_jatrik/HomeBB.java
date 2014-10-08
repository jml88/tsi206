package web_jatrik;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import comunicacion.Comunicacion;
import datatypes.DatosEquipo;
import datatypes.DatosJugador;

@Named("homeBB")
@RequestScoped
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
		String result = "";
		try {
			this.otrosEquipos = Comunicacion.getInstance().getIEquipoControlador().obtenerEquiposSistema();
			this.otrosEquipos.remove(equipo);
			result = "jugarAmistoso";
		} catch (NamingException e) {
			e.printStackTrace();
		}
		return result;
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
