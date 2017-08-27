/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.nmiljkovic.models.Aircraft;
import org.nmiljkovic.models.AircraftType;
import org.nmiljkovic.models.Airways;
import org.nmiljkovic.site.HibernateUtil;

/**
 *
 * @author milja
 */
public class AircraftRepository {
    Session session = null;
    
    public AircraftRepository() {
    }
    
    public Aircraft getAircraftById(int id) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        Aircraft aircraft = null;
        try {
            org.hibernate.Transaction tran = session.beginTransaction();
            Query q = session.createQuery("from Aircraft as aircraft where aircraft.id = " + id);
            aircraft = (Aircraft)q.list().get(0);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        session.close();
        return aircraft;
    }

    public List<Aircraft> getAircrafts() {
        this.session = HibernateUtil.getSessionFactory().openSession();
        List<Aircraft> aircrafts = null;
        try {
            org.hibernate.Transaction tran = session.beginTransaction();
            Query q = session.createQuery("from Aircraft");
            aircrafts = (List<Aircraft>)q.list();
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return aircrafts;
    }

    public List<Aircraft> getAircraftForAirlines(Integer id) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        List<Aircraft> aircrafts = null;
        try {
            org.hibernate.Transaction tran = session.beginTransaction();
            Query q = session.createQuery("from Aircraft as aircraft where aircraft.airways = " + id);
            aircrafts = (List<Aircraft>)q.list();
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return aircrafts;
    }

    public void save(AircraftType aircraft) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        try {
            org.hibernate.Transaction tran = session.beginTransaction();
            session.save(aircraft);
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            session.getTransaction().commit();
        }
        session.close();
    }
}
