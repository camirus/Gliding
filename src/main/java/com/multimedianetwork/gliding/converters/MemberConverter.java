/*
 * 
 * 
 */
package com.multimedianetwork.gliding.converters;

import com.multimedianetwork.gliding.managers.MemberManager;
import com.multimedianetwork.gliding.model.Member;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author Camelia Rus
 */
@FacesConverter("memberConverter")
public class MemberConverter implements Converter {

    @Override
    public Object getAsObject(FacesContext facesContext, UIComponent component, String string) {
        if (string == null || string.length() == 0) {
            return null;
        }
        Member member = null;

        try {
            Integer id = new Integer(string);
            member = new MemberManager().getById(id);
        } catch (Exception ex) {
        }

        return member;
    }

    @Override
    public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
        if (object == null) {
            return null;
        }
        if (object instanceof Member) {
            Member o = (Member) object;
            return o.getId() == null ? "" : String.valueOf(o.getId().intValue());
        } else if (object instanceof String) {
            return (String) object;
        } else {
            throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: com.multimedianetwork.cashflow.model.Member");
        }
    }
}
