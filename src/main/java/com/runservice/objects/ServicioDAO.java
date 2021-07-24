/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.runservice.objects;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduardo
 */
public class ServicioDAO {
    SQLiteConnection sqlitecon;
    Statement stm;
    ResultSet rs;
    PreparedStatement pstm;
    
    public ServicioDAO(){
        this.sqlitecon = new SQLiteConnection();
    }
    
    public ArrayList<Servicio> obtenerServicios() {
        ArrayList<Servicio> servicios = new ArrayList<Servicio>();
        
        try{            
            this.stm = this.sqlitecon.getCon().createStatement();
            this.rs = this.stm.executeQuery("select * from servicios");

            while(rs.next()){
                Servicio servicio = new Servicio();
                servicio.setIdServicio(rs.getInt("id_servicio"));
                servicio.setNombreServicio(rs.getString("nombre_servicio"));
                servicio.setNombreEjecutable(rs.getString("nombre_ejecutable"));
                servicio.setEjecutando(rs.getBoolean("ejecutando"));
                
                servicios.add(servicio);
            }           
        }catch(SQLException ex){
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return servicios;
    }
    
    public void crearServicio(Servicio servicio){
        try {
            this.pstm = this.sqlitecon.getCon().prepareStatement("insert into servicios(nombre_servicio,nombre_ejecutable,ejecutando) values(?,?,?)");
            this.pstm.setString(1, servicio.getNombreServicio());
            this.pstm.setString(2, servicio.getNombreEjecutable());
            this.pstm.setBoolean(3, servicio.getEjecutando());
            this.pstm.execute();
        } catch (SQLException ex) {
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizarServicio(Servicio servicio){
        try {
            this.pstm = this.sqlitecon.getCon().prepareStatement("update servicios set nombre_servicio=?, nombre_ejecutable=? where id_servicio=?");
            this.pstm.setString(1, servicio.getNombreServicio());
            this.pstm.setString(2, servicio.getNombreEjecutable());
            this.pstm.setInt(3, servicio.getIdServicio());
            this.pstm.execute();
            
        } catch (SQLException ex) {
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void actualizarEjecutandoServicio(Servicio servicio){
        try {
            this.pstm = this.sqlitecon.getCon().prepareStatement("update servicios set ejecutando=? where id_servicio=?");
            this.pstm.setBoolean(1, servicio.getEjecutando());
            this.pstm.setInt(2, servicio.getIdServicio());
            this.pstm.execute();            
        } catch (SQLException ex) {
            Logger.getLogger(ServicioDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void eliminarServicio(Servicio servicio){
        
    }
}
