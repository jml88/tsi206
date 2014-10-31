package configuracionGral;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import datatypes.EnumPeriodicoPartido;

@Embeddable
public class PeriodicoPartido implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Column(nullable = true)
	private int dia;
	
	@Column(nullable = true)
	private int hora;
	
	@Column(nullable = true)
	private int minuto;
	
	@Column(nullable = true)
	private EnumPeriodicoPartido periodico;
	
	

	public PeriodicoPartido() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public PeriodicoPartido(int dia, int hora, int minuto,
			EnumPeriodicoPartido periodico) {
		super();
		this.dia = dia;
		this.hora = hora;
		this.minuto = minuto;
		this.periodico = periodico;
	}



	public int getDia() {
		return dia;
	}

	public void setDia(int dia) {
		this.dia = dia;
	}

	public int getHora() {
		return hora;
	}

	public void setHora(int hora) {
		this.hora = hora;
	}

	public int getMinuto() {
		return minuto;
	}

	public void setMinuto(int minuto) {
		this.minuto = minuto;
	}

	public EnumPeriodicoPartido getPeriodico() {
		return periodico;
	}

	public void setPeriodico(EnumPeriodicoPartido periodico) {
		this.periodico = periodico;
	}
	
	
	public Date diaPartido(Date fecha, int numeroFecha){
		Calendar ret = Calendar.getInstance();
		ret.setTime(fecha);
		switch (this.periodico)
		{
			case MINUTO:
				ret.add(Calendar.MINUTE, numeroFecha*this.minuto);
				break;
			case HORA:
				ret.add(Calendar.HOUR, numeroFecha*this.hora);
				break;
			case DIA:
				ret.add(Calendar.DAY_OF_YEAR, numeroFecha*this.dia);
				break;
			
		}
		
		return ret.getTime();
	}
	
	public PeriodicoPartido getDatos(){
		return this;
	}

	
	
	

}
