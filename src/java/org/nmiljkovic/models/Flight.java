package org.nmiljkovic.models;
// Generated Aug 23, 2017 10:48:40 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Flight generated by hbm2java
 */
public class Flight  implements java.io.Serializable {


     private Integer id;
     private Aircraft aircraft;
     private Airport airportByAirport;
     private Airport airportByDestAirport;
     private Gate gateByEndGate;
     private Gate gateByStartGate;
     private String flightId;
     private byte charter;
     private Date departure;
     private Date arrival;
     private int duration;
     private String status;
     private Date eta;
     private Date arrivedAt;
     private int booked;
     private Set bookings = new HashSet(0);
     private Set flightRadarses = new HashSet(0);
     private Set crews = new HashSet(0);

    public Flight() {
    }

	
    public Flight(Aircraft aircraft, Airport airportByAirport, Airport airportByDestAirport, Gate gateByEndGate, Gate gateByStartGate, String flightId, byte charter, Date departure, Date arrival, int duration, String status, int booked) {
        this.aircraft = aircraft;
        this.airportByAirport = airportByAirport;
        this.airportByDestAirport = airportByDestAirport;
        this.gateByEndGate = gateByEndGate;
        this.gateByStartGate = gateByStartGate;
        this.flightId = flightId;
        this.charter = charter;
        this.departure = departure;
        this.arrival = arrival;
        this.duration = duration;
        this.status = status;
        this.booked = booked;
    }
    public Flight(Aircraft aircraft, Airport airportByAirport, Airport airportByDestAirport, Gate gateByEndGate, Gate gateByStartGate, String flightId, byte charter, Date departure, Date arrival, int duration, String status, Date eta, Date arrivedAt, int booked, String flightcol, Set bookings, Set flightRadarses, Set crews) {
       this.aircraft = aircraft;
       this.airportByAirport = airportByAirport;
       this.airportByDestAirport = airportByDestAirport;
       this.gateByEndGate = gateByEndGate;
       this.gateByStartGate = gateByStartGate;
       this.flightId = flightId;
       this.charter = charter;
       this.departure = departure;
       this.arrival = arrival;
       this.duration = duration;
       this.status = status;
       this.eta = eta;
       this.arrivedAt = arrivedAt;
       this.booked = booked;
       this.bookings = bookings;
       this.flightRadarses = flightRadarses;
       this.crews = crews;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Aircraft getAircraft() {
        return this.aircraft;
    }
    
    public void setAircraft(Aircraft aircraft) {
        this.aircraft = aircraft;
    }
    public Airport getAirportByAirport() {
        return this.airportByAirport;
    }
    
    public void setAirportByAirport(Airport airportByAirport) {
        this.airportByAirport = airportByAirport;
    }
    public Airport getAirportByDestAirport() {
        return this.airportByDestAirport;
    }
    
    public void setAirportByDestAirport(Airport airportByDestAirport) {
        this.airportByDestAirport = airportByDestAirport;
    }
    public Gate getGateByEndGate() {
        return this.gateByEndGate;
    }
    
    public void setGateByEndGate(Gate gateByEndGate) {
        this.gateByEndGate = gateByEndGate;
    }
    public Gate getGateByStartGate() {
        return this.gateByStartGate;
    }
    
    public void setGateByStartGate(Gate gateByStartGate) {
        this.gateByStartGate = gateByStartGate;
    }
    public String getFlightId() {
        return this.flightId;
    }
    
    public void setFlightId(String flightId) {
        this.flightId = flightId;
    }
    public byte getCharter() {
        return this.charter;
    }
    
    public void setCharter(byte charter) {
        this.charter = charter;
    }
    public Date getDeparture() {
        return this.departure;
    }
    
    public void setDeparture(Date departure) {
        this.departure = departure;
    }
    public Date getArrival() {
        return this.arrival;
    }
    
    public void setArrival(Date arrival) {
        this.arrival = arrival;
    }
    public int getDuration() {
        return this.duration;
    }
    
    public void setDuration(int duration) {
        this.duration = duration;
    }
    public String getStatus() {
        return this.status;
    }
    
    public void setStatus(String status) {
        this.status = status;
    }
    public Date getEta() {
        return this.eta;
    }
    
    public void setEta(Date eta) {
        this.eta = eta;
    }
    public Date getArrivedAt() {
        return this.arrivedAt;
    }
    
    public void setArrivedAt(Date arrivedAt) {
        this.arrivedAt = arrivedAt;
    }
    public int getBooked() {
        return this.booked;
    }
    
    public void setBooked(int booked) {
        this.booked = booked;
    }
    
    public Set getBookings() {
        return this.bookings;
    }
    
    public void setBookings(Set bookings) {
        this.bookings = bookings;
    }
    public Set getFlightRadarses() {
        return this.flightRadarses;
    }
    
    public void setFlightRadarses(Set flightRadarses) {
        this.flightRadarses = flightRadarses;
    }
    public Set getCrews() {
        return this.crews;
    }
    
    public void setCrews(Set crews) {
        this.crews = crews;
    }

    public Date getDurationAsDate() {
        Date date = new Date(this.duration * 60000);
        return date;
    }
}


