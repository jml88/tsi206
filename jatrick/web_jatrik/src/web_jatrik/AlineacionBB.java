package web_jatrik;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.component.UINamingContainer;
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
	private List<Jugador> jugadores;
	private Alineacion datosAlineacion;
	private List<Jugador> goleros;
	private List<Jugador> lesionGolero;
	private List<Jugador> lesionDefensa;
	private List<Jugador> lesionMediocampista;
	private List<Jugador> lesionDelantero;
	private Alineacion alineacion;
	private String retorno;
	
	public AlineacionBB() {
		// TODO Auto-generated constructor stub
	}
	
	@PostConstruct
	public void init(){
		
		try {
			this.codEquipo = this.sesion.getDatosManager().getCodEquipo();
			this.jugadores = new ArrayList<Jugador>();
			this.jugadores.addAll(Comunicacion.getInstance().getIEquipoControlador().obtenerJugadoresEquipo(this.codEquipo));
			this.equipo = Comunicacion.getInstance().getIEquipoControlador().obtenerEquipo(this.codEquipo);
			
			ExternalContext context = FacesContext.getCurrentInstance().getExternalContext();
			this.idPartido = (int)context.getApplicationMap().get("idPartido");
			this.retorno = (String)context.getApplicationMap().get("retorno");
			Partido partido = Comunicacion.getInstance().getIPartidoControlador().findPartido(idPartido);
			
			this.goleros = new ArrayList<Jugador>();
			this.lesionGolero = new ArrayList<Jugador>();
			this.lesionDefensa = new ArrayList<Jugador>();
			this.lesionMediocampista = new ArrayList<Jugador>();
			this.lesionDelantero = new ArrayList<Jugador>();
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

	public List<Jugador> getJugadores() {
		return jugadores;
	}

	public void setJugadores(List<Jugador> jugadores) {
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

	public List<Jugador> getLesionGolero() {
		return lesionGolero;
	}

	public void setLesionGolero(List<Jugador> lesionGolero) {
		this.lesionGolero = lesionGolero;
	}

	public List<Jugador> getLesionDefensa() {
		return lesionDefensa;
	}

	public void setLesionDefensa(List<Jugador> lesionDefensa) {
		this.lesionDefensa = lesionDefensa;
	}

	public List<Jugador> getLesionMediocampista() {
		return lesionMediocampista;
	}

	public void setLesionMediocampista(List<Jugador> lesionMediocampista) {
		this.lesionMediocampista = lesionMediocampista;
	}

	public List<Jugador> getLesionDelantero() {
		return lesionDelantero;
	}

	public void setLesionDelantero(List<Jugador> lesionDelantero) {
		this.lesionDelantero = lesionDelantero;
	}

	public void onArqueroDroped(DragDropEvent ddEvent) {
		List<Jugador> defensas = datosAlineacion.getDefensas();
	    List<Jugador> mediocampistas = datosAlineacion.getMediocampistas();
	    List<Jugador> delanteros = datosAlineacion.getDelanteros();
	    
	    String[] idTokens = ddEvent.getDragId().split(String.valueOf(UINamingContainer.getSeparatorChar(FacesContext.getCurrentInstance())));
	    int rowIndex = Integer.parseInt(idTokens[idTokens.length - 2]);
	    String name = idTokens[idTokens.length - 3];

	    Jugador lesion = null;
	    if (name.equals("lesionGoleroList")) {
	    	
	    	lesion = lesionGolero.get(rowIndex);
	    	if(goleros.isEmpty()){
	    		goleros.add(lesion);
	            lesionGolero.remove(lesion);
	        }else{
	        	Jugador ex = goleros.get(0);
	        	goleros.remove(ex);
	        	lesionGolero.remove(lesion);
	        	lesionGolero.add(ex);
	        	goleros.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDefensaList")) {

	    	lesion = defensas.get(rowIndex);
	    	if(goleros.isEmpty()){
	    		goleros.add(lesion);
	        	defensas.remove(lesion);
	        }else{
	        	Jugador ex = goleros.get(0);
	        	goleros.remove(ex);
	        	defensas.remove(lesion);
	        	defensas.add(ex);
	        	goleros.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresMedioList")) {
	    	
	    	lesion = mediocampistas.get(rowIndex);
	    	if(goleros.isEmpty()){
	    		goleros.add(lesion);
	        	mediocampistas.remove(lesion);
	        }else{
	        	Jugador ex = goleros.get(0);
	        	goleros.remove(ex);
	        	mediocampistas.remove(lesion);
	        	mediocampistas.add(ex);
	        	goleros.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDelanteraList")) {

	    	lesion = delanteros.get(rowIndex);
	    	if(goleros.isEmpty()){
	    		goleros.add(lesion);
	        	delanteros.remove(lesion);
	        }else{
	        	Jugador ex = goleros.get(0);
	        	goleros.remove(ex);
	        	delanteros.remove(lesion);
	        	delanteros.add(ex);
	        	goleros.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionDefensaList")) {

	    	lesion = lesionDefensa.get(rowIndex);
	    	if(goleros.isEmpty()){
	    		goleros.add(lesion);
	        	lesionDefensa.remove(lesion);
	        }else{
	        	Jugador ex = goleros.get(0);
	        	goleros.remove(ex);
	        	lesionDefensa.remove(lesion);
	        	lesionDefensa.add(ex);
	        	goleros.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionMediocampistaList")) {

	    	lesion = lesionMediocampista.get(rowIndex);
	    	if(goleros.isEmpty()){
	    		goleros.add(lesion);
	        	lesionMediocampista.remove(lesion);
	        }else{
	        	Jugador ex = goleros.get(0);
	        	goleros.remove(ex);
	        	lesionMediocampista.remove(lesion);
	        	lesionMediocampista.add(ex);
	        	goleros.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionDelanteroList")) {

	    	lesion = lesionDelantero.get(rowIndex);
	    	if(goleros.isEmpty()){
	    		goleros.add(lesion);
	        	lesionDelantero.remove(lesion);
	        }else{
	        	Jugador ex = goleros.get(0);
	        	goleros.remove(ex);
	        	lesionDelantero.remove(lesion);
	        	lesionDelantero.add(ex);
	        	goleros.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDisponiblesList")) {

	    	lesion = jugadores.get(rowIndex);
	    	if(goleros.isEmpty()){
	    		goleros.add(lesion);
	        	jugadores.remove(lesion);
	        }else{
	        	Jugador ex = goleros.get(0);
	        	goleros.remove(ex);
	        	jugadores.remove(lesion);
	        	jugadores.add(ex);
	        	goleros.add(lesion);
	        }
	    	
	    }
        
    }
	
	public void onDefensaDroped(DragDropEvent ddEvent) {
		List<Jugador> defensas = datosAlineacion.getDefensas();
	    List<Jugador> mediocampistas = datosAlineacion.getMediocampistas();
	    List<Jugador> delanteros = datosAlineacion.getDelanteros();
	    
	    String[] idTokens = ddEvent.getDragId().split(String.valueOf(UINamingContainer.getSeparatorChar(FacesContext.getCurrentInstance())));
	    int rowIndex = Integer.parseInt(idTokens[idTokens.length - 2]);
	    String name = idTokens[idTokens.length - 3];

	    Jugador lesion = null;
	    if (name.equals("jugadoresArqueroList")) {
	    	
	    	lesion = goleros.get(rowIndex);
	    	if(defensas.size() < 5){
	    		if (defensas.size() + mediocampistas.size() + delanteros.size() < 10){
	    			defensas.add(lesion);
	    			goleros.remove(lesion);
	    		}
	    		else{
	    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ya hay 11 jugadores en la alineación"));
	    		}
	        }else{
	        	Jugador ex = defensas.get(0);
	        	defensas.remove(ex);
	        	goleros.remove(lesion);
	        	goleros.add(ex);
	        	defensas.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresMedioList")) {

	    	lesion = mediocampistas.get(rowIndex);
	    	if(defensas.size() < 5){
	    		defensas.add(lesion);
	        	mediocampistas.remove(lesion);
	        }else{
	        	Jugador ex = defensas.get(0);
	        	defensas.remove(ex);
	        	mediocampistas.remove(lesion);
	        	mediocampistas.add(ex);
	        	defensas.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDelanteraList")) {
	    	
	    	lesion = delanteros.get(rowIndex);
	    	if(defensas.size() < 5){
	    		defensas.add(lesion);
	        	delanteros.remove(lesion);
	        }else{
	        	Jugador ex = defensas.get(0);
	        	defensas.remove(ex);
	        	delanteros.remove(lesion);
	        	delanteros.add(ex);
	        	defensas.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionGoleroList")) {

	    	lesion = lesionGolero.get(rowIndex);
	    	if(defensas.size() < 5){
	    		if (defensas.size() + mediocampistas.size() + delanteros.size() < 10){
		    		defensas.add(lesion);
		        	lesionGolero.remove(lesion);
	    		}
	    		else{
	    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ya hay 11 jugadores en la alineación"));
	    		}
	        }else{
	        	Jugador ex = defensas.get(0);
	        	defensas.remove(ex);
	        	lesionGolero.remove(lesion);
	        	lesionGolero.add(ex);
	        	defensas.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionDefensaList")) {

	    	lesion = lesionDefensa.get(rowIndex);
	    	if(defensas.size() < 5){
	    		if (defensas.size() + mediocampistas.size() + delanteros.size() < 10){
		    		defensas.add(lesion);
		        	lesionDefensa.remove(lesion);
	    		}
	    		else{
	    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ya hay 11 jugadores en la alineación"));
	    		}
	        }else{
	        	Jugador ex = defensas.get(0);
	        	defensas.remove(ex);
	        	lesionDefensa.remove(lesion);
	        	lesionDefensa.add(ex);
	        	defensas.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionMediocampistaList")) {

	    	lesion = lesionMediocampista.get(rowIndex);
	    	if(defensas.size() < 5){
	    		if (defensas.size() + mediocampistas.size() + delanteros.size() < 10){
		    		defensas.add(lesion);
		        	lesionMediocampista.remove(lesion);
	    		}
	    		else{
	    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ya hay 11 jugadores en la alineación"));
	    		}
	        }else{
	        	Jugador ex = defensas.get(0);
	        	defensas.remove(ex);
	        	lesionMediocampista.remove(lesion);
	        	lesionMediocampista.add(ex);
	        	defensas.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionDelanteroList")) {

	    	lesion = lesionDelantero.get(rowIndex);
	    	if(defensas.size() < 5){
	    		if (defensas.size() + mediocampistas.size() + delanteros.size() < 10){
		    		defensas.add(lesion);
		        	lesionDelantero.remove(lesion);
	    		}
	    		else{
	    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ya hay 11 jugadores en la alineación"));
	    		}
	        }else{
	        	Jugador ex = defensas.get(0);
	        	defensas.remove(ex);
	        	lesionDelantero.remove(lesion);
	        	lesionDelantero.add(ex);
	        	defensas.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDisponiblesList")) {

	    	lesion = jugadores.get(rowIndex);
	    	if(defensas.size() < 5){
	    		if (defensas.size() + mediocampistas.size() + delanteros.size() < 10){
		    		defensas.add(lesion);
		        	jugadores.remove(lesion);
	    		}
	    		else{
	    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ya hay 11 jugadores en la alineación"));
	    		}
	        }else{
	        	Jugador ex = defensas.get(0);
	        	defensas.remove(ex);
	        	jugadores.remove(lesion);
	        	jugadores.add(ex);
	        	defensas.add(lesion);
	        }
	    	
	    }
    }
	
	public void onMedioDroped(DragDropEvent ddEvent) {
		List<Jugador> defensas = datosAlineacion.getDefensas();
	    List<Jugador> mediocampistas = datosAlineacion.getMediocampistas();
	    List<Jugador> delanteros = datosAlineacion.getDelanteros();
	    
	    String[] idTokens = ddEvent.getDragId().split(String.valueOf(UINamingContainer.getSeparatorChar(FacesContext.getCurrentInstance())));
	    int rowIndex = Integer.parseInt(idTokens[idTokens.length - 2]);
	    String name = idTokens[idTokens.length - 3];

	    Jugador lesion = null;
	    if (name.equals("jugadoresArqueroList")) {
	    	
	    	lesion = goleros.get(rowIndex);
	    	if(mediocampistas.size() < 5){
	    		if (defensas.size() + mediocampistas.size() + delanteros.size() < 10){
		    		mediocampistas.add(lesion);
		            goleros.remove(lesion);
	    		}
	    		else{
	    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ya hay 11 jugadores en la alineación"));
	    		}
	        }else{
	        	Jugador ex = mediocampistas.get(0);
	        	mediocampistas.remove(ex);
	        	goleros.remove(lesion);
	        	goleros.add(ex);
	        	mediocampistas.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDefensaList")) {

	    	lesion = defensas.get(rowIndex);
	    	if(mediocampistas.size() < 5){
	    		mediocampistas.add(lesion);
	        	defensas.remove(lesion);
	        }else{
	        	Jugador ex = mediocampistas.get(0);
	        	mediocampistas.remove(ex);
	        	defensas.remove(lesion);
	        	defensas.add(ex);
	        	mediocampistas.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDelanteraList")) {
	    	
	    	lesion = delanteros.get(rowIndex);
	    	if(mediocampistas.size() < 5){
	    		mediocampistas.add(lesion);
	        	delanteros.remove(lesion);
	        }else{
	        	Jugador ex = mediocampistas.get(0);
	        	mediocampistas.remove(ex);
	        	delanteros.remove(lesion);
	        	delanteros.add(ex);
	        	mediocampistas.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionGoleroList")) {

	    	lesion = lesionGolero.get(rowIndex);
	    	if(mediocampistas.size() < 5){
	    		if (defensas.size() + mediocampistas.size() + delanteros.size() < 10){
		    		mediocampistas.add(lesion);
		        	lesionGolero.remove(lesion);
	    		}
	    		else{
	    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ya hay 11 jugadores en la alineación"));
	    		}
	        }else{
	        	Jugador ex = mediocampistas.get(0);
	        	mediocampistas.remove(ex);
	        	lesionGolero.remove(lesion);
	        	lesionGolero.add(ex);
	        	mediocampistas.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionDefensaList")) {

	    	lesion = lesionDefensa.get(rowIndex);
	    	if(mediocampistas.size() < 5){
	    		if (defensas.size() + mediocampistas.size() + delanteros.size() < 10){
		    		mediocampistas.add(lesion);
		        	lesionDefensa.remove(lesion);
	    		}
	    		else{
	    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ya hay 11 jugadores en la alineación"));
	    		}
	        }else{
	        	Jugador ex = mediocampistas.get(0);
	        	mediocampistas.remove(ex);
	        	lesionDefensa.remove(lesion);
	        	lesionDefensa.add(ex);
	        	mediocampistas.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionMediocampistaList")) {

	    	lesion = lesionMediocampista.get(rowIndex);
	    	if(mediocampistas.size() < 5){
	    		if (defensas.size() + mediocampistas.size() + delanteros.size() < 10){
		    		mediocampistas.add(lesion);
		        	lesionMediocampista.remove(lesion);
	    		}
	    		else{
	    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ya hay 11 jugadores en la alineación"));
	    		}
	        }else{
	        	Jugador ex = mediocampistas.get(0);
	        	mediocampistas.remove(ex);
	        	lesionMediocampista.remove(lesion);
	        	lesionMediocampista.add(ex);
	        	mediocampistas.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionDelanteroList")) {

	    	lesion = lesionDelantero.get(rowIndex);
	    	if(mediocampistas.size() < 5){
	    		if (defensas.size() + mediocampistas.size() + delanteros.size() < 10){
		    		mediocampistas.add(lesion);
		        	lesionDelantero.remove(lesion);
	    		}
	    		else{
	    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ya hay 11 jugadores en la alineación"));
	    		}
	        }else{
	        	Jugador ex = mediocampistas.get(0);
	        	mediocampistas.remove(ex);
	        	lesionDelantero.remove(lesion);
	        	lesionDelantero.add(ex);
	        	mediocampistas.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDisponiblesList")) {

	    	lesion = jugadores.get(rowIndex);
	    	if(mediocampistas.size() < 5){
	    		if (defensas.size() + mediocampistas.size() + delanteros.size() < 10){
		    		mediocampistas.add(lesion);
		        	jugadores.remove(lesion);
	    		}
	    		else{
	    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ya hay 11 jugadores en la alineación"));
	    		}
	        }else{
	        	Jugador ex = mediocampistas.get(0);
	        	mediocampistas.remove(ex);
	        	jugadores.remove(lesion);
	        	jugadores.add(ex);
	        	mediocampistas.add(lesion);
	        }
	    	
	    }
    }
	
	public void onDelanteroDroped(DragDropEvent ddEvent) {
		List<Jugador> defensas = datosAlineacion.getDefensas();
	    List<Jugador> mediocampistas = datosAlineacion.getMediocampistas();
	    List<Jugador> delanteros = datosAlineacion.getDelanteros();
	    
	    String[] idTokens = ddEvent.getDragId().split(String.valueOf(UINamingContainer.getSeparatorChar(FacesContext.getCurrentInstance())));
	    int rowIndex = Integer.parseInt(idTokens[idTokens.length - 2]);
	    String name = idTokens[idTokens.length - 3];

	    Jugador lesion = null;
	    if (name.equals("jugadoresArqueroList")) {
	    	
	    	lesion = goleros.get(rowIndex);
	    	if(delanteros.size() < 3){
	    		if (defensas.size() + mediocampistas.size() + delanteros.size() < 10){
		    		delanteros.add(lesion);
		            goleros.remove(lesion);
	    		}
	    		else{
	    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ya hay 11 jugadores en la alineación"));
	    		}
	        }else{
	        	Jugador ex = delanteros.get(0);
	        	delanteros.remove(ex);
	        	goleros.remove(lesion);
	        	goleros.add(ex);
	        	delanteros.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDefensaList")) {

	    	lesion = defensas.get(rowIndex);
	    	if(delanteros.size() < 3){
	    		delanteros.add(lesion);
	        	defensas.remove(lesion);
	        }else{
	        	Jugador ex = delanteros.get(0);
	        	delanteros.remove(ex);
	        	defensas.remove(lesion);
	        	defensas.add(ex);
	        	delanteros.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresMedioList")) {
	    	
	    	lesion = mediocampistas.get(rowIndex);
	    	if(delanteros.size() < 3){
	    		delanteros.add(lesion);
	        	mediocampistas.remove(lesion);
	        }else{
	        	Jugador ex = delanteros.get(0);
	        	delanteros.remove(ex);
	        	mediocampistas.remove(lesion);
	        	mediocampistas.add(ex);
	        	delanteros.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionGoleroList")) {

	    	lesion = lesionGolero.get(rowIndex);
	    	if(delanteros.size() < 3){
	    		if (defensas.size() + mediocampistas.size() + delanteros.size() < 10){
		    		delanteros.add(lesion);
		        	lesionGolero.remove(lesion);
	    		}
	    		else{
	    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ya hay 11 jugadores en la alineación"));
	    		}
	        }else{
	        	Jugador ex = delanteros.get(0);
	        	delanteros.remove(ex);
	        	lesionGolero.remove(lesion);
	        	lesionGolero.add(ex);
	        	delanteros.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionDefensaList")) {

	    	lesion = lesionDefensa.get(rowIndex);
	    	if(delanteros.size() < 3){
	    		if (defensas.size() + mediocampistas.size() + delanteros.size() < 10){
		    		delanteros.add(lesion);
		        	lesionDefensa.remove(lesion);
	    		}
	    		else{
	    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ya hay 11 jugadores en la alineación"));
	    		}
	        }else{
	        	Jugador ex = delanteros.get(0);
	        	delanteros.remove(ex);
	        	lesionDefensa.remove(lesion);
	        	lesionDefensa.add(ex);
	        	delanteros.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionMediocampistaList")) {

	    	lesion = lesionMediocampista.get(rowIndex);
	    	if(delanteros.size() < 3){
	    		if (defensas.size() + mediocampistas.size() + delanteros.size() < 10){
		    		delanteros.add(lesion);
		        	lesionMediocampista.remove(lesion);
	    		}
	    		else{
	    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ya hay 11 jugadores en la alineación"));
	    		}
	        }else{
	        	Jugador ex = delanteros.get(0);
	        	delanteros.remove(ex);
	        	lesionMediocampista.remove(lesion);
	        	lesionMediocampista.add(ex);
	        	delanteros.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionDelanteroList")) {

	    	lesion = lesionDelantero.get(rowIndex);
	    	if(delanteros.size() < 3){
	    		if (defensas.size() + mediocampistas.size() + delanteros.size() < 10){
		    		delanteros.add(lesion);
		        	lesionDelantero.remove(lesion);
	    		}
	    		else{
	    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ya hay 11 jugadores en la alineación"));
	    		}
	        }else{
	        	Jugador ex = delanteros.get(0);
	        	delanteros.remove(ex);
	        	lesionDelantero.remove(lesion);
	        	lesionDelantero.add(ex);
	        	delanteros.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDisponiblesList")) {

	    	lesion = jugadores.get(rowIndex);
	    	if(delanteros.size() < 3){
	    		if (defensas.size() + mediocampistas.size() + delanteros.size() < 10){
		    		delanteros.add(lesion);
		        	jugadores.remove(lesion);
	    		}
	    		else{
	    			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", "Ya hay 11 jugadores en la alineación"));
	    		}
	        }else{
	        	Jugador ex = delanteros.get(0);
	        	delanteros.remove(ex);
	        	jugadores.remove(lesion);
	        	jugadores.add(ex);
	        	delanteros.add(lesion);
	        }
	    	
	    }
    }
	
	public void onLesionGoleroDroped(DragDropEvent ddEvent) {
		List<Jugador> defensas = datosAlineacion.getDefensas();
	    List<Jugador> mediocampistas = datosAlineacion.getMediocampistas();
	    List<Jugador> delanteros = datosAlineacion.getDelanteros();
	    
	    String[] idTokens = ddEvent.getDragId().split(String.valueOf(UINamingContainer.getSeparatorChar(FacesContext.getCurrentInstance())));
	    int rowIndex = Integer.parseInt(idTokens[idTokens.length - 2]);
	    String name = idTokens[idTokens.length - 3];

	    Jugador lesion = null;
	    if (name.equals("jugadoresArqueroList")) {
	    	
	    	lesion = goleros.get(rowIndex);
	    	if(lesionGolero.isEmpty()){
	    		lesionGolero.add(lesion);
	            goleros.remove(lesion);
	        }else{
	        	Jugador ex = lesionGolero.get(0);
	        	lesionGolero.remove(ex);
	        	goleros.remove(lesion);
	        	goleros.add(ex);
	        	lesionGolero.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDefensaList")) {

	    	lesion = defensas.get(rowIndex);
	    	if(lesionGolero.isEmpty()){
	    		lesionGolero.add(lesion);
	        	defensas.remove(lesion);
	        }else{
	        	Jugador ex = lesionGolero.get(0);
	        	lesionGolero.remove(ex);
	        	defensas.remove(lesion);
	        	defensas.add(ex);
	        	lesionGolero.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresMedioList")) {
	    	
	    	lesion = mediocampistas.get(rowIndex);
	    	if(lesionGolero.isEmpty()){
	    		lesionGolero.add(lesion);
	        	mediocampistas.remove(lesion);
	        }else{
	        	Jugador ex = lesionGolero.get(0);
	        	lesionGolero.remove(ex);
	        	mediocampistas.remove(lesion);
	        	mediocampistas.add(ex);
	        	lesionGolero.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDelanteraList")) {

	    	lesion = delanteros.get(rowIndex);
	    	if(lesionGolero.isEmpty()){
	    		lesionGolero.add(lesion);
	        	delanteros.remove(lesion);
	        }else{
	        	Jugador ex = lesionGolero.get(0);
	        	lesionGolero.remove(ex);
	        	delanteros.remove(lesion);
	        	delanteros.add(ex);
	        	lesionGolero.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionDefensaList")) {

	    	lesion = lesionDefensa.get(rowIndex);
	    	if(lesionGolero.isEmpty()){
	    		lesionGolero.add(lesion);
	        	lesionDefensa.remove(lesion);
	        }else{
	        	Jugador ex = lesionGolero.get(0);
	        	lesionGolero.remove(ex);
	        	lesionDefensa.remove(lesion);
	        	lesionDefensa.add(ex);
	        	lesionGolero.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionMediocampistaList")) {

	    	lesion = lesionMediocampista.get(rowIndex);
	    	if(lesionGolero.isEmpty()){
	    		lesionGolero.add(lesion);
	        	lesionMediocampista.remove(lesion);
	        }else{
	        	Jugador ex = lesionGolero.get(0);
	        	lesionGolero.remove(ex);
	        	lesionMediocampista.remove(lesion);
	        	lesionMediocampista.add(ex);
	        	lesionGolero.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionDelanteroList")) {

	    	lesion = lesionDelantero.get(rowIndex);
	    	if(lesionGolero.isEmpty()){
	    		lesionGolero.add(lesion);
	        	lesionDelantero.remove(lesion);
	        }else{
	        	Jugador ex = lesionGolero.get(0);
	        	lesionGolero.remove(ex);
	        	lesionDelantero.remove(lesion);
	        	lesionDelantero.add(ex);
	        	lesionGolero.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDisponiblesList")) {

	    	lesion = jugadores.get(rowIndex);
	    	if(lesionGolero.isEmpty()){
	    		lesionGolero.add(lesion);
	        	jugadores.remove(lesion);
	        }else{
	        	Jugador ex = lesionGolero.get(0);
	        	lesionGolero.remove(ex);
	        	jugadores.remove(lesion);
	        	jugadores.add(ex);
	        	lesionGolero.add(lesion);
	        }
	    	
	    }
        
    }
	
	public void onLesionDefensaDroped(DragDropEvent ddEvent) {
		List<Jugador> defensas = datosAlineacion.getDefensas();
	    List<Jugador> mediocampistas = datosAlineacion.getMediocampistas();
	    List<Jugador> delanteros = datosAlineacion.getDelanteros();
	    
	    String[] idTokens = ddEvent.getDragId().split(String.valueOf(UINamingContainer.getSeparatorChar(FacesContext.getCurrentInstance())));
	    int rowIndex = Integer.parseInt(idTokens[idTokens.length - 2]);
	    String name = idTokens[idTokens.length - 3];

	    Jugador lesion = null;
	    if (name.equals("jugadoresArqueroList")) {
	    	
	    	lesion = goleros.get(rowIndex);
	    	if(lesionDefensa.isEmpty()){
	    		lesionDefensa.add(lesion);
	            goleros.remove(lesion);
	        }else{
	        	Jugador ex = lesionDefensa.get(0);
	        	lesionDefensa.remove(ex);
	        	goleros.remove(lesion);
	        	goleros.add(ex);
	        	lesionDefensa.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDefensaList")) {

	    	lesion = defensas.get(rowIndex);
	    	if(lesionDefensa.isEmpty()){
	    		lesionDefensa.add(lesion);
	        	defensas.remove(lesion);
	        }else{
	        	Jugador ex = lesionDefensa.get(0);
	        	lesionDefensa.remove(ex);
	        	defensas.remove(lesion);
	        	defensas.add(ex);
	        	lesionDefensa.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresMedioList")) {
	    	
	    	lesion = mediocampistas.get(rowIndex);
	    	if(lesionDefensa.isEmpty()){
	    		lesionDefensa.add(lesion);
	        	mediocampistas.remove(lesion);
	        }else{
	        	Jugador ex = lesionDefensa.get(0);
	        	lesionDefensa.remove(ex);
	        	mediocampistas.remove(lesion);
	        	mediocampistas.add(ex);
	        	lesionDefensa.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDelanteraList")) {

	    	lesion = delanteros.get(rowIndex);
	    	if(lesionDefensa.isEmpty()){
	    		lesionDefensa.add(lesion);
	        	delanteros.remove(lesion);
	        }else{
	        	Jugador ex = lesionDefensa.get(0);
	        	lesionDefensa.remove(ex);
	        	delanteros.remove(lesion);
	        	delanteros.add(ex);
	        	lesionDefensa.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionGoleroList")) {

	    	lesion = lesionGolero.get(rowIndex);
	    	if(lesionDefensa.isEmpty()){
	    		lesionDefensa.add(lesion);
	        	lesionGolero.remove(lesion);
	        }else{
	        	Jugador ex = lesionDefensa.get(0);
	        	lesionDefensa.remove(ex);
	        	lesionGolero.remove(lesion);
	        	lesionGolero.add(ex);
	        	lesionDefensa.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionMediocampistaList")) {

	    	lesion = lesionMediocampista.get(rowIndex);
	    	if(lesionDefensa.isEmpty()){
	    		lesionDefensa.add(lesion);
	        	lesionMediocampista.remove(lesion);
	        }else{
	        	Jugador ex = lesionDefensa.get(0);
	        	lesionDefensa.remove(ex);
	        	lesionMediocampista.remove(lesion);
	        	lesionMediocampista.add(ex);
	        	lesionDefensa.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionDelanteroList")) {

	    	lesion = lesionDelantero.get(rowIndex);
	    	if(lesionDefensa.isEmpty()){
	    		lesionDefensa.add(lesion);
	        	lesionDelantero.remove(lesion);
	        }else{
	        	Jugador ex = lesionDefensa.get(0);
	        	lesionDefensa.remove(ex);
	        	lesionDelantero.remove(lesion);
	        	lesionDelantero.add(ex);
	        	lesionDefensa.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDisponiblesList")) {

	    	lesion = jugadores.get(rowIndex);
	    	if(lesionDefensa.isEmpty()){
	    		lesionDefensa.add(lesion);
	        	jugadores.remove(lesion);
	        }else{
	        	Jugador ex = lesionDefensa.get(0);
	        	lesionDefensa.remove(ex);
	        	jugadores.remove(lesion);
	        	jugadores.add(ex);
	        	lesionDefensa.add(lesion);
	        }
	    	
	    }
        
    }
	
	public void onLesionMediocampistaDroped(DragDropEvent ddEvent) {
		List<Jugador> defensas = datosAlineacion.getDefensas();
	    List<Jugador> mediocampistas = datosAlineacion.getMediocampistas();
	    List<Jugador> delanteros = datosAlineacion.getDelanteros();
	    
	    String[] idTokens = ddEvent.getDragId().split(String.valueOf(UINamingContainer.getSeparatorChar(FacesContext.getCurrentInstance())));
	    int rowIndex = Integer.parseInt(idTokens[idTokens.length - 2]);
	    String name = idTokens[idTokens.length - 3];

	    Jugador lesion = null;
	    if (name.equals("jugadoresArqueroList")) {
	    	
	    	lesion = goleros.get(rowIndex);
	    	if(lesionMediocampista.isEmpty()){
	    		lesionMediocampista.add(lesion);
	            goleros.remove(lesion);
	        }else{
	        	Jugador ex = lesionMediocampista.get(0);
	        	lesionMediocampista.remove(ex);
	        	goleros.remove(lesion);
	        	goleros.add(ex);
	        	lesionMediocampista.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDefensaList")) {

	    	lesion = defensas.get(rowIndex);
	    	if(lesionMediocampista.isEmpty()){
	    		lesionMediocampista.add(lesion);
	        	defensas.remove(lesion);
	        }else{
	        	Jugador ex = lesionMediocampista.get(0);
	        	lesionMediocampista.remove(ex);
	        	defensas.remove(lesion);
	        	defensas.add(ex);
	        	lesionMediocampista.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresMedioList")) {
	    	
	    	lesion = mediocampistas.get(rowIndex);
	    	if(lesionMediocampista.isEmpty()){
	    		lesionMediocampista.add(lesion);
	        	mediocampistas.remove(lesion);
	        }else{
	        	Jugador ex = lesionMediocampista.get(0);
	        	lesionMediocampista.remove(ex);
	        	mediocampistas.remove(lesion);
	        	mediocampistas.add(ex);
	        	lesionMediocampista.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDelanteraList")) {

	    	lesion = delanteros.get(rowIndex);
	    	if(lesionMediocampista.isEmpty()){
	    		lesionMediocampista.add(lesion);
	        	delanteros.remove(lesion);
	        }else{
	        	Jugador ex = lesionMediocampista.get(0);
	        	lesionMediocampista.remove(ex);
	        	delanteros.remove(lesion);
	        	delanteros.add(ex);
	        	lesionMediocampista.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionGoleroList")) {

	    	lesion = lesionGolero.get(rowIndex);
	    	if(lesionMediocampista.isEmpty()){
	    		lesionMediocampista.add(lesion);
	        	lesionGolero.remove(lesion);
	        }else{
	        	Jugador ex = lesionMediocampista.get(0);
	        	lesionMediocampista.remove(ex);
	        	lesionGolero.remove(lesion);
	        	lesionGolero.add(ex);
	        	lesionMediocampista.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionDefensaList")) {

	    	lesion = lesionDefensa.get(rowIndex);
	    	if(lesionMediocampista.isEmpty()){
	    		lesionMediocampista.add(lesion);
	        	lesionDefensa.remove(lesion);
	        }else{
	        	Jugador ex = lesionMediocampista.get(0);
	        	lesionMediocampista.remove(ex);
	        	lesionDefensa.remove(lesion);
	        	lesionDefensa.add(ex);
	        	lesionMediocampista.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionDelanteroList")) {

	    	lesion = lesionDelantero.get(rowIndex);
	    	if(lesionMediocampista.isEmpty()){
	    		lesionMediocampista.add(lesion);
	        	lesionDelantero.remove(lesion);
	        }else{
	        	Jugador ex = lesionMediocampista.get(0);
	        	lesionMediocampista.remove(ex);
	        	lesionDelantero.remove(lesion);
	        	lesionDelantero.add(ex);
	        	lesionMediocampista.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDisponiblesList")) {

	    	lesion = jugadores.get(rowIndex);
	    	if(lesionMediocampista.isEmpty()){
	    		lesionMediocampista.add(lesion);
	        	jugadores.remove(lesion);
	        }else{
	        	Jugador ex = lesionMediocampista.get(0);
	        	lesionMediocampista.remove(ex);
	        	jugadores.remove(lesion);
	        	jugadores.add(ex);
	        	lesionMediocampista.add(lesion);
	        }
	    	
	    }
        
    }
	
	public void onLesionDelanteroDroped(DragDropEvent ddEvent) {
        
        List<Jugador> defensas = datosAlineacion.getDefensas();
	    List<Jugador> mediocampistas = datosAlineacion.getMediocampistas();
	    List<Jugador> delanteros = datosAlineacion.getDelanteros();
	    
	    String[] idTokens = ddEvent.getDragId().split(String.valueOf(UINamingContainer.getSeparatorChar(FacesContext.getCurrentInstance())));
	    int rowIndex = Integer.parseInt(idTokens[idTokens.length - 2]);
	    String name = idTokens[idTokens.length - 3];

	    Jugador lesion = null;
	    if (name.equals("jugadoresArqueroList")) {
	    	
	    	lesion = goleros.get(rowIndex);
	    	if(lesionDelantero.isEmpty()){
	        	lesionDelantero.add(lesion);
	            goleros.remove(lesion);
	        }else{
	        	Jugador ex = lesionDelantero.get(0);
	        	lesionDelantero.remove(ex);
	        	goleros.remove(lesion);
	        	goleros.add(ex);
	        	lesionDelantero.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDefensaList")) {

	    	lesion = defensas.get(rowIndex);
	    	if(lesionDelantero.isEmpty()){
	        	lesionDelantero.add(lesion);
	        	defensas.remove(lesion);
	        }else{
	        	Jugador ex = lesionDelantero.get(0);
	        	lesionDelantero.remove(ex);
	        	defensas.remove(lesion);
	        	defensas.add(ex);
	        	lesionDelantero.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresMedioList")) {
	    	
	    	lesion = mediocampistas.get(rowIndex);
	    	if(lesionDelantero.isEmpty()){
	        	lesionDelantero.add(lesion);
	        	mediocampistas.remove(lesion);
	        }else{
	        	Jugador ex = lesionDelantero.get(0);
	        	lesionDelantero.remove(ex);
	        	mediocampistas.remove(lesion);
	        	mediocampistas.add(ex);
	        	lesionDelantero.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDelanteraList")) {

	    	lesion = delanteros.get(rowIndex);
	    	if(lesionDelantero.isEmpty()){
	        	lesionDelantero.add(lesion);
	        	delanteros.remove(lesion);
	        }else{
	        	Jugador ex = lesionDelantero.get(0);
	        	lesionDelantero.remove(ex);
	        	delanteros.remove(lesion);
	        	delanteros.add(ex);
	        	lesionDelantero.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionGoleroList")) {

	    	lesion = lesionGolero.get(rowIndex);
	    	if(lesionDelantero.isEmpty()){
	        	lesionDelantero.add(lesion);
	        	lesionGolero.remove(lesion);
	        }else{
	        	Jugador ex = lesionDelantero.get(0);
	        	lesionDelantero.remove(ex);
	        	lesionGolero.remove(lesion);
	        	lesionGolero.add(ex);
	        	lesionDelantero.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionDefensaList")) {

	    	lesion = lesionDefensa.get(rowIndex);
	    	if(lesionDelantero.isEmpty()){
	        	lesionDelantero.add(lesion);
	        	lesionDefensa.remove(lesion);
	        }else{
	        	Jugador ex = lesionDelantero.get(0);
	        	lesionDelantero.remove(ex);
	        	lesionDefensa.remove(lesion);
	        	lesionDefensa.add(ex);
	        	lesionDelantero.add(lesion);
	        }
	    	
	    } else if (name.equals("lesionMediocampistaList")) {

	    	lesion = lesionMediocampista.get(rowIndex);
	    	if(lesionDelantero.isEmpty()){
	        	lesionDelantero.add(lesion);
	        	lesionMediocampista.remove(lesion);
	        }else{
	        	Jugador ex = lesionDelantero.get(0);
	        	lesionDelantero.remove(ex);
	        	lesionMediocampista.remove(lesion);
	        	lesionMediocampista.add(ex);
	        	lesionDelantero.add(lesion);
	        }
	    	
	    } else if (name.equals("jugadoresDisponiblesList")) {

	    	lesion = jugadores.get(rowIndex);
	    	if(lesionDelantero.isEmpty()){
	        	lesionDelantero.add(lesion);
	        	jugadores.remove(lesion);
	        }else{
	        	Jugador ex = lesionDelantero.get(0);
	        	lesionDelantero.remove(ex);
	        	jugadores.remove(lesion);
	        	jugadores.add(ex);
	        	lesionDelantero.add(lesion);
	        }
	    	
	    }
        
    }
	
	public void onJugadoresDroped(DragDropEvent ddEvent) {
	    List<Jugador> defensas = datosAlineacion.getDefensas();
	    List<Jugador> mediocampistas = datosAlineacion.getMediocampistas();
	    List<Jugador> delanteros = datosAlineacion.getDelanteros();
	    
	    String[] idTokens = ddEvent.getDragId().split(String.valueOf(UINamingContainer.getSeparatorChar(FacesContext.getCurrentInstance())));
	    int rowIndex = Integer.parseInt(idTokens[idTokens.length - 2]);
	    String name = idTokens[idTokens.length - 3];

	    Jugador jugador = null;
	    if (name.equals("jugadoresArqueroList")) {
	    	jugador = goleros.get(rowIndex);
	    	goleros.remove(jugador);
	    	jugadores.add(jugador);
	    } else if (name.equals("jugadoresDefensaList")) {
	    	jugador = defensas.get(rowIndex);
	    	defensas.remove(jugador);
	    	jugadores.add(jugador);
	    } else if (name.equals("jugadoresMedioList")) {
	    	jugador = mediocampistas.get(rowIndex);
	    	mediocampistas.remove(jugador);
	    	jugadores.add(jugador);
	    } else if (name.equals("jugadoresDelanteraList")) {
	    	jugador = delanteros.get(rowIndex);
	    	delanteros.remove(jugador);
	    	jugadores.add(jugador);
	    } else if (name.equals("lesionGoleroList")) {
	    	jugador = lesionGolero.get(rowIndex);
	    	lesionGolero.remove(jugador);
	    	jugadores.add(jugador);
	    } else if (name.equals("lesionDefensaList")) {
	    	jugador = lesionDefensa.get(rowIndex);
	    	lesionDefensa.remove(jugador);
	    	jugadores.add(jugador);
	    } else if (name.equals("lesionMediocampistaList")) {
	    	jugador = lesionMediocampista.get(rowIndex);
	    	lesionMediocampista.remove(jugador);
	    	jugadores.add(jugador);
	    } else if (name.equals("lesionDelanteroList")) {
	    	jugador = lesionDelantero.get(rowIndex);
	    	lesionDelantero.remove(jugador);
	    	jugadores.add(jugador);
	    }
	    
	}
	
	public String enviarAlineacion(){
		
		try {
			if (!goleros.isEmpty())
				datosAlineacion.setGolero(goleros.get(0));
			if (!lesionGolero.isEmpty())
				datosAlineacion.setLesionGolero(lesionGolero.get(0));
			if (!lesionDefensa.isEmpty())
				datosAlineacion.setLesionDefensas(lesionDefensa.get(0));
			if (!lesionMediocampista.isEmpty())
				datosAlineacion.setLesionMediocampistas(lesionMediocampista.get(0));
			if (!lesionDelantero.isEmpty())
				datosAlineacion.setLesionDelantero(lesionDelantero.get(0));
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
	
	private void filtrarJugadores(List<Jugador> jug, List<Jugador> filter){
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
