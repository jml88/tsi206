package partidos;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

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
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name="RESULTADO_LOCALES",
            joinColumns = {@JoinColumn( name="CODRESULTADO")},
            inverseJoinColumns = {@JoinColumn( name="CODJUGADOR")}
    )
	private List<Jugador> goleadoresLocal;
	
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(
            name="RESULTADO_VISITANTES",
            joinColumns = {@JoinColumn( name="CODRESULTADO")},
            inverseJoinColumns = {@JoinColumn( name="CODJUGADOR")}
    )
	private List<Jugador> goleadoresVisitante;
	
	public ResultadoPartido(){
		this.golesLocal = 0;
		this.golesVisitante = 0;
		this.goleadoresLocal = new LinkedList<Jugador>();
		this.goleadoresVisitante = new LinkedList<Jugador>();
		
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

	public List<Jugador> getGoleadoresLocal() {
		return goleadoresLocal;
	}

	public void setGoleadoresLocal(List<Jugador> goleadoresLocal) {
		this.goleadoresLocal = goleadoresLocal;
	}

	public List<Jugador> getGoleadoresVisitante() {
		return goleadoresVisitante;
	}

	public void setGoleadoresVisitante(List<Jugador> goleadoresVisitante) {
		this.goleadoresVisitante = goleadoresVisitante;
	}	

	public void agregarGolLocal(){
		golesLocal++;
	}
	
	public void agregarGolVisitante(){
		golesVisitante++;
	}
}
