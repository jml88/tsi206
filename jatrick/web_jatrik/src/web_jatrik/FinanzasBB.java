package web_jatrik;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.inject.Named;
import javax.naming.NamingException;

import datatypes.EnumTipoTransaccion;
import equipos.Equipo;
import excepciones.NoExisteEquipoExcepcion;
import finanzas.Finanzas;

@Named("finanzasBB")
@ViewScoped
public class FinanzasBB implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	@Inject
	private SessionBB session;
	
	private int capitalActual;
	
	private int capitalCalculado;
	
	//positivos
	private int publicidad;
	
	private int ventasJugadores;
	
	private int socios;
	
	private int entradaPartidos;
	
	//negativos
	private int sueldos;
	
	private int pagoJuveniles;

	private int compraJugadores;
	
	private int codigoEquipo;
	
	private int premio;
	
	private Date fechaMinima;
	
	private List<Finanzas> finanzas;
	
	private Equipo equipo;
	
	@PostConstruct
	public void init(){
		try {
			codigoEquipo = session.getDatosManager().getCodEquipo();
			fechaMinima = comunicacion.Comunicacion.getInstance().getConfiguracionControlador().getConfiguracion().getFechaArranqueCampeonato();
			publicidad = comunicacion.Comunicacion.getInstance().getIFinanzasControlador().obtenerGastos(codigoEquipo, fechaMinima, EnumTipoTransaccion.PUBLICIDAD);
			ventasJugadores = comunicacion.Comunicacion.getInstance().getIFinanzasControlador().obtenerGastos(codigoEquipo, fechaMinima, EnumTipoTransaccion.VENTA_JUGADORES);
			socios = comunicacion.Comunicacion.getInstance().getIFinanzasControlador().obtenerGastos(codigoEquipo, fechaMinima, EnumTipoTransaccion.SOCIOS);
			entradaPartidos = comunicacion.Comunicacion.getInstance().getIFinanzasControlador().obtenerGastos(codigoEquipo, fechaMinima, EnumTipoTransaccion.ENTRADA_PARTIDO);
			sueldos = comunicacion.Comunicacion.getInstance().getIFinanzasControlador().obtenerGastos(codigoEquipo, fechaMinima, EnumTipoTransaccion.SUELDOS_JUGADORES);
			pagoJuveniles = comunicacion.Comunicacion.getInstance().getIFinanzasControlador().obtenerGastos(codigoEquipo, fechaMinima, EnumTipoTransaccion.PAGO_JUVENILES);
			compraJugadores = comunicacion.Comunicacion.getInstance().getIFinanzasControlador().obtenerGastos(codigoEquipo, fechaMinima, EnumTipoTransaccion.COMPRA_JUGADORES);
			premio = comunicacion.Comunicacion.getInstance().getIFinanzasControlador().obtenerGastos(codigoEquipo, fechaMinima, EnumTipoTransaccion.PREMIO);
			finanzas = comunicacion.Comunicacion.getInstance().getIFinanzasControlador().obtenerTransaccionesDesde(codigoEquipo, fechaMinima);
			capitalCalculado = comunicacion.Comunicacion.getInstance().getIFinanzasControlador().obtenerCapitalEquipo(codigoEquipo, getFechaMinima());
			capitalActual = comunicacion.Comunicacion.getInstance().getIEquipoControlador().getEquipo(codigoEquipo).getCapital();
			
		} catch (NamingException | NoExisteEquipoExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}
	}
	
	public void modificarFecha(){
		codigoEquipo = session.getDatosManager().getCodEquipo();
		try {
			fechaMinima = comunicacion.Comunicacion.getInstance().getConfiguracionControlador().getConfiguracion().getFechaArranqueCampeonato();
			publicidad = comunicacion.Comunicacion.getInstance().getIFinanzasControlador().obtenerGastos(codigoEquipo, fechaMinima, EnumTipoTransaccion.PUBLICIDAD);
			ventasJugadores = comunicacion.Comunicacion.getInstance().getIFinanzasControlador().obtenerGastos(codigoEquipo, fechaMinima, EnumTipoTransaccion.VENTA_JUGADORES);
			socios = comunicacion.Comunicacion.getInstance().getIFinanzasControlador().obtenerGastos(codigoEquipo, fechaMinima, EnumTipoTransaccion.SOCIOS);
			entradaPartidos = comunicacion.Comunicacion.getInstance().getIFinanzasControlador().obtenerGastos(codigoEquipo, fechaMinima, EnumTipoTransaccion.ENTRADA_PARTIDO);
			sueldos = comunicacion.Comunicacion.getInstance().getIFinanzasControlador().obtenerGastos(codigoEquipo, fechaMinima, EnumTipoTransaccion.SUELDOS_JUGADORES);
			pagoJuveniles = comunicacion.Comunicacion.getInstance().getIFinanzasControlador().obtenerGastos(codigoEquipo, fechaMinima, EnumTipoTransaccion.PAGO_JUVENILES);
			compraJugadores = comunicacion.Comunicacion.getInstance().getIFinanzasControlador().obtenerGastos(codigoEquipo, fechaMinima, EnumTipoTransaccion.COMPRA_JUGADORES);
			premio = comunicacion.Comunicacion.getInstance().getIFinanzasControlador().obtenerGastos(codigoEquipo, fechaMinima, EnumTipoTransaccion.PREMIO);
			finanzas = comunicacion.Comunicacion.getInstance().getIFinanzasControlador().obtenerTransaccionesDesde(codigoEquipo, fechaMinima);
			capitalCalculado = comunicacion.Comunicacion.getInstance().getIFinanzasControlador().obtenerCapitalEquipo(codigoEquipo, getFechaMinima());
			capitalActual = comunicacion.Comunicacion.getInstance().getIEquipoControlador().getEquipo(codigoEquipo).getCapital();
		} catch (NamingException | NoExisteEquipoExcepcion e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error!", e.getMessage()));
		}
		
	}
	
	public String tipoTransaccionToString(EnumTipoTransaccion tipoTransaccion){
		return tipoTransaccion.toString();
	}
	
	public int capitalSumado(){
		return publicidad + ventasJugadores + socios + entradaPartidos;
	}

	public int getCapitalActual() {
		return capitalActual;
	}

	public void setCapitalActual(int capitalActual) {
		this.capitalActual = capitalActual;
	}

	public int getPublicidad() {
		return publicidad;
	}

	public void setPublicidad(int publicidad) {
		this.publicidad = publicidad;
	}

	public int getVentasJugadoresUltimoMes() {
		return ventasJugadores;
	}

	public void setVentasJugadoresUltimoMes(int ventasJugadoresUltimoMes) {
		this.ventasJugadores = ventasJugadoresUltimoMes;
	}

	public int getSocios() {
		return socios;
	}

	public void setSocios(int socios) {
		this.socios = socios;
	}

	public int getEntradaUltimos4Partidos() {
		return entradaPartidos;
	}

	public void setEntradaUltimos4Partidos(int entradaUltimos4Partidos) {
		this.entradaPartidos = entradaUltimos4Partidos;
	}

	public int getSueldos() {
		return sueldos;
	}

	public void setSueldos(int sueldos) {
		this.sueldos = sueldos;
	}

	public int getPagoJuveniles() {
		return pagoJuveniles;
	}

	public void setPagoJuveniles(int pagoJuveniles) {
		this.pagoJuveniles = pagoJuveniles;
	}

	public int getCompraJugadoresUltimoMes() {
		return compraJugadores;
	}

	public void setCompraJugadoresUltimoMes(int compraJugadoresUltimoMes) {
		this.compraJugadores = compraJugadoresUltimoMes;
	}

	public List<Finanzas> getFinanzas() {
		return finanzas;
	}

	public void setFinanzas(List<Finanzas> finanzas) {
		this.finanzas = finanzas;
	}

	public Date getFechaMinima() {
		return fechaMinima;
	}

	public void setFechaMinima(Date fechaMinima) {
		this.fechaMinima = fechaMinima;
	}

	public int getPremio() {
		return premio;
	}

	public void setPremio(int premio) {
		this.premio = premio;
	}

	public int getCapitalCalculado() {
		return capitalCalculado;
	}

	public void setCapitalCalculado(int capitalCalculado) {
		this.capitalCalculado = capitalCalculado;
	}
	
	
}
