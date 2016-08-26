/*
 * 
 * 
 */
package com.multimedianetwork.gliding.controller;

import com.multimedianetwork.gliding.managers.FlightManager;
import com.multimedianetwork.gliding.model.util.ClubDetails;
import com.multimedianetwork.gliding.model.util.PilotDetails;
import java.io.Serializable;
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
public class PilotDetailsListController implements Serializable {

    private List<PilotDetails> pilotDetailsListForCurrentYear;
    private List<PilotDetails> pilotDetailsListForLastYear;
    private ClubDetails clubDetailsForCurrentYear;
    private ClubDetails clubDetailsForLastYear;
    private String username = "";

    @PostConstruct
    public void init() {
        pilotDetailsListForCurrentYear = new FlightManager().getPilotDetailsListForCurrentYear();
        pilotDetailsListForLastYear = new FlightManager().getPilotDetailsListForLastYear();
        clubDetailsForCurrentYear = new FlightManager().getClubDetailsForCurrentYear();
        clubDetailsForLastYear = new FlightManager().getClubDetailsForLastYear();
    }

    public PilotDetailsListController() {

    }

    public List<PilotDetails> getPilotDetailsListForCurrentYear() {
        return pilotDetailsListForCurrentYear;
    }

    public void setPilotDetailsListForCurrentYear(List<PilotDetails> pilotDetailsListForCurrentYear) {
        this.pilotDetailsListForCurrentYear = pilotDetailsListForCurrentYear;
    }

    public List<PilotDetails> getPilotDetailsListForLastYear() {
        return pilotDetailsListForLastYear;
    }

    public void setPilotDetailsListForLastYear(List<PilotDetails> pilotDetailsListForLastYear) {
        this.pilotDetailsListForLastYear = pilotDetailsListForLastYear;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public ClubDetails getClubDetailsForCurrentYear() {
        return clubDetailsForCurrentYear;
    }

    public void setClubDetailsForCurrentYear(ClubDetails clubDetailsForCurrentYear) {
        this.clubDetailsForCurrentYear = clubDetailsForCurrentYear;
    }

    public ClubDetails getClubDetailsForLastYear() {
        return clubDetailsForLastYear;
    }

    public void setClubDetailsForLastYear(ClubDetails clubDetailsForLastYear) {
        this.clubDetailsForLastYear = clubDetailsForLastYear;
    }

}
