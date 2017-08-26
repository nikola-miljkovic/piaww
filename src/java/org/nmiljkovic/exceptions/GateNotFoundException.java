/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.nmiljkovic.exceptions;

/**
 *
 * @author nimi
 */
public class GateNotFoundException extends Exception {
    public GateNotFoundException(String gate, String airportId) {
        super("Gate " + gate + " not found on airport " + airportId + ".");
    }

    public GateNotFoundException(int gateId) {
        super("Did not find gate with id: " + gateId); //To change body of generated methods, choose Tools | Templates.
    }
}
