package partido;

import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import campeonato.Posicion;
import campeonato.Torneo;
import comunicacion.Comunicacion;
import configuracionGral.ConfiguracionGral;
import configuracionGral.PeriodicoPartido;
import equipos.Equipo;

@Stateless
@Named
public class CampeonatoControlador {

	@PersistenceContext(unitName = "jatrik")
	private EntityManager em;

	private List<Posicion> ordenarPosiciones(Torneo t) {
		extracted(t);
		;
		return t.getPosiciones();
	}

	private void extracted(Torneo t) {
		Collections.sort(t.getPosiciones(), new Comparator<Posicion>() {
			@Override
			public int compare(Posicion o1, Posicion o2) {
				int retorno = -1;
				if (o1.getPuntos() > o2.getPuntos()) {
					retorno = 1;
				} else if (o1.getPuntos() == o2.getPuntos()) {
					if ((o1.getGolesAFavor() - o1.getGolesEnContra()) > (o2
							.getGolesAFavor() - o2.getGolesEnContra())) {
						retorno = 1;
					} else if ((o1.getGolesAFavor() - o1.getGolesEnContra()) == (o2
							.getGolesAFavor() - o2.getGolesEnContra())) {
						if (o1.getGolesAFavor() > o2.getGolesAFavor()) {
							retorno = 1;
						}
					}
				}
				return retorno;
			}
		});
	}

	private List<Torneo> obtenerTorneosDescenso(Torneo t) {
		Query q = em
				.createQuery("SELECT t FROM Torneo t where t.asciende.codigo = :torneo");
		q.setParameter("torneo", t.getCodigo());
		return q.getResultList();
	}

	public List<Torneo> obtenerTorneosActuales() {
		return em
				.createQuery(
						"Select t from Torneo t where t.actual = true order by t.nivelVertical ASC")
				.getResultList();

	}

	public void torneosFinalizados() {
		if (em.createQuery(
				"SELECT 1 FROM PartidoTorneo p where p.estado <> 'FINALIZADO'")
				.getResultList().size() == 0) {
			List<Torneo> torneos = this.obtenerTorneosActuales();
			// try {
			// fc.actualizarFinTorneos();
			// } catch (NoExisteEquipoExcepcion e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// } catch (NamingException e) {
			// // TODO Auto-generated catch block
			// e.printStackTrace();
			// }
			for (Torneo torneo : torneos) {
				this.actualizarDatosTorneo(torneo);
			}
		}
		// ;

	}

