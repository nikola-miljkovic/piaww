package org.nmiljkovic.models;
// Generated Aug 23, 2017 10:48:40 PM by Hibernate Tools 4.3.1



/**
 * Booking generated by hbm2java
 */
public class Booking  implements java.io.Serializable {


     private Integer id;
     private Flight flight;
     private int count;
     private long passport;
     private String firstName;
     private String lastName;
     private String creditCard;
     private String flightCode;

    public Booking() {
    }

    public Booking(Flight flight, int count, long passport, String firstName, String lastName, String creditCard, String flightCode) {
       this.flight = flight;
       this.count = count;
       this.passport = passport;
       this.firstName = firstName;
       this.lastName = lastName;
       this.creditCard = creditCard;
       this.flightCode = flightCode;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Flight getFlight() {
        return this.flight;
    }
    
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    public int getCount() {
        return this.count;
    }
    
    public void setCount(int count) {
        this.count = count;
    }
    public long getPassport() {
        return this.passport;
    }
    
    public void setPassport(long passport) {
        this.passport = passport;
    }
    public String getFirstName() {
        return this.firstName;
    }
    
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return this.lastName;
    }
    
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public String getCreditCard() {
        return this.creditCard;
    }
    
    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }
    public String getFlightCode() {
        return this.flightCode;
    }
    
    public void setFlightCode(String flightCode) {
        this.flightCode = flightCode;
    }




}


