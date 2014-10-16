package timers;

import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.ejb.TimerService;
import javax.inject.Inject;

import partidos.PartidoControlador;

@Singleton
@Startup
public class TimerMain {
	
	@Inject
	TimerPartido tp;
	
	@Resource
	TimerService ts;
	
	@Inject
	PartidoControlador pc;
	@Inject
	partido.PartidoControlador pclh;
	@PostConstruct
	public void crearTimersPartido(){
		
		if(ts.getTimers() != null){
			for(javax.ejb.Timer t: ts.getTimers()){
				t.cancel();
			}
		}
		
		Calendar f = new GregorianCalendar(2014, Calendar.OCTOBER, 15, 21, 46);
		pc.crearPartidoAmistoso(1, 2, f);
		if (!pclh.tieneConfiguracionPartido()){
			pclh.crearConfiguracionPartido(10, 90);
		}
		tp.crearTimerPeriodico(50000);
		
	}

}
