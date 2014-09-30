package jugadores;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import equipos.Equipo;
import partidos.Partido;

@Entity
@Table(name = Jugador.nombreTabla)
public class Jugador {

	public static final String nombreTabla = "JUGADORES";

	@Id
	@Column(name = "CODJUGADOR")
	private int codigo;

	private int velocidad;
	private int tecnica;
	private int ataque;
	private int defensa;
	private int porteria;
	
	@OneToOne
	private Equipo equipo; 
	
	/*atributos de la vida real */
	private int salario;
	
	private int temporadasRestantesContrato;
	
	/* atributos de estadisticas */
	@OneToMany
	private Set<Partido> partidosJugados;
	
	private int golesCarrera;
	
	private int golesLiga;
	
	private int golesCopa;
	
	private int jatTriks;
	
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
	
}
