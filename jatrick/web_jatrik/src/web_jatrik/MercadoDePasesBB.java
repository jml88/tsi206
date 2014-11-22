package web_jatrik;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import comunicacion.Comunicacion;
import equipos.Equipo;
import mercadoDePases.CompraVentaJugadores;
import mercadoDePases.Oferta;

@Named("mercadoDePasesBB")
@ViewScoped
public class MercadoDePasesBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<CompraVentaJugadores> compraVentaLista;
	
	private List<CompraVentaJugadores> compraVentaListaFiltered;
	
	private CompraVentaJugadores jugador;
	
	private int codigoEquipo;
	
	public static final String espacio = " ";
	
	@Inject
	SessionBB sesion;
	
	
	
	@PostConstruct
	public void init(){
		codigoEquipo = sesion.getDatosManager().getCodEquipo();
		//FIXME porque esto?
		CompraVentaJugadores c = new CompraVentaJugadores(200, null, null, null, null, null, true);
		try {
			compraVentaLista = Comunicacion.getInstance().getIMercadoDePasesControlador().listarJugadoresEnVenta();
			for (CompraVentaJugadores compraVentaJugadores : compraVentaLista) {
				compraVentaJugadores.setPrecio(150);
			}
			compraVentaLista.add(c);
		} catch (NamingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}
	}
	
	public void verDetallesJugador() {
		FacesContext faces = FacesContext.getCurrentInstance();
		faces.getExternalContext().getApplicationMap().put("jugador", jugador.getJugadorVenta());
		ConfigurableNavigationHandler configurableNavigationHandler = (ConfigurableNavigationHandler) FacesContext.getCurrentInstance().getApplication().getNavigationHandler();
		configurableNavigationHandler.performNavigation("/webPages/jugadores/detallesJugador.xhtml?faces-redirect=true");
    }
	
	
	public boolean filterBySalario(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
         
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
	
	public boolean filterByEdad(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
         
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
	
	public boolean filterByPortero(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
         
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
	
	public boolean filterByAtaque(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
         
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
	
	public boolean filterByDefensa(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
         
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
	
	public boolean filterByTecnica(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
         
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
	
	public boolean filterByPrecio(Object value, Object filter, Locale locale) {
        String filterText = (filter == null) ? null : filter.toString().trim();
        if(filterText == null||filterText.equals("")) {
            return true;
        }
         
        if(value == null) {
            return false;
        }
         
        return ((Comparable) value).compareTo(Integer.valueOf(filterText)) > 0;
    }
	

	public List<CompraVentaJugadores> getCompraVentaLista() {
		return compraVentaLista;
	}

	public void setCompraVentaLista(List<CompraVentaJugadores> compraVentaLista) {
		this.compraVentaLista = compraVentaLista;
	}
	
	

	public int getCodigoEquipo() {
		return codigoEquipo;
	}

	public void setCodigoEquipo(int codigoEquipo) {
		this.codigoEquipo = codigoEquipo;
	}


	public List<CompraVentaJugadores> getCompraVentaListaFiltered() {
		return compraVentaListaFiltered;
	}


	public void setCompraVentaListaFiltered(List<CompraVentaJugadores> compraVentaListaFiltered) {
		this.compraVentaListaFiltered = compraVentaListaFiltered;
	}


	public CompraVentaJugadores getJugador() {
		return jugador;
	}


	public void setJugador(CompraVentaJugadores jugador) {
		this.jugador = jugador;
	}

}
