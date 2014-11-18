package equipos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import jugadores.Jugador;

@Entity
@Table(name = Alineacion.nombreTabla)
public class Alineacion implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	public static final String nombreTabla = "ALINEACIONES";

	@Id
	@Column(name = "CODALINEACION")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	private boolean alineacionDefecto;

	//TODO averiguar si se puede limitar la cantidad de 
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name="ALINEACION_DELANTEROS",
            joinColumns = {@JoinColumn( name="CODALINEACION")},
            inverseJoinColumns = {@JoinColumn( name="CODJUGADOR")}
    )
	private List<Jugador> delanteros;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name="ALINEACION_MEDIOCAMPISTAS",
                    joinColumns = {@JoinColumn( name="CODALINEACION")},
                    inverseJoinColumns = {@JoinColumn( name="CODJUGADOR")}
    )
	private List<Jugador> mediocampistas;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name="ALINEACION_DEFENSAS",
                    joinColumns = {@JoinColumn( name="CODALINEACION")},
                    inverseJoinColumns = {@JoinColumn( name="CODJUGADOR")}
    )
	private List<Jugador> defensas;
	
	
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
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name="ALINEACION_SUPLENTES",
                    joinColumns = {@JoinColumn( name="CODALINEACION")},
                    inverseJoinColumns = {@JoinColumn( name="CODJUGADOR")}
    )
	private List<Jugador> suplentes;
	
	public Alineacion() {
		super();
		this.delanteros = new ArrayList<Jugador>();
		this.defensas= new ArrayList<Jugador>();
		this.mediocampistas = new ArrayList<Jugador>();
		this.suplentes = new ArrayList<Jugador>();
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
	public Alineacion(List<Jugador> delanteros, List<Jugador> mediocampistas,
			List<Jugador> defensas, Jugador golero, Jugador lesionDelantero,
			Jugador lesionMediocampistas, Jugador lesionDefensas,
			Jugador lesionGolero, List<Jugador> suplentes, boolean defecto) {
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
		this.alineacionDefecto = defecto;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public List<Jugador> getDelanteros() {
		return delanteros;
	}

	public void setDelanteros(List<Jugador> delanteros) {
		this.delanteros = delanteros;
	}

	public List<Jugador> getMediocampistas() {
		return mediocampistas;
	}

	public void setMediocampistas(List<Jugador> mediocampistas) {
		this.mediocampistas = mediocampistas;
	}

	public List<Jugador> getDefensas() {
		return defensas;
	}

	public void setDefensas(List<Jugador> defensas) {
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

	public List<Jugador> getSuplentes() {
		return suplentes;
	}

	public void setSuplentes(List<Jugador> suplentes) {
		this.suplentes = suplentes;
	}

	public boolean isAlineacionDefecto() {
		return alineacionDefecto;
	}

	public void setAlineacionDefecto(boolean alineacionDefecto) {
		this.alineacionDefecto = alineacionDefecto;
	}
	
}
