/*
 * 
 * 
 */
package com.multimedianetwork.gliding.dao.gps;

import com.multimedianetwork.gliding.model.FlightJournal;
import com.multimedianetwork.gliding.model.FlightTrack;
import com.multimedianetwork.persistence.hibernate.util.RemoteHibernateUtil;
import java.math.BigInteger;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;

/**
 *
 * @author Camelia Rus
 */
public class FlightDataImporterDao {

    private static final String REPORT_QUERY =
            "SELECT * FROM ("
            + "SELECT "
            + "Users.Name as carName,"
            + "DATE_ADD(EventProvider01.SamplingStart, INTERVAL 3 HOUR),"
            + "DATE_ADD(EventProvider01.SamplingEnd, INTERVAL 3 HOUR),"
            + "EventProvider01.RuleName,"
            + "EventProvider01.EventArgument,"
            + "ROUND(EventProvider01.DistanceGps),"
            + "EventProvider01.StartAddress as type,"
            + "/* Duration */"
            + "TIME_TO_SEC(TIMEDIFF(EventProvider01.SamplingEnd, EventProvider01.SamplingStart)) AS "
            + "Duration,"
            + "/* Idle time minus ovelapping time */"
            + "ROUND(AVG("
            + "EventProvider01.DistanceGps /TIME_TO_SEC(TIMEDIFF(EventProvider01.SamplingEnd, EventProvider01.SamplingStart))"
            + "*3.6) ,2) AS AvgSpeed, ROUND(MaxSpeed*3.6,2) as MaxSpeed "
            + ""
            + "FROM "
            + "(SELECT data_time_stamp_start AS 'SamplingStart', data_time_stamp_end AS 'SamplingEnd', "
            + "int_01 AS 'UserID', int_02 AS 'EventRuleID', "
            + "txt_01 AS 'RuleName', txt_02 AS 'EventArgument', dbl_01 AS 'DistanceGps', dbl_02 AS "
            + "'DistanceOdo', txt_03 AS 'StartAddress', "
            + "txt_04 AS 'StopAddress', txt_05 AS 'EventState', dbl_03 AS 'MaxSpeed' , "
            + "report_data_store_id "
            + "FROM reportv3_data_store "
            + "WHERE (date_add(data_time_stamp_start, INTERVAL 3 HOUR) > '%1%')) "
            + "as EventProvider01 "
            + ""
            + "JOIN ("
            + "SELECT DISTINCT u.user_id AS UserID, u.username as Username, u.name as Name, "
            + "u.description as Description, u.Surname as Surname, "
            + "email as Email, driver_id as IButton FROM users u JOIN user_groups ug ON u.user_id = "
            + "ug.user_id JOIN groups g ON g.group_id = ug.group_id) as "
            + "Users ON Users.UserID = EventProvider01.UserID "
            + "LEFT JOIN "
            + "(SELECT data_time_stamp_start AS 'SamplingStart', data_time_stamp_end AS 'SamplingEnd', "
            + "int_01 AS 'UserID', txt_01 AS 'StartAddress', "
            + "txt_02 AS 'StopAddress', dbl_01 AS 'DistanceGps', dbl_02 AS 'DistanceOdo',  dbl_04 AS 'AvgSpeed', "
            + "dbl_05 AS 'Fuel', txt_03 AS 'FatPointState' FROM reportv3_data_store WHERE "
            + "report_data_provider_id = 1 and (date_add(data_time_stamp_start, INTERVAL 3 HOUR) > '%1%') AND "
            + "(true_break = 1 OR (true_break = 0 AND data_time_stamp_start != data_time_stamp_end))) "
            + "Distance01 "
            + "ON Distance01.UserID = EventProvider01.UserID "
            + "AND Distance01.SamplingEnd >= EventProvider01.SamplingStart "
            + "AND Distance01.SamplingStart <= EventProvider01.SamplingEnd "
            + ""
            + "WHERE "
            + "(EventState != 'Off' or EventState is null) and Users.`name` = '%2%' "
            + ""
            + "GROUP BY "
            + "DATE(DATE_ADD(EventProvider01.SamplingStart, INTERVAL 0 SECOND)), "
            + "Users.Name, Users.Username, Users.UserID, EventProvider01.SamplingStart, EventProvider01.SamplingEnd,"
            + "EventProvider01.EventRuleID, EventProvider01.RuleName, EventProvider01.EventArgument, EventProvider01.DistanceGps,"
            + "EventProvider01.DistanceOdo, EventProvider01.StartAddress, EventProvider01.StopAddress, EventProvider01.EventState "
            + ""
            + "ORDER BY "
            + "EventProvider01.UserID, EventProvider01.SamplingStart, EventProvider01.SamplingEnd "
            + ") as aa where Duration > 0 and type='run' and MaxSpeed > 80";
    private static final String TRACKS_QUERY = "select longitude,latitude,altitude,ground_speed, "
            + "date_add(time_stamp,INTERVAL 3 HOUR) as dt from track_data where track_info_id in "
            + "(select track_info_id from track_info where owner_id in "
            + "(select u.user_id from users u where name='%2%') "
            + ") and valid=1 and date_add(time_stamp,INTERVAL 3 HOUR) > '%1%'";

