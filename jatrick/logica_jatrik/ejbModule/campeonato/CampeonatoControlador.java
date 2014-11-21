package campeonato;

import datatypes.DatosCopa;
import datatypes.DatosFixture;
import datatypes.DatosPartido;
import datatypes.DatosPartidoCopa;
import datatypes.DatosTorneo;
import equipos.Equipo;
import excepciones.NoExisteConfiguracionException;
import fabricas.HomeFactory;
import interfaces.ICampeonatoControlador;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import jugadores.Jugador;
import partidos.PartidoCopa;
import partidos.PartidoTorneo;
import partidos.ResultadoPartido;
import configuracionGral.ConfiguracionGral;
import configuracionGral.PeriodicoPartido;

@Stateless
@Named
public class CampeonatoControlador implements ICampeonatoControlador {

	@PersistenceContext(unitName = "jatrik")
	private EntityManager em;

	@Inject
	private HomeFactory hf;

	@Override
	public void crearCampeonato() throws NoExisteConfiguracionException {
		ConfiguracionGral cg = hf.getConfiguracionControlador()
				.getConfiguracion();
		if (cg == null) {
			throw new NoExisteConfiguracionException(
					"No existe configuracion general");
		}
		int nivelVertical = cg.getCantidadTorneos(); // se deberia tomar este
														// valor desde propertis
														// o una tabla
		int cantidadEquiposPorTorneo = cg.getCantEquipoTorneo(); // Idem

		for (int vertical = 0; vertical < nivelVertical; vertical++) {
			int cantDesc = cg.getCantidadDescensos();
			int nivelHorizontal = (int) Math.pow(cantDesc, vertical);
			for (int horizontal = 0; horizontal < nivelHorizontal; horizontal++) {
				Torneo t = this
						.crearTorneoDeCampeonato(cantidadEquiposPorTorneo);
				t.setCantEquipos(cantidadEquiposPorTorneo);
				t.setNivelVertical(vertical + 1);
				t.setNivelHorizontal(horizontal + 1);
				Calendar cal = Calendar.getInstance();
				cal.setTime(cg.getFechaArranqueCampeonato());
				t.setFechaDeArranque(cal);
				// em.persist(t);
				crearPartidosTorneo(t);
				if (vertical != 0) {
					asignarTorneoAsciende(t);
				}
			}
		}
	}

	private void asignarTorneoAsciende(Torneo t) {
		Query q = em
				.createQuery("SELECT t FROM Torneo t where t.nivelVertical = :nivel and :cantidad > (select count(*) from Torneo t1 where t1.asciende.codigo = t.codigo)");
		q.setParameter("nivel", t.getNivelVertical() - 1);
		q.setParameter("cantidad",
				(long) (Math.pow(2, t.getNivelVertical()) / 2));
		t.setAsciende((Torneo) q.getResultList().get(0));
	}

	private Torneo crearTorneoDeCampeonato(int cantCuadros) {
		// TODO crear un torneo y setear los cuadros. Este metodo tiene que
		// llamar a otro para crear jugadores.
		Torneo t = new Torneo();
		t.setCantCuadrosDesc(hf.getConfiguracionControlador()
				.getConfiguracion().getCantidadDescensos());
		List<Equipo> equipos = new LinkedList<Equipo>();
		em.persist(t);
		Equipo e = null;
		for (int i = 0; i < cantCuadros; i++) {
			int idEquipo = hf.getEquipoControlador().crearEquipo(
					"Equipo" + i,
					true,
					hf.getConfiguracionControlador().getConfiguracion()
							.getCantJugadoresArranque());
			e = hf.getEquipoControlador().findEquipo(idEquipo);
			e.getTorneos().add(t);
			Posicion p = new Posicion(e);
			p.setTorneo(t);
			t.getPosiciones().add(p);
			em.persist(p);
			equipos.add(e);
		}
		t.setEquipos(equipos);

		return t;
	}

