package facbircas;

import interfaces.IEquipoControlador;
import interfaces.IJugadorControlador;

import javax.ejb.Local;

@Local
public interface HomeFactory {
	
	public IEquipoControlador getEquipoControlador();
	
	public IJugadorControlador getJugadorControlador();

}
