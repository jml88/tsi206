package integracion_logica;

import java.util.List;

import partidos.Comentario;

public class ResultadoIntegracionDto {

	
	private int golesLocal;
	
	private int golesVisitante;
	
	private int penalesLocal;
	
	private int penalesVisitante;
	
	private List<JugadorIntegracion> goleadoresLocal;
	
	private List<JugadorIntegracion> goleadoresVisitante;
	
	private List<ComentarioIntegracion> comentarios;

	public ResultadoIntegracionDto(int golesLocal, int golesVisitante,
			int penalesLocal, int penalesVisitante,
			List<JugadorIntegracion> goleadoresLocal, List<JugadorIntegracion> goleadoresVisitante,
			List<ComentarioIntegracion> comentarios) {
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

	public List<JugadorIntegracion> getGoleadoresLocal() {
		return goleadoresLocal;
	}

	public void setGoleadoresLocal(List<JugadorIntegracion> goleadoresLocal) {
		this.goleadoresLocal = goleadoresLocal;
	}

	public List<JugadorIntegracion> getGoleadoresVisitante() {
		return goleadoresVisitante;
	}

	public void setGoleadoresVisitante(List<JugadorIntegracion> goleadoresVisitante) {
		this.goleadoresVisitante = goleadoresVisitante;
	}

	public List<ComentarioIntegracion> getComentarios() {
		return comentarios;
	}

	public void setComentarios(List<ComentarioIntegracion> comentarios) {
		this.comentarios = comentarios;
	}
	
	
}
