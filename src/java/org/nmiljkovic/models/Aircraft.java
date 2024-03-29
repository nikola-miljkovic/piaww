package org.nmiljkovic.models;
// Generated Aug 28, 2017 6:04:08 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Aircraft generated by hbm2java
 */
public class Aircraft  implements java.io.Serializable {


     private Integer id;
     private AircraftType aircraftType;
     private Airways airways;
     private Manufacturer manufacturer;
     private Set reservationRequests = new HashSet(0);
     private Set flights = new HashSet(0);

    public Aircraft() {
    }

	
    public Aircraft(AircraftType aircraftType, Airways airways, Manufacturer manufacturer) {
        this.aircraftType = aircraftType;
        this.airways = airways;
        this.manufacturer = manufacturer;
    }
    public Aircraft(AircraftType aircraftType, Airways airways, Manufacturer manufacturer, Set reservationRequests, Set flights) {
       this.aircraftType = aircraftType;
       this.airways = airways;
       this.manufacturer = manufacturer;
       this.reservationRequests = reservationRequests;
       this.flights = flights;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public AircraftType getAircraftType() {
        return this.aircraftType;
    }
    
    public void setAircraftType(AircraftType aircraftType) {
        this.aircraftType = aircraftType;
    }
    public Airways getAirways() {
        return this.airways;
    }
    
    public void setAirways(Airways airways) {
        this.airways = airways;
    }
    public Manufacturer getManufacturer() {
        return this.manufacturer;
    }
    
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
    public Set getReservationRequests() {
        return this.reservationRequests;
    }
    
    public void setReservationRequests(Set reservationRequests) {
        this.reservationRequests = reservationRequests;
    }
    public Set getFlights() {
        return this.flights;
    }
    
    public void setFlights(Set flights) {
        this.flights = flights;
    }




}


