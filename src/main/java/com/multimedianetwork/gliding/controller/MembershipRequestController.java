/*
 * 
 * 
 */
package com.multimedianetwork.gliding.controller;

import com.multimedianetwork.gliding.core.generators.pdf.MembershipRequestPDFGenerator;
import com.multimedianetwork.gliding.managers.MembershipRequestManager;
import com.multimedianetwork.gliding.model.MembershipRequest;
import com.multimedianetwork.gliding.utils.ImageScaler;
import com.multimedianetwork.webutil.FacesUtil;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.ConfigurableNavigationHandler;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.imageio.ImageIO;
import static org.hibernate.criterion.Restrictions.in;
import org.primefaces.event.FileUploadEvent;

/**
 *
 * @author Camelia Rus
 */
@ManagedBean
@ViewScoped
public class MembershipRequestController implements Serializable {

    private MembershipRequest membershipRequest;
    private List<MembershipRequest> membershipRequests;
    private String username = "";
    private boolean accept = false;
    private String membershipType = "mf";

    @PostConstruct
    public void init() {
        membershipRequests = new MembershipRequestManager().getList();
    }

    public MembershipRequestController() {

        if (membershipRequest == null) {
            membershipRequest = new MembershipRequest();
        }
    }

    public String insert() {

        if (accept) {
            try {
                membershipRequest.setAddDate(new Date());
                membershipRequest.setTemporaryMembership(
                        !"mf".equals(this.membershipType));
                new MembershipRequestManager().insert(membershipRequest);

                MembershipRequestPDFGenerator.getInstance().
                        generateMembershipRequestPDF(membershipRequest);
                int generatedRequestId = membershipRequest.getId();
                membershipRequest = new MembershipRequest();

                boolean asAttachment = false;
                String downloadedFileName = "cerere.pdf";
                FacesUtil.writePDFToOutput("/template/request/",
                        "req" + generatedRequestId + ".pdf",
                        asAttachment, downloadedFileName);

            } catch (Exception ex) {
                FacesUtil.addErrorMessage("Eroare adaugare cerere");
                Logger.getLogger(MembershipRequestController.class.getName()).
                        log(Level.SEVERE, null, ex);
                return null;
            }
        } else {
            FacesUtil.addErrorMessage("Nu ati bifat acordul!");
        }
        return null;
    }

    public void upload(FileUploadEvent event) {

        try {

            InputStream inputStream = event.getFile().getInputstream();
            Image image = ImageIO.read(inputStream);
            ImageScaler imageScaler = new ImageScaler(image);
            imageScaler.createScaledImage(240, 320, ImageScaler.ScaleType.FILL);
            byte[] imgBytes = imageScaler.
                    scaledImageToByteArray(ImageScaler.ImageType.IMAGE_JPG);
            membershipRequest.setPhoto(imgBytes);

            FacesUtil.addInfoMessage("Imaginea a fost salvata");

        } catch (IOException ex) {
            Logger.getLogger(MembershipRequestController.class.getName()).
                    log(Level.SEVERE, null, ex);
            FacesUtil.addErrorMessage("Eroare salvare imagine");
        }

    }

    public String update() {
        try {
            new MembershipRequestManager().update(membershipRequest);
            FacesUtil.addInfoMessage("Datele au fost actualizate");
            return null;
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Eroare actualizare planor");
            Logger.getLogger(MembershipRequestController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String delete(MembershipRequest membershipRequestToDelete) {

        try {
            new MembershipRequestManager().delete(membershipRequestToDelete);
            membershipRequest = new MembershipRequest();

            FacesUtil.addInfoMessage("Planorul a fost sters");
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Eroare stergere planor");
            Logger.getLogger(MembershipRequestController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<MembershipRequest> getMembershipRequests() {
        return membershipRequests;
    }

    public void setMembershipRequests(List<MembershipRequest> membershipRequests) {
        this.membershipRequests = membershipRequests;
    }

    public MembershipRequest getMembershipRequest() {
        return membershipRequest;
    }

    public void setMembershipRequest(MembershipRequest membershipRequest) {
        this.membershipRequest = membershipRequest;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public boolean isAccept() {
        return accept;
    }

    public void setAccept(boolean accept) {
        this.accept = accept;
    }

    public String getMembershipType() {
        return membershipType;
    }

    public void setMembershipType(String membershipType) {
        this.membershipType = membershipType;
    }

}
