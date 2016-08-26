/*
 * 
 * 
 */
package com.multimedianetwork.gliding.converters;

import com.multimedianetwork.gliding.managers.MemberTypeManager;
import com.multimedianetwork.gliding.model.MemberType;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Camelia Rus
 */
@FacesConverter("memberTypeConverter")
public class MemberTypeConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        MemberType memberType = null;

        try {
            Integer id = new Integer(string);
            memberType = new MemberTypeManager().getById(id);
        } catch (Exception ex) {
        }

        return memberType;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof MemberType) {
            MemberType o = (MemberType) object;
            return o.getId() == null ? "" : String.valueOf(o.getId().intValue());
        } else if (object instanceof String) {
            return (String) object;
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: com.multimedianetwork.cashflow.model.MemberType");
        }
    }
}
