package partidos;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import equipos.Equipo;

@Entity
@PrimaryKeyJoinColumn(name="partidoId")
public class Amistoso extends Partido {

	public Amistoso() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Amistoso(Equipo local, Equipo visitante, Calendar fecha) {
		super(local, visitante, fecha);
		// TODO Auto-generated constructor stub
	}

	
}
