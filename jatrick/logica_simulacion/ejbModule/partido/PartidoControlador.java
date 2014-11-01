package partido;


import java.util.Calendar;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import campeonato.Posicion;
import campeonato.Torneo;
import partidos.Comentario;
import partidos.ConfiguracionPartido;
import partidos.Partido;
import datatypes.EnumPartido;


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
    
    
    
//    public void crearPartido(){
//    	Partido p = new Partido();
//    	em.persist(p);
//    }
    
    public void crearComentario(String mensaje, Partido partido, int minuto){
    	
    	Query q = em.createQuery("select max(c.nroComentario) from Comentario c");
    	List<Integer> max = (List<Integer>)q.getResultList();
    	int maximo = 1;
    	if (max.size() != 0 && max.get(0)!= null){
    		maximo = max.get(0) +1;
    	};
    	Comentario c = new Comentario();
    	c.setMensaje(mensaje);
    	c.setPartido(partido);
    	c.setMinuto(minuto);
    	c.setNroComentario(maximo);
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
		for (Posicion posicion : t.getPosiciones()) {
			//TODO obtener el datoo del premio desde la configuraciones
			posicion.getEquipo().setCapital(100000);
		}
		
	}
	
//	public Partido crearPartidoPrueba(){
//		Partido p = new Partido();
//		em.persist(p);
//		return p;
//	}

}

