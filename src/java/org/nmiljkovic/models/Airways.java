package org.nmiljkovic.models;
// Generated Aug 20, 2017 9:06:03 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Airways generated by hbm2java
 */
public class Airways  implements java.io.Serializable {


     private int id;
     private String name;
     private String country;
     private String website;
     private String email;
     private String address;
     private Set users = new HashSet(0);
     private Set aircrafts = new HashSet(0);

    public Airways() {
    }

	
    public Airways(int id, String name, String country, String website, String email, String address) {
        this.id = id;
        this.name = name;
        this.country = country;
        this.website = website;
        this.email = email;
        this.address = address;
    }
    public Airways(int id, String name, String country, String website, String email, String address, Set users, Set aircrafts) {
       this.id = id;
       this.name = name;
       this.country = country;
       this.website = website;
       this.email = email;
       this.address = address;
       this.users = users;
       this.aircrafts = aircrafts;
    }
   
    public int getId() {
        return this.id;
    }
    
    public void setId(int id) {
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




}


