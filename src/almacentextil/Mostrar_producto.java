package almacentextil;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.*;

public final class Mostrar_producto extends JFrame {

    JPanel panelMostrar;
    JButton btn_Buscar;
    JLabel lblID;
    JScrollPane areaLista;
    JSpinner spnID;
    JTable tablaBD;

    String servidor = "jdbc:mysql://localhost:3306/almacentextil?zeroDateTimeBehavior=convertToNull";
    String usuarioDB = "root";
    String passwordDB = "";

    public Mostrar_producto() {
        visible();
    }

    void visible() {

        panelMostrar = (JPanel) this.getContentPane();
        panelMostrar.setLayout(null);

        this.setTitle(" -- Mostrar Productos --");
        this.setSize(450, 400);
        this.setBackground(Color.gray);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        tablaBD = new JTable();
        tablaBD.setBounds(5, 35, 420, 320);
        panelMostrar.add(tablaBD);
        
        btn_Buscar = new JButton("Buscar");
        btn_Buscar.setBounds(334, 5, 90, 20);
        panelMostrar.add(btn_Buscar);

        spnID = new JSpinner();
        spnID.setBounds(15, 5, 35, 25);
        panelMostrar.add(spnID);

        lblID = new JLabel("ID: ");
        panelMostrar.add(lblID);

        meConecto();

        btn_Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                try {
                    //Para establecer el modelo al JTable
                    DefaultTableModel modelo = new DefaultTableModel();
                    tablaBD.setModel(modelo);
                    
                    //Para conectarnos a nuestra base de datos
                    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                    Connection conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB);
                    
                    //Para ejecutar la consulta
                    String query = "SELECT * FROM `producto`";
                    Statement s = conexion.createStatement();
                    
                    //Ejecutamos la consulta que escribimos en la caja de texto
                    //y los datos lo almacenamos en un ResultSet
                    ResultSet rs = s.executeQuery(query);

                    //Obteniendo la informacion de las columnas que estan siendo consultadas
                    ResultSetMetaData rsMd = rs.getMetaData();

                    //La cantidad de columnas que tiene la consulta
                    int cantidadColumnas = rsMd.getColumnCount();

                    //Establecer como cabezeras el nombre de las colimnas
                    for (int i = 1; i <= cantidadColumnas; i++) {
                        modelo.addColumn(rsMd.getColumnLabel(i));
                    }
                    //Creando las filas para el JTable
                    while (rs.next()) {
                        Object[] fila = new Object[cantidadColumnas];
                        for (int i = 0; i < cantidadColumnas; i++) {
                            fila[i] = rs.getObject(i + 1);
                        }
                        modelo.addRow(fila);
                    }
                    rs.close();
                    conexion.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    public void meConecto() {
        try {
            //Creamos nuestra conexion a la BD mysql
            Class.forName("com.mysql.jdbc.Driver");
            
            Connection conexion = DriverManager.getConnection(servidor, usuarioDB, passwordDB);

            //Nuestra sentencia SQL
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
            st.close();
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Se ha producido un Error! ");
            System.err.println(e.getMessage());
        }
    }
}
