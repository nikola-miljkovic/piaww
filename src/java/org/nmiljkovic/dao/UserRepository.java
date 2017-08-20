/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.dao;

import org.hibernate.Query;
import org.hibernate.Session;
import org.nmiljkovic.models.User;
import org.nmiljkovic.site.HibernateUtil;

/**
 *
 * @author milja
 */
public class UserRepository {
    Session session = null;

    public UserRepository() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public User createUser(User user) {
        try {
            session.beginTransaction();
            session.save(user);
        } catch (Exception exc) {
            
        } finally {
            session.getTransaction().commit();
        }
        
        return user;
    }

    public User checkUser(String mUsername, String mPassword) {
        User user = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from User as user where user.username = '" + mUsername + "' and user.password = '" + mPassword + "'");
            user = (User)q.list().get(0);
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            session.getTransaction().commit();
        }
        
        return user;
    }
}
