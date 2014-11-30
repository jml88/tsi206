package mensajes;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import equipos.Equipo;
import excepciones.NoExisteMensajeExepcion;
import users.Manager;
import interfaces.IMensajeControlador;

public class MensajeControlador implements IMensajeControlador{

	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	
//	@Override
//	public int crearMensaje(String titulo, String cuerpo, Date fechaCreacion,
//			int remitente, int receptor) {
//		// TODO Auto-generated method stub
//		Equipo erem = em.find(Equipo.class, remitente);
//		Equipo erec = em.find(Equipo.class, receptor);
//		Mensaje mensaje = new Mensaje(titulo, cuerpo, erem, erec, fechaCreacion, false);
//		em.persist(mensaje);
//		return mensaje.getCodigo();
//	}
//
//	@Override
//	public List<Mensaje> listarMensajes(int codEquipo) {
//		return em.createQuery("SELECT m FROM Mensaje m WHERE m.receptor.codigo = :equipo")
//		.setParameter("equipo",codEquipo)
//		.getResultList();
//	}
//
//	@Override
//	public void marcarComoLeido(int codigoMensaje) throws NoExisteMensajeExepcion {
//		Mensaje m = findMensaje(codigoMensaje);
//		if(m == null){
//			throw new NoExisteMensajeExepcion("No existe mensaje de id: "+ codigoMensaje);
//		}
//		
//		m.setLeido(true);
//		em.merge(m);
//		
//	}
//
//	@Override
//	public Mensaje findMensaje(int codigoMensaje) {
//		return (Mensaje)em.find(Mensaje.class, codigoMensaje);
//	}

}
