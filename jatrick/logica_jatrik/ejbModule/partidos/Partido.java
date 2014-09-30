package partidos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import equipos.Equipo;

@Entity
@Table(name = Partido.nombreTabla)
public class Partido {

	public static final String nombreTabla = "PARTIDOS";

	@Id
	@Column(name = "CODPARTIDO")
	private int codigo;

	@OneToOne
	private Equipo local;
	
	@OneToOne
	private Equipo visitante;

}
