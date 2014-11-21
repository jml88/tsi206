package timers;

import java.util.Date;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import partido.CampeonatoControlador;

@Stateless
@LocalBean
public class TimerActualizarTorneo {
	
	@Resource
	private TimerService ts;
	
	@Inject
	private CampeonatoControlador cc;
	
	public void crearTimerimerActualizarTorneo(int tiempo) {
		TimerConfig tc = new TimerConfig();
		tc.setPersistent(false);
		ts.createIntervalTimer(new Date(), tiempo, tc);
	}

	
	
	@Timeout
	//@TransactionAttribute(TransactionAttributeType.NEVER)
	public void finalizarTorneo(Timer t) {
		cc.torneosFinalizados();
	}
}
