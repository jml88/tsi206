package interfaces;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;

import mensajes.Mensaje;
import campeonato.Copa;
import campeonato.Torneo;
import partidos.Partido;
import users.Manager;
import jugadores.Jugador;
import datatypes.DatosEquipo;
import datatypes.DatosJugador;
import datatypes.EnumEntrenamiento;
import equipos.Alineacion;
import equipos.Equipo;
import equipos.Estadio;
import excepciones.CapitalNegativo;
import excepciones.NoExisteEquipoExcepcion;
import excepciones.NoExisteMensajeExepcion;
import excepciones.NoSePuedeAgrandarEstadio;

@Local
public interface IEquipoControlador {
	
	public int crearEquipo(String nombreEquipo, boolean bot, int cantidad);
	
	public Equipo findEquipo(int codigoEquipo);
	
	public DatosEquipo obtenerEquipo(int codEquipo);
	
	public void modificarEquipo(DatosEquipo equipo);
	
	public Set<Equipo> listarEquiposSistema();
	
	public Set<Equipo> listarEquiposPais(int codigoPais);
	
	public Set<Equipo> listarEquiposTorneo(int codTorneo);
	
	public Set<DatosEquipo> obtenerEquiposSistema();
	
	public Set<DatosEquipo> obtenerEquiposPais(int codigoPais);
	
	public Set<DatosEquipo> obtenerEquiposTorneo(int codTorneo);
	
	public Set<Jugador> obtenerJugadoresEquipo(int codEquipo);

	public int crearAlineacion(List<Jugador> delanteros, List<Jugador> mediocampistas,
			List<Jugador> defensas, Jugador golero, Jugador lesionDelantero,
			Jugador lesionMediocampistas, Jugador lesionDefensas,
			Jugador lesionGolero, List<Jugador> suplentes, boolean defecto);

	public Alineacion findAlineacion(int codigoAlineacion);

	public Equipo asignarTorneo(Manager manager, DatosEquipo e);
	
	public void modificarTipoEntrenamientoEquipo(int codigoEquipo,EnumEntrenamiento tipoEntrenamiento) throws NoExisteEquipoExcepcion;

	public List<Partido> obtenerProximosPartidos(int codEquipo, int cantidad);
	
	public void elegirEntrenamiento(int idCodigoEquipo, EnumEntrenamiento enumEntrenamiento);
	
	public EnumEntrenamiento entrenamientoEquipo(int idCodigoEquipo);

	public List<Partido> obtenerAnterioresPartidos(int codEquipo, int cantidad);
	
	public Equipo getEquipo(int codigoEquipo);
	
	public Torneo obtenerTorneoActual(int codEquipo) throws NoExisteEquipoExcepcion;

	public List<Copa> obtenerCopasEquipo(int codEquipo);
	
	public List<Equipo> listarEquiposNoBots();

	public Mensaje findMensaje(int codigoMensaje);

	public void marcarComoLeido(int codigoMensaje) throws NoExisteMensajeExepcion;

	public List<Mensaje> listarMensajes(int codEquipo);

	public int crearMensaje(String titulo, String cuerpo, Date fechaCreacion,
			int remitente, int receptor);

	public void borrarMensaje(List<Mensaje> mensajes);
	
	public List<Equipo> obtenerRanking(int cantidad);

	public Jugador obtenerJuvenil(int codEquipo);
	
	public Estadio estadioDeEquipo(int codigoEquipo) throws NoExisteEquipoExcepcion;
	
	public void mejorarEstadioEquipo(int codigoEquipo) throws NoExisteEquipoExcepcion, CapitalNegativo, NoSePuedeAgrandarEstadio;
}
