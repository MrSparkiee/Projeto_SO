import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();
        Semaphore semInter = new Semaphore(0);
        Thread Interface = new Thread(new Interface(semInter, buffer));

        Interface.start();

        while (true) {
            System.out.println(buffer.getBotao());
            semInter.acquire();
            switch (buffer.getBotao()) {
                case "Iniciar":
                    System.out.println("Iniciar Lavagem");
                    buffer.setBotao("");
                    semInter.release();
                    break;
            }
        }

    }
}
