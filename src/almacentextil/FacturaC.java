package almacentextil;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.sql.*;
import javax.swing.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public final class FacturaC extends JFrame {

    private ArrayList<Integer> IDs = new ArrayList<>();
    private final JTabbedPane pestañas;
    private JPanel pestaña01, pestaña02, pestaña03;
    private String[] AÑOS, DIAS;
    private JTextField txt_IDFactura, txt_TotalnoIVA;
    private int ultimoID, precioNoIVA = 0;
    final String[] MESES = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    private JComboBox cmb_IDPedido;

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

        ejecutarLineaPedido_Cli();
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

        cmb_IDPedido = new JComboBox();
        cmb_IDPedido.setBounds(100, 60, 150, 20);
        cmb_IDPedido.addActionListener(new ActionListener() { // Accion a realizar cuando el JComboBox cambia de item seleccionado.
            @Override
            public void actionPerformed(ActionEvent e) {
                Busqueda();
            }
        });
        pestaña01.add(cmb_IDPedido);

        JLabel lbl_Fechahoy = new JLabel("Fecha Hoy:");
        lbl_Fechahoy.setBounds(10, 105, 80, 20);
        pestaña01.add(lbl_Fechahoy);

        JTextField txt_fechaHoy = new JTextField();
        txt_fechaHoy.setBounds(100, 105, 80, 20);
        txt_fechaHoy.setEditable(false);
        txt_fechaHoy.setText(fechaActual());
        pestaña01.add(txt_fechaHoy);

        JLabel lbl_TotalnoIVA = new JLabel("Total sin IVA:");
        lbl_TotalnoIVA.setBounds(10, 155, 80, 20);
        pestaña01.add(lbl_TotalnoIVA);

        txt_TotalnoIVA = new JTextField();
        txt_TotalnoIVA.setBounds(100, 155, 150, 20);
        txt_TotalnoIVA.setEditable(false);
        pestaña01.add(txt_TotalnoIVA);

        JLabel lbl_Descuento = new JLabel("Descuento x%:");
        lbl_Descuento.setBounds(10, 200, 100, 20);
        pestaña01.add(lbl_Descuento);

        JTextField txt_Descuento = new JTextField();
        txt_Descuento.setBounds(110, 200, 150, 20);
        txt_Descuento.addKeyListener(new KeyAdapter() {    //Solo caracteres numericos y solo 9 digitos
            @Override
            public void keyTyped(KeyEvent e) {
                int limitador = 3;
                char caracter = e.getKeyChar();
                if (((caracter < '0') || (caracter > '9')) && (caracter != '\b') || txt_Descuento.getText().length() >= limitador) {
                    e.consume();
                }
            }
        });
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

        JButton btn_Calc = new JButton("Calcular");
        btn_Calc.setBounds(300, 255, 90, 27);
        pestaña01.add(btn_Calc);

        btn_Limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    txt_Descuento.setText("");
                    cmb_IDPedido.setSelectedItem(0);

                } catch (Exception err) {
                    System.out.println("Error: " + err);
                }
            }
        });

        btn_Calc.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                float precioIVA = 0;
                float precioSinIVA = Float.parseFloat(txt_TotalnoIVA.getText());
                float descuento;

                if (txt_Descuento.getText().equalsIgnoreCase("")) {

                    txt_Descuento.setText("0");
                    descuento = 0;
                    precioIVA = (int) ((precioSinIVA - (precioSinIVA * (descuento / 100))) * 1.21);
                    txt_TotalIVA.setText(Integer.toString((int) precioIVA));

                } else {
                    descuento = Float.parseFloat(txt_Descuento.getText());
                    precioIVA = (int) ((precioSinIVA - (precioSinIVA * (descuento / 100))) * 1.21);
                    txt_TotalIVA.setText(Integer.toString((int) precioIVA));
                }
            }
        });

        btn_Aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {

                    boolean insertado = false;
                    String NULL = null;
                    String nFactura = txt_IDFactura.getText();
                    String idPedido = Integer.toString((int) cmb_IDPedido.getSelectedItem());
                    String fecha = fechaActual();
                    String totalSin = txt_TotalnoIVA.getText();
                    String descuento = txt_Descuento.getText();
                    if (descuento.equalsIgnoreCase("")) {
                        descuento = "0";
                    }
                    String totalCon = txt_TotalIVA.getText();

                    if (totalCon.equalsIgnoreCase("")) {
                        JOptionPane.showMessageDialog(null, "Total Vacio", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        Connection miConexion = (Connection) meConecto.ConectarMysql();

                        try (Statement st = miConexion.createStatement()) {

                            //Para ejecutar la consulta
                            String query = "INSERT INTO `almacentextil`.`factura_cli` (`Id_factura`, `Id_pedido`, `Fecha_factura`, `Total_sin_IVA`, `DTO`, `Total_con_IVA`) VALUES(" + NULL + ", '" + idPedido + "', '" + fecha + "', '" + totalSin + "', '" + descuento + "', '" + totalCon + "')";

                            Statement s = miConexion.createStatement();
                            st.executeUpdate(query);
                            insertado = true;

                            miConexion.close();

                            if (insertado == true) {
                                JOptionPane.showMessageDialog(null, "Insertado Con Exito!", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                ejecutarLineaPedido_Cli();
                                ejecutarFactura_Cli();
                            }

                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Se ha producido un Error", "Error", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                            insertado = false;
                        }

                        imprimir(nFactura, idPedido, fecha, totalSin, descuento, totalCon);
                    }

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

    static public void imprimir(String nFactura, String id_produc, String fecha, String totalsinIVA, String descuento, String totalconIVA) throws IOException {

        BufferedWriter bw = new BufferedWriter(new FileWriter("C:\\Users\\joseantonio\\Documents\\NetBeansProjects\\AlmacenTextil\\src\\Facturas\\Factura" + nFactura + ".txt"));

        bw.write("Numero de factura: " + nFactura);
        bw.newLine();
        bw.write("Numero del pedido: " + id_produc);
        bw.newLine();
        bw.write("Fecha de la factura: " + fecha);
        bw.newLine();
        bw.write("Total (sin IVA): " + totalsinIVA + "€");
        bw.newLine();
        bw.write("Descuento aplicado: " + descuento + "%");
        bw.newLine();
        bw.write("Total (con IVA): " + totalconIVA + "€");
        bw.newLine();
        bw.close();
    }

    public static String fechaActual() {
        Date ahora = new Date();
        SimpleDateFormat formateador = new SimpleDateFormat("yyyy-MM-dd");
        return formateador.format(ahora);
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

    public void ejecutarLineaPedido_Cli() {

        cmb_IDPedido.removeAllItems();
        IDs.clear();

        Connection miConexion = (Connection) meConecto.ConectarMysql();

        try (Statement st = miConexion.createStatement()) {

            //Nuestra sentencia SQL
            String sentencia = "SELECT * FROM `linea_pedido_cli` ORDER BY `Id_pedido` ASC";
            Statement s = miConexion.createStatement();

            //Almacenamos en un ResultSet
            ResultSet rs = s.executeQuery(sentencia);

            //Creando las filas para el JTable
            while (rs.next()) {

                if (!IDs.contains(rs.getInt("Id_pedido"))) {
                    IDs.add(rs.getInt("Id_pedido"));
                }

            }
            rs.close();
            miConexion.close();
        } catch (SQLException e) {
            System.err.println("Se ha producido un Error! ");
            System.err.println(e.getMessage());
        }

        for (Integer id : IDs) {
            cmb_IDPedido.addItem(id);
        }
    }

    public void Busqueda() {

        int bucarA = (int) cmb_IDPedido.getSelectedItem();
        int suma = 0;

        Connection miConexion = (Connection) meConecto.ConectarMysql();

        try (Statement st = miConexion.createStatement()) {

            //Nuestra sentencia SQL
            String sentencia = "SELECT * FROM `linea_pedido_cli` WHERE `Id_pedido` ='" + bucarA + "'";
            Statement s = miConexion.createStatement();

            //Almacenamos en un ResultSet
            ResultSet rs = s.executeQuery(sentencia);

            //Creando las filas para el JTable
            while (rs.next()) {

                precioNoIVA = rs.getInt("Total");
                suma = suma + precioNoIVA;
            }
            rs.close();
            miConexion.close();
        } catch (SQLException e) {
            System.err.println("Se ha producido un Error!: " + e);
            System.err.println(e.getMessage());
        }
        precioNoIVA = suma;
        txt_TotalnoIVA.setText(Float.toString(precioNoIVA));
    }
}
