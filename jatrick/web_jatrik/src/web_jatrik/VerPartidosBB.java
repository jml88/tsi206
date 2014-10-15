package web_jatrik;

import java.io.Serializable;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import org.primefaces.context.RequestContext;

import comunicacion.Comunicacion;
import datatypes.DatosPartido;

@Named("verPartidosBB")
@ViewScoped
public class VerPartidosBB implements Serializable {
	
	@Inject
	SessionBB sesion;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private Set<DatosPartido> partidos;
	private DatosPartido partidoSeleccionado;
	private Map<Integer, String> nombresEquipos;
	
	public VerPartidosBB() {
		super();
		this.partidos = new HashSet<DatosPartido>();
		this.nombresEquipos = new HashMap<Integer, String>();
		this.partidoSeleccionado = null;
	}
	
	@PostConstruct
	public void init() {
		try {
			this.partidos = Comunicacion.getInstance().getIPartidoControlador().obtenerPartidosUsuario(sesion.getDatosManager().getCodEquipo());
			for (DatosPartido dp : this.partidos) {
				this.nombresEquipos.put(dp.getEquipoLocalId(),
						Comunicacion.getInstance().getIEquipoControlador().obtenerEquipo(dp.getEquipoLocalId()).getNombre());
				this.nombresEquipos.put(dp.getEquipoVisitanteId(),
						Comunicacion.getInstance().getIEquipoControlador().obtenerEquipo(dp.getEquipoVisitanteId()).getNombre());
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public String agregarAlineacion() {
		String result = "";
		RequestContext request = RequestContext.getCurrentInstance();
		request.getAttributes().put("idPartido", this.partidoSeleccionado.getCodigo());
		result = "/webPages/partidos/enviarOrdenesPartido.xhtml?faces-redirect=true";
		return result;
	}
	
	public String volver() {
		return "/webPages/home/home.xhtml?faces-redirect=true";
	}

	public Set<DatosPartido> getPartidos() {
		return partidos;
	}

	public void setPartidos(Set<DatosPartido> partidos) {
		this.partidos = partidos;
	}

	public DatosPartido getPartidoSeleccionado() {
		return partidoSeleccionado;
	}

	public void setPartidoSeleccionado(DatosPartido partidoSeleccionado) {
		this.partidoSeleccionado = partidoSeleccionado;
	}

	public Map<Integer, String> getNombresEquipos() {
		return nombresEquipos;
	}

	public void setNombresEquipos(Map<Integer, String> nombresEquipos) {
		this.nombresEquipos = nombresEquipos;
	}
}
