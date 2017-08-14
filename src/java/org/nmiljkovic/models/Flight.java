package org.nmiljkovic.models;
// Generated Aug 13, 2017 2:52:35 PM by Hibernate Tools 4.3.1


import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Flight generated by hbm2java
 */
public class Flight  implements java.io.Serializable {


     private String id;
     private Aircraft aircraft;
     private Airport airportByAirport;
     private Airport airportByDestAirport;
     private Gate gateByEndGate;
     private Gate gateByStartGate;
     private byte charter;
     private Date departure;
     private Date arrival;
     private int duration;
     private String status;
     private Date eta;
     private Date arrivedAt;
     private Set flightRadarses = new HashSet(0);

    public Flight() {
    }

	
    public Flight(String id, Aircraft aircraft, Airport airportByAirport, Airport airportByDestAirport, Gate gateByEndGate, Gate gateByStartGate, byte charter, Date departure, Date arrival, int duration, String status, Date eta, Date arrivedAt) {
        this.id = id;
        this.aircraft = aircraft;
        this.airportByAirport = airportByAirport;
        this.airportByDestAirport = airportByDestAirport;
        this.gateByEndGate = gateByEndGate;
        this.gateByStartGate = gateByStartGate;
        this.charter = charter;
        this.departure = departure;
        this.arrival = arrival;
        this.duration = duration;
        this.status = status;
        this.eta = eta;
        this.arrivedAt = arrivedAt;
    }
    public Flight(String id, Aircraft aircraft, Airport airportByAirport, Airport airportByDestAirport, Gate gateByEndGate, Gate gateByStartGate, byte charter, Date departure, Date arrival, int duration, String status, Date eta, Date arrivedAt, Set flightRadarses) {
       this.id = id;
       this.aircraft = aircraft;
       this.airportByAirport = airportByAirport;
       this.airportByDestAirport = airportByDestAirport;
       this.gateByEndGate = gateByEndGate;
       this.gateByStartGate = gateByStartGate;
       this.charter = charter;
       this.departure = departure;
       this.arrival = arrival;
       this.duration = duration;
       this.status = status;
       this.eta = eta;
       this.arrivedAt = arrivedAt;
       this.flightRadarses = flightRadarses;
    }
   
    public String getId() {
        return this.id;
    }
    
    public void setId(String id) {
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
    public Set getFlightRadarses() {
        return this.flightRadarses;
    }
    
    public void setFlightRadarses(Set flightRadarses) {
        this.flightRadarses = flightRadarses;
    }




}


