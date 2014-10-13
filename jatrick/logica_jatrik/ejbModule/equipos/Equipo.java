package equipos;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import jugadores.Jugador;
import datatypes.DatosEquipo;

@Entity
@Table(name = Equipo.nombreTabla)
public class Equipo {

	public static final String nombreTabla = "EQUIPOS";

	@Id
	@Column(name = "CODEQUIPO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	@Column(name = "NOMBRE")
	private String nombre;
	
	@OneToMany(cascade = {CascadeType.PERSIST})
	private Set<Jugador> plantel;
	
	@OneToOne
	private Alineacion alineacionDefecto;
	
	@OneToOne
	private Estadio estadio;
	
	@Column(name = "CODPAIS")
	private int codPais;
	
	public Equipo() {
		this.plantel = new HashSet<Jugador>();
	}
	
	public Equipo(DatosEquipo de, Alineacion alineacionDefecto) {
		super();
		this.nombre = de.getNombre();
		this.alineacionDefecto = alineacionDefecto;
		this.plantel = new HashSet<Jugador>();
		this.codPais = de.getCodPais();
	}

	public Equipo(String nombre, Set<Jugador> plantel, Alineacion alineacionDefecto) {
		super();
		this.nombre = nombre;
		this.plantel = plantel;
		this.alineacionDefecto = alineacionDefecto;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	
	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set<Jugador> getPlantel() {
		return plantel;
	}

	public void setPlantel(Set<Jugador> plantel) {
		this.plantel = plantel;
	}

	public Alineacion getAlineacionDefecto() {
		return alineacionDefecto;
	}

	public void setAlineacionDefecto(Alineacion alineacionDefecto) {
		this.alineacionDefecto = alineacionDefecto;
	}
	
	public Estadio getEstadio() {
		return estadio;
	}

	public void setEstadio(Estadio estadio) {
		this.estadio = estadio;
	}

	public int getCodPais() {
		return codPais;
	}

	public void setCodPais(int codPais) {
		this.codPais = codPais;
	}

	public DatosEquipo getDatos() {
		return new DatosEquipo(this.codigo, this.nombre, this.codPais);
	}

}
