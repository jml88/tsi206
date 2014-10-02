package datatypes;

import java.io.Serializable;
import java.util.Set;

public class DatosEquipo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Set<DatosJugador> plantel;
	
	public DatosEquipo(Set<DatosJugador> plantel) {
		super();
		this.plantel = plantel;
	}

	public Set<DatosJugador> getPlantel() {
		return plantel;
	}

	public void setPlantel(Set<DatosJugador> plantel) {
		this.plantel = plantel;
	}
}

