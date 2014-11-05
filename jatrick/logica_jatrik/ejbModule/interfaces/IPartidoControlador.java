package interfaces;

import java.util.List;
import java.util.Calendar;
import java.util.Set;

import javax.ejb.Remote;

import datatypes.DatosAlineacion;
import datatypes.DatosComentario;
import excepciones.NoExistePartidoExepcion;
import partidos.Comentario;
import partidos.Partido;
import partidos.ResultadoPartido;


@Remote
public interface IPartidoControlador {
	
	public int crearPartidoAmistoso(int codEquipoLocal, int  codEquipoVisitante, Calendar fecha);
	
	public Partido findPartido(int codPartido);
	
	public List<Comentario> obtenerComentariosPartido(int codPartido, int nroComentario) throws NoExistePartidoExepcion;

	void setAlineacioPartido(DatosAlineacion alineacion, int idPartido, int idEquipo);
	
	public Set<Partido> obtenerPartidosUsuario(int codEquipo);

	int crearPartidoTorneo(int codEquipoLocal, int codEquipoVisitante,Calendar fecha);

	ResultadoPartido obtenerResultadoPartido(int idPartido);

}
