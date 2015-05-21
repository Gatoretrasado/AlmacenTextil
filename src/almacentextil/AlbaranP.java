package almacentextil;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

final class AlbaranP extends JFrame {

    private final JTabbedPane tabbedPane;
    private JPanel pestaña01, pestaña02, pestaña03;
    private String[] AÑOS;
    private String[] DIAS;
    final String[] MESES = {"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio", "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"};
    private int ultimoID_Pedido;
    private JTextField txt_IDAlbaran, txt_FechaPedido, txt_FechaEnvio,txt_IDPedido2;
    private JComboBox cmb_IDPedido1, cmb_IDAlbaran, cmb_IDProd, cmb_IDPedido2, cmb_IDAlbaran2;
    private JScrollPane scroll, scroll2,scroll3;
    private JTable tablaBD, tablaBD2,tablaBD3;

    //Para poder conectarse a la base de datos
    private final conexionDB meConecto = new conexionDB();

    public AlbaranP() {

        setTitle("  -- Albaran Proveedor -- ");
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
        tabbedPane.addTab("Crear Albaran", pestaña01);
        tabbedPane.addTab("Añadir Productos", pestaña02);
        tabbedPane.addTab("Mostrar Albaran", pestaña03);
        topPanel.add(tabbedPane, BorderLayout.CENTER);

        ejecutarPedido_prov();
        ejecutarPedidos();
        ejecutarProductos();
        ejecutarLineasAlbaran();

        setVisible(true);
    }

