/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.dto;

import com.google.gson.JsonObject;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 *
 * @author milja
 */
public class FlightDataDto {
    
    public String flightNo;
    public String airways;
    public String departureAirport;
    public int departureTerminal;
    public String departureGate;
    public String arrivalAirport;
    public int arrivalTerminal;
    public String arrivalGate;
    public Date startTime;
    public int duration;
    public String[] radars;
    public boolean charter;
    public int aircraftId;
    public String[] crew;

    public String getFlightNo() {
        return flightNo;
    }

    public void setFlightNo(String flightNo) {
        this.flightNo = flightNo;
    }

    public String getAirways() {
        return airways;
    }

    public void setAirways(String airways) {
        this.airways = airways;
    }

    public String getDepartureAirport() {
        return departureAirport;
    }

    public void setDepartureAirport(String departureAirport) {
        this.departureAirport = departureAirport;
    }

    public int getDepartureTerminal() {
        return departureTerminal;
    }

    public void setDepartureTerminal(int departureTerminal) {
        this.departureTerminal = departureTerminal;
    }

    public String getDepartureGate() {
        return departureGate;
    }

    public void setDepartureGate(String departureGate) {
        this.departureGate = departureGate;
    }

    public String getArrivalAirport() {
        return arrivalAirport;
    }

    public void setArrivalAirport(String arrivalAirport) {
        this.arrivalAirport = arrivalAirport;
    }

    public int getArrivalTerminal() {
        return arrivalTerminal;
    }

    public void setArrivalTerminal(int arrivalTerminal) {
        this.arrivalTerminal = arrivalTerminal;
    }

    public String getArrivalGate() {
        return arrivalGate;
    }

    public void setArrivalGate(String arrivalGate) {
        this.arrivalGate = arrivalGate;
    }
    
    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String[] getRadars() {
        return radars;
    }

    public void setRadars(String[] radars) {
        this.radars = radars;
    }

    public boolean isCharter() {
        return charter;
    }

    public void setCharter(boolean charter) {
        this.charter = charter;
    }

    public int getAircraftId() {
        return aircraftId;
    }

    public void setAircraftId(int aircraftId) {
        this.aircraftId = aircraftId;
    }

    public String[] getCrew() {
        return crew;
    }

    public void setCrew(String[] crew) {
        this.crew = crew;
    }
    
    
    
    public FlightDataDto() {
        
    }
    
    public static FlightDataDto fromJson(JsonObject obj) throws ParseException {
        FlightDataDto data = new FlightDataDto();
        data.setFlightNo(obj.get("FlightNo").getAsString());
        data.setAirways(obj.get("Air").getAsString());
        
        data.setDepartureAirport(obj.get("DepAP").getAsString());
        data.setDepartureTerminal(Integer.parseInt(obj.get("DTerm").getAsString().substring(13)));
        data.setDepartureGate(obj.get("DGate").getAsString());
        data.setArrivalAirport(obj.get("ArrAP").getAsString());
        data.setArrivalTerminal(Integer.parseInt(obj.get("ATerm").getAsString().substring(13)));
        data.setArrivalGate(obj.get("AGate").getAsString());
        
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyyKK:mm:ss a");
        String inputDate = obj.get("StartDate").getAsString() + obj.get("StartTime").getAsString();
        data.setStartTime(parser.parse(inputDate));
        
        SimpleDateFormat parserForDuration = new SimpleDateFormat("KK:mm");
        Date durationTime = parserForDuration.parse(obj.get("Duration").getAsString());
        data.setDuration(durationTime.getHours() * 60 + durationTime.getMinutes());
        
        String routeArray = obj.get("RouteRadar").getAsString();
        data.setRadars(routeArray.substring(1, routeArray.length() - 1).split(","));
        
        data.setCharter(obj.get("Charter").getAsBoolean());
        data.setAircraftId(obj.get("IDPlane").getAsInt());
        
        String crewArray = obj.get("Crew").getAsString();
        data.setCrew(crewArray.substring(1, crewArray.length() - 1).split(","));
        
        return data;
    }
    
    
    public static FlightDataDto fromCSV(String[] nextLine) throws ParseException {
        FlightDataDto data = new FlightDataDto();
        data.setFlightNo(nextLine[0]);
        data.setAirways(nextLine[1]);
        
        data.setDepartureAirport(nextLine[2]);
        data.setDepartureTerminal(Integer.parseInt(nextLine[3].substring(13)));
        data.setDepartureGate(nextLine[4]);
        data.setArrivalAirport(nextLine[5]);
        data.setArrivalTerminal(Integer.parseInt(nextLine[6].substring(13)));
        data.setArrivalGate(nextLine[7]);
        
        SimpleDateFormat parser = new SimpleDateFormat("dd/MM/yyyyKK:mm:ss a");
        String inputDate = nextLine[8] + nextLine[9];
        data.setStartTime(parser.parse(inputDate));
        
        SimpleDateFormat parserForDuration = new SimpleDateFormat("KK:mm");
        Date durationTime = parserForDuration.parse(nextLine[10]);
        data.setDuration(durationTime.getHours() * 60 + durationTime.getMinutes());
        
        String routeArray = nextLine[11];
        data.setRadars(routeArray.substring(1, routeArray.length() - 1).split(","));
        
        data.setCharter(Boolean.parseBoolean(nextLine[12]));
        data.setAircraftId(Integer.parseInt(nextLine[13]));
        
        String crewArray = nextLine[14];
        data.setCrew(crewArray.substring(1, crewArray.length() - 1).split(","));
        
        return data;
    }
}
