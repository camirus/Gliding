/*
 * 
 * 
 */
package com.multimedianetwork.gliding.managers.gps;

import com.multimedianetwork.gliding.dao.gps.FlightDataImporterDao;
import com.multimedianetwork.gliding.model.FlightJournal;
import com.multimedianetwork.gliding.model.FlightTrack;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Camelia Rus
 */
public class FlightDataImporterManager {

    private FlightDataImporterDao dao = new FlightDataImporterDao();

    public List<FlightJournal> getFlightJournalToImport(Date maxSamplingStart, String gliderName) {
        return dao.getFlightJournalToImport(maxSamplingStart, gliderName);
    }

    public List<FlightTrack> getFlightTracksToImport(Date maxTimeStamp, String gliderName) {
        return dao.getFlightTracksToImport(maxTimeStamp, gliderName);
    }
}
