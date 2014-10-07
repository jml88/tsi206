package web_jatrik;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;

import comunicacion.Comunicacion;

import datatypes.DatosManager;

@Named("loginBB")
@RequestScoped
public class LoginBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String username;

	private String password;

	public LoginBB() {
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
			DatosManager datosManager = Comunicacion.getInstance().getIUserControlador().findManager(managerId);
			Comunicacion.getInstance().getSesion().setDatosManager(datosManager);
			result = "loginOK";

			// SavedRequest savedRequest =
			// WebUtils.getAndClearSavedRequest(Faces.getRequest());
			// Faces.redirect(savedRequest != null ?
			// savedRequest.getRequestUrl() : HOME_URL);
			// RecibeMensaje r = new RecibeMensaje();

		} catch (UnknownAccountException ex) {
			ex.printStackTrace();
		} catch (IncorrectCredentialsException ex) {
			ex.printStackTrace();
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
