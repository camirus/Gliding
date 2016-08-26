/*
 * 
 * 
 */
package com.multimedianetwork.gliding.controller;

import com.multimedianetwork.gliding.managers.FlightManager;
import com.multimedianetwork.gliding.model.Flight;
import com.multimedianetwork.gliding.model.Glider;
import com.multimedianetwork.gliding.model.util.DayAndFlight;
import java.io.Serializable;
import java.util.ArrayList;
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
import org.primefaces.event.RowEditEvent;
import org.primefaces.event.SelectEvent;

/**
 *
 * @author Camelia Rus
 */
@ManagedBean
@ViewScoped
public class NewDayAndFlightController implements Serializable {
    
    protected List<DayAndFlight> dayAndFlightList;
    protected List<DayAndFlight> latestFlightsDayAndFlightList;
    protected List<Flight> flights;
    protected Date selectedDate;
    protected Glider glider;
    
    @PostConstruct
    public void init() {
        
        Date currentDate = new Date();
        selectedDate = currentDate;
        loadFlights(currentDate);
        if ((flights == null) || (flights.isEmpty())) {
            Date previousDate = new FlightManager().getLastFlightDateBeforeDate(currentDate);
            if (previousDate != null) {
                loadFlights(previousDate);
                selectedDate = previousDate;
            }
        }
    }
    
    public NewDayAndFlightController() {
    }
    
    public List<Flight> getFlights() {
        return flights;
    }
    
    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
    
    public Glider getGlider() {
        return glider;
    }
    
    public void setGlider(Glider glider) {
        this.glider = glider;
    }
    
    public Date getSelectedDate() {
        return selectedDate;
    }
    
    public void setSelectedDate(Date selectedDate) {
        this.selectedDate = selectedDate;
    }
    
    public void onDateSelect(SelectEvent event) {
        loadFlights(selectedDate);
    }
    
    public void selectedGliderChanged() {
        loadFlights(selectedDate);
    }
    
    public void decrementDate() {
        selectedDate = new FlightManager().getLastFlightDateBeforeDate(selectedDate);
        if (selectedDate != null) {
            loadFlights(selectedDate);
        } else {
            selectedDate = new Date();
            loadFlights(selectedDate);
        }
    }
    
    public void incrementDate() {
        selectedDate = new FlightManager().getFirstFlightDateAfterDate(selectedDate);
        if (selectedDate != null) {
            loadFlights(selectedDate);
        } else {
            selectedDate = new Date();
            loadFlights(selectedDate);
        }
    }
    
    protected void loadFlights(Date date) {
        
        retrieveFlights(date);
        
        Calendar cal = GregorianCalendar.getInstance();
        
        List<Flight> newFlightsList = new ArrayList<Flight>();
        
        for (Flight flight : flights) {
            
            cal.setTime(flight.getStartTime());
            int hour = cal.get(Calendar.HOUR_OF_DAY);
            int minute = cal.get(Calendar.MINUTE);
            flight.setStartHourAndMinuteInput(hour + ":" + minute);
            
            cal.setTime(flight.getEndTime());
            hour = cal.get(Calendar.HOUR_OF_DAY);
            minute = cal.get(Calendar.MINUTE);
            flight.setEndHourAndMinuteInput(hour + ":" + minute);
            
            if (glider != null) {
                if (flight.getGlider().equals(glider)) {
                    newFlightsList.add(flight);
                }
            } else {
                newFlightsList.add(flight);
            }
        }
        
        flights = newFlightsList;
    }
    
    public void addNewFlight() {
        Flight newFlight = new Flight();
        newFlight.setStartTime(selectedDate);
        newFlight.setEndTime(selectedDate);
        newFlight.setFlightDate(selectedDate);
        newFlight.setDurationInMinutes(0);
        newFlight.setInBooklet(true);
        if (glider!=null){
            newFlight.setGlider(glider);
        }
        flights.add(newFlight);
    }
    
    protected void insertFlight(Flight flight) {
        
        flight.setAddDate(new Date());
        new FlightManager().insertFromHourAndMinuteInput(flight);
        
    }
    
    protected void updateFlight(Flight flight) {
        
        new FlightManager().updateFromHourAndMinuteInput(flight);
        
    }
    
    public void deleteFlight(Flight flight) {
        
        try {
            new FlightManager().delete(flight);
            flights.remove(flight);
            FacesMessage msg = new FacesMessage("Zborul a fost sters");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        } catch (Exception ex) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Eroare stergere zbor", "Eroare stergere zbor");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            Logger.getLogger(FlightController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    public void onRowEdit(RowEditEvent event) {
        Flight flight = ((Flight) event.getObject());
        
        if (flight.getId() != null && flight.getId() != 0) {
            try {
                updateFlight(flight);
                FacesMessage msg = new FacesMessage("Zborul a fost salvat");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception ex) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Eroare salvare zbor", "Eroare salvare zbor");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                Logger.getLogger(FlightController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            try {
                insertFlight(flight);
                FacesMessage msg = new FacesMessage("Zborul a fost adaugat");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            } catch (Exception ex) {
                FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Eroare adaugare zbor", "Eroare adaugare zbor");
                FacesContext.getCurrentInstance().addMessage(null, msg);
                Logger.getLogger(FlightController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    public void onRowCancel(RowEditEvent event) {
        Flight flight = ((Flight) event.getObject());
        
        if (!(flight.getId() != null && flight.getId() != 0)) {
            try {
                flights.remove(flight);
            } catch (Exception ex) {
                Logger.getLogger(FlightController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    protected Date decrementDate(Date date) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, -1);
        return cal.getTime();
    }
    
    protected Date incrementDate(Date date) {
        Calendar cal = GregorianCalendar.getInstance();
        cal.setTime(date);
        cal.add(Calendar.DAY_OF_YEAR, 1);
        return cal.getTime();
    }
    
    protected void retrieveFlights(Date date){
        flights = new FlightManager().getList(date);
    }
}
