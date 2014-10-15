package timers;

import java.util.Calendar;
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
import partidos.Partido;
import datatypes.DatosMinutoPartido;
import excepciones.NoExisteEquipoExcepcion;


@Stateless
@LocalBean
public class TimerSimularPartido {
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@Resource
	private TimerService ts;
	
	@Inject
	private LogicaSimulacion lsim;
	
	@Inject
	private PartidoControlador cp;
	
	public void crearTimerSimularPartido(DatosMinutoPartido mp,Calendar c){
		ts.createTimer(c.getTime(), mp);
	}
	
	private void crearPartido(List<Integer> minutos, Partido p){
		
			for (Integer min : minutos){
				Calendar fecha = new GregorianCalendar();
				fecha = p.getFechaHora();
				fecha.add(Calendar.MINUTE, min);
				crearTimerSimularPartido(new DatosMinutoPartido(min, p.getCodigo()),fecha);
			}
			if (p.getAlineacionLocal() == null){
				p.setAlineacionLocal(p.getLocal().getAlineacionDefecto());
			}
			if (p.getAlineacionVisitante() == null){
				p.setAlineacionVisitante(p.getVisitante().getAlineacionDefecto());
			}
			em.merge(p);
	}
	
	
	@Timeout
	public void simularPartido(Timer t) throws NoExisteEquipoExcepcion{
		DatosMinutoPartido minutoDto = (DatosMinutoPartido)t.getInfo();
		Partido p = cp.find(minutoDto.getIdPartido());
		if(p==null)
		{
			throw new NoExisteEquipoExcepcion("No existe equipo de id " + minutoDto.getIdPartido());
		}
		List<Integer> minutos = lsim.simular(p,minutoDto.getMinuto());
		if (minutos.size() > 0){
			crearPartido(minutos, p);
		}
	}

}
