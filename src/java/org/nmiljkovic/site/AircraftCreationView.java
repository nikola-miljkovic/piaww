/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.site;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import java.io.Serializable;
import java.math.BigDecimal;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;
import javax.annotation.PostConstruct;
import javax.faces.bean.ViewScoped;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import org.nmiljkovic.dao.UserRepository;
import org.nmiljkovic.dao.AircraftRepository;
import org.nmiljkovic.dao.AirportRepository;
import org.nmiljkovic.dao.AirwaysRepository;
import org.nmiljkovic.dao.FlightRepository;
import org.nmiljkovic.dao.GateRepository;
import org.nmiljkovic.dao.ManufactureRepository;
import org.nmiljkovic.dao.RadarRepository;
import org.nmiljkovic.dto.FlightDataDto;
import org.nmiljkovic.exceptions.AicraftNotFoundException;
import org.nmiljkovic.exceptions.AirportNotFoundException;
import org.nmiljkovic.exceptions.GateNotFoundException;
import org.nmiljkovic.models.Aircraft;
import org.nmiljkovic.models.AircraftType;
import org.nmiljkovic.models.Airport;
import org.nmiljkovic.models.Airways;
import org.nmiljkovic.models.Flight;
import org.nmiljkovic.models.Gate;
import org.nmiljkovic.models.Manufacturer;
import org.nmiljkovic.models.Radar;
import org.nmiljkovic.models.User;
import org.primefaces.model.UploadedFile;

@ManagedBean
@ViewScoped
public class AircraftCreationView implements Serializable {
    
    private UploadedFile img;
    private List<Manufacturer> manufacturers;
    private int manufacturer;
    private String name;
    private BigDecimal length;
    private Integer speed;
    private String license;
    private int capacity;
    
    public AircraftCreationView() {
    }
    
    @PostConstruct
    void prepare() {
        ManufactureRepository manufactureRepo = new ManufactureRepository();
                
        manufacturers = manufactureRepo.getManufacturers();
    }

    public UploadedFile getImg() {
        return img;
    }

    public void setImg(UploadedFile img) {
        this.img = img;
    }

    public int getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(int manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getLength() {
        return length;
    }

    public void setLength(BigDecimal length) {
        this.length = length;
    }

    public Integer getSpeed() {
        return speed;
    }

    public void setSpeed(Integer speed) {
        this.speed = speed;
    }

    public String getLicense() {
        return license;
    }

    public void setLicense(String license) {
        this.license = license;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public List<Manufacturer> getManufacturers() {
        return manufacturers;
    }

    public void setManufacturers(List<Manufacturer> manufacturers) {
        this.manufacturers = manufacturers;
    }
    
    public void createAircraft() throws FileNotFoundException, IOException {
        if (img != null) {
            ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
            String relativeWebPath = "/upload";
            String absoluteDiskPath = externalContext.getRealPath(relativeWebPath);
            Path file = Paths.get(absoluteDiskPath + "/" + getName().toLowerCase().replace(" ", "_") + ".jpg");
            Files.write(file, img.getContents());
        }
        
        ManufactureRepository manuRepo = new ManufactureRepository();
        Manufacturer manufacturer = null;
        for (Manufacturer manu : manufacturers) {
            if (manu.getId() == this.manufacturer) {
                manufacturer = manu;
                break;
            }
        }
        
        if (manufacturer == null) {
            return;
        }
        
        AircraftType aircraft = new AircraftType(manufacturer, this.name, this.length, this.speed, this.license, this.capacity, null);
        AircraftRepository aircraftRepo = new AircraftRepository();
        aircraftRepo.save(aircraft);
    }
}
