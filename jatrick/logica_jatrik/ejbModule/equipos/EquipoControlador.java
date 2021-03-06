package equipos;

import excepciones.CapitalNegativo;
import excepciones.NoExisteEquipoExcepcion;
import excepciones.NoExisteMensajeExepcion;
import excepciones.NoSePuedeAgrandarEstadio;
import fabricas.HomeFactory;
import finanzas.Finanzas;
import interfaces.IEquipoControlador;
import interfaces.IJugadorControlador;

import java.util.Calendar;
import java.util.Date;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import mensajes.Mensaje;
import jugadores.Jugador;
import partidos.Partido;
import partidos.PartidoTorneo;
import users.Manager;
import campeonato.Copa;
import campeonato.Torneo;
import configuracionGral.ConfiguracionGral;
import datatypes.DatosEquipo;
import datatypes.EnumEntrenamiento;
import datatypes.EnumTipoTransaccion;

@Stateless
@Named
public class EquipoControlador implements IEquipoControlador {

	@PersistenceContext(unitName = "jatrik")
	private EntityManager em;

	@Inject
	private HomeFactory hf;
	
//	private ConfiguracionGral config = hf.getConfiguracionControlador().getConfiguracion();

	@Override
	public Estadio estadioDeEquipo(int codigoEquipo) throws NoExisteEquipoExcepcion {
		Equipo e = hf.getEquipoControlador().findEquipo(codigoEquipo);
		if(e==null){
			throw new NoExisteEquipoExcepcion("No existe equipo de id: "+ codigoEquipo );
		}
		return e.getEstadio();
	}

	@Override
	public void mejorarEstadioEquipo(int codigoEquipo) throws NoExisteEquipoExcepcion, CapitalNegativo, NoSePuedeAgrandarEstadio {
		Equipo e = hf.getEquipoControlador().findEquipo(codigoEquipo);
		ConfiguracionGral config = hf.getConfiguracionControlador().getConfiguracion();
		if(e==null){
			throw new NoExisteEquipoExcepcion("No existe equipo de id: "+ codigoEquipo );
		}
		
		if (e.getCapital() < config.getCuestaAgrandarEstadio()){
			throw new CapitalNegativo("No tienes suficiente capital");
		}
		
		Estadio estadio = e.getEstadio();
		
		if(estadio.getCapacidad() >= hf.getConfiguracionControlador().getConfiguracion().getCapacidadMaximaEstadio()){
			throw new NoSePuedeAgrandarEstadio("Ya se ha llegado a la capacidad máxima del estadio");
		}
		
		e.setCapital(e.getCapital() - config.getCuestaAgrandarEstadio());
		Finanzas credito = new Finanzas(-config.getCuestaAgrandarEstadio(), Calendar.getInstance().getTime(), e, EnumTipoTransaccion.INVERSION_ESTADIO);
		int capacidad = estadio.getCapacidad() + config.getCapacidadAgrandarEstadio();
		if (capacidad > config.getCapacidadAgrandarEstadio()){
			capacidad = config.getCapacidadAgrandarEstadio();
		}
		estadio.setCapacidad(capacidad);
		
		em.merge(e);
		em.persist(credito);
		em.merge(estadio);
		
		
	}

	@Override
	public int crearEquipo(String nombreEquipo, boolean bot, int cantidad) {
		// Alineacion alineacionDefecto = new Alineacion();
		//
		// em.persist(alineacionDefecto);

		Equipo e = new Equipo(nombreEquipo, null, null);
		e.setBot(bot);
		// em.persist(e);

		Estadio estadio = new Estadio();
		estadio.setCapacidad(5000);
		estadio.setNombre(nombreEquipo + " Arena");

		e.setEstadio(estadio);

		IJugadorControlador ijc = hf.getJugadorControlador();
		Set<Jugador> plantel = ijc.generarJugadores(cantidad, e);
		e.setPlantel(plantel);
		em.persist(e);

		return e.getCodigo();
	}

	@Override
	public Equipo findEquipo(int codigoEquipo) {
		return em.find(Equipo.class, codigoEquipo);
	}

	@Override
	public DatosEquipo obtenerEquipo(int codEquipo) {
		Equipo equipo = this.findEquipo(codEquipo);
		return equipo.getDatos();
	}

	@Override
	public Set<Equipo> listarEquiposSistema() {
		Set<Equipo> result = new HashSet<Equipo>();
		String consulta = "SELECT e FROM Equipo e";
		Query query = em.createQuery(consulta);
		for (Object o : query.getResultList()) {
			result.add((Equipo) o);
		}
		return result;
	}

	@Override
	public Set<Equipo> listarEquiposPais(int codigoPais) {
		Set<Equipo> result = new HashSet<Equipo>();
		for (Equipo e : this.listarEquiposSistema()) {
			if (e.getCodPais() == codigoPais) {
				result.add(e);
			}
		}
		return result;
	}

