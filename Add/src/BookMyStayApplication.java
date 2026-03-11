/**
 * Runs all available use cases in sequence
 * from a single application entry point.
 */
public class BookMyStayApplication {

    /**
     * Application entry point that executes
     * the complete learning flow.
     *
     * @param args command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("=== Book My Stay App ===");
        System.out.println();

        System.out.println("=== Use Case 1 ===");
        UseCase1HotelBookingApp.main(new String[0]);
        System.out.println();

        System.out.println("=== Use Case 2 ===");
        UseCase2RoomInitialization.main(new String[0]);
        System.out.println();

        System.out.println("=== Use Case 3 ===");
        UseCase3InventorySetup.main(new String[0]);
        System.out.println();

        System.out.println("=== Use Case 4 ===");
        UseCase4RoomSearch.main(new String[0]);
        System.out.println();

        System.out.println("=== Use Case 5 ===");
        UseCase5BookingRequestQueue.main(new String[0]);
    }
}
