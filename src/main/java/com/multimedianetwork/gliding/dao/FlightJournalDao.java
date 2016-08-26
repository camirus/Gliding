/*
 * 
 * 
 */
package com.multimedianetwork.gliding.dao;

import com.multimedianetwork.gliding.model.FlightJournal;
import com.multimedianetwork.persistence.hibernate.util.HibernateUtil;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Camelia Rus
 */
public class FlightJournalDao extends AbstractDao<FlightJournal> {

    public FlightJournalDao() {
        super(FlightJournal.class);
    }

    @Override
    public FlightJournal getById(int id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            FlightJournal object = (FlightJournal) session.createCriteria(entityClass).
                    add(Restrictions.eq("id", id))
                    .uniqueResult();
            tx.commit();
            return object;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return null;
        }
    }

    public Date getMaxSamplingStartDate(String gliderName) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();

            Date maxDate = (Date) session.createSQLQuery("Select max(sampling_start) as maxs from flight_journal "
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

    @Override
    public List<FlightJournal> getList() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;

        try {
            tx = session.beginTransaction();
            List<FlightJournal> list = session.createCriteria(entityClass)
                    .addOrder(Order.desc("samplingStart")).list();
            tx.commit();
            return list;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return new ArrayList<FlightJournal>();
        }
    }

    public void insert(List<FlightJournal> flights) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            for (FlightJournal flightJournal : flights) {
                session.save(flightJournal);
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
}
