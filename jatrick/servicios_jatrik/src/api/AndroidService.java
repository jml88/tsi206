package api;

import interfaces.IPartidoControlador;
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

import jugadores.Jugador;
import partidos.Partido;
import partidos.ResultadoPartido;
import users.Manager;
import users.User;

import comunicacion.Comunicacion;

import datatypes.DatosComentario;
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
	
	@Path("/getPartidosJugadosManager/{username}/{password}")
	@GET
	@Produces("application/json")
	public Response getPartidosJugadosManager(@PathParam("username") String username, @PathParam("password") String password){
		
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
	
	@Path("/getProximosPartidosManager/{username}/{password}")
	@GET
	@Produces("application/json")
	public Response getProximosPartidosManager(@PathParam("username") String username, @PathParam("password") String password){
		
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
		visita.setNombre("Equipo Visita 2 F.C.");
		
		partido.setCodigo(40);
		partido.setLocal(local);
		partido.setVisitante(visita);
		partido.setEstado(EnumPartido.FINALIZADO);
		partido.setFechaHora(Calendar.getInstance());
		
		Partido partido2 = new Partido();
		partido2.setCodigo(60);
		partido2.setLocal(local);
		partido2.setVisitante(visita);
		partido2.setEstado(EnumPartido.POR_JUGAR);
		partido2.setFechaHora(Calendar.getInstance());
		
		partidos.add(partido);
		partidos.add(partido2);
				
		return Response.ok(partidos, MediaType.APPLICATION_JSON).build();
	}
	
	@Path("/getComentariosPartido/{idPartido}/{nroComentario}/{username}/{password}")
	@GET
	@Produces("application/json")
	public Response getComentariosPartido(
			@PathParam("idPartido") String idPartido, 											
			@PathParam("idPartido") String nroComentario, 
			@PathParam("username") String username, 
			@PathParam("password") String password){
		
		boolean result = this.checkLogin(username, password);
		if (!result)
			return Response.status(Response.Status.FORBIDDEN).build();
		
		try {
			IPartidoControlador ipc = Comunicacion.getInstance().getIPartidoControlador();
			List<DatosComentario> comentarios = ipc.obtenerComentariosPartido(Integer.parseInt(idPartido), Integer.parseInt(nroComentario));
			return Response.ok(comentarios, MediaType.APPLICATION_JSON).build();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
		}
		
		//TODO: Sacar esto
//		List<Partido> partidos = new ArrayList<Partido>();
//		Partido partido = new Partido();
//		Equipo local = new Equipo();
//		local.setCodigo(1);
//		local.setBot(false);
//		local.setNombre("Equipo Local F.C.");
//		
//		Equipo visita = new Equipo();
//		visita.setCodigo(2);
//		visita.setBot(false);
//		visita.setNombre("Equipo Visita 2 F.C.");
//		
//		partido.setCodigo(40);
//		partido.setLocal(local);
//		partido.setVisitante(visita);
//		partido.setEstado(EnumPartido.FINALIZADO);
//		partido.setFechaHora(Calendar.getInstance());
//		
//		Partido partido2 = new Partido();
//		partido2.setCodigo(60);
//		partido2.setLocal(local);
//		partido2.setVisitante(visita);
//		partido2.setEstado(EnumPartido.POR_JUGAR);
//		partido2.setFechaHora(Calendar.getInstance());
//		
//		partidos.add(partido);
//		partidos.add(partido2);
//		
//		List<DatosComentario> comentarios = new ArrayList<DatosComentario>();
//		DatosComentario comentario1 = new DatosComentario();
//		comentario1.setId(0);
//		comentario1.setIdPartido(partido.getCodigo());
//		comentario1.setNroComentario(0);
//		comentario1.setMensaje("Gol del local");
//		comentario1.setMinuto(4);
//		DatosComentario comentario2 = new DatosComentario();
//		comentario2.setId(1);
//		comentario2.setIdPartido(partido.getCodigo());
//		comentario2.setNroComentario(1);
//		comentario2.setMensaje("Gol del visitante");
//		comentario2.setMinuto(7);
//		
//		comentarios.add(comentario1);
//		comentarios.add(comentario2);
//		//****************************************************************
//				
//		return Response.ok(comentarios, MediaType.APPLICATION_JSON).build();
	}
	
	@Path("/getResultadoPartido/{idPartido}/{username}/{password}")
	@GET
	@Produces("application/json")
	public Response getResultadoPartido(@PathParam("idPartido") String idPartido, @PathParam("username") String username, @PathParam("password") String password){
		
		boolean result = this.checkLogin(username, password);
		if (!result)
			return Response.status(Response.Status.FORBIDDEN).build();
		
		//TODO: esto es lo posta
//		try {
//			IPartidoControlador ipc = Comunicacion.getInstance().getIPartidoControlador();
//			ResultadoPartido resultado = ipc.obtenerResultadoPartido(Integer.parseInt(idPartido));
//			return Response.ok(resultado, MediaType.APPLICATION_JSON).build();
//			
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			return Response.status(Response.Status.INTERNAL_SERVER_ERROR).build();
//		}
		
		//TODO: sacar todo esto
		ResultadoPartido resultado = new ResultadoPartido();
		resultado.setGolesLocal(5);
		resultado.setGolesVisitante(4);
		
		List<Jugador> jugadoresLocal = new ArrayList<Jugador>();
		Jugador j1 = new Jugador();
		j1.setApellido1("ApellidoJ1");
		j1.setNombre("NombreJ1");
		
		Jugador j2 = new Jugador();
		j2.setApellido1("ApellidoJ2");
		j2.setNombre("NombreJ2");
		jugadoresLocal.add(j1);
		jugadoresLocal.add(j2);
		resultado.setGoleadoresLocal(jugadoresLocal);
		
		List<Jugador> jugadoresVisitante = new ArrayList<Jugador>();
		Jugador j3 = new Jugador();
		j3.setApellido1("ApellidoJ3");
		j3.setNombre("NombreJ3");
		
		Jugador j4 = new Jugador();
		j4.setApellido1("ApellidoJ3");
		j4.setNombre("NombreJ3");
		jugadoresVisitante.add(j3);
		jugadoresVisitante.add(j4);
		resultado.setGoleadoresVisitante(jugadoresVisitante);
	
		return Response.ok(resultado, MediaType.APPLICATION_JSON).build();
		//*******************************************************************
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
