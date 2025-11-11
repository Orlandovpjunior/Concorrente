import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Semaphore;

class Buffer {
    private final List<Integer> data = new ArrayList<>();
    private final Semaphore mutex = new Semaphore(1);
    private final Semaphore full = new Semaphore(0);
    private final Semaphore empty = new Semaphore(100);
    
    public void put(int value) {

        try {
            empty.acquire();
            mutex.acquire();
            data.add(value);
            System.out.println("Inserted: " + value + " | Buffer size: " + data.size());
            mutex.release();
            full.release();
        } catch (InterruptedException e) {
            System.err.println(e.getStackTrace());
        }
    }
    
    public int remove() {
        int value = -1;

        try {
            full.acquire();
            mutex.acquire();
            value = data.remove(0);
            System.out.println("Removed: " + value + " | Buffer size: " + data.size());
            mutex.release();
            empty.release();
            
        } catch (InterruptedException e) {
            System.err.println(e.getStackTrace());
        }
        return value;
    }
}
