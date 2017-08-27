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
import org.nmiljkovic.models.Aircraft;
import org.nmiljkovic.models.Airport;
import org.nmiljkovic.models.Airways;
import org.nmiljkovic.models.Flight;
import org.nmiljkovic.models.User;

@ManagedBean
@ViewScoped
public class AirinesAdminView implements Serializable {
    
    private List<Flight> flightList;
    private int pageSize;
    private int pageIndex;
    
    public AirinesAdminView() {
        pageSize = 10;
        pageIndex = 0;
    }
    
    @PostConstruct
    public void prepare() {
        loadFlights();
    }
    
    private void loadFlights() {
        FacesContext context = FacesContext.getCurrentInstance();
        FlightRepository flightRepo = new FlightRepository();
        
        User user = (User) context.getExternalContext().getSessionMap().get("user");
        flightList = flightRepo.getFlightsForAirlines(user.getAirways().getId());
    }
    
    public void checkoutFlight(int id) {
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    public void setFlightList(List<Flight> flightList) {
    }
    
    public void setPageSize(int size) {
        this.pageSize = size;
        loadFlights();
    }
}
