package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.models.Caso;

import java.util.LinkedList;

public class BroteOT {
    String ID;
    LinkedList<Caso> casos;

    public BroteOT(String ID, LinkedList<Caso> casos){
        this.ID = ID;
        this.casos = casos;
    }
}
