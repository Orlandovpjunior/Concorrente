package semaforos.exemploLivro;

import java.util.concurrent.Semaphore;

public class Exercicio31 implements Runnable{

    private static Semaphore statementA = new Semaphore(0);

    public static void main(String[] args) {
        Thread a1 = new Thread(new Exercicio31(), "statement a1");
        Thread b1 = new Thread(new Exercicio31(), "statement b1");

        a1.start();
        b1.start();
    }

    @Override
    public void run() {
        String threadName = Thread.currentThread().getName();

        switch (threadName) {
            case "statement a1":
                System.out.println("A1 Lendo linha do arquivo");
                try {
                    Thread.sleep(1000);
                    statementA.release();
                } catch (InterruptedException e) {
                    System.err.println(e.getStackTrace());
                }
                break;
            case "statement b1":
                try {
                    statementA.acquire();
                } catch (InterruptedException e) {
                    System.err.println(e.getStackTrace());
                }
                System.out.println("B1 exibindo linha na tela");
                break;
            default:
                break;
        }
    }
    
}
