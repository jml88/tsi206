package equipos;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import jugadores.Jugador;

@Entity
@Table(name = Equipo.nombreTabla)
public class Equipo {

	public static final String nombreTabla = "EQUIPOS";

	@Id
	@Column(name = "CODEQUIPO")
	private int codigo;

	private Set<Jugador> jugadores;
	private Alineacion alineacionDefecto;

	private Set<Alineacion> alineaciones;

}
