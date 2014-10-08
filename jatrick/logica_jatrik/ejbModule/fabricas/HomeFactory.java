package fabricas;

import interfaces.IEquipoControlador;
import interfaces.IJugadorControlador;
import interfaces.IUserControlador;

import javax.ejb.Local;

@Local
public interface HomeFactory {
	
	public IEquipoControlador getEquipoControlador();
	
	public IJugadorControlador getJugadorControlador();
	
	public IUserControlador getUserControlador();

}