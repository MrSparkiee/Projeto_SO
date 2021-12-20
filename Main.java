import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadLocalRandom;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();
        Semaphore semMT = new Semaphore(1);
        Moedeiro moedeiro = new Moedeiro();
        Teclado teclado = new Teclado(semMT, buffer);

        moedeiro.run();
        teclado.run();
        while (true) {
            System.out.println(buffer.getBotao());
            switch (buffer.getBotao()) {
                case "Iniciar":
                    semMT.acquire();
                    teclado.labelTeste.setText("Iniciar");
                    try {
                        teclado.labelEstado.setText("Ocupado");
                        teclado.labelTeste.setText("Tapete");
                        Thread.sleep(5 * 1000);
                        teclado.labelTeste.setText("Lavagem");
                        Thread.sleep(ThreadLocalRandom.current().nextInt(4, 9) * 1000);
                        teclado.labelTeste.setText("Aspersor");
                        Thread.sleep(5 * 1000);
                        teclado.labelTeste.setText("Secagem");
                        Thread.sleep(ThreadLocalRandom.current().nextInt(3, 7) * 1000);
                        teclado.labelTeste.setText("Fim");
                        teclado.labelEstado.setText("Livre");
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    buffer.setBotao("");
                    break;
            }
            semMT.release();
        }

    }
}
