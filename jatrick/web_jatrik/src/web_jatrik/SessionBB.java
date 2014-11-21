package web_jatrik;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.primefaces.model.StreamedContent;

import datatypes.DatosManager;

@Named("sessionBB")
@SessionScoped
public class SessionBB implements Serializable {

	private static final long serialVersionUID = 1L;
	private DatosManager datosManager;
	private StreamedContent escudo;

	public SessionBB() {
		datosManager = null;
	}

	public DatosManager getDatosManager() {
		return datosManager;
	}

	public void setDatosManager(DatosManager datosManager) {
		this.datosManager = datosManager;
	}

	public String logout() {
		datosManager = null;
		SecurityUtils.getSubject().logout();
		return "/webPages/login/login?faces-redirect=true";
	}

	public StreamedContent getEscudo() {
		return escudo;
	}

	public void setEscudo(StreamedContent escudo) {
		this.escudo = escudo;
	}
}
