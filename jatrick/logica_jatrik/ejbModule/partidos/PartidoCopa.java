package partidos;

import java.util.Calendar;

import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

@Entity
@PrimaryKeyJoinColumn(name="partidoId")
public class PartidoCopa extends Partido{
	
	@OneToOne
	private PartidoCopa siguienteFase;
	
	private int fase;

	public PartidoCopa getSiguienteFase() {
		return siguienteFase;
	}

	public void setSiguienteFase(PartidoCopa siguienteFase) {
		this.siguienteFase = siguienteFase;
	}

	public int getFase() {
		return fase;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}

	public PartidoCopa(){
		super();
		this.siguienteFase = null;
	}
	
	public PartidoCopa(PartidoCopa siguienteFase, Calendar fechaHora){
		super();
		this.siguienteFase = siguienteFase;
		this.setFechaHora(fechaHora);
	}

}
