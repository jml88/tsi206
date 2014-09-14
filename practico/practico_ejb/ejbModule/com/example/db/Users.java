package com.example.db;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.example.db.User;

@Stateless
public class Users implements UsersInterface {

	@PersistenceContext( unitName = "practico_ejb" ) 
	private EntityManager em;
	
	public Users(){
		
	}
	
	public String register() {
		User usuario1 = new User();
		usuario1.setName("juan");
		usuario1.setEmail("juan@lema.com");
		usuario1.setUser_name("jlema");
		
		
		em.persist(usuario1);
		return "jlema";
	}
	
	public boolean login(String usr, String pass){
		User u = em.createNamedQuery("User.login", User.class).setParameter(usr, pass).getSingleResult();
		return u != null ? true : false;
	}
	
	public boolean register(String usr, String pass, String name, String email){
		User u = new User();
		u.setUser_name(usr);
		u.setPassword(pass);
		u.setName(name);
		u.setEmail(email);
		
		em.persist(u);
		
		return true;
	}

}
