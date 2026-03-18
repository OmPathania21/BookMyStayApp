/**
 * ============================================================
 *  MAIN CLASS - UseCase11ConcurrentBookingSimulation
 * ============================================================
 *
 * Use Case 11: Concurrent Booking Simulation
 *
 * Description:
 * This class simulates multiple users
 * attempting to book rooms at the same time.
 *
 * It highlights race conditions and
 * demonstrates how synchronization
 * prevents inconsistent allocations.
 *
 * @version 11.0
 */
public class UseCase11ConcurrentBookingSimulation {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        BookingRequestQueue bookingQueue = new BookingRequestQueue();
        bookingQueue.enqueue(new Reservation("Abhi", "Single"));
        bookingQueue.enqueue(new Reservation("Vanmathi", "Double"));
        bookingQueue.enqueue(new Reservation("Kunal", "Suite"));
        bookingQueue.enqueue(new Reservation("Subha", "Single"));

        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single", 5);
        inventory.addRoomType("Double", 3);
        inventory.addRoomType("Suite", 2);

        RoomAllocationService allocationService = new RoomAllocationService();

        System.out.println("Concurrent Booking Simulation");

        // Create booking processor tasks
        Thread t1 = new Thread(
                new ConcurrentBookingProcessor(
                        bookingQueue, inventory, allocationService
                )
        );

        Thread t2 = new Thread(
                new ConcurrentBookingProcessor(
                        bookingQueue, inventory, allocationService
                )
        );

        // Start concurrent processing
        t1.start();
        t2.start();

        try {
            t1.join();
            t2.join();
        } catch (InterruptedException e) {
            System.out.println("Thread execution interrupted.");
        }

        System.out.println();
        System.out.println("Remaining Inventory:");
        System.out.println("Single: " + inventory.getAvailableCount("Single"));
        System.out.println("Double: " + inventory.getAvailableCount("Double"));
        System.out.println("Suite: " + inventory.getAvailableCount("Suite"));
    }
}
