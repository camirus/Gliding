/*
 * 
 * 
 */
package com.multimedianetwork.gliding.controller;

import com.multimedianetwork.gliding.managers.FlightJournalManager;
import com.multimedianetwork.gliding.model.FlightJournal;
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
public class FlightJournalController implements Serializable {

    private FlightJournal flightJournal;
    private List<FlightJournal> flightJournals;
    private String username = "";

    @PostConstruct
    public void init() {
        flightJournals = new FlightJournalManager().getList();
    }

    public FlightJournalController() {

    }

    public List<FlightJournal> getFlightJournals() {
        return flightJournals;
    }

    public void setFlightJournals(List<FlightJournal> flightJournals) {
        this.flightJournals = flightJournals;
    }

    public FlightJournal getFlightJournal() {
        return flightJournal;
    }

    public void setFlightJournal(FlightJournal flightJournal) {
        this.flightJournal = flightJournal;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
}
