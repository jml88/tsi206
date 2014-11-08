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

import configuracionGral.ConfiguracionGral;
import configuracionGral.PeriodicoPartido;
import partido.PartidoControlador;
import partidos.Partido;
import partidos.PartidoTorneo;
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
	
	@Inject
	TimerActualizarTorneo tat;
	
	///Crea timer periodico 
	public void crearTimerPeriodico(int tiempo){
		TimerConfig tc = new TimerConfig();
		tc.setPersistent(false);
		ts.createIntervalTimer(new Date(), tiempo, tc);
	}
	
	@Timeout
	public void crearTimers(Timer t){
		//TODO busca los partidos en la base y crea los timers correspondientes
//		System.out.println("*****************");
//		System.out.println("Timer Timer Timer!!");
//		System.out.println("*****************");
		List<Partido> partidos = pc.listPartidosFecha(new GregorianCalendar());
//		boolean seCreo = false;
//		ConfiguracionGral cg = pc.obtenerConfiguracionGral();
//		if(cg != null && cg.isModificado()){
//			PeriodicoPartido fechaPartido = cg.getPeriodicoPartido();
//			Date c = cg.getFechaArranqueCampeonato();
//			Date fechaP = fechaPartido.diaPartido(c, cg.getCantEquipoTorneo());
//			tat.crearTimerimerActualizarTorneo(pt.getFechaHora(), pt.getFechaNro());
//		}

		
		for(Partido p : partidos)
		{
			pc.partidoPorSimular(p);
			PartidoTorneo pt = pc.findPartidoTorneo(p.getCodigo());
			DatosMinutoPartido dmp = new DatosMinutoPartido(0, p.getCodigo(), false);
			tsm.crearTimerSimularPartido(dmp, p.getFechaHora(), p.getFechaHora().get(Calendar.HOUR_OF_DAY),p.getFechaHora().get(Calendar.MINUTE));
		}
		
	}

}
