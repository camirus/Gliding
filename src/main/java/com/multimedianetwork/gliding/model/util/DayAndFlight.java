/*
 * 
 * 
 */
package com.multimedianetwork.gliding.model.util;

import com.multimedianetwork.gliding.model.Flight;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Camelia Rus
 */
public class DayAndFlight implements Serializable{

    private Date day;
    private List<Flight> flights;

    public DayAndFlight() {
    }

    public DayAndFlight(Date day, List<Flight> flights) {
        this.day = day;
        this.flights = flights;
    }

    public Date getDay() {
        return day;
    }

    public void setDay(Date day) {
        this.day = day;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
}
