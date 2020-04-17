package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.exceptions.BroteNotFoundException;
import edu.upc.eetac.dsa.exceptions.CasoNotFoundException;
import edu.upc.eetac.dsa.models.Brote;
import edu.upc.eetac.dsa.models.Caso;
import org.apache.log4j.Logger;


import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedList;

public class Covid19ManagerImpl implements Covid19Manager{

    private static Logger log = Logger.getLogger(Covid19ManagerImpl.class);
    private static Covid19Manager instance;
    private HashMap<String,Brote> brotesMap;

    //Constructor para Singleton
    private Covid19ManagerImpl(){
        this.brotesMap = new HashMap<>();

    }
    public static Covid19Manager getInstance(){
        if(instance == null){
            instance = new Covid19ManagerImpl();
        }
        return instance;
    }

    //Añadir un Brote nuevo
    @Override
    public Brote newBrote(String ID) {
        Brote brote = new Brote(ID);
        brotesMap.put(ID,brote);
        log.info("Brote añadido correctamente");
        return brote;
    }
    //Listado de brotes disponibles
    @Override
    public LinkedList<Brote> listOfBrotes() {
        if(brotesMap != null){
            LinkedList<Brote> brotesList = new LinkedList<Brote>(brotesMap.values());
            log.info("Lista de brotes: " + brotesList);
            return brotesList;
        }
        else{
            log.error("No hay ningún brote en la lista");
            return null;
        }
    }
    //Añadir un caso a un Brote
    @Override
    public Caso addCaso(String ID, Caso caso) throws BroteNotFoundException {
        Brote brote = getBrote(ID);
        if(brote != null){
            brote.addCaso(caso);
            log.info("Caso añadido correctamente a " + brote.getID());
            return caso;
        }
        else throw new BroteNotFoundException();
    }
    //Ordenar casos de un Brote
    @Override
    public LinkedList<Caso> sortByClassification(String ID) throws BroteNotFoundException {
        Brote brote = getBrote(ID);
        if(brote != null)
        {
            LinkedList<Caso> casoList = brote.getCasos();
            Collections.sort(casoList, new Comparator<Caso>() {
                @Override
                public int compare(Caso c1, Caso c2) {
                    if(c1.getClasificacion().equals(c2.getClasificacion())){
                        return c1.getFechaInforme().compareTo(c2.getFechaInforme());
                    }
                    else
                        return c1.getClasificacion().compareTo(c2.getClasificacion());
                }
            });
            log.info("Lista ordenada por clasificacion: " + casoList.toString());
            return casoList;
        }
        else
            throw new BroteNotFoundException();
    }

    @Override
    public Brote getBrote(String ID) throws BroteNotFoundException {
        Brote foundBrote = brotesMap.get(ID);
        if(foundBrote != null){
            log.info("El brote: " + foundBrote.getID() + " ha sido encontrado");
            return foundBrote;
        }
        else{
            throw new BroteNotFoundException();
        }
    }

    @Override
    public HashMap<String, Brote> getBrotes() {
        return null;
    }

    @Override
    public void liberarRecursos() {
        this.brotesMap.clear();
    }


}
