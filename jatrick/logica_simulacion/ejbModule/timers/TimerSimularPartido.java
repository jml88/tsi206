package timers;

import java.util.Calendar;

import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import partido.PartidoControlador;

public class TimerSimularPartido {
	
	@PersistenceContext( unitName = "mongoUnit" ) 
	private EntityManager em;
	
	@Inject
	TimerService ts;
	
	@Inject
	PartidoControlador pc;
	
	public void crearTimerSimularPartido(int idPartido,Calendar c){
		
	}
	
	@Timeout
	public void simularPartido(Timer t){
		
	}

}
