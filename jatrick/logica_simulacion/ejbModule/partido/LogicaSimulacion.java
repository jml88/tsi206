package partido;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.inject.Inject;

import campeonato.Posicion;
import jugadores.Jugador;
import partidos.ConfiguracionPartido;
import partidos.Partido;
import partidos.PartidoCopa;
import partidos.PartidoTorneo;
import datatypes.DatosAlineacion;
import datatypes.EnumPartido;
import equipos.Alineacion;
import equipos.Equipo;
import excepciones.NoExisteEquipoExcepcion;
import finanzas.FinanzasControladorSimulacion;

@Stateless
@Local
public class LogicaSimulacion {

	// @Inject
	// private TimerSimularPartido tsp;

	@Inject
	PartidoControlador pc;

	@Inject
	SimulacionControlador sc;
	
	@Inject
	private FinanzasControladorSimulacion fc;

	private List<Integer> minutosJugada() {
		List<Integer> minutos = new LinkedList<Integer>();
		ConfiguracionPartido cp = pc.findConfiguracionPartido();
		int jugadas = cp.getCantidadJugadas();
		while (minutos.size() != jugadas) {
			double d = (Math.random() * 90) + 1;
			int min = (int) d;
			Integer mi = new Integer(min);
			if (!minutos.contains(mi)) {
				minutos.add(mi);
			}
		}
		return minutos;
	}

	//
	private List<Integer> simularPartido(Partido p) {
		return this.minutosJugada();

	}

	//
	/**
	 * probabildad de jugada de gol = (Promedio (RegateATs+RegateMEDs) –
	 * Promedio(PotenciaMEDs+PotenciaDEFs))/100 RegateATs = Sumatoria de la
	 * habilidad de regate de todos los delanteros RegateMEDs = Sumatoria de la
	 * habilidad de regate de todos los mediocampistas PotenciaDEFs = Sumatoria
	 * de la habilidad de potencia de todos los defensas PotenciaMEDs =
	 * Sumatoria de la habilidad de potencia de todos los mediocampistas
	 * 
	 * @param alineacionLocal
	 * @param alineacionVisitante
	 * @return
	 */
	private double probabilidadJugadaGol(Alineacion alineacionLocal,
			Alineacion alineacionVisitante) {
		int RegateATs = 1;
		int RegateMEDs = 1;
		int PotenciaDEFs = 1;
		int PotenciaMEDs = 1;

		List<Jugador> delanterosL = quitarSancionadosYLesionados(alineacionLocal
				.getDelanteros());
		List<Jugador> mediosL = quitarSancionadosYLesionados(alineacionLocal
				.getMediocampistas());
		List<Jugador> defensasL = quitarSancionadosYLesionados(alineacionLocal
				.getDefensas());

		List<Jugador> defensasV = quitarSancionadosYLesionados(alineacionVisitante
				.getDefensas());

		List<Jugador> mediosV = quitarSancionadosYLesionados(alineacionVisitante
				.getMediocampistas());

		for (Jugador j : delanterosL) {
			RegateATs += j.getTecnica();
		}
		for (Jugador j : mediosL) {
			RegateMEDs += j.getTecnica();
		}
		for (Jugador j : defensasL) {
			PotenciaDEFs += j.getDefensa();
		}
		for (Jugador j : defensasV) {
			PotenciaMEDs += j.getDefensa();
		}
		long promLocal = (RegateATs + RegateMEDs)
				/ (delanterosL.size() + mediosL.size());
		long promVisitante = (PotenciaDEFs + PotenciaMEDs)
				/ (defensasV.size() + mediosV.size());

		return Math.abs((promLocal - promVisitante) / 100);
	}

