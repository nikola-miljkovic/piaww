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
public class AicraftNotFoundException extends Exception {
    public AicraftNotFoundException(int aircraftId) {
        super("Aircraft with id " + aircraftId + " not found.");
    }
}