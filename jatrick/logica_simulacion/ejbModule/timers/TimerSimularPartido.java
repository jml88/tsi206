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
import javax.ejb.TimerConfig;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import partido.LogicaSimulacion;
import partido.PartidoControlador;
import partidos.Partido;
import partidos.PartidoTorneo;
import campeonato.Posicion;
import datatypes.DatosAlineacion;
import datatypes.DatosMinutoPartido;
import excepciones.NoExisteEquipoExcepcion;

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
	private PartidoControlador cp;

	// @Inject
	// ConfiguracionControlador cc;

	public void crearTimerSimularPartido(DatosMinutoPartido mp, Calendar c,
			int hora, int minuto) {
		Date d = c.getTime();
		d.setHours(hora);
		d.setMinutes(minuto);
		ts.createTimer(d, mp);
	}

	private void crearPartido(List<Integer> minutos, Partido p) {
		Calendar fecha = new GregorianCalendar();
		Collections.sort(minutos);
		int ultimoMinuto = minutos.get(minutos.size() - 1);
		for (Integer min : minutos) {
			fecha = (Calendar) p.getFechaHora().clone();
			fecha.add(Calendar.MINUTE, min);
			crearTimerSimularPartido(new DatosMinutoPartido(min, p.getCodigo(),
					min == ultimoMinuto), fecha,
					fecha.get(Calendar.HOUR_OF_DAY), fecha.get(Calendar.MINUTE));
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
		cp.partidoFinalizado(p);
		PartidoTorneo pt = cp.findPartidoTorneo(p.getCodigo());	
		if (pt != null) {
			if (pt.getTorneo().isUltimoPartidoTorneo()){
				cp.actualizarDatosTorneo(pt.getTorneo());	
			}
			
			Posicion posLocal = ((PartidoTorneo) pt).getTorneo()
					.obtenerPosicionEquipo(pt.getLocal());
			Posicion posVisitante = ((PartidoTorneo) pt).getTorneo()
					.obtenerPosicionEquipo(pt.getVisitante());
			cp.actualizarPosicionFechaTorneo(posLocal, posVisitante, pt);

		} else {
		
		}
	}

	@Timeout
	public void simularPartido(Timer t) throws NoExisteEquipoExcepcion {
		DatosMinutoPartido minutoDto = (DatosMinutoPartido) t.getInfo();
		Partido p = cp.find(minutoDto.getIdPartido());
		if (p == null) {
			throw new NoExisteEquipoExcepcion("No existe equipo de id "
					+ minutoDto.getIdPartido());
		}
		List<Integer> minutos = lsim.simular(p, minutoDto.getMinuto());
		if (minutos.size() > 0) {
			if (p.getLocal().getAlineacionDefecto() == null) {
				if (p.getAlineacionLocal() == null) {
					DatosAlineacion datosAlineacion = cp.crearAlineacion(p
							.getLocal());
					cp.setAlineacioPartido(datosAlineacion, p.getCodigo(), p
							.getLocal().getCodigo());
				}
			}
			if (p.getVisitante().getAlineacionDefecto() == null) {
				if (p.getAlineacionVisitante() == null) {
					DatosAlineacion datosAlineacion = cp.crearAlineacion(p
							.getVisitante());
					cp.setAlineacioPartido(datosAlineacion, p.getCodigo(), p
							.getVisitante().getCodigo());
				}
			}
			crearPartido(minutos, p);
		} else {
			if (minutoDto.isUltimaJugada()) {
				this.actualizarDatosPartido(p);
			}
		}
	}

}
