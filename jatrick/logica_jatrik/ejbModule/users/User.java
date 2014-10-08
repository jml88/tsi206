package users;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import equipos.Alineacion;


@NamedQueries({
	@NamedQuery(
			name="User.login", 
			query="select u "
			+ "from User u "
			+ "where u.username = :usr "
			+ "and u.password = :pass"),
	@NamedQuery(
	        name = "User.list",
	        query = "SELECT u FROM User u"),
	@NamedQuery(
			name="User.findByName",
			query="select u.id from User u where u.username = :username")
})

@Entity
@Inheritance(strategy=InheritanceType.JOINED)
@Table(name = User.nombreTabla)
public class User {

	public static final String nombreTabla = "USER";
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	protected int id;
	
	@NotNull
	@Column(unique = true)
	protected String username;
	
	@NotNull
	protected String password;
	
	@NotNull
	protected String name;
	
	@NotNull
	protected String email;
	
    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "USERROLES", joinColumns = { @JoinColumn(name = "userId") })
    @Column(name = "role")
    protected Set<Role> roles;
	
	public User(){
		
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setName(String name) {
		this.name = name;
		
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}
		
}
