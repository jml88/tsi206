package practico_web;

import java.io.IOException;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;
import javax.naming.AuthenticationException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.ExcessiveAttemptsException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.web.util.SavedRequest;
import org.apache.shiro.web.util.WebUtils;
import org.primefaces.component.messages.Messages;

import com.example.db.User;
import com.example.service.UserService;
import com.example.service.UsersInterface;


//@Stateless(mappedName = "loginBB")
//@ManagedBean
//@LocalBean
@Named
@RequestScoped
public class LoginBB {
	
	@EJB
	private UserService u;
	
	private String username;
	private String password;
	
	public LoginBB(){
		
	}
	
	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login(){
		User usuario = u.login(this.username, this.password);
		return "loginOK";
	}
	
	public String submit() throws IOException {
        try {
            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password, false)); //en el false va remember
//            SavedRequest savedRequest = WebUtils.getAndClearSavedRequest(Faces.getRequest());
//            Faces.redirect(savedRequest != null ? savedRequest.getRequestUrl() : HOME_URL);
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
	
	public String logout(){
		System.out.println("Se llamo logout");
		SecurityUtils.getSubject().logout();
		System.out.println("Paso SecurityUtils.getSubject().logout();");
		return "logoutOK";
	}
}