	public Object[] probabilidadGol(Alineacion alineacionAtaca, Jugador golero,
			double probJugadaGol) {
		/**
		 * probabilidad de gol = ((probabilidad de jugada gol) x (tiro de un
		 * jugador*factor_aleatorio_ataque ­ habilidad del -
		 * portero*factor_aleatorio_portero))/100
		 * 
		 * El tiro a gol debe ser realizado por un único jugador, por lo cual es
		 * necesario definirlo. Dado que un delantero tiene mayor probabilidad
		 * de gol que un defensa o mediocampista, se definen las siguientes
		 * probabilidades para la selección del jugador: ● probabilidad que
		 * salga un delantero: 60% ● probabilidad que salga un mediocampista:
		 * 30% ● probabilidad que salga un defensa: 10% Una vez determinado el
		 * tipo de jugador (atacante, mediocampista o defensa) que realiza el
		 * disparo, se debe determinar de alguna forma (puede ser aleatoria),
		 * cual de todos es el que efectivamente realiza el tiro a gol. La forma
		 * de determinar este jugador es libre a cada grupo.
		 */
		double probDel = Math.random() * 6;
		double probMed = Math.random() * 3;
		double probDef = Math.random() * 1;
		Jugador jugador = null;

		jugador = alineacionAtaca.getMediocampistas().get(0);

		List<Jugador> delanterosL = quitarSancionadosYLesionados(alineacionAtaca
				.getDelanteros());
		List<Jugador> mediosL = quitarSancionadosYLesionados(alineacionAtaca
				.getMediocampistas());
		List<Jugador> defensasL = quitarSancionadosYLesionados(alineacionAtaca
				.getDefensas());

		if (probDel >= probMed && probDel >= probDef) {
			jugador = eligoJugadorGol(delanterosL);
		} else if (probMed > probDel && probMed >= probDef) {
			jugador = eligoJugadorGol(mediosL);
		} else {
			jugador = eligoJugadorGol(defensasL);
		}
		double probablidadGol = ((probJugadaGol) * (jugador.getAtaque()
				* Math.random() - golero.getPorteria() * Math.random())) / 100;
		// String[] s = new String[2];
		Object[] ob = new Object[2];
		ob[0] = Math.random();
		ob[1] = jugador;
		return ob;
	}

	private void probabilidadLesion(Alineacion alineacionLocal,
			Alineacion alineacionVisitante, Partido p, int minuto) {
		List<Jugador> delanterosL = quitarSancionadosYLesionados(alineacionLocal
				.getDelanteros());
		List<Jugador> mediosL = quitarSancionadosYLesionados(alineacionLocal
				.getMediocampistas());
		List<Jugador> defensasL = quitarSancionadosYLesionados(alineacionLocal
				.getDefensas());

		Jugador ju = null;

		ju = alineacionLocal.getMediocampistas().get(0);

		if (Math.random() > 0.5) {
			double d = Math.random() * 3;
			int j = (int) d;
			if (j == 1) {
				ju = eligoJugadorGol(defensasL);
			} else if (j == 2) {
				ju = eligoJugadorGol(mediosL);
			} else {
				ju = eligoJugadorGol(delanterosL);
			}

		} else {

			double d = Math.random() * 3;
			int j = (int) d;
			if (j == 1) {
				ju = eligoJugadorGol(defensasL);
			} else if (j == 2) {
				ju = eligoJugadorGol(mediosL);
			} else {
				ju = eligoJugadorGol(delanterosL);
			}

		}
		if (Math.random() > 0.96) {
			pc.crearComentarioLesion(ju, p, minuto);
			ju.setLesion(3);
		}
	}

	private Jugador eligoJugadorGol(List<Jugador> jugadores) {
		if (jugadores.size() == 0) {
			return null;
		}
		int cantidad = jugadores.size();
		double d = (Math.random() * cantidad);
		int j = (int) d;
		return jugadores.get(j);

	}

	private List<Jugador> quitarSancionadosYLesionados(List<Jugador> jugadores) {
		List<Jugador> jug = new LinkedList<Jugador>();
		for (Jugador j : jugadores) {
			if (j.getSancionLiga() == null && j.getLesion() == null) {
				jug.add(j);
			}
		}
		return jug;
	}

