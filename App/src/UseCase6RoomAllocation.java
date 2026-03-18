/**
 * ============================================================
 *  MAIN CLASS - UseCase6RoomAllocation
 * ============================================================
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Description:
 * This class demonstrates how booking requests are confirmed and rooms
 * are allocated safely.
 *
 * It consumes booking requests in FIFO
 * Order and updates inventory immediately.
 *
 * @version 6.0
 */
public class UseCase6RoomAllocation {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        BookingRequestQueue requestQueue = new BookingRequestQueue();
        requestQueue.enqueue(new Reservation("Abhi", "Single"));
        requestQueue.enqueue(new Reservation("Subha", "Single"));
        requestQueue.enqueue(new Reservation("Vanmathi", "Suite"));

        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single", 2);
        inventory.addRoomType("Suite", 1);

        RoomAllocationService allocationService = new RoomAllocationService();

        System.out.println("Room Allocation Processing");
        while (!requestQueue.isEmpty()) {
            Reservation reservation = requestQueue.dequeue();
            allocationService.allocateRoom(reservation, inventory);
        }
    }
}
