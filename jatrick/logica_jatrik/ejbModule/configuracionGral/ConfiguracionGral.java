package configuracionGral;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@Column(name="PuntosParaEntrenar")
	private int puntosParaEntrenar;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(nullable = true)
	private Date fechaArranqueCampeonato;
	
	@Column
	private int juvenilEdad;
	
	@Column
	private int adultoEdad;
	
	@Column
	private int veteranoEdad;
	
	@Column
	private int mejoraEntrenaJuvenil;
	
	@Column
	private int mejoraEntrenaAdulto;
	
	@Column
	private int mejoraEntrenaVeterano;
	
	@Column
	private int desmejoraNoEntrenaVeterano;
	
	@Column
	private int desmejoraNoEntrenaAdulto;
	
	@Column
	private int desmejoraNoEntrenaJuvenil;
	
	@Column
	private boolean modificado;
	
	@Column
	private int publicidadMinima;
	
	@Column
	private int publicidadMedia;
	
	@Column
	private int publicidadMaxima;
	
	@Embedded
	@Column(nullable = true)
	private PeriodicoPartido periodicoPartido;
	
	//Estadio
	@Column
	private int capacidadMaximaEstadio;
	
	@Column
	private int capacidadMinimaEstadio;
	
	@Column
	private int capacidadAgrandarEstadio;	

	@Column
	private int cuestaAgrandarEstadio;
	
	@Column
	private int sociosEmpieza;
	
	@Column
	private int seguidoresEmpieza;
	
	
	public void mergeConfiguracionGral(ConfiguracionGral dcg){
		
		this.setCantEquipoTorneo(dcg.getCantEquipoTorneo());
		this.setCantJugadoresArranque(dcg.getCantJugadoresArranque());
		this.setDineroInicial(dcg.getDineroInicial());
		this.setNumeroTorneo(dcg.getNumeroTorneo());
		this.setCantidadDescensos(dcg.getCantidadDescensos());
		this.setPremio(dcg.getPremio());
		this.setCantidadTorneos(dcg.getCantidadTorneos());
		this.setAdultoEdad(dcg.getAdultoEdad());
		this.setCapacidadAgrandarEstadio(dcg.getCapacidadAgrandarEstadio());
		this.setCantidadDescensos(dcg.getCantidadDescensos());
		this.setCapacidadMaximaEstadio(dcg.getCapacidadMaximaEstadio());
		this.setCapacidadMinimaEstadio(dcg.getCapacidadMinimaEstadio());
		this.setCuestaAgrandarEstadio(dcg.getCuestaAgrandarEstadio());
		this.setDesmejoraNoEntrenaAdulto(dcg.getDesmejoraNoEntrenaAdulto());
		this.setDesmejoraNoEntrenaJuvenil(dcg.getDesmejoraNoEntrenaJuvenil());
		this.setDesmejoraNoEntrenaVeterano(dcg.getDesmejoraNoEntrenaVeterano());
		this.setJuvenilEdad(dcg.getJuvenilEdad());
		this.setMejoraEntrenaAdulto(dcg.getMejoraEntrenaAdulto());
		this.setMejoraEntrenaJuvenil(dcg.getMejoraEntrenaJuvenil());
		this.setMejoraEntrenaVeterano(dcg.getMejoraEntrenaVeterano());
		this.setPremio(dcg.getPremio());
		this.setPuntosParaEntrenar(dcg.getPuntosParaEntrenar());
		this.setVeteranoEdad(dcg.getVeteranoEdad());
		this.setPublicidadMaxima(dcg.getPublicidadMaxima());
		this.setPublicidadMinima(dcg.getPublicidadMinima());
		this.setPublicidadMedia(dcg.getPublicidadMedia());
		this.setSociosEmpieza(dcg.getSociosEmpieza());
		this.setSeguidoresEmpieza(dcg.getSeguidoresEmpieza());
		
	}

	


	


	public ConfiguracionGral(int premio, int dineroInicial,
			int cantJugadoresArranque, int cantEquipoTorneo, int numeroFecha,
			int numeroTorneo, int cantidadTorneos, int cantidadDescensos,
			int puntosParaEntrenar, Date fechaArranqueCampeonato,
			int juvenilEdad, int adultoEdad, int veteranoEdad,
			int mejoraEntrenaJuvenil, int mejoraEntrenaAdulto,
			int mejoraEntrenaVeterano, int desmejoraNoEntrenaVeterano,
			int desmejoraNoEntrenaAdulto, int desmejoraNoEntrenaJuvenil,
			boolean modificado, int publicidadMinima, int publicidadMedia,
			int publicidadMaxima, PeriodicoPartido periodicoPartido,
			int capacidadMaximaEstadio, int capacidadMinimaEstadio,
			int capacidadAgrandarEstadio, int cuestaAgrandarEstadio,
			int sociosEmpieza, int seguidoresEmpieza) {
		super();
		this.premio = premio;
		this.dineroInicial = dineroInicial;
		this.cantJugadoresArranque = cantJugadoresArranque;
		this.cantEquipoTorneo = cantEquipoTorneo;
		this.numeroFecha = numeroFecha;
		this.numeroTorneo = numeroTorneo;
		this.cantidadTorneos = cantidadTorneos;
		this.cantidadDescensos = cantidadDescensos;
		this.puntosParaEntrenar = puntosParaEntrenar;
		this.fechaArranqueCampeonato = fechaArranqueCampeonato;
		this.juvenilEdad = juvenilEdad;
		this.adultoEdad = adultoEdad;
		this.veteranoEdad = veteranoEdad;
		this.mejoraEntrenaJuvenil = mejoraEntrenaJuvenil;
		this.mejoraEntrenaAdulto = mejoraEntrenaAdulto;
		this.mejoraEntrenaVeterano = mejoraEntrenaVeterano;
		this.desmejoraNoEntrenaVeterano = desmejoraNoEntrenaVeterano;
		this.desmejoraNoEntrenaAdulto = desmejoraNoEntrenaAdulto;
		this.desmejoraNoEntrenaJuvenil = desmejoraNoEntrenaJuvenil;
		this.modificado = modificado;
		this.publicidadMinima = publicidadMinima;
		this.publicidadMedia = publicidadMedia;
		this.publicidadMaxima = publicidadMaxima;
		this.periodicoPartido = periodicoPartido;
		this.capacidadMaximaEstadio = capacidadMaximaEstadio;
		this.capacidadMinimaEstadio = capacidadMinimaEstadio;
		this.capacidadAgrandarEstadio = capacidadAgrandarEstadio;
		this.cuestaAgrandarEstadio = cuestaAgrandarEstadio;
		this.sociosEmpieza = sociosEmpieza;
		this.seguidoresEmpieza = seguidoresEmpieza;
	}







	public ConfiguracionGral() {
		super();
		// TODO Auto-generated constructor stub
		this.modificado= false;
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


	public Date getFechaArranqueCampeonato() {
		return fechaArranqueCampeonato;
	}


	public void setFechaArranqueCampeonato(Date fechaArranqueCampeonato) {
		this.fechaArranqueCampeonato = fechaArranqueCampeonato;
	}


	public PeriodicoPartido getPeriodicoPartido() {
		return periodicoPartido;
	}


	public void setPeriodicoPartido(PeriodicoPartido periodicoPartido) {
		this.periodicoPartido = periodicoPartido;
	}
	
	
	
	public int getPuntosParaEntrenar() {
		return puntosParaEntrenar;
	}


	public void setPuntosParaEntrenar(int puntosParaEntrenar) {
		this.puntosParaEntrenar = puntosParaEntrenar;
	}


	public ConfiguracionGral getDatos(){
		
		return this;
	}
	
	public int valorEntrenamiento(int edad){
		
		if(edad <= juvenilEdad){
			return mejoraEntrenaJuvenil;
		}
		else if(edad <= adultoEdad){
			return mejoraEntrenaAdulto;
		}
		else{
			return mejoraEntrenaVeterano;
		}
			
	}
	
	public int valorNoEntrenamiento(int edad){
		
		if(edad <= juvenilEdad){
			return desmejoraNoEntrenaJuvenil;
		}
		else if(edad <= adultoEdad){
			return desmejoraNoEntrenaAdulto;
		}
		else{
			return desmejoraNoEntrenaVeterano;
		}
			
	}


	public int getJuvenilEdad() {
		return juvenilEdad;
	}


	public void setJuvenilEdad(int juvenilEdad) {
		this.juvenilEdad = juvenilEdad;
	}


	public int getAdultoEdad() {
		return adultoEdad;
	}


	public void setAdultoEdad(int adultoEdad) {
		this.adultoEdad = adultoEdad;
	}


	public int getVeteranoEdad() {
		return veteranoEdad;
	}


	public void setVeteranoEdad(int veteranoEdad) {
		this.veteranoEdad = veteranoEdad;
	}


	public int getMejoraEntrenaJuvenil() {
		return mejoraEntrenaJuvenil;
	}


	public void setMejoraEntrenaJuvenil(int mejoraEntrenaJuvenil) {
		this.mejoraEntrenaJuvenil = mejoraEntrenaJuvenil;
	}


	public int getMejoraEntrenaAdulto() {
		return mejoraEntrenaAdulto;
	}


	public void setMejoraEntrenaAdulto(int mejoraEntrenaAdulto) {
		this.mejoraEntrenaAdulto = mejoraEntrenaAdulto;
	}


	public int getMejoraEntrenaVeterano() {
		return mejoraEntrenaVeterano;
	}


	public void setMejoraEntrenaVeterano(int mejoraEntrenaVeterano) {
		this.mejoraEntrenaVeterano = mejoraEntrenaVeterano;
	}


	public int getDesmejoraNoEntrenaVeterano() {
		return desmejoraNoEntrenaVeterano;
	}


	public void setDesmejoraNoEntrenaVeterano(int desmejoraNoEntrenaVeterano) {
		this.desmejoraNoEntrenaVeterano = desmejoraNoEntrenaVeterano;
	}


	public int getDesmejoraNoEntrenaAdulto() {
		return desmejoraNoEntrenaAdulto;
	}


	public void setDesmejoraNoEntrenaAdulto(int desmejoraNoEntrenaAdulto) {
		this.desmejoraNoEntrenaAdulto = desmejoraNoEntrenaAdulto;
	}


	public int getDesmejoraNoEntrenaJuvenil() {
		return desmejoraNoEntrenaJuvenil;
	}


	public void setDesmejoraNoEntrenaJuvenil(int desmejoraNoEntrenaJuvenil) {
		this.desmejoraNoEntrenaJuvenil = desmejoraNoEntrenaJuvenil;
	}


	public boolean isModificado() {
		return modificado;
	}


	public void setModificado(boolean modificado) {
		this.modificado = modificado;
	}
	
	public int getCapacidadMaximaEstadio() {
		return capacidadMaximaEstadio;
	}


	public void setCapacidadMaximaEstadio(int capacidadMaximaEstadio) {
		this.capacidadMaximaEstadio = capacidadMaximaEstadio;
	}


	public int getCapacidadMinimaEstadio() {
		return capacidadMinimaEstadio;
	}


	public void setCapacidadMinimaEstadio(int capacidadMinimaEstadio) {
		this.capacidadMinimaEstadio = capacidadMinimaEstadio;
	}


	public int getPublicidadMinima() {
		return publicidadMinima;
	}


	public void setPublicidadMinima(int publicidadMinima) {
		this.publicidadMinima = publicidadMinima;
	}


	public int getPublicidadMedia() {
		return publicidadMedia;
	}


	public void setPublicidadMedia(int publicidadMedia) {
		this.publicidadMedia = publicidadMedia;
	}


	public int getPublicidadMaxima() {
		return publicidadMaxima;
	}


	public void setPublicidadMaxima(int publicidadMaxima) {
		this.publicidadMaxima = publicidadMaxima;
	}


	public int getSociosEmpieza() {
		return sociosEmpieza;
	}


	public void setSociosEmpieza(int sociosEmpieza) {
		this.sociosEmpieza = sociosEmpieza;
	}


	public int getSeguidoresEmpieza() {
		return seguidoresEmpieza;
	}


	public void setSeguidoresEmpieza(int seguidoresEmpieza) {
		this.seguidoresEmpieza = seguidoresEmpieza;
	}


	public int getCapacidadAgrandarEstadio() {
		return capacidadAgrandarEstadio;
	}


	public void setCapacidadAgrandarEstadio(int capacidadAgrandarEstadio) {
		this.capacidadAgrandarEstadio = capacidadAgrandarEstadio;
	}


	public int getCuestaAgrandarEstadio() {
		return cuestaAgrandarEstadio;
	}


	public void setCuestaAgrandarEstadio(int cuestaAgrandarEstadio) {
		this.cuestaAgrandarEstadio = cuestaAgrandarEstadio;
	}
	
	

	
	

}
