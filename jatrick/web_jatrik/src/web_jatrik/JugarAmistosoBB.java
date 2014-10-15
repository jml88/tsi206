package web_jatrik;

import java.io.Serializable;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.model.SelectItem;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.naming.NamingException;

import comunicacion.Comunicacion;
import datatypes.DatosEquipo;

@Named("jugarAmistosoBB")
@ViewScoped
public class JugarAmistosoBB implements Serializable {
	
	private Set<DatosEquipo> equipos;
	private int equipoSelected;
	private SelectItem[] equiposItems;
	private DatosEquipo equipoSeleccionado;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public JugarAmistosoBB() {
		super();
		this.equipoSelected = -1;
		this.equipos = new HashSet<DatosEquipo>();
		this.equiposItems = new SelectItem[this.equipos.size()];
	}
	
	@PostConstruct
	public void init() {
		try {
			this.equipos = Comunicacion.getInstance().getIEquipoControlador().obtenerEquiposSistema();
			this.equiposItems = new SelectItem[this.equipos.size()];
			int i = 0;
			for (DatosEquipo de : this.equipos) {
				this.equiposItems[i++] = new SelectItem(de.getCodigo(), "");
			}
		} catch (NamingException e) {
			e.printStackTrace();
		}
	}
	
	public String volver() {
		return "volver";
	}
	
	public String jugar(){
		String result = "";
		
		return result;
	}

	public Set<DatosEquipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(Set<DatosEquipo> equipos) {
		this.equipos = equipos;
	}

	public int getEquipoSelected() {
		return equipoSelected;
	}

	public void setEquipoSelected(int equipoSelected) {
		this.equipoSelected = equipoSelected;
	}

	public SelectItem[] getEquiposItems() {
		return equiposItems;
	}

	public void setEquiposItems(SelectItem[] equiposItems) {
		this.equiposItems = equiposItems;
	}

	public DatosEquipo getEquipoSeleccionado() {
		return equipoSeleccionado;
	}

	public void setEquipoSeleccionado(DatosEquipo equipoSeleccionado) {
		this.equipoSeleccionado = equipoSeleccionado;
	}
	
	
}
