package interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Local;

import excepciones.NoExisteMensajeExepcion;
import users.Manager;
import mensajes.Mensaje;

@Local
public interface IMensajeControlador {

	public int crearMensaje(String titulo, String mensaje, Date fechaCreacion, Manager remitente, Manager receptor);
	
	public List<Mensaje> listarMensajes(Manager u);
	
	public void marcarComoLeido(int codigoMensaje) throws NoExisteMensajeExepcion;
	
	public Mensaje findMensaje(int codigoMensaje);
}
