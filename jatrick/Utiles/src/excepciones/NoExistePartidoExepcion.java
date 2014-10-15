package excepciones;

public class NoExistePartidoExepcion extends Exception {

	private String mensaje;

	public NoExistePartidoExepcion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public NoExistePartidoExepcion(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
