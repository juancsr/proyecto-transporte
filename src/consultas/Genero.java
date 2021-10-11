package consultas;

import Conexion.ConexionPostgresql;
import Conexion.Conexion;
import java.awt.List;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;


public class Genero extends Entity implements GeneroEntity {
    
    private int id;
    private String nombre;

    public Genero() {}

    public Genero(String nombre) {
        this.nombre = nombre;
    }

    public Genero(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    @Override
    public ArrayList<Genero> ObtenerGeneros() {
        ArrayList generos = new ArrayList<Genero>();
        
        try {
            String sql = "SELECT * FROM genero";
            Statement st = ccn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                int idGen = rs.getInt("id");
                String nombre = rs.getString("nombre");
                Genero genero = new Genero(idGen, nombre);
                generos.add(genero);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDIERON TRAER LOS GENEROS " + e);  
        }finally {
//            this.ccn.Desconexion();
        }
        return generos;
    }
    
    @Override
    public int getIdByName() {
        int id = 0;
         try {
            String sql = "SELECT id FROM genero where nombre='"+this.nombre+"'";
             System.out.println(sql);
            Statement st = ccn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
             System.out.println("No se pudo obtener el id del genero: SQLException: " + e);
        }
        return id;
    }
    
    //getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
}
