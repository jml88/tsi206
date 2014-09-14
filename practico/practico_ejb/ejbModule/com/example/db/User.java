package com.example.db;

import javax.persistence.*;

@NamedQuery(name="User.login", query="select u.Id, u.userName "
		+ "from User u "
		+ "where u.userName = :usr "
		+ "and u.password = :pass")

@Entity
public class User {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int Id;
	private String userName;
	private String password;
	private String name;
	private String email;
	
	public User(){
		
	}
	
	public String getUser_name() {
		return userName;
	}

	public void setUser_name(String user_name) {
		this.userName = user_name;
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
	
}
