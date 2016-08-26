/*
 * 
 * 
 */
package com.multimedianetwork.gliding.dao;

import com.multimedianetwork.gliding.model.Flight;
import com.multimedianetwork.gliding.model.User;
import com.multimedianetwork.persistence.hibernate.util.HibernateUtil;
import java.util.ArrayList;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 *
 * @author Camelia Rus
 */
public class UserDao extends AbstractDao<User> {

    public UserDao() {
        super(User.class);
    }

    public User getByUsernameAndPassword(String username, String password) {

        Session session = HibernateUtil.getSessionFactory().getCurrentSession();

        Transaction tx = null;
        try {
            tx = session.beginTransaction();
            User object = (User) session.createCriteria(entityClass).
                    add(Restrictions.or(Restrictions.eq("username", username), Restrictions.eq("email", username))).
                    add(Restrictions.eq("password", password)).uniqueResult();
            tx.commit();
            return object;
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            return null;
        }

    }
}
