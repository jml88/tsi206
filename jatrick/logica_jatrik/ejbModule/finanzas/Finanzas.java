package finanzas;

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

import datatypes.EnumTipoTransaccion;
import equipos.Equipo;

@Entity
@Table(name = Finanzas.nombreTabla)
public class Finanzas implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public static final String nombreTabla = "FINANZAS";
	
	@Id
	@Column(name = "CODIGO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	@Column
	private int monto;

	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Date fechaTransaccion;
	
	@OneToOne
	private Equipo equipo;
	
	@Column
	private EnumTipoTransaccion tipoTransaccion;

	public Finanzas() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Finanzas(int monto, Date fechaTransaccion, Equipo equipo,
			EnumTipoTransaccion tipoTransaccion) {
		super();
		this.monto = monto;
		this.fechaTransaccion = fechaTransaccion;
		this.equipo = equipo;
		this.tipoTransaccion = tipoTransaccion;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getMonto() {
		return monto;
	}

	public void setMonto(int monto) {
		this.monto = monto;
	}

	public Date getFechaTransaccion() {
		return fechaTransaccion;
	}

	public void setFechaTransaccion(Date fechaTransaccion) {
		this.fechaTransaccion = fechaTransaccion;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public EnumTipoTransaccion getTipoTransaccion() {
		return tipoTransaccion;
	}

	public void setTipoTransaccion(EnumTipoTransaccion tipoTransaccion) {
		this.tipoTransaccion = tipoTransaccion;
	}
	
	
	

}
