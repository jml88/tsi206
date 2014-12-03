package equipos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

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

	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name="ALINEACION_DELANTEROS",
            joinColumns = {@JoinColumn( name="CODALINEACION")},
            inverseJoinColumns = {@JoinColumn( name="CODJUGADOR")}
    )
	private Set<Jugador> delanteros;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name="ALINEACION_MEDIOCAMPISTAS",
                    joinColumns = {@JoinColumn( name="CODALINEACION")},
                    inverseJoinColumns = {@JoinColumn( name="CODJUGADOR")}
    )
	private Set<Jugador> mediocampistas;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name="ALINEACION_DEFENSAS",
                    joinColumns = {@JoinColumn( name="CODALINEACION")},
                    inverseJoinColumns = {@JoinColumn( name="CODJUGADOR")}
    )
	private Set<Jugador> defensas;
	
	
	@OneToOne(fetch = FetchType.LAZY)
	private Jugador golero;

	/* suplentes por lesion */
	@OneToOne(fetch = FetchType.LAZY)
	private Jugador lesionDelantero;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Jugador lesionMediocampistas;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Jugador lesionDefensas;
	
	@OneToOne(fetch = FetchType.LAZY)
	private Jugador lesionGolero;

	/* suplentes en general */
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name="ALINEACION_SUPLENTES",
                    joinColumns = {@JoinColumn( name="CODALINEACION")},
                    inverseJoinColumns = {@JoinColumn( name="CODJUGADOR")}
    )
	private Set<Jugador> suplentes;
	
	public Alineacion() {
		super();
		this.delanteros = new HashSet<Jugador>();
		this.defensas=  new HashSet<Jugador>();
		this.mediocampistas =  new HashSet<Jugador>();
		this.suplentes =  new HashSet<Jugador>();
	}
	
	public Alineacion(Alineacion a) {
		this.alineacionDefecto = a.isAlineacionDefecto();
		this.delanteros =  new HashSet<Jugador>();
		this.defensas=  new HashSet<Jugador>();
		this.mediocampistas =  new HashSet<Jugador>();
		this.suplentes =  new HashSet<Jugador>();
//		this.lesionDefensas = 
//		this.lesionDelantero = 
//		this.lesionGolero = 
//		this.lesionMediocampistas = 
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
		this.delanteros = new HashSet<Jugador>(delanteros);
		this.mediocampistas = new HashSet<Jugador>(mediocampistas);
		this.defensas = new HashSet<Jugador>(defensas);
		this.golero = golero;
		this.lesionDelantero = lesionDelantero;
		this.lesionMediocampistas = lesionMediocampistas;
		this.lesionDefensas = lesionDefensas;
		this.lesionGolero = lesionGolero;
		this.suplentes = new HashSet<Jugador>(suplentes);
		this.alineacionDefecto = defecto;
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

	public boolean isAlineacionDefecto() {
		return alineacionDefecto;
	}

	public void setAlineacionDefecto(boolean alineacionDefecto) {
		this.alineacionDefecto = alineacionDefecto;
	}
	
	public List<Jugador> getSuplentesSet() {
		List<Jugador> list = new LinkedList<Jugador>(suplentes);
		return list;
	}
	
	public List<Jugador> getDelanterosSet() {
		List<Jugador> list = new LinkedList<Jugador>(delanteros);
		return list;
	}
	
	public List<Jugador> getMediocampistasSet() {
		List<Jugador> list = new LinkedList<Jugador>(mediocampistas);
		return list;
	}
	
	public List<Jugador> getDefensasSet() {
		List<Jugador> list = new LinkedList<Jugador>(defensas);
		return list;
	}
	
	public void setDefensasSet(List<Jugador> defensas) {
		this.defensas = new HashSet<Jugador>(defensas);
	}
	
	public void setMediocampistasSet(List<Jugador> mediocampistas) {
		this.mediocampistas = new HashSet<Jugador>(mediocampistas);
	}
	
	public void setDelanterosSet(List<Jugador> delanteros) {
		this.delanteros= new HashSet<Jugador>(delanteros);
	}
	
}
