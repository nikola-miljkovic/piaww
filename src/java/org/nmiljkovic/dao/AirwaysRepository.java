/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.nmiljkovic.models.Airways;
import org.nmiljkovic.site.HibernateUtil;

/**
 *
 * @author milja
 */
public class AirwaysRepository {
    Session session = null;
    
    public AirwaysRepository() {
        
    }
    
    public List<Airways> getAirways() {
        this.session = HibernateUtil.getSessionFactory().openSession();
        List<Airways> airwaysList = null;
        try {
            org.hibernate.Transaction tran = session.beginTransaction();
            Query q = session.createQuery("from Airways");
            airwaysList = (List<Airways>)q.list();
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return airwaysList;
    }

    public void updateOrSave(Airways airways) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        try {
            org.hibernate.Transaction tran = session.beginTransaction();
            session.saveOrUpdate(airways);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.getTransaction().commit();
        }
        session.close();
    }

    public Airways getAirwaysById(int id) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        Airways airway = null;
        try {
            org.hibernate.Transaction tran = session.beginTransaction();
            Query q = session.createQuery("from Airways as airway where airway.id = " + id);
            airway = (Airways)q.list().get(0);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return airway;
    }
}
