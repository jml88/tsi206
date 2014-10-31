package interfaces;

import configuracionGral.ConfiguracionGral;
import excepciones.NoExisteConfiguracionException;

public interface IConfiguracionControlador {
	
	public ConfiguracionGral getConfiguracion();
	
	public void crearConfiguracionGral(ConfiguracionGral dcg);
	
	public void modificarConfiguracion(ConfiguracionGral dcg) throws NoExisteConfiguracionException;
	
	public ConfiguracionGral getDatosConfiguracionGral();
	
	public void crearOModificarConfiguracion(ConfiguracionGral dcg) throws NoExisteConfiguracionException;
}
