package datatypes;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.persistence.OneToMany;
import javax.persistence.OneToOne;


public class DatosAlineacion {
	
	private boolean defecto;
	
	private List<DatosJugador> delanteros;
	
	private List<DatosJugador> mediocampistas;
	
	private List<DatosJugador> defensas;
	
	private List<DatosJugador> goleros;

	/* suplentes por lesion */
	@OneToOne
	private DatosJugador lesionDelantero;
	
	@OneToOne
	private DatosJugador lesionMediocampistas;
	
	@OneToOne
	private DatosJugador lesionDefensas;
	
	@OneToOne
	private DatosJugador lesionGolero;

	/* suplentes en general */
	@OneToMany
	private List<DatosJugador> suplentes;
	
	public DatosAlineacion(){
		defecto = false;
		goleros = new ArrayList<DatosJugador>();
		delanteros = new ArrayList<DatosJugador>();
		mediocampistas = new ArrayList<DatosJugador>();
		defensas = new ArrayList<DatosJugador>();
		suplentes = new ArrayList<DatosJugador>();
	}

	public List<DatosJugador> getDelanteros() {
		return delanteros;
	}

	public void setDelanteros(List<DatosJugador> delanteros) {
		this.delanteros = delanteros;
	}

	public List<DatosJugador> getMediocampistas() {
		return mediocampistas;
	}

	public void setMediocampistas(List<DatosJugador> mediocampistas) {
		this.mediocampistas = mediocampistas;
	}

	public List<DatosJugador> getDefensas() {
		return defensas;
	}

	public void setDefensas(List<DatosJugador> defensas) {
		this.defensas = defensas;
	}

	public List<DatosJugador> getGoleros() {
		return goleros;
	}

	public void setGoleros(List<DatosJugador> goleros) {
		this.goleros = goleros;
	}

	public DatosJugador getLesionDelantero() {
		return lesionDelantero;
	}

	public void setLesionDelantero(DatosJugador lesionDelantero) {
		this.lesionDelantero = lesionDelantero;
	}

	public DatosJugador getLesionMediocampistas() {
		return lesionMediocampistas;
	}

	public void setLesionMediocampistas(DatosJugador lesionMediocampistas) {
		this.lesionMediocampistas = lesionMediocampistas;
	}

	public DatosJugador getLesionDefensas() {
		return lesionDefensas;
	}

	public void setLesionDefensas(DatosJugador lesionDefensas) {
		this.lesionDefensas = lesionDefensas;
	}

	public DatosJugador getLesionGolero() {
		return lesionGolero;
	}

	public void setLesionGolero(DatosJugador lesionGolero) {
		this.lesionGolero = lesionGolero;
	}

	public List<DatosJugador> getSuplentes() {
		return suplentes;
	}

	public void setSuplentes(List<DatosJugador> suplentes) {
		this.suplentes = suplentes;
	}

	public boolean isDefecto() {
		return defecto;
	}

	public void setDefecto(boolean defecto) {
		this.defecto = defecto;
	}
	
	public void addGolero(DatosJugador golero){
		goleros.add(golero);
	}
	
	

}
