package entidadesRest;

public class PartidoRest {
	
	private int codigo;
	private String nombreLocal;
	private String nombreVisistante;
	private long fecha;
	
	public PartidoRest() {
		// TODO Auto-generated constructor stub
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombreLocal() {
		return nombreLocal;
	}

	public void setNombreLocal(String nombreLocal) {
		this.nombreLocal = nombreLocal;
	}

	public String getNombreVisistante() {
		return nombreVisistante;
	}

	public void setNombreVisistante(String nombreVisistante) {
		this.nombreVisistante = nombreVisistante;
	}

	public long getFecha() {
		return fecha;
	}

	public void setFecha(long fecha) {
		this.fecha = fecha;
	}

}
