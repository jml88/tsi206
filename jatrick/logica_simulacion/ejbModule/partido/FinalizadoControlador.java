package partido;

import java.util.Calendar;
import java.util.LinkedList;
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
				.createQuery("SELECT p FROM Partido p WHERE p.estado = 'POR_JUGAR' and p.suscriptoMinutoAmintuto = true");
		return (List<Partido>) q.getResultList();
	}

	public List<Partido> obtenerPartidosNoSuscriptosParaSimular(
			Calendar fechaActual) {
		Query q = em
				.createQuery("select p from Partido p WHERE (p.estado = 'POR_JUGAR') and (p.suscriptoMinutoAmintuto = false)");
		
		List<Partido> partidosCercanos = new LinkedList<Partido>();
		List<Partido> partidos = q.getResultList();

		for (Partido partido : partidos) {
			long tiempo = (fechaActual.getTimeInMillis()/(long)60000) -(partido.getFechaHora().getTimeInMillis()/(long)60000);
			if ( tiempo < 2 && tiempo >= 0){
				partidosCercanos.add(partido);
			}
		}

		return partidosCercanos;

	}
}
