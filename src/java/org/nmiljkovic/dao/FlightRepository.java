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
import org.nmiljkovic.models.FlightStatus;
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
    }

    public Flight createFlight(Flight flight) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            session.save(flight);
        } catch (Exception exc) {
            
        } finally {
            session.getTransaction().commit();
        }
        this.session.close();
        
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
        this.session = HibernateUtil.getSessionFactory().openSession();
        List<Flight> flights = null;
        try {
            session.beginTransaction();
            Date endOfDayDate = new Date(departureDate.getTime() + 86400000);
            Query q = session.createQuery("from Flight as flight where " 
                    + "flight.departure > :date and flight.departure < :dateEnd AND flight.status = :status "
                    + "and flight.airportByAirport = '" + departure + "' "
                    + "and flight.airportByDestAirport = '" + destination + "'");
            
            flights = (List<Flight>)q.setDate("date", departureDate)
                    .setDate("dateEnd", endOfDayDate)
                    .setCharacter("status", 'P').list();
            
            if (returnDate != null) {
                q = session.createQuery("from Flight as flight where " 
                    + "flight.departure > :date and flight.departure < :dateEnd AND flight.status = :status "
                    + "and flight.airportByAirport = '" + destination + "' "
                    + "and flight.airportByDestAirport = '" + departure + "'");
                endOfDayDate = new Date(returnDate.getTime() + 86400000);
                flights.addAll((List<Flight>)q.setDate("date", returnDate)
                    .setDate("dateEnd", endOfDayDate)
                    .setCharacter("status", 'P').list());
                
                System.out.println("HELLO : " + flights.size());
            }
        } catch (Exception exc) {
            
        } finally {
            session.getTransaction().commit();
        }
        this.session.close();
        
        return flights;
    }

    public List<Flight> getTodaysFlights() {
        this.session = HibernateUtil.getSessionFactory().openSession();
        List<Flight> flights = null;
        try {
            session.beginTransaction();
            Date date = new Date();
            Query q = session.createQuery("from Flight where departure > :date AND status = :status")
                    .setCharacter("status", 'P')
                    .setDate("date", date);
            flights = (List<Flight>)q.list();
        } catch (Exception exc) {
            
        } finally {
            session.getTransaction().commit();
        }
        this.session.close();
        
        return flights;
    }

    public List<Flight> getFlightsForAirlines(Integer id) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        List<Flight> flights = null;
        try {
            session.beginTransaction();
            Query q = session.createQuery("from Flight as flight where " 
                    + "flight.aircraft.airways.id = " + id  + " order by flight.departure");
            flights = (List<Flight>)q.list();
        } catch (Exception exc) {
            
        } finally {
            session.getTransaction().commit();
        }
        this.session.close();
        
        return flights;
    }
    
    public List<Flight> getFlightsForPilot(int pilotId) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        List<Flight> flights = null;
        try {
            Date date = new Date();
            String hql = "select f from Flight as f, Crew as c where c.user.id = :userId and f.id = c.flight.id and f.status = 'P' and f.departure > :date order by f.departure";
            flights = (List<Flight>)session.createQuery(hql)
                    .setInteger("userId", pilotId)
                    .setDate("date", date)
                    .list();
            System.out.println("TEST: " + flights.size());
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        
        session.close();
        return flights;
    }

    public Flight startFlight(Flight flight) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        try {
            session.beginTransaction();
            flight.setStatus("F");
            flight.setEta(flight.getArrival());
            session.saveOrUpdate(flight);
            FlightStatus fs = new FlightStatus(flight, 0);
            session.save(fs);
        } catch (Exception exc) {
            
        } finally {
            session.getTransaction().commit();
        }
        session.close();
        return flight;
    }

    public Flight getCurrentFlightForPilot(Integer pilotId) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        Flight flight = null;
        try {
            String hql = "select f from Flight as f, Crew as c where c.user.id = :userId and f.id = c.flight.id and f.status = 'F'";
            flight = (Flight)session.createQuery(hql)
                    .setInteger("userId", pilotId)
                    .list().get(0);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        
        session.close();
        
        return flight;
    }

    public Radar getRadarForFlight(Integer flightId) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        Radar radar = null;
        try {
            String hql = "select r from FlightRadars as r, FlightStatus as fs WHERE "
                    + "r.flight.id = :flightId AND r.flight.id = fs.flight.id AND r.id.position = fs.status";
            FlightRadars fRadar = (FlightRadars)session.createQuery(hql)
                    .setInteger("flightId", flightId)
                    .list().get(0);
            radar = fRadar.getRadar();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        
        session.close();
        
        return radar;
    }

    public Flight getFlightWithId(int flightId) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        Flight flight = null;
        try {
            String hql = "select f from Flight as f where f.id = :flightId";
            flight = (Flight)session.createQuery(hql)
                    .setInteger("flightId", flightId)
                    .list().get(0);
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        
        session.close();
        
        return flight;
    }

    public int updateFlight(int flightId, int eta, int count) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        try {
            System.out.println("FLIGHT ID: "+ flightId);
            session.beginTransaction();
            String hql = "from FlightStatus as fs where fs.flight.id = :flightId";
            FlightStatus fs  = (FlightStatus)session.createQuery(hql)
                    .setInteger("flightId", flightId)
                    .list().get(0);
            
            fs.setStatus(fs.getStatus() + 1);
            
             hql = "select f from Flight as f where f.id = :flightId";
            Flight flight  = (Flight)session.createQuery(hql)
                .setInteger("flightId", flightId)
                .list().get(0);
            flight.setEta(new Date(new Date().getTime() + (eta * 60000)));
            if (fs.getStatus() == count - 1) {
                flight.setStatus("D");
                flight.setArrivedAt(new Date());
            }
            session.saveOrUpdate(flight);
            session.saveOrUpdate(fs);
        } catch (Exception exc) {
            exc.printStackTrace();
        } finally {
            session.getTransaction().commit();
        }
        session.close();
        return 0;
    }

    public int getRadarCountForFlight(int flightId) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        int count = -1;
        try {
            String hql = "select fr from FlightRadars as fr where fr.id.flight = :flightId";
            count = (int)session.createQuery(hql)
                    .setInteger("flightId", flightId)
                    .list().size();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        
        session.close();
        
        return count;
    }

    public List<Flight> getFlightsForSteward(Integer id) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        List<Flight> flights = null;
        try {
            Date date = new Date();
            String hql = "select f from Flight as f, Crew as c where c.user.id = :userId and f.id = c.flight.id and f.status = 'P' and f.departure > :date order by f.departure";
            flights = (List<Flight>)session.createQuery(hql)
                    .setInteger("userId", id)
                    .setDate("date", date)
                    .list();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        
        session.close();
        return flights;
    }

    public List<Flight> getOldFlightsForSteward(Integer id) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        List<Flight> flights = null;
        try {
            Date date = new Date();
            String hql = "select f from Flight as f, Crew as c where c.user.id = :userId and f.id = c.flight.id and f.status = 'D' and f.departure > :date order by f.departure";
            flights = (List<Flight>)session.createQuery(hql)
                    .setInteger("userId", id)
                    .setDate("date", date)
                    .list();
        } catch (Exception exc) {
            exc.printStackTrace();
        }
        
        session.close();
        return flights;
    }
}
