package partidos;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

import equipos.Equipo;

@Entity
@DiscriminatorValue("T")
public class PartidoTorneo extends Partido {
	
	//public static final String nombreTabla = "PARTIDOSTORNEO";
	
	
	@Column
	private int fechaNro;

	public PartidoTorneo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PartidoTorneo(Equipo local, Equipo visitante, Calendar fecha) {
		super(local, visitante, fecha);
		// TODO Auto-generated constructor stub
	}
	

	public PartidoTorneo(Equipo local, Equipo visitante, Calendar fecha,
			int fechaNro) {
		super(local, visitante, fecha);
		this.fechaNro = fechaNro;
	}

	public int getFechaNro() {
		return fechaNro;
	}

	public void setFechaNro(int fechaNro) {
		this.fechaNro = fechaNro;
	}

	

}