	@Override
	public Set<Equipo> listarEquiposTorneo(int codTorneo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<DatosEquipo> obtenerEquiposSistema() {
		Set<DatosEquipo> result = new HashSet<DatosEquipo>();
		for (Equipo e : this.listarEquiposSistema()) {
			result.add(e.getDatos());
		}
		return result;
	}

	@Override
	public Set<DatosEquipo> obtenerEquiposPais(int codigoPais) {
		Set<DatosEquipo> result = new HashSet<DatosEquipo>();
		for (Equipo e : this.listarEquiposPais(codigoPais)) {
			result.add(e.getDatos());
		}
		return result;
	}

	@Override
	public Set<DatosEquipo> obtenerEquiposTorneo(int codTorneo) {
		Set<DatosEquipo> result = new HashSet<DatosEquipo>();
		for (Equipo e : this.listarEquiposTorneo(codTorneo)) {
			result.add(e.getDatos());
		}
		return result;
	}

	@Override
	public Set<Jugador> obtenerJugadoresEquipo(int codEquipo) {
		// Set<DatosJugador> result = new HashSet<DatosJugador>();
		Equipo equipo = this.findEquipo(codEquipo);
		// for (Jugador j : equipo.getPlantel()) {
		// result.add(j.getDatos());
		// }
		// return result;

		return equipo.getPlantel();
	}

	@Override
	public int crearAlineacion(List<Jugador> delanteros,
			List<Jugador> mediocampistas, List<Jugador> defensas,
			Jugador golero, Jugador lesionDelantero,
			Jugador lesionMediocampistas, Jugador lesionDefensas,
			Jugador lesionGolero, List<Jugador> suplentes, boolean defecto) {

		Alineacion alineacion = new Alineacion(delanteros, mediocampistas,
				defensas, golero, lesionDelantero, lesionMediocampistas,
				lesionDefensas, lesionGolero, suplentes, defecto);
		em.persist(alineacion);
		return alineacion.getCodigo();

	}

	@Override
	public Alineacion findAlineacion(int codigoAlineacion) {
		return em.find(Alineacion.class, codigoAlineacion);
	}

	@Override
	public void modificarTipoEntrenamientoEquipo(int codigoEquipo,
			EnumEntrenamiento tipoEntrenamiento) throws NoExisteEquipoExcepcion {
		Equipo e = findEquipo(codigoEquipo);
		if (e == null) {
			throw new NoExisteEquipoExcepcion("No existe el equipo de id: "
					+ codigoEquipo);
		}

		e.setTipoEntrenamiento(tipoEntrenamiento);
		em.merge(e);
	}

	public Equipo asignarTorneo(Manager manager, DatosEquipo eq) {
		// TODO asignar el torneo correctamente, o sea, el torneo de mas abajo
		List<Torneo> torneos = hf.getCampeontaoControlador().obtenerTorneos();
		ConfiguracionGral config = hf.getConfiguracionControlador()
				.getConfiguracion();
		for (Torneo t : torneos) {
			for (Equipo e : t.getEquipos()) {
				if (e.isBot()) {
					manager.setEquipo(e);
					manager.setTorneo(t);
					e.setBot(false);
					e.setNombre(eq.getNombre());
					Estadio estadio = e.getEstadio();
					estadio.setNombre(eq.getNombre() + " Arena");
					estadio.setCapacidad(config.getCapacidadMinimaEstadio());
					e.setCapital(config.getDineroInicial());
					e.setPublicidad(config.getPublicidadMinima());
					e.setSeguidores(config.getSeguidoresEmpieza());
					e.setSocios(config.getSociosEmpieza());
					e.setGastoJuveniles(0);
					e.setAltura(eq.getAltura());
					em.merge(e);
					em.merge(manager);
					em.persist(estadio);
					return e;
				}
			}
		}
		return null;
	}

	@Override
	public void modificarEquipo(DatosEquipo equipo) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Partido> obtenerProximosPartidos(int codEquipo, int cantidad) {
		Query q = em
				.createQuery("select p from Partido p where (p.local.codigo = :codEquipo or p.visitante.codigo = :codEquipo) and (p.estado = 'POR_JUGAR' or p.estado = 'POR_SIMULAR' or p.estado = 'JUGANDO') order by p.fechaHora");
		q.setParameter("codEquipo", codEquipo);
		List<Partido> partidosAux = q.setMaxResults(cantidad).getResultList();
		List<Partido> partidos = new LinkedList<Partido>(partidosAux);
		for (Partido partido : partidosAux) {
			PartidoTorneo pt = em
					.find(PartidoTorneo.class, partido.getCodigo());
			if (pt != null) {
				if (!pt.getTorneo().isActual()) {
					partidos.remove(partido);
				}
			}
		}
		return partidos;
	}

	@Override
	public List<Partido> obtenerAnterioresPartidos(int codEquipo, int cantidad) {
		Query q = em
				.createQuery("select p from Partido p where (p.local.codigo = :codEquipo or p.visitante.codigo = :codEquipo) and (p.estado = 'FINALIZADO') order by p.fechaHora");
		q.setParameter("codEquipo", codEquipo);
		return q.setMaxResults(cantidad).getResultList();
	}

	@Override
	public void elegirEntrenamiento(int idCodigoEquipo,
			EnumEntrenamiento enumEntrenamiento) {
		// TODO Auto-generated method stub
		Equipo e = findEquipo(idCodigoEquipo);
		e.setTipoEntrenamiento(enumEntrenamiento);
		em.persist(e);

	}

	@Override
	public EnumEntrenamiento entrenamientoEquipo(int idCodigoEquipo) {
		// TODO Auto-generated method stub
		Equipo e = findEquipo(idCodigoEquipo);
		return e.getTipoEntrenamiento();
	}

	@Override
	public Equipo getEquipo(int codigoEquipo) {
		return em.find(Equipo.class, codigoEquipo);
	}

	@Override
	public Torneo obtenerTorneoActual(int codEquipo)
			throws NoExisteEquipoExcepcion {
		Equipo e = findEquipo(codEquipo);
		if (e == null) {
			throw new NoExisteEquipoExcepcion("No existe el equipo de id: "
					+ codEquipo);
		}

		List<Torneo> torneos = e.getTorneos();
		if (torneos == null) {
			return null;
		}
		Torneo torneoMasActual = torneos.get(0);
		for (Torneo torneo : torneos) {

			if (torneoMasActual.getFechaDeArranque().compareTo(
					torneo.getFechaDeArranque()) > 0) {
				torneoMasActual = torneo;
			}
		}

		return torneoMasActual;

	}

	@Override
	public List<Copa> obtenerCopasEquipo(int codEquipo) {
		em.find(Equipo.class, codEquipo);
		Query q = em
				.createQuery("select c from Copa c where :codEquipo in (select e.codigo from c.equipos e)");
		q.setParameter("codEquipo", codEquipo);
		return q.getResultList();
	}

	@Override
	public List<Equipo> listarEquiposNoBots() {

		String consulta = "SELECT e FROM Equipo e Where e.bot = :false";
		Query query = em.createQuery(consulta).setParameter("false", false);

		return query.getResultList();
	}

	@Override
	public int crearMensaje(String titulo, String cuerpo, Date fechaCreacion,
			int remitente, int receptor) {
		// TODO Auto-generated method stub
		Equipo erem = em.find(Equipo.class, remitente);
		Equipo erec = em.find(Equipo.class, receptor);
		Mensaje mensaje = new Mensaje(titulo, cuerpo, erem, erec,
				fechaCreacion, false);
		em.persist(mensaje);
		return mensaje.getCodigo();
	}

	@Override
	public List<Mensaje> listarMensajes(int codEquipo) {
		return em
				.createQuery(
						"SELECT m FROM Mensaje m WHERE m.receptor.codigo = :equipo")
				.setParameter("equipo", codEquipo).getResultList();
	}

	@Override
	public void marcarComoLeido(int codigoMensaje)
			throws NoExisteMensajeExepcion {
		Mensaje m = findMensaje(codigoMensaje);
		if (m == null) {
			throw new NoExisteMensajeExepcion("No existe mensaje de id: "
					+ codigoMensaje);
		}

		m.setLeido(true);
		em.merge(m);

	}

	@Override
	public Mensaje findMensaje(int codigoMensaje) {
		return (Mensaje) em.find(Mensaje.class, codigoMensaje);
	}

	@Override
	public void borrarMensaje(List<Mensaje> mensajes) {
		for (Mensaje mensaje : mensajes) {
			em.remove(em.find(Mensaje.class, mensaje.getCodigo()));
		}

	}

	@Override
	public List<Equipo> obtenerRanking(int cantidad) {
		return (List<Equipo>) em
				.createQuery("select e from Equipo e order by e.ranking DESC")
				.setMaxResults(cantidad).getResultList();
	}

	@Override
	public Jugador obtenerJuvenil(int codEquipo) {
		Jugador j = null;
		Equipo e = findEquipo(codEquipo);
		if (e.isJuvenilFecha()) {
			IJugadorControlador ijc = hf.getJugadorControlador();
			Set<Jugador> jugadores = ijc.generarJugadores(1, e);
			j = jugadores.iterator().next();
			e.getPlantel().add(j);
			e.setJuvenilFecha(false);
		}
		return j;
	}
}
