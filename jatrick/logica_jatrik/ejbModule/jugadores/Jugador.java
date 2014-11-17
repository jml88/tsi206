package jugadores;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import partidos.Partido;
import datatypes.DatosJugador;
import datatypes.EnumEntrenamiento;
import equipos.Equipo;

@Entity
@Table(name = Jugador.nombreTabla)
public class Jugador implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String nombreTabla = "JUGADORES";

	@Id
	@Column(name = "CODJUGADOR")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
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
	
	private Integer lesion;
	
	private Integer tarjetasPartido;
	
	private Integer sancionLiga;
	
	@Embedded
	@Column(nullable=true)
	private EntrenamientoJugador entrenamiento;
	
	
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
		this.lesion = null;
		this.tarjetasPartido = null;
		this.sancionLiga = null;
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
		this.lesion = null;
		this.tarjetasPartido = null;
		this.sancionLiga = null;
		salario = calcularSueldoJugador();
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
	
	
	public Integer getLesion() {
		return lesion;
	}

	public void setLesion(Integer lesion) {
		this.lesion = lesion;
	}

	public Integer getTarjetasPartido() {
		return tarjetasPartido;
	}

	public void setTarjetasPartido(Integer tarjetasPartido) {
		this.tarjetasPartido = tarjetasPartido;
	}

	public Integer getSancionLiga() {
		return sancionLiga;
	}

	public void setSancionLiga(Integer sancionLiga) {
		this.sancionLiga = sancionLiga;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public EntrenamientoJugador getEntrenamiento() {
		return entrenamiento;
	}

	public void setEntrenamiento(EntrenamientoJugador entrenamiento) {
		this.entrenamiento = entrenamiento;
	}

	public DatosJugador getDatos() {
		return new DatosJugador(this.codigo, this.nombre, this.apellido1,
				this.apellido2, this.edad, this.velocidad, this.tecnica,
				this.ataque, this.defensa, this.porteria, this.salario,
				this.temporadasRestantesContrato, this.golesCarrera,
				this.golesLiga, this.golesCopa, this.jatTriks,
				this.equipo.getCodigo());
	}
	
	public String getFullName(){
		return this.nombre + " " + this.apellido1 + " " + this.apellido2;
	}
	
	public void entrenar(EnumEntrenamiento tipo,int valor,int valorNoEntrena){
		
		if(tipo == EnumEntrenamiento.ATAQUE){
			if (entrenamiento.getAtaqueEntrenamiento() + valor >= 10){
				ataque = ataque + ((entrenamiento.getAtaqueEntrenamiento() + valor) / 10);
			}
			entrenamiento.setAtaqueEntrenamiento((entrenamiento.getAtaqueEntrenamiento() + valor) % 10);
		}
		else{
			if(entrenamiento.getAtaqueEntrenamiento()-valorNoEntrena < 0){
				ataque = ataque - (Math.abs(entrenamiento.getAtaqueEntrenamiento() - valorNoEntrena) / 10);
			}
			
			entrenamiento.setAtaqueEntrenamiento(Math.abs(entrenamiento.getAtaqueEntrenamiento() - valorNoEntrena) % 10);
		}
		if(tipo == EnumEntrenamiento.DEFENSA){
			if (entrenamiento.getDefensaEntrenamiento() + valor >= 10){
				defensa = defensa + ((entrenamiento.getDefensaEntrenamiento() + valor) % 10);
			}
			entrenamiento.setDefensaEntrenamiento((entrenamiento.getDefensaEntrenamiento() + valor) % 10);
		}
		else{
			if(entrenamiento.getDefensaEntrenamiento()-valorNoEntrena < 0){
				defensa = defensa - (Math.abs(entrenamiento.getDefensaEntrenamiento() - valorNoEntrena) / 10);
			}
			
			entrenamiento.setDefensaEntrenamiento(Math.abs(entrenamiento.getDefensaEntrenamiento() - valorNoEntrena) % 10);
		}
		if(tipo == EnumEntrenamiento.PORTERO){
			if (entrenamiento.getPorteroEntrenamiento() + valor >= 10){
				porteria = porteria + ((entrenamiento.getPorteroEntrenamiento() + valor) % 10);
			}
			entrenamiento.setPorteroEntrenamiento((entrenamiento.getPorteroEntrenamiento() + valor) % 10);
		}
		else{
			if(entrenamiento.getPorteroEntrenamiento()-valorNoEntrena < 0){
				porteria = porteria - (Math.abs(entrenamiento.getPorteroEntrenamiento() - valorNoEntrena) / 10);
			}
			
			entrenamiento.setPorteroEntrenamiento(Math.abs(entrenamiento.getPorteroEntrenamiento() - valorNoEntrena) % 10);
		}
		if(tipo == EnumEntrenamiento.TECNICA){
			if (entrenamiento.getTecnicaEntrenamiento() + valor >= 10){
				tecnica = tecnica + ((entrenamiento.getTecnicaEntrenamiento() + valor) % 10);
			}
			entrenamiento.setTecnicaEntrenamiento((entrenamiento.getTecnicaEntrenamiento() + valor) % 10);
		}
		else{
			if(entrenamiento.getTecnicaEntrenamiento()-valorNoEntrena < 0){
				tecnica = tecnica - (Math.abs(entrenamiento.getTecnicaEntrenamiento() - valorNoEntrena) / 10);
			}
			
			entrenamiento.setTecnicaEntrenamiento(Math.abs(entrenamiento.getTecnicaEntrenamiento() - valorNoEntrena) % 10);
		}
	}
	
	public String idString(){
		return nombre + codigo;
	}
	
	public int calcularSueldoJugador(){
		
		return (this.getAtaque() + this.getDefensa() + this.getPorteria() + this.getTecnica()) / (this.getEdad())*10;
	}
}
