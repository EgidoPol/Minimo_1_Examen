package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.exceptions.BroteNotFoundException;
import edu.upc.eetac.dsa.exceptions.CasoNotFoundException;
import edu.upc.eetac.dsa.models.Brote;
import edu.upc.eetac.dsa.models.Caso;

import java.util.HashMap;
import java.util.LinkedList;

public interface Covid19Manager {

    public Brote newBrote(String ID);
    public LinkedList<Brote> listOfBrotes();
    public Caso addCaso(String ID, Caso caso) throws BroteNotFoundException;
    public LinkedList<Caso> sortByClassification(String ID) throws BroteNotFoundException;


    public Brote getBrote(String ID) throws BroteNotFoundException;
    public HashMap<String,Brote> getBrotes();

    public void liberarRecursos();

}
