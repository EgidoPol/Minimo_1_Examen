package edu.upc.eetac.dsa.models;

import util.RandomID;

import java.util.Date;

public class Caso {
    ////////Atributos//////////
    private String nombre;
    private String apellidos;
    private String ID;
    private Date fechaDeNacimiento;
    private Date fechaInforme;
    private String nivelDeRiesgo;
    private String genero;
    private String email;
    private int telefono;
    private String direccion;
    private String clasificacion; //A: confirmado, B: sospechoso: C: no caso
    ///////////////////////////
    //Constructor
    public Caso(String nombre, String apellidos, Date fechaDeNacimiento, Date fechaInforme, String nivelDeRiesgo, String genero, String email, int telefono, String direccion, String clasificacion){
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.ID = RandomID.getId();
        this.fechaDeNacimiento = fechaDeNacimiento;
        this.fechaInforme = fechaInforme;
        this.nivelDeRiesgo = nivelDeRiesgo;
        this.genero = genero;
        this.email = email;
        this.telefono = telefono;
        this.direccion = direccion;
        this.clasificacion = clasificacion;
    }
    ////////////////////
    //Gets
    public String getID() {return ID;}
    public String getClasificacion() {return clasificacion;}
    public Date getFechaInforme() {return fechaInforme;}
    public String getNombre() {return nombre;}
    public String getApellidos() {return apellidos;}
    public int getTelefono() {return telefono;}
    public String getNivelDeRiesgo() {return nivelDeRiesgo;}

    //Sets
    public void setClasificacion(String clasificacion) {this.clasificacion = clasificacion;}
    public void setFechaInforme(Date fechaInforme) {this.fechaInforme = fechaInforme;}
}
