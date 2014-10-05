package interfaces;

import java.util.List;

import javax.ejb.Local;

import users.Manager;
import users.User;

@Local
public interface IUserControlador {

	public User find(Long id);
	public User login(String usr, String pass);
	public List<User> list();
	public Long createManager(Manager manager);
	public void update(User user);
    public void delete(User user);
}
