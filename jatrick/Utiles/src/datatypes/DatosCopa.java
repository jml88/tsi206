package datatypes;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

public class DatosCopa implements Serializable{
	
	private int codigo;
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	private String nombre;
	
	private int premio;
	
	private Calendar fechaInicio;
	
	private List<DatosPartidoCopa> partidos;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getPremio() {
		return premio;
	}

	public void setPremio(int premio) {
		this.premio = premio;
	}

	public Calendar getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public List<DatosPartidoCopa> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<DatosPartidoCopa> partidos) {
		this.partidos = partidos;
	}
	
	

}
