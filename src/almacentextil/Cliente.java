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

class Cliente extends JFrame {

    private JTabbedPane tabbedPane;
    private JPanel panel1;
    private JPanel panel2;
    private JPanel panel3;

    public Cliente() {
		// NOTE: to reduce the amount of code in this example, it uses
        // panels with a NULL layout.  This is NOT suitable for
        // production code since it may not display correctly for
        // a look-and-feel.

        setTitle("Cliente");
        setSize(450, 400);
        setBackground(Color.gray);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setLocationRelativeTo(null);

        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        getContentPane().add(topPanel);

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

        JLabel etq_alb1 = new JLabel("CIF:");
        etq_alb1.setBounds(10, 15, 50, 25);
        panel1.add(etq_alb1);

        JTextField id_alb1 = new JTextField();
        id_alb1.setBounds(80, 15, 100, 25);
        panel1.add(id_alb1);

        JLabel etq_ped1 = new JLabel("Nombre:");
        etq_ped1.setBounds(10, 60, 150, 25);
        panel1.add(etq_ped1);

        JTextField id_ped1 = new JTextField();
        id_ped1.setBounds(80, 60, 300, 25);
        panel1.add(id_ped1);
        
        JLabel etq_env1 = new JLabel("Direccion:");
        etq_env1.setBounds(10, 105, 100, 25);
        panel1.add(etq_env1);
        
        JTextField fech_env1 = new JTextField();
        fech_env1.setBounds(80, 105, 300, 25);
        panel1.add(fech_env1);
        
        JLabel etq_ciu1 = new JLabel("Ciudad:");
        etq_ciu1.setBounds(10, 145, 80, 25);
        panel1.add(etq_ciu1);
        
        JTextField ciudad1 = new JTextField();
        ciudad1.setBounds(80, 145, 100, 25);
        panel1.add(ciudad1);
        
        JLabel etq_pais1 = new JLabel("Pais:");
        etq_pais1.setBounds(210, 145, 80, 25);
        panel1.add(etq_pais1);
        
        JComboBox pais1 = new JComboBox();
        pais1.addItem("España");
        pais1.addItem("E.E.U.U");
        pais1.addItem("Inglaterra");
        pais1.addItem("Japon");
        pais1.addItem("Mexico");
        pais1.addItem("China");
        pais1.setBounds(270, 145, 100, 25);
        panel1.add(pais1);
        
        JLabel etq_telf1 = new JLabel("Telefono:");
        etq_telf1.setBounds(10, 185, 80, 25);
        panel1.add(etq_telf1);
        
        JTextField telf1 = new JTextField();
        telf1.setBounds(80, 185, 100, 25);
        panel1.add(telf1);
        
        JLabel etq_deu1 = new JLabel("Deuda:");
        etq_deu1.setBounds(210, 185, 80, 25);
        panel1.add(etq_deu1);
        
        JTextField deu1 = new JTextField();
        deu1.setBounds(270, 185, 100, 25);
        panel1.add(deu1);
        
        JButton aceptar1 = new JButton("Aceptar");
        aceptar1.setBounds(220,290,80,25);
        panel1.add(aceptar1);
        
        JButton limpiar1 = new JButton("Limpiar");
        limpiar1.setBounds(320,290,80,25);
        panel1.add(limpiar1);
    }

    public void createPage2() {
        panel2 = new JPanel();
        panel2.setLayout(null);

        JLabel etq_alb2 = new JLabel("CIF:");
        etq_alb2.setBounds(10, 15, 50, 25);
        panel2.add(etq_alb2);

        JTextField id_alb2 = new JTextField();
        id_alb2.setBounds(80, 15, 100, 25);
        panel2.add(id_alb2);

        JLabel etq_ped2 = new JLabel("Nombre:");
        etq_ped2.setBounds(10, 60, 150, 25);
        panel2.add(etq_ped2);

        JTextField id_ped2 = new JTextField();
        id_ped2.setBounds(80, 60, 300, 25);
        panel2.add(id_ped2);
        
        JLabel etq_env2 = new JLabel("Direccion:");
        etq_env2.setBounds(10, 105, 100, 25);
        panel2.add(etq_env2);
        
        JTextField fech_env2 = new JTextField();
        fech_env2.setBounds(80, 105, 300, 25);
        panel2.add(fech_env2);
        
        JLabel etq_ciu2 = new JLabel("Ciudad:");
        etq_ciu2.setBounds(10, 145, 80, 25);
        panel2.add(etq_ciu2);
        
        JTextField ciudad2 = new JTextField();
        ciudad2.setBounds(80, 145, 100, 25);
        panel2.add(ciudad2);
        
        JLabel etq_pais2 = new JLabel("Pais:");
        etq_pais2.setBounds(210, 145, 80, 25);
        panel2.add(etq_pais2);
        
        JComboBox pais2 = new JComboBox();
        pais2.addItem("España");
        pais2.addItem("E.E.U.U");
        pais2.addItem("Inglaterra");
        pais2.addItem("Japon");
        pais2.addItem("Mexico");
        pais2.addItem("China");
        pais2.setBounds(270, 145, 100, 25);
        panel2.add(pais2);
        
        JLabel etq_telf2 = new JLabel("Telefono:");
        etq_telf2.setBounds(10, 185, 80, 25);
        panel2.add(etq_telf2);
        
        JTextField telf2 = new JTextField();
        telf2.setBounds(80, 185, 100, 25);
        panel2.add(telf2);
        
        JLabel etq_deu2 = new JLabel("Deuda:");
        etq_deu2.setBounds(210, 185, 80, 25);
        panel2.add(etq_deu2);
        
        JTextField deu2 = new JTextField();
        deu2.setBounds(270, 185, 100, 25);
        panel2.add(deu2);
        
        JButton eliminar = new JButton("Eliminar");
        eliminar.setBounds(30,290,80,25);
        eliminar.setBackground(Color.red);
        eliminar.setForeground(Color.white);
        panel2.add(eliminar);
        
        JButton aceptar2 = new JButton("Aceptar");
        aceptar2.setBounds(220,290,80,25);
        panel2.add(aceptar2);
        
        JButton limpiar2 = new JButton("Limpiar");
        limpiar2.setBounds(320,290,80,25);
        panel2.add(limpiar2);
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
                        PreparedStatement prst = con.prepareStatement("select * from cliente");

                        //Paso04 Ejecutamos la sentencia
                        ResultSet rsst = prst.executeQuery();
                        System.out.println("la sentencia es: " + rsst);
                        System.out.println("---- Ejecutmamos la sentencia");

                        while (rsst.next()) {
                            for (int i = 1; i <= 7; i++) {
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
