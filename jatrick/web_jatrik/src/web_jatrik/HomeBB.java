package web_jatrik;

import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;

import partidos.Partido;
import comunicacion.Comunicacion;
import datatypes.DatosEquipo;
import datatypes.DatosJugador;

@Named("homeBB")
@ViewScoped
public class HomeBB implements Serializable {

	@Inject
	SessionBB sesion;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int codEquipo;
	private DatosEquipo equipo;
	private Set<DatosJugador> jugadores;
	private Set<DatosEquipo> otrosEquipos;
	private List<Partido> partidosProximos;
	private List<Partido> partidosAnteriores;
	private Partido partidoSeleccionado;
	private Partido partidoSeleccionadoAnterior;
	

	public HomeBB() {
		super();
	}
	
	public String verEquipo(int codigo){
		return "/webPages/competiciones/liga.xhtml?faces-redirect=true";
	}

	@PostConstruct
	public void init() {
		try {
			this.codEquipo = this.sesion.getDatosManager().getCodEquipo();
			// this.jugadores =
			// Comunicacion.getInstance().getIEquipoControlador().obtenerJugadoresEquipo(this.codEquipo);
			if (!this.sesion.getDatosManager().getName().equals("admin")) {
				this.equipo = Comunicacion.getInstance()
						.getIEquipoControlador().obtenerEquipo(this.codEquipo);
				this.partidosProximos = Comunicacion
						.getInstance()
						.getIEquipoControlador()
						.obtenerProximosPartidos(this.sesion.getDatosManager().getCodEquipo(),
								5);
				this.partidosAnteriores = Comunicacion
						.getInstance()
						.getIEquipoControlador()
						.obtenerAnterioresPartidos(this.sesion.getDatosManager().getCodEquipo(),
								5);
			}
			this.otrosEquipos = new HashSet<DatosEquipo>();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	// NAVEGACIONES
	public String agregarAlineacion() {
		String result = "";
		ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
		context.getApplicationMap().put("idPartido", this.partidoSeleccionado.getCodigo());
		context.getApplicationMap().put("retorno", "home");
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
	
	public String verEquipo(ActionEvent actionEvent) {
		String result = "";
		
		return result;
	}

	public String jugarAmistoso() {
		return "/webPages/partidos/jugarAmistoso.xhtml?faces-redirect=true";
	}

	public String verAlineacion() {
		return "/webPages/partidos/enviarOrdenesPartido.xhtml?faces-redirect=true";
	}

	public String verPartidos() {
		return "/webPages/partidos/verPartidos.xhtml?faces-redirect=true";
	}
	
	public String entrenamiento(){
		return "/webPages/entrenamiento/entrenamiento.xhtml?faces-redirect=true";
	}

	// GETS Y SETS
	public DatosEquipo getEquipo() {
		return equipo;
	}

	public void setEquipo(DatosEquipo equipo) {
		this.equipo = equipo;
	}

	public Set<DatosJugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(Set<DatosJugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Set<DatosEquipo> getOtrosEquipos() {
		return otrosEquipos;
	}

	public void setOtrosEquipos(Set<DatosEquipo> otrosEquipos) {
		this.otrosEquipos = otrosEquipos;
	}

	public int getCodEquipo() {
		return codEquipo;
	}

	public void setCodEquipo(int codEquipo) {
		this.codEquipo = codEquipo;
	}

	public List<Partido> getPartidosProximos() {
		return partidosProximos;
	}

	public void setPartidosProximos(List<Partido> partidosProximos) {
		this.partidosProximos = partidosProximos;
	}

	public Partido getPartidoSeleccionado() {
		return partidoSeleccionado;
	}

	public void setPartidoSeleccionado(Partido partidoSeleccionado) {
		this.partidoSeleccionado = partidoSeleccionado;
	}

	public List<Partido> getPartidosAnteriores() {
		return partidosAnteriores;
	}

	public void setPartidosAnteriores(List<Partido> partidosAnteriores) {
		this.partidosAnteriores = partidosAnteriores;
	}

	public Partido getPartidoSeleccionadoAnterior() {
		return partidoSeleccionadoAnterior;
	}

	public void setPartidoSeleccionadoAnterior(Partido partidoSeleccionadoAnterior) {
		this.partidoSeleccionadoAnterior = partidoSeleccionadoAnterior;
	}

}
