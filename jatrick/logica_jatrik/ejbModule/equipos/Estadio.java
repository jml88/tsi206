package equipos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Estadio {
	
	@Id
	@Column(name = "CODESTADIO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	@Column(name = "NOMBREESTADIO")
	private String nombre;
	
	@Column(name = "CAPACIDADESTADIO")
	private int capacidad;
	
	public Estadio() {
		
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCapacidad() {
		return capacidad;
	}

	public void setCapacidad(int capacidad) {
		this.capacidad = capacidad;
	}

	public int getCodigo() {
		return codigo;
	}
	
	public void setCodigo(int codigo){
		this.codigo = codigo;
	}
	
	

}
