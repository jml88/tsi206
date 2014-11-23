package web_jatrik;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Named;
import javax.naming.NamingException;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.primefaces.event.FileUploadEvent;
import org.primefaces.model.UploadedFile;
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
	private Double altura;
	private UploadedFile escudo;
	private boolean escudoCargado;
	
	private String nombreEstadio;
	
	public RegistroBB(){
		
	}
	
    @PostConstruct
    public void init() {
    	datosmanager = new DatosManager();
    	this.escudo = null;
    	this.escudoCargado = false;
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
	
	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}

	public String getNombreEstadio() {
		return nombreEstadio;
	}

	public void setNombreEstadio(String nombreEstadio) {
		this.nombreEstadio = nombreEstadio;
	}
	
	public UploadedFile getEscudo() {
		return escudo;
	}

	public void setEscudo(UploadedFile escudo) {
		this.escudo = escudo;
	}
	
	public boolean isEscudoCargado() {
		return escudoCargado;
	}

	public void setEscudoCargado(boolean escudoCargado) {
		this.escudoCargado = escudoCargado;
	}

	public void subirEscudo(FileUploadEvent event) {
		try {
			Comunicacion.getInstance().getIUserControlador().guardarEscudoEquipo(event.getFile().getInputstream());
			this.escudoCargado = true;
		} catch (NamingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public String registro() {
		String result = "";
        try {
        	Set<String> r = new HashSet<String>();
        	r.add("MANAGER");
        	this.datosmanager.setRoles(r);
        	this.datosmanager.setLat(this.lat);
        	this.datosmanager.setLng(this.lng);
        	int codManager = Comunicacion.getInstance().getIUserControlador().createManager(this.datosmanager, this.password, this.nombreEquipo, this.escudoCargado, this.altura);
        	this.datosmanager = Comunicacion.getInstance().getIUserControlador().obtenerManager(codManager);
        	Comunicacion.getInstance().getSesion().setDatosManager(datosmanager);
            SecurityUtils.getSubject().login(new UsernamePasswordToken(datosmanager.getUsername(), password, false));
    		File escudo = Comunicacion.getInstance().getIUserControlador().obtenerEscudo(this.datosmanager.getCodEquipo());
        	Comunicacion.getInstance().getSesion().setEscudo(escudo);
        	if (!datosmanager.getName().equals("admin")) {
                result = "/webPages/home/home.xhtml?faces-redirect=true";
        	} else {
        		result = "/webPages/admin/admin.xhtml?faces-redirect=true";
        	}
        	 
        } catch (RuntimeException e) {
            //Messages.addGlobalError("Registration failed: {0}", e.getMessage());
            e.printStackTrace(); // TODO: logger.
        } catch (Exception e) {
        	e.printStackTrace();
        }
        return result;
    }
	
}
