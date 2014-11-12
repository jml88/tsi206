package interfaces;

import java.util.List;
import java.util.Map;

import javax.ejb.Local;

import jugadores.Jugador;
import campeonato.Posicion;
import campeonato.Torneo;
import datatypes.DatosFixture;
import datatypes.DatosTorneo;
import partidos.PartidoTorneo;
import excepciones.NoExisteConfiguracionException;

@Local
public interface ICampeonatoControlador {
	
	
	/**
	 * 
	 * @param codigoTorneo codigo del torneo
	 * @return el torneo al cual se asciende, null en caso de que sea el ultimo torneo
	 */
	public DatosTorneo obtenerTorneoAsciende(int codigoTorneo);
	
	/**
	 * 
	 * @param codigoTorneo codigo del torneo
	 * @return el torneo al cual se desciende, null en caso de que sea primera
	 */
	public DatosTorneo obtenerTorneoDesciende(int codigoTorneo);
	
	/**
	 * Crear los campeonatos y partidos
	 * @throws NoExisteConfiguracionException 
	 */
	public void crearCampeonato() throws NoExisteConfiguracionException;
	
	public List<Torneo> obtenerTorneos();

	public List<Posicion> obtenerPosiciones(int idTorneo);

	public void agregarTorneoNivelInferior();

	public void crearPartidosTorneo(Torneo t);

	public List<Torneo> obtenerTorneosActuales();

	public List<Jugador> obtenerGoleadoresTorneo(int codTorneo);

	public List<DatosFixture> obtenerFixtureTorneo(int codTorneo);
	

}
