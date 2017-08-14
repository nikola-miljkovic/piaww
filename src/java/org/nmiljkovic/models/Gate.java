package org.nmiljkovic.models;
// Generated Aug 13, 2017 2:52:35 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Gate generated by hbm2java
 */
public class Gate  implements java.io.Serializable {


     private int id;
     private Airport airport;
     private int terminal;
     private String gate;
     private Set flightsForEndGate = new HashSet(0);
     private Set flightsForStartGate = new HashSet(0);

    public Gate() {
    }

	
    public Gate(int id, Airport airport, int terminal, String gate) {
        this.id = id;
        this.airport = airport;
        this.terminal = terminal;
        this.gate = gate;
    }
    public Gate(int id, Airport airport, int terminal, String gate, Set flightsForEndGate, Set flightsForStartGate) {
       this.id = id;
       this.airport = airport;
       this.terminal = terminal;
       this.gate = gate;
       this.flightsForEndGate = flightsForEndGate;
       this.flightsForStartGate = flightsForStartGate;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
        this.id = id;
    }
    public Airport getAirport() {
        return this.airport;
    }
    
    public void setAirport(Airport airport) {
        this.airport = airport;
    }
    public int getTerminal() {
        return this.terminal;
    }
    
    public void setTerminal(int terminal) {
        this.terminal = terminal;
    }
    public String getGate() {
        return this.gate;
    }
    
    public void setGate(String gate) {
        this.gate = gate;
    }
    public Set getFlightsForEndGate() {
        return this.flightsForEndGate;
    }
    
    public void setFlightsForEndGate(Set flightsForEndGate) {
        this.flightsForEndGate = flightsForEndGate;
    }
    public Set getFlightsForStartGate() {
        return this.flightsForStartGate;
    }
    
    public void setFlightsForStartGate(Set flightsForStartGate) {
        this.flightsForStartGate = flightsForStartGate;
    }




}


