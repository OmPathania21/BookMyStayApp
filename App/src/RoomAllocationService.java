import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * ============================================================
 *  CLASS - RoomAllocationService
 * ============================================================
 *
 * Use Case 6: Reservation Confirmation & Room Allocation
 *
 * Description:
 * This class is responsible for confirming
 * booking requests and assigning rooms.
 *
 * It ensures:
 * - Each room ID is unique
 * - Inventory is updated immediately
 * - No room is double-booked
 *
 * @version 6.0
 */
public class RoomAllocationService {

    /**
     * Stores all allocated room IDs to
     * prevent duplicate assignments.
     */
    private final Set<String> allocatedRoomIds;

    /**
     * Stores assigned room IDs by room type.
     * Key   -> Room type
     * Value -> Set of assigned room IDs
     */
    private final Map<String, Set<String>> assignedRoomsByType;

    private final Map<String, Integer> roomTypeCounters;

    /**
     * Initializes allocation tracking structures.
     */
    public RoomAllocationService() {
        this.allocatedRoomIds = new HashSet<>();
        this.assignedRoomsByType = new HashMap<>();
        this.roomTypeCounters = new HashMap<>();
    }

    /**
     * Confirms a booking request by assigning
     * a unique room ID and updating inventory.
     *
     * @param reservation booking request
     * @param inventory centralized room inventory
     */
    public void allocateRoom(Reservation reservation, RoomInventory inventory) {
        String roomType = reservation.getRoomType();
        if (!inventory.isAvailable(roomType)) {
            System.out.println("No rooms available for type: " + roomType);
            return;
        }

        String roomId = generateRoomId(roomType);
        allocatedRoomIds.add(roomId);
        assignedRoomsByType
                .computeIfAbsent(roomType, k -> new HashSet<>())
                .add(roomId);
        inventory.allocateRoom(roomType);

        System.out.println(
                "Booking confirmed for Guest: "
                        + reservation.getGuestName()
                        + ", Room ID: "
                        + roomId
        );
    }

    /**
     * Generates a unique room ID
     * for the given room type.
     *
     * @param roomType type of room
     * @return unique room ID
     */
    private String generateRoomId(String roomType) {
        int nextNumber = roomTypeCounters.getOrDefault(roomType, 0);
        String roomId;
        do {
            nextNumber += 1;
            roomId = roomType + "-" + nextNumber;
        } while (allocatedRoomIds.contains(roomId));

        roomTypeCounters.put(roomType, nextNumber);
        return roomId;
    }
}
