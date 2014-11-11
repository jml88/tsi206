package web_jatrik;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import partidos.Partido;

import comunicacion.Comunicacion;

@Named("verPartidosBB")
@ViewScoped
public class VerPartidosBB implements Serializable {
	
	@Inject
	SessionBB sesion;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Set<Partido> partidos;
	private Partido partidoSeleccionado;
	private Map<Integer, String> nombresEquipos;
	
	public VerPartidosBB() {
		super();
		this.partidos = new HashSet<Partido>();
		this.nombresEquipos = new HashMap<Integer, String>();
		this.partidoSeleccionado = null;
	}
	
	@PostConstruct
	public void init() {
		try {
			this.partidos = Comunicacion.getInstance().getIPartidoControlador().obtenerPartidosAmistososUsuario(sesion.getDatosManager().getCodEquipo());
			for (Partido dp : this.partidos) {
				this.nombresEquipos.put(dp.getLocal().getCodigo(),
						Comunicacion.getInstance().getIEquipoControlador().obtenerEquipo(dp.getLocal().getCodigo()).getNombre());
				this.nombresEquipos.put(dp.getVisitante().getCodigo(),
						Comunicacion.getInstance().getIEquipoControlador().obtenerEquipo(dp.getVisitante().getCodigo()).getNombre());
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public String agregarAlineacion() {
		String result = "";
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		context.getApplicationMap().put("idPartido", this.partidoSeleccionado.getCodigo());
		result = "/webPages/partidos/enviarOrdenesPartido.xhtml?faces-redirect=true";
		return result;
	}
	
	public String verPartidoEnVivo() {
		String result = "";
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		context.getApplicationMap().put("DatosPartido", this.partidoSeleccionado);
		result = "/webPages/partidos/minutoAMinuto.xhtml?faces-redirect=true";
		return result;
	}
	
	
	public String volver() {
		return "/webPages/home/home.xhtml?faces-redirect=true";
	}

	public Set<Partido> getPartidos() {
		return partidos;
	}

	public void setPartidos(Set<Partido> partidos) {
		this.partidos = partidos;
	}

	public Partido getPartidoSeleccionado() {
		return partidoSeleccionado;
	}

	public void setPartidoSeleccionado(Partido partidoSeleccionado) {
		this.partidoSeleccionado = partidoSeleccionado;
	}

	public Map<Integer, String> getNombresEquipos() {
		return nombresEquipos;
	}

	public void setNombresEquipos(Map<Integer, String> nombresEquipos) {
		this.nombresEquipos = nombresEquipos;
	}
}
