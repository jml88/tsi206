package equipos.entidades;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import jugadores.entidades.Jugador;

@Entity
@Table(name = Equipo.nombreTabla)
public class Equipo {

	public static final String nombreTabla = "EQUIPOS";

	@Id
	@Column(name = "CODEQUIPO")
	private int codigo;
	
	@OneToMany
	private Set<Jugador> plantel;
	
	@OneToOne
	private Alineacion alineacionDefecto;

	public Set<Jugador> getPlantel() {
		return plantel;
	}

	public void setPlantel(Set<Jugador> plantel) {
		this.plantel = plantel;
	}

	public Alineacion getAlineacionDefecto() {
		return alineacionDefecto;
	}

	public void setAlineacionDefecto(Alineacion alineacionDefecto) {
		this.alineacionDefecto = alineacionDefecto;
	}	

}
