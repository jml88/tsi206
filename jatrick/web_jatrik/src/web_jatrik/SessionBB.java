package web_jatrik;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import org.apache.shiro.SecurityUtils;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

import datatypes.DatosManager;

@Named("sessionBB")
@SessionScoped
public class SessionBB implements Serializable {

	private static final long serialVersionUID = 1L;
	private DatosManager datosManager;
	private StreamedContent escudoStream;
	private File escudo;

	public SessionBB() {
		this.datosManager = null;
		this.escudo = null;
		this.escudoStream = null;
	}

	public DatosManager getDatosManager() {
		return datosManager;
	}

	public void setDatosManager(DatosManager datosManager) {
		this.datosManager = datosManager;
	}

	public String logout() {
		this.datosManager = null;
		this.escudo = null;
		this.escudoStream = null;
		SecurityUtils.getSubject().logout();
		return "/webPages/login/login?faces-redirect=true";
	}

	public StreamedContent getEscudoStream() {
		try {
			if (this.escudo != null) {
				this.escudoStream = new DefaultStreamedContent(new FileInputStream(this.escudo));
			}
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		return escudoStream;
	}

	public void setEscudoStream(StreamedContent escudoStream) {
		this.escudoStream = escudoStream;
	}

	public File getEscudo() {
		return escudo;
	}

	public void setEscudo(File escudo) {
		this.escudo = escudo;
	}
}
