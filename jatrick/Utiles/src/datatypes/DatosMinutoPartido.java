package datatypes;

import java.io.Serializable;

public class DatosMinutoPartido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int minuto;
	
	private int idPartido;
	
	private boolean ultimaJugada;

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public int getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}
	

	public boolean isUltimaJugada() {
		return ultimaJugada;
	}

	public void setUltimaJugada(boolean ultimaJugada) {
		this.ultimaJugada = ultimaJugada;
	}

	public DatosMinutoPartido(int minuto, int idPartido, boolean ultimaJugada) {
		super();
		this.minuto = minuto;
		this.idPartido = idPartido;
		this.ultimaJugada = ultimaJugada;
	}

	public DatosMinutoPartido() {
		super();
		// TODO Auto-generated constructor stub
	}
}
