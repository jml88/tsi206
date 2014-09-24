package practico_web;

import java.io.IOException;

import javax.ejb.ActivationConfigProperty;
import javax.ejb.EJB;
import javax.ejb.MessageDriven;
import javax.enterprise.context.RequestScoped;
import javax.inject.Named;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.QueueReceiver;
import javax.jms.TextMessage;
import javax.naming.NamingException;

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
import com.example.service.EnviaMensaje;
import com.example.service.EnviarMensajeI;
import com.example.service.RecibeMensaje;
import com.example.service.UserService;


//@Stateless(mappedName = "loginBB")
//@ManagedBean
//@LocalBean
@Named
@RequestScoped
public class AdminBB{
	
	@EJB
	private UserService u;
	@EJB
	private EnviaMensaje e;
	
//	@EJB
//	private EnviarMensajeI e;
	
	private String username;
	private String password;
	
	private String texto;
	
	private String textoMostrar;
	
	public AdminBB(){
		this.textoMostrar = "prueba";
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

	public String getTexto() {
		return texto;
	}

	public void setTexto(String texto) {
		this.texto = texto;
	}

	public String getTextoMostrar() {
		return textoMostrar;
	}

	public void setTextoMostrar(String textoMostrar) {
		this.textoMostrar = textoMostrar;
	}

	public String login(){
		User usuario = u.login(this.username, this.password);
		return "loginOK";
	}
	
	public String enviar(){
		try {
			this.e.enviarMensaje(this.texto);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JMSException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "";
	}
	
	public String submit() throws IOException {
        try {
        	if (this.username.equals("admin")){
        		try {
					this.e.enviarMensaje("prueba");
				} catch (NamingException | JMSException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
        		return "admin";
        	}
//            SecurityUtils.getSubject().login(new UsernamePasswordToken(username, password, false)); //en el false va remember
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
	
	public String logout(){
		System.out.println("Se llamo logout");
		SecurityUtils.getSubject().logout();
		System.out.println("Paso SecurityUtils.getSubject().logout();");
		return "logoutOK";
	}
	
}
