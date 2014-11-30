package fabricas;

import interfaces.ICampeonatoControlador;
import interfaces.IConfiguracionControlador;
import interfaces.IEquipoControlador;
import interfaces.IEstadioControlador;
import interfaces.IFinanzasControlador;
import interfaces.IIntegracion_cliente;
import interfaces.IJugadorControlador;
import interfaces.IMensajeControlador;
import interfaces.IMercadoDePasesControlador;
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

	public IMercadoDePasesControlador getMercadoPasesControlador();
	
	public IMensajeControlador getMensajeControlador();
	
	public IFinanzasControlador getFinanzasControlador();
	
	public IEstadioControlador getEstadioControlador();
	
	public IIntegracion_cliente getIIntegracionCliente();
	

}
