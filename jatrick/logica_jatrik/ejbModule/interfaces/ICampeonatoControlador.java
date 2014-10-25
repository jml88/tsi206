package interfaces;

import javax.ejb.Local;

import datatypes.DatosTorneo;
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
	
	

}
