package org.nmiljkovic.models;
// Generated Aug 27, 2017 8:06:21 PM by Hibernate Tools 4.3.1



/**
 * FlightRadars generated by hbm2java
 */
public class FlightRadars  implements java.io.Serializable {


     private FlightRadarsId id;
     private Flight flight;
     private Radar radar;

    public FlightRadars() {
    }

    public FlightRadars(FlightRadarsId id, Flight flight, Radar radar) {
       this.id = id;
       this.flight = flight;
       this.radar = radar;
    }
   
    public FlightRadarsId getId() {
        return this.id;
    }
    
    public void setId(FlightRadarsId id) {
        this.id = id;
    }
    public Flight getFlight() {
        return this.flight;
    }
    
    public void setFlight(Flight flight) {
        this.flight = flight;
    }
    public Radar getRadar() {
        return this.radar;
    }
    
    public void setRadar(Radar radar) {
        this.radar = radar;
    }




}


