package interfaces;

import java.io.File;
import java.io.InputStream;
import java.util.List;

import javax.ejb.Local;

import users.User;
import datatypes.DatosManager;

@Local
public interface IUserControlador {

	public User find(int id);
	public DatosManager obtenerManager(int id);
	public User login(String usr, String pass);
	public List<User> list();
	public int findUserByUserName(String username);
	public int createManager(DatosManager datosManager, String password, String nombreEquipo, boolean escudo, Double altura);
	public void update(User user);
    public void delete(User user);
    public void guardarEscudoEquipo(InputStream in);
    public File obtenerEscudo(int codEquipo);
}
