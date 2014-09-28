package interfaces;

import javax.ejb.Remote;

import partidos.Partido;

@Remote
public interface IPartidoControlador {
	
	public int crearPartido();
	
	public Partido findPartido(int codPartido);

}
