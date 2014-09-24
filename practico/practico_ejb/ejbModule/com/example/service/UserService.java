package com.example.service;
import java.util.ArrayList;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Produces;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.db.Role;
import com.example.db.User;

@Stateless
@LocalBean
public class UserService {

	@PersistenceContext( unitName = "practico_ejb" ) 
	private EntityManager em;
	
	public UserService(){
		
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
	
	public boolean register(String usr, String pass, String name, String email){
		User u = new User();
		u.setUsername(usr);
		u.setPassword(pass);
		u.setName(name);
		u.setEmail(email);
		
		List<Role> roles = new ArrayList<Role>();
		roles.add(Role.PLAYER);
		
		em.persist(u);
		
		return true;
	}
	
	public Long create(User user) {
        em.persist(user);
        return user.getId();
    }

    public void update(User user) {
        em.merge(user);
    }

    public void delete(User user) {
        em.remove(em.contains(user) ? user : em.merge(user));
    }

}
