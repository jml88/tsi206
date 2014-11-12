package datatypes;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

public class DatosFixture implements Serializable{
	
	private int fecha;
	
	private List<DatosPartido> partidos;

	public DatosFixture(int fecha) {
		super();
		this.fecha = fecha;
		this.partidos = new LinkedList<DatosPartido>();
	}

	public int getFecha() {
		return fecha;
	}

	public void setFecha(int fecha) {
		this.fecha = fecha;
	}

	public List<DatosPartido> getPartidos() {
		return partidos;
	}

	public void setPartidos(List<DatosPartido> partidos) {
		this.partidos = partidos;
	}
	
	

}
