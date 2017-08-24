/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.site;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import org.nmiljkovic.dao.UserRepository;
import org.nmiljkovic.dao.AircraftRepository;
import org.nmiljkovic.models.Aircraft;
import org.nmiljkovic.models.Airport;
import org.nmiljkovic.models.Airways;
import org.nmiljkovic.models.Flight;
import org.nmiljkovic.models.User;

@ManagedBean
@ViewScoped
public class AdminView implements Serializable {
    
    private List<User> users;
    private List<Aircraft> aircrafts;
    private List<Airport> airports;
    private List<Airways> airways;
    private List<Flight> flights;

    public AdminView() {
    }
    
    @PostConstruct
    void prepare() {
        UserRepository userRepo = new UserRepository();
        users = userRepo.getNonFlaggedUsers();
    }
    
    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    public List<Aircraft> getAircrafts() {
        return aircrafts;
    }

    public void setAircrafts(List<Aircraft> aircrafts) {
        this.aircrafts = aircrafts;
    }

    public List<Airport> getAirports() {
        return airports;
    }

    public void setAirports(List<Airport> airports) {
        this.airports = airports;
    }

    public List<Airways> getAirways() {
        return airways;
    }

    public void setAirways(List<Airways> airways) {
        this.airways = airways;
    }

    public List<Flight> getFlights() {
        return flights;
    }

    public void setFlights(List<Flight> flights) {
        this.flights = flights;
    }
    
    public String confirmUser(User user) {
        UserRepository userRepo = new UserRepository();
        userRepo.updateUser(user);
        users = userRepo.getNonFlaggedUsers();
        return null;
    }
}
