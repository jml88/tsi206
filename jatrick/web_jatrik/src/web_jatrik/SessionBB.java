package web_jatrik;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;

import datatypes.DatosManager;

@Named("sessionBB")
@SessionScoped
public class SessionBB implements Serializable {

	private static final long serialVersionUID = 1L;
	private DatosManager datosManager;

	public SessionBB() {
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
		return "logoutOK";
	}
}
