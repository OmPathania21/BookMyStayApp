/**
 * ============================================================
 *  MAIN CLASS - UseCase7AddOnServiceSelection
 * ============================================================
 *
 * Use Case 7: Add-On Service Selection
 *
 * Description:
 * This class demonstrates how optional
 * services can be attached to a confirmed
 * booking.
 *
 * Services are added after room allocation
 * and do not affect inventory.
 *
 * @version 7.0
 */
public class UseCase7AddOnServiceSelection {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        String reservationId = "Single-1";

        AddOnServiceManager serviceManager = new AddOnServiceManager();
        serviceManager.addService(reservationId, new AddOnService("Breakfast", 500.0));
        serviceManager.addService(reservationId, new AddOnService("Spa", 1000.0));

        double totalCost = serviceManager.calculateTotalServiceCost(reservationId);

        System.out.println("Add-On Service Selection");
        System.out.println("Reservation ID: " + reservationId);
        System.out.println("Total Add-On Cost: " + totalCost);
    }
}
