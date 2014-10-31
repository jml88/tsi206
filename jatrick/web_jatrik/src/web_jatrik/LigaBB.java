package web_jatrik;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import campeonato.Torneo;
import comunicacion.Comunicacion;
import equipos.Equipo;

@Named("ligaBB")
@ViewScoped
public class LigaBB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	List<Equipo> equipos;
	
	Torneo torneo;
	
	
	@Inject
	SessionBB sesion;
	
	@PostConstruct
	public void init() {
		try {
			List<Torneo> torneos = Comunicacion.getInstance().getCampeonatoControlador().obtenerTorneos();
			for (Torneo t : torneos) {
				for (Equipo equipo : t.getEquipos()) {
					if (equipo.getCodigo() == sesion.getDatosManager().getCodEquipo()){
						this.torneo = t;
						this.equipos = this.torneo.getEquipos();
//						this.torneo.ge
						break;
					}
				}
			}
			
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}
	
	

}
