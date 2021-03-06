package datatypes;

import java.io.Serializable;

public class DatosEquipo implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private int codigo;
	private String nombre;
	private int codPais;
	private boolean bot;
	private Double altura;
	
	public DatosEquipo(int codigo, String nombre, int codPais, boolean bot, Double altura) {
		super();
		this.codigo = codigo;
		this.nombre = nombre;
		this.codPais = codPais;
		this.bot = bot;
		this.altura = altura;
	}
	
	public int getCodigo() {
		return codigo;
	}

	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public int getCodPais() {
		return codPais;
	}

	public void setCodPais(int codPais) {
		this.codPais = codPais;
	}

	public boolean isBot() {
		return bot;
	}

	public void setBot(boolean bot) {
		this.bot = bot;
	}

	public Double getAltura() {
		return altura;
	}

	public void setAltura(Double altura) {
		this.altura = altura;
	}
}

