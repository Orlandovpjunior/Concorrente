package semaforos.exemploLivro.mutex;

import java.util.concurrent.Semaphore;


public class Mutex {
    private static int count = 0;
    private static Semaphore mutex = new Semaphore(1);

    public static void main(String[] args) {
    
        Thread t1 = new Thread(new Task());
        Thread t2 = new Thread(new Task());
        t1.start();
        t2.start();

        try {
            t2.join();
            t1.join();
        } catch (InterruptedException e) {
            System.err.println(e.getStackTrace());
        }

        System.out.println(count);
    }
    
    static class Task implements Runnable {

        @Override
        public void run() {
            try {
                mutex.acquire();
                count += 1;
                mutex.release();
            } catch (InterruptedException e) {
                System.err.println(e.getStackTrace());
            }
            
        }

    }
}


