package campeonato;

import datatypes.DatosTorneo;
import equipos.Equipo;
import excepciones.NoExisteConfiguracionException;
import fabricas.HomeFactory;
import interfaces.ICampeonatoControlador;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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
				em.persist(t);
				crearPartidosTorneo(t);
				if (vertical != 0){
					asignarTorneoAsciende(t);
				}
			}
		}
	}

	private void asignarTorneoAsciende(Torneo t) {
		Query q = em.createQuery("SELECT t FROM Torneo t where t.nivelVertical = :nivel and :cantidad > (select count(*) from Torneo t1 where t1.asciende.codigo = t.codigo)");
		q.setParameter("nivel", t.getNivelVertical()-1);
		q.setParameter("cantidad", (long)(Math.pow(2, t.getNivelVertical())/2));
		t.setAsciende((Torneo) q.getResultList().get(0));
	}

	private Torneo crearTorneoDeCampeonato(int cantCuadros) {
		// TODO crear un torneo y setear los cuadros. Este metodo tiene que
		// llamar a otro para crear jugadores.
		Torneo t = new Torneo();
		t.setCantCuadrosDesc(hf.getConfiguracionControlador()
				.getConfiguracion().getCantidadDescensos());
		List<Equipo> equipos = new LinkedList<Equipo>();

		Equipo e = null;
		for (int i = 0; i < cantCuadros; i++) {
			int idEquipo = hf.getEquipoControlador().crearEquipo(
					"Equipo" + i,
					true,
					hf.getConfiguracionControlador().getConfiguracion()
							.getCantJugadoresArranque());
			e = hf.getEquipoControlador().findEquipo(idEquipo);
			Posicion p = new Posicion(e);
			t.getPosiciones().add(p);
			em.persist(p);
			equipos.add(e);
		}
		t.setEquipos(equipos);
		// em.merge(t);
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
			Date c = t.getFechaDeArranque().getTime();//conf.getFechaArranqueCampeonato();
			Date fechaP = fechaPartido.diaPartido(c, fila + 1);

			Calendar ca = Calendar.getInstance();
			ca.setTime(fechaP);
			PartidoTorneo p = new PartidoTorneo(local, visitante,
					ca, fila + 1, t);
			ResultadoPartido rp = new ResultadoPartido();
			em.persist(rp);
			p.setResultado(rp);
			em.persist(p);
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
				PartidoTorneo p = new PartidoTorneo(local, visitante,
						ca, fila + 1, t);
				ResultadoPartido rp = new ResultadoPartido();
				em.persist(rp);
				p.setResultado(rp);
				em.persist(p);
				// fecha = fechaPartido.diaPartido(c.getTime(),
				// visitante+cantidadEquipos-1);
				// c.setTime(fecha);
				// PartidoTorneo segVuelta = new
				// PartidoTorneo(equipos.get(visitante),e, c,
				// visitante+cantidadEquipos-1);
				// em.persist(segVuelta);
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

		int cantDesc = cg.getCantidadDescensos();
		int nivelHorizontal = (int) Math.pow(cantDesc, nivelVertical);
		for (int horizontal = 0; horizontal < nivelHorizontal; horizontal++) {
			Torneo t = this.crearTorneoDeCampeonato(cantidadEquiposPorTorneo);
			t.setCantEquipos(cantidadEquiposPorTorneo);
			t.setNivelVertical(nivelVertical);
			t.setNivelHorizontal(horizontal + 1);
			t.setActual(true);
//			t.setFechaDeArranque(cal);
			em.persist(t);
			crearPartidosTorneo(t);

		}
	}

}
