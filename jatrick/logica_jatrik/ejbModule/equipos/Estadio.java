package equipos;

import javax.persistence.Column;
import javax.persistence.Id;

public class Estadio {
	
	@Id
	@Column(name = "CODESTADIO")
	private int codigo;

}
