package edu.upc.eetac.dsa.exceptions;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class BroteNotFoundException extends Exception {
    private static Logger log = LogManager.getLogger(BroteNotFoundException.class);
    public BroteNotFoundException(){log.error("Brote no encontrado.");}
}
