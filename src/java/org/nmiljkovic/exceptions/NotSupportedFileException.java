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
public class NotSupportedFileException extends Exception {
    public NotSupportedFileException() {
        super("Files supported: XML and JSON");
    }
}
