package web_jatrik;

import java.io.Serializable;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import comunicacion.Comunicacion;
import equipos.Equipo;

@Named("verOtroEquipoBB")
@ViewScoped
public class VerOtroEquipoBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	SessionBB sesion;
	
	private Equipo codEquipo;
	private Equipo equipo;

	
	@PostConstruct
	public void init() {
		
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public Equipo getCodEquipo() {
		return codEquipo;
	}

	public void setCodEquipo(Equipo codEquipo) {
		this.codEquipo = codEquipo;
	}
}
