package integracion_cliente;

import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Named;

import integracion_logica.ResultadoIntegracionDto;
import jugadores.Jugador;
import clienteWs.JRequestMatchDTO;
import clienteWs.MatchEventDTO;
import clienteWs.PlayerDTO;
import clienteWs.TeamDTO;
import equipos.Equipo;
import partidos.Comentario;
import partidos.ResultadoPartido;

@Stateless
@Named
public class Transformar {
	
	public Comentario matchEventToComentario(MatchEventDTO dto){
		Comentario c = new Comentario();
		c.setMensaje(dto.getMessage());
		c.setMinuto(dto.getMinute());
		
		return c;
	}
	
	public List<Comentario> getComentariosFromJResult(JRequestMatchDTO dto){
		LinkedList<Comentario> res = new LinkedList<Comentario>();
		for (MatchEventDTO eventoDto : dto.getMatchDTO().getEvents()) {
			Comentario com = new Comentario();
			com = matchEventToComentario(eventoDto);
			res.add(com);
		}
		
		return res;
	}
	
	public Jugador playerDtoToJugador(PlayerDTO dto){
		Jugador j = new Jugador();
		j.setNombre(dto.getName());
		j.setAtaque(dto.getAttack().intValue());
		j.setDefensa(dto.getDefense().intValue());
		j.setPorteria(dto.getGoalkeeping().intValue());
		j.setVelocidad(dto.getSpeed().intValue());
		j.setTecnica(dto.getTechnique().intValue());
		return j;
		
	}
	
	public PlayerDTO jugadorToPlayerDTO(Jugador j, int posicion, int titular){
		PlayerDTO player = new PlayerDTO();
		player.setAttack((double)j.getAtaque());
		player.setDefense((double)j.getDefensa());
		player.setGoalkeeping((double)j.getPorteria());
		player.setName(j.getNombre() + j.getApellido1() + j.getApellido2());
		player.setSpeed((double)j.getVelocidad());
		player.setTechnique((double) j.getTecnica());
		player.setPositionDTO(posicion);
		player.setSinceDTO(titular);
		return player;
	}
	
	public Equipo teamDtoToEquipo(TeamDTO dto){
		Equipo equipo = new Equipo();
		equipo.setCodigoIntegracion(dto.getId());
		equipo.setNombre(dto.getName());
		
		return equipo;
	}
	
	public TeamDTO equipoToTeamDTO(Equipo e){
		TeamDTO dto = new TeamDTO();
		dto.setName(e.getNombre());
		for (Jugador j : e.getAlineacionDefecto().getDefensas()) {
			PlayerDTO player = jugadorToPlayerDTO(j, 1, 0);
			dto.getPlayers().add(player);
		}
		for (Jugador j : e.getAlineacionDefecto().getMediocampistas()) {
			PlayerDTO player = jugadorToPlayerDTO(j, 2, 0);
			dto.getPlayers().add(player);
		}
		for (Jugador j : e.getAlineacionDefecto().getDelanteros()) {
			PlayerDTO player = jugadorToPlayerDTO(j, 3, 0);
			dto.getPlayers().add(player);
		}
		PlayerDTO player = jugadorToPlayerDTO(e.getAlineacionDefecto().getGolero(), 0, 0);
		dto.getPlayers().add(player);
		player = jugadorToPlayerDTO(e.getAlineacionDefecto().getLesionDefensas(), 1, 1);
		dto.getPlayers().add(player);
		player = jugadorToPlayerDTO(e.getAlineacionDefecto().getLesionMediocampistas(), 2, 1);
		dto.getPlayers().add(player);
		player = jugadorToPlayerDTO(e.getAlineacionDefecto().getLesionDelantero(), 3, 1);
		dto.getPlayers().add(player);
		return dto;
	}
	
	public JRequestMatchDTO resultadoToJRequest(ResultadoIntegracionDto resultado){
		return null;
	}
	
	public ResultadoPartido jRequestToResultado(JRequestMatchDTO dto){
		ResultadoPartido resultado = new ResultadoPartido();
		
		resultado.setGolesLocal(dto.getMatchDTO().getLocalGoals());
		resultado.setGolesVisitante(dto.getMatchDTO().getAwayGoals());
		return resultado;
		
	}

}
