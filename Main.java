import java.util.concurrent.Semaphore;

public class Main {
    public static void main(String[] args) throws InterruptedException {
        Buffer buffer = new Buffer();
        Semaphore semInterface = new Semaphore(0);
        Semaphore semTapete = new Semaphore(0);
        Thread Interface = new Thread(new Interface(semInterface, buffer));
        Thread Tapete = new Thread(new Tapete(semTapete));
        

        Interface.start();
        Tapete.start();
        
        while (true) {
            System.out.println(buffer.getBotao());
            semInterface.acquire();
            switch (buffer.getBotao()) {
                case "Iniciar":
                    System.out.println("Iniciar Lavagem");
                    break;
                case "Cancelar":
                    System.out.println("Cancelar Lavagem");
                    break;
            }
        }

    }
}
