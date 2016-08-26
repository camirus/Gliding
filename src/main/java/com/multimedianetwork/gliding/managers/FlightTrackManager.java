/*
 * 
 * 
 */
package com.multimedianetwork.gliding.managers;

import com.multimedianetwork.gliding.model.FlightTrack;
import com.multimedianetwork.gliding.dao.FlightTrackDao;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Camelia Rus
 */
public class FlightTrackManager extends AbstractManager<FlightTrack> {

    public FlightTrackManager() {
        super(FlightTrack.class);
        dao = new FlightTrackDao();
    }

    public void insert(List<FlightTrack> flights) {
        ((FlightTrackDao) dao).insert(flights);
    }

    public Date getMaxTimeStamp(String gliderName) {
        return ((FlightTrackDao) dao).getMaxTimeStamp(gliderName);
    }

    public List<FlightTrack> getListBetweenTimeStamps(String gliderName, Date startDate, Date endDate) {
        return ((FlightTrackDao) dao).getListBetweenTimeStamps(gliderName, startDate, endDate);
    }
}
