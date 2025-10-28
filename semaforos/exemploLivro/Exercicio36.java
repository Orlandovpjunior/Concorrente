package semaforos.exemploLivro;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CyclicBarrier;

public class Exercicio36 implements Runnable{

    private static int numThreads = 4;

    public static void main(String[] args) {

        List<Thread> threads = new ArrayList<>();
        CyclicBarrier cyclicBarrier = new CyclicBarrier(numThreads, () -> {
            
        });

        for (int i = 1; i <= numThreads; i++) {
            
            if (i <=2) {
                Thread newThread1 = new Thread(new Exercicio36(), "A" + i);
                threads.add(newThread1);
                newThread1.start();
            }else{
                Thread newThread = new Thread(new Exercicio36(), "B" + i);
                threads.add(newThread);
                newThread.start();
            }
        }
    }


    @Override
    public void run() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'run'");
    }
}
