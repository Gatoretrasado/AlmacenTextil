package almacentextil;

import java.awt.Color;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;

public class Mostrar_producto extends JFrame {

    JPanel panel3;

    public Mostrar_producto() {
        visible();
    }

    void visible() {

        panel3 = (JPanel) this.getContentPane();
        panel3.setLayout(null);

        this.setTitle("Producto");
        this.setSize(450, 400);
        this.setBackground(Color.gray);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);

        JButton aceptar3 = new JButton("Aceptar");
        aceptar3.setBounds(220, 290, 80, 25);
        panel3.add(aceptar3);

        JButton limpiar3 = new JButton("Limpiar");
        limpiar3.setBounds(320, 290, 80, 25);
        panel3.add(limpiar3);

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

        /*btnBuscar.addActionListener(new ActionListener() {
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
         PreparedStatement prst = con.prepareStatement("select * from producto");

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
         });*/
    }
}
