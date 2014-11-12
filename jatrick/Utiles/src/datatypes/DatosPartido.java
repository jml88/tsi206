package datatypes;

import java.io.Serializable;

public class DatosPartido implements Serializable{
	
	int codPartido;
	private String local;
	private String visitante;
	private int golesLocal;
	private int golesVisitante;
	
	
	public DatosPartido(int codPartido, String local, String visitante,
			int golesLocal, int golesVisitante) {
		super();
		this.codPartido = codPartido;
		this.local = local;
		this.visitante = visitante;
		this.golesLocal = golesLocal;
		this.golesVisitante = golesVisitante;
	}
	
	public int getCodPartido() {
		return codPartido;
	}
	public void setCodPartido(int codPartido) {
		this.codPartido = codPartido;
	}
	public String getLocal() {
		return local;
	}
	public void setLocal(String local) {
		this.local = local;
	}
	public String getVisitante() {
		return visitante;
	}
	public void setVisitante(String visitante) {
		this.visitante = visitante;
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
	
	
	

}
