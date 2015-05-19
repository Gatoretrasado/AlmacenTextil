package almacentextil;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;

public final class Albaran extends JFrame {

    private final JTabbedPane tabbedPane;
    private JPanel pestaña01;
    private JPanel pestaña02;
    private JPanel pestaña03;
    private String[] AÑOS;
    private String[] DIAS;
    final String[] MESES = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

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

        JTextField txtID = new JTextField();
        txtID.setBounds(100, 15, 150, 20);
        txtID.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
                    e.consume();
                }
            }
        });
        pestaña01.add(txtID);

        JLabel lblPedido = new JLabel("ID Pedido:");
        lblPedido.setBounds(10, 60, 80, 20);
        pestaña01.add(lblPedido);

        JTextField txtPedido = new JTextField();
        txtPedido.setBounds(100, 60, 150, 20);
        txtPedido.addKeyListener(new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b')) {
                    e.consume();
                }
            }
        });
        pestaña01.add(txtPedido);

        JLabel lblEnvio = new JLabel("Fecha Envio:");
        lblEnvio.setBounds(10, 105, 80, 20);
        pestaña01.add(lblEnvio);

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

        JButton btnAceptar = new JButton("Aceptar");
        btnAceptar.setBounds(220, 290, 80, 20);
        pestaña01.add(btnAceptar);

        JButton btnLimpiar = new JButton("Limpiar");
        btnLimpiar.setBounds(320, 290, 80, 20);
        pestaña01.add(btnLimpiar);

        btnLimpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    txtID.setText("");
                    txtPedido.setText("");
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

        btnAceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    int fallos = 0;
                    int idAlbaran = 0;
                    int idFactura = 0;
                    String diaE = "", mesE = "", yearE = "";
                    String diaL = "", mesL = "", yearL = "";

                    if (txtID.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "ID Albaran Incorrecto", "Alerta", JOptionPane.INFORMATION_MESSAGE, null);
                        fallos++;
                    } else {
                        idAlbaran = Integer.parseInt(txtID.getText());
                    }

                    if (txtPedido.getText().equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "ID Factura Incorrecto", "Alerta", JOptionPane.INFORMATION_MESSAGE, null);
                        fallos++;
                    } else {
                        idFactura = Integer.parseInt(txtPedido.getText());
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

    public void crearPestaña03() {
        pestaña03 = new JPanel();
        pestaña03.setLayout(null);

        JTextArea area = new JTextArea();
        JScrollPane areaLista = new JScrollPane(area);
        JButton btnBuscar = new JButton("Buscar");
        final JSpinner spnID = new JSpinner();
        JLabel lblID = new JLabel("ID: ");

        spnID.setBounds(15, 5, 35, 25);
        btnBuscar.setBounds(334, 5, 90, 20);
        areaLista.setBounds(5, 35, 420, 250);
        area.setEditable(false);

        pestaña03.add(areaLista);
        pestaña03.add(btnBuscar);
        pestaña03.add(spnID);
        pestaña03.add(lblID);

        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    int id = (Integer) spnID.getValue();

                    try {

                        System.out.println("----- Empezamos");

                        //Paso01 Cargamos los drivers
                        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

                        //Paso02 Creamos el Objeto para la conexion 
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "damlocal", "case");
                        System.out.println(" Parece ser que nos hemos conectado");

                        //Paso03 Creamos el objeto para la sentencia  
                        PreparedStatement prst = con.prepareStatement("select * from albaran");

                        //Paso04 Ejecutamos la sentencia
                        ResultSet rsst = prst.executeQuery();
                        System.out.println("la sentencia es: " + rsst);
                        System.out.println("---- Ejecutmamos la sentencia");

                        while (rsst.next()) {
                            for (int i = 1; i <= 4; i++) {
                                System.out.print(rsst.getString(i) + '\t');
                            }
                            System.out.println();
                            System.out.println("Estamos en la fila  :" + rsst.getRow());
                        }

                        //Paso05 Cerramos la Conexion  
                        con.close();
                        System.out.println("---Cerramos la conexion");

                    } catch (SQLException sqle) {
                        System.out.println();
                        System.out.println(" Parece ser que nos hemos fallado");
                        sqle.printStackTrace();
                        System.out.println(sqle.getErrorCode() + " - " + sqle.getMessage());
                    }
                } catch (Exception err) {
                    System.out.println("Error 02");
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
}
