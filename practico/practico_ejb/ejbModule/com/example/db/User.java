package com.example.db;

import java.io.Serializable;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@NamedQueries({
	@NamedQuery(
			name="User.login", 
			query="select u "
			+ "from User u "
			+ "where u.username = :usr "
			+ "and u.password = :pass"),
	@NamedQuery(
	        name = "User.list",
	        query = "SELECT u FROM User u")
})

@Entity
public class User implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	
	@NotNull
	@Column(unique = true)
	private String username;
	
	@NotNull
	private String password;
	
	private String name;
	private String email;
	

    @ElementCollection(targetClass = Role.class, fetch = FetchType.EAGER)
    @Enumerated(EnumType.STRING)
    @CollectionTable(name = "UserRoles", joinColumns = { @JoinColumn(name = "userId") })
    @Column(name = "role")
    private List<Role> roles;
	
	public User(){
		
	}
	
	public long getId() {
		return id;
	}

	public void setId(long id) {
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


	public List<Role> getRoles() {
		return roles;
	}


	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
	
}
