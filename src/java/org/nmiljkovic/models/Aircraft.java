package org.nmiljkovic.models;
// Generated Aug 20, 2017 9:06:03 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Aircraft generated by hbm2java
 */
public class Aircraft  implements java.io.Serializable {


     private int id;
     private AircraftType aircraftType;
     private Airways airways;
     private Manufacturer manufacturer;
     private Set flights = new HashSet(0);

    public Aircraft() {
    }

	
    public Aircraft(int id, AircraftType aircraftType, Airways airways, Manufacturer manufacturer) {
        this.id = id;
        this.aircraftType = aircraftType;
        this.airways = airways;
        this.manufacturer = manufacturer;
    }
    public Aircraft(int id, AircraftType aircraftType, Airways airways, Manufacturer manufacturer, Set flights) {
       this.id = id;
       this.aircraftType = aircraftType;
       this.airways = airways;
       this.manufacturer = manufacturer;
       this.flights = flights;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
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
    public Set getFlights() {
        return this.flights;
    }
    
    public void setFlights(Set flights) {
        this.flights = flights;
    }




}


