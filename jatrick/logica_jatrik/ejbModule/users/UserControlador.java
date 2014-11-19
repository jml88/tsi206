package users;

import equipos.Equipo;
import fabricas.HomeFactory;
import interfaces.IEquipoControlador;
import interfaces.IUserControlador;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
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
    
    public DatosManager obtenerManager(int id){
    	Manager m = em.find(Manager.class, id);
    	String username = m.getUsername();
    	String name = m.getName();
    	String email = m.getEmail(); 
    	int codEquipo = m.getEquipo() != null ? m.getEquipo().getCodigo() : -1;
    	int codTorneo = m.getTorneo() != null ? m.getTorneo().getCodigo() : -1;
    	
    	Set<String> roles = new HashSet<String>();
    	
    	for (Role r : m.getRoles()) {
			if (r == Role.MANAGER)
				roles.add("MANAGER");
			else if (r== Role.ADMIN)
				roles.add("ADMIN");
		}
    	DatosManager datosManager = new DatosManager(username, name, email, codEquipo,codTorneo, roles,m.getLat(), m.getLng());
    	
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
	
	public int createManager(DatosManager datosManager, String password, String nombreEquipo, boolean escudo) {
		Manager manager = new Manager(datosManager, password);
		
		em.persist(manager);
        
        IEquipoControlador iec = hf.getEquipoControlador();
        if (!datosManager.getName().equals("admin")){
//        	manager.setEquipo();
//        	manager.setTorneo(torneo);
        	iec.asignarTorneo(manager,new DatosEquipo(0, nombreEquipo, 0, false));
        }
        if (escudo) {
        	this.asociarEscudo(nombreEquipo);
        }
        return manager.getId();
    }

    public void update(User user) {
        em.merge(user);
    }

    public void delete(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }
    
    public void guardarEscudoEquipo(InputStream in) {
    	try {
            // write the inputStream to a FileOutputStream
    		OutputStream out = new FileOutputStream(new File("imagenesJatrik/nombreTemporal"));
            int read = 0;
            byte[] bytes = new byte[1024];
            while ((read = in.read(bytes)) != -1) {
                out.write(bytes, 0, read);
            }
            in.close();
            out.flush();
            out.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
    
    public void asociarEscudo(String nombreEquipo) {
    	File escudo = new File("imagenesJatrik/nombreTemporal");
    	File file = new File("imagenesJatrik/" + nombreEquipo);
    	escudo.renameTo(file);
    }
    
    public File obtenerEscudo(int codEquipo) {
    	Equipo e = em.find(Equipo.class, codEquipo);
    	File escudo = new File("imagenesJatrik/" + e.getNombre());
    	return escudo;
    }
}
