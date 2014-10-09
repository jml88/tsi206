package excepciones;

public class NoExisteEquipoExcepcion extends Exception {

	
	public NoExisteEquipoExcepcion(){
		
	}
	
	public NoExisteEquipoExcepcion(String mensaje){
		this.mensaje = mensaje;
	}
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String mensaje;

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	

}
