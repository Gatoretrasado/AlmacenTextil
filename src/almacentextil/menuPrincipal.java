package almacentextil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;


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
        
        //Creamos el panel contenedor
        contentpanel = (JPanel)this.getContentPane();
        contentpanel.setLayout(null);
        
        btn_fact.setBounds(40,100, 100,100);
        btn_alb.setBounds(180,100,100,100);
        btn_ped.setBounds(40,230, 100,100);
        btn_prod.setBounds(180,230,100,100);
        btn_datos.setBounds(330,40, 80,30);
        
        contentpanel.add(btn_fact);
        contentpanel.add(btn_alb);
        contentpanel.add(btn_ped);
        contentpanel.add(btn_prod);
        contentpanel.add(btn_datos);
        
        //Hacemos la ventan visible
        this.setVisible(true); 
        //Le damos un tamaño a la ventana
        this.setSize(450,400);
        //Indicamos las coordenadas de pantalla donde se creara la ventana
        this.setLocationRelativeTo(null);;
        //Le damos un titulo a la ventana
        this.setTitle("Empresa");
        //Cierra el programa al salir
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        
        btn_alb.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {             
                   Albaran a = new Albaran();
                } catch (Exception err) {
                    System.out.println("Error 02");
                }
            }
        });
        btn_fact.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {             
                   Factura f = new Factura();
                } catch (Exception err) {
                    System.out.println("Error 02");
                }
            }
        });
        btn_ped.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {             
                   Pedido p = new Pedido();
                } catch (Exception err) {
                    System.out.println("Error 02");
                }
            }
        });
        btn_prod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {             
                   Mostrar_producto mp = new Mostrar_producto();
                } catch (Exception err) {
                    System.out.println("Error 02");
                }
            }
        });
        btn_datos.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {             
                   menuDatos md = new menuDatos();
                } catch (Exception err) {
                    System.out.println("Error 02");
                }
            }
        });
    }
    public static void main(String[] args) {
        menuPrincipal v1 = new menuPrincipal();
    }
}
