/*
 * 
 * 
 */
package com.multimedianetwork.gliding.controller.member;

import com.multimedianetwork.gliding.managers.FlightManager;
import com.multimedianetwork.gliding.model.Flight;
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
public class FlightMemberController implements Serializable {

    private List<Flight> flights;

    @PostConstruct
    public void init() {
        flights = new FlightManager().getListInBooklet();
    }

    public FlightMemberController() {
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

}
