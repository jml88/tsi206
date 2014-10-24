package interfaces;

import java.util.List;
import java.util.Calendar;
import java.util.Set;

import javax.ejb.Remote;

import datatypes.DatosAlineacion;
import datatypes.DatosComentario;
import datatypes.DatosPartido;
import excepciones.NoExistePartidoExepcion;
import partidos.Partido;


@Remote
public interface IPartidoControlador {
	
	public int crearPartidoAmistoso(int codEquipoLocal, int  codEquipoVisitante, Calendar fecha);
	
	public Partido findPartido(int codPartido);
	
	public List<DatosComentario> obtenerComentariosPartido(int codPartido, int nroComentario) throws NoExistePartidoExepcion;

	void setAlineacioPartido(DatosAlineacion alineacion, int idPartido, int idEquipo);
	
	public Set<DatosPartido> obtenerPartidosUsuario(int codEquipo);

	int crearPartidoTorneo(int codEquipoLocal, int codEquipoVisitante,Calendar fecha);

}
