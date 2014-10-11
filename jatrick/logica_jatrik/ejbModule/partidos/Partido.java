package partidos;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import equipos.Alineacion;
import equipos.Equipo;

@Entity
@Table(name = Partido.nombreTabla)
public class Partido {

	public static final String nombreTabla = "PARTIDOS";

	@Id
	@Column(name = "CODPARTIDO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	private Calendar fechaHora;

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
	
	

}
