package jugadores;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = EntrenamientoJugador.nombreTabla)
public class EntrenamientoJugador {
	
	public static final String nombreTabla = "EntrenamientoJugador";
	
	@Id
	@Column(name = "CODENTRJUG")
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int codigo;
	
	@Column
	private int tecnicaEntrenamiento;
	
	@Column
	private int defensaEntrenamiento;
	
	@Column
	private int ataqueEntrenamiento;
	
	@Column
	private int porteroEntrenamiento;

	public int getTecnicaEntrenamiento() {
		return tecnicaEntrenamiento;
	}

	public void setTecnicaEntrenamiento(int tecnicaEntrenamiento) {
		this.tecnicaEntrenamiento = tecnicaEntrenamiento;
	}

	public int getDefensaEntrenamiento() {
		return defensaEntrenamiento;
	}

	public void setDefensaEntrenamiento(int defensaEntrenamiento) {
		this.defensaEntrenamiento = defensaEntrenamiento;
	}

	public int getAtaqueEntrenamiento() {
		return ataqueEntrenamiento;
	}

	public void setAtaqueEntrenamiento(int ataqueEntrenamiento) {
		this.ataqueEntrenamiento = ataqueEntrenamiento;
	}

	public int getPorteroEntrenamiento() {
		return porteroEntrenamiento;
	}

	public void setPorteroEntrenamiento(int porteroEntrenamiento) {
		this.porteroEntrenamiento = porteroEntrenamiento;
	}

	public EntrenamientoJugador(int tecnicaEntrenamiento,
			int defensaEntrenamiento, int ataqueEntrenamiento,
			int porteroEntrenamiento) {
		super();
		this.tecnicaEntrenamiento = tecnicaEntrenamiento;
		this.defensaEntrenamiento = defensaEntrenamiento;
		this.ataqueEntrenamiento = ataqueEntrenamiento;
		this.porteroEntrenamiento = porteroEntrenamiento;
	}

	public EntrenamientoJugador() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	

}
