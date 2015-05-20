package almacentextil;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

final class Cliente extends JFrame {

    private final JTabbedPane tabbedPane;
    private JPanel pestaña01, pestaña02, pestaña03;
    private JTable tablaBD;
    private JComboBox cmbox_CIF, cmbox_CIF2, cmbox_Pais1, cmbox_Pais2;
    private JScrollPane scroll;

    //Para poder conectarse a la base de datos
    private final conexionDB meConecto = new conexionDB();

    public Cliente() {

        setTitle(" -- Cliente -- ");
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

        JLabel lbl_Deuda = new JLabel("Deuda:");
        lbl_Deuda.setBounds(210, 185, 80, 25);
        pestaña01.add(lbl_Deuda);

        JTextField txt_Deuda = new JTextField();
        txt_Deuda.setBounds(270, 185, 100, 25);
        txt_Deuda.addKeyListener(new KeyAdapter() {    //Solo caracteres numericos 
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b') && (caracter != '.') && (caracter == ',')) {
                    e.consume();
                }
            }
        });
        pestaña01.add(txt_Deuda);

        JButton btn_Aceptar = new JButton("Aceptar");
        btn_Aceptar.setBounds(220, 290, 80, 25);
        pestaña01.add(btn_Aceptar);

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
                    txt_Deuda.setText("");

                } catch (Exception err) {
                    System.out.println("Error: " + err);
                }
            }
        });

        btn_Aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                boolean insertado = false;
                String CIF = txt_CIF.getText();
                String Nombre = txt_Nombre.getText();
                String Direccion = txt_Direccion.getText();
                String Ciudad = txt_Ciudad.getText();
                String Pais = (String) cmbox_Pais1.getSelectedItem();
                int Telefono = Integer.parseInt(txt_Telefono.getText());
                float Deuda = Float.parseFloat(txt_Deuda.getText());

                Connection miConexion = (Connection) meConecto.ConectarMysql();

                try (Statement st = miConexion.createStatement()) {

                    //Para ejecutar la consulta
                    String query = "INSERT INTO `almacentextil`.`cliente` (`CIF_Cli`, `Nombre`, `Direccion`, `Ciudad`, `Pais`, `Telefono`, `Deuda`) VALUES ('" + CIF + "', '" + Nombre + "', '" + Direccion + "', '" + Ciudad + "', '" + Pais + "', '" + Telefono + "', '" + Deuda + "')";

                    Statement s = miConexion.createStatement();
                    st.executeUpdate(query);
                    insertado = true;

                    miConexion.close();

                    if (insertado == true) {
                        JOptionPane.showMessageDialog(null, "Insertado Con Exito!", "Guardado", JOptionPane.INFORMATION_MESSAGE);
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
        pestaña02.add(txt_Telefono);

        JLabel lbl_Deuda = new JLabel("Deuda:");
        lbl_Deuda.setBounds(210, 185, 80, 25);
        pestaña02.add(lbl_Deuda);

        JTextField txt_Deuda = new JTextField();
        txt_Deuda.setBounds(270, 185, 100, 25);
        pestaña02.add(txt_Deuda);

        JButton btn_Eliminar = new JButton("Eliminar");
        btn_Eliminar.setBounds(30, 290, 80, 25);
        btn_Eliminar.setBackground(Color.red);
        btn_Eliminar.setForeground(Color.white);
        pestaña02.add(btn_Eliminar);

        JButton btn_Aceptar = new JButton("Aceptar");
        btn_Aceptar.setBounds(220, 290, 80, 25);
        pestaña02.add(btn_Aceptar);

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
                    txt_Deuda.setText("");

                } catch (Exception err) {
                    System.out.println("Error: " + err);
                }
            }
        });

        btn_Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                String modificarA = (String) cmbox_CIF2.getSelectedItem();

                Connection miConexion = (Connection) meConecto.ConectarMysql();

                try (Statement st = miConexion.createStatement()) {

                    //Para ejecutar la consulta
                    String query = "SELECT * FROM `cliente` WHERE `CIF_Cli` LIKE '" + modificarA + "'";
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
                        txt_Deuda.setText(rs.getString("Deuda"));
                        
                    }
                    rs.close();
                    miConexion.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
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
                    String query = "SELECT * FROM `cliente` WHERE `CIF_Cli` LIKE '" + buscarA + "'";
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

        Connection miConexion = (Connection) meConecto.ConectarMysql();

        try (Statement st = miConexion.createStatement()) {

            //Para establecer el modelo al JTable
            DefaultTableModel modelo = new DefaultTableModel();
            tablaBD.setModel(modelo);

            //Nuestra sentencia SQL
            String sentencia = "SELECT * FROM `cliente`";
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
                cmbox_CIF.addItem(rs.getString("CIF_Cli"));
                cmbox_CIF2.addItem(rs.getString("CIF_Cli"));
                cmbox_Pais1.addItem(rs.getString("Pais"));
                cmbox_Pais2.addItem(rs.getString("Pais"));
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
    }
}
