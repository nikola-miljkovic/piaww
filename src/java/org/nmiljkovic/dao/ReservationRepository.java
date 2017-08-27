/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.dao;

import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.nmiljkovic.models.ReservationRequest;
import org.nmiljkovic.site.AirlinesAdminView;
import org.nmiljkovic.site.HibernateUtil;

/**
 *
 * @author milja
 */
public class ReservationRepository {
    Session session = null;
    
    public ReservationRepository() {
    }

    public ReservationRequest createReservation(ReservationRequest reservation) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(reservation);
        } catch (Exception exc) {
            
        } finally {
            session.getTransaction().commit();
        }
        this.session.close();
        
        return reservation;
    }
    
    public List<ReservationRequest> getReservationsForAirways(int airwaysId) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        List<ReservationRequest> reservations = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from ReservationRequest where " 
                    + "owner = " + airwaysId);
            reservations = (List<ReservationRequest>)q.list();
        } catch (Exception exc) {
            
        } finally {
            session.getTransaction().commit();
        }
        this.session.close();
        
        return reservations;
    }
    
    public void approveReservation(ReservationRequest reservation) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            reservation.setStatus(AirlinesAdminView.ReservationStatusReserved);
            reservation.getAircraft().setAirways(reservation.getAirwaysByRequester());
            
            session.saveOrUpdate(reservation);
            session.saveOrUpdate(reservation.getAircraft());
        } catch (Exception exc) {
            
        } finally {
            session.getTransaction().commit();
        }
        this.session.close();
    }
    
    public void finishReservation(ReservationRequest reservation) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            reservation.setStatus(AirlinesAdminView.ReservationStatusDone);
            reservation.getAircraft().setAirways(reservation.getAirwaysByOwner());
            
            session.saveOrUpdate(reservation);
            session.saveOrUpdate(reservation.getAircraft());
        } catch (Exception exc) {
            
        } finally {
            session.getTransaction().commit();
        }
        this.session.close();
    }
    
    public void forbidReservation(ReservationRequest reservation) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            reservation.setStatus(AirlinesAdminView.ReservationStatusNoAction);
            
            session.saveOrUpdate(reservation);
        } catch (Exception exc) {
            
        } finally {
            session.getTransaction().commit();
        }
        this.session.close();
    }
}
