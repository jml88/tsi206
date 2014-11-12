package web_jatrik;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import jugadores.Jugador;
import campeonato.Posicion;
import comunicacion.Comunicacion;
import datatypes.DatosFixture;
import datatypes.DatosManager;

@Named("ligaBB")
@ViewScoped
public class LigaBB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Posicion> posiciones;
	
	private List<Jugador> goleadores;
	
	private List<DatosFixture> fixture;

	@Inject
	SessionBB sesion;
	
	@PostConstruct
	public void init() {
		try {
//			int codTorneo = sesion.getDatosManager().getCodTorneo();
			int managerId = Comunicacion.getInstance().getIUserControlador().findUserByUserName(sesion.getDatosManager().getUsername());
			DatosManager datosManager = Comunicacion.getInstance().getIUserControlador().obtenerManager(managerId);
			int codTorneo = datosManager.getCodTorneo();
			FacesContext faces = FacesContext.getCurrentInstance();
			if (faces.getExternalContext().getApplicationMap().get("codTorneo") != null){
				codTorneo = (int)faces.getExternalContext().getApplicationMap().get("codTorneo");
				faces.getExternalContext().getApplicationMap().remove("codTorneo");
			}
			
			
			this.posiciones = Comunicacion.getInstance().getCampeonatoControlador().obtenerPosiciones(codTorneo);
			this.goleadores = Comunicacion.getInstance().getCampeonatoControlador().obtenerGoleadoresTorneo(codTorneo);
			this.fixture = Comunicacion.getInstance().getCampeonatoControlador().obtenerFixtureTorneo(codTorneo);
			
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}

	public List<Posicion> getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(List<Posicion> posiciones) {
		this.posiciones = posiciones;
	}

	public List<Jugador> getGoleadores() {
		return goleadores;
	}

	public void setGoleadores(List<Jugador> goleadores) {
		this.goleadores = goleadores;
	}

	public List<DatosFixture> getFixture() {
		return fixture;
	}

	public void setFixture(List<DatosFixture> fixture) {
		this.fixture = fixture;
	}

}
