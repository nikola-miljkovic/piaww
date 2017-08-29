/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.dao;

import java.util.Random;
import org.hibernate.Session;
import org.nmiljkovic.models.Booking;
import org.nmiljkovic.models.Flight;
import org.nmiljkovic.site.HibernateUtil;

/**
 *
 * @author milja
 */
public class BookingRepository {
    
    Session session = null;
    
    public BookingRepository() {
        
    }

    public String bookFlight(int flightId, String firstName, String lastName, long passport, String creditCardNumber, int count) {
        this.session = HibernateUtil.getSessionFactory().openSession();
        String flightCode = null;
        try {
            this.session.beginTransaction();
            FlightRepository flightRepo = new FlightRepository();
            Flight flight = flightRepo.getFlightWithId(flightId);
            flight.setBooked(flight.getBooked() + count);
            
            System.out.println("FLIGHT : " + flight.getAircraft().getAircraftType().getCapacity());
            System.out.println("FLIGHT : " + flight.getBooked());
            if (flight.getAircraft().getAircraftType().getCapacity() < flight.getBooked()) {
                return null;
            }
            
            Random rnd = new Random();
            
            String charPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            int length = charPool.length();
            StringBuilder strBuilder = new StringBuilder(8);
            for (int i = 0; i < 8; i++) {
                strBuilder.append(charPool.charAt(rnd.nextInt(length)));
            }
            Booking booking = new Booking(flight, count, passport, firstName, lastName, creditCardNumber, strBuilder.toString());
            session.beginTransaction();
            session.save(booking);
            session.saveOrUpdate(flight);
            flightCode = booking.getFlightCode();
        } catch (Exception exc) {
            
        } finally {
            session.getTransaction().commit();
        }
        session.close();
        return flightCode;
    }
}
