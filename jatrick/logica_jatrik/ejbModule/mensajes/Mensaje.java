package mensajes;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import users.Manager;
import mercadoDePases.CompraVentaJugadores;

@Entity
@Table(name = Mensaje.nombreTabla)
public class Mensaje implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String nombreTabla = "MENSAJES";
	
	@Id
	@Column(name = "CODIGO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	@Column
	private String titulo;
	
	@Column
	private String mensaje;
	
	@OneToOne
	private Manager remitente;
	
	@OneToOne
	private Manager receptor;
	
	@Column
	private Date fechaCreacion;
	
	@Column
	private boolean leido;

	public Mensaje() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public Mensaje(String titulo, String mensaje, Manager remitente,
			Manager receptor, Date fechaCreacion, boolean leido) {
		super();
		this.titulo = titulo;
		this.mensaje = mensaje;
		this.remitente = remitente;
		this.receptor = receptor;
		this.fechaCreacion = fechaCreacion;
		this.leido = leido;
	}

	

	public Date getFechaCreacion() {
		return fechaCreacion;
	}



	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}



	public boolean isLeido() {
		return leido;
	}



	public void setLeido(boolean leido) {
		this.leido = leido;
	}



	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public Manager getRemitente() {
		return remitente;
	}

	public void setRemitente(Manager remitente) {
		this.remitente = remitente;
	}

	public Manager getReceptor() {
		return receptor;
	}

	public void setReceptor(Manager receptor) {
		this.receptor = receptor;
	}
	
	

	
}
