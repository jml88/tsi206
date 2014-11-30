package web_jatrik;

import java.io.Serializable;
import java.rmi.RemoteException;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;
import javax.xml.rpc.ServiceException;

import partidos.Partido;
import comunicacion.Comunicacion;
import equipos.Equipo;

@Named("integracionBB")
@ViewScoped
public class IntegracionBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<Equipo> equipos;
	
	private int codigoEquipo;
	
	private Equipo equipoSeleccionado;
	
	@Inject
	private SessionBB session;
	
	@PostConstruct
	public void init(){
		try {
			setEquipos(Comunicacion.getInstance().getIIntegracion_cliente().listarEquiposIntegracion());
			codigoEquipo = session.getDatosManager().getCodEquipo();
			//Comunicacion.getInstance().getIIntegracion_cliente().jugarPartido(1, equipos.get(0));
		} catch (NamingException | RemoteException | ServiceException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public String jugarPartido(){
		try {
			int codigoPartido = Comunicacion.getInstance().getIIntegracion_cliente().jugarPartido(codigoEquipo, equipoSeleccionado);
			String result = "";
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			Partido partido = Comunicacion.getInstance().getIPartidoControlador().findPartido(codigoPartido);
			context.getApplicationMap().put("DatosPartido", partido);
			result = "/webPages/partidos/minutoAMinuto.xhtml?faces-redirect=true";
			return result;
		} catch (RemoteException | ServiceException | NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return "";
		}
	}

	public List<Equipo> getEquipos() {
		return equipos;
	}

	public void setEquipos(List<Equipo> equipos) {
		this.equipos = equipos;
	}

	public Equipo getEquipoSeleccionado() {
		return equipoSeleccionado;
	}

	public void setEquipoSeleccionado(Equipo equipoSeleccionado) {
		this.equipoSeleccionado = equipoSeleccionado;
	}
}
