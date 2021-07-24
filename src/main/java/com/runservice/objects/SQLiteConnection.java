/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.runservice.objects;

import java.sql.Connection;
import java.sql.DriverManager;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
/**
 *
 * @author Eduardo
 */
public class SQLiteConnection {
    private Connection con;
        
    public SQLiteConnection(){
        this.openConnection();
    }
    
    private void openConnection(){
        try{
            Path path = FileSystems.getDefault().getPath("").toAbsolutePath();
            Path dbPath = FileSystems.getDefault().getPath(
                path.toString(), "src", "main", "java", "com", 
                "runservice", "data", "runservices_db.db"
            );
            Class.forName("org.sqlite.JDBC");
            this.con = DriverManager.getConnection("jdbc:sqlite:" + dbPath);
            }catch(Exception ex){
                Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
            }        
    }
    
    public void closeConnection(){
        try {
            this.con.close();
        } catch (SQLException ex) {
            Logger.getLogger(SQLiteConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Connection getCon() {
        return con;
    }
    
}
