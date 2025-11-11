package semaforos.exemploLivro.multiplex;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Mutiplex {
    private static Semaphore mutiplex;

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("Choose the number of threads that will be accepted:\n");
        int num_threads_can_run = sc.nextInt();

        System.out.println("Choose the number of threads:\n");
        int num_threads = sc.nextInt();
        mutiplex = new Semaphore(num_threads_can_run);
        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < num_threads; i++) {
            Thread newThread = new Thread(new Task(),"Thread " + i);
            threads.add(newThread);
            newThread.start();
        }

        threads.forEach(f ->{
            try {
                f.join();
            } catch (InterruptedException e) {
                System.err.println(e.getStackTrace());
            }
        });
    }
    
    static class Task implements Runnable{

        @Override
        public void run() {
            String name = Thread.currentThread().getName();
            try {
                mutiplex.acquire();
                System.out.println("Current thread " + name + " be in the area");
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                System.err.println(e.getStackTrace());
            }finally{
                System.out.println("Current thread " + name + " is out of the area");
                mutiplex.release();
            }
        }
    }
}
