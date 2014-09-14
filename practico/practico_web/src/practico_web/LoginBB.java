package practico_web;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

import com.example.db.UsersInterface;


@Stateless(mappedName = "loginBB")
@ManagedBean
@LocalBean
public class LoginBB {
	
	@EJB
	private UsersInterface u;
	
	private String userName;
	private String password;
	
	public LoginBB(){
		
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String login(){
		u.login(this.userName, this.password);
		return "loginOK";
	}
}
