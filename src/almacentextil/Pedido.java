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

class Pedido extends JFrame {

    private JTabbedPane tabbedPane;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;

    public Pedido() {
		// NOTE: to reduce the amount of code in this example, it uses
        // panels with a NULL layout.  This is NOT suitable for
        // production code since it may not display correctly for
        // a look-and-feel.

        setTitle("Pedido");
        setSize(450,400);
        setBackground(Color.gray);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        // Create the tab pages
        createPage1();
        createPage2();
        createPage3();

        // Create a tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Crear", panel1);
        tabbedPane.addTab("Modificar", panel2);
        tabbedPane.addTab("Mostrar", panel3);
        topPanel.add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public void createPage1() {
        panel1 = new JPanel();
        panel1.setLayout(null);

        JLabel lbl_idP = new JLabel("Id Pedido:");
        lbl_idP.setBounds(30,25, 68,22);
        panel1.add(lbl_idP);

        JTextField txt_idP = new JTextField();
        txt_idP.setBounds(90, 25, 90, 20);
        panel1.add(txt_idP);

        JLabel lbl_fechaP = new JLabel("Fecha Pedido:");
        lbl_fechaP.setBounds(200, 25, 90, 20);
        panel1.add(lbl_fechaP);
        
        JPasswordField txt_fechaP = new JPasswordField();
        txt_fechaP.setBounds(290, 25, 90, 20);
        panel1.add(txt_fechaP);
        
        JLabel lbl_fechaL = new JLabel("Fecha Llegada:");
        lbl_fechaL.setBounds(200, 60, 90, 20);
        panel1.add(lbl_fechaL);
        
        JPasswordField txt_fechaL = new JPasswordField();
        txt_fechaL.setBounds(290, 60, 90, 20);
        panel1.add(txt_fechaL);
        
        JButton btn_acept = new JButton("Aceptar");
        btn_acept.setBounds(200, 300, 90,27);
        panel1.add(btn_acept);
        JButton btn_limp = new JButton("Limpiar");
        btn_limp.setBounds(300, 300, 90,27);
        panel1.add(btn_limp);
    }

    public void createPage2() {
        panel2 = new JPanel();
        panel2.setLayout(null);

        JLabel lbl_idP = new JLabel("Id Pedido:");
        lbl_idP.setBounds(30,25, 68,22);
        panel2.add(lbl_idP);

        JTextField txt_idP = new JTextField();
        txt_idP.setBounds(90, 25, 90, 20);
        panel2.add(txt_idP);

        JLabel lbl_fechaP = new JLabel("Fecha Pedido:");
        lbl_fechaP.setBounds(200, 25, 90, 20);
        panel2.add(lbl_fechaP);
        
        JPasswordField txt_fechaP = new JPasswordField();
        txt_fechaP.setBounds(290, 25, 90, 20);
        panel2.add(txt_fechaP);
        
        JLabel lbl_fechaL = new JLabel("Fecha Llegada:");
        lbl_fechaL.setBounds(200, 60, 90, 20);
        panel2.add(lbl_fechaL);
        
        JPasswordField txt_fechaL = new JPasswordField();
        txt_fechaL.setBounds(290, 60, 90, 20);
        panel2.add(txt_fechaL);
        
        JButton btn_acept = new JButton("Aceptar");
        btn_acept.setBounds(200, 300, 90,27);
        panel2.add(btn_acept);
        JButton btn_limp = new JButton("Limpiar");
        btn_limp.setBounds(300, 300, 90,27);
        panel2.add(btn_limp);
    }

    public void createPage3() {
        panel3 = new JPanel();
        panel3.setLayout(null);

        JTextArea area = new JTextArea();
        JScrollPane areaLista = new JScrollPane(area);
        JButton btnBuscar = new JButton("Buscar");
        final JSpinner spnID = new JSpinner();
        JLabel lblID = new JLabel("ID: ");

        spnID.setBounds(15, 5, 35, 25);
        btnBuscar.setBounds(334, 5, 90, 20);
        areaLista.setBounds(5, 35, 420, 250);
        area.setEditable(false);

        panel3.add(areaLista);
        panel3.add(btnBuscar);
        panel3.add(spnID);
        panel3.add(lblID);

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
}

