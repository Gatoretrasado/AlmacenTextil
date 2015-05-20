package almacentextil;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

final class Producto extends JFrame {

    private ArrayList<String> PAISES = new ArrayList<>();
    private final JTabbedPane tabbedPane;
    private JPanel pestaña01, pestaña02, pestaña03;
    private JTable tablaBD;
    private JComboBox cmbox_CIF, cmbox_CIF2, cmbox_Pais1, cmbox_Pais2;
    private JScrollPane scroll;

    //Para poder conectarse a la base de datos
    private final conexionDB meConecto = new conexionDB();

    public Producto() {

        setTitle(" -- Proveedor -- ");
        setSize(450, 400);
        setBackground(Color.gray);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);

        // Create the tab pages
        crearPestaña01();
        crearPestaña02();
        crearPestaña03();

        // Create a tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Crear", pestaña01);
        tabbedPane.addTab("Modificar", pestaña02);
        tabbedPane.addTab("Mostrar", pestaña03);
        topPanel.add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }

    //--------------------------------------------------------------------------
    //Primmera Pestaña
    public void crearPestaña01() {
        pestaña01 = new JPanel();
        pestaña01.setLayout(null);

        JLabel lbl_CIF = new JLabel("CIF:");
        lbl_CIF.setBounds(10, 15, 50, 25);
        pestaña01.add(lbl_CIF);

        JTextField txt_CIF = new JTextField();
        txt_CIF.setBounds(80, 15, 100, 25);
        txt_CIF.addKeyListener(new KeyAdapter() {    //Solo caracteres numericos y solo 9 digitos
            @Override
            public void keyTyped(KeyEvent e) {
                int limitador = 9;
                char caracter = e.getKeyChar();
                if (txt_CIF.getText().length() >= limitador) {
                    e.consume();
                }
            }
        });
        pestaña01.add(txt_CIF);

        JLabel lbl_Nombre = new JLabel("Nombre:");
        lbl_Nombre.setBounds(10, 60, 150, 25);
        pestaña01.add(lbl_Nombre);

        JTextField txt_Nombre = new JTextField();
        txt_Nombre.setBounds(80, 60, 300, 25);
        txt_Nombre.addKeyListener(new KeyAdapter() {    //Solo caracteres Alfabeticos
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (Character.isDigit(ch)) {
                    e.consume();
                }
            }
        });
        pestaña01.add(txt_Nombre);

        JLabel lbl_Dicreccion = new JLabel("Direccion:");
        lbl_Dicreccion.setBounds(10, 105, 100, 25);
        pestaña01.add(lbl_Dicreccion);

        JTextField txt_Direccion = new JTextField();
        txt_Direccion.setBounds(80, 105, 300, 25);
        pestaña01.add(txt_Direccion);

        JLabel lbl_Ciudad = new JLabel("Ciudad:");
        lbl_Ciudad.setBounds(10, 145, 80, 25);
        pestaña01.add(lbl_Ciudad);

        JTextField txt_Ciudad = new JTextField();
        txt_Ciudad.setBounds(80, 145, 100, 25);
        txt_Ciudad.addKeyListener(new KeyAdapter() {    //Solo caracteres Alfabeticos
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (Character.isDigit(ch)) {
                    e.consume();
                }
            }
        });
        pestaña01.add(txt_Ciudad);

        JLabel lbl_Pais = new JLabel("Pais:");
        lbl_Pais.setBounds(210, 145, 80, 25);
        pestaña01.add(lbl_Pais);

        cmbox_Pais1 = new JComboBox();
        cmbox_Pais1.setBounds(270, 145, 100, 25);
        pestaña01.add(cmbox_Pais1);

        JLabel lbl_Telefono = new JLabel("Telefono: ");
        lbl_Telefono.setBounds(10, 185, 80, 25);
        pestaña01.add(lbl_Telefono);

        JTextField txt_Telefono = new JTextField();
        txt_Telefono.setBounds(80, 185, 100, 25);
        txt_Telefono.addKeyListener(new KeyAdapter() {    //Solo caracteres numericos y solo 9 digitos
            @Override
            public void keyTyped(KeyEvent e) {
                int limitador = 9;
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b') || txt_Telefono.getText().length() >= limitador) {
                    e.consume();
                }
            }
        });
        pestaña01.add(txt_Telefono);

        JButton btn_Insertar = new JButton("Insertar");
        btn_Insertar.setBounds(220, 290, 80, 25);
        pestaña01.add(btn_Insertar);

        JButton btn_Limpiar = new JButton("Limpiar");
        btn_Limpiar.setBounds(320, 290, 80, 25);
        pestaña01.add(btn_Limpiar);

        btn_Limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    txt_CIF.setText("");
                    txt_Nombre.setText("");
                    txt_Direccion.setText("");
                    txt_Ciudad.setText("");
                    cmbox_Pais1.setSelectedIndex(0);
                    txt_Telefono.setText("");

                } catch (Exception err) {
                    System.out.println("Error: " + err);
                }
            }
        });

        btn_Insertar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                boolean insertado = false;
                String CIF = txt_CIF.getText();
                String Nombre = txt_Nombre.getText();
                String Direccion = txt_Direccion.getText();
                String Ciudad = txt_Ciudad.getText();
                String Pais = (String) cmbox_Pais1.getSelectedItem();
                int Telefono = Integer.parseInt(txt_Telefono.getText());

                Connection miConexion = (Connection) meConecto.ConectarMysql();

                try (Statement st = miConexion.createStatement()) {

                    //Para ejecutar la consulta
                    String query = "INSERT INTO `almacentextil`.`proveedor` (`CIF_Prov`, `Nombre`, `Direccion`, `Ciudad`, `Pais`, `Telefono`) VALUES ('" + CIF + "', '" + Nombre + "', '" + Direccion + "', '" + Ciudad + "', '" + Pais + "', '" + Telefono + "')";

                    Statement s = miConexion.createStatement();
                    st.executeUpdate(query);
                    insertado = true;

                    miConexion.close();

                    if (insertado == true) {
                        JOptionPane.showMessageDialog(null, "Insertado Con Exito!", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                        llenarJtable();
                    }

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Se ha producido un Error", "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                    insertado = false;
                }
            }
        });
    }

    //--------------------------------------------------------------------------
    //Segunda Pestaña
    public void crearPestaña02() {
        pestaña02 = new JPanel();
        pestaña02.setLayout(null);

        JLabel lbl_CIF = new JLabel("CIF:");
        lbl_CIF.setBounds(10, 15, 50, 25);
        pestaña02.add(lbl_CIF);

        cmbox_CIF2 = new JComboBox();
        cmbox_CIF2.setBounds(80, 15, 100, 25);
        pestaña02.add(cmbox_CIF2);

        JLabel lbl_Nombre = new JLabel("Nombre:");
        lbl_Nombre.setBounds(10, 60, 150, 25);
        pestaña02.add(lbl_Nombre);

        JTextField txt_Nombre = new JTextField();
        txt_Nombre.setBounds(80, 60, 300, 25);
        txt_Nombre.addKeyListener(new KeyAdapter() {    //Solo caracteres Alfabeticos
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (Character.isDigit(ch)) {
                    e.consume();
                }
            }
        });
        pestaña02.add(txt_Nombre);

        JLabel lbl_Direccion = new JLabel("Direccion:");
        lbl_Direccion.setBounds(10, 105, 100, 25);
        pestaña02.add(lbl_Direccion);

        JTextField txt_Direccion = new JTextField();
        txt_Direccion.setBounds(80, 105, 300, 25);
        pestaña02.add(txt_Direccion);

        JLabel lbl_Ciudad = new JLabel("Ciudad:");
        lbl_Ciudad.setBounds(10, 145, 80, 25);
        pestaña02.add(lbl_Ciudad);

        JTextField txt_Ciudad = new JTextField();
        txt_Ciudad.setBounds(80, 145, 100, 25);
        txt_Ciudad.addKeyListener(new KeyAdapter() {    //Solo caracteres Alfabeticos
            @Override
            public void keyTyped(KeyEvent e) {
                char ch = e.getKeyChar();
                if (Character.isDigit(ch)) {
                    e.consume();
                }
            }
        });
        pestaña02.add(txt_Ciudad);

        JLabel lbl_Pais = new JLabel("Pais:");
        lbl_Pais.setBounds(210, 145, 80, 25);
        pestaña02.add(lbl_Pais);

        cmbox_Pais2 = new JComboBox();
        cmbox_Pais2.setBounds(270, 145, 100, 25);
        pestaña02.add(cmbox_Pais2);

        JLabel lbl_Telefono = new JLabel("Telefono:");
        lbl_Telefono.setBounds(10, 185, 80, 25);
        pestaña02.add(lbl_Telefono);

        JTextField txt_Telefono = new JTextField();
        txt_Telefono.setBounds(80, 185, 100, 25);
        txt_Telefono.addKeyListener(new KeyAdapter() {    //Solo caracteres numericos y solo 9 digitos
            @Override
            public void keyTyped(KeyEvent e) {
                int limitador = 9;
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b') || txt_Telefono.getText().length() >= limitador) {
                    e.consume();
                }
            }
        });
        pestaña02.add(txt_Telefono);

        JButton btn_Eliminar = new JButton("Eliminar");
        btn_Eliminar.setBounds(30, 290, 80, 25);
        btn_Eliminar.setBackground(Color.red);
        btn_Eliminar.setForeground(Color.white);
        pestaña02.add(btn_Eliminar);

        JButton btn_Modificar = new JButton("Modificar");
        btn_Modificar.setBounds(220, 290, 80, 25);
        pestaña02.add(btn_Modificar);

        JButton btn_Buscar = new JButton("Buscar");
        btn_Buscar.setBounds(250, 15, 100, 25);
        pestaña02.add(btn_Buscar);

        JButton btn_Limpiar = new JButton("Limpiar");
        btn_Limpiar.setBounds(320, 290, 80, 25);
        pestaña02.add(btn_Limpiar);

        btn_Limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    cmbox_CIF2.setSelectedIndex(0);
                    txt_Nombre.setText("");
                    txt_Direccion.setText("");
                    txt_Ciudad.setText("");
                    cmbox_Pais2.setSelectedIndex(0);
                    txt_Telefono.setText("");

                } catch (Exception err) {
                    System.out.println("Error: " + err);
                }
            }
        });

        btn_Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                String buscarA = (String) cmbox_CIF2.getSelectedItem();

                Connection miConexion = (Connection) meConecto.ConectarMysql();

                try (Statement st = miConexion.createStatement()) {

                    //Para ejecutar la consulta
                    String query = "SELECT * FROM `proveedor` WHERE `CIF_Prov` LIKE '" + buscarA + "'";
                    Statement s = miConexion.createStatement();

                    //Almacenamos en un ResultSet
                    ResultSet rs = s.executeQuery(query);

                    //Obteniendo la informacion de las columnas que estan siendo consultadas
                    ResultSetMetaData rsMd = rs.getMetaData();

                    //Creando las filas para el JTable
                    while (rs.next()) {

                        txt_Nombre.setText(rs.getString("Nombre"));
                        txt_Direccion.setText(rs.getString("Direccion"));
                        txt_Ciudad.setText(rs.getString("Ciudad"));
                        cmbox_Pais2.setSelectedItem(rs.getString("Pais"));
                        txt_Telefono.setText(rs.getString("Telefono"));

                    }
                    rs.close();
                    miConexion.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });

        btn_Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                boolean eliminado = false;
                String eliminarA = (String) cmbox_CIF2.getSelectedItem();

                if (txt_Nombre.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Seleccione un proveedor", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int Borrar = JOptionPane.showConfirmDialog(null, "Desea Eliminar a: " + txt_Nombre.getText());

                    if (JOptionPane.OK_OPTION == Borrar) {
                        Connection miConexion = (Connection) meConecto.ConectarMysql();

                        try (Statement st = miConexion.createStatement()) {

                            //Para ejecutar la consulta
                            String query = "DELETE FROM `almacentextil`.`proveedor` WHERE `proveedor`.`CIF_Prov` = '" + eliminarA + "'";

                            Statement s = miConexion.createStatement();
                            st.executeUpdate(query);
                            eliminado = true;

                            miConexion.close();

                            if (eliminado == true) {
                                JOptionPane.showMessageDialog(null, "Eliminado Con Exito!", "Eliminado", JOptionPane.INFORMATION_MESSAGE);
                                llenarJtable();
                            }

                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Se ha producido un Error", "Error", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                            eliminado = false;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Eliminacion Cancelada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
        );

        btn_Modificar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                boolean modificado = false;

                String modificarA = (String) cmbox_CIF2.getSelectedItem();

                if (txt_Nombre.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Seleccione un Proveedor", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int Modificar = JOptionPane.showConfirmDialog(null, "Desea Modificar a: " + txt_Nombre.getText());

                    if (JOptionPane.OK_OPTION == Modificar) {

                        String Nombre = txt_Nombre.getText();
                        String Direccion = txt_Direccion.getText();
                        String Ciudad = txt_Ciudad.getText();
                        String Pais = (String) cmbox_Pais2.getSelectedItem();
                        int Telefono = Integer.parseInt(txt_Telefono.getText());

                        Connection miConexion = (Connection) meConecto.ConectarMysql();

                        try (Statement st = miConexion.createStatement()) {

                            //Para ejecutar la consulta
                            String query = "UPDATE `almacentextil`.`proveedor` SET `Nombre` = '" + Nombre + "', `Direccion` = '" + Direccion + "', `Ciudad` = '" + Ciudad + "', `Pais` = '" + Pais + "', `Telefono` = '" + Telefono + "' WHERE `proveedor`.`CIF_Prov` = '" + modificarA + "';";

                            Statement s = miConexion.createStatement();
                            st.executeUpdate(query);
                            modificado = true;

                            miConexion.close();

                            if (modificado == true) {
                                JOptionPane.showMessageDialog(null, "Modificado Con Exito!", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                llenarJtable();
                            }

                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Se ha producido un Error", "Error", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                            modificado = false;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Modificacion Cancelada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        });
    }

    //--------------------------------------------------------------------------
    //Tercera Pestaña
    public void crearPestaña03() {
        pestaña03 = new JPanel();
        pestaña03.setLayout(null);

        cmbox_CIF = new JComboBox();
        cmbox_CIF.setBounds(15, 5, 100, 20);
        pestaña03.add(cmbox_CIF);

        JButton btn_Buscar = new JButton("Buscar");
        btn_Buscar.setBounds(334, 5, 90, 20);
        pestaña03.add(btn_Buscar);

        tablaBD = new JTable();
        scroll = new JScrollPane(tablaBD);
        scroll.setBounds(5, 35, 420, 250);
        pestaña03.add(scroll);

        llenarJtable();

        btn_Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                String buscarA = (String) cmbox_CIF.getSelectedItem();

                Connection miConexion = (Connection) meConecto.ConectarMysql();

                try (Statement st = miConexion.createStatement()) {

                    //Para establecer el modelo al JTable
                    DefaultTableModel modelo = new DefaultTableModel();
                    tablaBD.setModel(modelo);

                    //Para ejecutar la consulta
                    String query = "SELECT * FROM `proveedor` WHERE `CIF_Prov` LIKE '" + buscarA + "'";
                    Statement s = miConexion.createStatement();

                    //Almacenamos en un ResultSet
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
                    miConexion.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

            }
        });
    }

    public void llenarJtable() {

        cmbox_CIF.removeAllItems();
        cmbox_CIF2.removeAllItems();
        cmbox_Pais1.removeAllItems();
        cmbox_Pais2.removeAllItems();
        PAISES.clear();

        Connection miConexion = (Connection) meConecto.ConectarMysql();

        try (Statement st = miConexion.createStatement()) {

            //Para establecer el modelo al JTable
            DefaultTableModel modelo = new DefaultTableModel();
            tablaBD.setModel(modelo);

            //Nuestra sentencia SQL
            String sentencia = "SELECT * FROM `proveedor`";
            Statement s = miConexion.createStatement();

            //Almacenamos en un ResultSet
            ResultSet rs = s.executeQuery(sentencia);

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
                cmbox_CIF.addItem(rs.getString("CIF_Prov"));
                cmbox_CIF2.addItem(rs.getString("CIF_Prov"));

                if (!PAISES.contains(rs.getString("Pais"))) {
                    PAISES.add(rs.getString("Pais"));
                }

                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            rs.close();
            miConexion.close();
        } catch (SQLException e) {
            System.err.println("Se ha producido un Error! ");
            System.err.println(e.getMessage());
        }

        for (String Pais : PAISES) {
            cmbox_Pais1.addItem(Pais);
            cmbox_Pais2.addItem(Pais);
        }

    }
}
