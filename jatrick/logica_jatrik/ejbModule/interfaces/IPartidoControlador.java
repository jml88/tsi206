package interfaces;

import java.util.Calendar;

import javax.ejb.Remote;

import datatypes.DatosEquipo;
import partidos.Partido;

@Remote
public interface IPartidoControlador {
	
	public int crearPartidoAmistoso(int codEquipoLocal, int  codEquipoVisitante, Calendar fecha);
	
	public Partido findPartido(int codPartido);

}
