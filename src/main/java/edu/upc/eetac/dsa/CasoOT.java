package edu.upc.eetac.dsa;

import edu.upc.eetac.dsa.models.Caso;

import java.util.Date;

public class CasoOT {
    private String nombre;
    private String apellidos;
    private String ID;
    private Date fechaInforme;
    private String nivelDeRiesgo;
    private int telefono;
    private String clasificacion; //A: confirmado, B: sospechoso: C: no caso

    public CasoOT(Caso caso){
        this.nombre = caso.getNombre();
        this.apellidos = caso.getApellidos();
        this.ID = caso.getID();
        this.fechaInforme = caso.getFechaInforme();
        this.nivelDeRiesgo = caso.getNivelDeRiesgo();
        this.telefono = caso.getTelefono();
        this.clasificacion = caso.getClasificacion();
    }
}
