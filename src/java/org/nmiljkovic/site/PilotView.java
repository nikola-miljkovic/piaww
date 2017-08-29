/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.site;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.nmiljkovic.dao.UserRepository;
import org.nmiljkovic.dao.AircraftRepository;
import org.nmiljkovic.dao.AirwaysRepository;
import org.nmiljkovic.dao.FlightRepository;
import org.nmiljkovic.dao.ReservationRepository;
import org.nmiljkovic.exceptions.AicraftNotFoundException;
import org.nmiljkovic.models.Aircraft;
import org.nmiljkovic.models.Airport;
import org.nmiljkovic.models.Airways;
import org.nmiljkovic.models.Flight;
import org.nmiljkovic.models.Radar;
import org.nmiljkovic.models.ReservationRequest;
import org.nmiljkovic.models.User;

@ManagedBean
@RequestScoped
public class PilotView implements Serializable {
   
    private List<Flight> pilotFlights;
    private Flight currentFlight;
    private int currentFlightRadarCount;
    private Radar currentLocation;
    private int avgSpeed;
    private int roadRemaining;
    
    public PilotView() {
    }
    
    @PostConstruct
    public void prepare() {
        loadContext();
    }
    
    private void loadContext() {
        FacesContext context = FacesContext.getCurrentInstance();
        User user = (User) context.getExternalContext().getSessionMap().get("user");
        
        FlightRepository flightRepo = new FlightRepository();
        currentFlight = flightRepo.getCurrentFlightForPilot(user.getId());
        if (currentFlight == null) {
            pilotFlights = flightRepo.getFlightsForPilot(user.getId());
        } else {
            currentLocation = flightRepo.getRadarForFlight(currentFlight.getId());
            currentFlightRadarCount = flightRepo.getRadarCountForFlight(currentFlight.getId());
        }
    }

    public List<Flight> getPilotFlights() {
        return pilotFlights;
    }

    public void setPilotFlights(List<Flight> pilotFlights) {
        this.pilotFlights = pilotFlights;
    }

    public Flight getCurrentFlight() {
        return currentFlight;
    }

    public void setCurrentFlight(Flight currentFlight) {
        this.currentFlight = currentFlight;
    }

    public Radar getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(Radar currentLocation) {
        this.currentLocation = currentLocation;
    }

    public int getAvgSpeed() {
        return avgSpeed;
    }

    public void setAvgSpeed(int avgSpeed) {
        this.avgSpeed = avgSpeed;
    }

    public int getRoadRemaining() {
        return roadRemaining;
    }

    public void setRoadRemaining(int roadRemaining) {
        this.roadRemaining = roadRemaining;
    }
    
    public void updateState(int flightId) {
        FlightRepository flightRepo = new FlightRepository();
        flightRepo.updateFlight(flightId, getRoadRemaining() / getAvgSpeed(), currentFlightRadarCount);
        
        loadContext();
    }
    
    public void startFlight(int flightId) {
        FlightRepository flightRepo = new FlightRepository();
        for (Flight flight : pilotFlights) {
            if (flight.getId() == flightId) {
                currentFlight = flightRepo.startFlight(flight);
                break;
            }
        }
        
        loadContext();
    }
}
