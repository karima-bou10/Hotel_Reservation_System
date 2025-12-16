import java.text.SimpleDateFormat;

public class Main {

	public static void main(String[] args) {
		Service service = new Service();
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
		
		// Create 3 Rooms
		service.setRoom(1, RoomType.STANDARD, 1000);
		service.setRoom(2, RoomType.JUNIOR, 2000);
		service.setRoom(3, RoomType.SUITE, 3000);
		
		// Create 2 Users
	    service.setUser(1, 5000);
	    service.setUser(2, 10000);
	    
	    // Test Bookings
	    try {
			service.bookRoom(1, 2, sdf.parse("30/06/2026"), sdf.parse("07/07/2026")); //Insufficient balance
		} catch (Exception e) {
            System.out.println(e.getMessage());
			e.printStackTrace();
		}
	    
	    try {
			service.bookRoom(1, 2, sdf.parse("07/07/2026"), sdf.parse("30/06/2026")); //Invalid dates
		} catch (Exception e) {
            System.out.println(e.getMessage());
			e.printStackTrace();
		}
	    
	    try {
			service.bookRoom(1, 1, sdf.parse("07/07/2026"), sdf.parse("08/07/2026")); //Passed
		} catch (Exception e) {
            System.out.println(e.getMessage());
			e.printStackTrace();
		}
	    
	    try {
			service.bookRoom(2, 1, sdf.parse("07/07/2026"), sdf.parse("09/07/2026")); //Room not available
		} catch (Exception e) {
            System.out.println(e.getMessage());
			e.printStackTrace();
		}
	    
	    try {
			service.bookRoom(2, 3, sdf.parse("07/07/2026"), sdf.parse("08/07/2026")); //Passed
		} catch (Exception e) {
            System.out.println(e.getMessage());
			e.printStackTrace();
		}
	    
	    //Update Room 1
	    service.setRoom(1, RoomType.SUITE, 10000);
	    
        // Print results
	    System.out.println("\n--- PRINT ALL USERS ---");
        service.printAllUsers();
        
        System.out.println("\n--- PRINT ALL ---");
        service.printAll();
	}
}
