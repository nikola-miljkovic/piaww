package org.nmiljkovic.models;
// Generated Aug 28, 2017 6:04:08 PM by Hibernate Tools 4.3.1



/**
 * FlightStatus generated by hbm2java
 */
public class FlightStatus  implements java.io.Serializable {


     private Integer id;
     private Flight flight;
     private Integer status;

    public FlightStatus() {
    }

    public FlightStatus(Flight flight, Integer status) {
       this.flight = flight;
       this.status = status;
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
    public Integer getStatus() {
        return this.status;
    }
    
    public void setStatus(Integer status) {
        this.status = status;
    }




}

