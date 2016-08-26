/*
 * 
 * 
 */
package com.multimedianetwork.gliding.dao;

import com.multimedianetwork.gliding.model.FlightTrack;
import com.multimedianetwork.persistence.hibernate.util.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Camelia Rus
 */
public class FlightTrackDao extends AbstractDao<FlightTrack> {

    public FlightTrackDao() {
        super(FlightTrack.class);
    }

    public void insert(List<FlightTrack> flights) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (FlightTrack flightTrack : flights) {
                session.save(flightTrack);
            }
            tx.commit();
        } catch (RuntimeException e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
        }

    }

    public List<FlightTrack> getListBetweenTimeStamps(String gliderName, Date startDate, Date endDate) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<FlightTrack> object = session.createCriteria(entityClass).
                    add(Restrictions.eq("planeName", gliderName)).
                    add(Restrictions.ge("timeStamp", startDate)).
                    add(Restrictions.le("timeStamp", endDate)).
                    list();
            tx.commit();
            return object;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return null;
        }
    }

    public Date getMaxTimeStamp(String gliderName) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Date maxDate = (Date) session.createSQLQuery("Select max(time_stamp) as maxs from flight_track "
                    + "where plane_name = '" + gliderName + "'").addScalar("maxs", StandardBasicTypes.TIMESTAMP).uniqueResult();

            tx.commit();
            return maxDate;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return new Date();
        }
    }
}
