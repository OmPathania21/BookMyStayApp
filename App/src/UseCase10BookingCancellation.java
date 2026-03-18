/**
 * ============================================================
 *  MAIN CLASS - UseCase10BookingCancellation
 * ============================================================
 *
 * Use Case 10: Booking Cancellation & Inventory Rollback
 *
 * Description:
 * This class demonstrates how confirmed
 * bookings can be cancelled safely.
 *
 * Inventory is restored and rollback
 * history is maintained.
 *
 * @version 10.0
 */
public class UseCase10BookingCancellation {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        RoomInventory inventory = new RoomInventory();
        inventory.addRoomType("Single", 5);

        CancellationService cancellationService = new CancellationService();
        cancellationService.registerBooking("Single-1", "Single");

        System.out.println("Booking Cancellation");
        cancellationService.cancelBooking("Single-1", inventory);

        System.out.println();
        cancellationService.showRollbackHistory();

        System.out.println();
        System.out.println(
                "Updated Single Room Availability: "
                        + inventory.getAvailableCount("Single")
        );
    }
}
