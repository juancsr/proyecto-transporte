package consultas;

public interface SalarioEntity {

    public Salario getOne(int id);
    public boolean guardar();
    public boolean eliminar();
}
