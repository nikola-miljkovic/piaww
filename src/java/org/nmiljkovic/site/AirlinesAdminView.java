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
import org.nmiljkovic.models.ReservationRequest;
import org.nmiljkovic.models.User;

@ManagedBean
@ViewScoped
public class AirlinesAdminView implements Serializable {
    
    private List<Flight> flightList;
    private List<ReservationRequest> reservationRequests;
    private List<Airways> airwayList;
    private List<Aircraft> aircraftList;
    private int selectedAirways;
    private int selectedAircraft;
    private Airways userAirways;
    
    public static int ReservationStatusNone = 0;
    public static int ReservationStatusReserved = 1;
    public static int ReservationStatusDone = 2;
    public static int ReservationStatusNoAction = 3;
    
    public static String[] reservations = new String[] {
        "None",
        "Reserved",
        "Done",
        "Forbidden"
    };
    
    public AirlinesAdminView() {
    }
    
    @PostConstruct
    public void prepare() {
        FacesContext context = FacesContext.getCurrentInstance();
        User user = (User) context.getExternalContext().getSessionMap().get("user");
        userAirways = user.getAirways();
        loadReservations();
        loadFlights();
        
        AirwaysRepository airwaysRepo = new AirwaysRepository();
        airwayList = airwaysRepo.getAirways();
    }
    
    private void loadFlights() {
        FlightRepository flightRepo = new FlightRepository();
        flightList = flightRepo.getFlightsForAirlines(userAirways.getId());
    }

    private void loadReservations() {
        ReservationRepository reservationRepo = new ReservationRepository();
        reservationRequests = reservationRepo.getReservationsForAirways(userAirways.getId());
    }
    
    public void checkoutFlight(int id) {
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public List<ReservationRequest> getReservationRequests() {
        return reservationRequests;
    }

    public void setReservationRequests(List<ReservationRequest> reservationRequests) {
        this.reservationRequests = reservationRequests;
    }
    
    public String[] getReservationString() {
        return reservations;
    }
    
    public void approveReservation(ReservationRequest reservation) {
        ReservationRepository reservationRepo = new ReservationRepository();
        reservationRepo.approveReservation(reservation);
    }
    
    public void forbidReservation(ReservationRequest reservation) {
        ReservationRepository reservationRepo = new ReservationRepository();
        reservationRepo.forbidReservation(reservation);
    }
    
    public void finishReservation(ReservationRequest reservation) {
        ReservationRepository reservationRepo = new ReservationRepository();
        reservationRepo.finishReservation(reservation);
    }

    public List<Airways> getAirwayList() {
        return airwayList;
    }

    public void setAirwayList(List<Airways> airways) {
        this.airwayList = airways;
    }

    public int getSelectedAirways() {
        return selectedAirways;
    }

    public void setSelectedAirways(int selectedAirways) {
        this.selectedAirways = selectedAirways;
        AircraftRepository aircraftRepo = new AircraftRepository();
        aircraftList = aircraftRepo.getAircraftForAirlines(selectedAirways);
    }

    public List<Aircraft> getAircraftList() {
        return aircraftList;
    }

    public void setAircraftList(List<Aircraft> aircraftList) {
        this.aircraftList = aircraftList;
    }

    public int getSelectedAircraft() {
        return selectedAircraft;
    }

    public void setSelectedAircraft(int selectedAircraft) {
        this.selectedAircraft = selectedAircraft;
    }
    
    public void createReservation() throws AicraftNotFoundException {
        ReservationRepository reservationRepo = new ReservationRepository();
        AircraftRepository aircraftRepo = new AircraftRepository();
        AirwaysRepository airwaysRepo = new AirwaysRepository();
        
        Aircraft aircraft = aircraftRepo.getAircraftById(selectedAircraft);
        if (aircraft == null) {
            throw new AicraftNotFoundException(selectedAircraft);
        }
        
        Airways airways = airwaysRepo.getAirwaysById(selectedAirways);
        
        ReservationRequest reservation = new ReservationRequest(aircraft, userAirways, airways, ReservationStatusNone);
        reservationRepo.createReservation(reservation);
    }
}
