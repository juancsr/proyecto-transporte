package consultas;

import Conexion.ConexionPostgresql;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Empleado extends Entity implements EmpleadoEntity {
    
    private int id;
    private Genero genero;
    private Salario salario;
    private Persona persona;
    private Departamento depto;

    public Empleado() {}

    public Empleado(Genero genero, Salario salario, Persona persona, 
            Departamento depto) {
        this.genero = genero;
        this.salario = salario;
        this.persona = persona;
        this.depto = depto;
    }

    public Empleado(int id, Genero genero, Salario salario, Persona persona, 
            Departamento depto) {
        this.id = id;
        this.genero = genero;
        this.salario = salario;
        this.persona = persona;
        this.depto = depto;
    }
    
    @Override
    public ArrayList<Empleado> consultarEmpleados() {
        ArrayList generos = new ArrayList<Genero>();
        
        try {
            String sql = "select * from empleado";
            Statement st = ccn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDIERON TRAER LOS GENEROS " + e);
            
        }finally {
            ccn.Desconexion();
        }
        return generos;
    }
    
    @Override
    public long calcularSalario(int id) {
        long salario = 0;
        Empleado empleado = null;
        try {
            String sql = "select * from empleado where id = " + id;
            Statement st = ccn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDIERON TRAER LOS GENEROS " + e);  
        }finally {
            ccn.Desconexion();
        }
        return salario;
    }
    
    @Override
    public boolean guardar() {
        boolean guardado = false;
        System.err.println(this.genero.getNombre());
        System.err.println(this.depto.getNombre());
        try {
            if (!this.salario.guardar()) {
                return guardado;
            }
            if (!this.persona.guardar()) {
                return guardado;
            }
            int genId = this.genero.getIdByName();
            int deptoId = this.depto.getIdByName();
            Statement st = ccn.createStatement();
            String sql = "INSERT INTO empleado (salario_id, persona_id, genero_id, depto_id)\n"
                    + "VALUES("+this.salario.getId()+", "+this.persona.getId()+", "+genId+", "+deptoId+")";
            System.out.println(sql);
            st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                this.id = rs.getInt(1);
            }
            guardado = true;
        } catch (SQLException e) {
            System.out.println("NO SE PUDO REGISTRAR AL EMPLEADO: SQLException: " + e);
        }
        
        return guardado;
    };
}
