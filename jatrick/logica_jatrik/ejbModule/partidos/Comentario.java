package partidos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.TableGenerator;
import javax.xml.bind.annotation.XmlRootElement;

import partidos.Partido;

@Entity
public class Comentario {
	
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
	
}
