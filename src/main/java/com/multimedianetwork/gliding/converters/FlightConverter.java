/*
 * 
 * 
 */
package com.multimedianetwork.gliding.converters;

import com.multimedianetwork.gliding.managers.FlightManager;
import com.multimedianetwork.gliding.model.Flight;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Camelia Rus
 */
@FacesConverter("flightConverter")
public class FlightConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Flight flight = null;

        try {
            Integer id = new Integer(string);
            flight = new FlightManager().getById(id);
        } catch (Exception ex) {
        }

        return flight;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Flight) {
            Flight o = (Flight) object;
            return o.getId() == null ? "" : String.valueOf(o.getId().intValue());
        } else if (object instanceof String) {
            return (String) object;
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: com.multimedianetwork.cashflow.model.Flight");
        }
    }
}
