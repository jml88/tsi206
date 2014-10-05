package web_jatrik;

import interfaces.IUserControlador;

import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;

import users.Manager;
import users.Role;
import users.User;

@Named
@RequestScoped
public class RegistroBB {

	private Manager manager;
	private String passwordConfirm;
	
	@Inject
	IUserControlador iuser;
	
	public RegistroBB(){
		
	}
	
    @PostConstruct
    public void init() {
        manager = new Manager();
    }
    
	public User getManager() {
		return manager;
	}

	public void setManager(Manager manager) {
		this.manager = manager;
	}
	
	public String getPasswordConfirm() {
		return passwordConfirm;
	}

	public void setPasswordConfirm(String passwordConfirm) {
		this.passwordConfirm = passwordConfirm;
	}

	public String registro() {
        try {
        	Set<Role> r = new HashSet<Role>();
        	r.add(Role.MANAGER);
        	manager.setRoles(r);
        	iuser.createManager(manager);
            SecurityUtils.getSubject().login(new UsernamePasswordToken(manager.getUsername(), manager.getPassword(), false)); //en el false va remember
            //Messages.addGlobalInfo("Registration suceed, new user ID is: {0}", user.getId());
            
            
        }
        catch (RuntimeException e) {
            //Messages.addGlobalError("Registration failed: {0}", e.getMessage());
            e.printStackTrace(); // TODO: logger.
        }
        
        return "registerOK";
    }
	
}
