package web_jatrik;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import campeonato.Posicion;

import comunicacion.Comunicacion;

@Named("ligaBB")
@ViewScoped
public class LigaBB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Posicion> posiciones;

	@Inject
	SessionBB sesion;
	
	@PostConstruct
	public void init() {
		try {
			this.posiciones = Comunicacion.getInstance().getCampeonatoControlador().obtenerPosiciones(sesion.getDatosManager().getCodTorneo());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Posicion> getPosiciones() {
		return posiciones;
	}

	public void setPosiciones(List<Posicion> posiciones) {
		this.posiciones = posiciones;
	}

}
