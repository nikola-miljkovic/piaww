/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.site;

import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.google.gson.JsonSyntaxException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import org.nmiljkovic.dto.FlightDataDto;
import org.primefaces.event.FileUploadEvent;

import org.primefaces.model.UploadedFile;

@ManagedBean
@RequestScoped
public class ImportFlightsView {
    
    public ImportFlightsView() {
    }
    
    private UploadedFile file;
 
    public UploadedFile getFile() {
        return file;
    }
 
    public void setFile(UploadedFile file) {
        this.file = file;
    }
     
    public void upload() {
        //file = e.getFile();
        if(file != null) {
            FacesMessage message;
            
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
                        FlightDataDto flightData = FlightDataDto.fromJson(obj);
                    }
                }
                
                message = new FacesMessage("Succesful", file.getFileName() + " is uploaded.");
            } catch (JsonSyntaxException exc) {
                // TODO ERROR HANDLING
                message = new FacesMessage("ERROR", "ERROR #1 " + exc.getMessage());
            } catch (UnsupportedEncodingException exc) {
                message = new FacesMessage("ERROR", "ERROR #2 " + exc.getMessage());
            } catch (ParseException exc) {
                message = new FacesMessage("ERROR", "ERROR #3 " + exc.getMessage());
            } catch (Exception exc) {
                message = new FacesMessage("ERROR", "ERROR #0 " + exc.getMessage());
            }
            
            FacesContext.getCurrentInstance().addMessage(null, message);
        }
    }
}
