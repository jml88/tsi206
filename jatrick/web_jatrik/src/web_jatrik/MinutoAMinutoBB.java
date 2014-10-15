package web_jatrik;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import comunicacion.Comunicacion;
import datatypes.DatosComentario;
import datatypes.DatosEquipo;
import datatypes.DatosPartido;

@Named("minutoAMinutoBB")
@ViewScoped
public class MinutoAMinutoBB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DatosComentario datosComentario;
	
	private DatosPartido datosPartido;
	
	private DatosEquipo equipoLocal, equipoVisitante;
	
	@Inject
	SessionBB sesion;
	
	@PostConstruct
	public void init(){
		try{
			FacesContext faces = FacesContext.getCurrentInstance();
			datosPartido = (DatosPartido)faces.getExternalContext().getRequestMap().get("datosPartido");
			
			
			
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}

}
