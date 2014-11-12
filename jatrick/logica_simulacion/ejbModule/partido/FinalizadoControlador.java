package partido;

import java.util.Calendar;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import partidos.Partido;

@Stateless
@LocalBean
public class FinalizadoControlador {

	@PersistenceContext(unitName = "jatrik")
	private EntityManager em;
	
	public List<Partido> listPartidosFecha(Calendar c) {
		Query q = em
				.createQuery("SELECT p FROM Partido p WHERE p.estado = 'POR_JUGAR' ");
		return (List<Partido>) q.getResultList();
	}
}
