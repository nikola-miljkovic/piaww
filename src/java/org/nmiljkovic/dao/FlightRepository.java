/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.dao;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.nmiljkovic.models.Aircraft;
import org.nmiljkovic.models.Airways;
import org.nmiljkovic.models.Flight;
import org.nmiljkovic.models.FlightRadars;
import org.nmiljkovic.models.FlightRadarsId;
import org.nmiljkovic.models.Gate;
import org.nmiljkovic.models.Radar;
import org.nmiljkovic.site.HibernateUtil;

/**
 *
 * @author milja
 */
public class FlightRepository {
    Session session = null;
    
    public FlightRepository() {
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public Flight createFlight(Flight flight) {
        try {
            session.beginTransaction();
            session.save(flight);
        } catch (Exception exc) {
            
        } finally {
            session.getTransaction().commit();
        }
        
        return flight;
    }

    public Flight createFlight(Flight flight, List<Radar> radars) {
        try {
            session.beginTransaction();
            session.save(flight);
            int count = 1;
            for (Radar radar : radars) {
                FlightRadarsId flightRadarsId = new FlightRadarsId(count++, flight.getId(), radar.getName());
                FlightRadars flightRadar = new FlightRadars(flightRadarsId, flight, radar);
                session.save(flightRadar);
            }
        } catch (Exception exc) {
            
        } finally {
            session.getTransaction().commit();
        }
        
        return flight;
    }
}