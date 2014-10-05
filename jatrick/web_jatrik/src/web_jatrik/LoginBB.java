package web_jatrik;

import java.io.Serializable;

import javax.enterprise.context.Conversation;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import prueba.Test;

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
    private Conversation conversation;
	
	@Inject
	private Test test;
    
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
    
    public String submit()
    {
    	test.save();
    	System.out.println("entra++++++++++++++++++");
    	return "loginOK";
    }
    
    
}
