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

	private DatosManager datosManager;
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
    	datosManager = new DatosManager();
    }
    
	public DatosManager getDatosmanager() {
		return datosManager;
	}

	public void setDatosManager(DatosManager datosManager) {
		this.datosManager = datosManager;
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
        	datosManager.setRoles(r);
        	iuser.createManager(datosManager, password);
        	sessionBB.setDatosManager(datosManager);
            SecurityUtils.getSubject().login(new UsernamePasswordToken(datosManager.getUsername(), password, false)); //en el false va remember
            //Messages.addGlobalInfo("Registration suceed, new user ID is: {0}", user.getId());
            
            
        }
        catch (RuntimeException e) {
            //Messages.addGlobalError("Registration failed: {0}", e.getMessage());
            e.printStackTrace(); // TODO: logger.
        }
        
        return "registerOK";
    }
	
}
