package partido;

import javax.ejb.Local;
import javax.ejb.Stateless;

import partidos.Partido;

@Stateless
@Local
public class LogicaSimulacion{
	
	//
	private void simularPartido(Partido p){
		//TODO Hace el sorteo de los minutos, tiene que crear un timer para la simulacion 
	}
	
	//
	private void simularJugada(Partido p){
		//TODO Hace la logica de simular una jugada
	}
	
	public void simular(Partido p)
	{
		//TODO Tiene que decidir si simula el partido o hace el sorteo de los tiempos de las 
		//jugadas criticas
	}
	
}
