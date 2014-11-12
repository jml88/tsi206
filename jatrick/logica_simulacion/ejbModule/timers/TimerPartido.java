package timers;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;
import javax.ejb.AccessTimeout;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;

import org.jboss.ejb3.annotation.TransactionTimeout;

import partido.FinalizadoControlador;
import partido.PartidoControlador;
import partidos.Partido;
import datatypes.DatosMinutoPartido;

@Stateless
@LocalBean
@TransactionTimeout(value = 10, unit = TimeUnit.MINUTES)
public class TimerPartido {
	
	@Resource
	TimerService ts;
	
	@Inject
	FinalizadoControlador fc;
	
	@Inject
	PartidoControlador pc;
	
	@Inject
	TimerSimularPartido tsm;
	
	///Crea timer periodico TimerPartido
	public void crearTimerPeriodico(int tiempo){
		TimerConfig tc = new TimerConfig();
		tc.setPersistent(false);
		ts.createIntervalTimer(new Date(), tiempo, tc);
	}
	
	@Timeout
	public void crearTimers(Timer t){
		List<Partido> partidos = fc.listPartidosFecha(new GregorianCalendar());

		
		for(Partido p : partidos)
		{
			pc.partidoPorSimular(p);
			DatosMinutoPartido dmp = new DatosMinutoPartido(0, p.getCodigo(), false);
			tsm.crearTimerSimularPartido(dmp, p.getFechaHora(), p.getFechaHora().get(Calendar.HOUR_OF_DAY),p.getFechaHora().get(Calendar.MINUTE));
		}
		
	}

}
