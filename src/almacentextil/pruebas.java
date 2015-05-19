package almacentextil;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
 
public class pruebas implements ActionListener{//implementando el listener de eventos
 
    JButton bt1, bt2, bt3;//creando variables globales de los botones
 
    public pruebas(){//constructor de la clase
 
        JFrame jf = new JFrame("Botones con Java");//creacion de ventana con el titulo
        jf.setLayout(new FlowLayout());//Configurar como se dispondra el espacio del jframe
        Dimension d = new Dimension();//objeto para obtener el ancho de la pantalla
 
        //Instanciando botones con texto
        bt1 = new JButton("Hola");
        bt2 = new JButton("Que tal?");
        bt3 = new JButton(" ;) ");
 
        //agregando los botones a la ventana
        jf.add(bt1); jf.add(bt2); jf.add(bt3);
 
        //añadiendo el listener a los botones para manipular los eventos del click
        bt1.addActionListener(this);
        bt2.addActionListener(this);
        bt3.addActionListener(this);
 
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//finaliza el programa cuando se da click en la X        
        jf.setResizable(false);//para configurar si se redimensiona la ventana
        jf.setLocation((int) ((d.getWidth()/2)+290), 50);//para ubicar inicialmente donde se muestra la ventana (x, y)
        jf.setSize(400, 250);//configurando tamaño de la ventana (ancho, alto)
        jf.setVisible(true);//configurando visualización de la venta
    }
 
    public static void main(String[] args) {
 
        pruebas gj = new pruebas();//uso de constructor para la ventana
    }
 
    @Override
    public void actionPerformed(ActionEvent e) {//sobreescribimos el metodo del listener
 
        if(e.getActionCommand().equals("Hola")){//podemos comparar por el contenido del boton
            JOptionPane.showMessageDialog(null, e.getActionCommand());
        }
        if(e.getSource()==bt2){//podemos comparar por el nombre del objeto del boton
            JOptionPane.showMessageDialog(null, e.getActionCommand());
        }
        if(e.getSource()==bt3){//podemos comparar por el nombre del objeto del boton
            JOptionPane.showMessageDialog(null, e.getActionCommand());
        }
    }
}