package estudoslab1.java;

public class HelloRunnable implements Runnable{

    public static void main(String[] args) {

        // Criando um objeto thread passando uma instância que implementa Runable
        Thread myThread = new Thread(new HelloRunnable(), "myThread-HelloRunnable");
        Thread myThread2 = new Thread(new HelloRunnable(), "myThread2");
        myThread.start();
        myThread2.start();


    }

    @Override
    public void run() {
        while (true) {
            System.out.println("Hello World!");
        }
    }
    
    static class HelloThread extends Thread {
        
        @Override
        public void run(){
            while (true) {
                System.out.println("Hello World!");
            }
        }
        
    }
}