	@Override
	public void crearPartidosTorneo(Torneo t) {
		ConfiguracionGral conf = hf.getConfiguracionControlador()
				.getConfiguracion();
		// Crea los partidos
		int cantidadEquipos = conf.getCantEquipoTorneo();

		/** http://es.wikipedia.org/wiki/Sistema_de_todos_contra_todos **/
		Integer[][][] arrayFixture = new Integer[cantidadEquipos - 1][cantidadEquipos / 2][2];

		int nroEquipo = 0;
		for (int fila = 0; fila < cantidadEquipos - 1; fila++) {
			for (int columna = 0; columna < cantidadEquipos / 2; columna++) {
				arrayFixture[fila][columna][0] = (nroEquipo++ % (cantidadEquipos - 1)) + 1;
			}
		}

		Equipo[] conversion = new Equipo[cantidadEquipos + 1];
		Equipo local = null;
		Equipo visitante = null;
		for (int i = 0; i < cantidadEquipos; i++) {
			conversion[i + 1] = t.getEquipos().get(i);
		}

		int localidad = 0;
		for (int fila = 0; fila < cantidadEquipos - 1; fila++) {
			if (localidad == 0) {
				arrayFixture[fila][0][1] = arrayFixture[fila][0][0];
			}
			arrayFixture[fila][0][localidad] = cantidadEquipos;
			localidad = (localidad + 1) % 2;

			local = conversion[arrayFixture[fila][0][0]];
			visitante = conversion[arrayFixture[fila][0][1]];

			PeriodicoPartido fechaPartido = conf.getPeriodicoPartido();
			Date c = t.getFechaDeArranque().getTime();// conf.getFechaArranqueCampeonato();
			Date fechaP = fechaPartido.diaPartido(c, fila + 1);

			Calendar ca = Calendar.getInstance();
			ca.setTime(fechaP);
			PartidoTorneo p = new PartidoTorneo(local, visitante, ca, fila + 1,
					t);
			p.setTipoPartido("Liga");
			ResultadoPartido rp = new ResultadoPartido();
			em.persist(rp);
			p.setResultado(rp);
			em.persist(p);
			
			fechaP = fechaPartido.diaPartido(c, fila + cantidadEquipos);
			ca = Calendar.getInstance();
			ca.setTime(fechaP);
			
			PartidoTorneo segVuelta = new PartidoTorneo(visitante,local , ca,
					fila + cantidadEquipos, t);
			segVuelta.setTipoPartido("Liga");
			ResultadoPartido rpSG = new ResultadoPartido();
			em.persist(rpSG);
			segVuelta.setResultado(rpSG);
			em.persist(segVuelta);
		}
		em.flush();
		nroEquipo = cantidadEquipos - 1;
		for (int fila = 0; fila < cantidadEquipos - 1; fila++) {
			for (int columna = 1; columna < cantidadEquipos / 2; columna++) {
				arrayFixture[fila][columna][1] = nroEquipo % (cantidadEquipos);
				nroEquipo = nroEquipo == 1 ? cantidadEquipos - 1
						: nroEquipo - 1;

				local = conversion[arrayFixture[fila][columna][0]];
				visitante = conversion[arrayFixture[fila][columna][1]];

				PeriodicoPartido fechaPartido = conf.getPeriodicoPartido();
				Date c = conf.getFechaArranqueCampeonato();
				Date fechaP = fechaPartido.diaPartido(c, fila + 1);
				Calendar ca = Calendar.getInstance();
				ca.setTime(fechaP);
				PartidoTorneo p = new PartidoTorneo(local, visitante, ca,
						fila + 1, t);
				p.setTipoPartido("Liga");
				ResultadoPartido rp = new ResultadoPartido();
				em.persist(rp);
				p.setResultado(rp);
				em.persist(p);
				
				fechaP = fechaPartido.diaPartido(c, fila + cantidadEquipos);
				ca = Calendar.getInstance();
				ca.setTime(fechaP);
				
				PartidoTorneo segVuelta = new PartidoTorneo(visitante,local , ca,
						fila + cantidadEquipos, t);
				segVuelta.setTipoPartido("Liga");
				ResultadoPartido rpSG = new ResultadoPartido();
				em.persist(rpSG);
				segVuelta.setResultado(rpSG);
				em.persist(segVuelta);
			}
		}
		em.flush();
	}

