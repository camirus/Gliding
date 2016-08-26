/*
 * 
 * 
 */
package com.multimedianetwork.gliding.dao;

import com.multimedianetwork.gliding.model.Flight;
import com.multimedianetwork.gliding.model.Member;
import com.multimedianetwork.gliding.model.util.ClubDetails;
import com.multimedianetwork.gliding.model.util.PilotDetails;
import com.multimedianetwork.persistence.hibernate.util.HibernateUtil;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Projections;
import org.hibernate.criterion.Restrictions;
import org.hibernate.type.StandardBasicTypes;

/**
 *
 * @author Camelia Rus
 */
public class FlightDao extends AbstractDao<Flight> {

    public FlightDao() {
        super(Flight.class);
    }

    @Override
    public Flight getById(int id) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Flight object = (Flight) session.createCriteria(entityClass).
                    add(Restrictions.eq("id", id))
                    .createAlias("memberByPilotId", "m1")
                    .createAlias("memberByCopilotId", "m2", CriteriaSpecification.LEFT_JOIN)
                    .createAlias("glider", "g")
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

    @Override
    public List<Flight> getList() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Flight> list = session.createCriteria(entityClass)
                    .createAlias("memberByPilotId", "m1")
                    .createAlias("memberByCopilotId", "m2", CriteriaSpecification.LEFT_JOIN)
                    .createAlias("glider", "g")
                    .addOrder(Order.desc("startTime"))
                    .list();
            tx.commit();
            return list;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return new ArrayList<Flight>();
        }
    }

    public List<Flight> getListInBooklet() {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Flight> list = session.createCriteria(entityClass)
                    .createAlias("memberByPilotId", "m1")
                    .createAlias("memberByCopilotId", "m2", CriteriaSpecification.LEFT_JOIN)
                    .createAlias("glider", "g")
                    .add(Restrictions.eq("inBooklet", true))
                    .addOrder(Order.desc("startTime"))
                    .list();
            tx.commit();
            return list;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return new ArrayList<Flight>();
        }
    }

    public List<Flight> getList(int pilotId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Flight> list = session.createCriteria(entityClass)
                    .createAlias("memberByPilotId", "m1")
                    .createAlias("memberByCopilotId", "m2", CriteriaSpecification.LEFT_JOIN)
                    .createAlias("glider", "g")
                    .add(Restrictions.or(Restrictions.eq("m1.id", pilotId),
                                    Restrictions.eq("m2.id", pilotId)))
                    .addOrder(Order.desc("startTime"))
                    .list();
            tx.commit();
            return list;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return new ArrayList<Flight>();
        }
    }

    public List<Flight> getListInBooklet(int pilotId) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Flight> list = session.createCriteria(entityClass)
                    .createAlias("memberByPilotId", "m1")
                    .createAlias("memberByCopilotId", "m2", CriteriaSpecification.LEFT_JOIN)
                    .createAlias("glider", "g")
                    .add(Restrictions.eq("inBooklet", true))
                    .add(Restrictions.or(Restrictions.eq("m1.id", pilotId),
                                    Restrictions.eq("m2.id", pilotId)))
                    .addOrder(Order.desc("startTime"))
                    .list();
            tx.commit();
            return list;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return new ArrayList<Flight>();
        }
    }

    public int getNumberofFlightsbyPilotIdAndYear(int pilotId, int year) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            int number = ((Number) session.createCriteria(entityClass, "f")
                    .createAlias("memberByPilotId", "m1")
                    .createAlias("memberByCopilotId", "m2", CriteriaSpecification.LEFT_JOIN)
                    .createAlias("glider", "g")
                    .add(Restrictions.or(Restrictions.eq("m1.id", pilotId),
                                    Restrictions.eq("m2.id", pilotId))).
                    add(Restrictions.sqlRestriction("year({alias}.flight_date) = " + year)).
                    setProjection(Projections.rowCount()).uniqueResult()).intValue();
            tx.commit();
            return number;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return 0;
        }
    }

    public int getNumberofFlightsbyPilotIdAndYearInBooklet(int pilotId, int year) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            int number = ((Number) session.createCriteria(entityClass, "f")
                    .createAlias("memberByPilotId", "m1")
                    .createAlias("memberByCopilotId", "m2", CriteriaSpecification.LEFT_JOIN)
                    .createAlias("glider", "g")
                    .add(Restrictions.or(Restrictions.eq("m1.id", pilotId),
                                    Restrictions.eq("m2.id", pilotId))).
                    add(Restrictions.sqlRestriction("year({alias}.flight_date) = " + year)).
                    add(Restrictions.eq("inBooklet", true)).
                    setProjection(Projections.rowCount()).uniqueResult()).intValue();
            tx.commit();
            return number;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return 0;
        }
    }

    public int getTotalDistancebyPilotIdAndYear(int pilotId, int year) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            int number = ((Number) session.createSQLQuery("select ifnull(sum(ifnull(distance,0)),0) from flight "
                    + "where (pilot_id=" + pilotId + " or copilot_id=" + pilotId
                    + ") and year(flight_date) = " + year).uniqueResult()).intValue();
            tx.commit();
            return number;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return 0;
        }
    }

    public int getTotalDistancebyPilotIdAndYearInBooklet(int pilotId, int year) {
        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            int number = ((Number) session.createSQLQuery("select ifnull(sum(ifnull(distance,0)),0) from flight "
                    + "where in_booklet=1 and (pilot_id=" + pilotId + " or copilot_id=" + pilotId
                    + ") and year(flight_date) = " + year).uniqueResult()).intValue();
            tx.commit();
            return number;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return 0;
        }
    }

    public int getTotalDurationbyPilotIdAndYear(int pilotId, int year) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            int number = ((Number) session.createSQLQuery("select ifnull(sum(ifnull(duration_in_minutes,0)),0) from flight "
                    + "where (pilot_id=" + pilotId + " or copilot_id=" + pilotId
                    + ") and year(flight_date) = " + year).uniqueResult()).intValue();
            tx.commit();
            return number;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return 0;
        }
    }

    public int getTotalDurationbyPilotIdAndYearInBooklet(int pilotId, int year) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            int number = ((Number) session.createSQLQuery("select ifnull(sum(ifnull(duration_in_minutes,0)),0) from flight "
                    + "where in_booklet=1 and (pilot_id=" + pilotId + " or copilot_id=" + pilotId
                    + ") and year(flight_date) = " + year).uniqueResult()).intValue();
            tx.commit();
            return number;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return 0;
        }
    }

    public Date getLastFlightDateBeforeDate(Date date) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
            Date lastDate = (Date) session.createSQLQuery("select distinct flight_date from flight "
                    + "where flight_date < '" + dateStr + "' "
                    + "order by flight_date desc limit 1").uniqueResult();
            tx.commit();
            return lastDate;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return new Date();
        }
    }

    public List<PilotDetails> getPilotDetailsList(int year) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            List<PilotDetails> pilotDetailsList = new ArrayList<PilotDetails>();
            List<Object[]> list = session.createSQLQuery("select pid,nr,duration,distance,name from "
                    + "(select pid,count(*) as nr,sum(duration) as duration, sum(distance) as distance  from "
                    + "((select pilot_id as pid, flight_date, "
                    + "ifnull(duration_in_minutes,0) as duration ,"
                    + "ifnull(distance,0) as distance from flight  "
                    + "where year(flight_date)=" + year + ") "
                    + "union all  "
                    + "(select copilot_id as pid, flight_date, "
                    + "ifnull(duration_in_minutes,0) as duration ,"
                    + "ifnull(distance,0) as distance from flight  "
                    + "where copilot_id is not null and year(flight_date)=" + year + ")) as t "
                    + "group by pid) as t2 "
                    + "inner join member m on t2.pid = m.id  "
                    + "order by duration desc").list();
            tx.commit();

            for (Object[] objects : list) {
                PilotDetails pilotDetails = new PilotDetails();
                pilotDetails.setYear(year);

                Member member = new Member();
                member.setId(Integer.parseInt(objects[0].toString()));
                member.setName(objects[4].toString());

                pilotDetails.setMember(member);

                pilotDetails.setNumberOfFlights(Integer.parseInt(objects[1].toString()));
                pilotDetails.setTotalTime(Integer.parseInt(objects[2].toString()));
                pilotDetails.setTotalDistance(Integer.parseInt(objects[3].toString()));

                pilotDetailsList.add(pilotDetails);

            }

            return pilotDetailsList;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return new ArrayList<PilotDetails>();
        }
    }

    public ClubDetails getClubDetailsList(int year) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();

            List<Object[]> list = session.createSQLQuery("select nr,duration,distance from "
                    + "(select count(*) as nr,sum(duration) as duration, sum(distance) as distance  from "
                    + "(select pilot_id as pid, flight_date, "
                    + "ifnull(duration_in_minutes,0) as duration ,"
                    + "ifnull(distance,0) as distance from flight  "
                    + "where year(flight_date)=" + year + ") as t ) as t2  "
            ).list();
            tx.commit();

            ClubDetails clubDetails = new ClubDetails();

            for (Object[] objects : list) {

                clubDetails.setYear(year);

                clubDetails.setNumberOfFlights(Integer.parseInt(objects[0].toString()));
                clubDetails.setTotalTime(Integer.parseInt(objects[1].toString()));
                clubDetails.setTotalDistance(Integer.parseInt(objects[2].toString()));

            }

            return clubDetails;
        } catch (Exception e) {
            Logger.getLogger(FlightDao.class.getName()).log(Level.SEVERE, null, e);
            if (tx != null) {
                tx.rollback();
            }
            return null;
        }
    }

    public Date getFirstFlightDateAfterDate(Date date) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            String dateStr = new SimpleDateFormat("yyyy-MM-dd").format(date);
            Date lastDate = (Date) session.createSQLQuery(""
                    + "select distinct flight_date from flight "
                    + "where flight_date > '" + dateStr + "' "
                    + "order by flight_date asc limit 1").uniqueResult();
            tx.commit();
            return lastDate;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return new Date();
        }
    }

    public List<Flight> getList(Date date) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Flight> list = session.createCriteria(entityClass)
                    .createAlias("memberByPilotId", "m1")
                    .createAlias("memberByCopilotId", "m2", CriteriaSpecification.LEFT_JOIN)
                    .createAlias("glider", "g")
                    .add(Restrictions.eq("flightDate", date))
                    .addOrder(Order.asc("startTime"))
                    .list();
            tx.commit();
            return list;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return new ArrayList<Flight>();
        }
    }

    public List<Flight> getListInBooklet(Date date) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Flight> list = session.createCriteria(entityClass)
                    .createAlias("memberByPilotId", "m1")
                    .createAlias("memberByCopilotId", "m2", CriteriaSpecification.LEFT_JOIN)
                    .createAlias("glider", "g")
                    .add(Restrictions.eq("flightDate", date))
                    .add(Restrictions.eq("inBooklet", true))
                    .addOrder(Order.asc("startTime"))
                    .list();
            tx.commit();
            return list;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return new ArrayList<Flight>();
        }
    }

    public List<Date> getDistinctFlightDatesList() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Date> list = session.createSQLQuery("select distinct "
                    + "flight_date as fc from flight order by flight_date desc")
                    .addScalar("fc", StandardBasicTypes.DATE).list();
            tx.commit();

            return list;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return new ArrayList<Date>();
        }
    }

    public void insert(Flight flight, Member member1, Member member2)
            throws Exception {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            if (member1 != null) {
                session.save(member1);
                flight.setMemberByPilotId(member1);
            }
            if (member2 != null) {
                session.save(member2);
                flight.setMemberByCopilotId(member2);
            }
            flight.setMemberByFirstSeatPayedByMember(flight.getMemberByPilotId());
            if (flight.getMemberByCopilotId() == null) {
                flight.setMemberBySecondSeatPayedByMember(flight.getMemberByPilotId());
            } else {
                flight.setMemberBySecondSeatPayedByMember(flight.getMemberByCopilotId());
            }

            session.save(flight);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
        }

    }

    public void update(Flight flight, Member member1, Member member2)
            throws Exception {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();
        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            if (member1 != null) {
                session.save(member1);
                flight.setMemberByPilotId(member1);
            }
            if (member2 != null) {
                session.save(member2);
                flight.setMemberByCopilotId(member2);
            }
            flight.setMemberByFirstSeatPayedByMember(flight.getMemberByPilotId());
            if (flight.getMemberByCopilotId() == null) {
                flight.setMemberBySecondSeatPayedByMember(flight.getMemberByPilotId());
            } else {
                flight.setMemberBySecondSeatPayedByMember(flight.getMemberByCopilotId());
            }

            session.update(flight);
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            throw e;
        } finally {
        }

    }
}
