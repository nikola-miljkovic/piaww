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
import javax.faces.context.FacesContext;
import org.nmiljkovic.dao.UserRepository;
import org.nmiljkovic.dao.AircraftRepository;
import org.nmiljkovic.models.Aircraft;
import org.nmiljkovic.models.Airport;
import org.nmiljkovic.models.Airways;
import org.nmiljkovic.models.Flight;
import org.nmiljkovic.models.User;

@ManagedBean
@RequestScoped
public class AirlinesView implements Serializable {
    
    private Airways airways;
    
    public AirlinesView() {
        airways = new Airways();
    }

    public Airways getAirways() {
        return airways;
    }

    public void setAirways(Airways airways) {
        this.airways = airways;
    }
    
    public String updateAirways() {
        return null;
    }
}
