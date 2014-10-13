package partido;

import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import jugadores.Jugador;
import partidos.ConfiguracionPartido;
import partidos.Partido;
import timers.TimerSimularPartido;
import datatypes.DatosMinutoPartido;
import datatypes.EnumPartido;
import equipos.Alineacion;

@Stateless
@Local
public class LogicaSimulacion{
	
	@Inject
	private TimerSimularPartido tsp;
	
	@Inject
	PartidoControlador pc;
	
	private List<Integer> minutosJugada(){
		List<Integer> minutos = new LinkedList<Integer>();
		ConfiguracionPartido cp = pc.findConfiguracionPartido();
		int jugadas = cp.getCantidadJugadas();
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
	/** probabildad de jugada de gol =  
	(Promedio (RegateATs+RegateMEDs) – Promedio(PotenciaMEDs+PotenciaDEFs))/100  
	RegateATs = Sumatoria de la habilidad de regate de todos los delanteros  
	RegateMEDs = Sumatoria de la habilidad de regate de todos los mediocampistas  
	PotenciaDEFs = Sumatoria de la habilidad de potencia de todos los defensas  
	PotenciaMEDs = Sumatoria de la habilidad de potencia de todos los mediocampistas  
	 * @param alineacionLocal
	 * @param alineacionVisitante
	 * @return
	 */
	private long probabilidadJugadaGol(Alineacion alineacionLocal, Alineacion alineacionVisitante){
		int RegateATs = 0;
		int RegateMEDs = 0;
		int PotenciaDEFs = 0;
		int PotenciaMEDs = 0;
		for(Jugador j : alineacionLocal.getDelanteros()){
			RegateATs += j.getTecnica();
		}
		for(Jugador j : alineacionLocal.getMediocampistas()){
			RegateMEDs += j.getTecnica();
		}
		for(Jugador j : alineacionVisitante.getDefensas()){
			PotenciaDEFs += j.getDefensa();
		}
		for(Jugador j : alineacionVisitante.getMediocampistas()){
			PotenciaMEDs += j.getDefensa();
		}
		long promLocal = (RegateATs + RegateMEDs)/(alineacionLocal.getDelanteros().size() + alineacionLocal.getMediocampistas().size());
		long promVisitante = (PotenciaDEFs + PotenciaMEDs)/(alineacionVisitante.getDefensas().size() + alineacionVisitante.getMediocampistas().size());
		
		return Math.abs((promLocal - promVisitante)/100);
	}
	
	public long probabilidadGol(Alineacion alineacionAtaca, Jugador golero){
		/**
		probabilidad de gol =  
				((probabilidad de jugada gol) x (tiro de un jugador*factor_aleatorio_ataque ­ habilidad del  
				portero*factor_aleatorio_portero))/100  
				 
				El   tiro   a   gol   debe   ser   realizado   por   un   único   jugador,   por   lo   cual   es   necesario   definirlo.   Dado   que  
				un   delantero   tiene   mayor   probabilidad   de   gol   que   un   defensa   o   mediocampista,   se   definen   las  
				siguientes probabilidades para la selección del jugador:  
				● probabilidad que salga un delantero: 60%  
				● probabilidad que salga un mediocampista: 30%  
				● probabilidad que salga un defensa: 10%  
				Una   vez   determinado   el   tipo   de   jugador   (atacante,   mediocampista   o   defensa)   que   realiza   el  
				disparo,   se   debe   determinar   de   alguna   forma   (puede   ser   aleatoria),   cual   de   todos   es   el   que  
				efectivamente realiza el tiro a gol. La forma de determinar este jugador es libre a cada grupo.  
				*/
		return 0;
	}
	
	public long probabilidadTarjeta(){
		/**
		 * Probabilidad de tarjeta = Oportunidad de gol x (promedio potencia de jugadores defensores y 
		mediocampistas)  
		La determinación de cuál de todos los jugadores recibió la tarjeta es de forma aleatoria.  
		En caso de que un jugador reciba dos tarjetas amarillas, este queda expulsado influyendo en el  
		desarrollo   del   encuentro.   Cada   jugador   expulsado   incide   en   el   equipo   penalizando   todas   sus  
		jugadas   un   10%,   tal   cual   sucede   con   el   factor   altura.   Por   ejemplo,   un   jugador   expulsado   incide  
		en un 10%, dos en un 20%, etc.  
		 */
		return 0;
	}
	
	private void simularJugada(Partido p, int minuto){
		//TODO Hace la logica de simular una jugada

		Alineacion alineacionLocal = p.getAlineacionLocal();
		Alineacion alineacionVisitante = p.getAlineacionVisitante();
		long probJGL = probabilidadJugadaGol(alineacionLocal,alineacionVisitante);
		long probJGV = probabilidadJugadaGol(alineacionVisitante,alineacionLocal);
		double prob = Math.random();
		boolean chanceLocal = probJGL + prob > probJGV + (1- prob);
		if (chanceLocal){
			probabilidadGol(alineacionLocal,alineacionVisitante.getGolero());
		}
		else{
			probabilidadGol(alineacionVisitante,alineacionLocal.getGolero());
		}
		
		pc.crearComentario("se escapa por la punta y levanta el centro\n directo a las gradas, se ve que es de las escuela del futbol uruguayo ", p, minuto);
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
	