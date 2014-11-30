package interfaces;

import java.rmi.RemoteException;
import java.util.List;

import javax.ejb.Local;
import javax.xml.rpc.ServiceException;

import equipos.Equipo;

@Local
public interface IIntegracion_cliente {
	
	public List<Equipo> listarEquiposIntegracion() throws ServiceException, RemoteException;
	
	public int jugarPartido(int idEquipoLocal, Equipo equipoIntegracion) throws ServiceException, RemoteException;

}
