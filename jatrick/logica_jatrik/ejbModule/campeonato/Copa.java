package campeonato;

import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import partidos.Partido;
import partidos.PartidoCopa;
import datatypes.DatosCopa;
import equipos.Equipo;

@Entity
public class Copa {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	private String nombre;
	
	private int ingreso;
	
	private int cantidadEquipos;
	
	@Temporal(TemporalType.TIMESTAMP)
	private Calendar fechaInicio;
	
	@ManyToMany(fetch = FetchType.EAGER)
	private List<Equipo> equipos;
	
	@OneToMany
	private List<PartidoCopa> partidos;
	
	public Copa(){
		this.equipos = new LinkedList<Equipo>();
		this.partidos = new LinkedList<PartidoCopa>();
	}
	
	public List<Equipo> getEquipos() {
		return equipos;
	}
	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}
	public List<PartidoCopa> getPartidos() {
		return partidos;
	}
	public void setPartidos(List<PartidoCopa> partidos) {
		this.partidos = partidos;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getIngreso() {
		return ingreso;
	}

	public void setIngreso(int ingreso) {
		this.ingreso = ingreso;
	}

	public Calendar getFechaInicio() {
		return fechaInicio;
	}

	public void setFechaInicio(Calendar fechaInicio) {
		this.fechaInicio = fechaInicio;
	}

	public int getCantidadEquipos() {
		return cantidadEquipos;
	}

	public void setCantidadEquipos(int cantidadEquipos) {
		this.cantidadEquipos = cantidadEquipos;
	}
	
	public DatosCopa obtenerDatos(){
		DatosCopa dc = new DatosCopa();
		dc.setCodigo(codigo);
		dc.setFechaInicio(fechaInicio);
		dc.setNombre(nombre);
		dc.setPremio(ingreso*cantidadEquipos);
		return dc;
	}

}
