package api;

import interfaces.IUserControlador;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import users.User;
import comunicacion.Comunicacion;

@Path("/api")
@Stateless
public class AndroidService {
	
	@Path("/login/{username}/{password}")
	@GET
	@Produces("application/json")
	public Response login(@PathParam("username") String username, @PathParam("password") String password){
		
		boolean result = this.checkLogin(username, password);
		String json = "OK";
		if (result)
			return Response.ok(json, MediaType.APPLICATION_JSON).build();
		else
			return Response.status(Response.Status.FORBIDDEN).build();
	}
	
	@Path("/getEquipoManager/{username}/{password}")
	@GET
	@Produces("application/json")
	public Response getEquipoManager(@PathParam("username") String username, @PathParam("password") String password){
		
		return Response.status(Response.Status.NOT_IMPLEMENTED).build();
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
