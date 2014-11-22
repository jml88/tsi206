package comunicacion;

import interfaces.ICampeonatoControlador;
import interfaces.IConfiguracionControlador;
import interfaces.IEquipoControlador;
import interfaces.IIntegracionLogica;
import interfaces.IJugadorControlador;
import interfaces.IPartidoControlador;
import interfaces.IUserControlador;
import interfazIntegracion.InterfazIntegracionSimulacion;

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
	
	public InterfazIntegracionSimulacion getIntegracionSimulacion() throws NamingException {
		return (InterfazIntegracionSimulacion)this.ctx.lookup("java:app/logica_simulacion/Integracion!interfazIntegracion.InterfazIntegracionSimulacion");
	}
	
	public IIntegracionLogica getIntegracionLogica() throws NamingException {
		return (IIntegracionLogica)this.ctx.lookup("java:app/logica_jatrik/IntegracionLogica!interfaces.IIntegracionLogica");
	}

}
