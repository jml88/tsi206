package partidos;

import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import jugadores.Jugador;

@Entity
public class ResultadoPartido {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	private int golesLocal;
	
	private int golesVisitante;
	
	@OneToMany
	private List<Jugador> goleadoresLocal;
	
	@OneToMany
	private List<Jugador> goleadoresVisitante;
	
	public ResultadoPartido(){
		this.golesLocal = 0;
		this.golesVisitante = 0;
		this.goleadoresLocal = new LinkedList<Jugador>();
		this.goleadoresVisitante = new LinkedList<Jugador>();
		
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

	
}
