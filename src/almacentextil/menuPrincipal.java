package almacentextil;

import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;


public class menuPrincipal extends JFrame{
    
    //Creamos los botones
    JButton btn_fact = new JButton("Factura");
    JButton btn_alb = new JButton("Albaran");
    JButton btn_ped = new JButton("Pedido");
    JButton btn_prod = new JButton("Productos");
    JButton btn_datos = new JButton("Datos");
    
    //Creamos el panel
    JPanel contentpanel;
    
    
    //Declaramos un constructo que llama a la funcion visible()
    public menuPrincipal(){
        visible();
    }
       
    void visible(){
        
        this.setTitle("Producto");
        this.setSize(450, 400);
        this.setBackground(Color.gray);
        this.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setVisible(true);
        
        btn_fact = new javax.swing.JButton();
        btn_alb = new javax.swing.JButton();
        btn_ped = new javax.swing.JButton();
        btn_prod = new javax.swing.JButton();
        btn_datos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setPreferredSize(new java.awt.Dimension(450, 400));

        btn_fact.setBackground(java.awt.Color.gray);
        btn_fact.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        btn_fact.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Factura.png"))); // NOI18N
        btn_fact.setText("Factura");
        btn_fact.setToolTipText("");
        btn_fact.setBorderPainted(false);
        btn_fact.setContentAreaFilled(false);
        btn_fact.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_fact.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_fact.setIconTextGap(-3);
        btn_fact.setMargin(new java.awt.Insets(1, 14, 2, 14));
        btn_fact.setPreferredSize(new java.awt.Dimension(120, 120));
        btn_fact.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Factura2.png"))); // NOI18N
        btn_fact.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/factura3.png"))); // NOI18N
        btn_fact.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_fact.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_fact.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_factActionPerformed(evt);
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

        btn_alb.setBackground(java.awt.Color.gray);
        btn_alb.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        btn_alb.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Albaran.png"))); // NOI18N
        btn_alb.setText("Albaran");
        btn_alb.setToolTipText("");
        btn_alb.setBorderPainted(false);
        btn_alb.setContentAreaFilled(false);
        btn_alb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_alb.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_alb.setIconTextGap(-3);
        btn_alb.setMargin(new java.awt.Insets(1, 14, 2, 14));
        btn_alb.setPreferredSize(new java.awt.Dimension(120, 120));
        btn_alb.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/Albaran2.png"))); // NOI18N
        btn_alb.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/albaran3.png"))); // NOI18N
        btn_alb.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_alb.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_alb.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_albActionPerformed(evt);
            }
        });

        btn_datos.setBackground(java.awt.Color.gray);
        btn_datos.setFont(new java.awt.Font("Consolas", 0, 12)); // NOI18N
        btn_datos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/datos.png"))); // NOI18N
        btn_datos.setText("Datos");
        btn_datos.setToolTipText("");
        btn_datos.setBorderPainted(false);
        btn_datos.setContentAreaFilled(false);
        btn_datos.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_datos.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        btn_datos.setIconTextGap(0);
        btn_datos.setMargin(new java.awt.Insets(1, 14, 2, 14));
        btn_datos.setPreferredSize(new java.awt.Dimension(120, 120));
        btn_datos.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/datos2.png"))); // NOI18N
        btn_datos.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/datos3.png"))); // NOI18N
        btn_datos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_datosActionPerformed(evt);
            }
        });

        btn_ped.setBackground(java.awt.Color.gray);
        btn_ped.setFont(new java.awt.Font("Cambria Math", 0, 18)); // NOI18N
        btn_ped.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/pedido.png"))); // NOI18N
        btn_ped.setText("Pedido");
        btn_ped.setToolTipText("");
        btn_ped.setBorderPainted(false);
        btn_ped.setContentAreaFilled(false);
        btn_ped.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        btn_ped.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btn_ped.setIconTextGap(-3);
        btn_ped.setMargin(new java.awt.Insets(1, 14, 2, 14));
        btn_ped.setPreferredSize(new java.awt.Dimension(120, 120));
        btn_ped.setPressedIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/pedido2.png"))); // NOI18N
        btn_ped.setRolloverIcon(new javax.swing.ImageIcon(getClass().getResource("/Iconos/pedido3.png"))); // NOI18N
        btn_ped.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        btn_ped.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        btn_ped.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btn_pedActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_alb, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_ped, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(68, 68, 68)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btn_fact, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(btn_prod, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(102, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btn_datos, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(btn_datos, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(30, 30, 30)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btn_ped, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btn_fact, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(47, 47, 47)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btn_prod, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                    .addComponent(btn_alb, javax.swing.GroupLayout.DEFAULT_SIZE, 97, Short.MAX_VALUE))
                .addGap(50, 50, 50))
        );
             
    }
    private void btn_factActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {             
                   Factura f = new Factura();
                } catch (Exception err) {
                    System.out.println("Error 02");
                }
    }                                        

    private void btn_prodActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {             
                   Mostrar_producto mp = new Mostrar_producto();
                } catch (Exception err) {
                    System.out.println("Error 02");
                }
    }                                        

    private void btn_albActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {             
                   Albaran a = new Albaran();
                } catch (Exception err) {
                    System.out.println("Error 02");
                }
    }                                        

    private void btn_datosActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {             
                   menuDatos md = new menuDatos();
                } catch (Exception err) {
                    System.out.println("Error 02");
                }
    }                                        

    private void btn_pedActionPerformed(java.awt.event.ActionEvent evt) {                                         
        try {             
                   Pedido p = new Pedido();
                } catch (Exception err) {
                    System.out.println("Error 02");
                }
    }      
    public static void main(String[] args) {
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menuPrincipalPruebas().setVisible(true);
            }
        });
    }
}
