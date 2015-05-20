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
    private JPanel pestaña01;
    private JPanel pestaña02;
    private JPanel pestaña03;
    static String nFactura;
    private String[] AÑOS;
    private String[] DIAS;
    final String[] MESES = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};


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

        setVisible(true);
    }

    public void crearPestaña01() {

        pestaña01 = new JPanel();
        pestaña01.setLayout(null);
        
        JLabel lbl_idFac = new JLabel("ID Factura:");
        lbl_idFac.setBounds(10, 15, 80, 20);
        pestaña01.add(lbl_idFac);

        JTextField txt_idFac = new JTextField();
        txt_idFac.setEditable(false);
        txt_idFac.setBounds(100, 15, 150, 20);
        
        pestaña01.add(txt_idFac);

        JLabel lbl_idPed = new JLabel("ID Pedido:");
        lbl_idPed.setBounds(10, 60, 80, 20);
        pestaña01.add(lbl_idPed);

        JComboBox cmb_idPed = new JComboBox();
        cmb_idPed.setBounds(100, 60, 150, 20);
        pestaña01.add(cmb_idPed);
        
        JLabel lbl_fechaFac = new JLabel("Fecha Factura:");
        lbl_fechaFac.setBounds(10, 105, 80, 20);
        pestaña01.add(lbl_fechaFac);
        
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
        
        JLabel lbl_totSin = new JLabel("Total sin IVA:");
        lbl_totSin.setBounds(10, 155, 80, 20);
        pestaña01.add(lbl_totSin);
        
        JTextField txt_totSin = new JTextField();
        txt_totSin.setBounds(100, 155, 150, 20);
        pestaña01.add(txt_totSin);
        
        JLabel lbl_desc = new JLabel("Descuento:");
        lbl_desc.setBounds(10, 200, 80, 20);
        pestaña01.add(lbl_desc);
        
        JTextField txt_desc = new JTextField();
        txt_desc.setBounds(100, 200, 150, 20);
        pestaña01.add(txt_desc);
        
        JLabel lbl_totCon = new JLabel("Total con IVA:");
        lbl_totCon.setBounds(10, 255, 80, 20);
        pestaña01.add(lbl_totCon);
        
        JTextField txt_totCon = new JTextField();
        txt_totCon.setEditable(false);
        txt_totCon.setBounds(100, 255, 150, 20);
        pestaña01.add(txt_totCon);
        
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
                    
        
                    nFactura = txt_idFac.getText();
                    String fecha = ""+(String)cmboxDias.getSelectedItem()+"/"+(String)cmboxMeses.getSelectedItem()+"/"+(String)cmboxYear.getSelectedItem();
                    imprimir((String)cmb_idPed.getSelectedItem(),fecha, txt_totSin.getText(), txt_desc.getText(), txt_totCon.getText());
                   
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
        btn_acept.setBounds(200, 300, 90,27);
        pestaña02.add(btn_acept);
        JButton btn_limp = new JButton("Limpiar");
        btn_limp.setBounds(300, 300, 90,27);
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
