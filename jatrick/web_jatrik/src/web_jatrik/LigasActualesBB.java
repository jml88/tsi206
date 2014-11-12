package web_jatrik;

import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import campeonato.Torneo;
import comunicacion.Comunicacion;

@Named("ligasActualesBB")
@ViewScoped
public class LigasActualesBB implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Torneo> torneos;

	@Inject
	SessionBB sesion;
	
	@PostConstruct
	public void init() {
		try {
			this.torneos = Comunicacion.getInstance().getCampeonatoControlador().obtenerTorneosActuales();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String seleccionoLiga(Torneo torneo){
		String result = "/webPages/competiciones/liga.xhtml?faces-redirect=true";
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		context.getApplicationMap().put("codTorneo", torneo.getCodigo());
		return result;
	}

	public List<Torneo> getTorneos() {
		return torneos;
	}

	public void setTorneos(List<Torneo> torneos) {
		this.torneos = torneos;
	}

}

