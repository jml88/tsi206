package practico_web;

import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.faces.bean.ManagedBean;

import com.example.db.UsersInterface;

@Stateless(mappedName = "registerBB")
@ManagedBean
@LocalBean
public class RegisterBB {

	@EJB
	private UsersInterface u;
	
	private String userName;
	private String password;
	private String passwordConfirm;
	private String name;
	private String email;
	
	public RegisterBB(){
		
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

	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}
	
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String register(){
		
		u.register(this.userName, this.password, this.name, this.email);
		
		return "registerOK";
	}
}
