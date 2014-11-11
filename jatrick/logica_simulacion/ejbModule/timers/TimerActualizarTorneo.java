package timers;

import java.util.Date;
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
import campeonato.Torneo;

@Stateless
@LocalBean
public class TimerActualizarTorneo {
	
	@Resource
	private TimerService ts;
	
	@Inject
	private PartidoControlador cp;
	
	public void crearTimerimerActualizarTorneo(int tiempo) {
		TimerConfig tc = new TimerConfig();
		tc.setPersistent(false);
		ts.createIntervalTimer(new Date(), tiempo, tc);
	}

	
	@Timeout
	public void finalizarTorneo(Timer t) {
		cp.torneosFinalizados();
	}
}
