package campeonato;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

import equipos.Equipo;

@Entity
public class Posicion implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	@OneToOne
	private Equipo equipo;
	
	@ManyToOne
	private Torneo torneo;
	
	@Column
	private int partidosJugados;
	
	@Column
	private int partidosGanados;
	
	@Column
	private int partidosEmpatados;
	
	@Column
	private int partidosPerdidos;
	
	@Column
	private int golesAFavor;
	
	@Column
	private int golesEnContra;
	
	@Column
	private int puntos;
	
	public Posicion(){
		this.equipo = null;
		this.golesAFavor = 0;
		this.golesEnContra = 0;
		this.partidosEmpatados = 0;
		this.partidosJugados = 0;
		this.partidosGanados = 0;
		this.partidosPerdidos = 0;
		this.puntos = 0;
	}
	
	public Posicion(Equipo equipo){
		this.equipo = equipo;
		this.golesAFavor = 0;
		this.golesEnContra = 0;
		this.partidosEmpatados = 0;
		this.partidosGanados = 0;
		this.partidosPerdidos = 0;
		this.partidosJugados = 
		this.puntos = 0;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public int getPartidosJugados() {
		return partidosJugados;
	}

	public void setPartidosJugados(int partidosJugados) {
		this.partidosJugados = partidosJugados;
	}

	public int getPartidosGanados() {
		return partidosGanados;
	}

	public void setPartidosGanados(int partidosGanados) {
		this.partidosGanados = partidosGanados;
	}

	public int getPartidosEmpatados() {
		return partidosEmpatados;
	}

	public void setPartidosEmpatados(int partidosEmpatados) {
		this.partidosEmpatados = partidosEmpatados;
	}

	public int getPartidosPerdidos() {
		return partidosPerdidos;
	}

	public void setPartidosPerdidos(int partidosPerdidos) {
		this.partidosPerdidos = partidosPerdidos;
	}

	public int getGolesAFavor() {
		return golesAFavor;
	}

	public void setGolesAFavor(int golesAFavor) {
		this.golesAFavor = golesAFavor;
	}

	public int getGolesEnContra() {
		return golesEnContra;
	}

	public void setGolesEnContra(int golesEnContra) {
		this.golesEnContra = golesEnContra;
	}

	public int getPuntos() {
		return puntos;
	}

	public void setPuntos(int puntos) {
		this.puntos = puntos;
	}

	public void actualizarFecha(int golesLocal, int golesVisitante) {
		if (golesLocal > golesVisitante){
			puntos += 3;
			partidosGanados += 1;
		}else if (golesLocal < golesVisitante){
			partidosPerdidos += 1;
		}else{
			puntos += 1;
			partidosEmpatados += 1;
		}
		partidosJugados += 1;
		golesAFavor += golesLocal;
		golesEnContra += golesVisitante;
	}

	public void resetearPosicion() {
//		this.equipo = null;
		this.golesAFavor = 0;
		this.golesEnContra = 0;
		this.partidosEmpatados = 0;
		this.partidosJugados = 0;
		this.partidosGanados = 0;
		this.partidosPerdidos = 0;
		this.puntos = 0;
	}



}
