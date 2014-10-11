package timers;

import java.util.Calendar;

import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import partido.LogicaSimulacion;
import partido.PartidoControlador;
import partidos.Partido;

public class TimerSimularPartido {
	
	@PersistenceContext( unitName = "mongoUnit" ) 
	private EntityManager em;
	
	@Inject
	TimerService ts;
	
	@Inject
	LogicaSimulacion lsim;
	
	public void crearTimerSimularPartido(int idPartido,Calendar c){
		ts.createTimer(c.getTime(), idPartido);
	}
	
	@Timeout
	public void simularPartido(Timer t){
		int idPartido = (int)t.getInfo();
		Partido p = new Partido();
		lsim.simular(p);
	}

}
