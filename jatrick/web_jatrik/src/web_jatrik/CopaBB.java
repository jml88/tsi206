package web_jatrik;

import java.awt.event.ActionEvent;
import java.io.Serializable;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import campeonato.Copa;
import comunicacion.Comunicacion;
import datatypes.DatosCopa;

@Named("copaBB")
@ViewScoped
public class CopaBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private List<Copa> copasFuturas;

	private Date fecha;

	private int cantidadEquipos;

	private String nombreCopa;

	private int inscripcion;

	private DatosCopa fixtureCopa;

	private List<Integer> codigosCopa;

	private List<Copa> copasEquipo;

	private boolean eligioTorneo = false;

	private int nroPestania;

	@Inject
	SessionBB sesion;

	@PostConstruct
	public void init() {
		try {
			eligioTorneo = false;
			FacesContext faces = FacesContext.getCurrentInstance();
			if (faces.getExternalContext().getApplicationMap().get("copa") != null) {
				this.fixtureCopa = (DatosCopa)faces.getExternalContext().getApplicationMap().get("fixture");
				faces.getExternalContext().getApplicationMap().remove("copa");
				this.eligioTorneo = true;
				this.nroPestania = 2;
			}
			this.copasEquipo = Comunicacion
					.getInstance()
					.getIEquipoControlador()
					.obtenerCopasEquipo(sesion.getDatosManager().getCodEquipo());
			this.codigosCopa = new LinkedList<Integer>();
			for (Copa c : copasEquipo) {
				this.codigosCopa.add(c.getCodigo());
			}

			this.copasFuturas = Comunicacion.getInstance()
					.getCampeonatoControlador().obtenerCopasFuturas();
			if (codigosCopa.size() == 1) {
				this.fixtureCopa = Comunicacion.getInstance()
						.getCampeonatoControlador()
						.obtenerFixtureCopa(this.codigosCopa.get(0));
				this.eligioTorneo = true;
			}

		} catch (Exception e) {

		}
	}

	public String crearCopa() {
		Calendar c = Calendar.getInstance();
		c.setTime(this.fecha);
		try {
			Comunicacion
					.getInstance()
					.getCampeonatoControlador()
					.crearCopa(this.cantidadEquipos, c, this.inscripcion,
							this.nombreCopa);

		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/webPages/competiciones/copas.xhtml?faces-redirect=true";
	}

	public List<Copa> getCopasFuturas() {
		return copasFuturas;
	}

	public void setCopasFuturas(List<Copa> copasFuturas) {
		this.copasFuturas = copasFuturas;
	}

	public String anotarse(int codigo) {
		try {
			Comunicacion
					.getInstance()
					.getCampeonatoControlador()
					.agregarEquipoACopa(
							sesion.getDatosManager().getCodEquipo(), codigo);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/webPages/competiciones/copas.xhtml?faces-redirect=true";
	}

	public String seleccionoCopa(int codigo) {
		try {
			this.fixtureCopa = Comunicacion.getInstance()
					.getCampeonatoControlador().obtenerFixtureCopa(codigo);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		this.nroPestania = 2;
		this.eligioTorneo = true;
		ExternalContext context = FacesContext.getCurrentInstance()
				.getExternalContext();
		context.getApplicationMap().put("copa", true);
		context.getApplicationMap().put("fixture", this.fixtureCopa);
		return "/webPages/competiciones/copas.xhtml?faces-redirect=true";
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public int getCantidadEquipos() {
		return cantidadEquipos;
	}

	public void setCantidadEquipos(int cantidadEquipos) {
		this.cantidadEquipos = cantidadEquipos;
	}

	public String getNombreCopa() {
		return nombreCopa;
	}

	public void setNombreCopa(String nombreCopa) {
		this.nombreCopa = nombreCopa;
	}

	public int getInscripcion() {
		return inscripcion;
	}

	public void setInscripcion(int inscripcion) {
		this.inscripcion = inscripcion;
	}

	public DatosCopa getFixtureCopa() {
		return fixtureCopa;
	}

	public void setFixtureCopa(DatosCopa fixtureCopa) {
		this.fixtureCopa = fixtureCopa;
	}

	public List<Integer> getCodigosCopa() {
		return codigosCopa;
	}

	public void setCodigosCopa(List<Integer> codigosCopa) {
		this.codigosCopa = codigosCopa;
	}

	public boolean isEligioTorneo() {
		return eligioTorneo;
	}

	public void setEligioTorneo(boolean eligioTorneo) {
		this.eligioTorneo = eligioTorneo;
	}

	public int getNroPestania() {
		return nroPestania;
	}

	public void setNroPestania(int nroPestania) {
		this.nroPestania = nroPestania;
	}

	public List<Copa> getCopasEquipo() {
		return copasEquipo;
	}

	public void setCopasEquipo(List<Copa> copasEquipo) {
		this.copasEquipo = copasEquipo;
	}

}
