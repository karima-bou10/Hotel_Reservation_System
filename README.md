# Hotel Reservation System – Technical Test 2
This project is a simple Hotel Reservation System implemented in Java.

## Features
- Create users with a balance
- Create and update rooms
- Book rooms for a specific period
- Prevent double booking for the same room
- Update user balance after a successful booking

## Technical Choices
- In-memory storage using ArrayList (no database, no repository)
- Previous bookings are not impacted when a room is updated
- Booking price is calculated and stored at reservation time
- Basic exception handling for invalid operations

## How to run
Run the `Main` class to execute the test scenario and print the results.

## Design Questions (Bonus)
1. **Suppose we put all the functions inside the same service. Is this the recommended approach?**

   Putting all the functions inside a single service can work for a small technical test, but it is not recommended for a real-world application 
   because it violates the **Single Responsibility Principle**. In this design, the service becomes responsible for multiple concerns such as
   user management, room management, and booking logic, which makes the class grow large and difficult to maintain as the system evolves. 
   A better approach would be to separate these responsibilities into dedicated services (for example `UserService`, `RoomService`, and `BookingService`).

3. **In this design, we chose to have a function setRoom(..) that should not impact the previous bookings. What is another way? What is your recommendation?**

   Another approach would be to make rooms **immutable** and create a new version of the room whenever its price or type changes. 
   Existing bookings would keep a reference to the old room version, while new bookings would use the updated one. 
   In this implementation, the total booking price is stored at the time of reservation, which ensures that previous bookings remain unchanged even if a room is updated later.






