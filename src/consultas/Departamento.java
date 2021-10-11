package consultas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Departamento extends Entity implements DepartamentoEntity {
    private int id;
    private String nombre;

    public Departamento() {}

    public Departamento(String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public Departamento(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }
    
    @Override
    public Departamento getOne(int id) {
        Departamento departamento = null;
        try {
            String sql = "select * from departamento where id="+id;
            Statement st = ccn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO CONSULAR LA INFORMACION DEL DEPARTAMENTO" + e);  
        }
        return departamento;
    }
    
    @Override
    public ArrayList<Departamento> getAll() {
        ArrayList<Departamento> departamentos = new ArrayList<>();
        try {
            String sql = "select * from departamento";
            Statement st = ccn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                int idDep = rs.getInt("id");
                String nombreDep = rs.getString("nombre");
                Departamento dep = new Departamento(idDep, nombreDep);
                departamentos.add(dep);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDIERON OBTENER TODOS LOS DEPARTAMENTOS" + e);  
        }
        return departamentos;
    }
    
    @Override
    public int getIdByName() {
        int id = 0;
         try {
            String sql = "SELECT id FROM departamento where nombre='"+this.nombre+"'";
            System.out.println(sql);
            Statement st = ccn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                id = rs.getInt("id");
            }
        } catch (SQLException e) {
             System.out.println("No se pudo obtener el id del departamento: SQLException: " + e);
        }
        return id;
    }
    
    // getters y settes
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
