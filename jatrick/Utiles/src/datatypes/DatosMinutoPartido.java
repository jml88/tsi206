package datatypes;

import java.io.Serializable;

public class DatosMinutoPartido implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int minuto;
	
	private long idPartido;

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public long getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(long idPartido) {
		this.idPartido = idPartido;
	}

	public DatosMinutoPartido(int minuto, long idPartido) {
		super();
		this.minuto = minuto;
		this.idPartido = idPartido;
	}

	public DatosMinutoPartido() {
		super();
		// TODO Auto-generated constructor stub
	}
}
