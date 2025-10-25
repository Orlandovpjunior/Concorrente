public class ThreadEx extends Thread{
    @Override
    public void run(){
        System.out.println("Sou o Thread " + this.getId() + ". Xau");
    }
}
