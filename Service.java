import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class Service {
	SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");

	ArrayList<Room> rooms = new ArrayList<>();
    ArrayList<User> users = new ArrayList<>(); 
    ArrayList<Booking> bookings = new ArrayList<>();

    void setRoom(int roomNumber, RoomType roomType, int roomPricePerNight) {
    	Room room = findRoom(roomNumber);

        if (room != null) {
            room.setRoomType(roomType);
            room.setRoomPricePerNight(roomPricePerNight);
        } else {
            rooms.add(new Room(roomNumber, roomType, roomPricePerNight));
        }
    }
    
    void setUser(int userId, int balance) {
    	User user = findUser(userId);

        if (user == null) {
        	users.add(new User(userId, balance));
        }      
    }
    
    void bookRoom(int userId, int roomNumber, Date checkIn, Date checkOut) {
    	User user = findUser(userId);
        if (user == null) {
            throw new IllegalArgumentException("User not found");
        }

        Room room = findRoom(roomNumber);
        if (room == null) {
            throw new IllegalArgumentException("Room not found");
        }
        
        if (!checkIn.before(checkOut)) {
            throw new IllegalArgumentException("Invalid dates");
        }
        
        if (!isRoomAvailable(room, checkIn, checkOut)) {
            throw new IllegalArgumentException("Room not available");
        }
        
        int nights = (int) ((checkOut.getTime() - checkIn.getTime()) / (1000 * 60 * 60 * 24));
        int totalPrice = nights * room.getRoomPricePerNight();
        
        if (user.getBalance() < totalPrice) {
            throw new IllegalArgumentException("Insufficient balance");
        }
        
        user.setBalance(user.getBalance()-totalPrice);
        bookings.add(new Booking(user, room, checkIn, checkOut, totalPrice));
    }
    
    private User findUser(int userId) {
        for (User u : users) {
            if (u.getUserId() == userId) return u;
        }
        return null;
    }

    private Room findRoom(int roomNumber) {
        for (Room r : rooms) {
            if (r.getRoomNumber() == roomNumber) return r;
        }
        return null;
    }
    
    private boolean isRoomAvailable(Room room, Date checkIn, Date checkOut) {
        for (Booking b : bookings) {
            if (b.getRoom().getRoomNumber() == room.getRoomNumber()) {
                if (checkIn.before(b.getCheckOut()) && checkOut.after(b.getCheckIn())) {
                    return false;
                }
            }
        }
        return true;
    }
    
    void printAllUsers() {
		 System.out.println("ID || Balance");
    	for(int i = users.size()-1; i>=0 ; i--) {
    		User u = users.get(i);
    		System.out.println(u.getUserId() + "  || " + u.getBalance());
    	}
    }
    
    void printAll() {
        System.out.println("Rooms:");
    	System.out.println("Room Number || Room Type || Room Price/night");
        for (int i = rooms.size() - 1; i >= 0; i--) {
            Room r = rooms.get(i);
            System.out.println(r.getRoomNumber() + "\t|| " + r.getRoomType() + "\t|| " + r.getRoomPricePerNight());
        }

        System.out.println("\nBookings:");
    	System.out.println("UserID || User Balance || Booked Room || Room Type || Room Price/night || From || To || Total");
        for (int i = bookings.size() - 1; i >= 0; i--) {
            Booking b = bookings.get(i);
            System.out.println(b.getUser().getUserId() + "\t|| " + b.getUser().getBalance()
            		+ " \t|| " + b.getRoom().getRoomNumber() + "\t\t|| " + b.getRoom().getRoomType() + " || " + b.getRoom().getRoomPricePerNight()
            		+ " || " + sdf.format(b.getCheckIn()) + " || " + sdf.format(b.getCheckOut()) + " || " + b.getTotalPrice()
            );
        }
    }
}
