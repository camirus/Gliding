/*
 * 
 * 
 */
package com.multimedianetwork.gliding.controller;

import com.multimedianetwork.gliding.converters.MemberTypeConverter;
import com.multimedianetwork.gliding.managers.MemberTypeManager;
import com.multimedianetwork.gliding.model.MemberType;
import com.multimedianetwork.webutil.FacesUtil;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Camelia Rus
 */
@ManagedBean
@ViewScoped
public class MemberTypeController implements Serializable {

    private MemberType memberType;
    private List<MemberType> memberTypes;
    private String username = "";

    @PostConstruct
    public void init() {
        memberTypes = new MemberTypeManager().getList();
    }

    public MemberTypeController() {

        if (memberType == null) {
            memberType = (MemberType) FacesUtil.getObjectFromRequestParameter("id", new MemberTypeConverter(), null);
        }
        if (memberType == null) {
            memberType = new MemberType();
        }
    }

    public String insert() {
        try {
            memberType.setName(memberType.getName().toUpperCase());
            new MemberTypeManager().insert(memberType);
            memberType = new MemberType();
            FacesUtil.addInfoMessage("Tipul a fost adaugat");
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Eroare adaugare tip");
            Logger.getLogger(MemberTypeController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String update() {
        try {
            memberType.setName(memberType.getName().toUpperCase());
            new MemberTypeManager().update(memberType);
            FacesUtil.addInfoMessage("Datele au fost actualizate");
            return null;
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Eroare actualizare tip");
            Logger.getLogger(MemberTypeController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String delete(MemberType memberTypeToDelete) {
        try {
            new MemberTypeManager().delete(memberTypeToDelete);
            memberType = new MemberType();

            FacesUtil.addInfoMessage("Tipul a fost sters");
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Eroare stergere tip");
            Logger.getLogger(MemberTypeController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

   public List<MemberType> getMemberTypes() {
        return memberTypes;
    }

    public void setMemberTypes(List<MemberType> memberTypes) {
        this.memberTypes = memberTypes;
    }

    public MemberType getMemberType() {
        return memberType;
    }

    public void setMemberType(MemberType memberType) {
        this.memberType = memberType;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
