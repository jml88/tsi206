package entidadesRest;

import java.util.List;

public class ResultadoRest {

	private int codigo;
	private int golesLocal;
	private int golesVisitante;
	private List<String> goleadoresLocal;
	private List<String> goleadoresVisitante;
	
	public ResultadoRest() {
		// TODO Auto-generated constructor stub
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

	public List<String> getGoleadoresLocal() {
		return goleadoresLocal;
	}

	public void setGoleadoresLocal(List<String> goleadoresLocal) {
		this.goleadoresLocal = goleadoresLocal;
	}

	public List<String> getGoleadoresVisitante() {
		return goleadoresVisitante;
	}

	public void setGoleadoresVisitante(List<String> goleadoresVisitante) {
		this.goleadoresVisitante = goleadoresVisitante;
	}
}
