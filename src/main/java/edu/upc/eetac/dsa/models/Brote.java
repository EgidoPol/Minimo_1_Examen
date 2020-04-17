package edu.upc.eetac.dsa.models;

import java.util.LinkedList;

public class Brote {

    ////////Atributos//////////
    private String ID;
    private LinkedList<Caso> casos;
    ///////////////////////////

    //Constructor
    public Brote(String ID){
        this.ID = ID;
        casos = new LinkedList<Caso>();
    }

    //Gets necesarios
    public String getID() {return ID;}
    public LinkedList<Caso> getCasos() {return casos;}
    public Caso getCaso(int i){return casos.get(i);}

    //Sets necesarios
    public void addCaso(Caso caso){this.casos.add(caso);}
    public void setCasos(LinkedList<Caso> casosList) {
        for (Caso c: casosList) {
            casos.add(c);
        }
    }

}
