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
import org.nmiljkovic.models.Airport;
import org.nmiljkovic.models.Airways;
import org.nmiljkovic.site.HibernateUtil;

/**
 *
 * @author milja
 */
public class AirportRepository {
    Session session = null;
    
    public AirportRepository() {
        
    }
    
    public Airport getAirportById(String id) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        Airport airport = null;
        try {
            org.hibernate.Transaction tran = session.beginTransaction();
            Query q = session.createQuery("from Airport as airport where airport.id = '" + id + "'");
            airport = (Airport)q.list().get(0);
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return airport;
    }

    public Airport createAirport(Airport airport) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        try {
            org.hibernate.Transaction tran = session.beginTransaction();
            session.save(airport);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.getTransaction().commit();
        }
        session.close();
        return airport;
    }

    public List<Airport> getAirports() {
        this.session = HibernateUtil.getSessionFactory().openSession();
        List<Airport> airports = null;
        try {
            org.hibernate.Transaction tran = session.beginTransaction();
            Query q = session.createQuery("from Airport");
            airports = (List<Airport>)q.list();
            tran.commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        session.close();
        return airports;
    }
}
