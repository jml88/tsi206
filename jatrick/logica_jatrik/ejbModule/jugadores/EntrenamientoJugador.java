package jugadores;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class EntrenamientoJugador implements Serializable {
	
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Column(name="TECNICAEENTR")
	private int tecnicaEntrenamiento;
	
	@Column(name="DEFENSAENTR")
	private int defensaEntrenamiento;
	
	@Column(name="ATAQUEENTR")
	private int ataqueEntrenamiento;
	
	@Column(name="PORTERIAENTR")
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
