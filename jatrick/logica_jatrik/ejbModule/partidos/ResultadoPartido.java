package partidos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import jugadores.Jugador;

@Entity
public class ResultadoPartido implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	private int golesLocal;
	
	private int golesVisitante;
	
	private int penalesLocal;
	
	private int penalesVisitante;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name="RESULTADO_LOCALES",
            joinColumns = {@JoinColumn( name="CODRESULTADO")},
            inverseJoinColumns = {@JoinColumn( name="CODJUGADOR")}
    )
	private Set<Jugador> goleadoresLocal;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name="RESULTADO_VISITANTES",
            joinColumns = {@JoinColumn( name="CODRESULTADO")},
            inverseJoinColumns = {@JoinColumn( name="CODJUGADOR")}
    )
	private Set<Jugador> goleadoresVisitante;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name="AMONESTADO_LOCALES",
            joinColumns = {@JoinColumn( name="CODRESULTADO")},
            inverseJoinColumns = {@JoinColumn( name="CODJUGADOR")}
    )
	private Set<Jugador> amonestadosLocal;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name="AMONESTADO_VISITANTES",
            joinColumns = {@JoinColumn( name="CODRESULTADO")},
            inverseJoinColumns = {@JoinColumn( name="CODJUGADOR")}
    )
	private Set<Jugador> amonestadosVisitante;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name="EXPULSADO_LOCALES",
            joinColumns = {@JoinColumn( name="CODRESULTADO")},
            inverseJoinColumns = {@JoinColumn( name="CODJUGADOR")}
    )
	private Set<Jugador> expulsadosLocal;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name="EXPULSADO_VISITANTES",
            joinColumns = {@JoinColumn( name="CODRESULTADO")},
            inverseJoinColumns = {@JoinColumn( name="CODJUGADOR")}
    )
	private Set<Jugador> expulsadosVisitante;
	
	public ResultadoPartido(){
		this.golesLocal = -1;
		this.golesVisitante = -1;
		this.goleadoresLocal = new HashSet<Jugador>();
		this.goleadoresVisitante = new HashSet<Jugador>();
		
	}
	

	public int getCodigo() {
		return codigo;
	}


	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}


	public int getGolesLocal() {
		return golesLocal;
	}

	public void setGolesLocal(int golesLocal) {
		this.golesLocal = golesLocal;
	}

	public int getGolesVisitante() {
		return golesVisitante;
	}

	public void setGolesVisitante(int golesVisitante) {
		this.golesVisitante = golesVisitante;
	}

	public Set<Jugador> getGoleadoresLocal() {
		return goleadoresLocal;
	}

	public void setGoleadoresLocal(Set<Jugador> goleadoresLocal) {
		this.goleadoresLocal = goleadoresLocal;
	}

	public Set<Jugador> getGoleadoresVisitante() {
		return goleadoresVisitante;
	}

	public void setGoleadoresVisitante(Set<Jugador> goleadoresVisitante) {
		this.goleadoresVisitante = goleadoresVisitante;
	}	

	public void agregarGolLocal(){
		if (golesLocal == -1){
			golesLocal = 0;
		}
		if (golesVisitante == -1){
			golesVisitante = 0;
		}
		golesLocal++;
	}
	
	public void agregarGolVisitante(){
		if (golesVisitante == -1){
			golesVisitante = 0;
		}
		golesVisitante++;
	}


	public int getPenalesLocal() {
		return penalesLocal;
	}


	public void setPenalesLocal(int penalesLocal) {
		this.penalesLocal = penalesLocal;
	}


	public int getPenalesVisitante() {
		return penalesVisitante;
	}


	public void setPenalesVisitante(int penalesVisitante) {
		this.penalesVisitante = penalesVisitante;
	}


	public Set<Jugador> getAmonestadosLocal() {
		return amonestadosLocal;
	}


	public void setAmonestadosLocal(Set<Jugador> amonestadosLocal) {
		this.amonestadosLocal = amonestadosLocal;
	}


	public Set<Jugador> getAmonestadosVisitante() {
		return amonestadosVisitante;
	}


	public void setAmonestadosVisitante(Set<Jugador> amonestadosVisitante) {
		this.amonestadosVisitante = amonestadosVisitante;
	}


	public Set<Jugador> getExpulsadosLocal() {
		return expulsadosLocal;
	}


	public void setExpulsadosLocal(Set<Jugador> expulsadosLocal) {
		this.expulsadosLocal = expulsadosLocal;
	}


	public Set<Jugador> getExpulsadosVisitante() {
		return expulsadosVisitante;
	}


	public void setExpulsadosVisitante(Set<Jugador> expulsadosVisitante) {
		this.expulsadosVisitante = expulsadosVisitante;
	}
	
	
}
