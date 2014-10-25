package configuracionGral;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;

import javax.persistence.Embeddable;

import datatypes.DatosPeriodicoPartido;
import datatypes.EnumPeriodicoPartido;

@Embeddable
public class PeriodicoPartido implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int dia;
	
	private int hora;
	
	private int minuto;
	
	private EnumPeriodicoPartido periodico;
	
	private int day_of_week; //1..7, 1 es domingo
	

	public PeriodicoPartido() {
		super();
		// TODO Auto-generated constructor stub
	}

	

	public PeriodicoPartido(int dia, int hora, int minuto,
			EnumPeriodicoPartido periodico, int day_of_week) {
		super();
		this.dia = dia;
		this.hora = hora;
		this.minuto = minuto;
		this.periodico = periodico;
		this.day_of_week = day_of_week;
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

	public int getDay_of_week() {
		return day_of_week;
	}

	public void setDay_of_week(int day_of_week) {
		this.day_of_week = day_of_week;
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
	
	public DatosPeriodicoPartido getDatos(){
		DatosPeriodicoPartido ret = new DatosPeriodicoPartido(dia, hora, minuto, periodico, day_of_week);
		return ret;
	}

	
	
	

}
