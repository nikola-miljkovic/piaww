package org.nmiljkovic.models;
// Generated Aug 28, 2017 6:04:08 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Radar generated by hbm2java
 */
public class Radar  implements java.io.Serializable {


     private String name;
     private Set flightRadarses = new HashSet(0);

    public Radar() {
    }

	
    public Radar(String name) {
        this.name = name;
    }
    public Radar(String name, Set flightRadarses) {
       this.name = name;
       this.flightRadarses = flightRadarses;
    }
   
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public Set getFlightRadarses() {
        return this.flightRadarses;
    }
    
    public void setFlightRadarses(Set flightRadarses) {
        this.flightRadarses = flightRadarses;
    }




}


