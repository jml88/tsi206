package interfaces;

import java.util.List;

import javax.ejb.Local;

import datatypes.DatosManager;
import users.Manager;
import users.User;

@Local
public interface IUserControlador {

	public User find(int id);
	public DatosManager findManager(int id);
	public User login(String usr, String pass);
	public List<User> list();
	public int findUserByUserName(String username);
	public Long createManager(DatosManager datosManager, String password);
	public void update(User user);
    public void delete(User user);
}
