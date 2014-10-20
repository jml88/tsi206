package web_jatrik;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;

import comunicacion.Comunicacion;
import datatypes.DatosManager;

@Named("registroBB")
@ViewScoped
public class RegistroBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private DatosManager datosmanager;
	private String password;
	private String passwordConfirm;
	private String nombreEquipo;
	
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

	public String getNombreEquipo() {
		return nombreEquipo;
	}

	public void setNombreEquipo(String nombreEquipo) {
		this.nombreEquipo = nombreEquipo;
	}

	public String registro() {
		String result = "";
        try {
        	Set<String> r = new HashSet<String>();
        	r.add("MANAGER");
        	datosmanager.setRoles(r);
        	int codManager = Comunicacion.getInstance().getIUserControlador().createManager(this.datosmanager, this.password, this.nombreEquipo);
        	
        	//obtenego los datosManager actualizados luego de crearlo
        	datosmanager = Comunicacion.getInstance().getIUserControlador().obtenerManager(codManager);
        	Comunicacion.getInstance().getSesion().setDatosManager(datosmanager);
            SecurityUtils.getSubject().login(new UsernamePasswordToken(datosmanager.getUsername(), password, false)); //en el false va remember
            //Messages.addGlobalInfo("Registration suceed, new user ID is: {0}", user.getId());
            result = "registerOK"; 
        } catch (RuntimeException e) {
            //Messages.addGlobalError("Registration failed: {0}", e.getMessage());
            e.printStackTrace(); // TODO: logger.
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return result;
    }
	
}
