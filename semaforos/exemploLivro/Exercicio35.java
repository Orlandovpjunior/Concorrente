package semaforos.exemploLivro;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.Semaphore;

public class Exercicio35 implements Runnable{
    private static int n_threads;
    private static Semaphore mutex;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        n_threads = sc.nextInt();
        mutex = new Semaphore(n_threads);

        List<Thread> threads = new ArrayList<>();

        for (int i = 0; i < 4; i++) {
            Thread newThread = new Thread(new Exercicio35(), "Thread " + i);
            threads.add(newThread);
            newThread.start();
        }

        try {
            for (Thread thread : threads) {
                thread.join();
            }
        } catch (InterruptedException e) {
            System.err.println(e.getStackTrace());
        }


    }
    @Override
    public void run() {
        try {
            mutex.acquire();
            System.out.println(Thread.currentThread().getName() + " ENTROU");
            System.out.println(Thread.currentThread().getName() + " SAINDO");
            mutex.release();
        } catch (InterruptedException e) {
            System.err.println(e.getStackTrace());
        }

    }
    
}
