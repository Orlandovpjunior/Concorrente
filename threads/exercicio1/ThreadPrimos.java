package exercicio1;

public class ThreadPrimos extends Thread{

    private int limiteInferior, limiteSuperior;

    public ThreadPrimos(int limiteInferior, int limiteSuperior){
        this.limiteInferior = limiteInferior;
        this.limiteSuperior = limiteSuperior;
    }

    @Override
    public void run(){
        for (int valor = limiteInferior; valor < limiteSuperior; valor++) {
            if (eh_primo(valor)) {
                System.out.println(valor);
            }
        }
    }

    private boolean eh_primo(int num){
        if (num < 2) {
            return false;
        }

        if (num == 2) {
            return true;
        }
        
        if (num % 2 == 0) {
            return false; 
        }

        for (int i = 3; i < (int) Math.sqrt(num) + 1; i += 2) {
            if (num % i == 0) {
                return false;
            }
        }

        return true;
    }
}
