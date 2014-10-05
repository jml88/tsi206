package datatypes;

import java.io.Serializable;
import java.util.Set;

import org.jboss.security.auth.spi.Users;


public class DatosManager implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String name;
	private String email;
	private int codEquipo;
	private Set<String> roles;
	
	public DatosManager(String username, String name, String email, int codEquipo, Set<String> roles) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.codEquipo = codEquipo;
		this.roles = roles;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getCodEquipo() {
		return codEquipo;
	}

	public void setCodEquipo(int codEquipo) {
		this.codEquipo = codEquipo;
	}

	public Set<String> getRoles() {
		return roles;
	}

	public void setRoles(Set<String> roles) {
		this.roles = roles;
	}
}
