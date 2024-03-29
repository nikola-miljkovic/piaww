/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.dao;

import java.util.ArrayList;
import java.util.List;
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
        //this.session = HibernateUtil.getSessionFactory().openSession();
    }
    
    public User createUser(User user) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(user);
        } catch (Exception exc) {
            
        } finally {
            session.getTransaction().commit();
        }
        this.session.close();
        
        return user;
    }

    public User checkUser(String mUsername, String mPassword) {
        this.session = HibernateUtil.getSessionFactory().openSession();
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
        this.session.close();
        
        return user;
    }

    public List<User> getNonFlaggedUsers() {
        this.session = HibernateUtil.getSessionFactory().openSession();
        List<User> users = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from User as user where user.flag = 0");
            users = (List<User>)q.list();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            session.getTransaction().commit();
        }
        this.session.close();
        
        return users;
    }

    public void updateUser(User user) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.saveOrUpdate(user);
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            session.getTransaction().commit();
        }
        this.session.close();
    }

    public List<User> getEmployeeWithFlag(int flightAirlineSelected, int flag) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        List<User> users = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from User as user where user.flag = " 
                    + flag + " and user.airways = " + flightAirlineSelected
            );
            users = (List<User>)q.list();
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            session.getTransaction().commit();
        }
        this.session.close();
        
        return users;
    }

    public List<User> getCrew(String[] usernames) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        List<User> userList = null;
        try {
            org.hibernate.Transaction tran = session.beginTransaction();
            List<String> userEntries = new ArrayList<>();
            for (String username : usernames) {
                userEntries.add("user.username = '" + username + "'");
            }
            String queryString = "from User as user where " + String.join(" or ", userEntries);
            Query q = session.createQuery(queryString);
            userList = (List<User>)q.list();
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        this.session.close();
        
        return userList;
    }
}
