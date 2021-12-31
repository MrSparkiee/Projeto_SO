import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();
        Semaphore semInterface = new Semaphore(0);
        Semaphore semTapete = new Semaphore(0);
        Semaphore semRolos = new Semaphore(0);
        Semaphore semAS = new Semaphore(0);

        Thread Interface = new Thread(new Interface(semInterface, buffer));
        Thread Tapete = new Thread(new Tapete(semTapete));
        Thread Rolos = new Thread(new Rolos(semRolos));
        Thread AspersorSecador = new Thread(new AspersorSecador(semAS));

        Interface.start();
        Tapete.start();
        Rolos.start();
        AspersorSecador.start();
        
        while (true) {
            System.out.println(buffer.getBotao());
            semInterface.acquire();
            switch (buffer.getBotao()) {
                case "Iniciar":
                    System.out.println("Lavagem Adicionada");
                    semTapete.release();
                    semRolos.release();
                    break;
                case "Cancelar":
                    System.out.println("Cancelar Lavagem");
                    break;
            }
        }

    }
}
