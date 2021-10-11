package consultas;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Salario extends Entity implements SalarioEntity {

    private int id;
    private long base;
    private int valorHora;
    private long horasExtraDiu;
    private long horasExtraNoc;

    public Salario() {
    }

    public Salario(long base, int valorHora, long horasExtraDiu, long horasExtraNoc) {
        this.base = base;
        this.valorHora = valorHora;
        this.horasExtraDiu = horasExtraDiu;
        this.horasExtraNoc = horasExtraNoc;
    }

    public Salario(int id, long base, int valorHora, long horasExtraDiu, long horasExtraNoc) {
        this.id = id;
        this.base = base;
        this.valorHora = valorHora;
        this.horasExtraDiu = horasExtraDiu;
        this.horasExtraNoc = horasExtraNoc;
    }

    @Override
    public Salario getOne(int id) {
        Salario salario = null;
        try {
            String sql = "select * from salario where id=" + id;
            Statement st = ccn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO CONSULTAR EL SALARIO " + e);
        }
        return salario;
    }

    @Override
    public boolean guardar() {
        boolean guardado = false;
        try {
            String sql = "INSERT INTO salario(base, valor_hora, horas_extra_diu, horas_extra_noc)\n"
                    + "	VALUES (" + this.base + ", " + this.valorHora + ", " + this.horasExtraDiu + ", " + horasExtraNoc + ");";
            System.out.println(sql);
            Statement st = ccn.createStatement();
            st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                this.id = rs.getInt(1);
            }
            guardado = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO GUARDAR EL SALARIO DEL EMPLEADO: SQLException: " + e);
        }
        return guardado;
    }
    
    @Override
    public boolean eliminar() {
        boolean eliminado = false;
        try {
            Statement st = ccn.createStatement();
            String sql = "DELETE FROM salario WHERE id="+this.id;
            System.out.println(sql);
            st.execute(sql);
            eliminado = true;
        } catch (SQLException e) {
            System.out.println("NO SE PUDO ELIMINAR EL SALARIO: SQLException: " + e);
        }
        return eliminado;
    }
    
    @Override
    public boolean actualizar() {
        boolean editado = false;
        try {
            Statement st = ccn.createStatement();
            String sql = "UPDATE salario SET base="+this.base+", valor_hora="+this.valorHora+", \n"
                    + "horas_extra_diu="+this.horasExtraDiu+", horas_extra_noc="+this.horasExtraNoc+" \n"
                    + "WHERE id="+this.id;
            System.out.println("-- "+sql);
            st.executeUpdate(sql);
            editado = true;
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO ACTUALIZAR EL SALARIO DEL EMPLEADO: SQLException: " + e);
        }
        return editado;
    }

    //getters y setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getBase() {
        return base;
    }

    public void setBase(long base) {
        this.base = base;
    }

    public int getValorHora() {
        return valorHora;
    }

    public void setValorHora(int valorHora) {
        this.valorHora = valorHora;
    }

    public long getHorasExtraDiu() {
        return horasExtraDiu;
    }

    public void setHorasExtraDiu(long horasExtraDiu) {
        this.horasExtraDiu = horasExtraDiu;
    }

    public long getHorasExtraNoc() {
        return horasExtraNoc;
    }

    public void setHorasExtraNoc(long horasExtraNoc) {
        this.horasExtraNoc = horasExtraNoc;
    }

}
