package timers;

import javax.annotation.PostConstruct;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.inject.Inject;

@Singleton
@Startup
public class TimerMain {
	
	@Inject
	TimerPartido tp;
	
	@PostConstruct
	public void crearTimersPartido(){
		tp.bla(5000);
		
	}

}
