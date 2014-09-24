package com.example.service;

import javax.ejb.EJB;
import javax.jws.WebService;

@WebService(endpointInterface = "com.example.service.ServiciosSOAPI")
public class ServiciosSOAP implements ServiciosSOAPI{
	
	@EJB
	UserService u;
	
	
	public String login(String nick, String pass){
		return u.login(nick, pass)!= null ? "ok":"fail";
	}

}
