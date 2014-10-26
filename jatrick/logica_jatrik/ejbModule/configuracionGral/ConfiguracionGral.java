package configuracionGral;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import datatypes.DatosConfiguracionGral;
import datatypes.DatosPeriodicoPartido;

@Entity
@Table(name = ConfiguracionGral.nombreTabla)
public class ConfiguracionGral {
	
	public static final String nombreTabla = "ConfiguracionGral";

	@Id
	@Column(name = "CODIGO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	@Column(name="Premio")
	private int premio;
	
	@Column(name="DineroInicial")
	private int dineroInicial;
	
	@Column(name="CantJugadoresArranque")
	private int cantJugadoresArranque;
	
	@Column(name="CantEquipoTorneo")
	private int cantEquipoTorneo;
	
	@Column(name="NumeroFecha")
	private int numeroFecha;
	
	@Column(name="NumeroTorneo")
	private int numeroTorneo;
	
	@Column(name="CantidadTorneos")
	private int cantidadTorneos;
	
	@Column(name="CantidadDescensos")
	private int cantidadDescensos;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column
	private Calendar fechaArranqueCampeonato;
	
	@Embedded
	private PeriodicoPartido periodicoPartido;

	
	public ConfiguracionGral(int codigo, int premio, int dineroInicial,
			int cantJugadoresArranque, int cantEquipoTorneo, int numeroFecha,
			int numeroTorneo, int cantidadTorneos, int cantidadDescensos,
			Calendar fechaArranqueCampeonato) {
		super();
		this.codigo = codigo;
		this.premio = premio;
		this.dineroInicial = dineroInicial;
		this.cantJugadoresArranque = cantJugadoresArranque;
		this.cantEquipoTorneo = cantEquipoTorneo;
		this.numeroFecha = numeroFecha;
		this.numeroTorneo = numeroTorneo;
		this.cantidadTorneos = cantidadTorneos;
		this.cantidadDescensos = cantidadDescensos;
		this.fechaArranqueCampeonato = fechaArranqueCampeonato;
	}


	public ConfiguracionGral() {
		super();
		// TODO Auto-generated constructor stub
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

	public static String getNombretabla() {
		return nombreTabla;
	}



	public int getCantidadTorneos() {
		return cantidadTorneos;
	}



	public void setCantidadTorneos(int cantidadTorneos) {
		this.cantidadTorneos = cantidadTorneos;
	}



	public int getCantidadDescensos() {
		return cantidadDescensos;
	}



	public void setCantidadDescensos(int cantidadDescensos) {
		this.cantidadDescensos = cantidadDescensos;
	}


	public Calendar getFechaArranqueCampeonato() {
		return fechaArranqueCampeonato;
	}


	public void setFechaArranqueCampeonato(Calendar fechaArranqueCampeonato) {
		this.fechaArranqueCampeonato = fechaArranqueCampeonato;
	}


	public PeriodicoPartido getPeriodicoPartido() {
		return periodicoPartido;
	}


	public void setPeriodicoPartido(PeriodicoPartido periodicoPartido) {
		this.periodicoPartido = periodicoPartido;
	}
	
	public DatosConfiguracionGral getDatos(){
		
		DatosPeriodicoPartido dp;
		if(periodicoPartido != null){
			dp = periodicoPartido.getDatos();
		}
		else{
			dp = new DatosPeriodicoPartido();
		}
		 
		DatosConfiguracionGral ret = new DatosConfiguracionGral(premio, dineroInicial, cantJugadoresArranque, cantEquipoTorneo, numeroFecha, numeroTorneo, cantidadTorneos, cantidadDescensos,
										fechaArranqueCampeonato, dp);
		return ret;
	}
	
	

}
