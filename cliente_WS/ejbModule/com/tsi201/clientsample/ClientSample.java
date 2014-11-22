package com.tsi201.clientsample;

import com.tsi201.*;

public class ClientSample {

	public static void main(String[] args) {
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        IntegracionService service1 = new IntegracionService();
	        System.out.println("Create Web Service...");
	        Integracion port1 = service1.getIntegracionPort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port1.play(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port1.ping());
	        System.out.println("Server said: " + port1.getTeams());
	        System.out.println("Create Web Service...");
	        Integracion port2 = service1.getIntegracionPort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port2.play(null,null));
	        //Please input the parameters instead of 'null' for the upper method!
	
	        System.out.println("Server said: " + port2.ping());
	        System.out.println("Server said: " + port2.getTeams());
	        System.out.println("***********************");
	        System.out.println("Call Over!");
	}
}
