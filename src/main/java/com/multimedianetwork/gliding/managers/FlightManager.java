/*
 * 
 * 
 */
package com.multimedianetwork.gliding.managers;

import com.multimedianetwork.gliding.model.Flight;
import com.multimedianetwork.gliding.dao.FlightDao;
import com.multimedianetwork.gliding.model.Member;
import com.multimedianetwork.gliding.model.util.ClubDetails;
import com.multimedianetwork.gliding.model.util.DayAndFlight;
import com.multimedianetwork.gliding.model.util.PilotDetails;
import com.multimedianetwork.gliding.utils.DateUtils;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Camelia Rus
 */
public class FlightManager extends AbstractManager<Flight> {

    public FlightManager() {
        super(Flight.class);
        dao = new FlightDao();
    }

    public List<Flight> getListInBooklet() {
        return ((FlightDao) dao).getListInBooklet();
    }

    public List<Flight> getList(Date date) {
        return ((FlightDao) dao).getList(date);
    }

    public List<Flight> getListInBooklet(Date date) {
        return ((FlightDao) dao).getListInBooklet(date);
    }

    public List<Date> getDistinctFlightDatesList() {
        return ((FlightDao) dao).getDistinctFlightDatesList();
    }

    public List<DayAndFlight> getDaysAndFlightList() {

        List<DayAndFlight> daysAndFlightList = new ArrayList<DayAndFlight>();

        List<Date> distinctDays = getDistinctFlightDatesList();
        for (Date date : distinctDays) {
            List<Flight> flights = getList(date);
            DayAndFlight dayAndFlight = new DayAndFlight(date, flights);
            daysAndFlightList.add(dayAndFlight);
        }

        return daysAndFlightList;
    }

    public List<DayAndFlight> getDaysAndFlightListInBooklet() {

        List<DayAndFlight> daysAndFlightList = new ArrayList<DayAndFlight>();

        List<Date> distinctDays = getDistinctFlightDatesList();
        for (Date date : distinctDays) {
            List<Flight> flights = getListInBooklet(date);
            DayAndFlight dayAndFlight = new DayAndFlight(date, flights);
            daysAndFlightList.add(dayAndFlight);
        }

        return daysAndFlightList;
    }

    public Date getLastFlightDateBeforeDate(Date date) {
        return ((FlightDao) dao).getLastFlightDateBeforeDate(date);
    }

    public Date getFirstFlightDateAfterDate(Date date) {
        return ((FlightDao) dao).getFirstFlightDateAfterDate(date);
    }

