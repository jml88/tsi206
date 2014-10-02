package datatypes;

import java.io.Serializable;

public class DatosJugador implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int codigo = -1;
	private String nombre;
	private String apellido1;
	private String apellido2;
	private int edad;
	private int velocidad = 0;
	private int tecnica = 0;
	private int ataque = 0;
	private int defensa = 0;
	private int porteria = 0;
	private int salario = 0;
	private int temporadasRestantesContrato = 0;
	private int golesCarrera = 0;
	private int golesLiga = 0;
	private int golesCopa = 0;
	private int jatTriks = 0;
	private int codEquipo = -1;
	
	/**
	 * @param codigo
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
	 * @param codEquipo
	 */
	public DatosJugador(int codigo, String nombre, String apellido1,
			String apellido2, int edad, int velocidad, int tecnica, int ataque,
			int defensa, int porteria, int salario,
			int temporadasRestantesContrato, int golesCarrera, int golesLiga,
			int golesCopa, int jatTriks, int codEquipo) {
		super();
		this.codigo = codigo;
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
		this.codEquipo = codEquipo;
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

	public void setCodigo(int codigo) {
		this.codigo = codigo;
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

	public int getCodEquipo() {
		return codEquipo;
	}

	public void setCodEquipo(int codEquipo) {
		this.codEquipo = codEquipo;
	}
}

