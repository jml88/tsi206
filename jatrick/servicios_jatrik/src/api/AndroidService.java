package api;

import interfaces.IUserControlador;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import users.User;

import comunicacion.Comunicacion;

@Path("/api")
@Stateless
public class AndroidService {
	
	@Path("/login/{username}/{password}")
	@GET
	@Produces("text/plain")
	public String login(@PathParam("username") String username, @PathParam("password") String password){
		
		boolean result = this.checkLogin(username, password);
		
		if (result)
			return "OK";
		else
			return "No OK";
	}
	
	public boolean checkLogin(String username, String password){
		
		User u = null;
		
		try {
			IUserControlador iuc = Comunicacion.getInstance().getIUserControlador();
			u = iuc.login(username, password);
			return u != null;
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	}

}
