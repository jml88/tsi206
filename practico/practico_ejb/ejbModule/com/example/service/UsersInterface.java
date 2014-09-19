package com.example.service;

import javax.ejb.Local;

import com.example.db.User;

@Local
public interface UsersInterface {
	
	public User login(String usr, String pass);
	public boolean register(String usr, String pass, String name, String email);
	public Long create(User user);
}
