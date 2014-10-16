package datatypes;

import java.io.Serializable;
import java.util.Calendar;


public class DatosPartido implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private int codigo;
	
	private Calendar fechaHora;
    
	private EnumPartido estado;

	private int equipoLocalId;
	
	private int equipoVisitanteId;
	
	private int alineacionLocalId;
	
	private int alineacionVisitanteId;

	public DatosPartido() {
		super();
		// TODO Auto-generated constructor stub
	}

	public DatosPartido(int codigo, Calendar fechaHora, EnumPartido estado,
			int equipoLocalId, int equipoVisitanteId, int alineacionLocalId,
			int alineacionVisitanteId) {
		super();
		this.codigo = codigo;
		this.fechaHora = fechaHora;
		this.estado = estado;
		this.equipoLocalId = equipoLocalId;
		this.equipoVisitanteId = equipoVisitanteId;
		this.alineacionLocalId = alineacionLocalId;
		this.alineacionVisitanteId = alineacionVisitanteId;
	}

	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public Calendar getFechaHora() {
		return fechaHora;
	}

	public void setFechaHora(Calendar fechaHora) {
		this.fechaHora = fechaHora;
	}

	public EnumPartido getEstado() {
		return estado;
	}

	public void setEstado(EnumPartido estado) {
		this.estado = estado;
	}

	public int getEquipoLocalId() {
		return equipoLocalId;
	}

	public void setEquipoLocalId(int equipoLocalId) {
		this.equipoLocalId = equipoLocalId;
	}

	public int getEquipoVisitanteId() {
		return equipoVisitanteId;
	}

	public void setEquipoVisitanteId(int equipoVisitanteId) {
		this.equipoVisitanteId = equipoVisitanteId;
	}

	public int getAlineacionLocalId() {
		return alineacionLocalId;
	}

	public void setAlineacionLocalId(int alineacionLocalId) {
		this.alineacionLocalId = alineacionLocalId;
	}

	public int getAlineacionVisitanteId() {
		return alineacionVisitanteId;
	}

	public void setAlineacionVisitanteId(int alineacionVisitanteId) {
		this.alineacionVisitanteId = alineacionVisitanteId;
	}
	
	


}
