package api;

import interfaces.IUserControlador;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import partidos.Partido;
import users.Manager;
import users.User;
import comunicacion.Comunicacion;
import datatypes.EnumPartido;
import equipos.Equipo;
import equipos.Estadio;

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
		
		boolean result = this.checkLogin(username, password);
		if (!result)
			return Response.status(Response.Status.FORBIDDEN).build();
		
		try {
			IUserControlador iuc = Comunicacion.getInstance().getIUserControlador();
			int codManager = iuc.findUserByUserName(username);
			Manager manager = (Manager) iuc.find(codManager);
			
			//TODO User esta funcion y borrar lo que sigue
			//Equipo equipo = manager.getEquipo();
			
			//esto se va
			Equipo equipo = new Equipo();
			equipo.setCodigo(-1);
			equipo.setBot(false);
			equipo.setNombre("Equipo Dummy F.C.");
			
			Estadio estadio = new Estadio();
			estadio.setCodigo(-1);
			estadio.setCapacidad(40000);
			estadio.setNombre("Equipo Dummy Arena");
			
			equipo.setEstadio(estadio);
			//hasta acá
			
			return Response.ok(equipo, MediaType.APPLICATION_JSON).build();
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();		
		}	
	}
	
	@Path("/getPartidosManager/{username}/{password}")
	@GET
	@Produces("application/json")
	public Response getPartidosManager(@PathParam("username") String username, @PathParam("password") String password){
		
		boolean result = this.checkLogin(username, password);
		if (!result)
			return Response.status(Response.Status.FORBIDDEN).build();
		
		List<Partido> partidos = new ArrayList<Partido>();
		Partido partido = new Partido();
		Equipo local = new Equipo();
		local.setCodigo(1);
		local.setBot(false);
		local.setNombre("Equipo Local F.C.");
		
		Equipo visita = new Equipo();
		visita.setCodigo(2);
		visita.setBot(false);
		visita.setNombre("Equipo Visita 1 F.C.");
		
		partido.setCodigo(30);
		partido.setLocal(local);
		partido.setVisitante(visita);
		partido.setEstado(EnumPartido.FINALIZADO);
		partido.setFechaHora(Calendar.getInstance());
		
		Partido partido2 = new Partido();
		partido2.setCodigo(50);
		partido2.setLocal(local);
		partido2.setVisitante(visita);
		partido2.setEstado(EnumPartido.POR_JUGAR);
		partido2.setFechaHora(Calendar.getInstance());
		
		partidos.add(partido);
		partidos.add(partido2);
				
		return Response.ok(partidos, MediaType.APPLICATION_JSON).build();
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
