package partido;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import partidos.Comentario;
import partidos.Partido;
import timers.TimerSimularPartido;
import datatypes.DatosMinutoPartido;
import datatypes.EnumPartido;

@Stateless
@Local
public class LogicaSimulacion{
	
	@Inject
	private TimerSimularPartido tsp;
	
	private List<Integer> minutosJugada(){
		List<Integer> minutos = new LinkedList<Integer>();
		int jugadas = (int)Math.random()*10 +10;
		while(minutos.size() != jugadas){
			Integer min = new Integer((int)Math.random()*90);
			if (!minutos.contains(min)){
				minutos.add(min);
			}
		}
		return minutos;
	}
	//
	private void simularPartido(Partido p){
		List<Integer> minutos = this.minutosJugada();
		for (Integer min : minutos){
			Calendar fecha = new GregorianCalendar();
			fecha = p.getFechaHora();
			fecha.add(Calendar.MINUTE, min);
			tsp.crearTimerSimularPartido(new DatosMinutoPartido(min, p.getCodigo()),fecha);
		}
	}
	
	//
	private void simularJugada(Partido p, int minuto){
		//TODO Hace la logica de simular una jugada
		/**
		 * probabildad de jugada de gol =  
(Promedio (RegateATs+RegateMEDs) – Promedio(PotenciaMEDs+PotenciaDEFs))/100  

RegateATs = Sumatoria de la habilidad de regate de todos los delanteros  
RegateMEDs = Sumatoria de la habilidad de regate de todos los mediocampistas  
PotenciaDEFs = Sumatoria de la habilidad de potencia de todos los defensas  
PotenciaMEDs = Sumatoria de la habilidad de potencia de todos los mediocampistas  
		 */
//		ProbJugadaGol = ();
//		p.getAlineacionLocal().get
		Comentario com = new Comentario(1, "se escapa por la punta y levanta el centro\n directo a las gradas, se ve que es de las escuela del futbol uruguayo ", p, minuto);
	}
	
	public void simular(Partido p, int minuto)
	{
		if(p.getEstado().equals(EnumPartido.POR_JUGAR)){
			this.simularPartido(p);
		}
		else if (p.getEstado().equals(EnumPartido.JUGANDO)){
			this.simularJugada(p, minuto);
		}
	}
	
}
	