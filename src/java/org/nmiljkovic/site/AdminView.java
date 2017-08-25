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
import org.nmiljkovic.dao.AirportRepository;
import org.nmiljkovic.dao.AirwaysRepository;
import org.nmiljkovic.dao.GateRepository;
import org.nmiljkovic.models.Aircraft;
import org.nmiljkovic.models.Airport;
import org.nmiljkovic.models.Airways;
import org.nmiljkovic.models.Flight;
import org.nmiljkovic.models.Gate;
import org.nmiljkovic.models.User;

@ManagedBean
@ViewScoped
public class AdminView implements Serializable {
    
    private List<User> users;
    private List<Aircraft> aircrafts;
    private List<Airport> airports;
    private List<Airways> airways;
    private List<Flight> flights;

    // Related to flight creation
    private Flight flight;
    private int flightAirlineSelected;
    private Aircraft airlineAircraft;
    private List<Aircraft> flightAircrafts;
    
    private String airportDepId;
    private String airportArrId;
    private List<Gate> flightStartGates;
    private List<Gate> flightEndGates;
    private List<User> flightPilots;
    private List<User> flightStewards;
    
    private int gateByStartGate;
    private int gateByEndGate;
    
    private User flightPilot1;
    private User flightPilot2;
    private User flightSteward1;
    private User flightSteward2;
    private User flightSteward3;
    
    public AdminView() {
    }
    
    @PostConstruct
    void prepare() {
        UserRepository userRepo = new UserRepository();
        users = userRepo.getNonFlaggedUsers();
        
        AirwaysRepository airwaysRepo = new AirwaysRepository();
        airways = airwaysRepo.getAirways();
        
        AirportRepository airportRepo = new AirportRepository();
        airports = airportRepo.getAirports();
        
        flight = new Flight();
        airlineAircraft = new Aircraft();
        flightAircrafts = new ArrayList<>();
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

    public Flight getFlight() {
        return flight;
    }

    public void setFlight(Flight flight) {
        this.flight = flight;
    }

    public int getFlightAirlineSelected() {
        return flightAirlineSelected;
    }

    public void setFlightAirlineSelected(int flightAirlineSelected) {
        this.flightAirlineSelected = flightAirlineSelected;
        
        AircraftRepository aircraftRepo = new AircraftRepository();
        this.flightAircrafts = aircraftRepo.getAircraftForAirlines(flightAirlineSelected);
        
        UserRepository userRepo = new UserRepository();
        this.flightPilots = userRepo.getEmployeeWithFlag(flightAirlineSelected, LoginView.UserTypePilot);
        this.flightStewards = userRepo.getEmployeeWithFlag(flightAirlineSelected, LoginView.UserTypeSteward);
    }

    public Aircraft getAirlineAircraft() {
        return airlineAircraft;
    }

    public void setAirlineAircraft(Aircraft airlineAircraft) {
        this.airlineAircraft = airlineAircraft;
    }

    public List<Aircraft> getFlightAircrafts() {
        return flightAircrafts;
    }

    public void setFlightAircrafts(List<Aircraft> flightAircrafts) {
        this.flightAircrafts = flightAircrafts;
    }

    public String getAirportDepId() {
        return airportDepId;
    }

    public void setAirportDepId(String airportDepId) {
        this.airportDepId = airportDepId;
        
        GateRepository gateRepo = new GateRepository();
        this.flightStartGates = gateRepo.getGatesForAirport(airportDepId);
    }

    public String getAirportArrId() {
        return airportArrId;
    }

    public void setAirportArrId(String airportArrId) {
        this.airportArrId = airportArrId;
        
        GateRepository gateRepo = new GateRepository();
        this.flightEndGates = gateRepo.getGatesForAirport(airportArrId);
    }

    public List<Gate> getFlightStartGates() {
        return flightStartGates;
    }

    public void setFlightStartGates(List<Gate> flightStartGates) {
        this.flightStartGates = flightStartGates;
    }

    public List<Gate> getFlightEndGates() {
        return flightEndGates;
    }

    public void setFlightEndGates(List<Gate> flightEndGates) {
        this.flightEndGates = flightEndGates;
    }

    public List<User> getFlightPilots() {
        return flightPilots;
    }

    public void setFlightPilots(List<User> flightPilots) {
        this.flightPilots = flightPilots;
    }

    public List<User> getFlightStewards() {
        return flightStewards;
    }

    public void setFlightStewards(List<User> flightStewards) {
        this.flightStewards = flightStewards;
    }

    public User getFlightPilot1() {
        return flightPilot1;
    }

    public void setFlightPilot1(User flightPilot1) {
        this.flightPilot1 = flightPilot1;
    }

    public User getFlightPilot2() {
        return flightPilot2;
    }

    public void setFlightPilot2(User flightPilot2) {
        this.flightPilot2 = flightPilot2;
    }

    public User getFlightSteward1() {
        return flightSteward1;
    }

    public void setFlightSteward1(User flightSteward1) {
        this.flightSteward1 = flightSteward1;
    }

    public User getFlightSteward2() {
        return flightSteward2;
    }

    public void setFlightSteward2(User flightSteward2) {
        this.flightSteward2 = flightSteward2;
    }

    public User getFlightSteward3() {
        return flightSteward3;
    }

    public void setFlightSteward3(User flightSteward3) {
        this.flightSteward3 = flightSteward3;
    }

    public int getGateByStartGate() {
        return gateByStartGate;
    }

    public void setGateByStartGate(int gateByStartGate) {
        this.gateByStartGate = gateByStartGate;
    }

    public int getGateByEndGate() {
        return gateByEndGate;
    }

    public void setGateByEndGate(int gateByEndGate) {
        this.gateByEndGate = gateByEndGate;
    }
    
    public String createFlight() {
        return null;
    }
}
