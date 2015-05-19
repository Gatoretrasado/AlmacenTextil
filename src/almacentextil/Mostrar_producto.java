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
    JComboBox cmbox_ID;
    JTable tablaBD;
    JScrollPane scroll;

    //Para poder conectarse a la base de datos
    private final conexionDB meConecto = new conexionDB();

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
        scroll = new JScrollPane(tablaBD);
        panelMostrar.add(tablaBD);
        panelMostrar.add(scroll);

        btn_Buscar = new JButton("Buscar");
        btn_Buscar.setBounds(334, 5, 90, 20);
        panelMostrar.add(btn_Buscar);

        cmbox_ID = new JComboBox();
        cmbox_ID.setBounds(15, 5, 50, 25);
        panelMostrar.add(cmbox_ID);

        lblID = new JLabel("ID: ");
        panelMostrar.add(lblID);

        meConecto();

        btn_Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                int buscarA = (Integer) cmbox_ID.getSelectedItem();

                try {
                    //Para establecer el modelo al JTable
                    DefaultTableModel modelo = new DefaultTableModel();
                    tablaBD.setModel(modelo);

                    //Para conectarnos a nuestra base de datos
                    DriverManager.registerDriver(new com.mysql.jdbc.Driver());
                    Connection conexion = DriverManager.getConnection(null, null, null);

                    //Para ejecutar la consulta
                    String query = "SELECT * FROM `producto` WHERE `Id_producto` = " + buscarA + "";
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

        Connection miConexion = (Connection) meConecto.ConectarMysql();

        try (Statement st = miConexion.createStatement()) {
            //Nuestra sentencia SQL
            String sentencia = "SELECT * FROM `producto`";

            // Ejecutamos la sentencia y almacenamos el resultado
            ResultSet rs = st.executeQuery(sentencia);

            //Recoremos el ResultSet para ir mostrando los datos.
            while (rs.next()) {
                int id = rs.getInt("Id_producto");
                String nombre = rs.getString("Nombre");
                String desc = rs.getString("Descrip");
                int Precio = rs.getInt("Precio_uni");
                cmbox_ID.addItem(rs.getInt("Id_producto"));

                //Mostramos el Resultado
                System.out.format("%s, %s, %s, %s\n", id, nombre, desc, Precio);
            }
            st.close();
        } catch (SQLException e) {
            System.err.println("Se ha producido un Error! ");
            System.err.println(e.getMessage());
        }
    }
}
