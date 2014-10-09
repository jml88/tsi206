package timers;

import java.util.Date;

import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import partido.PartidoControlador;

public class TimerPartido {
	
	@PersistenceContext( unitName = "mongoUnit" ) 
	private EntityManager em;
	
	@Inject
	TimerService ts;
	
	@Inject
	PartidoControlador pc;
	
	public void bla(int tiempo){
		Timer t = ts.createIntervalTimer(new Date(), tiempo, null);
	}
	
	@Timeout
	public void crearTimers(Timer t){
		//TODO busca los partidos en la base y crea los timers correspondientes
	}

}
