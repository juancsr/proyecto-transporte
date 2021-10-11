package Conexion;

import java.sql.*;

public interface Conexion {
    Connection getConnection();
    void Desconexion();
    Statement createStatement();
}
