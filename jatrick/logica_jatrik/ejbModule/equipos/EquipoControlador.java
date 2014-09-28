package equipos;

import interfaces.IEquipoControlador;

import java.util.Set;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import datatypes.DatosEquipo;

@Stateless
@LocalBean
public class EquipoControlador implements IEquipoControlador{
	
	@PersistenceContext( unitName = "jactrick" ) 
	private EntityManager em;

	@Override
	public int crearEquipo(DatosEquipo de) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Equipo findEquipo(int codigoEquipo) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Equipo> listarEquiposSistema() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Equipo> listarEquiposPais(int codigoPais) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Set<Equipo> listarEquiposTorneo(int codTorneo) {
		// TODO Auto-generated method stub
		return null;
	}

}
