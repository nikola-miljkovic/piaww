package org.nmiljkovic.models;
// Generated Aug 28, 2017 6:04:08 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Airways generated by hbm2java
 */
public class Airways  implements java.io.Serializable {


     private Integer id;
     private String name;
     private String country;
     private String website;
     private String email;
     private String address;
     private Set reservationRequestsForRequester = new HashSet(0);
     private Set users = new HashSet(0);
     private Set aircrafts = new HashSet(0);
     private Set reservationRequestsForOwner = new HashSet(0);

    public Airways() {
    }

	
    public Airways(String name, String country, String website, String email, String address) {
        this.name = name;
        this.country = country;
        this.website = website;
        this.email = email;
        this.address = address;
    }
    public Airways(String name, String country, String website, String email, String address, Set reservationRequestsForRequester, Set users, Set aircrafts, Set reservationRequestsForOwner) {
       this.name = name;
       this.country = country;
       this.website = website;
       this.email = email;
       this.address = address;
       this.reservationRequestsForRequester = reservationRequestsForRequester;
       this.users = users;
       this.aircrafts = aircrafts;
       this.reservationRequestsForOwner = reservationRequestsForOwner;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public String getCountry() {
        return this.country;
    }
    
    public void setCountry(String country) {
        this.country = country;
    }
    public String getWebsite() {
        return this.website;
    }
    
    public void setWebsite(String website) {
        this.website = website;
    }
    public String getEmail() {
        return this.email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
    public String getAddress() {
        return this.address;
    }
    
    public void setAddress(String address) {
        this.address = address;
    }
    public Set getReservationRequestsForRequester() {
        return this.reservationRequestsForRequester;
    }
    
    public void setReservationRequestsForRequester(Set reservationRequestsForRequester) {
        this.reservationRequestsForRequester = reservationRequestsForRequester;
    }
    public Set getUsers() {
        return this.users;
    }
    
    public void setUsers(Set users) {
        this.users = users;
    }
    public Set getAircrafts() {
        return this.aircrafts;
    }
    
    public void setAircrafts(Set aircrafts) {
        this.aircrafts = aircrafts;
    }
    public Set getReservationRequestsForOwner() {
        return this.reservationRequestsForOwner;
    }
    
    public void setReservationRequestsForOwner(Set reservationRequestsForOwner) {
        this.reservationRequestsForOwner = reservationRequestsForOwner;
    }




}


