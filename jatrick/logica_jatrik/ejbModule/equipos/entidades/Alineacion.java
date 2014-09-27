package equipos.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import jugadores.entidades.Jugador;

@Entity
@Table(name = Alineacion.nombreTabla)
public class Alineacion {

	public static final String nombreTabla = "ALINEACIONES";

	@Id
	@Column(name = "CODALINEACION")
	private int codigo;

	//TODO averiguar si se puede limitar la cantidad de 
	@OneToMany
	private Set<Jugador> delanteros;
	
	@OneToMany
	private Set<Jugador> mediocampistas;
	
	@OneToMany
	private Set<Jugador> defensas;
	
	@OneToOne
	private Jugador golero;

	/* suplentes por lesion */
	@OneToOne
	private Jugador lesionDelantero;
	
	@OneToOne
	private Jugador lesionMediocampistas;
	
	@OneToOne
	private Jugador lesionDefensas;
	
	@OneToOne
	private Jugador lesionGolero;

	/* suplentes en general */
	@OneToMany
	private Set<Jugador> suplentes;
}
