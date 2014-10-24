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
import configuracionGral.DatosPeriodicoPartido;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import datatypes.DatosTorneo;
import equipos.Equipo;
import fabricas.HomeFactory;
import interfaces.ICampeonatoControlador;

@Stateless
@Named
public class CampeonatoControlador implements ICampeonatoControlador{

	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@Inject
	private HomeFactory hf;
	
	

	public void crearCampeonato() {
		int nivelVertical = hf.getConfiguracionControlador().getConfiguracion().getCantidadTorneos(); //se deberia tomar este valor desde propertis o una tabla
		int cantidadEquiposPorTorneo = hf.getConfiguracionControlador().getConfiguracion().getCantEquipoTorneo(); //Idem
		
		for(int vertical = 0; vertical<nivelVertical; vertical++){
			int cantDesc = hf.getConfiguracionControlador().getConfiguracion().getCantidadDescensos();
			int nivelHorizontal = (int)Math.pow(cantDesc,vertical);
			for(int horizontal = 0; horizontal<nivelHorizontal; horizontal++)
			{
				Torneo t = this.crearTorneoDeCampeonato(cantidadEquiposPorTorneo);
				t.setCantEquipos(cantidadEquiposPorTorneo);
				t.setNivelVertical(vertical);
				t.setNivelHorizontal(horizontal);
				em.persist(t);
			}
		}
	}

	
		
	private Torneo crearTorneoDeCampeonato(int cantCuadros){
		//TODO crear un torneo y setear los cuadros. Este metodo tiene que llamar a otro para crear jugadores.
		Torneo t = new Torneo();
		t.setCantCuadrosDesc(hf.getConfiguracionControlador().getConfiguracion().getCantidadDescensos());
		List<Equipo> equipos = new LinkedList<Equipo>();
		
		for(int i = 0; i < cantCuadros; i++){
			int idEquipo = hf.getEquipoControlador().crearEquipo("Equipo" + i);
			equipos.add(hf.getEquipoControlador().findEquipo(idEquipo));
		}
		//em.persist(t);
		return t;
	}
	


	public void crearPartidosTorneo(Torneo t){
		//Crea los partidos
		int cantidadEquipos = hf.getConfiguracionControlador().getConfiguracion().getCantEquipoTorneo();
		Equipo[] equipos =(Equipo[])t.getEquipos().toArray();
		
		
		for(int local = 0; local < cantidadEquipos; local++){
			Equipo e = equipos[local];
			
			for(int visitante = local+1; visitante < cantidadEquipos; visitante++){
				DatosPeriodicoPartido fechaPartido = hf.getConfiguracionControlador().getConfiguracion().getPeriodicoPartido();
				Calendar c = hf.getConfiguracionControlador().getConfiguracion().getFechaArranqueCampeonato();
				Date fecha = fechaPartido.diaPartido(c.getTime(), visitante);
				c.setTime(fecha);
				PartidoTorneo p = new PartidoTorneo(e, equipos[visitante+1], c, visitante);
				em.persist(p);
				
				fecha = fechaPartido.diaPartido(c.getTime(), visitante+cantidadEquipos-1);
				c.setTime(fecha);
				PartidoTorneo segVuelta = new PartidoTorneo(equipos[visitante+1],e, c, visitante+cantidadEquipos-1);
				em.persist(segVuelta);
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
