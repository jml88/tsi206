package com.tsi206.jatrik;

import android.app.Application;

public class MyApp extends Application {

	private String username;
	private String password;
	
	public MyApp() {
		// TODO Auto-generated constructor stub
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
}
