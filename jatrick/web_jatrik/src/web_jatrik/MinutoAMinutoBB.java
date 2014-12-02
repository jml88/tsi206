package web_jatrik;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import jugadores.Jugador;
import partidos.Comentario;
import partidos.Partido;
import partidos.ResultadoPartido;
import comunicacion.Comunicacion;
import datatypes.DatosEquipo;
import excepciones.NoExistePartidoExepcion;

@Named("minutoAMinutoBB")
@ViewScoped
public class MinutoAMinutoBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Comentario> comentariosPartido;

	private Partido datosPartido;

	List<Jugador> jugadoresLocal;

	List<Jugador> jugadoresVisitante;

	private DatosEquipo equipoLocal, equipoVisitante;

	private int numeroComentario;

	private ResultadoPartido resultadoPartido;

	@Inject
	SessionBB sesion;

	@PostConstruct
	public void init() {
		try {
			FacesContext faces = FacesContext.getCurrentInstance();
			datosPartido = (Partido) faces.getExternalContext()
					.getApplicationMap().get("DatosPartido");
			Integer codPartido = (Integer) faces.getExternalContext()
					.getApplicationMap().get("codPartido");
			if (codPartido != null){
				datosPartido = Comunicacion.getInstance().getIPartidoControlador().findPartido(codPartido);
			}
			if (datosPartido != null) {

				equipoLocal = Comunicacion.getInstance()
						.getIEquipoControlador()
						.obtenerEquipo(datosPartido.getLocal().getCodigo());
				equipoVisitante = Comunicacion.getInstance()
						.getIEquipoControlador()
						.obtenerEquipo(datosPartido.getVisitante().getCodigo());
				numeroComentario = 0;
				comentariosPartido = new LinkedList<Comentario>();
				jugadoresLocal = new LinkedList<Jugador>();
				jugadoresVisitante = new LinkedList<Jugador>();
				jugadoresLocal.add(datosPartido.getAlineacionLocal()
						.getGolero());
				jugadoresLocal.addAll(datosPartido.getAlineacionLocal()
						.getDefensas());
				eliminarRepetidos(jugadoresLocal);
				jugadoresLocal.addAll(datosPartido.getAlineacionLocal()
						.getMediocampistas());
				eliminarRepetidos(jugadoresLocal);
				jugadoresLocal.addAll(datosPartido.getAlineacionLocal()
						.getDelanteros());
				eliminarRepetidos(jugadoresLocal);
				jugadoresVisitante.add(datosPartido.getAlineacionVisitante()
						.getGolero());
				jugadoresVisitante.addAll(datosPartido.getAlineacionVisitante()
						.getDefensas());
				eliminarRepetidos(jugadoresVisitante);
				jugadoresVisitante.addAll(datosPartido.getAlineacionVisitante()
						.getMediocampistas());
				eliminarRepetidos(jugadoresVisitante);
				jugadoresVisitante.addAll(datosPartido.getAlineacionVisitante()
						.getDelanteros());
				eliminarRepetidos(jugadoresVisitante);
			}
			
			comentariosPartido.add(new Comentario(-1,
					"El partido va a comenzar", null, 0));
			List<Comentario> comentarios = Comunicacion
					.getInstance()
					.getIPartidoControlador()
					.obtenerComentariosPartido(datosPartido.getCodigo(),
							numeroComentario);
			for (Comentario comentario : comentarios) {
				if (numeroComentario < comentario.getId()) {
					numeroComentario = comentario.getId();
				}
				this.comentariosPartido.add(comentario);
			}
			this.resultadoPartido = Comunicacion.getInstance()
					.getIPartidoControlador()
					.obtenerResultadoPartido(datosPartido.getCodigo());
			actualizarJugadores(jugadoresVisitante,resultadoPartido.getGoleadoresVisitante());
			actualizarJugadores(jugadoresLocal,resultadoPartido.getGoleadoresLocal());
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	private void eliminarRepetidos(List<Jugador> lista) {
		HashSet<Jugador> hs = new HashSet<Jugador>();
		hs.addAll(lista);
		lista.clear();
		lista.addAll(hs);
	}
	
	private void actualizarJugadores(List<Jugador> jugadores, Set<Jugador> jugadoresG){
		for (Jugador jugador : jugadores) {
			for(Jugador jugadorG : jugadoresG){
				if(jugadorG.getCodigo() == jugador.getCodigo()){
					jugador.setGolesMostrar(jugador.getGolesMostrar()+1);
				}
			}
		}
	}

	public void getComentariosDePartido() {
		try {
			List<Comentario> comentarios = Comunicacion
					.getInstance()
					.getIPartidoControlador()
					.obtenerComentariosPartido(datosPartido.getCodigo(),
							numeroComentario);
			// comentarios.add(new DatosComentario(1, "mensaje", 1, 1, 5));

			for (Comentario c : comentarios) {
				if (numeroComentario < c.getId()) {
					numeroComentario = c.getId();
				}
				comentariosPartido.add(c);
				this.resultadoPartido = Comunicacion.getInstance()
						.getIPartidoControlador()
						.obtenerResultadoPartido(datosPartido.getCodigo());
			}
		} catch (NoExistePartidoExepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<Comentario> getComentariosPartido() {
		return comentariosPartido;
	}

	public void setComentariosPartido(List<Comentario> comentariosPartido) {
		this.comentariosPartido = comentariosPartido;
	}

	public Partido getDatosPartido() {
		return datosPartido;
	}

	public void setDatosPartido(Partido datosPartido) {
		this.datosPartido = datosPartido;
	}

	public DatosEquipo getEquipoLocal() {
		return equipoLocal;
	}

	public void setEquipoLocal(DatosEquipo equipoLocal) {
		this.equipoLocal = equipoLocal;
	}

	public DatosEquipo getEquipoVisitante() {
		return equipoVisitante;
	}

	public void setEquipoVisitante(DatosEquipo equipoVisitante) {
		this.equipoVisitante = equipoVisitante;
	}

	public int getNumeroComentario() {
		return numeroComentario;
	}

	public void setNumeroComentario(int numeroComentario) {
		this.numeroComentario = numeroComentario;
	}

	public ResultadoPartido getResultadoPartido() {
		return resultadoPartido;
	}

	public void setResultadoPartido(ResultadoPartido resultadoPartido) {
		this.resultadoPartido = resultadoPartido;
	}

	public List<Jugador> getJugadoresLocal() {
		return jugadoresLocal;
	}

	public void setJugadoresLocal(List<Jugador> jugadoresLocal) {
		this.jugadoresLocal = jugadoresLocal;
	}

	public List<Jugador> getJugadoresVisitante() {
		return jugadoresVisitante;
	}

	public void setJugadoresVisitante(List<Jugador> jugadoresVisitante) {
		this.jugadoresVisitante = jugadoresVisitante;
	}

	public String editarAlineacion() {
		String result = "";
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		context.getApplicationMap().put("idPartido",
				this.datosPartido.getCodigo());
		context.getApplicationMap().put("retorno", "minutoAminuto");
		result = "/webPages/partidos/enviarOrdenesPartido.xhtml?faces-redirect=true";
		return result;
	}

}
