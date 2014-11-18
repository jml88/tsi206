package finanzas;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.sun.javafx.collections.ListListenerHelper;

import jugadores.Jugador;
import partidos.Partido;
import campeonato.Posicion;
import campeonato.Torneo;
import configuracion.ConfiguracionControladorLocal;
import datatypes.EnumTipoTransaccion;
import equipos.Equipo;
import excepciones.NoExisteEquipoExcepcion;

@Stateless
@Named
public class FinanzasControlador {
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@Inject
	ConfiguracionControladorLocal conf;
	
	
	public int calcularSueldoJugador(Jugador j){
		
		return j.calcularSueldoJugador();
	}
	
	public void ingresarTransaccion(Equipo e, int monto, EnumTipoTransaccion tipoTransaccion){
		Finanzas finanzas = new Finanzas(monto, Calendar.getInstance().getTime(), e, tipoTransaccion);
		em.persist(finanzas);
	}
	
	public void pagarSueldosAEquipo(Equipo e){
		List<Jugador> jugadores = new LinkedList<Jugador>();
		
		jugadores.addAll(e.getPlantel());
		
		int capital = e.getCapital();
		int dineroTransaccion = 0;
		for (Jugador jugador : jugadores){
			capital -= jugador.getSalario();
			dineroTransaccion = jugador.getSalario();
		}
		
		ingresarTransaccion(e,-dineroTransaccion, EnumTipoTransaccion.SUELDOS_JUGADORES);
		e.setCapital(capital);
		em.merge(e);
	}
	
	public Torneo obtenerTorneoActual(int codEquipo) throws NoExisteEquipoExcepcion {
		Equipo e = em.find(Equipo.class,codEquipo);
		if(e==null){
			throw new NoExisteEquipoExcepcion("No existe el equipo de id: " + codEquipo);
		}
		
		List<Torneo> torneos = e.getTorneos();
		if(torneos == null){
			return null;
		}
		Torneo torneoMasActual = torneos.get(0);
		for (Torneo torneo : torneos) {
			
			if(torneoMasActual.getFechaDeArranque().compareTo(torneo.getFechaDeArranque())>0  ){
				torneoMasActual = torneo; 
			}
		}
		
		return torneoMasActual;
		
	}
	
	
	public void generarPublicidad(Equipo e) throws NoExisteEquipoExcepcion, NamingException{
Torneo t = obtenerTorneoActual(e.getCodigo());
		
		Posicion p = (Posicion)em.createQuery("Select p from Posicion p where p.equipo.codigo = :equipo AND p.torneo.codigo = :torneo")
				.setParameter("equipo", e.getCodigo())
				.setParameter("torneo", t.getCodigo())
				.getSingleResult();
		int pos = ordenarPosiciones(t).indexOf(p);
		int cantidadCuadros = conf.getConfiguracion().getCantEquipoTorneo();
		
		if(pos/(cantidadCuadros/3) == 0){
			e.setPublicidad(100);
		}
		else if (pos/(cantidadCuadros/3) == 1){
			e.setPublicidad(50);
		}
		else{
			e.setPublicidad(30);
		}
		em.merge(e);
	}
	
	public void cobrarPublicidad(Equipo e){
		//TODO
		e.setCapital(e.getCapital() + e.getPublicidad());
		em.merge(e);
		ingresarTransaccion(e,e.getPublicidad(), EnumTipoTransaccion.PUBLICIDAD);
	}
	
	public void cobrarEntradasPartido(Partido p){
		Equipo equipoLocal = p.getLocal();
		int cantidadEspectadores = equipoLocal.getSeguidores() + p.getVisitante().getSeguidores();
		if(cantidadEspectadores > equipoLocal.getEstadio().getCapacidad()){
			cantidadEspectadores = equipoLocal.getEstadio().getCapacidad();
		}
		//las entradas valen uno
		equipoLocal.setCapital(equipoLocal.getCapital() + cantidadEspectadores);
		em.merge(equipoLocal);
		ingresarTransaccion(equipoLocal, cantidadEspectadores, EnumTipoTransaccion.ENTRADA_PARTIDO);
	}
	
