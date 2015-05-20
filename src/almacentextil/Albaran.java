package almacentextil;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public final class Albaran extends JFrame {

    private final JTabbedPane tabbedPane;
    private JTextField txt_ID;
    private JScrollPane scroll;
    private JTable tablaBD;
    private JComboBox cmbox_ID;
    private JButton btn_Limpiar, btn_Aceptar;
    private JLabel lbl_Pedido, lbl_Envio;
    private JPanel pestaña01, pestaña02, pestaña03;
    private String[] AÑOS;
    private String[] DIAS;
    final String[] MESES = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    //Para poder conectarse a la base de datos
    private final conexionDB meConecto = new conexionDB();

    public Albaran() {

        setTitle(" -- Albaran --");
        setSize(450, 400);
        setBackground(Color.gray);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);

        cargarFechas();

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
    //--------------------------------------------------------------------------
    //Primera Pestaña
    public void crearPestaña01() {

        pestaña01 = new JPanel();
        pestaña01.setLayout(null);

        JLabel lblID = new JLabel("ID Albaran:");
        lblID.setBounds(10, 15, 80, 20);
        pestaña01.add(lblID);

        txt_ID = new JTextField();
        txt_ID.setBounds(100, 15, 150, 20);
        txt_ID.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
                    e.consume();
                }
            }
        });
        pestaña01.add(txt_ID);

        lbl_Pedido = new JLabel("ID Pedido:");
        lbl_Pedido.setBounds(10, 60, 80, 20);
        pestaña01.add(lbl_Pedido);

        JTextField txt_Pedido = new JTextField();
        txt_Pedido.setBounds(100, 60, 150, 20);
        txt_Pedido.addKeyListener(new KeyAdapter() {    //Solo caracteres numericos
            @Override
            public void keyTyped(KeyEvent e) { 
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
                    e.consume();
                }
            }
        });
        pestaña01.add(txt_Pedido);

        lbl_Envio = new JLabel("Fecha Envio:");
        lbl_Envio.setBounds(10, 105, 80, 20);
        pestaña01.add(lbl_Envio);

        JComboBox cmboxDias = new JComboBox(DIAS);
        cmboxDias.setBounds(100, 105, 40, 20);
        cmboxDias.setSelectedIndex(1);
        pestaña01.add(cmboxDias);

        JComboBox cmboxMeses = new JComboBox(MESES);
        cmboxMeses.setBounds(150, 105, 85, 20);
        pestaña01.add(cmboxMeses);

        JComboBox cmboxYear = new JComboBox(AÑOS);
        cmboxYear.setBounds(250, 105, 80, 20);
        pestaña01.add(cmboxYear);

        JLabel lbllegada = new JLabel("Fecha Llegada:");
        lbllegada.setBounds(10, 145, 145, 20);
        pestaña01.add(lbllegada);

        JComboBox cmboxDias2 = new JComboBox(DIAS);
        cmboxDias2.setBounds(100, 145, 40, 20);
        cmboxDias2.setSelectedIndex(1);
        pestaña01.add(cmboxDias2);

        JComboBox cmboxMeses2 = new JComboBox(MESES);
        cmboxMeses2.setBounds(150, 145, 85, 20);
        pestaña01.add(cmboxMeses2);

        JComboBox cmboxYear2 = new JComboBox(AÑOS);
        cmboxYear2.setBounds(250, 145, 80, 20);
        pestaña01.add(cmboxYear2);

        btn_Aceptar = new JButton("Aceptar");
        btn_Aceptar.setBounds(220, 290, 80, 20);
        pestaña01.add(btn_Aceptar);

        btn_Limpiar = new JButton("Limpiar");
        btn_Limpiar.setBounds(320, 290, 80, 20);
        pestaña01.add(btn_Limpiar);

        btn_Limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    txt_ID.setText("");
                    txt_Pedido.setText("");
                    cmboxDias.setSelectedIndex(0);
                    cmboxMeses.setSelectedIndex(0);
                    cmboxYear.setSelectedIndex(0);

                    cmboxDias2.setSelectedIndex(0);
                    cmboxMeses2.setSelectedIndex(0);
                    cmboxYear2.setSelectedIndex(0);
                } catch (Exception err) {
                    System.out.println("Error: " + err);
                }
            }
        });

        btn_Aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    int fallos = 0;
                    int idAlbaran = 0;
                    int idFactura = 0;
                    String diaE = "", mesE = "", yearE = "";
                    String diaL = "", mesL = "", yearL = "";

                    if (txt_ID.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "ID Albaran Incorrecto", "Alerta", JOptionPane.INFORMATION_MESSAGE, null);
                        fallos++;
                    } else {
                        idAlbaran = Integer.parseInt(txt_ID.getText());
                    }

                    if (txt_Pedido.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "ID Factura Incorrecto", "Alerta", JOptionPane.INFORMATION_MESSAGE, null);
                        fallos++;
                    } else {
                        idFactura = Integer.parseInt(txt_Pedido.getText());
                    }

                    diaE = (String) cmboxDias.getSelectedItem();
                    mesE = (String) cmboxMeses.getSelectedItem();
                    yearE = (String) cmboxYear.getSelectedItem();
                    diaL = (String) cmboxDias2.getSelectedItem();
                    mesL = (String) cmboxMeses2.getSelectedItem();
                    yearL = (String) cmboxYear2.getSelectedItem();

                    if (cmboxYear2.getSelectedIndex() >= cmboxYear.getSelectedIndex()) {
                        if (cmboxMeses2.getSelectedIndex() >= cmboxMeses.getSelectedIndex()) {
                            if (cmboxDias2.getSelectedIndex() >= cmboxDias.getSelectedIndex()) {
                            } else {
                                JOptionPane.showMessageDialog(null, "La fecha de Llegada no puede ser menor que la Fecha de Envio", "Alerta", JOptionPane.INFORMATION_MESSAGE, null);
                                fallos++;
                            }
                        } else {
                            JOptionPane.showMessageDialog(null, "La fecha de Llegada no puede ser menor que la Fecha de Envio", "Alerta", JOptionPane.INFORMATION_MESSAGE, null);
                            fallos++;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "La fecha de Llegada no puede ser menor que la Fecha de Envio", "Alerta", JOptionPane.INFORMATION_MESSAGE, null);
                        fallos++;
                    }

                    if (fallos == 0) {

                        System.out.println("ID Albaran: " + idAlbaran);
                        System.out.println("ID Factura: " + idFactura);
                        System.out.println("Fecha Envio: " + diaE + "/" + mesE + "/" + yearE);
                        System.out.println("Fecha Llegada: " + diaL + "/" + mesL + "/" + yearL);
                    }

                } catch (HeadlessException | NumberFormatException err) {
                    System.out.println("Error 02" + err);
                }
            }
        });
    }

    //--------------------------------------------------------------------------
    //Segunda Pestaña
    public void crearPestaña02() {
        pestaña02 = new JPanel();
        pestaña02.setLayout(null);

        JLabel etq_alb2 = new JLabel("ID Albaran:");
        etq_alb2.setBounds(10, 15, 150, 20);
        pestaña02.add(etq_alb2);

        JTextField id_alb2 = new JTextField();
        id_alb2.setBounds(100, 15, 100, 20);
        pestaña02.add(id_alb2);

        JLabel etq_ped2 = new JLabel("ID Pedido:");
        etq_ped2.setBounds(10, 60, 150, 20);
        pestaña02.add(etq_ped2);

        JTextField id_ped2 = new JTextField();
        id_ped2.setBounds(100, 60, 150, 20);
        pestaña02.add(id_ped2);

        JLabel etq_env2 = new JLabel("Fecha Envio:");
        etq_env2.setBounds(10, 105, 150, 20);
        pestaña02.add(etq_env2);

        JTextField fech_env2 = new JTextField();
        fech_env2.setBounds(100, 105, 150, 20);
        pestaña02.add(fech_env2);

        JLabel etq_lle2 = new JLabel("Fecha Llegada:");
        etq_lle2.setBounds(10, 145, 150, 20);
        pestaña02.add(etq_lle2);

        JTextField fech_lle2 = new JTextField();
        fech_lle2.setBounds(100, 145, 150, 20);
        pestaña02.add(fech_lle2);

        JButton aceptar2 = new JButton("Aceptar");
        aceptar2.setBounds(220, 290, 80, 20);
        pestaña02.add(aceptar2);

        JButton limpiar2 = new JButton("Limpiar");
        limpiar2.setBounds(320, 290, 80, 20);
        pestaña02.add(limpiar2);

        limpiar2.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    id_alb2.setText("");
                    id_ped2.setText("");
                    fech_env2.setText("");
                    fech_lle2.setText("");
                } catch (Exception err) {
                    System.out.println("Error 02");
                }
            }
        });
    }

    //--------------------------------------------------------------------------
    //Tercera Pestaña
    public void crearPestaña03() {
        pestaña03 = new JPanel();
        pestaña03.setLayout(null);

        cmbox_ID = new JComboBox();
        cmbox_ID.setBounds(15, 5, 100, 20);
        pestaña03.add(cmbox_ID);

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
                int buscarA = (Integer) cmbox_ID.getSelectedItem();
                
                Connection miConexion = (Connection) meConecto.ConectarMysql();

                try (Statement st = miConexion.createStatement()) {
                    
                    //Para establecer el modelo al JTable
                    DefaultTableModel modelo = new DefaultTableModel();
                    tablaBD.setModel(modelo);

                    //Para ejecutar la consulta
                    String query = "SELECT * FROM `producto` WHERE `Id_producto` = " + buscarA + "";
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

    //Metodo para llenar los JComboBox
    void cargarFechas() {

        DIAS = new String[31];
        for (int a = 1; a < 31; a++) {
            String num = Integer.toString(a);
            DIAS[a] = num;
        }

        AÑOS = new String[41];
        int contador = 0;
        for (int a = 1990; a <= 2030; a++) {
            String num = Integer.toString(a);
            AÑOS[contador] = num;
            contador++;
        }
    }

    public void llenarJtable() {

        Connection miConexion = (Connection) meConecto.ConectarMysql();

        try (Statement st = miConexion.createStatement()) {

            //Para establecer el modelo al JTable
            DefaultTableModel modelo = new DefaultTableModel();
            tablaBD.setModel(modelo);

            //Nuestra sentencia SQL
            String sentencia = "SELECT * FROM `producto`";
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
                cmbox_ID.addItem(rs.getInt("Id_producto"));
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
