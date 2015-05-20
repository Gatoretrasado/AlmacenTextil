package almacentextil;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

final class PedidoC extends JFrame {

    private final JTabbedPane tabbedPane;
    private JPanel pestaña01, pestaña02, pestaña03;
    private String[] AÑOS;
    private String[] DIAS;
    final String[] MESES = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    private int ultimoID_Pedido;
    private JTextField txt_IDPedido;
    private JComboBox cmb_IDCliente, cmb_IDPedido, cmb_IDProd;
    private JScrollPane scroll;
    private JTable tablaBD;

    //Para poder conectarse a la base de datos
    private final conexionDB meConecto = new conexionDB();

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

        ejecutarPedido_CLi();
        ejecutarClientes();
        ejecutarProductos();

        setVisible(true);
    }

    public void crearPestaña01() {
        //crear
        pestaña01 = new JPanel();
        pestaña01.setLayout(null);

        JLabel lbl_IDPedido = new JLabel("Id Pedido:");
        lbl_IDPedido.setBounds(30, 25, 68, 22);
        pestaña01.add(lbl_IDPedido);

        txt_IDPedido = new JTextField();
        txt_IDPedido.setEditable(false);
        txt_IDPedido.setBounds(90, 25, 90, 20);
        pestaña01.add(txt_IDPedido);

        JLabel lbl_IDCliente = new JLabel("Id Cliente:");
        lbl_IDCliente.setBounds(200, 25, 90, 20);
        pestaña01.add(lbl_IDCliente);

        cmb_IDCliente = new JComboBox();
        cmb_IDCliente.setBounds(275, 25, 90, 20);
        pestaña01.add(cmb_IDCliente);

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

        tablaBD = new JTable();
        scroll = new JScrollPane(tablaBD);
        scroll.setBounds(5, 125, 430, 170);
        pestaña01.add(scroll);

        JButton btn_Aceptar = new JButton("Añadir");
        btn_Aceptar.setBounds(200, 300, 90, 27);
        pestaña01.add(btn_Aceptar);
        JButton btn_Limpiar = new JButton("Limpiar");
        btn_Limpiar.setBounds(300, 300, 90, 27);
        pestaña01.add(btn_Limpiar);

        btn_Limpiar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    cmb_IDCliente.setSelectedIndex(0);
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

        btn_Aceptar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                int fallos = 0;

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

                    int IDPedido = Integer.parseInt(txt_IDPedido.getText());
                    String IDCliente = (String) cmb_IDCliente.getSelectedItem();
                    String fechaPedido = (String) cmboxYear.getSelectedItem() + "-" + ("0" + (cmboxMeses.getSelectedIndex() + 1)) + "-" + ("0" + (String) cmboxDias.getSelectedItem());
                    String fechaLlegada = (String) cmboxYear2.getSelectedItem() + "-" + ("0" + (cmboxMeses2.getSelectedIndex() + 1)) + "-" + ("0" + (String) cmboxDias2.getSelectedItem());

                    Connection miConexion = (Connection) meConecto.ConectarMysql();
                    boolean insertado = false;

                    try (Statement st = miConexion.createStatement()) {

                        //Para ejecutar la consulta
                        String query = "INSERT INTO `almacentextil`.`pedido_cli` (`Id_pedido`, `CIF_cli`, `Fecha_pedido`, `Fecha_llegada`) VALUES ('" + IDPedido + "', '" + IDCliente + "', '" + fechaPedido + "', '" + fechaLlegada + "')";
                        Statement s = miConexion.createStatement();
                        st.executeUpdate(query);
                        insertado = true;

                        miConexion.close();

                        if (insertado == true) {
                            JOptionPane.showMessageDialog(null, "Insertado Con Exito!", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                            ejecutarClientes();
                            ejecutarPedido_CLi();
                            ejecutarProductos();
                        }

                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(null, "Se ha producido un Error", "Error", JOptionPane.ERROR_MESSAGE);
                        ex.printStackTrace();
                        insertado = false;
                    }

                }

            }
        });
    }

    public void crearPestaña02() {
        pestaña02 = new JPanel();
        pestaña02.setLayout(null);

        JLabel lbl_IDPedido = new JLabel("Id Pedido:");
        lbl_IDPedido.setBounds(30, 25, 68, 22);
        pestaña02.add(lbl_IDPedido);

        cmb_IDPedido = new JComboBox();
        cmb_IDPedido.setBounds(90, 25, 90, 20);
        pestaña02.add(cmb_IDPedido);

        JLabel lbl_IDProd = new JLabel("Id Producto:");
        lbl_IDProd.setBounds(200, 25, 90, 20);
        pestaña02.add(lbl_IDProd);

        cmb_IDProd = new JComboBox();
        cmb_IDProd.setBounds(280, 25, 70, 20);
        pestaña02.add(cmb_IDProd);

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

        SpinnerModel sm = new SpinnerNumberModel(0, 0, 10, 1);
        JSpinner spn_cantidad = new JSpinner(sm);
        spn_cantidad.setBounds(210, 60, 50, 20);
        pestaña02.add(spn_cantidad);

        JLabel lbl_total = new JLabel("Total: ");
        lbl_total.setBounds(270, 60, 90, 20);
        pestaña02.add(lbl_total);

        JTextField txt_total = new JTextField();
        txt_total.setBounds(310, 60, 50, 20);
        txt_total.setEditable(false);
        pestaña02.add(txt_total);

        JButton btn_Suma = new JButton("Sum");
        btn_Suma.setBounds(370, 60, 60, 20);
        pestaña02.add(btn_Suma);

        JButton btn_Aceptar = new JButton("Aceptar");
        btn_Aceptar.setBounds(200, 300, 90, 27);
        pestaña02.add(btn_Aceptar);

        JButton btn_Buscar = new JButton("Buscar");
        btn_Buscar.setBounds(360, 25, 75, 20);
        pestaña02.add(btn_Buscar);

        JButton btn_Limpiar = new JButton("Limpiar");
        btn_Limpiar.setBounds(300, 300, 90, 27);
        pestaña02.add(btn_Limpiar);

        JButton btn_Eliminar = new JButton("Eliminar");
        btn_Eliminar.setBounds(30, 300, 90, 27);
        btn_Eliminar.setBackground(Color.red);
        btn_Eliminar.setForeground(Color.white);
        pestaña02.add(btn_Eliminar);

        btn_Eliminar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                boolean eliminado = false;

                int eliminarA1 = (Integer) cmb_IDPedido.getSelectedItem();
                int eliminarA2 = (Integer) cmb_IDProd.getSelectedItem();

                if (txt_precio.getText().equalsIgnoreCase("")) {
                    JOptionPane.showMessageDialog(null, "Seleccione un Producto", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    int Borrar = JOptionPane.showConfirmDialog(null, "Eliminar: " + eliminarA1 + " - " + eliminarA2);

                    if (JOptionPane.OK_OPTION == Borrar) {
                        Connection miConexion = (Connection) meConecto.ConectarMysql();

                        try (Statement st = miConexion.createStatement()) {

                            //Para ejecutar la consulta
                            String query = "DELETE FROM `linea_pedido_cli` WHERE `Id_pedido` = " + eliminarA1 + " and `Id_producto` = " + eliminarA2 + "";

                            Statement s = miConexion.createStatement();
                            st.executeUpdate(query);
                            eliminado = true;

                            miConexion.close();

                            if (eliminado == true) {
                                JOptionPane.showMessageDialog(null, "Eliminado Con Exito!", "Eliminado", JOptionPane.INFORMATION_MESSAGE);

                            }

                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Se ha producido un Error", "Error", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                            eliminado = false;
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Eliminacion Cancelada", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                    }
                }
            }
        }
        );

        btn_Limpiar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt
                    ) {
                        try {
                            txt_total.setText("");
                            txt_precio.setText("");
                            cmb_IDProd.setSelectedIndex(0);
                            cmb_IDPedido.setSelectedIndex(0);
                            spn_cantidad.setValue(0);

                        } catch (Exception err) {
                            System.out.println("Error: " + err);
                        }
                    }
                }
        );

        btn_Suma.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt
                    ) {
                        try {

                            if (txt_precio.getText().equalsIgnoreCase("")) {
                                JOptionPane.showMessageDialog(null, "Seleccione Producto", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                            } else {
                                float cantidad = (float) ((Integer) spn_cantidad.getValue());
                                float precio = Float.parseFloat(txt_precio.getText());
                                float result = precio * cantidad;
                                txt_total.setText(Integer.toString((int) result));
                            }
                        } catch (Exception e) {
                            System.out.println("Error 275: " + e);
                        }

                    }
                }
        );

        btn_Buscar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt
                    ) {
                        int buscarA = (Integer) cmb_IDProd.getSelectedItem();

                        Connection miConexion = (Connection) meConecto.ConectarMysql();

                        try (Statement st = miConexion.createStatement()) {

                            //Para ejecutar la consulta
                            String query = "SELECT * FROM `producto` WHERE `Id_producto` = " + buscarA + "";
                            Statement s = miConexion.createStatement();

                            //Almacenamos en un ResultSet
                            ResultSet rs = s.executeQuery(query);

                            //Obteniendo la informacion de las columnas que estan siendo consultadas
                            ResultSetMetaData rsMd = rs.getMetaData();

                            //Creando las filas para el JTable
                            while (rs.next()) {

                                txt_precio.setText(rs.getString("Precio_uni"));
                            }
                            rs.close();
                            miConexion.close();
                        } catch (Exception ex) {
                            ex.printStackTrace();
                        }
                    }
                }
        );

        btn_Aceptar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt
                    ) {

                        if (txt_total.getText().equalsIgnoreCase("")) {
                            JOptionPane.showMessageDialog(null, "Realice la sum", "Aviso", JOptionPane.INFORMATION_MESSAGE);
                        } else {

                            int IDPedido = (Integer) cmb_IDPedido.getSelectedItem();
                            int IDProducto = (Integer) cmb_IDProd.getSelectedItem();
                            String Precio = txt_precio.getText();
                            int Cantidad = (Integer) spn_cantidad.getValue();
                            int Total = Integer.parseInt(txt_total.getText());
                            String NULL = null;

                            Connection miConexion = (Connection) meConecto.ConectarMysql();
                            boolean insertado = false;

                            try (Statement st = miConexion.createStatement()) {

                                //Para ejecutar la consulta
                                String query = "INSERT INTO `almacentextil`.`linea_pedido_cli` (`Id_pedido`, `Id_producto`, `Id_linea`, `Precio`, `Cantidad`, `Total`) VALUES ('" + IDPedido + "', '" + IDProducto + "', " + NULL + ", '" + Precio + "', '" + Cantidad + "', '" + Total + "')";
                                Statement s = miConexion.createStatement();
                                st.executeUpdate(query);
                                insertado = true;

                                miConexion.close();

                                if (insertado == true) {
                                    JOptionPane.showMessageDialog(null, "Insertado Con Exito!", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                    ejecutarClientes();
                                    ejecutarPedido_CLi();
                                    ejecutarProductos();
                                }

                            } catch (Exception ex) {
                                JOptionPane.showMessageDialog(null, "Se ha producido un Error", "Error", JOptionPane.ERROR_MESSAGE);
                                ex.printStackTrace();
                                insertado = false;
                            }

                        }
                    }
                }
        );
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

    public void ejecutarPedido_CLi() {

        cmb_IDPedido.removeAllItems();

        Connection miConexion = (Connection) meConecto.ConectarMysql();

        try (Statement st = miConexion.createStatement()) {

            //Para establecer el modelo al JTable
            DefaultTableModel modelo = new DefaultTableModel();
            tablaBD.setModel(modelo);

            //Nuestra sentencia SQL
            String sentencia = "SELECT * FROM `pedido_cli`";
            Statement s = miConexion.createStatement();

            //Almacenamos en un ResultSet
            ResultSet rs = s.executeQuery(sentencia);

            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsMd = rs.getMetaData();

            //La cantidad de columnas que tiene la consulta
            int cantidadColumnas = rsMd.getColumnCount();

            //Establecer como cabezeras el nombre de las colimnas
            for (int i = 1; i <= cantidadColumnas; i++) {
                modelo.addColumn(rsMd.getColumnLabel(i));
            }
            //Creando las filas para el JTable
            while (rs.next()) {
                Object[] fila = new Object[cantidadColumnas];
                ultimoID_Pedido = rs.getInt("Id_pedido");
                cmb_IDPedido.addItem(rs.getInt("Id_pedido"));

                for (int i = 0; i < cantidadColumnas; i++) {
                    fila[i] = rs.getObject(i + 1);
                }
                modelo.addRow(fila);
            }
            rs.close();
            miConexion.close();
        } catch (SQLException e) {
            System.err.println("Se ha producido un Error! ");
            System.err.println(e.getMessage());
        }

        ultimoID_Pedido = ultimoID_Pedido + 1;
        txt_IDPedido.setText(Integer.toString(ultimoID_Pedido));
    }

    public void ejecutarClientes() {

        cmb_IDCliente.removeAllItems();
        Connection miConexion = (Connection) meConecto.ConectarMysql();

        try (Statement st = miConexion.createStatement()) {

            //Nuestra sentencia SQL
            String sentencia = "SELECT * FROM `cliente`";
            Statement s = miConexion.createStatement();

            //Almacenamos en un ResultSet
            ResultSet rs = s.executeQuery(sentencia);

            //Creando las filas para el JTable
            while (rs.next()) {
                cmb_IDCliente.addItem(rs.getString("CIF_Cli"));
            }
            rs.close();
            miConexion.close();
        } catch (SQLException e) {
            System.err.println("Se ha producido un Error! ");
            System.err.println(e.getMessage());
        }
    }

    public void ejecutarProductos() {

        cmb_IDProd.removeAllItems();

        Connection miConexion = (Connection) meConecto.ConectarMysql();

        try (Statement st = miConexion.createStatement()) {

            //Nuestra sentencia SQL
            String sentencia = "SELECT * FROM `producto`";
            Statement s = miConexion.createStatement();

            //Almacenamos en un ResultSet
            ResultSet rs = s.executeQuery(sentencia);

            //Creando las filas para el JTable
            while (rs.next()) {
                cmb_IDProd.addItem(rs.getInt("Id_producto"));
            }
            rs.close();
            miConexion.close();
        } catch (SQLException e) {
            System.err.println("Se ha producido un Error! ");
            System.err.println(e.getMessage());
        }
    }
}
