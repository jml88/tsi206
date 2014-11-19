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
	
	@Inject
	TimerActualizarTorneo tat;
	
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
		
		if (!pclh.tieneConfiguracionPartido()){
			pclh.crearConfiguracionPartido(20, 10);
		}
		tp.crearTimerPeriodico(100000);
		tat.crearTimerimerActualizarTorneo(100000);
		
	}

}
