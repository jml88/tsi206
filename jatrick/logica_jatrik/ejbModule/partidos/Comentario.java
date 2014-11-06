package partidos;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import datatypes.DatosComentario;

@Entity
public class Comentario implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name = "CODIGOCOMENTARIO")
	private int Id;
	
	private String mensaje;
	
	@OneToOne
	private Partido partido;
	
	private int minuto;

	public Comentario() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Comentario(int id, String mensaje, Partido partido, int minuto) {
		super();
		Id = id;
		this.mensaje = mensaje;
		this.partido = partido;
		this.minuto = minuto;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Partido getPartido() {
		return partido;
	}

	public void setPartido(Partido partido) {
		this.partido = partido;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public DatosComentario getDatos(){
		DatosComentario datos = new DatosComentario();
		datos.setId(this.Id);
		datos.setIdPartido(this.partido.getCodigo());
		datos.setMensaje(this.mensaje);
		datos.setMinuto(this.getMinuto());
		return datos;
	}
	
}
