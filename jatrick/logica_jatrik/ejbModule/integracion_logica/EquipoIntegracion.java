package integracion_logica;

public class EquipoIntegracion {
	
	private String nombreEquipo;
	
	private int codigoEquipo;
	
	private int codigoEquipoIntegracion;

	public EquipoIntegracion(String nombreEquipo, int codigoEquipo,
			int codigoEquipoIntegracion) {
		super();
		this.nombreEquipo = nombreEquipo;
		this.codigoEquipo = codigoEquipo;
		this.codigoEquipoIntegracion = codigoEquipoIntegracion;
	}
	
	

	public EquipoIntegracion() {
		super();
		// TODO Auto-generated constructor stub
	}



	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public int getCodigoEquipo() {
		return codigoEquipo;
	}

	public void setCodigoEquipo(int codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}

	public int getCodigoEquipoIntegracion() {
		return codigoEquipoIntegracion;
	}

	public void setCodigoEquipoIntegracion(int codigoEquipoIntegracion) {
		this.codigoEquipoIntegracion = codigoEquipoIntegracion;
	}

}
