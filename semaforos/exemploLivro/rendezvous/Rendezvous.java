package semaforos.exemploLivro.rendezvous;

import java.util.concurrent.Semaphore;

public class Rendezvous implements Runnable{

    private static Semaphore a1Arrive = new Semaphore(0);
    private static Semaphore b1Arrive = new Semaphore(0);

    public static void main(String[] args) {
        Thread a1 = new Thread(new Rendezvous(),"A1");
        Thread b1 = new Thread(new Rendezvous(),"B1");
        Thread a2 = new Thread(new Rendezvous(),"A2");
        Thread b2 = new Thread(new Rendezvous(),"B2");

        a1.start();
        a2.start();
        b1.start();
        b2.start();

        try {
            a1.join();
            a2.join();
            b1.join();
            b2.join();
        } catch (InterruptedException e) {
            System.err.println(e.getStackTrace());
        }
    }

    @Override
    public void run() {
        String name = Thread.currentThread().getName();

        switch (name) {
            case "A1":
                System.out.println("A1 Chegou!");
                a1Arrive.release();
                break;
            case "B1":
                System.out.println("B1 Chegou!");
                b1Arrive.release();
                break;
            case "A2":
                try {
                    b1Arrive.acquire();
                } catch (InterruptedException e) {
                    System.err.println(e.getStackTrace());
                }
                System.out.println("A2 Chegou!");
                break;
            case "B2":
                try {
                    a1Arrive.acquire();
                } catch (InterruptedException e) {
                    System.err.println(e.getStackTrace());
                }
                System.out.println("B2 Chegou!");
                break;
            default:
                break;
        }
    }
    
}
