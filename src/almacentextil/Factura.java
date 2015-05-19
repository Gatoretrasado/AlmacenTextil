package almacentextil;

import java.awt.*;
import java.awt.event.*;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.*;
import javax.swing.*;

public final class Factura extends JFrame {

    private final JTabbedPane pestañas;
    private JPanel pestaña01;
    private JPanel pestaña02;
    private JPanel pestaña03;
    static String nFactura;

    public Factura() {

        setTitle(" -- Facturas -- ");
        setSize(450, 400);
        setLocationRelativeTo(null);
        setResizable(false);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);

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

        setVisible(true);
    }

    public void crearPestaña01() {

        pestaña01 = new JPanel();
        pestaña01.setLayout(null);
        
        JLabel etq_fact1 = new JLabel("ID Factura:");
        etq_fact1.setBounds(10, 15, 80, 20);
        pestaña01.add(etq_fact1);

        JTextField id_fact1 = new JTextField();
        id_fact1.setBounds(100, 15, 150, 20);
        pestaña01.add(id_fact1);

        JLabel etq_ped1 = new JLabel("ID Pedido:");
        etq_ped1.setBounds(10, 60, 80, 20);
        pestaña01.add(etq_ped1);

        JTextField id_ped1 = new JTextField();
        id_ped1.setBounds(100, 60, 150, 20);
        pestaña01.add(id_ped1);
        
        JLabel etq_fech1 = new JLabel("Fecha Factura:");
        etq_fech1.setBounds(10, 105, 80, 20);
        pestaña01.add(etq_fech1);
        
        JTextField fech_env1 = new JTextField();
        fech_env1.setBounds(100, 105, 150, 20);
        pestaña01.add(fech_env1);
        
        JLabel etq_tot_sin1 = new JLabel("Total sin IVA:");
        etq_tot_sin1.setBounds(10, 155, 80, 20);
        pestaña01.add(etq_tot_sin1);
        
        JTextField tot_sin1 = new JTextField();
        tot_sin1.setBounds(100, 155, 150, 20);
        pestaña01.add(tot_sin1);
        
        JLabel etq_dto1 = new JLabel("Descuento:");
        etq_dto1.setBounds(10, 200, 80, 20);
        pestaña01.add(etq_dto1);
        
        JTextField dto1 = new JTextField();
        dto1.setBounds(100, 200, 150, 20);
        pestaña01.add(dto1);
        
        JLabel etq_tot_con1 = new JLabel("Total con IVA:");
        etq_tot_con1.setBounds(10, 255, 80, 20);
        pestaña01.add(etq_tot_con1);
        
        JTextField tot_con1 = new JTextField();
        tot_con1.setBounds(100, 255, 150, 20);
        pestaña01.add(tot_con1);
        
        JButton btn_acept = new JButton("Aceptar");
        btn_acept.setBounds(200, 300, 90,27);
        pestaña01.add(btn_acept);
        JButton btn_limp = new JButton("Limpiar");
        btn_limp.setBounds(300, 300, 90,27);
        pestaña01.add(btn_limp);
        
         btn_acept.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    
        
                    nFactura = id_fact1.getText();
                    imprimir(id_ped1.getText(), fech_env1.getText(), tot_sin1.getText(), dto1.getText(), tot_con1.getText());
                   
                } catch (Exception err) {
                    System.out.println(err);
                }
            }
        });
    }

    public void crearPestaña02() {
        pestaña02 = new JPanel();
        pestaña02.setLayout(null);

        JLabel etq_fact2 = new JLabel("ID Factura:");
        etq_fact2.setBounds(10, 15, 80, 20);
        pestaña02.add(etq_fact2);

        JTextField id_fact2 = new JTextField();
        id_fact2.setBounds(100, 15, 150, 20);
        pestaña02.add(id_fact2);

        JLabel etq_ped2 = new JLabel("ID Pedido:");
        etq_ped2.setBounds(10, 60, 80, 20);
        pestaña02.add(etq_ped2);

        JTextField id_ped2 = new JTextField();
        id_ped2.setBounds(100, 60, 150, 20);
        pestaña02.add(id_ped2);
        
        JLabel etq_fech2 = new JLabel("Fecha Factura:");
        etq_fech2.setBounds(10, 105, 80, 20);
        pestaña02.add(etq_fech2);
        
        JTextField fech_env2 = new JTextField();
        fech_env2.setBounds(100, 105, 150, 20);
        pestaña02.add(fech_env2);
        
        JLabel etq_tot_sin2 = new JLabel("Total sin IVA:");
        etq_tot_sin2.setBounds(10, 155, 80, 20);
        pestaña02.add(etq_tot_sin2);
        
        JTextField tot_sin2 = new JTextField();
        tot_sin2.setBounds(100, 155, 150, 20);
        pestaña02.add(tot_sin2);
        
        JLabel etq_dto2 = new JLabel("Descuento:");
        etq_dto2.setBounds(10, 200, 80, 20);
        pestaña02.add(etq_dto2);
        
        JTextField dto2 = new JTextField();
        dto2.setBounds(100, 200, 150, 20);
        pestaña02.add(dto2);
        
        JLabel etq_tot_con2 = new JLabel("Total con IVA:");
        etq_tot_con2.setBounds(10, 255, 80, 20);
        pestaña02.add(etq_tot_con2);
        
        JTextField tot_con2 = new JTextField();
        tot_con2.setBounds(100, 255, 150, 20);
        pestaña02.add(tot_con2);
        
        JButton btn_acept = new JButton("Aceptar");
        btn_acept.setBounds(200, 300, 90,27);
        pestaña02.add(btn_acept);
        JButton btn_limp = new JButton("Limpiar");
        btn_limp.setBounds(300, 300, 90,27);
        pestaña02.add(btn_limp);
        
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
     static public void imprimir(String id_p,String fecha,String totalsinIVA,String destuento,String totalconIVA) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\joseantonio\\Documents\\NetBeansProjects\\AlmacenTextil\\src\\Facturas\\Factura"+nFactura+".txt"));

        bw.write("Numero de factura: "+nFactura);
        bw.newLine();
        bw.write("Numero del pedido: "+id_p);
        bw.newLine();
        bw.write("Fecha de la factura: "+fecha);
        bw.newLine();
        bw.write("Total (sin IVA): "+totalsinIVA+"€");
        bw.newLine();
        bw.write("Descuento aplicado: "+destuento+"%");
        bw.newLine();
        bw.write("Total (con IVA): "+totalconIVA+"€");
        bw.newLine();
        bw.close();
    }
}
