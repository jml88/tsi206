package interfaces;

import integracion_logica.EquipoIntegracion;

import java.util.List;

import javax.ejb.Local;
import javax.ejb.Remote;

@Local
public interface IIntegracionLogica {

	public List<EquipoIntegracion> listarEquipos();
}
