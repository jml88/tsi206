package equipos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.MapKeyColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import jugadores.Jugador;
import users.Manager;
import campeonato.Torneo;
import datatypes.DatosEquipo;
import datatypes.EnumEntrenamiento;

@Entity
@Table(name = Equipo.nombreTabla)
public class Equipo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String nombreTabla = "EQUIPOS";

	@Id
	@Column(name = "CODEQUIPO")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int codigo;

	@Column
	private int codigoIntegracion;

	@Column(name = "NOMBRE")
	private String nombre;
	
	@Column
	private boolean juvenilFecha;

	@OneToMany(cascade = { CascadeType.PERSIST }, fetch = FetchType.EAGER)
	private Set<Jugador> plantel;

	@OneToOne
	private Alineacion alineacionDefecto;

	@OneToOne(cascade = { CascadeType.PERSIST })
	private Estadio estadio;

	@Column(name = "CODPAIS")
	private int codPais;

	@Column(name = "TIPOENTRENAMIENTO")
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

	@Column
	private int ranking;

	@ElementCollection(fetch = FetchType.EAGER)
	@MapKeyColumn(name = "name")
	private Map<Integer, Integer> ligasGanadas;

	@Column
	private int copasGanadas;

	@Column
	private boolean equipoIntegracion;

	@OneToOne(mappedBy = "equipo")
	private Manager usuario;

	@Column
	private Double altura;

	public Equipo() {
		this.plantel = new HashSet<Jugador>();
		this.tipoEntrenamiento = EnumEntrenamiento.ATAQUE;
		this.torneos = new LinkedList<Torneo>();
		this.ranking = 0;
		this.ligasGanadas = new HashMap<Integer, Integer>();
		this.copasGanadas = 0;
		this.juvenilFecha = true;
	}

	public Equipo(DatosEquipo de, Alineacion alineacionDefecto) {
		super();
		this.nombre = de.getNombre();
		this.alineacionDefecto = alineacionDefecto;
		this.plantel = new HashSet<Jugador>();
		this.codPais = de.getCodPais();
		this.tipoEntrenamiento = EnumEntrenamiento.ATAQUE;
		this.torneos = new LinkedList<Torneo>();
		this.ranking = 0;
		this.altura = de.getAltura();
		this.ligasGanadas = new HashMap<Integer, Integer>();
		this.copasGanadas = 0;
		this.juvenilFecha = true;
	}

	public Equipo(String nombre, Set<Jugador> plantel,
			Alineacion alineacionDefecto) {
		super();
		this.nombre = nombre;
		this.plantel = plantel;
		this.alineacionDefecto = alineacionDefecto;
		this.tipoEntrenamiento = EnumEntrenamiento.ATAQUE;
		this.torneos = new LinkedList<Torneo>();
		this.ranking = 0;
		this.ligasGanadas = new HashMap<Integer, Integer>();
		this.copasGanadas = 0;
		this.juvenilFecha = true;
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
		return new DatosEquipo(this.codigo, this.nombre, this.codPais,
				this.bot, this.altura);
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

	public int getRanking() {
		return ranking;
	}

	public void setRanking(int ranking) {
		this.ranking = ranking;
	}

	public boolean isEquipoIntegracion() {
		return equipoIntegracion;
	}

	public void setEquipoIntegracion(boolean equipoIntegracion) {
		this.equipoIntegracion = equipoIntegracion;
	}

	public int getCodigoIntegracion() {
		return codigoIntegracion;
	}

	public void setCodigoIntegracion(int codigoIntegracion) {
		this.codigoIntegracion = codigoIntegracion;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public Map<Integer, Integer> getLigasGanadas() {
		return ligasGanadas;
	}

	public void setLigasGanadas(Map<Integer, Integer> ligasGanadas) {
		this.ligasGanadas = ligasGanadas;
	}

	
	public boolean isJuvenilFecha() {
		return juvenilFecha;
	}

	public void setJuvenilFecha(boolean juvenilFecha) {
		this.juvenilFecha = juvenilFecha;
	}

	public void agregarCampeonatoGanado(int nivel) {
		if (this.ligasGanadas.get(nivel) == null) {
			this.ligasGanadas.put(nivel, 1);
		} else {
			this.ligasGanadas.put(nivel, this.ligasGanadas.get(nivel) + 1);
		}
	}

	public int getCopasGanadas() {
		return copasGanadas;
	}

	public void setCopasGanadas(int copasGanadas) {
		this.copasGanadas = copasGanadas;
	}

}
