package partido;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import comunicacion.Comunicacion;
import jugadores.Jugador;
import partidos.Comentario;
import partidos.ConfiguracionPartido;
import partidos.Partido;
import partidos.PartidoTorneo;
import partidos.ResultadoPartido;
import campeonato.Posicion;
import campeonato.Torneo;
import datatypes.DatosAlineacion;
import datatypes.DatosJugador;
import datatypes.EnumPartido;
import equipos.Alineacion;
import equipos.Equipo;


@Stateless
@LocalBean
public class PartidoControlador {

	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	
	public PartidoControlador(){
		
	}
	
    public Partido find(int id) {
        return em.find(Partido.class, id);
    }
    
    //Debe devolver todos los partidos que se juegan el mismo dia de c
    public List<Partido> listPartidosFecha(Calendar c){
    	Query q = em.createQuery("SELECT p FROM Partido p WHERE p.estado = 'POR_JUGAR' and "
    			+ "DATE(p.fechaHora) = :fecha");
    	q.setParameter("fecha",c.getTime());
    	return (List<Partido>)q.getResultList();
    }
    
    public void partidoEnJuego(Partido p){
    	p.setEstado(EnumPartido.JUGANDO);
    	em.merge(p);
    }
    
    public void partidoFinalizado(Partido p	){
    	PartidoTorneo pt = findPartidoTorneo(p.getCodigo());
    	if (pt != null){
    		pt.getTorneo().sumarPartidoJugado();
    	}
    	p.setEstado(EnumPartido.FINALIZADO);
    	em.merge(p);
    }
    
    
    
//    public void crearPartido(){
//    	Partido p = new Partido();
//    	em.persist(p);
//    }
    
    public void crearComentario(String mensaje, Partido partido, int minuto){
    	
    	Query q = em.createQuery("select max(c.Id) from Comentario c");
    	List<Integer> max = (List<Integer>)q.getResultList();
    	Comentario c = new Comentario();
    	c.setMensaje(mensaje);
    	c.setPartido(partido);
    	c.setMinuto(minuto);
    	em.persist(c);
    }
	
    public ConfiguracionPartido findConfiguracionPartido(){
    	 return (ConfiguracionPartido)em.createQuery("select c from ConfiguracionPartido c").getSingleResult();
    }
    
    public boolean tieneConfiguracionPartido(){
   	 return em.createQuery("select c from ConfiguracionPartido c").getResultList().size() != 0;
   }
    
    public void crearConfiguracionPartido(int cantidadJugadas, int duracion){
    	ConfiguracionPartido cp = new ConfiguracionPartido();
    	cp.setCantidadJugadas(cantidadJugadas);
    	cp.setDuracion(duracion);	
    	em.persist(cp);
    }

	public void partidoPorSimular(Partido p) {
		p.setEstado(EnumPartido.POR_SIMULAR);
    	em.merge(p);
		
	}
	
