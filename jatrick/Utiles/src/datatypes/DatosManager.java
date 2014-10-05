package datatypes;

import java.io.Serializable;


public class DatosManager implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String username;
	private String name;
	private String email;
	private int codEquipo;
	
	public DatosManager(String username, String name, String email, int codEquipo) {
		super();
		this.username = username;
		this.name = name;
		this.email = email;
		this.codEquipo = codEquipo;
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
}
