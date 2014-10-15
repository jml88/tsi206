package partidos;

import equipos.Equipo;
import fabricas.HomeFactory;
import java.util.List;
import interfaces.IPartidoControlador;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
@LocalBean
public class PartidoControlador implements IPartidoControlador {
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@Inject
	private HomeFactory hf;
	
	@Override
	public int crearPartidoAmistoso(int codEquipoLocal, int  codEquipoVisitante, Calendar fecha) {
		Equipo el = hf.getEquipoControlador().findEquipo(codEquipoLocal);
		Equipo ev = hf.getEquipoControlador().findEquipo(codEquipoVisitante);
		Partido p = new Partido(el,ev,fecha);
		em.persist(p);
		return 0;
	}

	@Override
	public Partido findPartido(int codPartido) {
		return em.find(Partido.class, codPartido);
	}
	
}
