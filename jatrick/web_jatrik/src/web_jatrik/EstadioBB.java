package web_jatrik;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import equipos.Equipo;
import equipos.Estadio;
import excepciones.CapitalNegativo;
import excepciones.NoExisteEquipoExcepcion;
import excepciones.NoSePuedeAgrandarEstadio;

@Named("estadioBB")
@ViewScoped
public class EstadioBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private Estadio estadio;
	
	private int codigoEquipo;
	
	private int cuestaAgrandar;
	
	private int cantidadAgranda;
	
	private List<Integer[]> ligasGanadas;
	
	private Equipo equipo;
	
	@Inject
	private SessionBB session;
	
	@PostConstruct
	public void init(){
		codigoEquipo = session.getDatosManager().getCodEquipo();
		
		try {
			equipo = comunicacion.Comunicacion.getInstance().getIEquipoControlador().findEquipo(codigoEquipo);
			estadio = comunicacion.Comunicacion.getInstance().getIEquipoControlador().estadioDeEquipo(codigoEquipo);
			ligasGanadas = new LinkedList<Integer[]>();

			for (Map.Entry<Integer, Integer> entry : equipo.getLigasGanadas().entrySet())
			{
				Integer[] i =new Integer[2];
				i[0] = entry.getKey();
				i[1] = entry.getValue();
				ligasGanadas.add(i);
			}
			cuestaAgrandar = comunicacion.Comunicacion.getInstance().getConfiguracionControlador().getConfiguracion().getCuestaAgrandarEstadio();
			cantidadAgranda = comunicacion.Comunicacion.getInstance().getConfiguracionControlador().getConfiguracion().getCapacidadAgrandarEstadio();
		} catch (NoExisteEquipoExcepcion | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}
	}
	
	public String agrandarEstadio(){
		try {
			comunicacion.Comunicacion.getInstance().getIEquipoControlador().mejorarEstadioEquipo(codigoEquipo);
			estadio = comunicacion.Comunicacion.getInstance().getIEquipoControlador().estadioDeEquipo(codigoEquipo);
		} catch (NoExisteEquipoExcepcion | CapitalNegativo
				| NoSePuedeAgrandarEstadio | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}
		
		return "";
	}

	public Estadio getEstadio() {
		return estadio;
	}

	public void setEstadio(Estadio estadio) {
		this.estadio = estadio;
	}

	public int getCuestaAgrandar() {
		return cuestaAgrandar;
	}

	public void setCuestaAgrandar(int valorAgrandar) {
		this.cuestaAgrandar = valorAgrandar;
	}

	public int getCantidadAgranda() {
		return cantidadAgranda;
	}

	public void setCantidadAgranda(int cantidadAgranda) {
		this.cantidadAgranda = cantidadAgranda;
	}

	public Equipo getEquipo() {
		return equipo;
	}

	public void setEquipo(Equipo equipo) {
		this.equipo = equipo;
	}

	public List<Integer[]> getLigasGanadas() {
		return ligasGanadas;
	}

	public void setLigasGanadas(List<Integer[]> ligasGanadas) {
		this.ligasGanadas = ligasGanadas;
	}
	
}
