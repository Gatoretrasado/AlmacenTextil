package almacentextil;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.*;

final class PedidoC extends JFrame {

    private final JTabbedPane tabbedPane;
    private JPanel pestaña01, pestaña02, pestaña03;
    private String[] AÑOS;
    private String[] DIAS;
    final String[] MESES = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};

    public PedidoC() {

        setTitle("  -- Pedido Cliente -- ");
        setSize(450, 400);
        setResizable(false);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        cargarFechas();

        // Create the tab pages
        crearPestaña01();
        crearPestaña02();
        crearPestaña03();

        // Create a tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Crear Pedido", pestaña01);
        tabbedPane.addTab("Añadir Productos", pestaña02);
        tabbedPane.addTab("Mostrar Pedido", pestaña03);
        topPanel.add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public void crearPestaña01() {
        //crear
        pestaña01 = new JPanel();
        pestaña01.setLayout(null);

        JLabel lbl_idP = new JLabel("Id Pedido:");
        lbl_idP.setBounds(30, 25, 68, 22);
        pestaña01.add(lbl_idP);

        JTextField txt_idP = new JTextField();
        txt_idP.setEditable(false);
        txt_idP.setBounds(90, 25, 90, 20);
        pestaña01.add(txt_idP);

        JLabel lbl_idCli = new JLabel("Id Cliente:");
        lbl_idCli.setBounds(200, 25, 90, 20);
        pestaña01.add(lbl_idCli);

        JTextField txt_idCli = new JTextField();
        txt_idCli.setBounds(275, 25, 90, 20);
        pestaña01.add(txt_idCli);

        JLabel lbl_Envio = new JLabel("Fecha Envio:");
        lbl_Envio.setBounds(10, 65, 80, 20);
        pestaña01.add(lbl_Envio);

        JComboBox cmboxDias = new JComboBox(DIAS);
        cmboxDias.setBounds(100, 65, 40, 20);
        cmboxDias.setSelectedIndex(1);
        pestaña01.add(cmboxDias);

        JComboBox cmboxMeses = new JComboBox(MESES);
        cmboxMeses.setBounds(150, 65, 85, 20);
        pestaña01.add(cmboxMeses);

        JComboBox cmboxYear = new JComboBox(AÑOS);
        cmboxYear.setBounds(250, 65, 80, 20);
        pestaña01.add(cmboxYear);
        
        JLabel lbllegada = new JLabel("Fecha Llegada:");
        lbllegada.setBounds(10, 100, 100, 20);
        pestaña01.add(lbllegada);

        JComboBox cmboxDias2 = new JComboBox(DIAS);
        cmboxDias2.setBounds(100, 100, 40, 20);
        cmboxDias2.setSelectedIndex(1);
        pestaña01.add(cmboxDias2);

        JComboBox cmboxMeses2 = new JComboBox(MESES);
        cmboxMeses2.setBounds(150, 100, 85, 20);
        pestaña01.add(cmboxMeses2);

        JComboBox cmboxYear2 = new JComboBox(AÑOS);
        cmboxYear2.setBounds(250, 100, 80, 20);
        pestaña01.add(cmboxYear2);

        JButton btn_acept = new JButton("Aceptar");
        btn_acept.setBounds(200, 300, 90, 27);
        pestaña01.add(btn_acept);
        JButton btn_limp = new JButton("Limpiar");
        btn_limp.setBounds(300, 300, 90, 27);
        pestaña01.add(btn_limp);
    }

    public void crearPestaña02() {
        pestaña02 = new JPanel();
        pestaña02.setLayout(null);

        JLabel lbl_idP = new JLabel("Id Pedido:");
        lbl_idP.setBounds(30, 25, 68, 22);
        pestaña02.add(lbl_idP);

        JComboBox cmb_idP = new JComboBox();
        cmb_idP.setBounds(90, 25, 90, 20);
        pestaña02.add(cmb_idP);

        JLabel lbl_idProd = new JLabel("Id Producto:");
        lbl_idProd.setBounds(200, 25, 90, 20);
        pestaña02.add(lbl_idProd);

        JComboBox cmb_idProd = new JComboBox();
        cmb_idProd.setBounds(290, 25, 90, 20);
        pestaña02.add(cmb_idProd);

        JLabel lbl_precio = new JLabel("Precio:");
        lbl_precio.setBounds(30, 60, 90, 20);
        pestaña02.add(lbl_precio);

        JTextField txt_precio = new JTextField();
        txt_precio.setBounds(90, 60, 50, 20);
        txt_precio.setEditable(false);
        pestaña02.add(txt_precio);
        
        JLabel lbl_cantidad = new JLabel("Cantidad:");
        lbl_cantidad.setBounds(150, 60, 90, 20);
        pestaña02.add(lbl_cantidad);

        SpinnerModel sm = new SpinnerNumberModel(0,0,10,1);   
        JSpinner spn_cantidad = new JSpinner(sm);
        spn_cantidad.setBounds(210, 60, 50, 20);
        pestaña02.add(spn_cantidad);
        
        JLabel lbl_total = new JLabel("Total:");
        lbl_total.setBounds(270, 60, 90, 20);
        pestaña02.add(lbl_total);

        JTextField txt_total = new JTextField();
        txt_total.setBounds(310, 60, 50, 20);
        txt_total.setEditable(false);
        pestaña02.add(txt_total);

        JButton btn_acept = new JButton("Aceptar");
        btn_acept.setBounds(200, 300, 90, 27);
        pestaña02.add(btn_acept);
        JButton btn_limp = new JButton("Limpiar");
        btn_limp.setBounds(300, 300, 90, 27);
        pestaña02.add(btn_limp);
    }

    public void crearPestaña03() {
        pestaña03 = new JPanel();
        pestaña03.setLayout(null);
       
        JLabel lbl_idP = new JLabel("Id Pedido:");
        lbl_idP.setBounds(10, 25, 68, 22);
        pestaña03.add(lbl_idP);

        JComboBox cmb_idP = new JComboBox();
        cmb_idP.setBounds(90, 25, 90, 20);
        pestaña03.add(cmb_idP);

        JLabel lbl_idCli = new JLabel("Id Cliente:");
        lbl_idCli.setBounds(200, 25, 90, 20);
        pestaña03.add(lbl_idCli);

        JTextField txt_idCli = new JTextField();
        txt_idCli.setEditable(false);
        txt_idCli.setBounds(275, 25, 90, 20);
        pestaña03.add(txt_idCli);

        JLabel lbl_Envio = new JLabel("Fecha Envio:");
        lbl_Envio.setBounds(10, 65, 80, 20);
        pestaña03.add(lbl_Envio);

        JTextField txt_Envio = new JTextField();
        txt_Envio.setEditable(false);
        txt_Envio.setBounds(90, 65, 90, 20);
        pestaña03.add(txt_Envio);
        
        JLabel lbl_Pedido = new JLabel("Fecha Pedido:");
        lbl_Pedido.setBounds(200, 65, 80, 20);
        pestaña03.add(lbl_Pedido);

        JTextField txt_Pedido = new JTextField();
        txt_Pedido.setEditable(false);
        txt_Pedido.setBounds(285, 65, 90, 20);
        pestaña03.add(txt_Pedido);
        
        
        JButton btnBuscar = new JButton("Buscar");    
        btnBuscar.setBounds(334, 5, 90, 20);
        pestaña03.add(btnBuscar);

        
        btnBuscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                   // int id = (Integer) spnID.getValue();

                    try {

                        System.out.println("----- Empezamos");

                        //Paso01 Cargamos los drivers
                        DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());

                        //Paso02 Creamos el Objeto para la conexion 
                        Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "damlocal", "case");
                        System.out.println(" Parece ser que nos hemos conectado");

                        //Paso03 Creamos el objeto para la sentencia  
                        PreparedStatement prst = con.prepareStatement("select * from pedido");

                        //Paso04 Ejecutamos la sentencia
                        ResultSet rsst = prst.executeQuery();
                        System.out.println("la sentencia es: " + rsst);
                        System.out.println("---- Ejecutmamos la sentencia");

                        while (rsst.next()) {
                            for (int i = 1; i <= 5; i++) {
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
