package web_jatrik;

import interfaces.IUserControlador;

import java.io.IOException;
import java.io.Serializable;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;

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
	
	@Inject
	private IUserControlador iuser;
	@Inject
	private SessionBB sessionBB;
	
//	@Inject
//  private Conversation conversation;
    
    public LoginBB()
    {
//    	this.conversation.begin();
    }
    
    public String registrarse()
    {
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

		try{
			
			SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password, false)); //en el false va remember
			int managerId = iuser.findUserByUserName(username);
			DatosManager datosManager = iuser.findManager(managerId);
			sessionBB.setDatosManager(datosManager);
			
//            SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());
//            Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : HOME_URL);
//        	RecibeMensaje r = new RecibeMensaje();
        	
		} catch ( UnknownAccountException uae ) {
			uae.printStackTrace();
		} catch ( IncorrectCredentialsException ice ) {
			ice.printStackTrace();
		} catch ( LockedAccountException lae ) {
			lae.printStackTrace();
		} catch ( ExcessiveAttemptsException eae ) {
			eae.printStackTrace();
//		} catch ( AuthenticationException ae ) {
			//unexpected error?
		}
    	
        return "loginOK";
    }
    
    
}
