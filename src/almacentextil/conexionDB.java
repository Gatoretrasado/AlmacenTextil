package almacentextil;

import java.sql.*;

public class conexionDB {

    public static void main(String[] args) {
        try {
            //Creamos nuestra conexion a la BD mysql
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost:3306/almacentextil?zeroDateTimeBehavior=convertToNull";
            String usuarioDB = "root";
            String passwordDB = "";

            Connection conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB);

            /*//Nuestra sentencia SQL
            String query = "SELECT * FROM `producto`";

            //Creamos la sentencia en Java
            Statement st = conexion.createStatement();

            // Ejecutamos la sentencia y almacenamos el resultado
            ResultSet rs = st.executeQuery(query);

            //Recoremos el ResultSet para ir mostrando los datos.
            while (rs.next()) {
                int id = rs.getInt("Id_producto");
                String nombre = rs.getString("Nombre");
                String desc = rs.getString("Descrip");
                int Precio = rs.getInt("Precio_uni");

                //Mostramos el Resultado
                System.out.format("%s, %s, %s, %s\n", id, nombre, desc, Precio);
            }
            st.close();*/
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Se ha producido un Error! ");
            System.err.println(e.getMessage());
        }
    }
}
