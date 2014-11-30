package interfaces;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Remote;

import partidos.Amistoso;
import partidos.Comentario;
import partidos.Partido;
import partidos.ResultadoPartido;
import equipos.Alineacion;
import excepciones.NoExistePartidoExepcion;


@Remote
public interface IPartidoControlador {
	
	public int crearPartidoAmistoso(int codEquipoLocal, int  codEquipoVisitante, Calendar fecha, int cantidadRetp);
	
	public Partido findPartido(int codPartido);
	
	public Partido obtenerPartido(int codPartido);
	
	public List<Comentario> obtenerComentariosPartido(int codPartido, int nroComentario) throws NoExistePartidoExepcion;

	//void setAlineacioPartido(DatosAlineacion alineacion, int idPartido, int idEquipo);
	
	public Set<Partido> obtenerPartidosUsuario(int codEquipo);

	int crearPartidoTorneo(int codEquipoLocal, int codEquipoVisitante,Calendar fecha);

	ResultadoPartido obtenerResultadoPartido(int idPartido);

	Set<Partido> obtenerPartidosAmistososUsuario(int codEquipo);

	void setAlineacioPartido(Alineacion alineacion, int idPartido, int idEquipo);

	//Alineacion findAlineacion(int codAlineacion);

	Alineacion findAlineacionLocal(int codPartido);

	Alineacion findAlineacionVisitante(int codPartido);

	public void retarAmistoso(int codEquipo, Integer codEquipoRetado,
			Date fechaAmistoso, int cantidadAmisto);

	public List<Amistoso> obtenerAmistososAConcretar(int codEquipo);

	public void cancelarAmistoso(int codAmistoso);

	public void aceptarAmistoso(int codAmistoso);

}
