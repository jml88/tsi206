package utiles;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.ConverterException;

public class DateConverterDate implements Converter {
	
	public Object getAsObject(FacesContext context, UIComponent component, String value) throws ConverterException {
        try {
            Calendar cal = null;
            if(!value.equals("")){
                cal = new GregorianCalendar();
                SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");
                format.setLenient(false);
                cal.setTime(format.parse(value));
            }           
            return cal.getTime();
        }catch (ParseException exc) {
            throw new ConverterException();
        }catch(NumberFormatException e){
            throw new ConverterException(new FacesMessage("Formato de fecha invalido"));
        }   
        
    }

    public String getAsString(FacesContext context, UIComponent component, Object value) throws ConverterException {
        String result = "";
        
        if (value != null && value instanceof Date){            
            Date date = (Date)value;            
            SimpleDateFormat format = new SimpleDateFormat("dd/MM/yyyy");            
            result = format.format(date.getTime());            
        }
        return result;       
        
    }

}
