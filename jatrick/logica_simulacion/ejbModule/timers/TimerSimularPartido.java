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
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import partido.LogicaSimulacion;
import partido.PartidoControlador;
import partido.SimulacionControlador;
import partidos.ConfiguracionPartido;
import partidos.Partido;
import partidos.PartidoCopa;
import partidos.PartidoTorneo;
import campeonato.Posicion;
import datatypes.DatosAlineacion;
import datatypes.DatosMinutoPartido;
import equipos.Equipo;
import excepciones.NoExisteEquipoExcepcion;
import finanzas.FinanzasControladorSimulacion;

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
	private FinanzasControladorSimulacion fc;
	
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
			p.setAlineacionLocal(sc.copiarAlineacion(p.getLocal().getAlineacionDefecto()));
		}
		if (p.getAlineacionVisitante() == null) {
			p.setAlineacionVisitante(sc.copiarAlineacion(p.getVisitante().getAlineacionDefecto()));
		}
		em.merge(p);
	}
	
	private Equipo obtenerGanador(PartidoTorneo pc){
		Equipo e = null;
		if (pc.getResultado().getGolesLocal() > pc.getResultado().getGolesVisitante()){
			e = pc.getLocal();
		}
		else if ((pc.getResultado().getGolesLocal() < pc.getResultado().getGolesVisitante())){
			e = pc.getVisitante();
		}
		return e;
	}
	
	private Equipo obtenerGanador(PartidoCopa pcc){
		Equipo e = null;
		if (pcc.getResultado().getGolesLocal() > pcc.getResultado().getGolesVisitante()){
			e = pcc.getLocal();
		}
		else if (pcc.getResultado().getGolesLocal() < pcc.getResultado().getGolesVisitante()){
			e = pcc.getVisitante();
		}
		else{
			double pL = Math.random()*5 +1;
			int penalesL = (int)pL;
			double pV = Math.random()*5 +1;
			int penalesV = (int)pV;
			sc.setPenalesPartido(pcc.getResultado(),penalesL,penalesV);
			if (penalesL > penalesV){
				pc.crearComentario("Pasa el "+pcc.getLocal().getNombre()+" tras vencer  en la ronda de penales por "+penalesL+" a "+penalesV, pcc, 90);
				e = pcc.getLocal();
			}
			else{
				pc.crearComentario("Pasa el "+pcc.getVisitante().getNombre()+" tras vencer  en la ronda de penales por "+penalesV+" a "+penalesL, pcc, 90);
				e = pcc.getVisitante();
			}
		}
		return e;
	}

	private void actualizarDatosPartido(Partido p) {
		sc.partidoFinalizado(p);
		PartidoTorneo pt = sc.findPartidoTorneo(p.getCodigo());	
		PartidoCopa pc = sc.findPartidoCopa(p.getCodigo());
		if (pt != null) {
			Posicion posLocal = ((PartidoTorneo) pt).getTorneo()
					.obtenerPosicionEquipo(pt.getLocal());
			Posicion posVisitante = ((PartidoTorneo) pt).getTorneo()
					.obtenerPosicionEquipo(pt.getVisitante());
			sc.actualizarPosicionFechaTorneo(posLocal, posVisitante, pt);
			fc.actualizarDespuesPartido(pt);
			fc.actualizarPorMes(pt);
			Equipo e = obtenerGanador(pt);
			if (e != null){
				e.setRanking(e.getRanking()+1);
			}
		} else if (pc != null){
			Equipo e = obtenerGanador(pc);
			e.setRanking(e.getRanking()+1);
			sc.actualizarCopa(pc,e);
		}
	}

	//@TransactionAttribute(TransactionAttributeType.NEVER)
	@Timeout
	public void simularPartido(Timer t) throws NoExisteEquipoExcepcion {
		System.out.println("CANTIDAD TIMERS ACTIVOS "+ts.getTimers().size());
		DatosMinutoPartido minutoDto = (DatosMinutoPartido) t.getInfo();
		Partido p = sc.find(minutoDto.getIdPartido());
		if (p == null) {
			throw new NoExisteEquipoExcepcion("No existe equipo de id "
					+ minutoDto.getIdPartido());
		}
		if (minutoDto.getMinuto() == -1){
			System.out.println("INICIO SIMULAR PARTIDO "+p.getCodigo()+" !!!!!!!");
		}
		else{
			System.out.println("INICIO SIMULACION DE MINUTO "+ minutoDto.getMinuto()+" DE PARTIDO "+p.getCodigo()+" !!!!!!!");
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
			System.out.println("FIN SIMULAR PARTIDO "+p.getCodigo()+" !!!!!!!");
		} else {
			if (minutoDto.isUltimaJugada()) {
				if (p.getResultado().getGolesLocal() == 0 && p.getResultado().getGolesVisitante() == 0){
					double r =Math.random();
					if (r > 0.5){
						pc.crearComentario("Por Suerte finaliza este martirio de partido, ambos equipos deben mejorar mucho para proximas actuaciones", p, 90);
					}
					else{
						pc.crearComentario("Fin del partido, amargo 0 a 0", p, 90);
					}
				}
				else{
					pc.crearComentario("FIN del tiempo reglamentario", p, 90);
				}
				this.actualizarDatosPartido(p);
			}
			System.out.println("FIN SIMULACION DE MINUTO "+ minutoDto.getMinuto()+" DE PARTIDO "+p.getCodigo()+" !!!!!!!");
		}
	}

}
