/*
 * 
 * 
 */
package com.multimedianetwork.gliding.dao;

import com.multimedianetwork.gliding.model.Member;
import com.multimedianetwork.persistence.hibernate.util.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Camelia Rus
 */
public class MemberDao extends AbstractDao<Member> {

    public MemberDao() {
        super(Member.class);
    }

    @Override
    public Member getById(int id) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            Member object = (Member) session.createCriteria(entityClass).
                    add(Restrictions.eq("id", id))
                    .createAlias("memberType", "mt").uniqueResult();
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
    public List<Member> getList() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Member> list = session.createCriteria(entityClass, "m")
                    .createAlias("memberType", "mt")
                    .addOrder(Order.asc("m.id"))
                    .list();
            tx.commit();
            return list;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return new ArrayList<Member>();
        }
    }

    public List<Member> getOneDayMembers() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Member> list = session.createCriteria(entityClass, "m")
                    .createAlias("memberType", "mt")
                    .add(Restrictions.eq("mt.oneDayMember", true))
                    .addOrder(Order.asc("m.id"))
                    .list();
            tx.commit();
            return list;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return new ArrayList<Member>();
        }
    }

    public List<Member> getPermanentMembers() {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            List<Member> list = session.createCriteria(entityClass, "m")
                    .createAlias("memberType", "mt")
                    .add(Restrictions.eq("mt.oneDayMember", false))
                    .addOrder(Order.asc("m.id"))
                    .list();
            tx.commit();
            return list;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return new ArrayList<Member>();
        }
    }
}
