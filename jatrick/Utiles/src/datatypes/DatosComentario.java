package datatypes;

import java.io.Serializable;



public class DatosComentario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int Id;
	
	private String mensaje;
	
	private int idPartido;
	
	private int minuto;
	
	private int nroComentario;

	public DatosComentario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DatosComentario(int id, String mensaje, int idPartido, int minuto,
			int nroComentario) {
		super();
		Id = id;
		this.mensaje = mensaje;
		this.idPartido = idPartido;
		this.minuto = minuto;
		this.nroComentario = nroComentario;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public int getNroComentario() {
		return nroComentario;
	}

	public void setNroComentario(int nroComentario) {
		this.nroComentario = nroComentario;
	}
	
	

}
