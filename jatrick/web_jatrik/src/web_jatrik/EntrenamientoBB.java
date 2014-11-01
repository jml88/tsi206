package web_jatrik;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import comunicacion.Comunicacion;
import datatypes.EnumEntrenamiento;
import excepciones.NoExisteEquipoExcepcion;

@Named("entrenamientoBB")
@ViewScoped
public class EntrenamientoBB implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private EnumEntrenamiento entrenamiento;
	
	private int entrenamientoValue;
	
	
	@Inject
	SessionBB sesion;
	
	private int idEquipo;

	public EnumEntrenamiento getEntrenamiento() {
		return entrenamiento;
	}

	public void setEntrenamiento(EnumEntrenamiento entrenamiento) {
		this.entrenamiento = entrenamiento;
	}
	
	private void convertEntrenamientoToEnum(){
		switch(entrenamientoValue){
		case 0:
			entrenamiento = EnumEntrenamiento.ATAQUE;
			break;
		case 1:
			entrenamiento = EnumEntrenamiento.DEFENSA;
			break;
		case 2:
			entrenamiento = EnumEntrenamiento.TECNICA;
			break;
		case 3:
			entrenamiento = EnumEntrenamiento.PORTERO;
			break;
		}
		
	}
	
	private void convertEntrenamientoToInt(){
		switch (entrenamiento) {
		case ATAQUE:
			entrenamientoValue = 0;
			break;
		case DEFENSA:
			entrenamientoValue = 1;
			break;
		case TECNICA:
			entrenamientoValue = 2;
			break;
		case PORTERO:
			entrenamientoValue = 3;
			break;
		}
	}
	
	@PostConstruct
	public void init(){
		idEquipo = sesion.getDatosManager().getCodEquipo();
		try {
			entrenamiento = Comunicacion.getInstance().getIEquipoControlador().entrenamientoEquipo(idEquipo);
			convertEntrenamientoToInt();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String modificarTipoEntrenamiento(){
		try {
			try {
				convertEntrenamientoToEnum();
				Comunicacion.getInstance().getIEquipoControlador().modificarTipoEntrenamientoEquipo(idEquipo, entrenamiento);
			} catch (NoExisteEquipoExcepcion e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}

	public int getIdEquipo() {
		return idEquipo;
	}

	public void setIdEquipo(int idEquipo) {
		this.idEquipo = idEquipo;
	}

	public int getEntrenamientoValue() {
		return entrenamientoValue;
	}

	public void setEntrenamientoValue(int entrnamientoValue) {
		this.entrenamientoValue = entrnamientoValue;
	}
	
}
