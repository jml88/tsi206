package timers;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

import partidos.PartidoControlador;

@Singleton
@Startup
public class TimerMain {
	
	@Inject
	TimerPartido tp;
	
	@Inject
	PartidoControlador pc;
	
	@PostConstruct
	public void crearTimersPartido(){
		Calendar f = new GregorianCalendar(2014, Calendar.OCTOBER, 14, 22, 39);
		pc.crearPartidoAmistoso(1, 2, f);
		tp.crearTimerPeriodico(50000);
		
	}

}
