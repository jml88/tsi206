package partidos;

import java.util.LinkedList;
import java.util.List;

import interfaces.IPartidoControlador;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
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

	@Override
	public int crearPartido() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Partido findPartido(int codPartido) {
		return em.find(Partido.class, codPartido);
	}
	
	public List<DatosComentario> comentarioPartido(int codPartido, int nroComentario) throws NoExistePartidoExepcion{
		
		Partido p = em.find(Partido.class,codPartido );
		if(p == null){
			throw new NoExistePartidoExepcion("No existe partido de id " + codPartido);
		}
		List<Comentario> comentarios = em.createQuery("SELECT c FROM Comentario c Where c.nroComentario > nroComentario"
				+ "and c.Partido = Partido", Comentario.class)
				.setParameter(1, nroComentario)
				.setParameter(2, p)
				.getResultList();
		List<DatosComentario> ret = new LinkedList<DatosComentario>();
		for(Comentario c : comentarios){
			ret.add(c.getDatos());
		}
		
		return ret;
		
	}
	
}
