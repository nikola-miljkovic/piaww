/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.site;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import org.nmiljkovic.dao.UserRepository;
import org.nmiljkovic.dao.AircraftRepository;
import org.nmiljkovic.dao.AirportRepository;
import org.nmiljkovic.dao.GateRepository;
import org.nmiljkovic.models.Aircraft;
import org.nmiljkovic.models.Airport;
import org.nmiljkovic.models.Airways;
import org.nmiljkovic.models.Flight;
import org.nmiljkovic.models.Gate;
import org.nmiljkovic.models.User;

@ManagedBean
@ViewScoped
public class AirportView implements Serializable {
    
    private List<Gate> gates;
    private Airport airport;

    public AirportView() {
        gates = new ArrayList<>();
        airport = new Airport();
        addGate();
    }

    public List<Gate> getGates() {
        return gates;
    }

    public void setGates(List<Gate> gates) {
        this.gates = gates;
    }

    public Airport getAirport() {
        return airport;
    }

    public void setAirport(Airport airport) {
        this.airport = airport;
    }
    
    public void addGate() {
        gates.add(new Gate());
    }
    
    public String createAirport() {
        AirportRepository airportRepo = new AirportRepository();
        GateRepository gateRepo = new GateRepository();
        
        airportRepo.createAirport(airport);
        for (Gate gate : gates) {
            gate.setAirport(airport);
            gateRepo.createGate(gate);
        }
        
        gates = new ArrayList<>();
        airport = new Airport();
        
        return null;
    }
}
