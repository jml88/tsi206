package practico_web;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.faces.bean.ManagedBean;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;

import com.example.db.User;
import com.example.db.Role;
import com.example.service.UserService;
import com.example.service.UsersInterface;

//@Stateless(mappedName = "registerBB")
//@ManagedBean
//@LocalBean

@Named
@RequestScoped
public class RegisterBB {

	@EJB
	private UserService service;
	
	private User user;
	
	private String userName;
	private String password;
	private String passwordConfirm;
	private String name;
	private String email;
	
	public RegisterBB(){
		
	}	

    @PostConstruct
    public void init() {
        user = new User();
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String register(){
		
		service.register(this.userName, this.password, this.name, this.email);
		
		return "registerOK";
	}
	
	public String submit() {
        try {
        	List<Role> r = new ArrayList<Role>();
        	r.add(Role.PLAYER);
        	user.setRoles(r);
            service.create(user);
            SecurityUtils.getSubject().login(new UsernamePasswordToken(user.getUsername(), user.getPassword(), false)); //en el false va remember
            //Messages.addGlobalInfo("Registration suceed, new user ID is: {0}", user.getId());
            
            
        }
        catch (RuntimeException e) {
            //Messages.addGlobalError("Registration failed: {0}", e.getMessage());
            e.printStackTrace(); // TODO: logger.
        }
        
        return "registerOK";
    }
	
}
