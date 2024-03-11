package Entity;
//Ta bort denna kommentar
import java.util.LinkedList;

/**
 * Trådsäker generisk buffert.
 * @param <T>
 */
public class Buffer<T> {
    private LinkedList<T> buffer = new LinkedList<T>();

    /**
     * Lägger till ett objekt till buffert
     * @param obj
     */
    public synchronized void put(T obj) {
        buffer.addLast(obj);
        notifyAll();
    }

    /**
     * Hämtar objekt från buffert
     * @return
     * @throws InterruptedException
     */
    public synchronized T get() throws InterruptedException {
        while(buffer.isEmpty()) {
            wait();
        }
        return buffer.removeFirst();
    }

    /**
     * Retunerar buffertens storlek
     * @return
     */
    public int size() {
        return buffer.size();
    }
}