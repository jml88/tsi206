package integracion_logica;


public class ComentarioIntegracion {
	
	private String mensaje;
	
	
	private int minuto;

	public ComentarioIntegracion(String mensaje,
			int minuto) {
		super();
		this.mensaje = mensaje;
		this.minuto = minuto;
	}

	public ComentarioIntegracion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}


	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}
	
	
	
	
	

}
