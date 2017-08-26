/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import org.hibernate.Query;
import org.hibernate.Session;
import org.nmiljkovic.models.Aircraft;
import org.nmiljkovic.models.Airways;
import org.nmiljkovic.models.Crew;
import org.nmiljkovic.models.CrewId;
import org.nmiljkovic.models.Flight;
import org.nmiljkovic.models.FlightRadars;
import org.nmiljkovic.models.FlightRadarsId;
import org.nmiljkovic.models.Gate;
import org.nmiljkovic.models.Radar;
import org.nmiljkovic.models.User;
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

    public Flight createFlight(Flight flight, List<Radar> radars, List<User> crew) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(flight);
            int count = 0;
            for (Radar radar : radars) {
                FlightRadarsId flightRadarsId = new FlightRadarsId(count++, flight.getId(), radar.getName());
                FlightRadars flightRadar = new FlightRadars(flightRadarsId, flight, radar);
                session.save(flightRadar);
            }
            
            for (User crewMember : crew) {
                CrewId crewEntryId = new CrewId(crewMember.getId(), flight.getId());
                Crew crewEntry = new Crew(crewEntryId, flight, crewMember);
                session.save(crewEntry);
            }
        } catch (Exception exc) {
            
        } finally {
            session.getTransaction().commit();
        }
        this.session.close();
        
        return flight;
    }

    public List<Flight> getAllFlightsWithCriteria(String departure, String destination, Date departureDate, Date returnDate, int adults, boolean direct, boolean twoWay) {
        List<Flight> flights = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Flight as flight where " 
                    + "flight.airportByAirport = '" + departure + "' "
                    + "and flight.airportByDestAirport = '" + destination + "' ");
            flights = (List<Flight>)q.list();
        } catch (Exception exc) {
            
        } finally {
            session.getTransaction().commit();
        }
        
        return flights;
    }

    public List<Flight> getTodaysFlights() {
        List<Flight> flights = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Flight");
            flights = (List<Flight>)q.list();
        } catch (Exception exc) {
            
        } finally {
            session.getTransaction().commit();
        }
        
        return flights;
    }
}
