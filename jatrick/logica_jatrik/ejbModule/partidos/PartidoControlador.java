package partidos;

import java.util.List;

import interfaces.IPartidoControlador;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

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
	
	public List<Comentario> comentarioPartido(int idPartido, int nroComentario){
		return em.createQuery("SELECT c FROM Comentario c Where  ", Comentario.class).getResultList();
		
		
	}
	
}
