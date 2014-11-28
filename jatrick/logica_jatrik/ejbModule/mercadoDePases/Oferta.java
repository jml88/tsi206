package mercadoDePases;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import equipos.Equipo;

@Entity
public class Oferta {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Calendar fecha;
	
	@OneToOne
	private Equipo equipoOferta;
	
	private int cantidad;

	public Oferta(){
		
	}
	
	public Oferta(Calendar fecha, int cantidad, Equipo equipoOferta){
		this.fecha = fecha;
		this.cantidad = cantidad;
		this.equipoOferta = equipoOferta;
	}
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Calendar getFecha() {
		return fecha;
	}

	public void setFecha(Calendar fecha) {
		this.fecha = fecha;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public Equipo getEquipoOferta() {
		return equipoOferta;
	}

	public void setEquipoOferta(Equipo equipoOferta) {
		this.equipoOferta = equipoOferta;
	}

}
