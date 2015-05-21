package almacentextil;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import javax.swing.*;

public final class FacturaC extends JFrame {

    private final JTabbedPane pestañas;
    private JPanel pestaña01, pestaña02, pestaña03;
    static String nFactura;
    private String[] AÑOS, DIAS;
    private JTextField txt_IDFactura;
    private int ultimoID;
    final String[] MESES = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    //Para poder conectarse a la base de datos
    private final conexionDB meConecto = new conexionDB();

    public FacturaC() {

        setTitle(" -- Facturas -- ");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);

        cargarFechas();

        //Creamos el contenido de cada pestaña
        crearPestaña01();
        crearPestaña02();
        crearPestaña03();

        // Creamos el panel de las pestañas
        pestañas = new JTabbedPane();
        pestañas.addTab("Crear", pestaña01);
        pestañas.addTab("Modificar", pestaña02);
        pestañas.addTab("Mostrar", pestaña03);
        topPanel.add(pestañas, BorderLayout.CENTER);
        
        ejecutarFactura_Cli();
                
        setVisible(true);
    }

    public void crearPestaña01() {

        pestaña01 = new JPanel();
        pestaña01.setLayout(null);

        JLabel lbl_IDFactura = new JLabel("ID Factura:");
        lbl_IDFactura.setBounds(10, 15, 80, 20);
        pestaña01.add(lbl_IDFactura);

        txt_IDFactura = new JTextField();
        txt_IDFactura.setEditable(false);
        txt_IDFactura.setBounds(100, 15, 150, 20);
        pestaña01.add(txt_IDFactura);

        JLabel lbl_IDPedido = new JLabel("ID Pedido:");
        lbl_IDPedido.setBounds(10, 60, 80, 20);
        pestaña01.add(lbl_IDPedido);

        JComboBox cmb_IDPedido = new JComboBox();
        cmb_IDPedido.setBounds(100, 60, 150, 20);
        pestaña01.add(cmb_IDPedido);

        JLabel lbl_Fechahoy = new JLabel("Fecha Factura:");
        lbl_Fechahoy.setBounds(10, 105, 80, 20);
        pestaña01.add(lbl_Fechahoy);

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

        JLabel lbl_TotalnoIVA = new JLabel("Total sin IVA:");
        lbl_TotalnoIVA.setBounds(10, 155, 80, 20);
        pestaña01.add(lbl_TotalnoIVA);

        JTextField txt_TotalnoIVA = new JTextField();
        txt_TotalnoIVA.setBounds(100, 155, 150, 20);
        txt_TotalnoIVA.setEditable(false);
        pestaña01.add(txt_TotalnoIVA);

        JLabel lbl_Descuento = new JLabel("Descuento:");
        lbl_Descuento.setBounds(10, 200, 80, 20);
        pestaña01.add(lbl_Descuento);

        JTextField txt_Descuento = new JTextField();
        txt_Descuento.setBounds(100, 200, 150, 20);
        pestaña01.add(txt_Descuento);

        JLabel lbl_TotalIVA = new JLabel("Total con IVA:");
        lbl_TotalIVA.setBounds(10, 255, 80, 20);
        pestaña01.add(lbl_TotalIVA);

        JTextField txt_TotalIVA = new JTextField();
        txt_TotalIVA.setEditable(false);
        txt_TotalIVA.setBounds(100, 255, 150, 20);
        pestaña01.add(txt_TotalIVA);

        JButton btn_Aceptar = new JButton("Aceptar");
        btn_Aceptar.setBounds(200, 300, 90, 27);
        pestaña01.add(btn_Aceptar);
        
        JButton btn_Limpiar = new JButton("Limpiar");
        btn_Limpiar.setBounds(300, 300, 90, 27);
        pestaña01.add(btn_Limpiar);

        btn_Aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {

                    nFactura = txt_IDFactura.getText();
                    //imprimir(txt_idPed.getText(), .getText(), txt_totSin.getText(), txt_desc.getText(), tot_con1.getText());

                } catch (Exception err) {
                    System.out.println(err);
                }
            }
        });
    }

    public void crearPestaña02() {
        pestaña02 = new JPanel();
        pestaña02.setLayout(null);

        JLabel lbl_idFac = new JLabel("ID Factura:");
        lbl_idFac.setBounds(10, 15, 80, 20);
        pestaña02.add(lbl_idFac);

        JComboBox cmb_idFac = new JComboBox();
        cmb_idFac.setBounds(100, 15, 150, 20);

        pestaña02.add(cmb_idFac);

        JLabel lbl_idPed = new JLabel("ID Pedido:");
        lbl_idPed.setBounds(10, 60, 80, 20);
        pestaña02.add(lbl_idPed);

        JComboBox cmb_idPed = new JComboBox();
        cmb_idPed.setBounds(100, 60, 150, 20);
        pestaña02.add(cmb_idPed);

        JLabel lbl_fechaFac = new JLabel("Fecha Factura:");
        lbl_fechaFac.setBounds(10, 105, 80, 20);
        pestaña02.add(lbl_fechaFac);

        JComboBox cmboxDias = new JComboBox(DIAS);
        cmboxDias.setBounds(100, 105, 40, 20);
        cmboxDias.setSelectedIndex(1);
        pestaña02.add(cmboxDias);

        JComboBox cmboxMeses = new JComboBox(MESES);
        cmboxMeses.setBounds(150, 105, 85, 20);
        pestaña02.add(cmboxMeses);

        JComboBox cmboxYear = new JComboBox(AÑOS);
        cmboxYear.setBounds(250, 105, 80, 20);
        pestaña02.add(cmboxYear);

        JLabel lbl_totSin = new JLabel("Total sin IVA:");
        lbl_totSin.setBounds(10, 155, 80, 20);
        pestaña02.add(lbl_totSin);

        JTextField txt_totSin = new JTextField();
        txt_totSin.setBounds(100, 155, 150, 20);
        pestaña02.add(txt_totSin);

        JLabel lbl_desc = new JLabel("Descuento:");
        lbl_desc.setBounds(10, 200, 80, 20);
        pestaña02.add(lbl_desc);

        JTextField txt_desc = new JTextField();
        txt_desc.setBounds(100, 200, 150, 20);
        pestaña02.add(txt_desc);

        JLabel lbl_totCon = new JLabel("Total con IVA:");
        lbl_totCon.setBounds(10, 255, 80, 20);
        pestaña02.add(lbl_totCon);

        JTextField txt_totCon = new JTextField();
        txt_totCon.setEditable(false);
        txt_totCon.setBounds(100, 255, 150, 20);
        pestaña02.add(txt_totCon);

        JButton btnBuscar = new JButton("Buscar");
        btnBuscar.setBounds(334, 10, 90, 25);
        pestaña02.add(btnBuscar);

        JButton btn_acept = new JButton("Aceptar");
        btn_acept.setBounds(200, 300, 90, 27);
        pestaña02.add(btn_acept);
        JButton btn_limp = new JButton("Limpiar");
        btn_limp.setBounds(300, 300, 90, 27);
        pestaña02.add(btn_limp);

        btn_acept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {

                } catch (Exception err) {
                    System.out.println(err);
                }
            }
        });

    }

    public void crearPestaña03() {
        pestaña03 = new JPanel();
        pestaña03.setLayout(null);

        JTextArea area = new JTextArea();
        JScrollPane areaLista = new JScrollPane(area);
        areaLista.setBounds(5, 35, 420, 250);
        area.setEditable(false);
        pestaña03.add(areaLista);

        JButton btn_Volver = new JButton("Volver");
        btn_Volver.setBounds(334, 5, 90, 20);
        pestaña03.add(btn_Volver);

        JButton btn_Buscar = new JButton("Buscar");
        btn_Buscar.setBounds(235, 5, 90, 20);
        pestaña03.add(btn_Buscar);

        JComboBox cmbox_ID = new JComboBox();
        cmbox_ID.setBounds(15, 5, 50, 25);
        pestaña03.add(cmbox_ID);

        JLabel lblID = new JLabel("ID: ");
        pestaña03.add(lblID);

        btn_Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    int id = (Integer) cmbox_ID.getSelectedItem();

                    try {

                        System.out.println("----- Empezamos");

                        //Paso01 Cargamos los drivers
                        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

                        //Paso02 Creamos el Objeto para la conexion 
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "damlocal", "case");
                        System.out.println(" Parece ser que nos hemos conectado");

                        //Paso03 Creamos el objeto para la sentencia  
                        PreparedStatement prst = con.prepareStatement("select * from factura");

                        //Paso04 Ejecutamos la sentencia
                        ResultSet rsst = prst.executeQuery();
                        System.out.println("la sentencia es: " + rsst);
                        System.out.println("---- Ejecutmamos la sentencia");

                        while (rsst.next()) {
                            for (int i = 1; i <= 6; i++) {
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

    static public void imprimir(String id_p, String fecha, String totalsinIVA, String destuento, String totalconIVA) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\joseantonio\\Documents\\NetBeansProjects\\AlmacenTextil\\src\\Facturas\\Factura" + nFactura + ".txt"));

        bw.write("Numero de factura: " + nFactura);
        bw.newLine();
        bw.write("Numero del pedido: " + id_p);
        bw.newLine();
        bw.write("Fecha de la factura: " + fecha);
        bw.newLine();
        bw.write("Total (sin IVA): " + totalsinIVA + "€");
        bw.newLine();
        bw.write("Descuento aplicado: " + destuento + "%");
        bw.newLine();
        bw.write("Total (con IVA): " + totalconIVA + "€");
        bw.newLine();
        bw.close();
    }

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

    public void ejecutarFactura_Cli() {

        Connection miConexion = (Connection) meConecto.ConectarMysql();

        try (Statement st = miConexion.createStatement()) {

            //Nuestra sentencia SQL
            String sentencia = "SELECT * FROM `factura_cli`";
            Statement s = miConexion.createStatement();

            //Almacenamos en un ResultSet
            ResultSet rs = s.executeQuery(sentencia);

            //Creando las filas para el JTable
            while (rs.next()) {
                ultimoID = rs.getInt("Id_factura");
            }
            rs.close();
            miConexion.close();
        } catch (SQLException e) {
            System.err.println("Se ha producido un Error! ");
            System.err.println(e.getMessage());
        }

        ultimoID = ultimoID + 1;
        txt_IDFactura.setText(Integer.toString(ultimoID));
    }
}
