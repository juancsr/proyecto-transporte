package consultas;

public interface PersonaEntity {

    public Persona getOne(int id);
    public boolean guardar();
    public boolean eliminar();
}
