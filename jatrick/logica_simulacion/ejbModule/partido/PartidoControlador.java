package partido;


import java.util.Calendar;
import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import datatypes.EnumPartido;
import partidos.Comentario;
import partidos.ConfiguracionPartido;
import partidos.Partido;


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

}

