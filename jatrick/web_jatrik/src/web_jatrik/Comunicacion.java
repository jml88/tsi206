package web_jatrik;

import javax.faces.context.FacesContext;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

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

}