    public List<FlightTrack> getFlightTracksToImport(Date maxTimeStamp, String gliderName) {

        List<FlightTrack> flightTracks = new ArrayList<FlightTrack>();

        Session session = RemoteHibernateUtil.getSessionFactory().getCurrentSession();

        try {
            session.getTransaction().begin();

            String selectString = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            selectString = TRACKS_QUERY.replace("%1%", sdf.format(maxTimeStamp));
            selectString = selectString.replace("%2%", gliderName);

            int timeZoneOffsetInHours = getTimeZoneOffsetInHours();
            if (timeZoneOffsetInHours == 2) {
                selectString = selectString.replaceAll("INTERVAL 3 HOUR", "INTERVAL 2 HOUR");
            }

            List<Object[]> objList = session.createSQLQuery(selectString).list();

            for (Object[] jourObj : objList) {
                FlightTrack flightTrack = new FlightTrack();
                flightTrack.setLongitude(((Double) jourObj[0]).doubleValue());
                flightTrack.setLatitude(((Double) jourObj[1]).doubleValue());
                flightTrack.setAltitude(((Double) jourObj[2]).doubleValue());
                flightTrack.setGroundSpeed(((Double) jourObj[3]).doubleValue());
                flightTrack.setTimeStamp((Date) jourObj[4]);
                flightTrack.setPlaneName(gliderName);

                flightTracks.add(flightTrack);
            }


            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            Logger.getLogger(FlightDataImporterDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flightTracks;
    }

    public List<FlightJournal> getFlightJournalToImport(Date maxSamplingStart, String gliderName) {

        List<FlightJournal> flightJournalList = new ArrayList<FlightJournal>();

        Session session = RemoteHibernateUtil.getSessionFactory().getCurrentSession();

        try {
            session.getTransaction().begin();

            String selectString = "";
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

            selectString = REPORT_QUERY.replace("%1%", sdf.format(maxSamplingStart));
            selectString = selectString.replace("%2%", gliderName);

            int timeZoneOffsetInHours = getTimeZoneOffsetInHours();
            if (timeZoneOffsetInHours == 2) {
                selectString = selectString.replaceAll("INTERVAL 3 HOUR", "INTERVAL 2 HOUR");
            }

            List<Object[]> objList = session.createSQLQuery(selectString).list();

            for (Object[] jourObj : objList) {
                FlightJournal flightJournal = new FlightJournal();
                flightJournal.setPlaneName((String) jourObj[0]);
                flightJournal.setSamplingStart((Date) jourObj[1]);
                flightJournal.setSamplingEnd((Date) jourObj[2]);
                flightJournal.setStartAddress((String) jourObj[3]);
                flightJournal.setStopAddress((String) jourObj[4]);
                flightJournal.setDistance(((Double) jourObj[5]).floatValue());
                flightJournal.setType((String) jourObj[6]);
                flightJournal.setDuration(((BigInteger) jourObj[7]).longValue());
                flightJournal.setAvgSpeed(((Double) jourObj[8]).floatValue());
                flightJournal.setMaxSpeed(((Double) jourObj[9]).floatValue());
                flightJournalList.add(flightJournal);
            }


            session.getTransaction().commit();
        } catch (Exception ex) {
            session.getTransaction().rollback();
            Logger.getLogger(FlightDataImporterDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return flightJournalList;
    }

    private int getTimeZoneOffsetInHours() {
        Calendar c = Calendar.getInstance();
        System.out.println("current: " + c.getTime());

        TimeZone z = c.getTimeZone();
        int offset = z.getRawOffset();
        if (z.inDaylightTime(new Date())) {
            offset = offset + z.getDSTSavings();
        }
        int offsetHrs = offset / 1000 / 60 / 60;
        int offsetMins = offset / 1000 / 60 % 60;

        return offsetHrs;
    }
}
