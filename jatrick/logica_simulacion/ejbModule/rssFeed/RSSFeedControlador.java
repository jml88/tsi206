package rssFeed;

import interfaces.IRSSFeedControlador;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class RSSFeedControlador implements IRSSFeedControlador {
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	public RSSFeedControlador() {
	}
}
