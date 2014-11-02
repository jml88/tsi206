package partidos;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import campeonato.Torneo;
import equipos.Equipo;

@Entity
@PrimaryKeyJoinColumn(name="partidoId")
public class PartidoTorneo extends Partido {
	
	//public static final String nombreTabla = "PARTIDOSTORNEO";
	
	
	@Column
	private int fechaNro;
	
	@ManyToOne
	private Torneo torneo;

	public PartidoTorneo() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PartidoTorneo(Equipo local, Equipo visitante, Calendar fecha) {
		super(local, visitante, fecha);
		// TODO Auto-generated constructor stub
	}
	

	public PartidoTorneo(Equipo local, Equipo visitante, Calendar fecha,
			int fechaNro, Torneo torneo) {
		super(local, visitante, fecha);
		this.fechaNro = fechaNro;
		this.torneo = torneo;
	}

	public int getFechaNro() {
		return fechaNro;
	}

	public void setFechaNro(int fechaNro) {
		this.fechaNro = fechaNro;
	}

	public Torneo getTorneo() {
		return torneo;
	}

	public void setTorneo(Torneo torneo) {
		this.torneo = torneo;
	}

	

}
