package integracion_logica;

import java.util.LinkedList;
import java.util.List;

public class AlineacionIntegracion {
	
		
	
	private List<JugadorIntegracion> delanteros;
	
	
	private List<JugadorIntegracion> mediocampistas;
	
	private List<JugadorIntegracion> defensas;
	
	private JugadorIntegracion lesionDelantero;
	
	private JugadorIntegracion lesionMediocampistas;
	
	private JugadorIntegracion lesionDefensas;
	
	private JugadorIntegracion lesionGolero;

	private List<JugadorIntegracion> suplentes;

	public AlineacionIntegracion() {
		super();
		// TODO Auto-generated constructor stub
		this.defensas = new LinkedList<JugadorIntegracion>();
		this.delanteros = new LinkedList<JugadorIntegracion>();
		this.mediocampistas = new LinkedList<JugadorIntegracion>();
		this.suplentes = new LinkedList<JugadorIntegracion>();
	}

	public AlineacionIntegracion(List<JugadorIntegracion> delanteros,
			List<JugadorIntegracion> mediocampistas,
			List<JugadorIntegracion> defensas,
			JugadorIntegracion lesionDelantero,
			JugadorIntegracion lesionMediocampistas,
			JugadorIntegracion lesionDefensas, JugadorIntegracion lesionGolero,
			List<JugadorIntegracion> suplentes) {
		super();
		this.delanteros = delanteros;
		this.mediocampistas = mediocampistas;
		this.defensas = defensas;
		this.lesionDelantero = lesionDelantero;
		this.lesionMediocampistas = lesionMediocampistas;
		this.lesionDefensas = lesionDefensas;
		this.lesionGolero = lesionGolero;
		this.suplentes = suplentes;
	}

	public List<JugadorIntegracion> getDelanteros() {
		return delanteros;
	}

	public void setDelanteros(List<JugadorIntegracion> delanteros) {
		this.delanteros = delanteros;
	}

	public List<JugadorIntegracion> getMediocampistas() {
		return mediocampistas;
	}

	public void setMediocampistas(List<JugadorIntegracion> mediocampistas) {
		this.mediocampistas = mediocampistas;
	}

	public List<JugadorIntegracion> getDefensas() {
		return defensas;
	}

	public void setDefensas(List<JugadorIntegracion> defensas) {
		this.defensas = defensas;
	}

	public JugadorIntegracion getLesionDelantero() {
		return lesionDelantero;
	}

	public void setLesionDelantero(JugadorIntegracion lesionDelantero) {
		this.lesionDelantero = lesionDelantero;
	}

	public JugadorIntegracion getLesionMediocampistas() {
		return lesionMediocampistas;
	}

	public void setLesionMediocampistas(JugadorIntegracion lesionMediocampistas) {
		this.lesionMediocampistas = lesionMediocampistas;
	}

	public JugadorIntegracion getLesionDefensas() {
		return lesionDefensas;
	}

	public void setLesionDefensas(JugadorIntegracion lesionDefensas) {
		this.lesionDefensas = lesionDefensas;
	}

	public JugadorIntegracion getLesionGolero() {
		return lesionGolero;
	}

	public void setLesionGolero(JugadorIntegracion lesionGolero) {
		this.lesionGolero = lesionGolero;
	}

	public List<JugadorIntegracion> getSuplentes() {
		return suplentes;
	}

	public void setSuplentes(List<JugadorIntegracion> suplentes) {
		this.suplentes = suplentes;
	}
	
	

}
