package org.nmiljkovic.site;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.HashSet;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.nmiljkovic.dao.AircraftRepository;
import org.nmiljkovic.dao.AirportRepository;
import org.nmiljkovic.dao.FlightRepository;
import org.nmiljkovic.dao.GateRepository;
import org.nmiljkovic.dao.RadarRepository;
import org.nmiljkovic.dto.FlightDataDto;
import org.nmiljkovic.exceptions.AicraftNotFoundException;
import org.nmiljkovic.exceptions.AirportNotFoundException;
import org.nmiljkovic.exceptions.GateNotFoundException;
import org.nmiljkovic.exceptions.NotSupportedFileException;
import org.nmiljkovic.models.Aircraft;
import org.nmiljkovic.models.Airport;
import org.nmiljkovic.models.Flight;
import org.nmiljkovic.models.Gate;
import org.nmiljkovic.models.Radar;
import org.primefaces.model.UploadedFile;

@ManagedBean
@RequestScoped
public class ImportFlightsView {
    
    private static final AircraftRepository aircraftRepo;
    private static final AirportRepository airportRepo;
    private static final GateRepository gateRepo;
    private static final RadarRepository radarRepo;
    private static final FlightRepository flightRepo;
    
    static {
        aircraftRepo = new AircraftRepository();
        airportRepo = new AirportRepository();
        gateRepo = new GateRepository();
        radarRepo = new RadarRepository();
        flightRepo = new FlightRepository();
    }
    
    public ImportFlightsView() {
    }
    
    private UploadedFile file;
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void storeFlightData(FlightDataDto flightData) throws GateNotFoundException, GateNotFoundException, AirportNotFoundException, AicraftNotFoundException {
        Aircraft aircraft = aircraftRepo.getAircraftById(flightData.aircraftId);
        if (aircraft == null) {
            throw new AicraftNotFoundException(flightData.aircraftId);
        }

        Airport departureAirport = airportRepo.getAirportById(flightData.departureAirport);
        if (departureAirport == null) {
            throw new AirportNotFoundException(flightData.departureAirport);
        }

        Airport arrivalAirport = airportRepo.getAirportById(flightData.arrivalAirport);
        if (arrivalAirport == null) {
            throw new AirportNotFoundException(flightData.arrivalAirport);
        }

        Gate departureGate = gateRepo.getGate(flightData.departureAirport, flightData.departureTerminal, flightData.departureGate);
        if (departureGate == null) {
            throw new GateNotFoundException(flightData.departureGate, flightData.departureAirport);
        }

        Gate arrivalGate = gateRepo.getGate(flightData.arrivalAirport, flightData.arrivalTerminal, flightData.arrivalGate);
        if (arrivalGate == null) {
            throw new GateNotFoundException(flightData.arrivalGate, flightData.arrivalAirport);
        }
        
        List<Radar> radars = radarRepo.getRadarList(flightData.radars);
        
        byte isCharter = (byte)(flightData.charter ? 1 : 0);
        Flight newFlight = new Flight(flightData.flightNo, aircraft, 
                arrivalAirport, departureAirport, arrivalGate, departureGate, 
                isCharter, flightData.startTime, flightData.startTime, 
                flightData.duration, "P");
        
        flightRepo.createFlight(newFlight, radars);
    }
    
    public void upload() {
        if(file != null) {
            FacesMessage message;
            FlightDataDto flightData;
            
            try {
                String fileContent = new String(file.getContents(), "UTF-8");
                if (file.getFileName().endsWith("csv")) {
                    // Parse CSV
                } else if (file.getFileName().endsWith("json")) {
                    Gson gson = new Gson();
                    JsonParser parser = new JsonParser();
                    JsonArray array = parser.parse(fileContent).getAsJsonArray();
                    for (JsonElement elem : array){
                        JsonObject obj = elem.getAsJsonObject();
                        flightData = FlightDataDto.fromJson(obj);
                        storeFlightData(flightData);
                    }
                } else {
                    throw new NotSupportedFileException();
                }
                
                message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            } catch (JsonSyntaxException exc) {
                // TODO ERROR HANDLING
                message = new FacesMessage("ERROR", "ERROR #1 " + exc.getMessage());
            } catch (UnsupportedEncodingException exc) {
                message = new FacesMessage("ERROR", "ERROR #2 " + exc.getMessage());
            } catch (ParseException exc) {
                message = new FacesMessage("ERROR", "ERROR #3 " + exc.getMessage());
            } catch (NotSupportedFileException exc) {
                message = new FacesMessage("ERROR", "ERROR #4 " + exc.getMessage());
            } catch (AicraftNotFoundException exc) {
                message = new FacesMessage("ERROR", "ERROR #5 " + exc.getMessage());
            } catch (AirportNotFoundException exc) {
                message = new FacesMessage("ERROR", "ERROR #6 " + exc.getMessage());
            } catch (GateNotFoundException exc) {
                message = new FacesMessage("ERROR", "ERROR #7 " + exc.getMessage());
            } catch (Exception exc) {
                message = new FacesMessage("ERROR", "ERROR #0 " + exc.getMessage());
            }
            
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