	public void actualizarDatosTorneo(Torneo t) {
		ordenarPosiciones(t);
		try {

			List<Torneo> torneosDescenso = obtenerTorneosDescenso(t);
			t.setActual(false);
			Torneo nuevoTorneo = t;
			if (t.getAsciende() == null) {
				PeriodicoPartido fechaPartido = obtenerConfiguracionGral()
						.getPeriodicoPartido();
				Date c = t.getFechaDeArranque().getTime();
				Date fechaP = fechaPartido.diaPartido(c, t.getCantEquipos());
				Calendar ca = Calendar.getInstance();
				ca.setTime(fechaP);
				nuevoTorneo = new Torneo(t.getNivelVertical(),
						t.getNivelHorizontal(), t.getPremio(),
						t.getCantEquipos(), t.getCantCuadrosDesc(), ca);
				em.persist(nuevoTorneo);
				List<Equipo> equipos = new LinkedList<Equipo>(t.getEquipos());
				List<Posicion> posciones = new LinkedList<Posicion>();
				for (Posicion pos : t.getPosiciones()) {
					Posicion p = new Posicion(pos.getEquipo());
					p.setPuntos(pos.getPuntos());
					p.setGolesAFavor(pos.getGolesAFavor());
					p.setGolesEnContra(pos.getGolesEnContra());
					p.setTorneo(nuevoTorneo);
					em.persist(p);
					posciones.add(p);

				}
				nuevoTorneo.setEquipos(equipos);
				for (Equipo eq : equipos) {
					eq.getTorneos().add(nuevoTorneo);
				}
				nuevoTorneo.setPosiciones(posciones);
				nuevoTorneo.setActual(true);
				ordenarPosiciones(nuevoTorneo);
			}
			em.flush();
			Torneo nuevoTorneoD = null;
			int d = 0;
			if (torneosDescenso.size() > 0) {
				for (int i = nuevoTorneo.getCantEquipos()
						- nuevoTorneo.getCantCuadrosDesc(); i < nuevoTorneo
						.getCantEquipos(); i++) {
					Torneo tDescenso = torneosDescenso.get(d++);
					tDescenso.setActual(false);
					Posicion p = nuevoTorneo.getPosiciones().get(i);

					PeriodicoPartido fechaPartido = obtenerConfiguracionGral()
							.getPeriodicoPartido();
					Date c = tDescenso.getFechaDeArranque().getTime();
					Date fechaP = fechaPartido.diaPartido(c,
							tDescenso.getCantEquipos());
					Calendar ca = Calendar.getInstance();
					ca.setTime(fechaP);
					nuevoTorneoD = new Torneo(tDescenso.getNivelVertical(),
							tDescenso.getNivelHorizontal(),
							tDescenso.getPremio(), tDescenso.getCantEquipos(),
							tDescenso.getCantCuadrosDesc(), ca);
					em.persist(nuevoTorneoD);
					List<Equipo> equiposD = new LinkedList<Equipo>(
							tDescenso.getEquipos());
					for (Equipo eq : equiposD) {
						eq.getTorneos().add(nuevoTorneoD);
					}
					nuevoTorneoD.setEquipos(equiposD);
					List<Posicion> poscionesD = new LinkedList<Posicion>();
					for (Posicion pos : tDescenso.getPosiciones()) {
						Posicion posi = new Posicion(pos.getEquipo());
						posi.setPuntos(pos.getPuntos());
						posi.setGolesAFavor(pos.getGolesAFavor());
						posi.setGolesEnContra(pos.getGolesEnContra());
						posi.setTorneo(nuevoTorneoD);
						em.persist(posi);
						poscionesD.add(posi);
					}
					nuevoTorneoD.setPosiciones(poscionesD);
					ordenarPosiciones(nuevoTorneoD);
					equipoDesciende(p, nuevoTorneoD, nuevoTorneo);
					p.resetearPosicion();

					nuevoTorneoD.setAsciende(nuevoTorneo);
					em.merge(nuevoTorneo);

					Comunicacion.getInstance().getCampeonatoControlador()
							.crearPartidosTorneo(nuevoTorneoD);
					em.flush();
					resetearDatos(nuevoTorneoD);
				}
			}
			em.flush();
			// equipoAsciende(t.getPosiciones().get(0).getEquipo(),t);

			if (t.getAsciende() == null) {

				em.flush();
				Comunicacion.getInstance().getCampeonatoControlador()
						.crearPartidosTorneo(nuevoTorneo);
				resetearDatos(nuevoTorneo);
			}
			em.flush();

		} catch (NamingException e) {
			e.printStackTrace();
		}

	}

	private void equipoDesciende(Posicion desciende, Torneo torneoB,
			Torneo torneoA) {
		this.ordenarPosiciones(torneoB);
		Posicion campeon = torneoB.getPosiciones().get(0);
		Equipo eCampeon = campeon.getEquipo();
		eCampeon.getTorneos().add(torneoA);
		eCampeon.getTorneos().remove(torneoB);
		torneoA.getEquipos().add(eCampeon);
		Equipo eDesciende = desciende.getEquipo();
		eDesciende.getTorneos().add(torneoB);
		eDesciende.getTorneos().add(torneoA);
		torneoB.getEquipos().add(eDesciende);
		campeon.setEquipo(eDesciende);

		torneoB.getEquipos().remove(torneoB.getEquipos().indexOf(eCampeon));
		torneoB.getEquipos().add(eDesciende);

		desciende.setEquipo(eCampeon);
		torneoA.getEquipos().remove(torneoA.getEquipos().indexOf(eDesciende));
		torneoA.getEquipos().add(eCampeon);

	}

	public ConfiguracionGral obtenerConfiguracionGral() {
		return (ConfiguracionGral) em.createQuery(
				"Select cg from ConfiguracionGral cg").getSingleResult();
	}

	private void resetearDatos(Torneo t) {

	}

}
