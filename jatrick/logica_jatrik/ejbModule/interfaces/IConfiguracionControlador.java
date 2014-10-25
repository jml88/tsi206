package interfaces;

import configuracionGral.ConfiguracionGral;
import datatypes.DatosConfiguracionGral;
import excepciones.NoExisteConfiguracionException;

public interface IConfiguracionControlador {
	
	public ConfiguracionGral getConfiguracion();
	
	public void crearConfiguracionGral(DatosConfiguracionGral dcg);
	
	public void modificarConfiguracion(DatosConfiguracionGral dcg) throws NoExisteConfiguracionException;
	
	public DatosConfiguracionGral getDatosConfiguracionGral();
	
	public void crearOModificarConfiguracion(DatosConfiguracionGral dcg) throws NoExisteConfiguracionException;
}
