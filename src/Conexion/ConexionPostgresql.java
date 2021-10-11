package Conexion;

import java.sql.DriverManager;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

public class ConexionPostgresql implements Conexion {

    Connection ccn = null;

    public ConexionPostgresql() {
        try {
            Class.forName ("org.postgresql.Driver");
            String conexion = "jdbc:postgresql://localhost/tranposrte?user=julian&password=julian";
//            System.out.println("URI: " + conexion);
            ccn = DriverManager.getConnection(conexion);
        } catch (SQLException e) {
            {
                JOptionPane.showMessageDialog(null, "CONEXION ERRONEA: SQLException: " + e);
                JOptionPane.showMessageDialog(null, "FINALIZANDO PROGRAMA");
                System.exit(0);
            }
        } 
        catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "CONEXION ERRONEA: ClassNotFoundException: " + e);
        }
//        System.out.println("Conexion exitosa");
    }

    @Override
    public Connection getConnection() {
        return ccn;
    }

    @Override
    public Statement createStatement() {
        Statement stement = null;
        try {
            stement = ccn.createStatement();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO CREAR STATEMENT" + e);
        }
        return stement;
    }

    @Override
    public void Desconexion() {
        try {
            ccn.close();
            System.exit(0);
        } catch (SQLException ex) {
            Logger.getLogger(ConexionPostgresql.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
