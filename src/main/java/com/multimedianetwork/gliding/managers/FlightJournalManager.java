/*
 * 
 * 
 */
package com.multimedianetwork.gliding.managers;

import com.multimedianetwork.gliding.model.FlightJournal;
import com.multimedianetwork.gliding.dao.FlightJournalDao;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Camelia Rus
 */
public class FlightJournalManager extends AbstractManager<FlightJournal> {

    public FlightJournalManager() {
        super(FlightJournal.class);
        dao = new FlightJournalDao();
    }

    public void insert(List<FlightJournal> flights) {
        ((FlightJournalDao) dao).insert(flights);
    }

    public Date getMaxSamplingStartDate(String gliderName) {
        return ((FlightJournalDao) dao).getMaxSamplingStartDate(gliderName);
    }
}
