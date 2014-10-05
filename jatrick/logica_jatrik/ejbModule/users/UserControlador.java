package users;

import interfaces.IUserControlador;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Local;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Stateless
public class UserControlador implements IUserControlador {
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	public UserControlador(){
		
	}

    public User find(Long id) {
        return em.find(User.class, id);
    }
    
	public User login(String usr, String pass){
		List<User> found = em.createNamedQuery("User.login", User.class)
		.setParameter("usr", usr)
		.setParameter("pass", pass)
		.getResultList();
		return found.isEmpty() ? null : found.get(0);
	}
	
	@Produces
    @Named("users")
    @RequestScoped
    public List<User> list() {
        return em.createNamedQuery("User.list", User.class).getResultList();
    }
	
	public Long createManager(Manager manager) {
        em.persist(manager);
        return manager.getId();
    }

    public void update(User user) {
        em.merge(user);
    }

    public void delete(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }
}
