package excepciones;

public class NoExisteEquipoPartidoExcepcion extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mensaje;

	public NoExisteEquipoPartidoExcepcion(String mensaje) {
		super();
		this.mensaje = mensaje;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public NoExisteEquipoPartidoExcepcion() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
