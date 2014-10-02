package equipos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import jugadores.Jugador;

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
	
	public Alineacion() {
		super();
		this.delanteros = new HashSet<Jugador>();
		this.defensas= new HashSet<Jugador>();
		this.mediocampistas = new HashSet<Jugador>();
		this.suplentes = new HashSet<Jugador>();
	}

	/**
	 * @param delanteros
	 * @param mediocampistas
	 * @param defensas
	 * @param golero
	 * @param lesionDelantero
	 * @param lesionMediocampistas
	 * @param lesionDefensas
	 * @param lesionGolero
	 * @param suplentes
	 */
	public Alineacion(Set<Jugador> delanteros, Set<Jugador> mediocampistas,
			Set<Jugador> defensas, Jugador golero, Jugador lesionDelantero,
			Jugador lesionMediocampistas, Jugador lesionDefensas,
			Jugador lesionGolero, Set<Jugador> suplentes) {
		super();
		this.delanteros = delanteros;
		this.mediocampistas = mediocampistas;
		this.defensas = defensas;
		this.golero = golero;
		this.lesionDelantero = lesionDelantero;
		this.lesionMediocampistas = lesionMediocampistas;
		this.lesionDefensas = lesionDefensas;
		this.lesionGolero = lesionGolero;
		this.suplentes = suplentes;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Set<Jugador> getDelanteros() {
		return delanteros;
	}

	public void setDelanteros(Set<Jugador> delanteros) {
		this.delanteros = delanteros;
	}

	public Set<Jugador> getMediocampistas() {
		return mediocampistas;
	}

	public void setMediocampistas(Set<Jugador> mediocampistas) {
		this.mediocampistas = mediocampistas;
	}

	public Set<Jugador> getDefensas() {
		return defensas;
	}

	public void setDefensas(Set<Jugador> defensas) {
		this.defensas = defensas;
	}

	public Jugador getGolero() {
		return golero;
	}

	public void setGolero(Jugador golero) {
		this.golero = golero;
	}

	public Jugador getLesionDelantero() {
		return lesionDelantero;
	}

	public void setLesionDelantero(Jugador lesionDelantero) {
		this.lesionDelantero = lesionDelantero;
	}

	public Jugador getLesionMediocampistas() {
		return lesionMediocampistas;
	}

	public void setLesionMediocampistas(Jugador lesionMediocampistas) {
		this.lesionMediocampistas = lesionMediocampistas;
	}

	public Jugador getLesionDefensas() {
		return lesionDefensas;
	}

	public void setLesionDefensas(Jugador lesionDefensas) {
		this.lesionDefensas = lesionDefensas;
	}

	public Jugador getLesionGolero() {
		return lesionGolero;
	}

	public void setLesionGolero(Jugador lesionGolero) {
		this.lesionGolero = lesionGolero;
	}

	public Set<Jugador> getSuplentes() {
		return suplentes;
	}

	public void setSuplentes(Set<Jugador> suplentes) {
		this.suplentes = suplentes;
	}
}
