/*
 * 
 * 
 */
package com.multimedianetwork.gliding.core;

import com.multimedianetwork.gliding.managers.FlightJournalManager;
import com.multimedianetwork.gliding.managers.FlightTrackManager;
import com.multimedianetwork.gliding.managers.gps.FlightDataImporterManager;
import com.multimedianetwork.gliding.model.FlightJournal;
import com.multimedianetwork.gliding.model.FlightTrack;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Camelia Rus
 */
public class FlightDataFetcher {

    /*
     * Fetch flight journal for gliderName 
     */
    public synchronized void fetchFlightJournalData(String gliderName) {

        if (gliderName != null) {
            /*
             * Get the max sampling start date indicating the last date that was updated. 
             */
            Date maxSamplingStartDate = new FlightJournalManager().getMaxSamplingStartDate(gliderName);
            if (maxSamplingStartDate == null) {

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, 2000);
                maxSamplingStartDate = cal.getTime();
            }
            try {
                /*
                 * Get new records from gspgateserver
                 */
                List<FlightJournal> flightJournalRecords = new FlightDataImporterManager().
                        getFlightJournalToImport(maxSamplingStartDate, gliderName);
                new FlightJournalManager().insert(flightJournalRecords);
            } catch (Exception ex) {
                Logger.getLogger(FlightDataFetcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    /*
     * Fetch flight tracks data for gliderName 
     */
    public synchronized void fetchFlightTracksData(String gliderName) {

        if (gliderName != null) {
            /*
             * Get the max sampling start date indicating the last date that was updated. 
             */
            Date maxTimeStamp = new FlightTrackManager().getMaxTimeStamp(gliderName);
            if (maxTimeStamp == null) {

                Calendar cal = Calendar.getInstance();
                cal.set(Calendar.YEAR, 2000);
                maxTimeStamp = cal.getTime();
            }
            try {
                /*
                 * Get new records from gspgateserver
                 */
                List<FlightTrack> flightTracksRecords = new FlightDataImporterManager().
                        getFlightTracksToImport(maxTimeStamp, gliderName);
                new FlightTrackManager().insert(flightTracksRecords);
            } catch (Exception ex) {
                Logger.getLogger(FlightDataFetcher.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
