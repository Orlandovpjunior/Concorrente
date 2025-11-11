class Consumer implements Runnable{
    private final Buffer buffer;
    private final int sleepTime;
    private final int id;
    private final int type;
    
    public Consumer(int id, Buffer buffer, int sleepTime, int type) {
        this.id = id;
        this.buffer = buffer;
        this.sleepTime = sleepTime;
        this.type = type;
    }

    @Override
    public void run() {
         try { 
            while (true) {
                int item = buffer.remove();
                
                if (item % 2 == type) {
                    System.out.println("Consumer " + id + " consumed item " + item);
                    Thread.sleep(sleepTime);
                }
                else {
                    System.out.println("Consumer " + id + " resend item " + item);
                    this.buffer.put(item);
                    Thread.sleep(sleepTime);
                }
            }
         } catch (InterruptedException e) {
            System.out.println("Consumer " + id + " interrupted. Exiting.");
            Thread.currentThread().interrupt();
         }
    }
}