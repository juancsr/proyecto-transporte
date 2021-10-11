package consultas;

import Conexion.ConexionPostgresql;
import Conexion.Conexion;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public abstract class Entity {
    protected Conexion ccn = new ConexionPostgresql(); 
}
