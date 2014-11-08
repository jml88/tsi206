package campeonato;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import equipos.Equipo;

@Entity
public class Torneo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@Column(name = "CODTORNEO")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	@Column(name = "NIVEL")
	private int nivelVertical;
	
	@Column(name = "NIVELHORIZONTAL")
	private int nivelHorizontal;
	
	@Column(name = "PREMIO")
	private int premio;
	
	@Column(name = "CANTEQUIPOS")
	private int cantEquipos;
	
	@Column(name = "CANTCUADROSDESC")
	private int cantCuadrosDesc;
	
	@Temporal(TemporalType.TIMESTAMP)
    @Column(name="FECHADEARRANQUE")
	private Calendar fechaDeArranque;
	
	@Column
	private boolean actual;
	
	@ManyToMany(mappedBy = "torneos")
	private List<Equipo> equipos;
	
	@OneToMany(mappedBy = "torneo")
	private List<Posicion> posiciones;
	
	@OneToOne
	private Torneo asciende;

	public Torneo() {
		super();
		// TODO Auto-generated constructor stub
		this.equipos = new LinkedList<Equipo>();
		this.posiciones = new LinkedList<Posicion>();
		this.asciende = null;
		this.cantCuadrosDesc = 0;
		this.actual = true;
	}

	public Torneo(int nivelVertical,int nivelHorizontal,int premio, int cantEquipos, int cantCuadrosDesc,
			Calendar fechaDeArranque) {
		super();
		this.nivelVertical = nivelVertical;
		this.nivelHorizontal = nivelHorizontal;
		this.premio = premio;
		this.cantEquipos = cantEquipos;
		this.cantCuadrosDesc = cantCuadrosDesc;
		this.fechaDeArranque = fechaDeArranque;
		this.equipos = new LinkedList<Equipo>();
		this.posiciones = new LinkedList<Posicion>();
		this.actual = true;
	}
	
	
	public Posicion obtenerPosicionEquipo(Equipo e){
		for (Posicion posicion : this.posiciones) {
			if (posicion.getEquipo().getCodigo() == e.getCodigo()){
				return posicion;
			}
		}
		return null;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public int getNivelVertical() {
		return nivelVertical;
	}

	public void setNivelVertical(int nivel) {
		this.nivelVertical = nivel;
	}
	
	public int getNivelHorizontal() {
		return nivelHorizontal;
	}

	public void setNivelHorizontal(int nivel) {
		this.nivelHorizontal = nivel;
	}

	public int getPremio() {
		return premio;
	}

	public void setPremio(int premio) {
		this.premio = premio;
	}

	public int getCantEquipos() {
		return cantEquipos;
	}

	public void setCantEquipos(int cantEquipos) {
		this.cantEquipos = cantEquipos;
	}

	public int getCantCuadrosDesc() {
		return cantCuadrosDesc;
	}

	public void setCantCuadrosDesc(int cantCuadrosDesc) {
		this.cantCuadrosDesc = cantCuadrosDesc;
	}

	public Calendar getFechaDeArranque() {
		return fechaDeArranque;
	}

	public void setFechaDeArranque(Calendar fechaDeArranque) {
		this.fechaDeArranque = fechaDeArranque;
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public List<Posicion> getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(List<Posicion> posiciones) {
		this.posiciones = posiciones;
	}

	public Torneo getAsciende() {
		return asciende;
	}

	public void setAsciende(Torneo asciende) {
		this.asciende = asciende;
	}

	public boolean isActual() {
		return actual;
	}

	public void setActual(boolean actual) {
		this.actual = actual;
	}
	
}