    @Override
    public void insert(Flight flight) {

        Member newMember1 = null;
        Member newMember2 = null;

        if (flight.isPilotNew()) {
            newMember1 = new Member();
            newMember1.setAddDate(new Date());
            newMember1.setAddedBy("AUTOMAT");
            newMember1.setName(flight.getPilotName());
            newMember1.setTelephone(flight.getPilotTelephone());
            newMember1.setEmail(flight.getPilotEmail());
            newMember1.setCnp(flight.getPilotCNP());
            newMember1.setHasLicense(flight.isPilotHasLicense());
            newMember1.setMemberType(flight.getPilotMemberType());
        }

        if (flight.isCopilotNew()) {
            newMember2 = new Member();
            newMember2.setAddDate(new Date());
            newMember2.setAddedBy("AUTOMAT");
            newMember2.setName(flight.getCopilotName());
            newMember2.setTelephone(flight.getCopilotTelephone());
            newMember2.setEmail(flight.getCopilotEmail());
            newMember2.setCnp(flight.getCopilotCNP());
            newMember2.setHasLicense(flight.isCopilotHasLicense());
            newMember2.setMemberType(flight.getCopilotMemberType());
        }

        flight.setStartTime(DateUtils.getDate(flight.getFlightDate(), flight.getStartHour(), flight.getStartMinute()));
        flight.setEndTime(DateUtils.getDate(flight.getFlightDate(), flight.getEndHour(), flight.getEndMinute()));

        int diffInMinutes = (int) ((flight.getEndTime().getTime() - flight.getStartTime().getTime())
                / (1000 * 60));
        flight.setDurationInMinutes(diffInMinutes);

        try {
            ((FlightDao) dao).insert(flight, newMember1, newMember2);
        } catch (Exception ex) {
            Logger.getLogger(FlightManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update(Flight flight) {

        Member newMember1 = null;
        Member newMember2 = null;

        if (flight.isPilotNew()) {
            newMember1 = new Member();
            newMember1.setAddDate(new Date());
            newMember1.setAddedBy("AUTOMAT");
            newMember1.setName(flight.getPilotName());
            newMember1.setTelephone(flight.getPilotTelephone());
            newMember1.setEmail(flight.getPilotEmail());
            newMember1.setCnp(flight.getPilotCNP());
            newMember1.setHasLicense(flight.isPilotHasLicense());
            newMember1.setMemberType(flight.getPilotMemberType());
        }

        if (flight.isCopilotNew()) {
            newMember2 = new Member();
            newMember2.setAddDate(new Date());
            newMember2.setAddedBy("AUTOMAT");
            newMember2.setName(flight.getCopilotName());
            newMember2.setTelephone(flight.getCopilotTelephone());
            newMember2.setEmail(flight.getCopilotEmail());
            newMember2.setCnp(flight.getCopilotCNP());
            newMember2.setHasLicense(flight.isCopilotHasLicense());
            newMember2.setMemberType(flight.getCopilotMemberType());
        }

        flight.setStartTime(DateUtils.getDate(flight.getFlightDate(), flight.getStartHour(), flight.getStartMinute()));
        flight.setEndTime(DateUtils.getDate(flight.getFlightDate(), flight.getEndHour(), flight.getEndMinute()));

        int diffInMinutes = (int) ((flight.getEndTime().getTime() - flight.getStartTime().getTime())
                / (1000 * 60));
        flight.setDurationInMinutes(diffInMinutes);

        try {
            ((FlightDao) dao).update(flight, newMember1, newMember2);
        } catch (Exception ex) {
            Logger.getLogger(FlightManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void insertFromHourAndMinuteInput(Flight flight) {
        setHourAndMinutes(flight);
        insert(flight);
    }

    public void updateFromHourAndMinuteInput(Flight flight) {
        setHourAndMinutes(flight);
        update(flight);
    }

    private void setHourAndMinutes(Flight flight) {

        String startHourAndMinuteInput = flight.getStartHourAndMinuteInput();
        String[] splits = startHourAndMinuteInput.split(":");
        if (splits.length == 2) {
            String hourStr = splits[0];
            String minutesStr = splits[1];

            flight.setStartHour(Integer.parseInt(hourStr));
            flight.setStartMinute(Integer.parseInt(minutesStr));
        }

        String endHourAndMinuteInput = flight.getEndHourAndMinuteInput();
        splits = endHourAndMinuteInput.split(":");
        if (splits.length == 2) {
            String hourStr = splits[0];
            String minutesStr = splits[1];

            flight.setEndHour(Integer.parseInt(hourStr));
            flight.setEndMinute(Integer.parseInt(minutesStr));
        }
    }

    public List<Flight> getList(int pilotId) {
        return ((FlightDao) dao).getList(pilotId);
    }

    public List<Flight> getListInBooklet(int pilotId) {
        return ((FlightDao) dao).getListInBooklet(pilotId);
    }

    public int getNumberofFlightsbyPilotIdAndYear(int pilotId, int year) {
        return ((FlightDao) dao).getNumberofFlightsbyPilotIdAndYear(pilotId, year);
    }

    public int getNumberofFlightsbyPilotIdAndYearInBooklet(int pilotId, int year) {
        return ((FlightDao) dao).getNumberofFlightsbyPilotIdAndYearInBooklet(pilotId, year);
    }

    public int getTotalDistancebyPilotIdAndYear(int pilotId, int year) {
        return ((FlightDao) dao).getTotalDistancebyPilotIdAndYear(pilotId, year);
    }

    public int getTotalDistancebyPilotIdAndYearInBooklet(int pilotId, int year) {
        return ((FlightDao) dao).getTotalDistancebyPilotIdAndYearInBooklet(pilotId, year);
    }

    public int getTotalDurationbyPilotIdAndYear(int pilotId, int year) {
        return ((FlightDao) dao).getTotalDurationbyPilotIdAndYear(pilotId, year);
    }

    public int getTotalDurationbyPilotIdAndYearInBooklet(int pilotId, int year) {
        return ((FlightDao) dao).getTotalDurationbyPilotIdAndYearInBooklet(pilotId, year);
    }

    public PilotDetails getDetailsForCurrentYear(Member member) {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

        PilotDetails pilotDetails = getDetails(member, year);
        return pilotDetails;
    }

    public PilotDetails getDetailsForLastYear(Member member) {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        int year = cal.get(Calendar.YEAR);

        PilotDetails pilotDetails = getDetails(member, year);
        return pilotDetails;
    }

    public ClubDetails getClubDetailsForCurrentYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

        ClubDetails clubDetails = getClubDetails(year);
        return clubDetails;
    }

    public ClubDetails getClubDetailsForLastYear() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        int year = cal.get(Calendar.YEAR);

        ClubDetails clubDetails = getClubDetails(year);
        return clubDetails;
    }

    public PilotDetails getDetails(Member member, int year) {
        PilotDetails pilotDetails = new PilotDetails();
        pilotDetails.setMember(member);
        pilotDetails.setYear(year);
        pilotDetails.setNumberOfFlights(getNumberofFlightsbyPilotIdAndYear(member.getId(), year));
        pilotDetails.setTotalDistance(getTotalDistancebyPilotIdAndYear(member.getId(), year));
        pilotDetails.setTotalTime(getTotalDurationbyPilotIdAndYear(member.getId(), year));
        return pilotDetails;
    }

    public PilotDetails getDetailsInBooklet(Member member, int year) {
        PilotDetails pilotDetails = new PilotDetails();
        pilotDetails.setMember(member);
        pilotDetails.setYear(year);
        pilotDetails.setNumberOfFlights(getNumberofFlightsbyPilotIdAndYearInBooklet(member.getId(), year));
        pilotDetails.setTotalDistance(getTotalDistancebyPilotIdAndYearInBooklet(member.getId(), year));
        pilotDetails.setTotalTime(getTotalDurationbyPilotIdAndYearInBooklet(member.getId(), year));
        return pilotDetails;
    }

    public List<PilotDetails> getPilotDetailsList(int year) {
        return ((FlightDao) dao).getPilotDetailsList(year);
    }

    public ClubDetails getClubDetails(int year) {
        return ((FlightDao) dao).getClubDetailsList(year);
    }

    public List<PilotDetails> getPilotDetailsListForCurrentYear() {
        Calendar cal = Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);

        return ((FlightDao) dao).getPilotDetailsList(year);
    }

    public List<PilotDetails> getPilotDetailsListForLastYear() {
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.YEAR, -1);
        int year = cal.get(Calendar.YEAR);

        return ((FlightDao) dao).getPilotDetailsList(year);
    }
}
