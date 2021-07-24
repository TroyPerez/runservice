/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.runservice.controller;

import com.runservice.objects.Servicio;
import com.runservice.objects.ServicioDAO;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author Eduardo
 */
public class ServicioController {
    private ServicioDAO serviciodao;
    
    public ServicioController(){
        this.serviciodao = new ServicioDAO();
    }
    
    //public ArrayList<Servicio> obtenerServicios(){
    public LinkedHashMap obtenerServicios(){        
        ArrayList<Servicio> servicios = serviciodao.obtenerServicios();
        
        ArrayList<Integer> idServicio = new ArrayList<Integer>();
        ArrayList<String> nombreServicio = new ArrayList<String>();
        ArrayList<String> nombreEjecutable = new ArrayList<String>();
        ArrayList<Boolean> ejecutando = new ArrayList<Boolean>();        
        
        LinkedHashMap<String, ArrayList> data = new LinkedHashMap<String, ArrayList>();
        
        LinkedHashMap<String, ArrayList<Integer>> idServicioMap = new LinkedHashMap<String, ArrayList<Integer>>();
        LinkedHashMap<String, ArrayList<String>> nombreServicioMap = new LinkedHashMap<String, ArrayList<String>>();
        LinkedHashMap<String, ArrayList<String>> nombreEjecutableMap = new LinkedHashMap<String, ArrayList<String>>();
        LinkedHashMap<String, ArrayList<Boolean>> ejecutandoMap = new LinkedHashMap<String, ArrayList<Boolean>>();
        
        for(Servicio servicio : servicios){
            idServicio.add(servicio.getIdServicio());
            nombreServicio.add(servicio.getNombreServicio());
            nombreEjecutable.add(servicio.getNombreEjecutable());
            ejecutando.add(servicio.getEjecutando());
        }        
        data.put("id_servicio", idServicio);
        data.put("nombre_servicio", nombreServicio);
        data.put("nombre_ejecutable", nombreEjecutable);
        data.put("ejecutando", ejecutando);
        
        return data;
    }
    
    public void agregarActualizarServicio(Object[] data, boolean agregar){
        Servicio servicio = new Servicio();
        
        if(agregar){//Crear
            servicio.setNombreServicio(String.valueOf(data[0]));
            servicio.setNombreEjecutable(String.valueOf(data[1]));
            servicio.setEjecutando(false);
            this.serviciodao.crearServicio(servicio);
        }else{//Actualizar
            servicio.setIdServicio((int)data[0]);
            servicio.setNombreServicio((String)data[1]);
            servicio.setNombreEjecutable((String)data[2]);            
            this.serviciodao.actualizarServicio(servicio);
        }
        
        
        
        
    }
    
}
