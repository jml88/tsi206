package web_jatrik;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import comunicacion.Comunicacion;
import datatypes.DatosManager;
import equipos.Equipo;

@Named("loginBB")
@ViewScoped
public class LoginBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;

	private String password;
	
	private List<Equipo> equipos;

	public LoginBB() {
	}
	
	@PostConstruct
	public void init() {
//		try {
//			this.torneos = Comunicacion.getInstance().getCampeonatoControlador().obtenerTorneosActuales();
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
	}

	public String registrarse() {
		return "toRegister";
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login() throws IOException {
		String result = "";
		try {
			// en el false va remember
			SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password, false));
			int managerId = Comunicacion.getInstance().getIUserControlador().findUserByUserName(username);
			DatosManager datosManager = Comunicacion.getInstance().getIUserControlador().obtenerManager(managerId);
			Comunicacion.getInstance().getSesion().setDatosManager(datosManager);
			File escudo = Comunicacion.getInstance().getIUserControlador().obtenerEscudo(datosManager.getCodEquipo());
        	Comunicacion.getInstance().getSesion().setEscudo(escudo);
	        if (!datosManager.getName().equals("admin")) {
				result = "/webPages/home/home.xhtml?faces-redirect=true";
			} else {
				result = "/webPages/admin/admin.xhtml?faces-redirect=true";
			}
			// SavedRequest savedRequest =
			// WebUtils.getAndClearSavedRequest(Faces.getRequest());
			// Faces.redirect(savedRequest != null ?
			// savedRequest.getRequestUrl() : HOME_URL);
			// RecibeMensaje r = new RecibeMensaje();

		} catch (UnknownAccountException ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
		} catch (IncorrectCredentialsException ex) {
			ex.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", ex.getMessage()));
		} catch (LockedAccountException ex) {
			ex.printStackTrace();
		} catch (ExcessiveAttemptsException ex) {
			ex.printStackTrace();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return result;
	}

}
