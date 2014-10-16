package web_jatrik;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import comunicacion.Comunicacion;
import datatypes.DatosComentario;
import datatypes.DatosEquipo;
import datatypes.DatosPartido;
import excepciones.NoExistePartidoExepcion;

@Named("minutoAMinutoBB")
@ViewScoped
public class MinutoAMinutoBB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<DatosComentario> comentariosPartido;
	
	private DatosPartido datosPartido;
	
	private DatosEquipo equipoLocal, equipoVisitante;
	
	private int numeroComentario;
	
	@Inject
	SessionBB sesion;
	
	@PostConstruct
	public void init(){
		try{
			FacesContext faces = FacesContext.getCurrentInstance();
			datosPartido = (DatosPartido)faces.getExternalContext().getApplicationMap().get("DatosPartido");
			equipoLocal = Comunicacion.getInstance().getIEquipoControlador().obtenerEquipo(datosPartido.getEquipoLocalId());
			equipoVisitante = Comunicacion.getInstance().getIEquipoControlador().obtenerEquipo(datosPartido.getEquipoVisitanteId());
			numeroComentario = 0;
			comentariosPartido = new LinkedList<DatosComentario>();
			comentariosPartido.add(new DatosComentario(-1, "El partido va a comenezar", 0, 0, 0));
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
	
	public void getComentariosDePartido(){
		try{
			List<DatosComentario> comentarios = Comunicacion.getInstance().getIPartidoControlador().obtenerComentariosPartido(datosPartido.getCodigo(), numeroComentario);
//			comentarios.add(new DatosComentario(1, "mensaje", 1, 1, 5));
			
			for(DatosComentario c: comentarios){
				if(numeroComentario < c.getNroComentario()){
					numeroComentario = c.getNroComentario();
				}
				comentariosPartido.add(c);
			}
		} catch (NoExistePartidoExepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public List<DatosComentario> getComentariosPartido() {
		return comentariosPartido;
	}

	public void setComentariosPartido(List<DatosComentario> comentariosPartido) {
		this.comentariosPartido = comentariosPartido;
	}

	public DatosPartido getDatosPartido() {
		return datosPartido;
	}

	public void setDatosPartido(DatosPartido datosPartido) {
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
	
	
	
	
	

}
