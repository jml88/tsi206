package comunicacion;

import interfaces.ICampeonatoControlador;
import interfaces.IConfiguracionControlador;
import interfaces.IEquipoControlador;
import interfaces.IFinanzasControlador;
import interfaces.IJugadorControlador;
import interfaces.IMercadoDePasesControlador;
import interfaces.IPartidoControlador;
import interfaces.IUserControlador;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

import web_jatrik.SessionBB;

public class Comunicacion {
	
	private static Comunicacion inst = null;
	private Context ctx;
	
	public Comunicacion() throws NamingException {
		super();
		this.ctx = new InitialContext();
	}
	
	public static Comunicacion getInstance() throws NamingException {
		if (Comunicacion.inst == null) {
			Comunicacion.inst = new Comunicacion();
		}
		return Comunicacion.inst;
	}
	
	public SessionBB getSesion() throws Exception {
		FacesContext ctx = FacesContext.getCurrentInstance();
		SessionBB sessionBB = (SessionBB)ctx.getApplication().getELResolver().getValue(ctx.getELContext(), null, "sessionBB");
		if (sessionBB == null) {
			throw new Exception("No esta logueado");
		}
		return sessionBB;
	}
	
	/**
	 * template por si es necesario agregar otra interfaz de la logica
	 * después cuando veamos como hacer lo de web service seguramente 
	 * la forma de hacer esto va a cambiar 
	 */
//	public Interfaz getInterfaz() throws NamingException {
//		return (Interfaz)this.ctx.lookup("java:app/logica_jatrik/ImplementacionDeInterfaz!interfaces.Interfaz");
//	}
	
	public IUserControlador getIUserControlador() throws NamingException {
		return (IUserControlador)this.ctx.lookup("java:app/logica_jatrik/UserControlador!interfaces.IUserControlador");
	}
	
	public IEquipoControlador getIEquipoControlador() throws NamingException {
		return (IEquipoControlador)this.ctx.lookup("java:app/logica_jatrik/EquipoControlador!interfaces.IEquipoControlador");
	}
	
	public IJugadorControlador getIJugadorControlador() throws NamingException {
		return (IJugadorControlador)this.ctx.lookup("java:app/logica_jatrik/JugadorControlador!interfaces.IJugadorControlador");
	}
	
	public IPartidoControlador getIPartidoControlador() throws NamingException {
		return (IPartidoControlador)this.ctx.lookup("java:app/logica_jatrik/PartidoControlador!interfaces.IPartidoControlador");
	}
	
	public IConfiguracionControlador getConfiguracionControlador() throws NamingException {
		return (IConfiguracionControlador)this.ctx.lookup("java:app/logica_jatrik/ConfiguracionControlador!interfaces.IConfiguracionControlador");
	}
	
	public ICampeonatoControlador getCampeonatoControlador() throws NamingException {
		return (ICampeonatoControlador)this.ctx.lookup("java:app/logica_jatrik/CampeonatoControlador!interfaces.ICampeonatoControlador");
	}
	
	public IMercadoDePasesControlador getIMercadoDePasesControlador() throws NamingException {
		return (IMercadoDePasesControlador)this.ctx.lookup("java:app/logica_jatrik/MercadoDePasesControlador!interfaces.IMercadoDePasesControlador");
	}
	
	public IFinanzasControlador getIFinanzasControlador() throws NamingException {
		return (IFinanzasControlador)this.ctx.lookup("java:app/logica_jatrik/FinanzasControlador!interfaces.IFinanzasControlador");
	}

}
