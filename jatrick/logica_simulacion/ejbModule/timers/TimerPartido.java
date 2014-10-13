package timers;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;

import partido.PartidoControlador;
import partidos.Partido;
import datatypes.DatosMinutoPartido;

public class TimerPartido {
	
	@Inject
	TimerService ts;
	
	@Inject
	PartidoControlador pc;
	
	@Inject
	TimerSimularPartido tsm;
	
	///Crea timer periodico 
	public void crearTimerPeriodico(int tiempo){
		Timer t = ts.createIntervalTimer(new Date(), tiempo, null);
	}
	
	@Timeout
	public void crearTimers(Timer t){
		//TODO busca los partidos en la base y crea los timers correspondientes
		List<Partido> partidos = pc.listPartidosFecha(new GregorianCalendar());
		for(Partido p : partidos)
		{
			DatosMinutoPartido dmp = new DatosMinutoPartido(0, p.getCodigo());
			tsm.crearTimerSimularPartido(dmp, p.getFechaHora());
		}
		
	}

}
