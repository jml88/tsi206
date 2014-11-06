package web_jatrik;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

@Named("finanzasBB")
@ViewScoped
public class FinanzasBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private int capitalActual;
	
	//positivos
	private int publicidad;
	
	private int ventasJugadoresUltimoMes;
	
	private int socios;
	
	private int entradaUltimos4Partidos;
	
	//negativos
	private int sueldos;
	
	private int pagoJuveniles;

	private int compraJugadoresUltimoMes;
	
	@PostConstruct
	public void init(){
		
	}
	
	public int capitalSumado(){
		return publicidad + ventasJugadoresUltimoMes + socios + entradaUltimos4Partidos;
	}

	public int getCapitalActual() {
		return capitalActual;
	}

	public void setCapitalActual(int capitalActual) {
		this.capitalActual = capitalActual;
	}

	public int getPublicidad() {
		return publicidad;
	}

	public void setPublicidad(int publicidad) {
		this.publicidad = publicidad;
	}

	public int getVentasJugadoresUltimoMes() {
		return ventasJugadoresUltimoMes;
	}

	public void setVentasJugadoresUltimoMes(int ventasJugadoresUltimoMes) {
		this.ventasJugadoresUltimoMes = ventasJugadoresUltimoMes;
	}

	public int getSocios() {
		return socios;
	}

	public void setSocios(int socios) {
		this.socios = socios;
	}

	public int getEntradaUltimos4Partidos() {
		return entradaUltimos4Partidos;
	}

	public void setEntradaUltimos4Partidos(int entradaUltimos4Partidos) {
		this.entradaUltimos4Partidos = entradaUltimos4Partidos;
	}

	public int getSueldos() {
		return sueldos;
	}

	public void setSueldos(int sueldos) {
		this.sueldos = sueldos;
	}

	public int getPagoJuveniles() {
		return pagoJuveniles;
	}

	public void setPagoJuveniles(int pagoJuveniles) {
		this.pagoJuveniles = pagoJuveniles;
	}

	public int getCompraJugadoresUltimoMes() {
		return compraJugadoresUltimoMes;
	}

	public void setCompraJugadoresUltimoMes(int compraJugadoresUltimoMes) {
		this.compraJugadoresUltimoMes = compraJugadoresUltimoMes;
	}
	
	
}
