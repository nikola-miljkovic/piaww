/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.site;

import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.Date;
import javax.faces.bean.SessionScoped;

@ManagedBean
@SessionScoped
public class FlightSearchFormView implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private boolean mTwoWay;
    private boolean mDirect;
    private String mDeparture;
    private String mDestination;
    private Date mDepartureDate;
    private Date mReturnDate;
    private int mAdults = 1; 
    
    public void submit() {
        System.out.println("loginz");
        //FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Info", "PrimeFaces Rocks."));
    }

    public boolean isTwoWay() {
        return mTwoWay;
    }

    public void setTwoWay(boolean twoWay) {
        this.mTwoWay = twoWay;
    }

    public boolean isDirect() {
        return mDirect;
    }

    public void setDirect(boolean direct) {
        this.mDirect = direct;
    }

    public String getDeparture() {
        return mDeparture;
    }

    public void setDeparture(String departure) {
        this.mDeparture = departure;
    }

    public String getDestination() {
        return mDestination;
    }

    public void setDestination(String destination) {
        this.mDestination = destination;
    }

    public Date getDepartureDate() {
        return mDepartureDate;
    }

    public void setDepartureDate(Date departureDate) {
        this.mDepartureDate = departureDate;
    }

    public Date getReturnDate() {
        return mReturnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.mReturnDate = returnDate;
    }

    public int getAdults() {
        return mAdults;
    }

    public void setAdults(int adults) {
        this.mAdults = adults;
    }
}
