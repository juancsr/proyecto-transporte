package consultas;

import java.util.ArrayList;

public interface EmpleadoEntity {
    
    public ArrayList<Empleado> consultarEmpleados();
    public long calcularSalario(int id);
    public boolean guardar();
}
