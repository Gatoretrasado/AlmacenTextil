package almacentextil;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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

        //Creamos el panel contenedor
        contentpanel = (JPanel) this.getContentPane();
        contentpanel.setLayout(null);

        btn_cli.setBounds(65, 113, 108, 62);
        btn_prov.setBounds(231, 113, 117, 62);
        btn_prod.setBounds(150, 226, 109, 63);
        btn_return.setBounds(348, 49, 84, 27);

        contentpanel.add(btn_cli);
        contentpanel.add(btn_prov);
        contentpanel.add(btn_prod);
        contentpanel.add(btn_return);

        //Hacemos la ventan visible
        this.setVisible(true);
        //Le damos un tamaño a la ventana
        this.setSize(450, 400);
        //Indicamos las coordenadas de pantalla donde se creara la ventana
        this.setLocationRelativeTo(null);;
        //Le damos un titulo a la ventana
        this.setTitle("Datos");
        //Cierra el programa al salir
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        this.setResizable(false);

        btn_prov.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Proveedor p = new Proveedor();
                } catch (Exception err) {
                    System.out.println("Error 61: " + err);
                }
            }
        });
        btn_cli.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Cliente c = new Cliente();
                } catch (Exception err) {
                    System.out.println("Error 70: " + err);
                }
            }
        });
        btn_prod.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    Producto p = new Producto();
                } catch (Exception err) {
                    System.out.println("Error 80: " + err);
                }
            }
        });
        btn_return.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                try {
                    dispose();
                } catch (Exception err) {
                    System.out.println("Error 90: " + err);
                }
            }
        });
    }
}
