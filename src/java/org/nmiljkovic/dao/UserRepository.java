/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.dao;

import org.hibernate.Hibernate;
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
}
