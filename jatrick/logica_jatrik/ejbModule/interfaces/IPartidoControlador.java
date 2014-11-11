package interfaces;

import java.util.Calendar;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import partidos.Comentario;
import partidos.Partido;
import partidos.ResultadoPartido;
import equipos.Alineacion;
import excepciones.NoExistePartidoExepcion;


@Remote
public interface IPartidoControlador {
	
	public int crearPartidoAmistoso(int codEquipoLocal, int  codEquipoVisitante, Calendar fecha);
	
	public Partido findPartido(int codPartido);
	
	public List<Comentario> obtenerComentariosPartido(int codPartido, int nroComentario) throws NoExistePartidoExepcion;

	//void setAlineacioPartido(DatosAlineacion alineacion, int idPartido, int idEquipo);
	
	public Set<Partido> obtenerPartidosUsuario(int codEquipo);

	int crearPartidoTorneo(int codEquipoLocal, int codEquipoVisitante,Calendar fecha);

	ResultadoPartido obtenerResultadoPartido(int idPartido);

	Set<Partido> obtenerPartidosAmistososUsuario(int codEquipo);

	void setAlineacioPartido(Alineacion alineacion, int idPartido, int idEquipo);

}
