package jugadores;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import datatypes.DatosJugador;
import equipos.Equipo;
import partidos.Partido;

@Entity
@Table(name = Jugador.nombreTabla)
public class Jugador {

	public static final String nombreTabla = "JUGADORES";

	@Id
	@Column(name = "CODJUGADOR")
	private int codigo;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="APELLIDO1")
	private String apellido1;
	
	@Column(name="APELLIDO2")
	private String apellido2;
	
	@Column(name="edad")
	private int edad;

	@Column(name="VELOCIDAD")
	private int velocidad;
	
	@Column(name="TECNICA")
	private int tecnica;
	
	@Column(name="ATAQUE")
	private int ataque;
	
	@Column(name="DEFENSA")
	private int defensa;
	
	@Column(name="PORTERIA")
	private int porteria;
	
	@Column(name="SALARIO")
	private int salario;
	
	@Column(name="TEMPRESTCONT")
	private int temporadasRestantesContrato;
	
	@Column(name="GOLESCARR")
	private int golesCarrera;
	
	@Column(name="GOLESLIGA")
	private int golesLiga;
	
	@Column(name="GOLESCOPA")
	private int golesCopa;
	
	@Column(name="JATRIKS")
	private int jatTriks;
	
	@OneToOne
	private Equipo equipo; 
	
	@OneToMany
	private Set<Partido> partidosJugados;
	
	/**
	 * Constructor defecto
	 */
	public Jugador() {
		super(); 
		this.partidosJugados = new HashSet<Partido>();
	}
	
	/**
	 * @param nombre
	 * @param apellido1
	 * @param apellido2
	 * @param edad
	 * @param velocidad
	 * @param tecnica
	 * @param ataque
	 * @param defensa
	 * @param porteria
	 * @param salario
	 * @param temporadasRestantesContrato
	 * @param golesCarrera
	 * @param golesLiga
	 * @param golesCopa
	 * @param jatTriks
	 * @param equipo
	 * @param partidosJugados
	 */
	public Jugador(String nombre, String apellido1, String apellido2, int edad,
			int velocidad, int tecnica, int ataque, int defensa, int porteria,
			int salario, int temporadasRestantesContrato, int golesCarrera,
			int golesLiga, int golesCopa, int jatTriks, Equipo equipo,
			Set<Partido> partidosJugados) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.edad = edad;
		this.velocidad = velocidad;
		this.tecnica = tecnica;
		this.ataque = ataque;
		this.defensa = defensa;
		this.porteria = porteria;
		this.salario = salario;
		this.temporadasRestantesContrato = temporadasRestantesContrato;
		this.golesCarrera = golesCarrera;
		this.golesLiga = golesLiga;
		this.golesCopa = golesCopa;
		this.jatTriks = jatTriks;
		this.equipo = equipo;
		this.partidosJugados = partidosJugados;
	}

	public Jugador(DatosJugador dj, Equipo equipo) {
		super();
		this.equipo = equipo;
		this.partidosJugados = new HashSet<Partido>();
		this.nombre = dj.getNombre();
		this.apellido1 = dj.getApellido1();
		this.apellido2 = dj.getApellido2();
		this.edad = dj.getEdad();
		this.velocidad = dj.getVelocidad();
		this.tecnica = dj.getTecnica();
		this.ataque = dj.getAtaque();
		this.defensa = dj.getDefensa();
		this.porteria = dj.getPorteria();
		this.salario = dj.getSalario();
		this.temporadasRestantesContrato = dj.getTemporadasRestantesContrato();
		this.golesCarrera = dj.getGolesCarrera();
		this.golesLiga = dj.getGolesLiga();
		this.golesCopa = dj.getGolesCopa();
		this.jatTriks = dj.getJatTriks();
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getCodigo() {
		return codigo;
	}

	public int getVelocidad() {
		return velocidad;
	}
	
	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}
	
	public int getTecnica() {
		return tecnica;
	}
	
	public void setTecnica(int tecnica) {
		this.tecnica = tecnica;
	}
	
	public int getAtaque() {
		return ataque;
	}
	
	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}
	
	public int getDefensa() {
		return defensa;
	}
	
	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}
	
	public int getPorteria() {
		return porteria;
	}
	
	public void setPorteria(int porteria) {
		this.porteria = porteria;
	}
	
	public Set<Partido> getPartidosJugados() {
		return partidosJugados;
	}
	
	public void setPartidosJugados(Set<Partido> partidosJugados) {
		this.partidosJugados = partidosJugados;
	}
	
	public int getGolesCarrera() {
		return golesCarrera;
	}
	
	public void setGolesCarrera(int golesCarrera) {
		this.golesCarrera = golesCarrera;
	}
	
	public int getGolesLiga() {
		return golesLiga;
	}
	
	public void setGolesLiga(int golesLiga) {
		this.golesLiga = golesLiga;
	}
	
	public int getGolesCopa() {
		return golesCopa;
	}
	
	public void setGolesCopa(int golesCopa) {
		this.golesCopa = golesCopa;
	}
	
	public int getJatTriks() {
		return jatTriks;
	}
	
	public void setJatTriks(int jatTriks) {
		this.jatTriks = jatTriks;
	}
	
	public Equipo getEquipo() {
		return equipo;
	}
	
	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}
	
	public int getSalario() {
		return salario;
	}
	
	public void setSalario(int salario) {
		this.salario = salario;
	}
	
	public int getTemporadasRestantesContrato() {
		return temporadasRestantesContrato;
	}
	
	public void setTemporadasRestantesContrato(int temporadasRestantesContrato) {
		this.temporadasRestantesContrato = temporadasRestantesContrato;
	}
	
	public DatosJugador getDatos() {
		return new DatosJugador(this.codigo, this.nombre, this.apellido1,
				this.apellido2, this.edad, this.velocidad, this.tecnica,
				this.ataque, this.defensa, this.porteria, this.salario,
				this.temporadasRestantesContrato, this.golesCarrera,
				this.golesLiga, this.golesCopa, this.jatTriks,
				this.equipo.getCodigo());
	}
}
