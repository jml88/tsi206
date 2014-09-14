package com.example.db;

import javax.ejb.Local;

@Local
public interface UsersInterface {

	public String register();
	
	public boolean login(String usr, String pass);
}
