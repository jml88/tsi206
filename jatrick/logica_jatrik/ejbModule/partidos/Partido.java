package partidos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import equipos.entidades.Equipo;

@Entity
@Table(name = Partido.nombreTabla)
public class Partido {

	public static final String nombreTabla = "PARTIDOS";

	@Id
	@Column(name = "CODPARTIDO")
	private int codigo;

	private Equipo local;
	private Equipo visitante;

}
