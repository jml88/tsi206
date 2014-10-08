package users;

import interfaces.IEquipoControlador;
import interfaces.IUserControlador;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import datatypes.DatosEquipo;
import datatypes.DatosManager;
import fabricas.HomeFactory;

@Stateless
public class UserControlador implements IUserControlador {
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@Inject
	private HomeFactory hf;
	
	public UserControlador(){
		
	}

    public User find(int id) {
        return em.find(User.class, id);
    }
    
    public DatosManager findManager(int id){
    	Manager m = em.find(Manager.class, id);
    	String username = m.getUsername();
    	String name = m.getName();
    	String email = m.getEmail(); 
    	int codEquipo = m.getEquipo() != null ? m.getEquipo().getCodigo() : -1;
    	Set<String> roles = new HashSet<String>();
    	
    	for (Role r : m.getRoles()) {
			if (r == Role.MANAGER)
				roles.add("MANAGER");
			else if (r== Role.ADMIN)
				roles.add("ADMIN");
		}
    	DatosManager datosManager = new DatosManager(username, name, email, codEquipo, roles);
    	
    	return datosManager;
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
	
	public int findUserByUserName(String username){
		return ((Number)em.createNamedQuery("User.findByName")
				.setParameter("username", username)
				.getSingleResult()).intValue();
	}
	
	public int createManager(DatosManager datosManager, String password, String nombreEquipo) {
		Manager manager = new Manager(datosManager, password);
        em.persist(manager);
        
        IEquipoControlador iec = hf.getEquipoControlador();
        iec.crearEquipo(nombreEquipo);
        
        return manager.getId();
    }

    public void update(User user) {
        em.merge(user);
    }

    public void delete(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }
}
