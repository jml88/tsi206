package partido;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import jugadores.Jugador;
import partidos.ConfiguracionPartido;
import partidos.Partido;
import partidos.PartidoTorneo;
import datatypes.EnumPartido;
import equipos.Alineacion;

@Stateless
@Local
public class LogicaSimulacion{
	
//	@Inject
//	private TimerSimularPartido tsp;
	
	@Inject
	PartidoControlador pc;
	
	@Inject
	SimulacionControlador sc;
	
	private List<Integer> minutosJugada(){
		List<Integer> minutos = new LinkedList<Integer>();
		ConfiguracionPartido cp = pc.findConfiguracionPartido();
		int jugadas = cp.getCantidadJugadas();
		while(minutos.size() != jugadas){
			double d = (Math.random()*cp.getDuracion()) +1;
			int min = (int)d;
			Integer mi = new Integer(min);
			if (!minutos.contains(mi)){
				minutos.add(mi);
			}
		}
		return minutos;
	}
	//
	private List<Integer> simularPartido(Partido p){
		return  this.minutosJugada();
		
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
	private double probabilidadJugadaGol(Alineacion alineacionLocal, Alineacion alineacionVisitante){
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
	
	public Object[] probabilidadGol(Alineacion alineacionAtaca, Jugador golero, double probJugadaGol){
		/**
		probabilidad de gol =  
				((probabilidad de jugada gol) x (tiro de un jugador*factor_aleatorio_ataque ­ habilidad del -
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
		double probDel = Math.random()*6;
		double probMed = Math.random()*3;
		double probDef = Math.random()*1;
		Jugador jugador = null;
		if (probDel >= probMed && probDel >= probDef){
			jugador = eligoJugadorGol(alineacionAtaca.getDelanteros());
		}
		else if (probMed > probDel && probMed >= probDef){
			jugador = eligoJugadorGol(alineacionAtaca.getMediocampistas());
		}
		else{
			jugador = eligoJugadorGol(alineacionAtaca.getDefensas());
		}
		double probablidadGol = ((probJugadaGol) * (jugador.getAtaque()*Math.random() - golero.getPorteria()*Math.random()))/100;
//		String[] s = new String[2];
		Object[] ob = new Object[2];
		ob[0] = Math.random();
		ob[1] = jugador;
		return ob;
	}
	
	private Jugador eligoJugadorGol(List<Jugador> jugadores) {
		//TODO hacerla bien, hacerla ahora
		return jugadores.get(0);
		
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
		PartidoTorneo pt = sc.findPartidoTorneo(p.getCodigo());
		Alineacion alineacionLocal = p.getAlineacionLocal();
		Alineacion alineacionVisitante = p.getAlineacionVisitante();
		double probJGL = probabilidadJugadaGol(alineacionLocal,alineacionVisitante);
		double probJGV = probabilidadJugadaGol(alineacionVisitante,alineacionLocal);
		double prob = Math.random();
		boolean chanceLocal = probJGL + prob > probJGV + (1- prob);
		if (chanceLocal){
			Object[] objt = probabilidadGol(alineacionLocal,alineacionVisitante.getGolero(), probJGL);
			double probG = (double) objt[0];
			Jugador jL = (Jugador)objt[1];
			if ( probG > 0.7){
				pc.crearComentario("Pared al borde del área para " + p.getLocal().getNombre() + "  Chuta y... GOOOOOLLLLL!!	", p, minuto);	
				pc.sumarGolLocal(p.getResultado(), jL);
				if (pt != null){
					jL.setGolesLiga(jL.getGolesLiga()+1);
				}
			}
			else{
				pc.crearComentario("se escapa por la punta y levanta el centro\n directo a las gradas, se ve que es de las escuela del futbol uruguayo ", p, minuto);
			}
		}
		else{
			Object[] objt = probabilidadGol(alineacionVisitante,alineacionLocal.getGolero(),probJGV);
			double probG = (double) objt[0];
			Jugador jV = (Jugador)objt[1];
			if (probG > 0.7){
				pc.crearComentario("Despiste en el area aprovechado por el ataque, GO GO GO GOOOLLLL!!", p, minuto);	
				pc.sumarGolVisitante(p.getResultado(), jV);
				if (pt != null){
					jV.setGolesLiga(jV.getGolesLiga()+1);
				}
			}
			else{
				pc.crearComentario("se escapa por la punta y levanta el centro\n directo a las gradas, se ve que es de las escuela del futbol uruguayo ", p, minuto);		
			}
		}
		
		
	}
	
	public List<Integer> simular(Partido p, int minuto)
	{
		List<Integer> minutos = new LinkedList<Integer>();
		if(p.getEstado().equals(EnumPartido.POR_SIMULAR)){
			pc.partidoEnJuego(p);
			minutos = this.simularPartido(p);
		}
		else if (p.getEstado().equals(EnumPartido.JUGANDO)){
			this.simularJugada(p, minuto);
		}
		return minutos;
	}
	
}
	