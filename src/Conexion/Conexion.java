package Conexion;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class Conexion {

Connection ccn = null;
Statement st = null;

    public Conexion(){
        
        Connection ccn = null;
        try {
            String user = "julian";
            String pass = "julian";
            String db = "tranposrte";
            String host = "localhost";
            String port = "5432";
            // URI ejemplo: postgres://USERNAME:PASSWORD@babar.elephantsql.com:5432/jszlmeae 
            String conexion = "postgres://"+user+":"+pass+"@b"+host+":"+port+"/"+db;
            ccn = DriverManager.getConnection(conexion);
            st = ccn.createStatement();
        } catch (Exception e) {
            {
                JOptionPane.showMessageDialog(null, "CONEXION ERRONEA " + e);  
            }
        }
        //** Este es la conexion original a accdb **
//        try 
//            {
//                String rutafile = "C:\\Users\\juliv\\Documents\\Universidad trabajos\\POO\\Proyecto\\Nomina1.accdb";
//                String Url = "jdbc:ucanaccess://" + rutafile;
//                ccn = DriverManager.getConnection(Url);
//                st = ccn.createStatement();
//            } catch (SQLException e) 
//                {
//                    JOptionPane.showMessageDialog(null, "CONEXION ERRONEA " + e);  
//                }
    }
    
    public Connection getConnection(){
        return ccn;
    }

    
    public void Desconexion(){
        try 
            {
                ccn.close();            
                System.exit(0);
            } catch (SQLException ex) 
                {
                    Logger.getLogger(Conexion.class.getName()).log(Level.SEVERE, null, ex);
                }
    }
    
}