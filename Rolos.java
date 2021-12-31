import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Semaphore;
import javax.swing.*;

public class Rolos implements  Runnable{
    private Enums.EstadoRolos estado;

    private Semaphore semRolos;

    private JFrame Rolos;
    private JLabel labelEstado;

    public Rolos(Semaphore semRolos){
        this.estado = Enums.EstadoRolos.PARADO;
        this.semRolos = semRolos;
    }

    public Enums.EstadoRolos getEstado() {
        return this.estado;
    }

    public void setEstado(Enums.EstadoRolos estado) {
        this.estado = estado;
        labelEstado.setText(estado.toString());
    }

    public void mostraRolos(){
        Rolos = new JFrame("Rolos Lavagem");

        Rolos.setLayout(null);
        Rolos.setPreferredSize(new Dimension(300, 150));

        labelEstado = new JLabel();
        JLabel labelTitulo = new JLabel();

        labelTitulo.setText("Rolos");
        labelTitulo.setBounds(125, 0, 50, 50);
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setVerticalAlignment(SwingConstants.CENTER);


        labelEstado.setText(this.getEstado().toString());
        labelEstado.setBounds(125, 50, 50, 50);
        labelEstado.setHorizontalAlignment(SwingConstants.CENTER);
        labelEstado.setVerticalAlignment(SwingConstants.CENTER);


        Rolos.add(labelTitulo);
        Rolos.add(labelEstado);

        Rolos.pack();
        Rolos.setLocation(75, 450);
        Rolos.setVisible(true);
        Rolos.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void IniciarRolos() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.setEstado(Enums.EstadoRolos.ATIVO);
        System.out.println("Rolos");
    }

    @Override
    public void run() {
        mostraRolos();
        while (true) {
            try {
                semRolos.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            IniciarRolos();
        }
    }
    
}
