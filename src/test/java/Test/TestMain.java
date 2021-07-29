/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Test;

import com.runservice.controller.ServicioController;
import com.runservice.objects.SQLiteConnection;
import com.runservice.views.AgregarActualizarServiciosView;
import com.runservice.views.ListaServiciosView;
import java.nio.file.Path;
import java.nio.file.FileSystems;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Eduardo
 */
public class TestMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        //testConnection();
        //testObtenerServicios();
        
    }
    
    public static void testConnection(){
        try {
            SQLiteConnection sqlitecon = new SQLiteConnection();
            Statement stm = sqlitecon.getCon().createStatement();
            ResultSet rs = stm.executeQuery("select * from servicios");
            
            while(rs.next()){
                String nombre = rs.getString("nombre_servicio");
                System.out.println(nombre);
            }
            sqlitecon.closeConnection();
        } catch (SQLException ex) {
            Logger.getLogger(TestMain.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public static void testObtenerServicios(){
        ServicioController servicioController = new ServicioController();
        
        LinkedHashMap data = servicioController.obtenerServicios();
        ArrayList idServicio = (ArrayList)data.get("id_servicio");
        ArrayList nombreServicio = (ArrayList)data.get("nombre_servicio");
        ArrayList nombreEjecutable = (ArrayList)data.get("nombre_ejecutable");
        ArrayList ejecutando = (ArrayList)data.get("ejecutando");
        
        Iterator idServicioI = (Iterator) idServicio.iterator();
        Iterator nombreServicioI = (Iterator) nombreServicio.iterator();
        Iterator nombreEjecutableI = (Iterator) nombreEjecutable.iterator();
        Iterator ejecutandoI = (Iterator) ejecutando.iterator();
        
        for(int i = 0;i < idServicio.size();i++){
            System.out.println(
                    idServicioI.next() + "\t" +
                    nombreServicioI.next() + "\t" + 
                    nombreEjecutableI.next() + "\t" +
                    ejecutandoI.next()
            );
        }
        
        
    }
    
}

/*
Hoy les traigo una forma de poder ejecutar un comando de consola en Java. La clave 
para ejecutar un comando en Java esta en los métodos exec(String command) y 
exec(String[] cmdarray).

La diferencia entre estos dos métodos Java es que el primero esta destinado a 
ejecutar comandos sin argumentos:

try {
	String cmd = "halt"; //Comando de apagado en linux
	Runtime.getRuntime().exec(cmd); 
} catch (IOException ioe) {
	System.out.println (ioe);
}
Y el segundo si, solo que deben poner cada ‘palabra’ en un índice distinto en el
arreglo Java:

try {
	String [] cmd = {"shutdown","-s","-t", "10"}; //Comando de apagado en windows
	Runtime.getRuntime().exec(cmd);
} catch (IOException ioe) {
	System.out.println (ioe);
}

Recordar que el comando se va a ejecutar en un proceso Java aparte.
Otra cosa a tener en cuenta es el retorno que brindan estos métodos. Para ello 
tenemos el objeto de tipo Process.

La clase Process posee algunos métodos interesantes, en especial el metodo 
public abstract InputStream getInputStream(), ya que con él podemos obtener un 
Stream para poder leer lo que el comando que ejecutamos escribío en la consola.

Process process = Runtime.getRuntime().exec("lsb_release -a");
InputStream inputstream = process.getInputStream();
BufferedInputStream bufferedinputstream = new BufferedInputStream(inputstream);

En mi caso, obtuve el siguiente texto al leer las lineas del buffer.
*/
