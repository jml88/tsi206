package mensajes;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import excepciones.NoExisteMensajeExepcion;
import users.Manager;
import interfaces.IMensajeControlador;

public class MensajeControlador implements IMensajeControlador{

	@PersistenceContext( unitName = "jatrik" ) 
	private EntityManager em;
	
	
	@Override
	public int crearMensaje(String titulo, String cuerpo, Date fechaCreacion,
			Manager remitente, Manager receptor) {
		// TODO Auto-generated method stub
		
		Mensaje mensaje = new Mensaje(titulo, cuerpo, remitente, receptor, fechaCreacion, false);
		em.persist(mensaje);
		return mensaje.getCodigo();
	}

	@Override
	public List<Mensaje> listarMensajes(Manager u) {
		return em.createQuery("SELECT m FROM Mensaje m WHERE m.usuario.codigo = :usuario")
		.setParameter("usuario",u.getId())
		.getResultList();
	}

	@Override
	public void marcarComoLeido(int codigoMensaje) throws NoExisteMensajeExepcion {
		Mensaje m = findMensaje(codigoMensaje);
		if(m == null){
			throw new NoExisteMensajeExepcion("No existe mensaje de id: "+ codigoMensaje);
		}
		
		m.setLeido(true);
		em.merge(m);
		
	}

	@Override
	public Mensaje findMensaje(int codigoMensaje) {
		return (Mensaje)em.find(Mensaje.class, codigoMensaje);
	}

}