	public void cobrarCuotaDeSocios(Equipo e){
		//TODO manejar mas o menos de la misma manera la cantidad de socios 
		e.setCapital(e.getCapital()+e.getSocios());
		em.merge(e);
		ingresarTransaccion(e,e.getPublicidad(), EnumTipoTransaccion.SOCIOS);
		
	}
	
	
	public void actualizarCantidadSeguidoresCuadro(Partido p){
		int cantidadSeguidoresLocal = p.getLocal().getSeguidores();
		int cantidadSeguidoresVisitante = p.getVisitante().getSeguidores();
		Equipo equipoLocal = p.getLocal();
		Equipo equipoVisitante = p.getVisitante();
		if(p.getResultado().getGolesLocal() > p.getResultado().getGolesVisitante()){
			cantidadSeguidoresLocal = (int)Math.round(cantidadSeguidoresLocal*1.1);
			if(cantidadSeguidoresLocal > equipoLocal.getEstadio().getCapacidad()){
				cantidadSeguidoresLocal = equipoLocal.getEstadio().getCapacidad();
			}
			equipoLocal.setSeguidores(cantidadSeguidoresLocal);
			cantidadSeguidoresVisitante = (int)Math.round(cantidadSeguidoresVisitante*0.8);
			if(cantidadSeguidoresVisitante < (equipoVisitante.getEstadio().getCapacidad())*0.1){
				cantidadSeguidoresVisitante = equipoVisitante.getEstadio().getCapacidad();
			}
			
			equipoVisitante.setSeguidores(cantidadSeguidoresVisitante);
		}
		if(p.getResultado().getGolesLocal() < p.getResultado().getGolesVisitante()){
			cantidadSeguidoresLocal = (int)Math.round(cantidadSeguidoresLocal*0.8);
			if(cantidadSeguidoresLocal < equipoLocal.getEstadio().getCapacidad()*0.1){
				cantidadSeguidoresLocal = equipoLocal.getEstadio().getCapacidad();
			}
			equipoLocal.setSeguidores(cantidadSeguidoresLocal);
			cantidadSeguidoresVisitante = (int)Math.round(cantidadSeguidoresVisitante*1.1);
			if(cantidadSeguidoresVisitante > equipoVisitante.getEstadio().getCapacidad()){
				cantidadSeguidoresVisitante = equipoVisitante.getEstadio().getCapacidad();
			}
			
			equipoVisitante.setSeguidores(cantidadSeguidoresVisitante);
		}
		else{
			cantidadSeguidoresLocal = (int)Math.round(cantidadSeguidoresLocal*0.9);
			if(cantidadSeguidoresLocal < equipoLocal.getEstadio().getCapacidad()*0.1){
				cantidadSeguidoresLocal = equipoLocal.getEstadio().getCapacidad();
			}
			equipoLocal.setSeguidores(cantidadSeguidoresLocal);
			cantidadSeguidoresVisitante = (int)Math.round(cantidadSeguidoresVisitante*0.9);
			if(cantidadSeguidoresVisitante > equipoVisitante.getEstadio().getCapacidad()*0.1){
				cantidadSeguidoresVisitante = equipoVisitante.getEstadio().getCapacidad();
			}
			
			equipoVisitante.setSeguidores(cantidadSeguidoresVisitante);
		}
		em.merge(equipoLocal);
		em.merge(equipoVisitante);
		
	}
	
	private List<Posicion> ordenarPosiciones(Torneo t) {
		Collections.sort(t.getPosiciones(), new Comparator() {

			public int compare(Object o1, Object o2) {
				int retorno = 0;
				retorno = ((Posicion) o1).getPuntos() > ((Posicion) o1)
						.getPuntos() ? 1 : -1;
				if (((Posicion) o1).getPuntos() == ((Posicion) o1).getPuntos()) {
					retorno = 0;
				}
				return retorno;
			}
		});
		;
		return t.getPosiciones();
	}
	
	public void actualizarCantidadSocios(Equipo e) throws NoExisteEquipoExcepcion{
		Torneo t = obtenerTorneoActual(e.getCodigo());
		
		Posicion p = (Posicion)em.createQuery("Select p from Posicion p where p.equipo.codigo = :equipo AND p.torneo.codigo = :torneo")
				.setParameter("equipo", e.getCodigo())
				.setParameter("torneo", t.getCodigo())
				.getSingleResult();
		int pos = ordenarPosiciones(t).indexOf(p);
		int cantidadCuadros = conf.getConfiguracion().getCantEquipoTorneo();
		
		
		
		if(pos/(cantidadCuadros/3) == 0){
			int socios = (int)Math.round(e.getSocios()*1.1);
			e.setSocios(socios);
		}
		else if (pos/(cantidadCuadros/3) == 1){
			int socios = (int)Math.round(e.getSocios()*0.91);
			e.setSocios(socios);
		}
		
		em.merge(e);
	}
	
	
	public void pagoDeJuveniles(Equipo e){
		e.setCapital(e.getCapital() - e.getGastoJuveniles());
		em.merge(e);
		ingresarTransaccion(e,-e.getGastoJuveniles(), EnumTipoTransaccion.PAGO_JUVENILES);
	}
	
	
	public void pagarPremio(Torneo torneo) throws NoExisteEquipoExcepcion{
		int premio = conf.getConfiguracion().getPremio();
		
		List<Posicion> posiciones = ordenarPosiciones(torneo);
		Equipo equipo = posiciones.get(0).getEquipo();
		equipo.setCapital(premio);
		em.merge(equipo);
		ingresarTransaccion(equipo, premio, EnumTipoTransaccion.PREMIO);
		
	}
	
	public void actualizarFinTorneos() throws NoExisteEquipoExcepcion, NamingException{
		List<Torneo> torneosActuales = em.createQuery("SELECT t FROM Torneo t WHERE t.actual = :true")
				.setParameter("true", true)
				.getResultList();
		
		for (Torneo torneo : torneosActuales) {
			//pagar premio
			pagarPremio(torneo);
			
			List<Equipo> equipos = torneo.getEquipos();
			for (Equipo equipo : equipos) {
				
				//actualizar cantidad de socios
				actualizarCantidadSocios(equipo);
				LinkedList<Jugador> jugadores = new LinkedList<Jugador>(equipo.getPlantel());
				//generar publicidad
				generarPublicidad(equipo);
				for (Jugador jugador : jugadores) {
					//Actualizar sueldo de jugador
					jugador.calcularSueldoJugador();
					em.merge(jugador);
				
				}
			}
		}
	}
	
	public void actualizarPorMes(Partido p){
			pagarSueldosAEquipo(p.getLocal());
			pagarSueldosAEquipo(p.getVisitante());
			cobrarPublicidad(p.getLocal());
			cobrarPublicidad(p.getVisitante());
			pagoDeJuveniles(p.getLocal());
			pagoDeJuveniles(p.getVisitante());
	}
	
	public void actualizarDespuesPartido(Partido p){
		actualizarCantidadSeguidoresCuadro(p);
		cobrarEntradasPartido(p);
	}
	
	
	
}
