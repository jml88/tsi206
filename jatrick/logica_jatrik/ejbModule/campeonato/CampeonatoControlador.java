package campeonato;

import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import partidos.PartidoTorneo;
import configuracionGral.ConfiguracionControlador;
import configuracionGral.ConfiguracionGral;
import configuracionGral.PeriodicoPartido;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import datatypes.DatosTorneo;
import equipos.Equipo;
import excepciones.NoExisteConfiguracionException;
import fabricas.HomeFactory;
import interfaces.ICampeonatoControlador;

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
				t.setNivelVertical(vertical);
				t.setNivelHorizontal(horizontal);
				em.persist(t);
				crearPartidosTorneo(t);

			}
		}
	}

	private Torneo crearTorneoDeCampeonato(int cantCuadros) {
		// TODO crear un torneo y setear los cuadros. Este metodo tiene que
		// llamar a otro para crear jugadores.
		Torneo t = new Torneo();
		t.setCantCuadrosDesc(hf.getConfiguracionControlador()
				.getConfiguracion().getCantidadDescensos());
		List<Equipo> equipos = new LinkedList<Equipo>();

		for (int i = 0; i < cantCuadros; i++) {
			int idEquipo = hf.getEquipoControlador().crearEquipo("Equipo" + i);
			equipos.add(hf.getEquipoControlador().findEquipo(idEquipo));
		}
		t.setEquipos(equipos);
		// em.persist(t);
		return t;
	}

	public void crearPartidosTorneo(Torneo t) {
		// Crea los partidos
		int cantidadEquipos = hf.getConfiguracionControlador()
				.getConfiguracion().getCantEquipoTorneo();

		/** http://es.wikipedia.org/wiki/Sistema_de_todos_contra_todos **/
		Integer[][][] arrayFixture = new Integer[cantidadEquipos-1][cantidadEquipos/2][2];
		
		int nroEquipo = 0;
		for(int fila = 0; fila < cantidadEquipos -1; fila++){
			for(int columna = 0; columna < cantidadEquipos/2; columna++){
				arrayFixture[fila][columna][0] = (nroEquipo++%(cantidadEquipos-1))+1;
			}
		}
		int localidad = 0;
		for(int fila = 0; fila < cantidadEquipos-1; fila++){
			if (localidad == 0){
				arrayFixture[fila][0][1] = arrayFixture[fila][0][0];
			}
			arrayFixture[fila][0][localidad] = cantidadEquipos;
			localidad = (localidad+1) % 2;
		}
		
		Equipo[] conversion = new Equipo[cantidadEquipos];
		Equipo local = null;
		Equipo visitante = null;
		for (int i = 0; i< cantidadEquipos ; i++) {
			conversion[i] = t.getEquipos().get(i);
		}
		nroEquipo = cantidadEquipos-1;
		for(int fila = 0; fila < cantidadEquipos -1; fila++){
			for(int columna = 1; columna < cantidadEquipos/2; columna++){
				arrayFixture[fila][columna][1] = nroEquipo%(cantidadEquipos);
				nroEquipo = nroEquipo==0? cantidadEquipos-1 : nroEquipo-1;
				
				local =conversion[arrayFixture[fila][columna][0]];
				visitante =conversion[arrayFixture[fila][columna][1]];
				
				PeriodicoPartido fechaPartido = hf
						.getConfiguracionControlador().getConfiguracion()
						.getPeriodicoPartido();
				Calendar c = hf.getConfiguracionControlador()
						.getConfiguracion().getFechaArranqueCampeonato();
				Date fechaP = fechaPartido.diaPartido(c.getTime(),fila);
				c.setTime(fechaP);
				PartidoTorneo p = new PartidoTorneo(local, visitante, c,
						fila);
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

}
