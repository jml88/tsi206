package jugadores;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = Jugador.nombreTabla)
public class Jugador {

	public static final String nombreTabla = "JUGADORES";

	@Id
	@Column(name = "CODJUGADOR")
	private int codigo;

	private int velocidad;
	private int tecnica;
	private int ataque;
	private int defensa;
	private int porteria;

}
