package web_jatrik;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import org.primefaces.context.RequestContext;
import org.primefaces.event.DragDropEvent;

import comunicacion.Comunicacion;
import datatypes.DatosAlineacion;
import datatypes.DatosEquipo;
import datatypes.DatosJugador;

@Named("alineacionBB")
@ViewScoped
public class AlineacionBB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	SessionBB sesion;
	
	private int codEquipo;
	private int idPartido;
	private DatosEquipo equipo;
	private Set<DatosJugador> jugadores;
	private DatosAlineacion datosAlineacion;
	
	public AlineacionBB() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void init(){
		
		try {
			this.codEquipo = this.sesion.getDatosManager().getCodEquipo();
			this.jugadores = Comunicacion.getInstance().getIEquipoControlador().obtenerJugadoresEquipo(this.codEquipo);
			this.equipo = Comunicacion.getInstance().getIEquipoControlador().obtenerEquipo(this.codEquipo);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
		RequestContext context = RequestContext.getCurrentInstance();
		idPartido = (int)context.getAttributes().get("idPartido");
		datosAlineacion = new DatosAlineacion();
    }
	 
    public int getCodEquipo() {
		return codEquipo;
	}
    
    public void setCodEquipo(int codEquipo) {
		this.codEquipo = codEquipo;
	}

	public int getIdPartido() {
		return idPartido;
	}

	public void setIdPartido(int idPartido) {
		this.idPartido = idPartido;
	}

	public DatosEquipo getEquipo() {
		return equipo;
	}

	public void setEquipo(DatosEquipo equipo) {
		this.equipo = equipo;
	}

	public Set<DatosJugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(Set<DatosJugador> jugadores) {
		this.jugadores = jugadores;
	}

	public DatosAlineacion getDatosAlineacion() {
		return datosAlineacion;
	}

	public void setDatosAlineacion(DatosAlineacion datosAlineacion) {
		this.datosAlineacion = datosAlineacion;
	}
	
	public void onArqueroDroped(DragDropEvent ddEvent) {
        DatosJugador golero = ((DatosJugador) ddEvent.getData());
        List<DatosJugador> goleros = datosAlineacion.getGoleros();
        
        if(goleros.isEmpty()){
        	goleros.add(golero);
            jugadores.remove(golero);
        }
        else{
        	DatosJugador exGolero = goleros.get(0);
        	goleros.remove(exGolero);
        	jugadores.remove(golero);
        	jugadores.add(exGolero);
        	goleros.add(golero);
        }
        
    }
	
	public void onDefensaDroped(DragDropEvent ddEvent) {
		DatosJugador defensa = ((DatosJugador) ddEvent.getData());
		List<DatosJugador> defensas = datosAlineacion.getDefensas();
		
		if(defensas.size() < 5){
			defensas.add(defensa);
		    jugadores.remove(defensa);
		}
		else{
			DatosJugador exDefensa = defensas.get(0);
			defensas.remove(exDefensa);
			jugadores.remove(defensa);
			jugadores.add(exDefensa);
			defensas.add(defensa);
		}
    }
	
	public void onMedioDroped(DragDropEvent ddEvent) {
		DatosJugador medio = ((DatosJugador) ddEvent.getData());
		List<DatosJugador> medios = datosAlineacion.getMediocampistas();
		
		if(medios.size() < 5){
			medios.add(medio);
		    jugadores.remove(medio);
		}
		else{
			DatosJugador exMedio = medios.get(0);
			medios.remove(exMedio);
			jugadores.remove(medio);
			jugadores.add(exMedio);
			medios.add(medio);
		}
    }
	
	public void onDelanteroDroped(DragDropEvent ddEvent) {
		DatosJugador delantero = ((DatosJugador) ddEvent.getData());
		List<DatosJugador> delanteros = datosAlineacion.getDelanteros();
		
		if(delanteros.size() < 3){
			delanteros.add(delantero);
		    jugadores.remove(delantero);
		}
		else{
			DatosJugador exDelantero = delanteros.get(0);
			delanteros.remove(exDelantero);
			jugadores.remove(delantero);
			jugadores.add(exDelantero);
			delanteros.add(delantero);
		}
    }
	
	public String enviarAlineación(){
		
		try {
			Comunicacion.getInstance().getIPartidoControlador().setAlineaciónPartido(datosAlineacion, idPartido, codEquipo);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "/webPages/home/home.xhtml?faces-redirect=true";
	}

}
