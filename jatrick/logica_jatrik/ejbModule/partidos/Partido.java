package partidos;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import datatypes.DatosPartido;
import datatypes.EnumPartido;
import equipos.Alineacion;
import equipos.Equipo;


@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name=Partido.nombreTabla)
public class Partido {

	public static final String nombreTabla = "PARTIDOS";
	
	public Partido(){
		
	}
	
	Partido(Equipo local, Equipo visitante, Calendar fecha){
		this.local = local;
		this.visitante = visitante;
		this.fechaHora = fecha;
		this.estado = EnumPartido.POR_JUGAR;
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

	@OneToOne
	private Equipo local;
	
	@OneToOne
	private Equipo visitante;
	
	@OneToOne
	private Alineacion alineacionLocal;
	
	@OneToOne
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
	
	public DatosPartido getDatos() {
		return new DatosPartido(this.codigo, this.fechaHora, this.estado, this.local.getCodigo(), this.visitante.getCodigo(),
				this.alineacionLocal != null? this.alineacionLocal.getCodigo():-1,
				this.alineacionVisitante != null? this.alineacionVisitante.getCodigo():-1);
	}

}
