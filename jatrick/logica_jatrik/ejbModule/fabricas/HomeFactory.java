package fabricas;

import interfaces.ICampeonatoControlador;
import interfaces.IConfiguracionControlador;
import interfaces.IEquipoControlador;
import interfaces.IJugadorControlador;
import interfaces.IMercadoDePases;
import interfaces.IPartidoControlador;
import interfaces.IUserControlador;

import javax.ejb.Local;

@Local
public interface HomeFactory {
	
	public IEquipoControlador getEquipoControlador();
	
	public IJugadorControlador getJugadorControlador();
	
	public IUserControlador getUserControlador();
	
	public IPartidoControlador getPartidoControlador();
	
	public IConfiguracionControlador getConfiguracionControlador();
	
	public ICampeonatoControlador getCampeontaoControlador();

	public IMercadoDePases getMercadoPasesControlador();
	

}
