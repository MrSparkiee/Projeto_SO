import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Teclado implements ActionListener, Runnable {
    JFrame janela;
    JLabel labelEstado;
    JLabel labelTeste;

    Semaphore semMT;
    Buffer buffer;

    public Teclado(Semaphore semMT, Buffer buffer) {
        this.semMT = semMT;
        this.buffer = buffer;
    }

    public void mostraJanela() {
        janela = new JFrame("Teclado");

        janela.setLayout(null);
        janela.setPreferredSize(new Dimension(800, 600));

        JButton botaoI = new JButton("Iniciar");
        JButton botaoC = new JButton("Cancelar");
        JButton botaoE = new JButton("Emergencia");

        labelEstado = new JLabel();
        labelTeste = new JLabel();

        botaoI.setBounds(300, 50, 150, 50);
        botaoC.setBounds(300, 100, 150, 50);
        botaoE.setBounds(300, 150, 150, 50);

        labelEstado.setText("Livre");
        labelTeste.setText("Teste");
        labelTeste.setName("Teste");

        labelEstado.setBounds(300, 0, 150, 50);
        labelEstado.setHorizontalAlignment(SwingConstants.CENTER);
        labelEstado.setVerticalAlignment(SwingConstants.CENTER);

        labelTeste.setBounds(350, 200, 100, 50);

        botaoI.addActionListener(this);
        botaoC.addActionListener(this);
        botaoE.addActionListener(this);

        janela.add(botaoI);
        janela.add(botaoC);
        janela.add(botaoE);
        janela.add(labelEstado);
        janela.add(labelTeste);

        janela.pack();
        janela.setLocationRelativeTo(null);
        janela.setVisible(true);
        janela.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Iniciar":
                buffer.setBotao(e.getActionCommand());
                break;
            case "Cancelar":
                buffer.setBotao(e.getActionCommand());
                break;
            case "Emergencia":
                buffer.setBotao(e.getActionCommand());
                break;
        }
    }

    public void buttonI_OnClick() {

        // try {
        //     labelEstado.setText("Ocupado");
        //     labelTeste.setText("Tapete");
        //     Thread.sleep(5 * 1000);
        //     labelTeste.setText("Lavagem");
        //     Thread.sleep(ThreadLocalRandom.current().nextInt(4, 9) * 1000);
        //     labelTeste.setText("Aspersor");
        //     Thread.sleep(5 * 1000);
        //     labelTeste.setText("Secagem");
        //     Thread.sleep(ThreadLocalRandom.current().nextInt(3, 7) * 1000);
        //     labelTeste.setText("Fim");
        //     labelEstado.setText("Livre");
        // } catch (InterruptedException e) {
        //     e.printStackTrace();
        // }
    }

    @Override
    public void run() {
        mostraJanela();
    }
}
