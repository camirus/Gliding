/*
 * 
 * 
 */
package com.multimedianetwork.gliding.controller;

import com.multimedianetwork.gliding.converters.MemberConverter;
import com.multimedianetwork.gliding.managers.MemberManager;
import com.multimedianetwork.gliding.managers.MemberTypeManager;
import com.multimedianetwork.gliding.managers.UserManager;
import com.multimedianetwork.gliding.model.Member;
import com.multimedianetwork.gliding.model.MemberType;
import com.multimedianetwork.gliding.model.User;
import com.multimedianetwork.webutil.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Camelia Rus
 */
@ManagedBean
@ViewScoped
public class MemberController implements Serializable {

    private Member member;
    private List<Member> members;
    private List<Member> filteredMembers;
    private List<MemberType> memberTypes = new ArrayList<MemberType>();
    private String username = "";

    @PostConstruct
    public void init() {
        members = new MemberManager().getList();
        filteredMembers = members;
        memberTypes = new MemberTypeManager().getList();
    }

    public MemberController() {

        if (member == null) {
            member = (Member) FacesUtil.getObjectFromRequestParameter("id", new MemberConverter(), null);
        }
        if (member == null) {
            member = new Member();
        }
    }

    public String insert() {
        try {
            member.setAddDate(new Date());
            member.setAddedBy(username);
            member.setLastUpdateDate(new Date());
            member.setLastUpdatedBy(username);
            new MemberManager().insert(member);
            member = new Member();
            FacesUtil.addInfoMessage("Membrul a fost adaugat");
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Eroare adaugare membru");
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String update() {
        try {
            member.setLastUpdateDate(new Date());
            member.setLastUpdatedBy(username);
            new MemberManager().update(member);
            FacesUtil.addInfoMessage("Datele au fost actualizate");
            return null;
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Eroare actualizare membru");
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String createUser() {
        try {
            if (member.getUser() == null) {

                User user = new UserManager().createUserForMember(member);
                member.setUser(user);

                member.setLastUpdateDate(new Date());
                member.setLastUpdatedBy(username);
                new MemberManager().update(member);
                FacesUtil.addInfoMessage("Utilizatorul a fost generat, parola: "
                        + user.getOriginalPassword());
            }
            return null;
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Eroare actualizare membru "+ex.getMessage());
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String delete(Member memberToDelete) {
        try {
            new MemberManager().delete(memberToDelete);
            member = new Member();

            FacesUtil.addInfoMessage("Membrul a fost sters");
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Eroare stergere membru");
            Logger.getLogger(MemberController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<MemberType> getMemberTypes() {
        return memberTypes;
    }

    public void setMemberTypes(List<MemberType> memberTypes) {
        this.memberTypes = memberTypes;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void hasLicenseCheck() {
    }

    public List<Member> completeMembers(String query) {

        filteredMembers = new ArrayList<Member>();

        for (Member cmember : members) {
            if (cmember.getName().toLowerCase().contains(query.toLowerCase())) {
                filteredMembers.add(cmember);
            }
        }

        return filteredMembers;
    }

    public void onItemSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
    }
    private String memberName;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public List<String> completeText(String query) {
        List<String> filteredMembers = new ArrayList<String>();

        for (Member cmember : members) {
            if (cmember.getName().toLowerCase().startsWith(query.toLowerCase())) {
                filteredMembers.add(cmember.getName());
            }
        }

        return filteredMembers;
    }

    public List<Member> getFilteredMembers() {
        return filteredMembers;
    }

    public void setFilteredMembers(List<Member> filteredMembers) {
        this.filteredMembers = filteredMembers;
    }

    public List<Member> getPermanentMembers() {
        return new MemberManager().getPermanentMembers();
    }

    public List<Member> getOneDayMembers() {
        return new MemberManager().getOneDayMembers();
    }

}
