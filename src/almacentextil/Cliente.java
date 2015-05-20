package almacentextil;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

final class Cliente extends JFrame {

    private JTabbedPane tabbedPane;
    private JPanel pestaña01, pestaña02, pestaña03;
    private JLabel lbl_CIF;
    private JTable tablaBD;
    private JComboBox cmbox_CIF;
    private JScrollPane scroll;

    //Para poder conectarse a la base de datos
    private final conexionDB meConecto = new conexionDB();

    public Cliente() {

        setTitle(" -- Cliente -- ");
        setSize(450, 400);
        setBackground(Color.gray);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

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

    public void crearPestaña01() {
        pestaña01 = new JPanel();
        pestaña01.setLayout(null);

        lbl_CIF = new JLabel("CIF:");
        lbl_CIF.setBounds(10, 15, 50, 25);
        pestaña01.add(lbl_CIF);

        JTextField id_alb1 = new JTextField();
        id_alb1.setBounds(80, 15, 100, 25);
        pestaña01.add(id_alb1);

        JLabel etq_ped1 = new JLabel("Nombre:");
        etq_ped1.setBounds(10, 60, 150, 25);
        pestaña01.add(etq_ped1);

        JTextField id_ped1 = new JTextField();
        id_ped1.setBounds(80, 60, 300, 25);
        pestaña01.add(id_ped1);

        JLabel etq_env1 = new JLabel("Direccion:");
        etq_env1.setBounds(10, 105, 100, 25);
        pestaña01.add(etq_env1);

        JTextField fech_env1 = new JTextField();
        fech_env1.setBounds(80, 105, 300, 25);
        pestaña01.add(fech_env1);

        JLabel etq_ciu1 = new JLabel("Ciudad:");
        etq_ciu1.setBounds(10, 145, 80, 25);
        pestaña01.add(etq_ciu1);

        JTextField ciudad1 = new JTextField();
        ciudad1.setBounds(80, 145, 100, 25);
        pestaña01.add(ciudad1);

        JLabel etq_pais1 = new JLabel("Pais:");
        etq_pais1.setBounds(210, 145, 80, 25);
        pestaña01.add(etq_pais1);

        JComboBox pais1 = new JComboBox();
        pais1.addItem("España");
        pais1.addItem("E.E.U.U");
        pais1.addItem("Inglaterra");
        pais1.addItem("Japon");
        pais1.addItem("Mexico");
        pais1.addItem("China");
        pais1.setBounds(270, 145, 100, 25);
        pestaña01.add(pais1);

        JLabel etq_telf1 = new JLabel("Telefono:");
        etq_telf1.setBounds(10, 185, 80, 25);
        pestaña01.add(etq_telf1);

        JTextField telf1 = new JTextField();
        telf1.setBounds(80, 185, 100, 25);
        pestaña01.add(telf1);

        JLabel etq_deu1 = new JLabel("Deuda:");
        etq_deu1.setBounds(210, 185, 80, 25);
        pestaña01.add(etq_deu1);

        JTextField deu1 = new JTextField();
        deu1.setBounds(270, 185, 100, 25);
        pestaña01.add(deu1);

        JButton aceptar1 = new JButton("Aceptar");
        aceptar1.setBounds(220, 290, 80, 25);
        pestaña01.add(aceptar1);

        JButton limpiar1 = new JButton("Limpiar");
        limpiar1.setBounds(320, 290, 80, 25);
        pestaña01.add(limpiar1);
    }

    public void crearPestaña02() {
        pestaña02 = new JPanel();
        pestaña02.setLayout(null);

        JLabel etq_alb2 = new JLabel("CIF:");
        etq_alb2.setBounds(10, 15, 50, 25);
        pestaña02.add(etq_alb2);

        JTextField id_alb2 = new JTextField();
        id_alb2.setBounds(80, 15, 100, 25);
        pestaña02.add(id_alb2);

        JLabel etq_ped2 = new JLabel("Nombre:");
        etq_ped2.setBounds(10, 60, 150, 25);
        pestaña02.add(etq_ped2);

        JTextField id_ped2 = new JTextField();
        id_ped2.setBounds(80, 60, 300, 25);
        pestaña02.add(id_ped2);

        JLabel etq_env2 = new JLabel("Direccion:");
        etq_env2.setBounds(10, 105, 100, 25);
        pestaña02.add(etq_env2);

        JTextField fech_env2 = new JTextField();
        fech_env2.setBounds(80, 105, 300, 25);
        pestaña02.add(fech_env2);

        JLabel etq_ciu2 = new JLabel("Ciudad:");
        etq_ciu2.setBounds(10, 145, 80, 25);
        pestaña02.add(etq_ciu2);

        JTextField ciudad2 = new JTextField();
        ciudad2.setBounds(80, 145, 100, 25);
        pestaña02.add(ciudad2);

        JLabel etq_pais2 = new JLabel("Pais:");
        etq_pais2.setBounds(210, 145, 80, 25);
        pestaña02.add(etq_pais2);

        JComboBox pais2 = new JComboBox();
        pais2.addItem("España");
        pais2.addItem("E.E.U.U");
        pais2.addItem("Inglaterra");
        pais2.addItem("Japon");
        pais2.addItem("Mexico");
        pais2.addItem("China");
        pais2.setBounds(270, 145, 100, 25);
        pestaña02.add(pais2);

        JLabel etq_telf2 = new JLabel("Telefono:");
        etq_telf2.setBounds(10, 185, 80, 25);
        pestaña02.add(etq_telf2);

        JTextField telf2 = new JTextField();
        telf2.setBounds(80, 185, 100, 25);
        pestaña02.add(telf2);

        JLabel etq_deu2 = new JLabel("Deuda:");
        etq_deu2.setBounds(210, 185, 80, 25);
        pestaña02.add(etq_deu2);

        JTextField deu2 = new JTextField();
        deu2.setBounds(270, 185, 100, 25);
        pestaña02.add(deu2);

        JButton eliminar = new JButton("Eliminar");
        eliminar.setBounds(30, 290, 80, 25);
        eliminar.setBackground(Color.red);
        eliminar.setForeground(Color.white);
        pestaña02.add(eliminar);

        JButton aceptar2 = new JButton("Aceptar");
        aceptar2.setBounds(220, 290, 80, 25);
        pestaña02.add(aceptar2);

        JButton limpiar2 = new JButton("Limpiar");
        limpiar2.setBounds(320, 290, 80, 25);
        pestaña02.add(limpiar2);
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
