package partido;


import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang.NotImplementedException;

import partidos.Partido;


@Stateless
@LocalBean
public class PartidoControlador {

	@PersistenceContext( unitName = "mongoUnit" ) 
	private EntityManager em;
	
	public PartidoControlador(){
		
	}
	
    public Partido find(Long id) {
        return em.find(Partido.class, id);
    }
    
    //Debe devolver todos los partidos que se juegan el mismo dia de c
    public List<Partido> listPartidosFecha(Calendar c){
    	
    	return em.createQuery("SELECT p FROM Partido p WHERE p.estado = POR_JUGAR and"
    			+ "p.fechaHora = fecha",Partido.class).setParameter(1,c.getTime()).getResultList();
    }
    
    
    
    public void crear(){
    	Partido p = new Partido();
    	em.persist(p);
    }
	
//	public User login(String usr, String pass){
//		List<User> found = em.createNamedQuery("User.login", User.class)
//		.setParameter("usr", usr)
//		.setParameter("pass", pass)
//		.getResultList();
//		return found.isEmpty() ? null : found.get(0);
//	}
//	
//	@Produces
//    @Named("users")
//    @RequestScoped
//    public List<User> list() {
//        return em.createNamedQuery("User.list", User.class).getResultList();
//    }
//	
//	public boolean register(String usr, String pass, String name, String email){
//		User u = new User();
//		u.setUsername(usr);
//		u.setPassword(pass);
//		u.setName(name);
//		u.setEmail(email);
//		
//		List<Role> roles = new ArrayList<Role>();
//		roles.add(Role.PLAYER);
//		
//		em.persist(u);
//		
//		return true;
//	}
//	
//	public Long create(User user) {
//        em.persist(user);
//        return user.getId();
//    }
//
//    public void update(User user) {
//        em.merge(user);
//    }
//
//    public void delete(User user) {
//        em.remove(em.contains(user) ? user : em.merge(user));
//    }

}

