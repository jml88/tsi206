package timers;

import java.util.Calendar;

import javax.ejb.Timeout;
import javax.ejb.Timer;
import javax.ejb.TimerService;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import datatypes.DatosMinutoPartido;
import excepciones.NoExisteEquipoExcepcion;
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
	
	@Inject
	PartidoControlador cp;
	
	public void crearTimerSimularPartido(DatosMinutoPartido mp,Calendar c){
		ts.createTimer(c.getTime(), mp);
	}
	
	@Timeout
	public void simularPartido(Timer t) throws NoExisteEquipoExcepcion{
		DatosMinutoPartido minutoDto = (DatosMinutoPartido)t.getInfo();
		Partido p = cp.find(minutoDto.getIdPartido());
		if(p==null)
		{
			throw new NoExisteEquipoExcepcion("No existe equipo de id " + minutoDto.getIdPartido());
		}
		lsim.simular(p,minutoDto.getMinuto());
	}

}
