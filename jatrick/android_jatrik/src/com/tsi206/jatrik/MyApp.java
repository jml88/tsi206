package com.tsi206.jatrik;

import android.app.Application;

public class MyApp extends Application {

	private String username;
	private String password;
	private String apiServer;
	
	public MyApp() {
		// TODO Auto-generated constructor stub
		apiServer = "http://192.168.56.1:8080/servicios_jatrik/rest/api/";
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

	public String getApiServer() {
		return apiServer;
	}

	public void setApiServer(String apiServer) {
		this.apiServer = apiServer;
	}
}
