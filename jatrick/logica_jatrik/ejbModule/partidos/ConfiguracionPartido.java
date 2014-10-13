package partidos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ConfiguracionPartido {
	

	@Id
	@Column
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	private int duracion;
	
	private int cantidadJugadas;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getDuracion() {
		return duracion;
	}

	public void setDuracion(int duracion) {
		this.duracion = duracion;
	}

	public int getCantidadJugadas() {
		return cantidadJugadas;
	}

	public void setCantidadJugadas(int cantidadJugadas) {
		this.cantidadJugadas = cantidadJugadas;
	}
}