	public long probabilidadTarjeta(Alineacion alineacionLocal,
			Alineacion alineacionVisitante, Partido p, int minuto,
			boolean jugadaGol) {
		/**
		 * Probabilidad de tarjeta = Oportunidad de gol x (promedio potencia de
		 * jugadores defensores y mediocampistas) La determinación de cuál de
		 * todos los jugadores recibió la tarjeta es de forma aleatoria. En caso
		 * de que un jugador reciba dos tarjetas amarillas, este queda expulsado
		 * influyendo en el desarrollo del encuentro. Cada jugador expulsado
		 * incide en el equipo penalizando todas sus jugadas un 10%, tal cual
		 * sucede con el factor altura. Por ejemplo, un jugador expulsado incide
		 * en un 10%, dos en un 20%, etc.
		 */
		/** retorno = 1 tiro libre, retorno = 2 penal **/
		int retorno = 0;
		List<Jugador> delanterosL = new LinkedList<Jugador>();
		List<Jugador> mediosL = new LinkedList<Jugador>();
		List<Jugador> defensasL = new LinkedList<Jugador>();
		List<Jugador> delanterosV = new LinkedList<Jugador>();
		List<Jugador> mediosV = new LinkedList<Jugador>();
		List<Jugador> defensasV = new LinkedList<Jugador>();

		delanterosL = quitarSancionadosYLesionados(alineacionLocal
				.getDelanteros());
		delanterosV = quitarSancionadosYLesionados(alineacionVisitante
				.getDelanteros());
		mediosL = quitarSancionadosYLesionados(alineacionLocal
				.getMediocampistas());
		mediosV = quitarSancionadosYLesionados(alineacionVisitante
				.getMediocampistas());
		defensasL = quitarSancionadosYLesionados(alineacionLocal.getDefensas());
		defensasV = quitarSancionadosYLesionados(alineacionVisitante
				.getDefensas());

		double probDelL = Math.random() * 1;
		double probMedL = Math.random() * 3;
		double probDefL = Math.random() * 6;
		Jugador jugadorL = null;

		if (probDelL >= probMedL && probDelL >= probDefL) {
			jugadorL = eligoJugadorGol(delanterosL);
		} else if (probMedL > probDelL && probMedL >= probDefL) {
			jugadorL = eligoJugadorGol(mediosL);
		} else {
			jugadorL = eligoJugadorGol(defensasL);
		}
		if (jugadorL == null){
			jugadorL = defensasL.get(0);
		}
		double probDelV = Math.random() * 1;
		double probMedV = Math.random() * 3;
		double probDefV = Math.random() * 6;
		Jugador jugadorV = null;
		if (probDelV >= probMedV && probDelV >= probDefV) {
			jugadorV = eligoJugadorGol(delanterosV);
		} else if (probMedV > probDelV && probMedV >= probDefV) {
			jugadorV = eligoJugadorGol(mediosV);
		} else {
			jugadorV = eligoJugadorGol(defensasV);
		}

		double pT = Math.random();
		if (((pT > 0.3) && (pT < 0.7) && !jugadaGol)
				|| (jugadaGol && pT > 0.75 && pT <= 0.9)) {
			pc.crearComentarioAmarilla(jugadorV, jugadorL, p, minuto);
			if (jugadorV.getTarjetasPartido() == null) {
				jugadorV.setTarjetasPartido(2);

			} else if (jugadorV.getTarjetasPartido() == 1) {
				jugadorV.setTarjetasPartido(2);
				jugadorV.setSancionLiga(1);
			}
			if (Math.random() > 0.9) {
				pc.crearComentarioLesion(jugadorL, p, minuto);
				jugadorL.setLesion(1);
			}
			retorno = 1;

		} else if ((!jugadaGol && (pT > 0.7)) || (jugadaGol && (pT > 0.93))) {
			pc.crearComentarioRoja(jugadorV, jugadorL, p, minuto);
			jugadorV.setSancionLiga(2);
			if (Math.random() > 0.9) {
				pc.crearComentarioLesion(jugadorL, p, minuto);
				jugadorL.setLesion(1);
			}
			retorno = 2;
		} else if ((!jugadaGol) || (jugadaGol && pT > 0.6)) {
			pc.crearComentarioFalta(jugadorV, jugadorL, p, minuto);
		}
		return retorno;
	}

