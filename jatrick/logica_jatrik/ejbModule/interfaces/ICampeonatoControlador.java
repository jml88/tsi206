package interfaces;

import javax.ejb.Local;

import datatypes.DataTorneo;

@Local
public interface ICampeonatoControlador {
	
	
	/**
	 * 
	 * @param codigoTorneo codigo del torneo
	 * @return el torneo al cual se asciende, null en caso de que sea el ultimo torneo
	 */
	public DataTorneo obtenerTorneoAsciende(int codigoTorneo);
	
	/**
	 * 
	 * @param codigoTorneo codigo del torneo
	 * @return el torneo al cual se desciende, null en caso de que sea primera
	 */
	public DataTorneo obtenerTorneoDesciende(int codigoTorneo);
	
	
	
	

}
