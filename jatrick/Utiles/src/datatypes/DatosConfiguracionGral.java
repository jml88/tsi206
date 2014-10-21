package datatypes;


public class DatosConfiguracionGral {

	
	private int codigo;
	
	private int premio;
	
	private int dineroInicial;
	
	private int cantJugadoresArranque;
	
	private int cantEquipoTorneo;
	
	private int numeroFecha;
	
	private int numeroTorneo;

	public DatosConfiguracionGral() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DatosConfiguracionGral(int premio, int dineroInicial,
			int cantJugadoresArranque, int cantEquipoTorneo, int numeroFecha,
			int numeroTorneo) {
		super();
		this.premio = premio;
		this.dineroInicial = dineroInicial;
		this.cantJugadoresArranque = cantJugadoresArranque;
		this.cantEquipoTorneo = cantEquipoTorneo;
		this.numeroFecha = numeroFecha;
		this.numeroTorneo = numeroTorneo;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getPremio() {
		return premio;
	}

	public void setPremio(int premio) {
		this.premio = premio;
	}

	public int getDineroInicial() {
		return dineroInicial;
	}

	public void setDineroInicial(int dineroInicial) {
		this.dineroInicial = dineroInicial;
	}

	public int getCantJugadoresArranque() {
		return cantJugadoresArranque;
	}

	public void setCantJugadoresArranque(int cantJugadoresArranque) {
		this.cantJugadoresArranque = cantJugadoresArranque;
	}

	public int getCantEquipoTorneo() {
		return cantEquipoTorneo;
	}

	public void setCantEquipoTorneo(int cantEquipoTorneo) {
		this.cantEquipoTorneo = cantEquipoTorneo;
	}

	public int getNumeroFecha() {
		return numeroFecha;
	}

	public void setNumeroFecha(int numeroFecha) {
		this.numeroFecha = numeroFecha;
	}

	public int getNumeroTorneo() {
		return numeroTorneo;
	}

	public void setNumeroTorneo(int numeroTorneo) {
		this.numeroTorneo = numeroTorneo;
	}



}
