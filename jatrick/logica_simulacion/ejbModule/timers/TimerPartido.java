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
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;

import org.jboss.ejb3.annotation.TransactionTimeout;

import partido.FinalizadoControlador;
import partido.LogicaSimulacion;
import partido.PartidoControlador;
import partidos.Partido;
import datatypes.DatosMinutoPartido;
import excepciones.NoExisteEquipoExcepcion;

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
	LogicaSimulacion ls;
	
	@Inject
	TimerSimularPartido tsm;
	
	///Crea timer periodico TimerPartido
	public void crearTimerPeriodico(int tiempo){
		TimerConfig tc = new TimerConfig();
		ts.createIntervalTimer(new Date(), tiempo, tc);
	}
	
	//@TransactionAttribute(TransactionAttributeType.NEVER)
	@Timeout
	public void crearTimers(Timer t){
		List<Partido> partidosMaM = fc.listPartidosFecha(new GregorianCalendar());
		List<Partido> partidosDirectos = fc.obtenerPartidosNoSuscriptosParaSimular(Calendar.getInstance());
		for(Partido p : partidosMaM)
		{
			pc.partidoPorSimular(p);
			DatosMinutoPartido dmp = new DatosMinutoPartido(-1, p.getCodigo(), false);
			tsm.crearTimerSimularPartido(dmp, p.getFechaHora(), p.getFechaHora().get(Calendar.HOUR_OF_DAY),p.getFechaHora().get(Calendar.MINUTE),p.getFechaHora().get(Calendar.SECOND));
		}
		for (Partido partido : partidosDirectos) {
			pc.partidoPorSimular(partido);
			try {
				ls.simularPartidoCompleto(partido);
			} catch (NoExisteEquipoExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
