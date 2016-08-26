/*
 * 
 * 
 */
package com.multimedianetwork.gliding.controller;

import com.multimedianetwork.gliding.converters.MemberConverter;
import com.multimedianetwork.gliding.managers.FlightManager;
import com.multimedianetwork.gliding.managers.MemberTypeManager;
import com.multimedianetwork.gliding.model.Flight;
import com.multimedianetwork.gliding.model.Member;
import com.multimedianetwork.gliding.model.MemberType;
import com.multimedianetwork.gliding.model.util.PilotDetails;
import com.multimedianetwork.webutil.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

/**
 *
 * @author Camelia Rus
 */
@ManagedBean
@ViewScoped
public class ViewMemberController implements Serializable {

    private Member member;
    private List<MemberType> memberTypes = new ArrayList<MemberType>();
    private String username = "";
    private List<Flight> latestFlights = new ArrayList<Flight>();
    private PilotDetails detailsForLastYear;
    private PilotDetails detailsForCurrentYear;

    @PostConstruct
    public void init() {
        memberTypes = new MemberTypeManager().getList();
    }

    public ViewMemberController() {

        if (member == null) {
            member = (Member) FacesUtil.getObjectFromRequestParameter("id", new MemberConverter(), null);
            latestFlights = new FlightManager().getList(member.getId());
            detailsForCurrentYear = new FlightManager().getDetailsForCurrentYear(member);
            detailsForLastYear = new FlightManager().getDetailsForLastYear(member);
        }
        if (member == null) {
            member = new Member();
        }
    }

    public List<Flight> getLatestFlights() {
        return latestFlights;
    }

    public void setLatestFlights(List<Flight> latestFlights) {
        this.latestFlights = latestFlights;
    }

    public List<MemberType> getMemberTypes() {
        return memberTypes;
    }

    public void setMemberTypes(List<MemberType> memberTypes) {
        this.memberTypes = memberTypes;
    }

    public PilotDetails getDetailsForLastYear() {
        return detailsForLastYear;
    }

    public void setDetailsForLastYear(PilotDetails detailsForLastYear) {
        this.detailsForLastYear = detailsForLastYear;
    }

    public PilotDetails getDetailsForCurrentYear() {
        return detailsForCurrentYear;
    }

    public void setDetailsForCurrentYear(PilotDetails detailsForCurrentYear) {
        this.detailsForCurrentYear = detailsForCurrentYear;
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

    private String memberName;

    public String getMemberName() {
        return memberName;
    }

    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

}
