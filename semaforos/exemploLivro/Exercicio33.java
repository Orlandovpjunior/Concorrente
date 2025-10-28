package semaforos.exemploLivro;

import java.util.concurrent.Semaphore;

public class Exercicio33 implements Runnable {

    private static Semaphore A1chegou = new Semaphore(0);
    private static Semaphore B1chegou = new Semaphore(0);

    public static void main(String[] args) {
        Thread a1 = new Thread(new Exercicio33(), "A1");
        Thread b1 = new Thread(new Exercicio33(), "B1");
        Thread a2 = new Thread(new Exercicio33(), "A2");
        Thread b2 = new Thread(new Exercicio33(), "B2");
        a1.start();
        a2.start();
        b1.start();
        b2.start();
    }

    @Override
    public void run() {
        String currentThread = Thread.currentThread().getName();

        switch (currentThread) {
            case "A1":
                A1chegou.release();
                System.out.println("A1");
                break;
            case "A2":
                try {
                    B1chegou.acquire();
                } catch (InterruptedException e) {
                     System.err.println(e.getStackTrace());
                }
                System.out.println("A2");
                break;
            case "B1":
                B1chegou.release();
                System.out.println("B1");
                break;
            case "B2":
                try {
                    A1chegou.acquire();
                } catch (InterruptedException e) {
                    System.err.println(e.getStackTrace());
                }
                System.out.println("B2");
                break;
            default:
                break;
        }
    }
}