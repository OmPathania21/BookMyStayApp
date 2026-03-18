import java.util.ArrayDeque;
import java.util.Queue;

/**
 * Maintains booking requests in FIFO order.
 */
public class BookingRequestQueue {
    private final Queue<Reservation> queue;

    public BookingRequestQueue() {
        this.queue = new ArrayDeque<>();
    }

    public void enqueue(Reservation reservation) {
        queue.add(reservation);
    }

    public Reservation dequeue() {
        return queue.poll();
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}
