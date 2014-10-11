package timers;

import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import datatypes.DatosPartido;
import partido.PartidoControlador;
import partidos.Partido;

public class TimerPartido {
	
	@PersistenceContext( unitName = "mongoUnit" ) 
	private EntityManager em;
	
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
			tsm.crearTimerSimularPartido(p.getCodigo(), p.getFechaHora());
		}
		
	}

}
