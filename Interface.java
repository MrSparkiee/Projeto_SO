import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Semaphore;
import javax.swing.*;

public class Interface implements ActionListener, Runnable {
    private static final double PRECO_LAVAGEM = 4.20;
    private double saldo;

    private JFrame moedeiro;
    private JLabel labelSaldo;
    private JTextField textFieldQuantia;

    private JFrame janela;
    private JLabel labelEstado;
    private JLabel labelTeste; // ELIMINAR

    private Semaphore semInterface;
    private Buffer buffer;

    public Interface(Semaphore semInterface, Buffer buffer) {
        this.saldo = 999;  // Valor para debug - Mudar para 0 
        this.semInterface = semInterface;
        this.buffer = buffer;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public synchronized void acorda(){
        this.notify();
    }

    public synchronized void espera(){
        try{
            this.wait();
        }catch(InterruptedException e){
            System.out.println(e);
        }
    }

    public synchronized void acordaTodos(){
        this.notifyAll();   
    }

    public void adicionarMoeda(String moeda) {
        switch (moeda) {
            case "2":
                this.saldo += Float.parseFloat(moeda);
                break;
            case "1":
                this.saldo += Float.parseFloat(moeda);
                break;
            case "0.5":
            case "0.50":
                this.saldo += Float.parseFloat(moeda);
                break;
            case "0.2":
            case "0.20":
                this.saldo += Float.parseFloat(moeda);
                break;
            case "0.1":
            case "0.10":
                this.saldo += Float.parseFloat(moeda);
                break;
            case "0.05":
                this.saldo += Float.parseFloat(moeda);
                break;
            case "0.02":
                this.saldo += Float.parseFloat(moeda);
                break;
            case "0.01":
                this.saldo += Float.parseFloat(moeda);
                break;
            default:
                JOptionPane.showMessageDialog(null, "Valor Invalido!");

        }
        labelSaldo.setText(String.format("%.2f", this.saldo) + "€");
    }

    public boolean verificaSaldo() {
        if (this.saldo >= PRECO_LAVAGEM) {
            this.saldo -= 4.20;
            labelSaldo.setText(String.format("%.2f", this.saldo) + "€");
            return true;
        }
        return false;
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

    public void mostraMoedeiro() {
        moedeiro = new JFrame("Moedeiro");

        moedeiro.setLayout(null);
        moedeiro.setPreferredSize(new Dimension(300, 200));

        JButton botaoA = new JButton("Adicionar");
        JButton botaoR = new JButton("Remover");

        labelSaldo = new JLabel();
        textFieldQuantia = new JTextField(16);

        botaoA.setBounds(50, 100, 100, 50);
        botaoR.setBounds(150, 100, 100, 50);

        labelSaldo.setBounds(125, 0, 50, 50);
        labelSaldo.setText(String.format("%.2f", this.saldo) + "€");
        labelSaldo.setHorizontalAlignment(SwingConstants.CENTER);
        labelSaldo.setVerticalAlignment(SwingConstants.CENTER);

        textFieldQuantia.setBounds(100, 50, 100, 20);
        textFieldQuantia.setHorizontalAlignment(SwingConstants.CENTER);

        botaoA.addActionListener(this);
        botaoR.addActionListener(this);

        moedeiro.add(textFieldQuantia);
        moedeiro.add(botaoA);
        moedeiro.add(botaoR);
        moedeiro.add(labelSaldo);

        moedeiro.pack();
        moedeiro.setLocation(75, 100);
        moedeiro.setVisible(true);
        moedeiro.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        switch (e.getActionCommand()) {
            case "Iniciar":
                if (this.verificaSaldo()) {
                    buffer.setBotao(e.getActionCommand());
                    labelEstado.setText("Ocupado");
                    semInterface.release();
                }
                break;
            case "Cancelar":
                buffer.setBotao(e.getActionCommand());
                semInterface.release();
                break;
            case "Emergencia":
                buffer.setBotao(e.getActionCommand());
                semInterface.release();
                break;
            case "Adicionar":
                adicionarMoeda(textFieldQuantia.getText());
                break;
            case "Remover":
                this.saldo = 0;
                labelSaldo.setText(String.format("%.2f", this.saldo) + "€");
                break;
        }

    }

    @Override
    public void run() {
        mostraMoedeiro();
        mostraJanela();
    }
}
