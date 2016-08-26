/*
 * 
 * 
 */
package com.multimedianetwork.gliding.controller;

import com.multimedianetwork.gliding.managers.FlightManager;
import com.multimedianetwork.gliding.model.util.DayAndFlight;
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
public class DayAndFlightController implements Serializable {

    private List<DayAndFlight> dayAndFlightList;
    private String username = "";

    @PostConstruct
    public void init() {
        dayAndFlightList = new FlightManager().getDaysAndFlightList();
    }

    public DayAndFlightController() {
    }

    public List<DayAndFlight> getDayAndFlightList() {
        return dayAndFlightList;
    }

    public void setDayAndFlightList(List<DayAndFlight> dayAndFlightList) {
        this.dayAndFlightList = dayAndFlightList;
    }
}
