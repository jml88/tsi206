package datatypes;

import java.util.List;

public class DatosPartidoCopa {
	
	private List<DatosPartido> partidosFase;
	
	private int fase;

	public List<DatosPartido> getPartidosFase() {
		return partidosFase;
	}

	public void setPartidosFase(List<DatosPartido> partidosFase) {
		this.partidosFase = partidosFase;
	}

	public int getFase() {
		return fase;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}
	
	

}
