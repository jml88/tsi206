package integracion_logica;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;

import equipos.Equipo;
import fabricas.HomeFactory;
import interfaces.IIntegracionLogica;

@Stateless
@Named
public class IntegracionLogica implements IIntegracionLogica{

	@Inject
	private HomeFactory hf;
	
	@Inject 
	private IntegracionConvert ic;
	
	@Override
	public List<EquipoIntegracion> listarEquipos() {
		List<EquipoIntegracion> ret = new LinkedList<EquipoIntegracion>();
		List<Equipo> equipos = hf.getEquipoControlador().listarEquiposNoBots();
		for (Equipo equipo : equipos) {
			ret.add(ic.equipoToEquipoIntegracion(equipo));
		}
		return ret;
	}

}
