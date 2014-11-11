package web_jatrik;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.primefaces.model.map.DefaultMapModel;
import org.primefaces.model.map.MapModel;

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
	
	private Double lat;
	private Double lng;
	private MapModel modelo;
	
	private String nombreEstadio;
	
	public RegistroBB(){
		
	}
	
    @PostConstruct
    public void init() {
    	modelo = new DefaultMapModel();
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
	
	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}
	
	public String getNombreEstadio() {
		return nombreEstadio;
	}

	public void setNombreEstadio(String nombreEstadio) {
		this.nombreEstadio = nombreEstadio;
	}
	
	public MapModel getModelo() {
		return modelo;
	}

	public void setModelo(MapModel modelo) {
		this.modelo = modelo;
	}

	public String registro() {
		String result = "";
        try {
        	Set<String> r = new HashSet<String>();
        	r.add("MANAGER");
        	this.datosmanager.setRoles(r);
        	int codManager = Comunicacion.getInstance().getIUserControlador().createManager(this.datosmanager, this.password, this.nombreEquipo);
        	
        	//obtenego los datosManager actualizados luego de crearlo
        	this.datosmanager = Comunicacion.getInstance().getIUserControlador().obtenerManager(codManager);
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
