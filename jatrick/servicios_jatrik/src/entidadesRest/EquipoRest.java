package entidadesRest;

public class EquipoRest {

	private int codigo;
	private String nombre;
	private String nombreEstadio;
	private int capacidadEstadio;
	
	public EquipoRest() {
		// TODO Auto-generated constructor stub
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNombreEstadio() {
		return nombreEstadio;
	}

	public void setNombreEstadio(String nombreEstadio) {
		this.nombreEstadio = nombreEstadio;
	}

	public int getCapacidadEstadio() {
		return capacidadEstadio;
	}

	public void setCapacidadEstadio(int capacidadEstadio) {
		this.capacidadEstadio = capacidadEstadio;
	}
}
