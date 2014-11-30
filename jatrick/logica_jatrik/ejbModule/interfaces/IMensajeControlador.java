package interfaces;

import java.util.Date;
import java.util.List;

import javax.ejb.Remote;

import mensajes.Mensaje;
import excepciones.NoExisteMensajeExepcion;

@Remote
public interface IMensajeControlador {
	
//	public List<Mensaje> listarMensajes(int codEquipo);
//	
//	public void marcarComoLeido(int codigoMensaje) throws NoExisteMensajeExepcion;
//	
//	public Mensaje findMensaje(int codigoMensaje);
//
//	int crearMensaje(String titulo, String cuerpo, Date fechaCreacion,
//			int remitente, int receptor);
}
