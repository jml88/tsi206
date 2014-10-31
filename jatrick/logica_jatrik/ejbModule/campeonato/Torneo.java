package campeonato;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import equipos.Equipo;

@Entity
public class Torneo {
	
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
	
	@OneToMany
	private List<Equipo> equipos;
	
	@OneToMany
	private List<Posicion> posiciones;

	public Torneo() {
		super();
		// TODO Auto-generated constructor stub
		this.equipos = new LinkedList<Equipo>();
		this.posiciones = new LinkedList<Posicion>();
	}

	public Torneo(int codigo,int nivelVertical,int nivelHorizontal,int premio, int cantEquipos, int cantCuadrosDesc,
			Calendar fechaDeArranque) {
		super();
		this.codigo = codigo;
		this.nivelVertical = nivelVertical;
		this.nivelHorizontal = nivelHorizontal;
		this.premio = premio;
		this.cantEquipos = cantEquipos;
		this.cantCuadrosDesc = cantCuadrosDesc;
		this.fechaDeArranque = fechaDeArranque;
		this.equipos = new LinkedList<Equipo>();
		this.posiciones = new LinkedList<Posicion>();
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
	
}
