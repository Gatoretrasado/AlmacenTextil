package almacentextil;


import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import javax.swing.*;

class Producto extends JFrame {

    private JTabbedPane tabbedPane;
    private JPanel panel1;
    private JPanel panel2;

    public Producto() {
        // NOTE: to reduce the amount of code in this example, it uses
        // panels with a NULL layout.  This is NOT suitable for
        // production code since it may not display correctly for
        // a look-and-feel.

        setTitle("Producto");
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

        // Create a tabbed pane
        tabbedPane = new JTabbedPane();
        tabbedPane.addTab("Crear", panel1);
        tabbedPane.addTab("Modificar", panel2);
        topPanel.add(tabbedPane, BorderLayout.CENTER);
        setVisible(true);
    }

    public void createPage1() {
        panel1 = new JPanel();
        panel1.setLayout(null);

        JLabel etq_alb2 = new JLabel("ID:");
        etq_alb2.setBounds(10, 15, 50, 25);
        panel1.add(etq_alb2);

        JTextField id_alb2 = new JTextField();
        id_alb2.setBounds(80, 15, 100, 25);
        panel1.add(id_alb2);

        JLabel etq_ped2 = new JLabel("Precio UNI:");
        etq_ped2.setBounds(10, 60, 150, 25);
        panel1.add(etq_ped2);

        JTextField id_ped2 = new JTextField();
        id_ped2.setBounds(80, 60, 90, 25);
        panel1.add(id_ped2);
        
        JLabel etq_env2 = new JLabel("Nombre:");
        etq_env2.setBounds(10, 105, 100, 25);
        panel1.add(etq_env2);
        
        JTextField fech_env2 = new JTextField();
        fech_env2.setBounds(80, 105, 300, 25);
        panel1.add(fech_env2);
        
        JLabel etq_ciu2 = new JLabel("Descripcion:");
        etq_ciu2.setBounds(10, 145, 80, 25);
        panel1.add(etq_ciu2);
        
        JTextArea txt_desc = new JTextArea();
        txt_desc.setBounds(80, 170, 300, 100);
        panel1.add(txt_desc);
        
        JButton aceptar2 = new JButton("Aceptar");
        aceptar2.setBounds(220,290,80,25);
        panel1.add(aceptar2);
        
        JButton limpiar2 = new JButton("Limpiar");
        limpiar2.setBounds(320,290,80,25);
        panel1.add(limpiar2);
    }

    public void createPage2() {
        panel2 = new JPanel();
        panel2.setLayout(null);

        JLabel etq_alb2 = new JLabel("ID:");
        etq_alb2.setBounds(10, 15, 50, 25);
        panel2.add(etq_alb2);

        JTextField id_alb2 = new JTextField();
        id_alb2.setBounds(80, 15, 100, 25);
        panel2.add(id_alb2);

        JLabel etq_ped2 = new JLabel("Precio UNI:");
        etq_ped2.setBounds(10, 60, 150, 25);
        panel2.add(etq_ped2);

        JTextField id_ped2 = new JTextField();
        id_ped2.setBounds(80, 60, 90, 25);
        panel2.add(id_ped2);
        
        JLabel etq_env2 = new JLabel("Nombre:");
        etq_env2.setBounds(10, 105, 100, 25);
        panel2.add(etq_env2);
        
        JTextField fech_env2 = new JTextField();
        fech_env2.setBounds(80, 105, 300, 25);
        panel2.add(fech_env2);
        
        JLabel etq_ciu2 = new JLabel("Descripcion:");
        etq_ciu2.setBounds(10, 145, 80, 25);
        panel2.add(etq_ciu2);
        
        JTextArea txt_desc = new JTextArea();
        txt_desc.setBounds(80, 170, 300, 100);
        panel2.add(txt_desc);
        
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
}
