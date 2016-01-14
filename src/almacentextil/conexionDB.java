package almacentextil;

import java.sql.*;
import javax.swing.JOptionPane;

public class conexionDB {

    public Connection ConectarMysql() {

        Connection conexion = null;

        try {

            //Creamos nuestra conexion a la BD mysql
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost:3306/almacentextil?zeroDateTimeBehavior=convertToNull";
            String usuarioDB = "root";
            String passwordDB = "";

            conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB);

        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error1 en la Conexión con la BD " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, ex, "Error2 en la Conexión con la BD " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex, "Error3 en la Conexión con la BD " + ex.getMessage(), JOptionPane.ERROR_MESSAGE);
            conexion = null;
        } finally {
            return conexion;
        }
    }
}
