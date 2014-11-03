package timers;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;

import partido.PartidoControlador;
import partidos.Partido;
import datatypes.DatosAlineacion;
import datatypes.DatosMinutoPartido;

@Stateless
@LocalBean
public class TimerPartido {
	
	@Resource
	TimerService ts;
	
	@Inject
	PartidoControlador pc;
	
	@Inject
	TimerSimularPartido tsm;
	
	///Crea timer periodico 
	public void crearTimerPeriodico(int tiempo){
		ts.createIntervalTimer(new Date(), tiempo, new TimerConfig());
	}
	
	@Timeout
	public void crearTimers(Timer t){
		//TODO busca los partidos en la base y crea los timers correspondientes
//		System.out.println("*****************");
//		System.out.println("Timer Timer Timer!!");
//		System.out.println("*****************");
		List<Partido> partidos = pc.listPartidosFecha(new GregorianCalendar());
		for(Partido p : partidos)
		{
			pc.partidoPorSimular(p);
			
			DatosMinutoPartido dmp = new DatosMinutoPartido(0, p.getCodigo(), false);
			tsm.crearTimerSimularPartido(dmp, p.getFechaHora(), p.getFechaHora().get(Calendar.HOUR_OF_DAY),p.getFechaHora().get(Calendar.MINUTE));
		}
		
	}

}
