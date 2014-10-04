package prueba;

import javax.ejb.Local;
import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
//import org.hibernate.search.jpa.FullTextEntityManager;

@LocalBean
@Named
@Stateless
public class Test {
	
	@PersistenceContext( unitName = "logica_simulacion" ) 
	private EntityManager em;
	
//	@Produces 
//	public FullTextEntityManager getFTEM() { 
//		return Search.getFullTextEntityManager(em); 
//	}

	public void save() {
		// TODO Auto-generated method stub
		
		
		
		Quote q = new Quote();
		q.setId("1");
		q.setQuoteNumber("caca");
		
		em.persist(q);
		

	}

}
