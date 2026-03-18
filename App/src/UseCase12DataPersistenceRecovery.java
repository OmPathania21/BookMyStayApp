/**
 * ============================================================
 *  MAIN CLASS - UseCase12DataPersistenceRecovery
 * ============================================================
 *
 * Use Case 12: Data Persistence & System Recovery
 *
 * Description:
 * This class demonstrates how system state
 * can be restored after an application restart.
 *
 * Inventory data is loaded from a file
 * before any booking operations occur.
 *
 * @version 12.0
 */
public class UseCase12DataPersistenceRecovery {

    /**
     * Application entry point.
     *
     * @param args Command-line arguments
     */
    public static void main(String[] args) {
        System.out.println("System Recovery");

        String filePath = "inventory.txt";
        RoomInventory inventory = new RoomInventory();
        FilePersistenceService persistenceService = new FilePersistenceService();

        persistenceService.loadInventory(inventory, filePath);

        if (inventory.getRoomTypes().isEmpty()) {
            System.out.println("No valid inventory data found. Starting fresh.");
            inventory.addRoomType("Single", 5);
            inventory.addRoomType("Double", 3);
            inventory.addRoomType("Suite", 2);
        }

        System.out.println();
        System.out.println("Current Inventory:");
        System.out.println("Single: " + inventory.getAvailableCount("Single"));
        System.out.println("Double: " + inventory.getAvailableCount("Double"));
        System.out.println("Suite: " + inventory.getAvailableCount("Suite"));

        persistenceService.saveInventory(inventory, filePath);
        System.out.println("Inventory saved successfully.");
    }
}
