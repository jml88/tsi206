package interfaces;

import java.util.List;

import javax.ejb.Remote;

import datatypes.DatosComentario;
import excepciones.NoExistePartidoExepcion;
import partidos.Partido;

@Remote
public interface IPartidoControlador {
	
	public int crearPartido();
	
	public Partido findPartido(int codPartido);
	
	public List<DatosComentario> obtenerComentariosPartido(int codPartido, int nroComentario) throws NoExistePartidoExepcion;

}
