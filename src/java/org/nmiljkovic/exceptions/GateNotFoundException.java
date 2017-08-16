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
}
