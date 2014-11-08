package equipos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.jboss.resteasy.spi.touri.MappedBy;

import campeonato.Torneo;
import users.Manager;
import jugadores.Jugador;
import datatypes.DatosEquipo;
import datatypes.EnumEntrenamiento;

@Entity
@Table(name = Equipo.nombreTabla)
public class Equipo implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

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
	
	@OneToOne(cascade = {CascadeType.PERSIST})
	private Estadio estadio;
	
	@Column(name = "CODPAIS")
	private int codPais;
	
	@Column(name="TIPOENTRENAMIENTO")
	private EnumEntrenamiento tipoEntrenamiento;
	
	@Column
	private boolean bot;
	
	@Column
	private int capital;
	
	@Column
	private int publicidad;
	
	@Column
	private int socios;
	
	@ManyToMany
	private List<Torneo> torneos;
	
	@Column
	private int seguidores;
	
	@Column
	private int gastoJuveniles;
	
	@OneToOne(mappedBy= "equipo")
	private Manager usuario;
	
	public Equipo() {
		this.plantel = new HashSet<Jugador>();
		this.tipoEntrenamiento = EnumEntrenamiento.ATAQUE;
		this.torneos = new LinkedList<Torneo>();
	}
	
	public Equipo(DatosEquipo de, Alineacion alineacionDefecto) {
		super();
		this.nombre = de.getNombre();
		this.alineacionDefecto = alineacionDefecto;
		this.plantel = new HashSet<Jugador>();
		this.codPais = de.getCodPais();
		this.tipoEntrenamiento = EnumEntrenamiento.ATAQUE;
		this.torneos = new LinkedList<Torneo>();
	}

	public Equipo(String nombre, Set<Jugador> plantel, Alineacion alineacionDefecto) {
		super();
		this.nombre = nombre;
		this.plantel = plantel;
		this.alineacionDefecto = alineacionDefecto;
		this.tipoEntrenamiento = EnumEntrenamiento.ATAQUE;
		this.torneos = new LinkedList<Torneo>();
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
		return new DatosEquipo(this.codigo, this.nombre, this.codPais, this.bot);
	}

	public EnumEntrenamiento getTipoEntrenamiento() {
		return tipoEntrenamiento;
	}

	public void setTipoEntrenamiento(EnumEntrenamiento tipoEntrenamiento) {
		this.tipoEntrenamiento = tipoEntrenamiento;
	}

	public boolean isBot() {
		return bot;
	}

	public void setBot(boolean bot) {
		this.bot = bot;
	}

	public int getCapital() {
		return capital;
	}

	public void setCapital(int capital) {
		this.capital = capital;
	}

	public Manager getUsuario() {
		return usuario;
	}

	public void setUsuario(Manager usuario) {
		this.usuario = usuario;
	}

	

	public List<Torneo> getTorneos() {
		return torneos;
	}

	public void setTorneos(List<Torneo> torneos) {
		this.torneos = torneos;
	}

	public int getPublicidad() {
		return publicidad;
	}

	public void setPublicidad(int publicidad) {
		this.publicidad = publicidad;
	}

	public int getSocios() {
		return socios;
	}

	public void setSocios(int socios) {
		this.socios = socios;
	}

	public int getSeguidores() {
		return seguidores;
	}

	public void setSeguidores(int seguidores) {
		this.seguidores = seguidores;
	}

	public int getGastoJuveniles() {
		return gastoJuveniles;
	}

	public void setGastoJuveniles(int gastoJuveniles) {
		this.gastoJuveniles = gastoJuveniles;
	}

	
	
}
