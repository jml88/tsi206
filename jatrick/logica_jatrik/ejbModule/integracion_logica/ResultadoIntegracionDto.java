package integracion_logica;

import java.util.LinkedList;
import java.util.List;

import partidos.Comentario;

public class ResultadoIntegracionDto {

	
	private int golesLocal;
	
	private int golesVisitante;
	
	private int penalesLocal;
	
	private int penalesVisitante;
	
	private LinkedList<JugadorIntegracion> goleadoresLocal;
	
	private LinkedList<JugadorIntegracion> goleadoresVisitante;
	
	private LinkedList<ComentarioIntegracion> comentarios;

	public ResultadoIntegracionDto(int golesLocal, int golesVisitante,
			int penalesLocal, int penalesVisitante,
			LinkedList<JugadorIntegracion> goleadoresLocal, LinkedList<JugadorIntegracion> goleadoresVisitante,
			LinkedList<ComentarioIntegracion> comentarios) {
		super();
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
		this.penalesLocal = penalesLocal;
		this.penalesVisitante = penalesVisitante;
		this.goleadoresLocal = goleadoresLocal;
		this.goleadoresVisitante = goleadoresVisitante;
		this.comentarios = comentarios;
	}

	public ResultadoIntegracionDto() {
		super();
		// TODO Auto-generated constructor stub
		this.goleadoresLocal = new LinkedList<JugadorIntegracion>();
		this.comentarios = new LinkedList<ComentarioIntegracion>();
		this.goleadoresVisitante = new LinkedList<JugadorIntegracion>();
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

	public LinkedList<JugadorIntegracion> getGoleadoresLocal() {
		return goleadoresLocal;
	}

	public void setGoleadoresLocal(LinkedList<JugadorIntegracion> goleadoresLocal) {
		this.goleadoresLocal = goleadoresLocal;
	}

	public LinkedList<JugadorIntegracion> getGoleadoresVisitante() {
		return goleadoresVisitante;
	}

	public void setGoleadoresVisitante(LinkedList<JugadorIntegracion> goleadoresVisitante) {
		this.goleadoresVisitante = goleadoresVisitante;
	}

	public LinkedList<ComentarioIntegracion> getComentarios() {
		return comentarios;
	}

	public void setComentarios(LinkedList<ComentarioIntegracion> comentarios) {
		this.comentarios = comentarios;
	}
	
	public void agregarGolLocal(){
		golesLocal++;
	}
	
	public void agregarGolVisitante(){
		golesVisitante++;
	}
	
	
}