	private void simularJugada(Partido p, int minuto) {
		// TODO Hace la logica de simular una jugada
		PartidoTorneo pt = sc.findPartidoTorneo(p.getCodigo());
		PartidoCopa pCopa = sc.findPartidoCopa(p.getCodigo());
		Alineacion alineacionLocal = p.getAlineacionLocal();
		Alineacion alineacionVisitante = p.getAlineacionVisitante();
		double probJGL = probabilidadJugadaGol(alineacionLocal,
				alineacionVisitante);
		double probJGV = probabilidadJugadaGol(alineacionVisitante,
				alineacionLocal);
		double prob = Math.random();
		double tipoJugadaD = Math.random() * 10;
		int tipoJugada = (int) tipoJugadaD;

		if (tipoJugada == 0) {
			probabilidadLesion(alineacionLocal, alineacionVisitante, p, minuto);
		} else if ((tipoJugada == 1) && (tipoJugada == 2)) {
			probabilidadTarjeta(alineacionLocal, alineacionLocal, p, minuto,
					false);
		} else {

			boolean chanceLocal = probJGL + prob > probJGV + (1 - prob);
			if (chanceLocal) {
				long probT = probabilidadTarjeta(alineacionLocal,
						alineacionVisitante, p, minuto, true);

				Object[] objt = probabilidadGol(alineacionLocal,
						alineacionVisitante.getGolero(), probJGL);
				double probG = (double) objt[0];
				Jugador jL = (Jugador) objt[1];

				if (probG > 0.8) {

					pc.crearComentarioGol((int) probT, jL, p
							.getAlineacionVisitante().getGolero(),
							p.getLocal(), p, minuto);

					pc.sumarGolLocal(p.getResultado(), jL);
					if (pt != null) {
						jL.setGolesLiga(jL.getGolesLiga() + 1);
					}
					else if (pCopa != null){
						jL.setGolesCopa(jL.getGolesCopa() + 1);
					}
				} else {
					pc.crearComentarioJugadaGol((int) probT, jL, p
							.getAlineacionVisitante().getGolero(),
							p.getLocal(), p, minuto);
				}
			} else {
				long probT = probabilidadTarjeta(alineacionVisitante,
						alineacionLocal, p, minuto, true);
				Object[] objt = probabilidadGol(alineacionVisitante,
						alineacionLocal.getGolero(), probJGV);
				double probG = (double) objt[0];
				Jugador jV = (Jugador) objt[1];
				if (probG > 0.8) {
					pc.crearComentarioGol((int) probT, jV, p
							.getAlineacionLocal().getGolero(),
							p.getVisitante(), p, minuto);
					pc.sumarGolVisitante(p.getResultado(), jV);
					if (pt != null) {
						jV.setGolesLiga(jV.getGolesLiga() + 1);
					}else if (pCopa != null){
							jV.setGolesCopa(jV.getGolesCopa() + 1);
						}
				} else {
					pc.crearComentarioJugadaGol((int) probT, jV, p
							.getAlineacionLocal().getGolero(),
							p.getVisitante(), pt, minuto);
				}
			}
		}
	}

	public List<Integer> simular(Partido p, int minuto) {
		List<Integer> minutos = new LinkedList<Integer>();
		if (p.getEstado().equals(EnumPartido.POR_SIMULAR)) {
			pc.partidoEnJuego(p);
			minutos = this.simularPartido(p);
		} else if (p.getEstado().equals(EnumPartido.JUGANDO)) {
			this.simularJugada(p, minuto);
		}
		return minutos;
	}

	public void simularPartidoCompleto(Partido p) throws NoExisteEquipoExcepcion {
		if (p == null) {
			throw new NoExisteEquipoExcepcion("No existe partido de id ");
		}
		List<Integer> minutos = simularPartido(p);
		Collections.sort(minutos);
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
		for (Integer minuto : minutos) {
			this.simularJugada(p, minuto);
		}
		if (p.getResultado().getGolesLocal() == 0
				&& p.getResultado().getGolesVisitante() == 0) {
			double r = Math.random();
			if (r > 0.5) {
				pc.crearComentario(
						"Por Suerte finaliza este martirio de partido, ambos equipos deben mejorar mucho para proximas actuaciones",
						p, 90);
			} else {
				pc.crearComentario("Fin del partido, amargo 0 a 0", p,
						90);
			}
		} else {
			pc.crearComentario("FIN del tiempo reglamentario", p, 90);
		}
		this.actualizarDatosPartido(p);
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

}
