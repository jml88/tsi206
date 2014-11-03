package fabricas;

import interfaces.ICampeonatoControlador;
import interfaces.IConfiguracionControlador;
import interfaces.IEquipoControlador;
import interfaces.IJugadorControlador;
import interfaces.IMensajeControlador;
import interfaces.IMercadoDePasesControlador;
import interfaces.IPartidoControlador;
import interfaces.IUserControlador;

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
	
	@Override
	public IUserControlador getUserControlador(){
		return (IUserControlador ) ctx.lookup("java:module/UserControlador!interfaces.IUserControlador");
	}
	
	@Override
	public IMercadoDePasesControlador getMercadoPasesControlador(){
		return (IMercadoDePasesControlador) ctx.lookup("java:module/MercadoDePasesControlador!interfaces.IUserControlador");
	}

	@Override
	public IPartidoControlador getPartidoControlador() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public IConfiguracionControlador getConfiguracionControlador() {
		// TODO Auto-generated method stub
		return (IConfiguracionControlador ) ctx.lookup("java:module/ConfiguracionControlador!interfaces.IConfiguracionControlador");
	}

	@Override
	public ICampeonatoControlador getCampeontaoControlador() {
		// TODO Auto-generated method stub
		return (ICampeonatoControlador ) ctx.lookup("java:module/CampeonatoControlador!interfaces.ICampeonatoControlador");
	}

	@Override
	public IMensajeControlador getMensajeControlador() {
		return (IMensajeControlador ) ctx.lookup("java:module/MensajeControlador!interfaces.IMensajeControlador");
	}

}
