import java.util.HashMap;
import java.util.Map;

/**
 * Tracks room availability for each room type.
 */
public class RoomInventory {
    private final Map<String, Integer> availableByType;

    public RoomInventory() {
        this.availableByType = new HashMap<>();
    }

    public void addRoomType(String roomType, int count) {
        if (count < 0) {
            throw new IllegalArgumentException("Room count cannot be negative.");
        }
        availableByType.put(roomType, count);
    }

    public boolean hasRoomType(String roomType) {
        return availableByType.containsKey(roomType);
    }

    public int getAvailableCount(String roomType) {
        Integer count = availableByType.get(roomType);
        return count == null ? -1 : count;
    }

    public boolean isAvailable(String roomType) {
        Integer count = availableByType.get(roomType);
        return count != null && count > 0;
    }

    public boolean allocateRoom(String roomType) {
        if (!isAvailable(roomType)) {
            return false;
        }
        availableByType.put(roomType, availableByType.get(roomType) - 1);
        return true;
    }

    public boolean restoreRoom(String roomType) {
        if (!hasRoomType(roomType)) {
            return false;
        }
        availableByType.put(roomType, availableByType.get(roomType) + 1);
        return true;
    }
}
