package integracion_cliente;

import java.util.Calendar;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.Stateless;
import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.xml.ws.BindingProvider;

import jugadores.Jugador;
import partidos.Comentario;
import partidos.Partido;
import partidos.PartidoIntegracion;
import partidos.ResultadoPartido;
import clienteWs.Integracion;
import clienteWs.IntegracionService;
import clienteWs.JRequestMatchDTO;
import clienteWs.MatchEventDTO;
import clienteWs.TeamDTO;
import datatypes.EnumPartido;
import equipos.Equipo;
import integracion_logica.ResultadoIntegracionDto;
import interfaces.IIntegracion_cliente;

@Stateless
@Named
public class Integracion_cliente implements IIntegracion_cliente{

	@Inject
	Transformar transformar;
	
	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	@Override
	public List<Equipo> listarEquiposIntegracion() {
		IntegracionService is = new IntegracionService();
		Integracion port = is.getIntegracionPort();
		
		BindingProvider bp = (BindingProvider)port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,"http://186.55.71.146:8080/JattrickCentral-ejb/integracion");
		
		List<TeamDTO> equiposCliente = port.getTeams();
		LinkedList<Equipo> equipos = new LinkedList<Equipo>();
		for (TeamDTO teamDTO : equiposCliente) {
			equipos.add(transformar.teamDtoToEquipo(teamDTO));
		}
		return equipos;
	}

	@Override
	public int jugarPartido(int idEquipoLocal, Equipo equipoIntegracion) {
		//crear el teamDTO 
		Equipo local = em.find(Equipo.class, idEquipoLocal);
		//eliminarRepetidosJugadoresEquipo(local);
		equipoIntegracion.setEquipoIntegracion(true);
		em.persist(equipoIntegracion);
		IntegracionService is = new IntegracionService();
		Integracion port = is.getIntegracionPort();
		TeamDTO localTeam = transformar.equipoToTeamDTO(local);
		BindingProvider bp = (BindingProvider)port;
		bp.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY,"http://186.55.71.146:8080/JattrickCentral-ejb/integracion");
		JRequestMatchDTO dto = port.play(localTeam, equipoIntegracion.getCodigoIntegracion());
		
		ResultadoPartido res = transformar.jRequestToResultado(dto);
		em.persist(res);
		
		List<Comentario> comentarios = transformar.getComentariosFromJResult(dto);
		
		
		Partido partido = new PartidoIntegracion();
		partido.setEstado(EnumPartido.FINALIZADO);
		partido.setLocal(equipoIntegracion);
		partido.setVisitante(local);
		partido.setResultado(res);
		partido.setFechaHora(Calendar.getInstance());
		partido.setTipoPartido("Integración");
		em.persist(partido);
		
		for (Comentario comentario : comentarios) {
			comentario.setPartido(partido);
			em.persist(comentario);
		}
		
		return partido.getCodigo();
		
		
	}
	
	/*private void eliminarRepetidos(List<Jugador> lista){
		HashSet<Jugador> hs = new HashSet<Jugador>();
		hs.addAll(lista);
		lista.clear();
		lista.addAll(hs);
	}
	
	private void eliminarRepetidosJugadoresEquipo(Equipo e){
		eliminarRepetidos(e.getAlineacionDefecto().getDefensas());
		eliminarRepetidos(e.getAlineacionDefecto().getDelanteros());
		eliminarRepetidos(e.getAlineacionDefecto().getMediocampistas());
		eliminarRepetidos(e.getAlineacionDefecto().getSuplentes());
		
		
	}*/

}