	@Override
	public DatosTorneo obtenerTorneoAsciende(int codigoTorneo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public DatosTorneo obtenerTorneoDesciende(int codigoTorneo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Torneo> obtenerTorneos() {
		Query q = em.createQuery("select t from Torneo t");
		return (List<Torneo>) q.getResultList();
	}

	@Override
	public List<Posicion> obtenerPosiciones(int idTorneo) {
		Query q = em
				.createQuery("select p FROM Torneo t join t.posiciones p WHERE t.codigo = :idTorneo ORDER BY p.puntos DESC,(p.golesAFavor - p.golesEnContra) DESC");
		q.setParameter("idTorneo", idTorneo);
		return q.getResultList();

	}

	@Override
	public List<Torneo> obtenerTorneosActuales() {
		return em
				.createQuery(
						"Select t from Torneo t where t.actual = true order by t.nivelVertical ASC")
				.getResultList();

	}

	private int nivelTorneoMenor() {
		Query q = em.createQuery("select max(t.nivelVertical) FROM Torneo t");
		return (int) q.getSingleResult();
	}

	@Override
	public void agregarTorneoNivelInferior() {
		ConfiguracionGral cg = hf.getConfiguracionControlador()
				.getConfiguracion();
		int cantidadEquiposPorTorneo = cg.getCantEquipoTorneo(); // Idem
		int nivelVertical = this.nivelTorneoMenor() + 1;
		List<Torneo> torneos = em
				.createQuery(
						"Select t from Torneo t where t.actual = true and (t.nivelVertical = (select max(t2.nivelVertical) from Torneo t2))")
				.getResultList();
		Calendar fechaArranque = torneos.get(0).getFechaDeArranque();
		int cantDesc = cg.getCantidadDescensos();
		int horizontal = 1;
		for (Torneo torneo : torneos) {
			for (int i = 0; i < cantDesc; i++) {
				Torneo t = this
						.crearTorneoDeCampeonato(cantidadEquiposPorTorneo);
				t.setCantEquipos(cantidadEquiposPorTorneo);
				t.setNivelVertical(nivelVertical);
				t.setNivelHorizontal(horizontal++);
				t.setActual(true);
				t.setAsciende(torneo);
				PeriodicoPartido fechaPartido = cg.getPeriodicoPartido();
				Date c = fechaArranque.getTime();
				Date fechaP = fechaPartido.diaPartido(c, t.getCantEquipos());
				Calendar ca = Calendar.getInstance();
				ca.setTime(fechaP);
				t.setFechaDeArranque(ca);
				crearPartidosTorneo(t);
			}
		}

		// int cantDesc = cg.getCantidadDescensos();
		// int nivelHorizontal = (int) Math.pow(cantDesc, nivelVertical);
		// for (int horizontal = 0; horizontal < nivelHorizontal; horizontal++)
		// {
		// Torneo t = this.crearTorneoDeCampeonato(cantidadEquiposPorTorneo);
		// t.setCantEquipos(cantidadEquiposPorTorneo);
		// t.setNivelVertical(nivelVertical);
		// t.setNivelHorizontal(horizontal + 1);
		// t.setActual(true);
		// PeriodicoPartido fechaPartido = cg.getPeriodicoPartido();
		// Date c = t.getFechaDeArranque().getTime();
		// Date fechaP = fechaPartido.diaPartido(c, t.getCantEquipos());
		// Calendar ca = Calendar.getInstance();
		// ca.setTime(fechaP);
		// t.setFechaDeArranque(ca);
		// // em.persist(t);
		// crearPartidosTorneo(t);

		// }
		// TODO nuevo codigo, obtener los torneos de nivel mas bajo, obtener la
		// ultima fecha de algun torno, pimbi
	}

	@Override
	public List<Jugador> obtenerGoleadoresTorneo(int codTorneo) {
		Torneo t = em.find(Torneo.class, codTorneo);
		Query q = em
				.createQuery("select j from Torneo  t join t.equipos e join e.plantel j  where t.codigo = :codTorneo order by j.golesLiga DESC");
		q.setParameter("codTorneo", codTorneo);
		return q.getResultList();
	}

	@Override
	public List<DatosFixture> obtenerFixtureTorneo(int codTorneo) {
		Torneo t = em.find(Torneo.class, codTorneo);
		Query q = em
				.createQuery("select p from PartidoTorneo p where p.torneo.codigo = :codTorneo order by p.fechaNro ASC");
		q.setParameter("codTorneo", codTorneo);
		List<PartidoTorneo> partidos = q.getResultList();
		List<DatosFixture> fixture = new LinkedList<DatosFixture>();
		DatosFixture d = new DatosFixture(1);
		int fecha = 1;
		for (PartidoTorneo partidoTorneo : partidos) {
			if (fecha != partidoTorneo.getFechaNro()) {
				fecha = partidoTorneo.getFechaNro();
				fixture.add(d);
				d = new DatosFixture(partidoTorneo.getFechaNro());
			}
			d.getPartidos().add(
					new DatosPartido(partidoTorneo.getCodigo(), partidoTorneo
							.getLocal().getNombre(), partidoTorneo
							.getVisitante().getNombre(), partidoTorneo
							.getResultado().getGolesLocal(), partidoTorneo
							.getResultado().getGolesVisitante()));
		}
		fixture.add(d);
		return fixture;
	}

	@Override
	public void crearCopa(int cantidadEquipos, Calendar fecha, int ingreso,
			String nombre) {
		int hasta = cantidadEquipos / 2;
		Copa c = new Copa();
		c.setFechaInicio(fecha);
		c.setIngreso(ingreso);
		c.setCantidadEquipos(cantidadEquipos);
		c.setNombre(nombre);
		em.persist(c);
		ConfiguracionGral cg = hf.getConfiguracionControlador()
				.getConfiguracion();
		List<PartidoCopa> proximaRonda = null;
		List<PartidoCopa> proximaRondaC = new LinkedList<PartidoCopa>();
		int z = 0;
		int f = 1;
		for (int i = 1; i < cantidadEquipos; i = i * 2) {
			for (int j = 0; j < hasta; j++) {
				PartidoCopa pc = new PartidoCopa();
				pc.setCopa(c);
				pc.setTipoPartido("Copa");
				pc.setFechaHora(fecha);
				pc.setFase(i);
				c.getPartidos().add(pc);
				proximaRondaC.add(pc);
				if (i != 1) {
					for (int g = z; g < z + 2; g++) {
						proximaRonda.get(g).setSiguienteFase(pc);
					}
					z += 2;
				}
				ResultadoPartido rp = new ResultadoPartido();
				em.persist(rp);
				pc.setResultado(rp);
				em.persist(pc);
			}
			PeriodicoPartido fechaPartido = cg.getPeriodicoPartido();
			Date fd = fecha.getTime();// conf.getFechaArranqueCampeonato();
			Date fechaP = fechaPartido.diaPartido(fd, f++);
			Calendar ca = Calendar.getInstance();
			ca.setTime(fechaP);
			z = 0;
			proximaRonda = proximaRondaC;
			proximaRondaC = new LinkedList<PartidoCopa>();
			hasta = hasta / 2;
		}
		
	}

	@Override
	public void agregarEquipoACopa(int codEquipo, int codCopa) {
		Copa c = em.find(Copa.class, codCopa);
		Equipo e = em.find(Equipo.class, codEquipo);
		c.getEquipos().add(e);
		// c.getPartidos().
		Query q = em
				.createQuery("select pc from Copa c join c.partidos pc where pc.fase = 1 and c.codigo = :codigo and (pc.local = null or pc.visitante = null)");
		q.setParameter("codigo", c.getCodigo());
		List<PartidoCopa> partidos = q.getResultList();
		for (PartidoCopa partidoCopa : partidos) {
			if (partidoCopa.getLocal() == null) {
				partidoCopa.setLocal(e);
				break;
			} else if (partidoCopa.getVisitante() == null) {
				partidoCopa.setVisitante(e);
				break;
			}
		}
	}

	@Override
	public List<Copa> obtenerCopasFuturas() {
		Calendar c = Calendar.getInstance();
		Query q = em
				.createQuery("select c from Copa c where c.fechaInicio > :fechaActual");
		q.setParameter("fechaActual", c);
		return q.getResultList();
	}

	@Override
	public DatosCopa obtenerFixtureCopa(int codCopa) {
		Copa c = em.find(Copa.class, codCopa);
		DatosCopa dc = c.obtenerDatos();
		Collections.sort(c.getPartidos(), new Comparator<PartidoCopa>() {
			@Override
			public int compare(PartidoCopa o1, PartidoCopa o2) {
				int retorno = 0;
				if (o1.getFase() > o2.getFase()) {
					retorno = 1;
				} else if (o2.getFase() > o1.getFase()){
					retorno = 1;
				}
				return retorno;
			}
		});
		List<DatosPartidoCopa> ldpc = new LinkedList<DatosPartidoCopa>();
		DatosPartidoCopa dpc = new DatosPartidoCopa();
		int fase = 1;
		dpc.setFase(fase);
		dpc.setPartidosFase(new LinkedList<DatosPartido>());
		
		for (PartidoCopa pc : c.getPartidos()) {
			if (fase != pc.getFase()){
				ldpc.add(dpc);
				dpc = new DatosPartidoCopa();
				dpc.setPartidosFase(new LinkedList<DatosPartido>());
				dpc.setFase(++fase);
			}
			String nombreLocal = pc.getLocal() != null ? pc.getLocal().getNombre() : "--";
			String nombreVisitante = pc.getVisitante() != null ? pc.getVisitante().getNombre() : "--";
			DatosPartido dp = new DatosPartido(pc.getCodigo(), nombreLocal, nombreVisitante, pc.getResultado().getGolesLocal(), pc.getResultado().getGolesVisitante());
			dpc.getPartidosFase().add(dp);
		}
		ldpc.add(dpc);
		dc.setPartidos(ldpc);
		return dc;
	}
	
//	@Override
//	public List<Equipo> obtenerUltimosCampeones(){
//		
//	}
}
