package entidadesRest;

public class ComentarioRest {

	private int codigo;	
	private String mensaje;
	private int codPartido;	
	private int minuto;
	
	public ComentarioRest() {
		// TODO Auto-generated constructor stub
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

	public int getCodPartido() {
		return codPartido;
	}

	public void setCodPartido(int codPartido) {
		this.codPartido = codPartido;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}
}
