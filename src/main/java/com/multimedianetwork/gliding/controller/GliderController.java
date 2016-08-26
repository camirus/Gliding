/*
 * 
 * 
 */
package com.multimedianetwork.gliding.controller;

import com.multimedianetwork.gliding.converters.GliderConverter;
import com.multimedianetwork.gliding.managers.GliderManager;
import com.multimedianetwork.gliding.model.Glider;
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
public class GliderController implements Serializable {

    private Glider glider;
    private List<Glider> gliders;
    private String username = "";

    @PostConstruct
    public void init() {
        gliders = new GliderManager().getList();
    }

    public GliderController() {

        if (glider == null) {
            glider = (Glider) FacesUtil.getObjectFromRequestParameter("id", new GliderConverter(), null);
        }
        if (glider == null) {
            glider = new Glider();
        }
    }

    public String insert() {
        try {
            glider.setAddDate(new Date());
            glider.setAddedBy(username);
            new GliderManager().insert(glider);
            glider = new Glider();
            FacesUtil.addInfoMessage("Planorul a fost adaugat");
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Eroare adaugare planor");
            Logger.getLogger(GliderController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String update() {
        try {
            new GliderManager().update(glider);
            FacesUtil.addInfoMessage("Datele au fost actualizate");
            return null;
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Eroare actualizare planor");
            Logger.getLogger(GliderController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String delete(Glider gliderToDelete) {
        try {
            new GliderManager().delete(gliderToDelete);
            glider = new Glider();

            FacesUtil.addInfoMessage("Planorul a fost sters");
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Eroare stergere planor");
            Logger.getLogger(GliderController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

   public List<Glider> getGliders() {
        return gliders;
    }

    public void setGliders(List<Glider> gliders) {
        this.gliders = gliders;
    }

    public Glider getGlider() {
        return glider;
    }

    public void setGlider(Glider glider) {
        this.glider = glider;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


}
