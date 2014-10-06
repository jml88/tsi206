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

import datatypes.DatosManager;

@Named
@RequestScoped
public class RegistroBB {

	private DatosManager datosmanager;
	private String password;
	private String passwordConfirm;
	
	@Inject
	SessionBB sessionBB;
	@Inject
	IUserControlador iuser;
	
	public RegistroBB(){
		
	}
	
    @PostConstruct
    public void init() {
    	datosmanager = new DatosManager();
    }
    
	public DatosManager getDatosmanager() {
		return datosmanager;
	}

	public void setDatosManager(DatosManager datosManager) {
		this.datosmanager = datosManager;
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

	public String registro() {
        try {
        	Set<String> r = new HashSet<String>();
        	r.add("MANAGER");
        	datosmanager.setRoles(r);
        	iuser.createManager(datosmanager, password);
        	sessionBB.setDatosManager(datosmanager);
            SecurityUtils.getSubject().login(new UsernamePasswordToken(datosmanager.getUsername(), password, false)); //en el false va remember
            //Messages.addGlobalInfo("Registration suceed, new user ID is: {0}", user.getId());
            
            
        }
        catch (RuntimeException e) {
            //Messages.addGlobalError("Registration failed: {0}", e.getMessage());
            e.printStackTrace(); // TODO: logger.
        }
        
        return "registerOK";
    }
	
}
