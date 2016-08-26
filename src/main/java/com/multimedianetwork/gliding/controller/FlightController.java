/*
 * 
 * 
 */
package com.multimedianetwork.gliding.controller;

import com.multimedianetwork.gliding.converters.FlightConverter;
import com.multimedianetwork.gliding.managers.FlightManager;
import com.multimedianetwork.gliding.managers.GliderManager;
import com.multimedianetwork.gliding.managers.MemberManager;
import com.multimedianetwork.gliding.model.Flight;
import com.multimedianetwork.gliding.model.Glider;
import com.multimedianetwork.gliding.model.Member;
import com.multimedianetwork.webutil.FacesUtil;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
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
public class FlightController implements Serializable {

    private Flight flight;
    private List<Flight> flights;
    private List<Member> members;
    private List<Glider> gliders;
    private List<Flight> filteredFlights;
    private String username = "";
    private List<String> takeOffMethods = new ArrayList<String>(
            Arrays.asList("Mosor", "Avion"));

    @PostConstruct
    public void init() {
        flights = new FlightManager().getList();
        latestFlights = flights;
        members = new MemberManager().getList();
        gliders = new GliderManager().getList();
        filteredFlights = flights;
    }

    public FlightController() {

        if (flight == null) {
            loadFlight();
        }
        if (flight == null) {
            flight = new Flight();
            Calendar cal = GregorianCalendar.getInstance();
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            
            flight.setStartHour(hour);
            flight.setStartMinute(minute);
            flight.setEndHour(hour);
            flight.setEndMinute(minute);
        }
    }

    public String insert() {
        try {
            flight.setAddDate(new Date());
            flight.setAddedBy(username);
            new FlightManager().insert(flight);
            flight = new Flight();
            FacesUtil.addInfoMessage("Zborul a fost adaugat");
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Eroare adaugare zbor");
            Logger.getLogger(FlightController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String update() {
        try {
            new FlightManager().update(flight);
            FacesUtil.addInfoMessage("Datele au fost actualizate");
            return null;
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Eroare actualizare zbor");
            Logger.getLogger(FlightController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
    public String updateMobile() {
        try {
            new FlightManager().update(flight);
            FacesUtil.addInfoMessage("Datele au fost actualizate");
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Eroare actualizare zbor");
            Logger.getLogger(FlightController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public String delete(Flight flightToDelete) {
        try {
            new FlightManager().delete(flightToDelete);
            flight = new Flight();

            FacesUtil.addInfoMessage("Zborul a fost sters");
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Eroare stergere zbor");
            Logger.getLogger(FlightController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }
    
     public String deleteCurrent() {
        try {
            Flight flightToDelete = flight;
            new FlightManager().delete(flightToDelete);
            flight = new Flight();

            FacesUtil.addInfoMessage("Zborul a fost sters");
            return "list?faces-redirect=true";
        } catch (Exception ex) {
            FacesUtil.addErrorMessage("Eroare stergere zbor");
            Logger.getLogger(FlightController.class.getName()).log(Level.SEVERE, null, ex);
            return null;
        }
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void hasLicenseCheck() {
    }

    public void onItemSelect(SelectEvent event) {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Item Selected", event.getObject().toString()));
    }
    private String flightName;

    public String getFlightName() {
        return flightName;
    }

    public void setFlightName(String flightName) {
        this.flightName = flightName;
    }

    public List<Flight> getFilteredFlights() {
        return filteredFlights;
    }

    public void setFilteredFlights(List<Flight> filteredFlights) {
        this.filteredFlights = filteredFlights;
    }
    private List<Flight> latestFlights;

    public List<Flight> getLatestFlights() {
        return latestFlights;
    }

    public void setLatestFlights(List<Flight> latestFlights) {
        this.latestFlights = latestFlights;
    }

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Glider> getGliders() {
        return gliders;
    }

    public void setGliders(List<Glider> gliders) {
        this.gliders = gliders;
    }

    public List<String> getTakeOffMethods() {
        return takeOffMethods;
    }

    public void setTakeOffMethods(List<String> takeOffMethods) {
        this.takeOffMethods = takeOffMethods;
    }

    private void loadFlight() {
        flight = (Flight) FacesUtil.getObjectFromRequestParameter("id", new FlightConverter(), null);

        if (flight != null) {

            Calendar cal = GregorianCalendar.getInstance();

            cal.setTime(flight.getStartTime());
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            flight.setStartHour(hour);
            flight.setStartMinute(minute);

            cal.setTime(flight.getEndTime());
            hour = cal.get(Calendar.HOUR_OF_DAY);
            minute = cal.get(Calendar.MINUTE);
            flight.setEndHour(hour);
            flight.setEndMinute(minute);

        }
    }
}
