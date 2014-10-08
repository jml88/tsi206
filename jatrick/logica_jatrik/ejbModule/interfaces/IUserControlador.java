package interfaces;

import java.util.List;

import javax.ejb.Local;

import datatypes.DatosManager;
import users.Manager;
import users.User;

@Local
public interface IUserControlador {

	public User find(int id);
	public DatosManager obtenerManager(int id);
	public User login(String usr, String pass);
	public List<User> list();
	public int findUserByUserName(String username);
	public int createManager(DatosManager datosManager, String password, String nombreEquipo);
	public void update(User user);
    public void delete(User user);
}
