package web_jatrik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.ejb.Remove;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import jugadores.Jugador;

import org.primefaces.event.DragDropEvent;

import partidos.Partido;
import comunicacion.Comunicacion;
import datatypes.DatosEquipo;
import equipos.Alineacion;

@Named("alineacionBB")
@ViewScoped
public class AlineacionBB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Inject
	SessionBB sesion;
	
	private int codEquipo;
	private int idPartido;
	private DatosEquipo equipo;
	private Set<Jugador> jugadores;
	private Alineacion datosAlineacion;
	private List<Jugador> goleros;
	private Alineacion alineacion;
	private String retorno;
	
	public AlineacionBB() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void init(){
		
		try {
			this.codEquipo = this.sesion.getDatosManager().getCodEquipo();
			this.jugadores = Comunicacion.getInstance().getIEquipoControlador().obtenerJugadoresEquipo(this.codEquipo);
			this.equipo = Comunicacion.getInstance().getIEquipoControlador().obtenerEquipo(this.codEquipo);
			
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			this.idPartido = (int)context.getApplicationMap().get("idPartido");
			this.retorno = (String)context.getApplicationMap().get("retorno");
			Partido partido = Comunicacion.getInstance().getIPartidoControlador().findPartido(idPartido);
			
			this.goleros = new ArrayList<Jugador>();
			
			if (codEquipo == partido.getLocal().getCodigo()){
				alineacion = Comunicacion.getInstance().getIPartidoControlador().findAlineacionLocal(idPartido);
			}else{
				alineacion = Comunicacion.getInstance().getIPartidoControlador().findAlineacionVisitante(idPartido);
			}
			
			//alineacion = this.codEquipo == partido.getLocal().getCodigo() ? partido.getAlineacionLocal() : partido.getAlineacionVisitante();
			
			if (alineacion != null){
				goleros.add(alineacion.getGolero());
				eliminarRepetidos(alineacion.getDefensas());
				eliminarRepetidos(alineacion.getDelanteros());
				eliminarRepetidos(alineacion.getMediocampistas());
				eliminarRepetidos(alineacion.getSuplentes());
				filtrarJugadores(jugadores, goleros);
				filtrarJugadores(jugadores, alineacion.getDefensas());
				filtrarJugadores(jugadores, alineacion.getDelanteros());
				filtrarJugadores(jugadores, alineacion.getMediocampistas());
				filtrarJugadores(jugadores, alineacion.getSuplentes());
				datosAlineacion = alineacion;
				
//				datosAlineacion.setGolero(alineacion.getGolero());
//				datosAlineacion.setDefensas(alineacion.getDefensas());
//				datosAlineacion.setDelanteros(alineacion.getDelanteros());
//				datosAlineacion.setMediocampistas(alineacion.getMediocampistas());
//				datosAlineacion.setLesionGolero(alineacion.getLesionGolero());
//				datosAlineacion.setLesionDefensas(alineacion.getLesionDefensas());
//				datosAlineacion.setLesionMediocampistas(alineacion.getLesionMediocampistas());
//				datosAlineacion.setLesionDelantero(alineacion.getLesionDelantero());
			}
			else{
				this.datosAlineacion = new Alineacion();
			}
			
			
		} catch (Exception ex) {
			ex.printStackTrace();
		}
			
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

	public Set<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(Set<Jugador> jugadores) {
		this.jugadores = jugadores;
	}

	public Alineacion getDatosAlineacion() {
		return datosAlineacion;
	}

	public void setDatosAlineacion(Alineacion datosAlineacion) {
		this.datosAlineacion = datosAlineacion;
	}
	
	public List<Jugador> getGoleros() {
		return goleros;
	}

	public void setGoleros(List<Jugador> goleros) {
		this.goleros = goleros;
	}

	public void onArqueroDroped(DragDropEvent ddEvent) {
        Jugador golero = ((Jugador) ddEvent.getData());
        //List<Jugador> goleros = this.goleros;
        
        if(goleros.isEmpty()){
        	goleros.add(golero);
            jugadores.remove(golero);
        }
        else{
        	Jugador exGolero = goleros.get(0);
        	goleros.remove(exGolero);
        	jugadores.remove(golero);
        	jugadores.add(exGolero);
        	goleros.add(golero);
        }
        
    }
	
	public void onDefensaDroped(DragDropEvent ddEvent) {
		Jugador defensa = ((Jugador) ddEvent.getData());
		List<Jugador> defensas = datosAlineacion.getDefensas();
		
		if(defensas.size() < 5){
			defensas.add(defensa);
		    jugadores.remove(defensa);
		}
		else{
			Jugador exDefensa = defensas.get(0);
			defensas.remove(exDefensa);
			jugadores.remove(defensa);
			jugadores.add(exDefensa);
			defensas.add(defensa);
		}
    }
	
	public void onMedioDroped(DragDropEvent ddEvent) {
		Jugador medio = ((Jugador) ddEvent.getData());
		List<Jugador> medios = datosAlineacion.getMediocampistas();
		
		if(medios.size() < 5){
			medios.add(medio);
		    jugadores.remove(medio);
		}
		else{
			Jugador exMedio = medios.get(0);
			medios.remove(exMedio);
			jugadores.remove(medio);
			jugadores.add(exMedio);
			medios.add(medio);
		}
    }
	
	public void onDelanteroDroped(DragDropEvent ddEvent) {
		Jugador delantero = ((Jugador) ddEvent.getData());
		List<Jugador> delanteros = datosAlineacion.getDelanteros();
		
		if(delanteros.size() < 3){
			delanteros.add(delantero);
		    jugadores.remove(delantero);
		}
		else{
			Jugador exDelantero = delanteros.get(0);
			delanteros.remove(exDelantero);
			jugadores.remove(delantero);
			jugadores.add(exDelantero);
			delanteros.add(delantero);
		}
    }
	
	public String enviarAlineacion(){
		
		try {
			if (!goleros.isEmpty())
				datosAlineacion.setGolero(goleros.get(0));
			Comunicacion.getInstance().getIPartidoControlador().setAlineacioPartido(datosAlineacion, idPartido, codEquipo);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		if (this.retorno.equals("home"))
			return "/webPages/home/home.xhtml?faces-redirect=true";
		else{
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			context.getApplicationMap().put("idPartido", this.idPartido);
			return "/webPages/partidos/minutoAMinuto.xhtml?faces-redirect=true";
		}
			
	}
	
	private void eliminarRepetidos(List<Jugador> lista){
		HashSet<Jugador> hs = new HashSet<Jugador>();
		hs.addAll(lista);
		lista.clear();
		lista.addAll(hs);
	}
	
	private void filtrarJugadores(Set<Jugador> jug, List<Jugador> filter){
		Jugador j = null;
		Iterator<Jugador> iter = jug.iterator();
		while (iter.hasNext()){
			j = (Jugador) iter.next();
			for (Jugador jugador : filter) {
				if(j.getCodigo() == jugador.getCodigo())
					iter.remove();
			}
		}
	}

}
