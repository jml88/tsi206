package mercadoDePases;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import equipos.Equipo;
import jugadores.Jugador;

@Entity
@Table(name = CompraVentaJugadores.nombreTabla)
public class CompraVentaJugadores implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String nombreTabla = "CompraVentaJugadores";
	
	@Id
	@Column(name = "CODIGO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	@Column
	private float precio;
	
	@OneToOne
	private Jugador jugadorVenta;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	private Date fechaPuestoEnVenta;
	
	@Column
	private Equipo equipoVenta;
	
	@Column(nullable = true)
	private Equipo equipoCompra;
	
	@Column
	private boolean activo;

	public CompraVentaJugadores() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public CompraVentaJugadores(float precio, Jugador jugadorVenta,
			Date fechaPuestoEnVenta, Equipo equipoVenta, Equipo equipoCompra,
			boolean activo) {
		super();
		this.precio = precio;
		this.jugadorVenta = jugadorVenta;
		this.fechaPuestoEnVenta = fechaPuestoEnVenta;
		this.equipoVenta = equipoVenta;
		this.equipoCompra = equipoCompra;
		this.activo = activo;
	}
	



	public boolean isActivo() {
		return activo;
	}



	public void setActivo(boolean activo) {
		this.activo = activo;
	}



	public float getPrecio() {
		return precio;
	}

	public void setPrecio(float precio) {
		this.precio = precio;
	}

	public Jugador getJugadorVenta() {
		return jugadorVenta;
	}

	public void setJugadorVenta(Jugador jugadorVenta) {
		this.jugadorVenta = jugadorVenta;
	}

	public Date getFechaPuestoEnVenta() {
		return fechaPuestoEnVenta;
	}

	public void setFechaPuestoEnVenta(Date fechaPuestoEnVenta) {
		this.fechaPuestoEnVenta = fechaPuestoEnVenta;
	}

	public Equipo getEquipoVenta() {
		return equipoVenta;
	}

	public void setEquipoVenta(Equipo equipoVenta) {
		this.equipoVenta = equipoVenta;
	}

	public Equipo getEquipoCompra() {
		return equipoCompra;
	}

	public void setEquipoCompra(Equipo equipoCompra) {
		this.equipoCompra = equipoCompra;
	}
	
	
	
	
	
	
	
}
