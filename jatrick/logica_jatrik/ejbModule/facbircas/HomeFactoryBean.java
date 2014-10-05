package facbircas;

import interfaces.IEquipoControlador;
import interfaces.IJugadorControlador;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.inject.Named;

@Stateless
@Named
public class HomeFactoryBean implements HomeFactory {

	@Resource
	public SessionContext ctx;
	
	@Override
	public IEquipoControlador getEquipoControlador() {
		return (IEquipoControlador) ctx.lookup("java:module/EquipoControlador!interfaces.IEquipoControlador");
	}

	@Override
	public IJugadorControlador getJugadorControlador() {
		return (IJugadorControlador) ctx.lookup("java:module/JugadorControlador!interfaces.IJugadorControlador");
	}
	

}
