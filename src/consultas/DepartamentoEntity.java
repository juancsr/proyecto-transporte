package consultas;

import java.util.ArrayList;

public interface DepartamentoEntity {
   
    public Departamento getOne(int id);
    public ArrayList<Departamento> getAll();
    public int getIdByName();
}
