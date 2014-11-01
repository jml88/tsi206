package utiles;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;

import datatypes.EnumEntrenamiento;

public class EntrenamientoConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		// TODO Auto-generated method stub
		
		if (value == "Ataque"){
			return EnumEntrenamiento.ATAQUE;
		}
		else if(value == "Defensa"){
			return EnumEntrenamiento.DEFENSA;
		}
		else if(value == "Portero"){
			return EnumEntrenamiento.PORTERO;
		}
		else if(value == "Tecnica"){
			return EnumEntrenamiento.TECNICA;
		}
		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		// TODO Auto-generated method stub
		EnumEntrenamiento entrenamiento = (EnumEntrenamiento)value;
		switch (entrenamiento) {
		case ATAQUE:
			return "Ataque";
		case DEFENSA:
			return "Defensa";
		case PORTERO:
			return "Portero";
		case TECNICA:
			return "Tecnica";
			

		}
		
		return null;
	}

}
