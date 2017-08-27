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
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import org.nmiljkovic.dao.UserRepository;
import org.nmiljkovic.dao.AircraftRepository;
import org.nmiljkovic.dao.AirportRepository;
import org.nmiljkovic.dao.AirwaysRepository;
import org.nmiljkovic.dao.FlightRepository;
import org.nmiljkovic.dao.GateRepository;
import org.nmiljkovic.dao.RadarRepository;
import org.nmiljkovic.dto.FlightDataDto;
import org.nmiljkovic.exceptions.AicraftNotFoundException;
import org.nmiljkovic.exceptions.AirportNotFoundException;
import org.nmiljkovic.exceptions.GateNotFoundException;
import org.nmiljkovic.models.Aircraft;
import org.nmiljkovic.models.Airport;
import org.nmiljkovic.models.Airways;
import org.nmiljkovic.models.Flight;
import org.nmiljkovic.models.Gate;
import org.nmiljkovic.models.Radar;
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
    private int airlineAircraft;
    private List<Aircraft> flightAircrafts;
    
    private String airportDepId;
    private String airportArrId;
    private List<Gate> flightStartGates;
    private List<Gate> flightEndGates;
    private List<User> flightPilots;
    private List<User> flightStewards;
    private List<Radar> flightRadars;
    
    private int gateByStartGate;
    private int gateByEndGate;
    private boolean flightIsCharter;
    
    private String flightPilot1;
    private String flightPilot2;
    private String flightSteward1;
    private String flightSteward2;
    private String flightSteward3;
    
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
        airlineAircraft = 0;
        flightAircrafts = new ArrayList<>();
        flightRadars = new ArrayList<>();
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

    public int getAirlineAircraft() {
        return airlineAircraft;
    }

    public void setAirlineAircraft(int airlineAircraft) {
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

    public String getFlightPilot1() {
        return flightPilot1;
    }

    public void setFlightPilot1(String flightPilot1) {
        this.flightPilot1 = flightPilot1;
    }

    public String getFlightPilot2() {
        return flightPilot2;
    }

    public void setFlightPilot2(String flightPilot2) {
        this.flightPilot2 = flightPilot2;
    }

    public String getFlightSteward1() {
        return flightSteward1;
    }

    public void setFlightSteward1(String flightSteward1) {
        this.flightSteward1 = flightSteward1;
    }

    public String getFlightSteward2() {
        return flightSteward2;
    }

    public void setFlightSteward2(String flightSteward2) {
        this.flightSteward2 = flightSteward2;
    }

    public String getFlightSteward3() {
        return flightSteward3;
    }

    public void setFlightSteward3(String flightSteward3) {
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

    public boolean isFlightIsCharter() {
        return flightIsCharter;
    }

    public void setFlightIsCharter(boolean flightIsCharter) {
        this.flightIsCharter = flightIsCharter;
    }

    public List<Radar> getFlightRadars() {
        return flightRadars;
    }

    public void setFlightRadars(List<Radar> flightRadars) {
        this.flightRadars = flightRadars;
    }
    
    public void addRadar() {
        flightRadars.add(new Radar());
    }
    
    public String createFlight() throws AicraftNotFoundException, AirportNotFoundException, GateNotFoundException {
        AircraftRepository aircraftRepo = new AircraftRepository();
        AirportRepository airportRepo = new AirportRepository();
        GateRepository gateRepo = new GateRepository();
        UserRepository userRepo = new UserRepository();
        RadarRepository radarRepo = new RadarRepository();
        FlightRepository flightRepo = new FlightRepository();
        
        Aircraft aircraft = aircraftRepo.getAircraftById(airlineAircraft);
        if (aircraft == null) {
            throw new AicraftNotFoundException(airlineAircraft);
        }

        Airport departureAirport = airportRepo.getAirportById(airportDepId);
        if (departureAirport == null) {
            throw new AirportNotFoundException(airportDepId);
        }

        Airport arrivalAirport = airportRepo.getAirportById(airportArrId);
        if (arrivalAirport == null) {
            throw new AirportNotFoundException(airportArrId);
        }

        Gate departureGate = gateRepo.getGateById(gateByStartGate);
        if (departureGate == null) {
            throw new GateNotFoundException(gateByStartGate);
        }

        Gate arrivalGate = gateRepo.getGateById(gateByEndGate);
        if (arrivalGate == null) {
            throw new GateNotFoundException(gateByEndGate);
        }
        
        List<String> radarList = new ArrayList<>();
        radarList.add(departureAirport.getId());
        flightRadars.stream().map((radar) -> {
            radarRepo.confirmRadar(radar);
            return radar;
        }).forEachOrdered((radar) -> {
            radarList.add(radar.getName());
        });
        radarRepo.confirmRadar(new Radar(departureAirport.getId()));
        radarRepo.confirmRadar(new Radar(arrivalAirport.getId()));
        radarList.add(arrivalAirport.getId());
        
        List<Radar> radars = radarRepo.getRadarList(radarList.toArray(new String[0]));
        
        List<String> crewMembers = new ArrayList<>();
        crewMembers.add(flightPilot1);
        crewMembers.add(flightPilot2);
        crewMembers.add(flightSteward1);
        crewMembers.add(flightSteward2);
        crewMembers.add(flightSteward3);
        
        List<User> crew = userRepo.getCrew(crewMembers.toArray(new String[0]));
        
        byte isCharter = (byte)(flightIsCharter ? 1 : 0);
        if (flightIsCharter) {
            Flight newFlight = new Flight(aircraft, 
                    arrivalAirport, departureAirport, arrivalGate, departureGate, flight.getFlightId(),
                    isCharter, flight.getDeparture(), new Date(flight.getDeparture().getTime() + (flight.getDuration() * 60000)), 
                    flight.getDuration(), "P", 0);
            
            flightRepo.createFlight(newFlight, radars, crew);
        } else {
            Calendar cal = Calendar.getInstance(TimeZone.getTimeZone("CET"));
            cal.setTime(flight.getDeparture());
            int currentWeek = cal.get(Calendar.WEEK_OF_MONTH);
            
            while (true) {
                Flight newFlight = new Flight(aircraft, 
                    arrivalAirport, departureAirport, arrivalGate, departureGate, flight.getFlightId(),
                    isCharter, cal.getTime(), new Date(cal.getTime().getTime() + (flight.getDuration() * 60000)), 
                    flight.getDuration(), "P", 0);
            
                flightRepo.createFlight(newFlight, radars, crew);
                  
                cal.add(Calendar.WEEK_OF_MONTH, 1);
                if (currentWeek < cal.get(Calendar.WEEK_OF_MONTH)) {
                    currentWeek = cal.get(Calendar.WEEK_OF_MONTH);
                } else {
                    break;
                }
            }
            
        }
        
        return null;
    }
}
