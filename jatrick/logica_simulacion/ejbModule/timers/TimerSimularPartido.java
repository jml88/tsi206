package timers;

import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

import javax.annotation.Resource;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import partido.LogicaSimulacion;
import partido.PartidoControlador;
import partido.SimulacionControlador;
import partidos.ConfiguracionPartido;
import partidos.Partido;
import partidos.PartidoTorneo;
import campeonato.Posicion;
import datatypes.DatosAlineacion;
import datatypes.DatosMinutoPartido;
import excepciones.NoExisteEquipoExcepcion;
import finanzas.FinanzasControlador;

@Stateless
@LocalBean
public class TimerSimularPartido {

	@PersistenceContext(unitName = "jatrik")
	private EntityManager em;

	@Resource
	private TimerService ts;

	@Inject
	private LogicaSimulacion lsim;

	@Inject
	private SimulacionControlador sc;
	
	@Inject
	private FinanzasControlador fc;
	
	@Inject
	PartidoControlador pc;

	// @Inject
	// ConfiguracionControlador cc;

	public void crearTimerSimularPartido(DatosMinutoPartido mp, Calendar c,
			int hora, int minuto, int segundo) {
		Date d = c.getTime();
		d.setHours(hora);
		d.setMinutes(minuto);
		d.setSeconds(segundo);
		ts.createTimer(d, mp);
	}

	private void crearPartido(List<Integer> minutos, Partido p) {
		ConfiguracionPartido cp = pc.findConfiguracionPartido();
		Calendar fecha = new GregorianCalendar();
		Collections.sort(minutos);
		int ultimoMinuto = minutos.get(minutos.size() - 1);
		for (Integer min : minutos) {
			
			Double minutoysegundos = ((double)(min*cp.getDuracion())/90);
			int minuto = (int)minutoysegundos.intValue();
			Double seg = ((double)(minutoysegundos-minuto))*60;
			int segundo = (int)seg.intValue();
			fecha = (Calendar) p.getFechaHora().clone();
			fecha.add(Calendar.MINUTE, minuto);
			fecha.add(Calendar.SECOND, segundo);
			crearTimerSimularPartido(new DatosMinutoPartido(min, p.getCodigo(),
					min == ultimoMinuto), fecha,
					fecha.get(Calendar.HOUR_OF_DAY), fecha.get(Calendar.MINUTE),fecha.get(Calendar.SECOND));
		}
		//TODO copiar la alineacion defecto
		if (p.getAlineacionLocal() == null) {
			p.setAlineacionLocal(p.getLocal().getAlineacionDefecto());
		}
		if (p.getAlineacionVisitante() == null) {
			p.setAlineacionVisitante(p.getVisitante().getAlineacionDefecto());
		}
		em.merge(p);
	}

	private void actualizarDatosPartido(Partido p) {
		sc.partidoFinalizado(p);
		PartidoTorneo pt = sc.findPartidoTorneo(p.getCodigo());	
		if (pt != null) {
			Posicion posLocal = ((PartidoTorneo) pt).getTorneo()
					.obtenerPosicionEquipo(pt.getLocal());
			Posicion posVisitante = ((PartidoTorneo) pt).getTorneo()
					.obtenerPosicionEquipo(pt.getVisitante());
			sc.actualizarPosicionFechaTorneo(posLocal, posVisitante, pt);
//			fc.actualizarDespuesPartido(pt);
//			fc.actualizarPorMes(pt);
		} else {
		
		}
	}

	@Timeout
	public void simularPartido(Timer t) throws NoExisteEquipoExcepcion {
		DatosMinutoPartido minutoDto = (DatosMinutoPartido) t.getInfo();
		Partido p = sc.find(minutoDto.getIdPartido());
		if (p == null) {
			throw new NoExisteEquipoExcepcion("No existe equipo de id "
					+ minutoDto.getIdPartido());
		}
		List<Integer> minutos = lsim.simular(p, minutoDto.getMinuto());
		if (minutos.size() > 0) {
			if (p.getLocal().getAlineacionDefecto() == null) {
				if (p.getAlineacionLocal() == null) {
					DatosAlineacion datosAlineacion = sc.crearAlineacion(p
							.getLocal());
					sc.setAlineacioPartido(datosAlineacion, p.getCodigo(), p
							.getLocal().getCodigo());
				}
			}
			if (p.getVisitante().getAlineacionDefecto() == null) {
				if (p.getAlineacionVisitante() == null) {
					DatosAlineacion datosAlineacion = sc.crearAlineacion(p
							.getVisitante());
					sc.setAlineacioPartido(datosAlineacion, p.getCodigo(), p
							.getVisitante().getCodigo());
				}
			}
			crearPartido(minutos, p);
		} else {
			if (minutoDto.isUltimaJugada()) {
//				if (p.getResultado().getGolesLocal() == 0 && p.getResultado().getGolesVisitante() == 0){
//					double r =Math.random();
//					if (r > 0.5){
//						pc.crearComentario("Por Suerte finaliza este martirio de partido, ambos equipos deben mejorar mucho para proximas actuaciones", p, 90);
//					}
//					else{
//						pc.crearComentario("Fin del partido, amargo 0 a 0", p, 90);
//					}
//				}
//				else{
//					pc.crearComentario("Fin del partido!!!", p, 90);
//				}
				this.actualizarDatosPartido(p);
				
				
			}
		}
	}

}
