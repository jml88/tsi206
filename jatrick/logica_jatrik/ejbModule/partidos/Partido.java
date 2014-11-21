package partidos;

import java.io.Serializable;
import java.util.Calendar;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import datatypes.EnumPartido;
import equipos.Alineacion;
import equipos.Equipo;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name=Partido.nombreTabla)
public class Partido implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public static final String nombreTabla = "PARTIDOS";
	
	public Partido(){
		this.estado = EnumPartido.POR_JUGAR;
		this.local = null;
		this.visitante = null;
		this.fechaHora = null;
		this.resultado = null;
		this.suscriptoMinutoAmintuto = true;
	}
	
	Partido(Equipo local, Equipo visitante, Calendar fecha){
		this.local = local;
		this.visitante = visitante;
		this.fechaHora = fecha;
		this.estado = EnumPartido.POR_JUGAR;
		this.resultado = null;
		this.suscriptoMinutoAmintuto = true;
	}

	@Id
	@Column(name = "CODPARTIDO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
    @Temporal(TemporalType.TIMESTAMP)
    @Column
	private Calendar fechaHora;
    
    @Enumerated(EnumType.STRING)
    @Column
	private EnumPartido estado;
    
    private String tipoPartido;
    
    private boolean suscriptoMinutoAmintuto;
    
    @OneToOne
    private ResultadoPartido resultado;

	@OneToOne
	private Equipo local;
	
	@OneToOne
	private Equipo visitante;
	
	@OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
	private Alineacion alineacionLocal;
	
	@OneToOne(cascade = {CascadeType.PERSIST}, fetch = FetchType.LAZY)
	private Alineacion alineacionVisitante;

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Equipo getLocal() {
		return local;
	}

	public void setLocal(Equipo local) {
		this.local = local;
	}

	public Equipo getVisitante() {
		return visitante;
	}

	public void setVisitante(Equipo visitante) {
		this.visitante = visitante;
	}

	public Alineacion getAlineacionLocal() {
		return alineacionLocal;
	}

	public void setAlineacionLocal(Alineacion alineacionLocal) {
		this.alineacionLocal = alineacionLocal;
	}

	public Alineacion getAlineacionVisitante() {
		return alineacionVisitante;
	}

	public void setAlineacionVisitante(Alineacion alineacionVisitante) {
		this.alineacionVisitante = alineacionVisitante;
	}

	public Calendar getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Calendar fechaHora) {
		this.fechaHora = fechaHora;
	}

	public EnumPartido getEstado() {
		return estado;
	}

	public void setEstado(EnumPartido estado) {
		this.estado = estado;
	}
	
	
	public ResultadoPartido getResultado() {
		return resultado;
	}

	public void setResultado(ResultadoPartido resultado) {
		this.resultado = resultado;
	}

	public String getTipoPartido() {
		return tipoPartido;
	}

	public void setTipoPartido(String tipoPartido) {
		this.tipoPartido = tipoPartido;
	}

	public boolean isSuscriptoMinutoAmintuto() {
		return suscriptoMinutoAmintuto;
	}

	public void setSuscriptoMinutoAmintuto(boolean suscriptoMinutoAmintuto) {
		this.suscriptoMinutoAmintuto = suscriptoMinutoAmintuto;
	}

}
