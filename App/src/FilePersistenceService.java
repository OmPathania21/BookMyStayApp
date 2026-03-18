import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

/**
 * ============================================================
 *  CLASS - FilePersistenceService
 * ============================================================
 *
 * Use Case 12: Data Persistence & System Recovery
 *
 * Description:
 * This class is responsible for persisting
 * critical system state to a plain text file.
 *
 * It supports:
 * - Saving room inventory state
 * - Restoring inventory on system startup
 *
 * No database or serialization framework
 * is used in this use case.
 *
 * @version 12.0
 */
public class FilePersistenceService {

    /**
     * Saves room inventory state to a file.
     *
     * Each line follows the format:
     * roomType=availableCount
     *
     * @param inventory centralized room inventory
     * @param filePath path to persistence file
     */
    public void saveInventory(RoomInventory inventory, String filePath) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath))) {
            for (String roomType : inventory.getRoomTypes()) {
                int count = inventory.getAvailableCount(roomType);
                writer.write(roomType + "=" + count);
                writer.newLine();
            }
        } catch (IOException e) {
            System.out.println("Failed to save inventory.");
        }
    }

    /**
     * Loads room inventory state from a file.
     *
     * @param inventory centralized room inventory
     * @param filePath path to persistence file
     */
    public void loadInventory(RoomInventory inventory, String filePath) {
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String trimmed = line.trim();
                if (trimmed.isEmpty() || !trimmed.contains("=")) {
                    continue;
                }
                String[] parts = trimmed.split("=", 2);
                String roomType = parts[0].trim();
                String countText = parts[1].trim();
                if (roomType.isEmpty()) {
                    continue;
                }
                int count = Integer.parseInt(countText);
                if (count < 0) {
                    continue;
                }
                inventory.addRoomType(roomType, count);
            }
        } catch (IOException | NumberFormatException e) {
            // Handle missing or corrupted data gracefully.
        }
    }
}
