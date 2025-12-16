
public class Room {
	private int roomNumber;
    private RoomType roomType;
    private int roomPricePerNight;
    
	public Room(int roomNumber, RoomType roomType, int roomPricePerNight) {
		this.roomNumber = roomNumber;
		this.roomType = roomType;
		this.roomPricePerNight = roomPricePerNight;
	}

	public int getRoomNumber() {
		return roomNumber;
	}

	public void setRoomNumber(int roomNumber) {
		this.roomNumber = roomNumber;
	}

	public RoomType getRoomType() {
		return roomType;
	}

	public void setRoomType(RoomType roomType) {
		this.roomType = roomType;
	}

	public int getRoomPricePerNight() {
		return roomPricePerNight;
	}

	public void setRoomPricePerNight(int roomPricePerNight) {
		this.roomPricePerNight = roomPricePerNight;
	}
}
