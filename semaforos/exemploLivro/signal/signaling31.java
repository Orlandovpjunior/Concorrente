package semaforos.exemploLivro.signal;

import java.util.concurrent.Semaphore;

public class signaling31 implements Runnable{
    private Semaphore aArrive = new Semaphore(0);

    public static void main(String[] args) {
        Thread t1 = new Thread(new signaling31(), "A");
        Thread t2 = new Thread(new signaling31(), "b");
        t2.start();
        t1.start();
    }

    @Override
    public void run() {

        String name = Thread.currentThread().getName();

        switch (name) {
            case "A":
                System.out.println("A Chegou");
                aArrive.release();
                break;
            case "B":
            try {
                aArrive.acquire();
            } catch (InterruptedException e) {
                System.err.println(e.getStackTrace());
            }
            default:
                break;
        }

    }
}
