package partido;

import javax.ejb.Local;
import javax.ejb.Stateless;

@Stateless
@Local
public class LogicaSimulacion implements LogicaSimulacionI{
	
	public void simular(){
		System.out.println("SE EJECUTO!");
	}
}
