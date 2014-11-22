package integracion_logica;


public class JugadorIntegracion {
	

	
	private String nombre;
	
	private String apellido1;
	
	private String apellido2;
	
	private int edad;

	private int velocidad;
	
	private int tecnica;
	
	private int ataque;
	
	private int defensa;
	
	private int porteria;
	
	private Integer lesion;
	
	private Integer tarjetasPartido;
	
	private EquipoIntegracion equipo;

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido1() {
		return apellido1;
	}

	public void setApellido1(String apellido1) {
		this.apellido1 = apellido1;
	}

	public String getApellido2() {
		return apellido2;
	}

	public void setApellido2(String apellido2) {
		this.apellido2 = apellido2;
	}

	public int getEdad() {
		return edad;
	}

	public void setEdad(int edad) {
		this.edad = edad;
	}

	public int getVelocidad() {
		return velocidad;
	}

	public void setVelocidad(int velocidad) {
		this.velocidad = velocidad;
	}

	public int getTecnica() {
		return tecnica;
	}

	public void setTecnica(int tecnica) {
		this.tecnica = tecnica;
	}

	public int getAtaque() {
		return ataque;
	}

	public void setAtaque(int ataque) {
		this.ataque = ataque;
	}

	public int getDefensa() {
		return defensa;
	}

	public void setDefensa(int defensa) {
		this.defensa = defensa;
	}

	public int getPorteria() {
		return porteria;
	}

	public void setPorteria(int porteria) {
		this.porteria = porteria;
	}


	public Integer getLesion() {
		return lesion;
	}

	public void setLesion(Integer lesion) {
		this.lesion = lesion;
	}

	public Integer getTarjetasPartido() {
		return tarjetasPartido;
	}

	public void setTarjetasPartido(Integer tarjetasPartido) {
		this.tarjetasPartido = tarjetasPartido;
	}

	public JugadorIntegracion(String nombre, String apellido1,
			String apellido2, int edad, int velocidad, int tecnica, int ataque,
			int defensa, int porteria, Integer lesion,
			Integer tarjetasPartido, EquipoIntegracion equipo) {
		super();
		this.nombre = nombre;
		this.apellido1 = apellido1;
		this.apellido2 = apellido2;
		this.edad = edad;
		this.velocidad = velocidad;
		this.tecnica = tecnica;
		this.ataque = ataque;
		this.defensa = defensa;
		this.porteria = porteria;
		this.lesion = lesion;
		this.tarjetasPartido = tarjetasPartido;
		this.equipo = equipo;
	}

	public JugadorIntegracion() {
		super();
		// TODO Auto-generated constructor stub
	}

	public EquipoIntegracion getEquipo() {
		return equipo;
	}

	public void setEquipo(EquipoIntegracion equipo) {
		this.equipo = equipo;
	}
	
	


}
