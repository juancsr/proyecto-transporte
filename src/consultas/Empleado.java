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

    private long vhed; // valor horas extra diurnas
    private long vhen; // valor horas extra nocturnas
    private long transporte; // valor de subsidio transporte
    private long salarioTotal;

    public Empleado() {
    }

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

    public Empleado(int id, Genero genero, Salario salario, Persona persona, 
            Departamento depto, long vhed, long vhen, long transporte, long salarioTotal) {
        this.id = id;
        this.genero = genero;
        this.salario = salario;
        this.persona = persona;
        this.depto = depto;
        this.vhed = vhed;
        this.vhen = vhen;
        this.transporte = transporte;
        this.salarioTotal = salarioTotal;
    }
    
    

    @Override
    public ArrayList<Empleado> consultarEmpleados() {
        ArrayList<Empleado> empleados = new ArrayList<>();

        try {
            String sql = "SELECT e.id, p.cedula, p.nombres, p.apellidos, \n"
                    + "p.celular, g.nombre , p.direccion , p.telefono ,\n"
                    + "p.correoelectronico, d.nombre , s.base ,\n"
                    + "s.valor_hora , s.horas_extra_diu , s.horas_extra_noc , \n"
                    + "(s.valor_hora * s.horas_extra_diu) vhed,\n"
                    + "(s.valor_hora * s.horas_extra_noc) vhen,\n"
                    + "106.454 transporte,\n"
                    + "(s.base + 106454 + (s.valor_hora * s.horas_extra_noc) + (s.valor_hora * s.horas_extra_diu)) salario\n"
                    + "FROM empleado e,\n"
                    + "persona p ,\n"
                    + "departamento d ,\n"
                    + "genero g ,\n"
                    + "salario s\n"
                    + "WHERE e.persona_id = p.id \n"
                    + "AND e.depto_id = d.id\n"
                    + "AND e.genero_id = g.id \n"
                    + "AND e.salario_id = s.id ;";
            Statement st = ccn.createStatement();
            ResultSet rs = st.executeQuery(sql);
            while(rs.next()) {
                int empId = rs.getInt(1);
                String empCed = rs.getString(2);
                String empNom = rs.getString(3);
                String empApe = rs.getString(4);
                String empCel = rs.getString(5);
                String empGen = rs.getString(6);
                Genero gen = new Genero(empGen);
                String empDir = rs.getString(7);
                String empTel = rs.getString(8);
                String empEmail = rs.getString(9);
                Persona per = new Persona(empCed, empNom, empApe, empCel, empDir, empTel, empEmail);
                String empDepto = rs.getString(10);
                Departamento dep = new Departamento(empDepto);
                long base = rs.getLong(11);
                int valorHora = rs.getInt(12);
                long horasExtraDiu = rs.getLong(13);
                long hroasExtraNoc = rs.getLong(14);
                Salario sal = new Salario(base, valorHora, horasExtraDiu, hroasExtraNoc);
                long vhed = rs.getLong(15);
                long vhen = rs.getLong(16);
                long transporte = rs.getLong(17);
                long salario = rs.getLong(18);
                
                Empleado emp = new Empleado(empId, gen, sal, per, dep, vhed, vhen, transporte, salario);
                empleados.add(emp);
            }
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDIERON TRAER LOS EMPLEADOS " + e);

        } 
//        finally {
//            ccn.Desconexion();
//        }
        return empleados;
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
        } finally {
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
                    + "VALUES(" + this.salario.getId() + ", " + this.persona.getId() + ", " + genId + ", " + deptoId + ")";
            System.out.println(sql);
            st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            if (rs.next()) {
                this.id = rs.getInt(1);
            }
            guardado = true;
        } catch (SQLException e) {
            System.out.println("NO SE PUDO REGISTRAR AL EMPLEADO: SQLException: " + e);
        }

        return guardado;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Genero getGenero() {
        return genero;
    }

    public void setGenero(Genero genero) {
        this.genero = genero;
    }

    public Salario getSalario() {
        return salario;
    }

    public void setSalario(Salario salario) {
        this.salario = salario;
    }

    public Persona getPersona() {
        return persona;
    }

    public void setPersona(Persona persona) {
        this.persona = persona;
    }

    public Departamento getDepto() {
        return depto;
    }

    public void setDepto(Departamento depto) {
        this.depto = depto;
    }

    public long getVhed() {
        return vhed;
    }

    public void setVhed(long vhed) {
        this.vhed = vhed;
    }

    public long getVhen() {
        return vhen;
    }

    public void setVhen(long vhen) {
        this.vhen = vhen;
    }

    public long getTransporte() {
        return transporte;
    }

    public void setTransporte(long transporte) {
        this.transporte = transporte;
    }

    public long getSalarioTotal() {
        return salarioTotal;
    }

    public void setSalarioTotal(long salarioTotal) {
        this.salarioTotal = salarioTotal;
    }

    
}
