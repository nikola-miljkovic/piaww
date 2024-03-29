package org.nmiljkovic.models;
// Generated Aug 28, 2017 6:04:08 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

/**
 * AircraftType generated by hbm2java
 */
public class AircraftType  implements java.io.Serializable {


     private Integer id;
     private Manufacturer manufacturer;
     private String name;
     private BigDecimal length;
     private Integer speed;
     private String license;
     private int capacity;
     private Set aircrafts = new HashSet(0);

    public AircraftType() {
    }

	
    public AircraftType(Manufacturer manufacturer, String license, int capacity) {
        this.manufacturer = manufacturer;
        this.license = license;
        this.capacity = capacity;
    }
    public AircraftType(Manufacturer manufacturer, String name, BigDecimal length, Integer speed, String license, int capacity, Set aircrafts) {
       this.manufacturer = manufacturer;
       this.name = name;
       this.length = length;
       this.speed = speed;
       this.license = license;
       this.capacity = capacity;
       this.aircrafts = aircrafts;
    }
   
    public Integer getId() {
        return this.id;
    }
    
    public void setId(Integer id) {
        this.id = id;
    }
    public Manufacturer getManufacturer() {
        return this.manufacturer;
    }
    
    public void setManufacturer(Manufacturer manufacturer) {
        this.manufacturer = manufacturer;
    }
    public String getName() {
        return this.name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    public BigDecimal getLength() {
        return this.length;
    }
    
    public void setLength(BigDecimal length) {
        this.length = length;
    }
    public Integer getSpeed() {
        return this.speed;
    }
    
    public void setSpeed(Integer speed) {
        this.speed = speed;
    }
    public String getLicense() {
        return this.license;
    }
    
    public void setLicense(String license) {
        this.license = license;
    }
    public int getCapacity() {
        return this.capacity;
    }
    
    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
    public Set getAircrafts() {
        return this.aircrafts;
    }
    
    public void setAircrafts(Set aircrafts) {
        this.aircrafts = aircrafts;
    }




}


