package web_jatrik;

import java.io.Serializable;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import org.primefaces.event.CloseEvent;
import org.primefaces.event.DashboardReorderEvent;
import org.primefaces.event.DragDropEvent;
import org.primefaces.event.ToggleEvent;
import org.primefaces.model.DashboardModel;

import comunicacion.Comunicacion;

import datatypes.DatosAlineacion;
import datatypes.DatosEquipo;
import datatypes.DatosJugador;

@Named("alineacionBB")
@ViewScoped
public class AlineacionBB implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private DashboardModel model;
	
	@Inject
	SessionBB sesion;
	
	private int codEquipo;
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
		
		datosAlineacion = new DatosAlineacion();
		
//		model = new DefaultDashboardModel();
//		
//		DashboardColumn column1 = new DefaultDashboardColumn();
//		DashboardColumn column2 = new DefaultDashboardColumn();
//		DashboardColumn column3 = new DefaultDashboardColumn();
//
//		column1.addWidget("sports");
//        column1.addWidget("finance");
//         
//        column2.addWidget("lifestyle");
//        column2.addWidget("weather");
//         
//        column3.addWidget("politics");
// 
//        model.addColumn(column1);
//        model.addColumn(column2);
//        model.addColumn(column3);
    }
	 
    public int getCodEquipo() {
		return codEquipo;
	}
    
    public void setCodEquipo(int codEquipo) {
		this.codEquipo = codEquipo;
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
	
	public void onPlayerDroped(DragDropEvent ddEvent) {
        DatosJugador golero = ((DatosJugador) ddEvent.getData());
  
        datosAlineacion.addGolero(golero);
        jugadores.remove(golero);
    }

	public void handleReorder(DashboardReorderEvent event) {
        FacesMessage message = new FacesMessage();
        message.setSeverity(FacesMessage.SEVERITY_INFO);
        message.setSummary("Reordered: " + event.getWidgetId());
        message.setDetail("Item index: " + event.getItemIndex() + ", Column index: " + event.getColumnIndex() + ", Sender index: " + event.getSenderColumnIndex());
         
        addMessage(message);
    }
     
    public void handleClose(CloseEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Panel Closed", "Closed panel id:'" + event.getComponent().getId() + "'");
         
        addMessage(message);
    }
     
    public void handleToggle(ToggleEvent event) {
        FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, event.getComponent().getId() + " toggled", "Status:" + event.getVisibility().name());
         
        addMessage(message);
    }
     
    private void addMessage(FacesMessage message) {
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
     
    public DashboardModel getModel() {
        return model;
    }
}
