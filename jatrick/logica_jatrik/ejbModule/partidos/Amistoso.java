package partidos;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.PrimaryKeyJoinColumn;

import datatypes.EnumPartido;
import equipos.Equipo;

@Entity
@PrimaryKeyJoinColumn(name="partidoId")
public class Amistoso extends Partido {
	
	@Column
	private int cantidadReto;

	public Amistoso() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Amistoso(Equipo local, Equipo visitante, Calendar fecha, int cantidadReto) {
		super(local, visitante, fecha);
		this.cantidadReto = cantidadReto;
		this.setEstado(EnumPartido.POR_CONCRETAR);
		// TODO Auto-generated constructor stub
	}

	public int getCantidadReto() {
		return cantidadReto;
	}

	public void setCantidadReto(int cantidadReto) {
		this.cantidadReto = cantidadReto;
	}

	
}
