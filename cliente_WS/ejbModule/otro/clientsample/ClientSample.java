package otro.clientsample;

import otro.*;

public class ClientSample {

	public static void main(String[] args) {
	        System.out.println("***********************");
	        System.out.println("Create Web Service Client...");
	        IntegracionServiciosService service1 = new IntegracionServiciosService();
	        System.out.println("Create Web Service...");
	        IntegracionServicios port1 = service1.getIntegracionServiciosPort();
	        System.out.println("Call Web Service Operation...");
	        System.out.println("Server said: " + port1.listarEquipos());
	                System.out.println("Call Over!");
	}
}
