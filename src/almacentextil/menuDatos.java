package almacentextil;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class menuDatos extends JFrame {

    //Creamos los botones
    JButton btn_cli = new JButton("Cliente");
    JButton btn_prov = new JButton("Proveedor");
    JButton btn_prod = new JButton("Producto");
    JButton btn_return = new JButton("Volver al menu");

    //Creamos el panel
    JPanel contentpanel;

    //Declaramos un constructo que llama a la funcion visible()
    public menuDatos() {
        visible();
    }

    void visible() {

        this.setTitle("Datos");
        this.setSize(450, 400);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setVisible(true);
        
        //Color
        this.getContentPane().setBackground(Color.orange);
        
        //Creamos el panel contenedor
        contentpanel = (JPanel) this.getContentPane();
        contentpanel.setLayout(null);
        
        btn_cli = new javax.swing.JButton();
        btn_prov = new javax.swing.JButton();
        btn_prod= new javax.swing.JButton();
        btn_return = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(450, 400));

        btn_prov.setBackground(java.awt.Color.gray);
        btn_prov.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        btn_prov.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Proveedor.png"))); // NOI18N
        btn_prov.setText("Proveedores");
        btn_prov.setToolTipText("");
        btn_prov.setBorderPainted(false);
        btn_prov.setContentAreaFilled(false);
        btn_prov.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_prov.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_prov.setIconTextGap(-3);
        btn_prov.setMargin(new java.awt.Insets(1, 14, 2, 14));
        btn_prov.setPreferredSize(new java.awt.Dimension(120, 120));
        btn_prov.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/proveedor2.png"))); // NOI18N
        btn_prov.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/proveedor3.png"))); // NOI18N
        btn_prov.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_prov.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_prov.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_provActionPerformed(evt);
            }
        });

        btn_prod.setBackground(java.awt.Color.gray);
        btn_prod.setFont(new java.awt.Font("Cambria Math", 0, 14)); // NOI18N
        btn_prod.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/productos.png"))); // NOI18N
        btn_prod.setText("Productos");
        btn_prod.setToolTipText("");
        btn_prod.setBorderPainted(false);
        btn_prod.setContentAreaFilled(false);
        btn_prod.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_prod.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_prod.setIconTextGap(-3);
        btn_prod.setMargin(new java.awt.Insets(1, 14, 2, 14));
        btn_prod.setPreferredSize(new java.awt.Dimension(120, 120));
        btn_prod.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/productos2.png"))); // NOI18N
        btn_prod.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/productos3.png"))); // NOI18N
        btn_prod.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_prod.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_prod.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_prodActionPerformed(evt);
            }
        });

        btn_return.setBackground(new java.awt.Color(204, 204, 255));
        btn_return.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        btn_return.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/volver.png"))); // NOI18N
        btn_return.setText("Volver");
        btn_return.setToolTipText("");
        btn_return.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_return.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_return.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        btn_return.setIconTextGap(0);
        btn_return.setMargin(new java.awt.Insets(1, 14, 2, 14));
        btn_return.setPreferredSize(new java.awt.Dimension(120, 120));
        btn_return.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_returnActionPerformed(evt);
            }
        });

        btn_cli.setBackground(java.awt.Color.gray);
        btn_cli.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        btn_cli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Cliente.png"))); // NOI18N
        btn_cli.setText("Clientes");
        btn_cli.setToolTipText("");
        btn_cli.setBorderPainted(false);
        btn_cli.setContentAreaFilled(false);
        btn_cli.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_cli.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_cli.setIconTextGap(-3);
        btn_cli.setMargin(new java.awt.Insets(1, 14, 2, 14));
        btn_cli.setPreferredSize(new java.awt.Dimension(120, 120));
        btn_cli.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Cliente2.png"))); // NOI18N
        btn_cli.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/cliente3.png"))); // NOI18N
        btn_cli.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_cli.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_cli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_cliActionPerformed(evt);
            }
        });

        
        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(58, 58, 58)
                                .addComponent(btn_cli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(50, 50, 50)
                                .addComponent(btn_prov, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(141, 141, 141)
                                .addComponent(btn_prod, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 92, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btn_return, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(36, 36, 36)
                .addComponent(btn_return, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_cli, javax.swing.GroupLayout.DEFAULT_SIZE, 100, Short.MAX_VALUE)
                    .addComponent(btn_prov, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(31, 31, 31)
                .addComponent(btn_prod, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE)
                .addGap(66, 66, 66))
        );         
        }
        public void btn_provActionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    Proveedor p = new Proveedor();
                } catch (Exception err) {
                    System.out.println("Error 61: " + err);
                }
            }
        
            public void btn_cliActionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    Cliente c = new Cliente();
                } catch (Exception err) {
                    System.out.println("Error 70: " + err);
                }
            }
        
        
        public void btn_prodActionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    Producto p = new Producto();
                } catch (Exception err) {
                    System.out.println("Error 80: " + err);
                }
            }
        
        public void btn_returnActionPerformed(java.awt.event.ActionEvent evt) {
                try {
                    dispose();
                } catch (Exception err) {
                    System.out.println("Error 90: " + err);
                }
            }
}
