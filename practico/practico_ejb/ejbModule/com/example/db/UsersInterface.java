package com.example.db;

import javax.ejb.Local;

@Local
public interface UsersInterface {

	public String register();
	
	public boolean login(String usr, String pass);
	public boolean register(String usr, String pass, String name, String email);
}
