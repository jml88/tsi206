package interfaces;

import java.util.List;
import java.util.Calendar;

import javax.ejb.Remote;
import datatypes.DatosComentario;
import excepciones.NoExistePartidoExepcion;
import partidos.Partido;


@Remote
public interface IPartidoControlador {
	
	public int crearPartidoAmistoso(int codEquipoLocal, int  codEquipoVisitante, Calendar fecha);
	
	public Partido findPartido(int codPartido);
	
	public List<DatosComentario> obtenerComentariosPartido(int codPartido, int nroComentario) throws NoExistePartidoExepcion;

}
