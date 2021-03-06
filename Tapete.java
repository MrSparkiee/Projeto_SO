import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Semaphore;
import javax.swing.*;

public class Tapete implements Runnable {
    private Enums.EstadoTapete estado;

    private Semaphore semTapete;

    private JFrame tapete;
    private JLabel labelEstado;

    public Tapete(Semaphore semTapete) {
        this.estado = Enums.EstadoTapete.PARADO;
        this.semTapete = semTapete;
    }

    public Enums.EstadoTapete getEstado() {
        return this.estado;
    }

    public void setEstado(Enums.EstadoTapete estado) {
        this.estado = estado;
        labelEstado.setText(estado.toString());
    }


    public void mostraTapete() {
        tapete = new JFrame("Tapete");

        tapete.setLayout(null);
        tapete.setPreferredSize(new Dimension(300, 150));

        labelEstado = new JLabel();
        JLabel labelTitulo = new JLabel();

        labelTitulo.setText("Tapete");
        labelTitulo.setBounds(125, 0, 50, 50);
        labelTitulo.setHorizontalAlignment(SwingConstants.CENTER);
        labelTitulo.setVerticalAlignment(SwingConstants.CENTER);

        labelEstado.setText(this.getEstado().toString());
        labelEstado.setBounds(100, 50, 100, 50);
        labelEstado.setHorizontalAlignment(SwingConstants.CENTER);
        labelEstado.setVerticalAlignment(SwingConstants.CENTER);

        tapete.add(labelTitulo);
        tapete.add(labelEstado);

        tapete.pack();
        tapete.setLocation(75, 300);
        tapete.setVisible(true);
        tapete.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    public void IniciarTapete() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        this.setEstado(Enums.EstadoTapete.MOVFRENTE);
        System.out.println("Tapete");
    }

    @Override
    public void run() {
        mostraTapete();
        while (true) {
            try {
                semTapete.acquire();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            IniciarTapete();
        }
    }

}
