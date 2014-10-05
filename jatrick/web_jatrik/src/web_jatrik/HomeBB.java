package web_jatrik;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import datatypes.DatosEquipo;
import datatypes.DatosJugador;

@Named("homeBB")
@RequestScoped
public class HomeBB implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DatosEquipo equipo;
	private Set<DatosJugador> jugadores;
	private Set<DatosEquipo> otrosEquipos;
	
	//CONSTRUCTOR
	public HomeBB() {
		//aca se inicializan los datos necesarios para ver la pagina inicial
		//y en cada navegacion se obtienen los datos restantes
    }
	
	//NAVEGACIONES
	public String jugarAmistoso() {
		//FIXME agregar operacion para que obtenga otros equipos
		this.otrosEquipos = new HashSet<DatosEquipo>();
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
