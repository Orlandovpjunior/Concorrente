package exercicio1;

import java.util.Scanner;

public class Resposta {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int inicio = sc.nextInt();
        int fim = sc.nextInt();

        ThreadPrimos t1 = new ThreadPrimos(inicio,fim/2);
        t1.start();
        ThreadPrimos t2 = new ThreadPrimos((fim / 2) + 1, fim);
        t2.start();
        sc.close();
    }

   
}
