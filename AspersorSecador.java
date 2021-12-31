import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Semaphore;
import javax.swing.*;

public class AspersorSecador implements  Runnable{
    private Enums.EstadoAS estado;

    private Semaphore sem;

    private JFrame AspersorSecador;
    private JLabel labelEstado;

    public AspersorSecador(Semaphore sem){
        this.estado = Enums.EstadoAS.PARADO;
        this.sem = sem;
    }

    public Enums.EstadoAS getEstado() {
        return this.estado;
    }

    public void setEstado(Enums.EstadoAS estado) {
        this.estado = estado;
        labelEstado.setText(estado.toString());
    }

    public void mostraAspersorSecador(){
        AspersorSecador = new JFrame("Aspersor e Secador");

        AspersorSecador.setLayout(null);
        AspersorSecador.setPreferredSize(new Dimension(300, 150));

        labelEstado = new JLabel();
        JLabel labelTitulo = new JLabel();

        labelTitulo.setText("Aspersor e Secador");
        labelTitulo.setBounds(75, 0, 125, 50);
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setVerticalAlignment(SwingConstants.CENTER);


        labelEstado.setText(this.getEstado().toString());
        labelEstado.setBounds(125, 50, 50, 50);
        labelEstado.setHorizontalAlignment(SwingConstants.CENTER);
        labelEstado.setVerticalAlignment(SwingConstants.CENTER);


        AspersorSecador.add(labelTitulo);
        AspersorSecador.add(labelEstado);

        AspersorSecador.pack();
        AspersorSecador.setLocation(75, 600);
        AspersorSecador.setVisible(true);
        AspersorSecador.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void run() {
        mostraAspersorSecador();
    }
    
}
