package semaforos.exemploLivro;
import java.util.concurrent.Semaphore;

public class Exercicio34 implements Runnable{

    private static int count = 0;
    private static Semaphore mutex = new Semaphore(1);

    public static void main(String[] args) {

        Thread a = new Thread(new Exercicio34(), "Thread A");
        Thread b = new Thread(new Exercicio34(), "Thread B");
        Thread c = new Thread(new Exercicio34(), "Thread C");

        a.start();
        b.start();
        c.start();

        try {
            a.join();
            b.join();
            c.join();
        } catch (InterruptedException e) {
            System.err.println(e.getStackTrace());
        }

        System.out.println("Valor final de count: " + count);
        
    }

    @Override
    public void run() {

        try {
            mutex.acquire();
            System.out.println(Thread.currentThread().getName() + " Lendo o valor de count: " + count);
            int temp = count;
            temp++;
            count = temp;
            System.out.println(Thread.currentThread().getName() +" Valor de count: " + count);
            mutex.release();

        } catch (InterruptedException e) {
            System.err.println(e.getStackTrace());
        }
    } 
}