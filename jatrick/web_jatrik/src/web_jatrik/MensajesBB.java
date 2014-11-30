package web_jatrik;

import java.io.Serializable;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import comunicacion.Comunicacion;
import mensajes.Mensaje;

@Named("mensajesBB")
@ViewScoped
public class MensajesBB implements Serializable {

	@Inject
	private SessionBB session;

	private List<Mensaje> mensajes;

	private Mensaje mensajeSeleccionado;

	private List<Mensaje> mensajesSeleccionados;
	
	private String mensaje;

	@PostConstruct
	public void init() {
		try {
			mensajeSeleccionado = null;
			mensajes = Comunicacion.getInstance().getIEquipoControlador()
					.listarMensajes(session.getDatosManager().getCodEquipo());
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public String seleccionoMensaje(Mensaje m) {
		try {
			Comunicacion.getInstance().getIEquipoControlador()
					.marcarComoLeido(m.getCodigo());
			m.setLeido(true);
			this.mensajeSeleccionado = m;
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/webPages/mensajes/verMensaje.xhtml?faces-redirect=true";
	}

	public String seleccionoMensajeBorrar() {
		try {

			Comunicacion.getInstance().getIEquipoControlador()
					.borrarMensaje(mensajesSeleccionados);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/webPages/mensajes/mensajes.xhtml?faces-redirect=true";
	}
	
	public String contestarMensaje(){
		try {
			Calendar c = Calendar.getInstance();
			Comunicacion.getInstance().getIEquipoControlador().crearMensaje("Re:"+mensajeSeleccionado.getTitulo(), "", c.getTime(), session.getDatosManager().getCodEquipo(), mensajeSeleccionado.getRemitente().getCodigo());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "/webPages/mensajes/mensajes.xhtml?faces-redirect=true";
	}

	public List<Mensaje> getMensajes() {
		return mensajes;
	}

	public void setMensajes(List<Mensaje> mensajes) {
		this.mensajes = mensajes;
	}

	public Mensaje getMensajeSeleccionado() {
		return mensajeSeleccionado;
	}

	public void setMensajeSeleccionado(Mensaje mensajeSeleccionado) {
		this.mensajeSeleccionado = mensajeSeleccionado;
	}

	public List<Mensaje> getMensajesSeleccionados() {
		return mensajesSeleccionados;
	}

	public void setMensajesSeleccionados(List<Mensaje> mensajesSeleccionados) {
		this.mensajesSeleccionados = mensajesSeleccionados;
	}

	public String getMensaje() {
		return mensaje;
	}

	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}

}
