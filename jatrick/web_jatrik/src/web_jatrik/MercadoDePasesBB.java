package web_jatrik;

import java.io.Serializable;
import java.util.Calendar;
import java.util.LinkedList;
import java.util.List;
import java.util.Locale;

import javax.annotation.PostConstruct;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import jugadores.Jugador;
import comunicacion.Comunicacion;
import equipos.Equipo;
import mercadoDePases.CompraVentaJugadores;

@Named("mercadoDePasesBB")
@ViewScoped
public class MercadoDePasesBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private List<CompraVentaJugadores> compraVentaLista;
	
	private int codigoEquipo;
	
	public static final String espacio = " ";
	
	@Inject
	SessionBB sesion;
	
	
	
	@PostConstruct
	public void init(){
		codigoEquipo = sesion.getDatosManager().getCodEquipo();
//		try {
//			compraVentaLista = Comunicacion.getInstance().getIMercadoDePasesControlador().listarJugadoresEnVenta();
//		} catch (NamingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		compraVentaLista = new LinkedList<CompraVentaJugadores>();
		
		CompraVentaJugadores cv = new CompraVentaJugadores(100, null, Calendar.getInstance().getTime(), null, new Equipo(), null, true);
		compraVentaLista.add(cv);
		
		cv = new CompraVentaJugadores(180, null, Calendar.getInstance().getTime(), null, new Equipo(), null, true);
		compraVentaLista.add(cv);
		
		cv = new CompraVentaJugadores(50, null, Calendar.getInstance().getTime(), null, new Equipo(), null, true);
		compraVentaLista.add(cv);
		
		cv = new CompraVentaJugadores(120, null, Calendar.getInstance().getTime(), null, new Equipo(), null, true);
		compraVentaLista.add(cv);
		
		cv = new CompraVentaJugadores(11550, null, Calendar.getInstance().getTime(), null, new Equipo(), null, true);
		compraVentaLista.add(cv);
		
		cv = new CompraVentaJugadores(250, null, Calendar.getInstance().getTime(), null, new Equipo(), null, true);
		compraVentaLista.add(cv);
		
		cv = new CompraVentaJugadores(350, null, Calendar.getInstance().getTime(), null, new Equipo(), null, true);
		compraVentaLista.add(cv);
	}
	
	
	public boolean filter(Object value, Object filter, Locale locale) {
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

}
