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
        availableByType.put(roomType, count);
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
}
