/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.runservice.objects;

import java.io.Serializable;

/**
 *
 * @author Eduardo
 */
public class Servicio implements Serializable {
    private int idServicio;
    private String nombreServicio;
    private String nombreEjecutable;
    private boolean ejecutando;
    
    public Servicio(){
    }
    
    public Servicio(int idServicio, String nombreServicio, String nombreEjecutable, boolean ejecutando) {
        this.idServicio = idServicio;
        this.nombreServicio = nombreServicio;
        this.nombreEjecutable = nombreEjecutable;
        this.ejecutando = ejecutando;
    }

    public int getIdServicio() {
        return idServicio;
    }

    public void setIdServicio(int idServicio) {
        this.idServicio = idServicio;
    }

    public String getNombreServicio() {
        return nombreServicio;
    }

    public void setNombreServicio(String nombreServicio) {
        this.nombreServicio = nombreServicio;
    }

    public String getNombreEjecutable() {
        return nombreEjecutable;
    }

    public void setNombreEjecutable(String nombreEjecutable) {
        this.nombreEjecutable = nombreEjecutable;
    }
    
    public boolean getEjecutando(){
        return this.ejecutando;
    }
    
    public void setEjecutando(boolean ejecutando){
        this.ejecutando = ejecutando;
    }      
    
}
