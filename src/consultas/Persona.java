package consultas;

import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class Persona extends Entity implements PersonaEntity {
    
    private int id;
    private String cedula;
    private String nombres;
    private String apellidos;
    private String celular;
    private String direccion;
    private String telefono;
    private String correoElectronico;

    public Persona() {}

    public Persona(String cedula, String nombres, String apellidos, 
            String celular, String direccion, String telefono, 
            String correoElectronico) {
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }

    public Persona(int id, String cedula, String nombres, String apellidos, 
            String celular, String direccion, String telefono, 
            String correoElectronico) {
        this.id = id;
        this.cedula = cedula;
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.celular = celular;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }
    

    @Override
    public Persona getOne(int id) {
        Persona persona = null;
        try {
            String sql = "select * from persona where id="+id;
            System.out.println(sql);
            Statement st = ccn.createStatement();
            ResultSet rs = st.executeQuery(sql);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO CONSULAR LA INFORMACION DE LA PERSONA " + e);
        }
        return persona;
    }
    
    @Override
    public boolean guardar() {
        boolean guardado = false;
        try {
            String sql = "INSERT INTO persona(cedula, nombres, apellidos, celular, direccion, telefono, correoelectronico)\n "
                    + "	VALUES ('"+this.cedula+"', '"+this.nombres+"', '"+this.apellidos+"', '"+this.celular+"', '"+this.direccion+"', "
                    + "'"+this.telefono+"', '"+this.correoElectronico+"')";
            System.out.println(sql);
            Statement st = ccn.createStatement();
            st.executeUpdate(sql, Statement.RETURN_GENERATED_KEYS);
            ResultSet rs = st.getGeneratedKeys();
            if(rs.next()) {
                this.id = rs.getInt(1);
            }
            guardado = true;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "NO SE PUDO CONSULAR LA INFORMACION DE LA PERSONA " + e);
        }
        return guardado;
    }
    
    //setters y getters

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCedula() {
        return cedula;
    }

    public void setCedula(String cedula) {
        this.cedula = cedula;
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }
}