    public void crearPestaña01() {
        //crear
        pestaña01 = new JPanel();
        pestaña01.setLayout(null);

        JLabel lbl_IDAlbaran = new JLabel("Id Albaran:");
        lbl_IDAlbaran.setBounds(30, 25, 68, 22);
        pestaña01.add(lbl_IDAlbaran);

        txt_IDAlbaran = new JTextField();
        txt_IDAlbaran.setEditable(false);
        txt_IDAlbaran.setBounds(90, 25, 90, 20);
        pestaña01.add(txt_IDAlbaran);

        JLabel lbl_IDPedido = new JLabel("Id Pedido:");
        lbl_IDPedido.setBounds(200, 25, 90, 20);
        pestaña01.add(lbl_IDPedido);

        cmb_IDPedido1 = new JComboBox();
        cmb_IDPedido1.setBounds(275, 25, 90, 20);
        pestaña01.add(cmb_IDPedido1);

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
                    cmb_IDPedido1.setSelectedIndex(0);
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

                    int IDAlbaran = Integer.parseInt(txt_IDAlbaran.getText());
                    String IDPedido1 = (String) cmb_IDPedido1.getSelectedItem();
                    String fechaEnvio = (String) cmboxYear.getSelectedItem() + "-" + ("0" + (cmboxMeses.getSelectedIndex() + 1)) + "-" + ("0" + (String) cmboxDias.getSelectedItem());
                    String fechaLlegada = (String) cmboxYear2.getSelectedItem() + "-" + ("0" + (cmboxMeses2.getSelectedIndex() + 1)) + "-" + ("0" + (String) cmboxDias2.getSelectedItem());

                    Connection miConexion = (Connection) meConecto.ConectarMysql();
                    boolean insertado = false;

                    try (Statement st = miConexion.createStatement()) {

                        //Para ejecutar la consulta
                        String query = "INSERT INTO `almacentextil`.`albaran_prov` (`Id_albaran`, `Id_pedido`, `Fecha_envio`, `Fecha_llegada`) VALUES ('" + IDAlbaran + "', '" + IDPedido1 + "', '" + fechaEnvio + "', '" + fechaLlegada + "')";
                        Statement s = miConexion.createStatement();
                        st.executeUpdate(query);
                        insertado = true;

                        miConexion.close();

                        if (insertado == true) {
                            JOptionPane.showMessageDialog(null, "Insertado Con Exito!", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                            ejecutarPedidos();
                            ejecutarPedido_prov();
                            ejecutarProductos();
                            ejecutarLineasAlbaran();
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

        JLabel lbl_IDAlbaran = new JLabel("Id Albaran:");
        lbl_IDAlbaran.setBounds(30, 25, 68, 22);
        pestaña02.add(lbl_IDAlbaran);

        cmb_IDAlbaran = new JComboBox();
        cmb_IDAlbaran.setBounds(90, 25, 90, 20);
        pestaña02.add(cmb_IDAlbaran);

        JLabel lbl_IDProd = new JLabel("Id Producto:");
        lbl_IDProd.setBounds(200, 25, 90, 20);
        pestaña02.add(lbl_IDProd);

        cmb_IDProd = new JComboBox();
        cmb_IDProd.setBounds(280, 25, 70, 20);
        pestaña02.add(cmb_IDProd);

        JLabel lbl_cantidad = new JLabel("Cantidad:");
        lbl_cantidad.setBounds(30, 60, 90, 20);
        pestaña02.add(lbl_cantidad);

        SpinnerModel sm = new SpinnerNumberModel(0, 0, 10, 1);
        JSpinner spn_cantidad = new JSpinner(sm);
        spn_cantidad.setBounds(90, 60, 50, 20);
        pestaña02.add(spn_cantidad);

        tablaBD2 = new JTable();
        scroll2 = new JScrollPane(tablaBD2);
        scroll2.setBounds(5, 125, 430, 170);
        pestaña02.add(scroll2);

        JButton btn_Aceptar = new JButton("Aceptar");
        btn_Aceptar.setBounds(200, 300, 90, 27);
        pestaña02.add(btn_Aceptar);

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

                int eliminarA1 = (Integer) cmb_IDAlbaran.getSelectedItem();
                int eliminarA2 = (Integer) cmb_IDProd.getSelectedItem();

                int Borrar = JOptionPane.showConfirmDialog(null, "Eliminar: " + eliminarA1 + " - " + eliminarA2);

                if (JOptionPane.OK_OPTION == Borrar) {
                    Connection miConexion = (Connection) meConecto.ConectarMysql();

                    try (Statement st = miConexion.createStatement()) {

                        //Para ejecutar la consulta
                        String query = "DELETE FROM `linea_albaran_prov` WHERE `Id_albaran` = " + eliminarA1 + " and `Id_producto` = " + eliminarA2 + "";

                        Statement s = miConexion.createStatement();
                        st.executeUpdate(query);
                        eliminado = true;

                        miConexion.close();

                        if (eliminado == true) {
                            JOptionPane.showMessageDialog(null, "Eliminado Con Exito!", "Eliminado", JOptionPane.INFORMATION_MESSAGE);
                            ejecutarPedidos();
                            ejecutarPedido_prov();
                            ejecutarProductos();
                            ejecutarLineasAlbaran();
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
        );

        btn_Limpiar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt
                    ) {
                        try {

                            cmb_IDProd.setSelectedIndex(0);
                            cmb_IDAlbaran.setSelectedIndex(0);
                            spn_cantidad.setValue(0);

                        } catch (Exception err) {
                            System.out.println("Error: " + err);
                        }
                    }
                }
        );

        btn_Aceptar.addActionListener(
                new ActionListener() {
                    @Override
                    public void actionPerformed(ActionEvent evt
                    ) {

                        int IDalbaran = (Integer) cmb_IDAlbaran.getSelectedItem();
                        int IDProducto = (Integer) cmb_IDProd.getSelectedItem();
                        int Cantidad = (Integer) spn_cantidad.getValue();
                        String NULL = null;

                        Connection miConexion = (Connection) meConecto.ConectarMysql();
                        boolean insertado = false;

                        try (Statement st = miConexion.createStatement()) {

                            //Para ejecutar la consulta
                            String query = "INSERT INTO `almacentextil`.`linea_albaran_prov`(`Id_albaran`, `Id_linea`, `Id_producto`, `Cantidad`) VALUES ('" + IDalbaran + "', " + NULL + ", '" + IDProducto + "', '" + Cantidad + "')";
                            Statement s = miConexion.createStatement();
                            st.executeUpdate(query);
                            insertado = true;

                            miConexion.close();

                            if (insertado == true) {
                                JOptionPane.showMessageDialog(null, "Insertado Con Exito!", "Guardado", JOptionPane.INFORMATION_MESSAGE);
                                ejecutarPedidos();
                                ejecutarPedido_prov();
                                ejecutarProductos();
                                ejecutarLineasAlbaran();
                            }

                        } catch (Exception ex) {
                            JOptionPane.showMessageDialog(null, "Se ha producido un Error", "Error", JOptionPane.ERROR_MESSAGE);
                            ex.printStackTrace();
                            insertado = false;
                        }

                    }

                }
        );
    }

    public void crearPestaña03() {
        pestaña03 = new JPanel();
        pestaña03.setLayout(null);

        JLabel lbl_idP = new JLabel("Id Albaran:");
        lbl_idP.setBounds(10, 25, 68, 22);
        pestaña03.add(lbl_idP);

        cmb_IDAlbaran2 = new JComboBox();
        cmb_IDAlbaran2.setBounds(90, 25, 90, 20);
        pestaña03.add(cmb_IDAlbaran2);

        JLabel lbl_idprov = new JLabel("Id Pedido:");
        lbl_idprov.setBounds(200, 25, 90, 20);
        pestaña03.add(lbl_idprov);

        txt_IDPedido2 = new JTextField();
        txt_IDPedido2.setEditable(false);
        txt_IDPedido2.setBounds(275, 25, 90, 20);
        pestaña03.add(txt_IDPedido2);

        JLabel lbl_FechaEnvio = new JLabel("Fecha Envio:");
        lbl_FechaEnvio.setBounds(10, 65, 80, 20);
        pestaña03.add(lbl_FechaEnvio);

        txt_FechaEnvio = new JTextField();
        txt_FechaEnvio.setEditable(false);
        txt_FechaEnvio.setBounds(90, 65, 90, 20);
        pestaña03.add(txt_FechaEnvio);

        JLabel lbl_FechaPedido = new JLabel("Fecha Pedido:");
        lbl_FechaPedido.setBounds(200, 65, 80, 20);
        pestaña03.add(lbl_FechaPedido);

        txt_FechaPedido = new JTextField();
        txt_FechaPedido.setEditable(false);
        txt_FechaPedido.setBounds(290, 65, 90, 20);
        pestaña03.add(txt_FechaPedido);

        tablaBD3 = new JTable();
        scroll3 = new JScrollPane(tablaBD3);
        scroll3.setBounds(5, 125, 430, 170);
        pestaña03.add(scroll3);

        JButton btn_Buscar = new JButton("Buscar");
        btn_Buscar.setBounds(334, 5, 90, 20);
        pestaña03.add(btn_Buscar);

        btn_Buscar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {

                int buscarA1 = (Integer) cmb_IDAlbaran2.getSelectedItem();

                Connection miConexion = (Connection) meConecto.ConectarMysql();

                try (Statement st = miConexion.createStatement()) {

                    //Para establecer el modelo al JTable
                    DefaultTableModel modelo = new DefaultTableModel();
                    tablaBD3.setModel(modelo);

                    //Para ejecutar la consulta
                    String query = "SELECT * FROM `linea_albaran_prov` WHERE `Id_albaran` = " + buscarA1 + " ORDER BY `Id_albaran` ASC";
                    Statement s = miConexion.createStatement();

                    //Almacenamos en un ResultSet
                    ResultSet rs = s.executeQuery(query);

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
                        for (int i = 0; i < cantidadColumnas; i++) {
                            fila[i] = rs.getObject(i + 1);
                        }
                        modelo.addRow(fila);
                    }
                    rs.close();
                    miConexion.close();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }

                OtraConsulta();
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

    public void ejecutarPedido_prov() {

        cmb_IDAlbaran.removeAllItems();
        cmb_IDAlbaran2.removeAllItems();
        

        Connection miConexion = (Connection) meConecto.ConectarMysql();

        try (Statement st = miConexion.createStatement()) {

            //Para establecer el modelo al JTable
            DefaultTableModel modelo = new DefaultTableModel();
            tablaBD.setModel(modelo);

            //Nuestra sentencia SQL
            String sentencia = "SELECT * FROM `albaran_prov`";
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
                ultimoID_Pedido = rs.getInt("Id_albaran");
                cmb_IDAlbaran.addItem(rs.getInt("Id_albaran"));
                cmb_IDAlbaran2.addItem(rs.getInt("Id_albaran"));
                //cmb_IDprovente2.addItem(rs.getString("CIF_prov"));

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
        txt_IDAlbaran.setText(Integer.toString(ultimoID_Pedido));
    }

    //Pilla el ide de los proventes y lo guarda en el combo (pestaña1)

    public void ejecutarPedidos() {

        cmb_IDPedido1.removeAllItems();

        Connection miConexion = (Connection) meConecto.ConectarMysql();

        try (Statement st = miConexion.createStatement()) {

            //Nuestra sentencia SQL
            String sentencia = "SELECT * FROM `pedido_prov`";
            Statement s = miConexion.createStatement();

            //Almacenamos en un ResultSet
            ResultSet rs = s.executeQuery(sentencia);

            //Creando las filas para el JTable
            while (rs.next()) {
                cmb_IDPedido1.addItem(rs.getString("Id_pedido"));
                
            }
            rs.close();
            miConexion.close();
        } catch (SQLException e) {
            System.err.println("Se ha producido un Error! ");
            System.err.println(e.getMessage());
        }
    }

    //Pilla el id de los productos y lo guarda en el combo(pestaña2)

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

    public void ejecutarLineasAlbaran() {

        Connection miConexion = (Connection) meConecto.ConectarMysql();

        try (Statement st = miConexion.createStatement()) {

            //Para establecer el modelo al JTable
            DefaultTableModel modelo = new DefaultTableModel();
            tablaBD2.setModel(modelo);
            tablaBD3.setModel(modelo);

            //Nuestra sentencia SQL
            String sentencia = "SELECT * FROM `linea_albaran_prov` ORDER BY `Id_albaran` ASC";
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
    }

    public void OtraConsulta() {

        String buscarA2 = ""+ cmb_IDAlbaran2.getSelectedItem();
        Date fecha1 = null;
        Date fecha2 = null;
        String pedidoID = null;

        Connection miConexion2 = (Connection) meConecto.ConectarMysql();

        try (Statement st = miConexion2.createStatement()) {

            //Para ejecutar la consulta
            String query = "SELECT * FROM `albaran_prov` WHERE `Id_albaran` = " + buscarA2 + "";
            Statement s = miConexion2.createStatement();

            //Almacenamos en un ResultSet
            ResultSet rs = s.executeQuery(query);

            //Obteniendo la informacion de las columnas que estan siendo consultadas
            ResultSetMetaData rsMd = rs.getMetaData();
            while (rs.next()) {
                fecha1 = rs.getDate("Fecha_envio");
                fecha2 = rs.getDate("Fecha_llegada");
                pedidoID = rs.getString("Id_pedido");
            }
            rs.close();
            miConexion2.close();

            DateFormat fecha = new SimpleDateFormat("yyyy/MM/dd");

            txt_FechaPedido.setText(fecha.format(fecha1));
            txt_FechaEnvio.setText(fecha.format(fecha2));
            txt_IDPedido2.setText(pedidoID);

        } catch (Exception ex) {
            ex.printStackTrace();
        }

    }
}
