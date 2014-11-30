package partidos;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import equipos.Equipo;

@Entity
@PrimaryKeyJoinColumn(name="partidoId")
public class PartidoIntegracion extends Partido {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public PartidoIntegracion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public PartidoIntegracion(Equipo local, Equipo visitante, Calendar fecha) {
		super(local, visitante, fecha);
		// TODO Auto-generated constructor stub
	}

	
}
