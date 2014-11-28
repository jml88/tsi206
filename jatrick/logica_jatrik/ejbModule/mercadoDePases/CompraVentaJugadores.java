package mercadoDePases;

import java.io.Serializable;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
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
	private int precio;
	
	@OneToOne
	private Jugador jugadorVenta;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date fechaPuestoEnVenta;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	private Date fechaDeTransaccion;
	
	@OneToOne
	private Equipo equipoVenta;
	
	@OneToOne(optional = true)
	private Equipo equipoCompra;
	
	@Column
	private boolean activo;
	
	@OneToMany
	private List<Oferta> ofertas;

	public CompraVentaJugadores() {
		super();
		// TODO Auto-generated constructor stub
		this.ofertas = new LinkedList<Oferta>();
	}

	public CompraVentaJugadores(int precio, Jugador jugadorVenta,
			Date fechaPuestoEnVenta, Date fechaDeTransaccion,
			Equipo equipoVenta, Equipo equipoCompra, boolean activo) {
		super();
		this.precio = precio;
		this.jugadorVenta = jugadorVenta;
		this.fechaPuestoEnVenta = fechaPuestoEnVenta;
		this.fechaDeTransaccion = fechaDeTransaccion;
		this.equipoVenta = equipoVenta;
		this.equipoCompra = equipoCompra;
		this.activo = activo;
		this.ofertas = new LinkedList<Oferta>();
	}


	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public boolean isActivo() {
		return activo;
	}

	public void setActivo(boolean activo) {
		this.activo = activo;
	}

	public int getPrecio() {
		return precio;
	}

	public void setPrecio(int precio) {
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

	
	public Date getFechaDeTransaccion() {
		return fechaDeTransaccion;
	}

	public void setFechaDeTransaccion(Date fechaDeTransaccion) {
		this.fechaDeTransaccion = fechaDeTransaccion;
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

	public List<Oferta> getOfertas() {
		return ofertas;
	}

	public void setOfertas(List<Oferta> ofertas) {
		this.ofertas = ofertas;
	}
	
	
}