	public void actualizarDatosTorneo(Torneo t){
		Collections.sort(t.getPosiciones(), new Comparator() 
        {

            public int compare(Object o1, Object o2) 
            {
            	int retorno = 0;
            	retorno  = ((Posicion)o1).getPuntos() > ((Posicion)o1).getPuntos() ? 1 : -1;
            	if (((Posicion)o1).getPuntos() == ((Posicion)o1).getPuntos()){ retorno = 0;}
                return retorno;
            }
           }    );
		;
		try {
		List<Torneo> torneosDescenso = obtenerTorneosDescenso(t);
		int d = 0;
		for(int i = t.getCantEquipos() - t.getCantCuadrosDesc() ; i < t.getCantEquipos(); i++ ){
			Posicion p = t.getPosiciones().get(i);
			equipoDesciende(p.getEquipo(),t);
			p.resetearPosicion();
			torneosDescenso.get(d++).getEquipos().add(p.getEquipo());
		}
		
		equipoAsciende(t.getPosiciones().get(0).getEquipo(),t);
		
			Comunicacion.getInstance().getCampeonatoControlador().crearPartidosTorneo(t);
	
		for (Posicion posicion : t.getPosiciones()) {

			posicion.getEquipo().setCapital(100000);
		}
		resetearDatos(t);
		
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	private List<Torneo> obtenerTorneosDescenso(Torneo t) {
		// TODO Auto-generated method stub
		Query q = em.createQuery("SELECT t FROM Torneo t where t.asciende.codigo = :torneo");
		q.setParameter("torneo", t.getCodigo());
		return q.getResultList();
	}

	private void equipoAsciende(Equipo e, Torneo t) {
		t.getAsciende().getEquipos().add(e);
		
	}

	private void equipoDesciende(Equipo equipo, Torneo t) {
		em.createQuery("Select ");
		
	}

	private void resetearDatos(Torneo t) {
		
		t.setCantidadPartidosJugados(0);
		
		
	}
	
	public void setAlineacioPartido(DatosAlineacion datosAlineacion, int idPartido, int idEquipo){
		
		Partido partido = em.find(Partido.class, idPartido);
		
		List<Jugador> delanteros = new ArrayList<Jugador>();
		List<Jugador> defensas = new ArrayList<Jugador>();
		List<Jugador> mediocampistas = new ArrayList<Jugador>();
		List<Jugador> suplentes = new ArrayList<Jugador>();
		
		Jugador golero = null;
		Jugador lesionDelantero = null;
		Jugador lesionMediocampistas = null;
		Jugador lesionDefensas = null;
		Jugador lesionGolero = null;
		
		if(!datosAlineacion.getGoleros().isEmpty())
			golero = em.find(Jugador.class, datosAlineacion.getGoleros().get(0).getCodigo());
		
		if(datosAlineacion.getLesionDelantero() != null)
			lesionDelantero = em.find(Jugador.class,datosAlineacion.getLesionDelantero().getCodigo());
		
		if(datosAlineacion.getLesionMediocampistas() != null)
			lesionMediocampistas = em.find(Jugador.class,datosAlineacion.getLesionMediocampistas().getCodigo());
		
		if(datosAlineacion.getLesionDefensas() != null)
			lesionDefensas = em.find(Jugador.class,datosAlineacion.getLesionDefensas().getCodigo());
		
		if(datosAlineacion.getLesionGolero() != null)
			lesionGolero = em.find(Jugador.class,datosAlineacion.getLesionGolero().getCodigo());
		
		boolean defecto = datosAlineacion.isDefecto();
		
		for (DatosJugador datosJugador : datosAlineacion.getDefensas()) {
			defensas.add(em.find(Jugador.class,datosJugador.getCodigo()));
		}
		for (DatosJugador datosJugador : datosAlineacion.getMediocampistas()) {
			mediocampistas.add(em.find(Jugador.class,datosJugador.getCodigo()));
		}
		for (DatosJugador datosJugador : datosAlineacion.getDelanteros()) {
			delanteros.add(em.find(Jugador.class,datosJugador.getCodigo()));
		}
		for (DatosJugador datosJugador : datosAlineacion.getSuplentes()) {
			suplentes.add(em.find(Jugador.class,datosJugador.getCodigo()));
		}
		
		Alineacion alineacion = new Alineacion(delanteros, mediocampistas, defensas, golero, lesionDelantero, lesionMediocampistas, lesionDefensas, lesionGolero, suplentes, defecto);
		em.persist(alineacion);
		int codAlineacion = alineacion.getCodigo();
		alineacion = em.find(Alineacion.class,codAlineacion);
		
		if(defecto){
			Equipo e = em.find(Equipo.class,idEquipo);
			e.setAlineacionDefecto(alineacion);
			em.merge(e);
		}
		
		if (partido.getLocal().getCodigo() == idEquipo)
			partido.setAlineacionLocal(alineacion);
		else if(partido.getVisitante().getCodigo() == idEquipo)
			partido.setAlineacionVisitante(alineacion);
		
		em.merge(partido);
	}

	public DatosAlineacion crearAlineacion(Equipo equipo) {
		DatosAlineacion da = new DatosAlineacion();
		da.setDefecto(true);
		int num = 0;
		for (Jugador jugador : equipo.getPlantel()) {
			if (num == 0){
				da.getGoleros().add(jugador.getDatos());
			}
			else if (num == 1 || num == 2 || num == 3 || num == 4){
				da.getDefensas().add(jugador.getDatos());
			}
			else if (num == 5 || num == 6 || num == 7 || num == 8){
				da.getMediocampistas().add(jugador.getDatos());
			}
			else if (num == 9 || num == 10){
				da.getDelanteros().add(jugador.getDatos());
			}
			num++;
		}
		return da;
	}

	public PartidoTorneo findPartidoTorneo(int codigo) {
		return em.find(PartidoTorneo.class, codigo);
		
	}

	public void actualizarPosicionFechaTorneo(Posicion posLocal, Posicion posVisitante,
			PartidoTorneo pt) {
		posLocal.actualizarFecha(pt.getResultado().getGolesLocal(), pt
				.getResultado().getGolesVisitante());
		em.merge(posLocal);
		posVisitante.actualizarFecha(pt.getResultado().getGolesVisitante(),
				pt.getResultado().getGolesLocal());
		em.merge(posVisitante);
		
	}
	


	public void sumarGolLocal(ResultadoPartido rp, Jugador jL) {
		rp.agregarGolLocal();
		jL.setGolesCarrera(jL.getGolesCarrera()+1); 
		jL.setGolesLiga(jL.getGolesLiga()+1);
		if (!rp.getGoleadoresLocal().contains(jL)){
			rp.getGoleadoresLocal().add(jL);
		}
		em.merge(rp);
	}
	
	public void sumarGolVisitante(ResultadoPartido rp, Jugador jV) {
		rp.agregarGolVisitante();
		jV.setGolesCarrera(jV.getGolesCarrera()+1); 
		jV.setGolesLiga(jV.getGolesLiga()+1);
		if (!rp.getGoleadoresVisitante().contains(jV)){
			rp.getGoleadoresVisitante().add(jV);
		}
		em.merge(rp);
	}

	
//	public Partido crearPartidoPrueba(){
//		Partido p = new Partido();
//		em.persist(p);
//		return p;
//	}

}

