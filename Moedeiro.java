// IGNORAR ESTA CLASSE É PARA ELIMINAR
// IGNORAR ESTA CLASSE É PARA ELIMINAR
// IGNORAR ESTA CLASSE É PARA ELIMINAR
// IGNORAR ESTA CLASSE É PARA ELIMINAR
// IGNORAR ESTA CLASSE É PARA ELIMINAR
// IGNORAR ESTA CLASSE É PARA ELIMINAR
// IGNORAR ESTA CLASSE É PARA ELIMINAR
// IGNORAR ESTA CLASSE É PARA ELIMINAR
// IGNORAR ESTA CLASSE É PARA ELIMINAR
// IGNORAR ESTA CLASSE É PARA ELIMINAR
// IGNORAR ESTA CLASSE É PARA ELIMINAR
// IGNORAR ESTA CLASSE É PARA ELIMINAR
// IGNORAR ESTA CLASSE É PARA ELIMINAR
// IGNORAR ESTA CLASSE É PARA ELIMINAR
// IGNORAR ESTA CLASSE É PARA ELIMINAR
// IGNORAR ESTA CLASSE É PARA ELIMINAR
// IGNORAR ESTA CLASSE É PARA ELIMINAR
// IGNORAR ESTA CLASSE É PARA ELIMINAR
// IGNORAR ESTA CLASSE É PARA ELIMINAR
// IGNORAR ESTA CLASSE É PARA ELIMINAR
// IGNORAR ESTA CLASSE É PARA ELIMINAR



import java.awt.*;
import java.awt.event.*;
import java.util.concurrent.Semaphore;
import javax.swing.*;

public class Moedeiro implements ActionListener, Runnable {
    double saldo;
    JFrame moedeiro;
    JFrame mensagem;
    JLabel labelSaldo;
    JTextField textFieldQuantia;
    Semaphore sem;
    Buffer buffer;
    public static final double PRECO_LAVAGEM = 4.20;

    public Moedeiro(Semaphore sem, Buffer buffer) {
        this.saldo = 0;
        this.sem = sem;
        this.buffer = buffer;
    }

    public double getSaldo() {
        return this.saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
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
            return true;
        }
        return false;
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
    }

}
