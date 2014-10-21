package campeonato;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import configuracionGral.ConfiguracionControlador;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;
import datatypes.DataTorneo;
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
	



	@Override
	public DataTorneo obtenerTorneoAsciende(int codigoTorneo) {
		// TODO Auto-generated method stub
		return null;
	}



	@Override
	public DataTorneo obtenerTorneoDesciende(int codigoTorneo) {
		// TODO Auto-generated method stub
		return null;
	}

	
	

}
