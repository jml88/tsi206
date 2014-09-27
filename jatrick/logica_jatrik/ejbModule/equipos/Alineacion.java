package equipos;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import jugadores.Jugador;

@Entity
@Table(name = Alineacion.nombreTabla)
public class Alineacion {

	public static final String nombreTabla = "ALINEACIONES";

	@Id
	@Column(name = "CODALINEACION")
	private int codigo;

	private Set<Jugador> delanteros;
	private Set<Jugador> mediocampistas;
	private Set<Jugador> defensas;
	private Jugador golero;

	/* suplentes por lesion */
	private Jugador lesionDelantero;
	private Jugador lesionMediocampistas;
	private Jugador lesionDefensas;
	private Jugador lesionGolero;

	/* suplentes en general */
	private Set<Jugador> delanterosSuplente;
	private Set<Jugador> mediocampistasSuplente;
	private Set<Jugador> defensasSuplente;
	private Set<Jugador> goleroSuplente;
}
