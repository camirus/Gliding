/*
 * 
 * 
 */
package com.multimedianetwork.gliding.core;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author user
 */
public class FlightJournalFetcherTest {
    
    public FlightJournalFetcherTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of fetchFlightJournalData method, of class FlightJournalFetcher.
     */
    //@Test
    public void testFetchFlightJournalData() {
        System.out.println("fetchFlightJournalData");
        String gliderName = "HA-5043";
        FlightDataFetcher instance = new FlightDataFetcher();
        instance.fetchFlightTracksData(gliderName);
    }
}