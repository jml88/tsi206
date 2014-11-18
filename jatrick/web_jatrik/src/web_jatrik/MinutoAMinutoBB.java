package web_jatrik;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import partidos.Comentario;
import partidos.Partido;
import partidos.ResultadoPartido;
import comunicacion.Comunicacion;
import datatypes.DatosComentario;
import datatypes.DatosEquipo;
import excepciones.NoExistePartidoExepcion;

@Named("minutoAMinutoBB")
@ViewScoped
public class MinutoAMinutoBB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Comentario> comentariosPartido;
	
	private Partido datosPartido;
	
	private DatosEquipo equipoLocal, equipoVisitante;
	
	private int numeroComentario;
	
	private ResultadoPartido resultadoPartido;
	
	@Inject
	SessionBB sesion;
	
	@PostConstruct
	public void init(){
		try{
			FacesContext faces = FacesContext.getCurrentInstance();
			datosPartido = (Partido)faces.getExternalContext().getApplicationMap().get("DatosPartido");
			equipoLocal = Comunicacion.getInstance().getIEquipoControlador().obtenerEquipo(datosPartido.getLocal().getCodigo());
			equipoVisitante = Comunicacion.getInstance().getIEquipoControlador().obtenerEquipo(datosPartido.getVisitante().getCodigo());
			numeroComentario = 0;
			comentariosPartido = new LinkedList<Comentario>();
			comentariosPartido.add(new Comentario(-1, "El partido va a comenzar",null,0));
			List<Comentario> comentarios = Comunicacion.getInstance().getIPartidoControlador().obtenerComentariosPartido(datosPartido.getCodigo(), numeroComentario);
			for (Comentario comentario : comentarios) {
				this.comentariosPartido.add(comentario);
			}
			this.resultadoPartido = Comunicacion.getInstance().getIPartidoControlador().obtenerResultadoPartido(datosPartido.getCodigo());
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void getComentariosDePartido(){
		try{
			List<Comentario> comentarios = Comunicacion.getInstance().getIPartidoControlador().obtenerComentariosPartido(datosPartido.getCodigo(), numeroComentario);
//			comentarios.add(new DatosComentario(1, "mensaje", 1, 1, 5));
			
			for(Comentario c: comentarios){
				if(numeroComentario < c.getId()){
					numeroComentario = c.getId();
				}
				comentariosPartido.add(c);
				this.resultadoPartido = Comunicacion.getInstance().getIPartidoControlador().obtenerResultadoPartido(datosPartido.getCodigo());
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
	
}
