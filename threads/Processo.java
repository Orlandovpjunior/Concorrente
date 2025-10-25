import java.io.IOException;

public class Processo {
    public static void main(String[] args) throws IOException{
        System.out.println("Processo que cria Threads");
        ThreadEx t1 = new ThreadEx();
        t1.start();
        ThreadEx t2 = new ThreadEx();
        t2.start();
        System.out.println("Processo Finalizado");
    }
}
