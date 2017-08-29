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
public class StewardView implements Serializable {
   
    private List<Flight> stewardFlights;
    private List<Flight> stewardOldFlights;
    
    public StewardView() {
    }
    
    @PostConstruct
    public void prepare() {
        loadContext();
    }
    
    private void loadContext() {
        FacesContext context = FacesContext.getCurrentInstance();
        User user = (User) context.getExternalContext().getSessionMap().get("user");
        
        FlightRepository flightRepo = new FlightRepository();
        stewardFlights = flightRepo.getFlightsForSteward(user.getId());
        stewardOldFlights = flightRepo.getOldFlightsForSteward(user.getId());
    }

    public List<Flight> getStewardFlights() {
        return stewardFlights;
    }

    public void setStewardFlights(List<Flight> stewardFlights) {
        this.stewardFlights = stewardFlights;
    }

    public List<Flight> getStewardOldFlights() {
        return stewardOldFlights;
    }

    public void setStewardOldFlights(List<Flight> stewardOldFlights) {
        this.stewardOldFlights = stewardOldFlights;
    }
}
