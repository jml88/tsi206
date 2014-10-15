package partidos;


import java.util.LinkedList;

import equipos.Equipo;
import fabricas.HomeFactory;

import java.util.Calendar;
import java.util.List;

import interfaces.IPartidoControlador;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import datatypes.DatosComentario;
import excepciones.NoExisteEquipoExcepcion;
import excepciones.NoExistePartidoExepcion;

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
	
	public List<DatosComentario> obtenerComentariosPartido(int codPartido, int nroComentario) throws NoExistePartidoExepcion{
		
		Partido p = em.find(Partido.class,codPartido );
		if(p == null){
			throw new NoExistePartidoExepcion("No existe partido de id " + codPartido);
		}
		@SuppressWarnings("unchecked")
		List<Comentario> comentarios = (List<Comentario>)em.createQuery("SELECT c FROM Comentario c Where c.nroComentario > :nroComentario"
				+ "and c.Partido = :Partido")
				.setParameter("nroComentario", nroComentario)
				.setParameter("Partido", p)
				.getResultList();
		List<DatosComentario> ret = new LinkedList<DatosComentario>();
		for(Comentario c : comentarios){
			ret.add(c.getDatos());
		}
		
		return ret;
		
	}
	
}
