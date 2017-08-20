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
        this.session = HibernateUtil.getSessionFactory().openSession();
    }

    public String bookFlight(String flightId, String firstName, String lastName, long passport, String creditCardNumber, int count) {
        String flightCode = null;
        try {
            Flight flight = new Flight();
            flight.setId(flightId);
            Random rnd = new Random();
            
            String charPool = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
            int length = charPool.length();
            StringBuilder strBuilder = new StringBuilder(8);
            for (int i = 0; i < 8; i++) {
                strBuilder.append(charPool.charAt(rnd.nextInt(length)));
            }
            Booking booking = new Booking(count, flight, count, passport, firstName, lastName, creditCardNumber, strBuilder.toString());
            session.beginTransaction();
            session.save(booking);
            flightCode = booking.getFlightCode();
        } catch (Exception exc) {
            
        } finally {
            session.getTransaction().commit();
        }
        
        return flightCode;
    }
}
